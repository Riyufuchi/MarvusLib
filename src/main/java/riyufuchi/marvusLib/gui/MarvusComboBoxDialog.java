package riyufuchi.marvusLib.gui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import riyufuchi.marvusLib.database.MarvusTableUtils;
import riyufuchi.marvusLib.enums.MarvusAction;
import riyufuchi.marvusLib.records.MarvusComboBoxDialogTexts;
import riyufuchi.sufuLib.gui.SufuDataDialog;
import riyufuchi.sufuLib.gui.utils.SufuComponentTools;
import riyufuchi.sufuLib.gui.utils.SufuDialogHelper;
import riyufuchi.sufuLib.gui.utils.SufuFactory;
import riyufuchi.sufuLib.gui.utils.SufuGuiTools;
import riyufuchi.sufuLib.interfaces.SufuDatabaseInterface;
import riyufuchi.sufuLib.records.SufuPair;
import riyufuchi.sufuLib.records.SufuRow;

/**
 * This dialog helps with working with enumeration tables that contains categories, types and etc...
 * 
 * @author riyufuchi
 * @since 11.01.2025
 * @version 11.01.2025
 */
@SuppressWarnings("serial")
public final class MarvusComboBoxDialog extends SufuDataDialog<SufuPair<SufuRow<Integer, String>, SufuRow<Integer, String>>>
{
	private JComboBox<SufuRow<Integer, String>> mainCB, userInputCB;
	private SufuDatabaseInterface<Integer, String> tableController;
	private MarvusAction dialogAction;
	
	/**
	 * 
	 * @param parentFrame
	 * @param texts
	 * @param tableController
	 * @param dialogAction
	 */
	public MarvusComboBoxDialog(JFrame parentFrame, MarvusComboBoxDialogTexts texts, SufuDatabaseInterface<Integer, String> tableController, MarvusAction dialogAction)
	{
		super(texts.title(), parentFrame, DialogType.OK, false, false, false, new Object[]{ dialogAction.toString() });
		this.dialogAction = dialogAction;
		this.tableController = tableController;
		if (dialogAction != MarvusAction.ADD && tableController == null) // When adding there is no need to handle data from a db table
			this.dialogAction = MarvusAction.NONE; // This prevents creation of UI or any other need to use table controller
		createInputs(getPane());
		SufuGuiTools.addLabels(this, texts.label1(), texts.label2());
		pack();
	}

	@Override
	protected void createInputs(JPanel pane)
	{
		switch (dialogAction)
		{
			case ADD -> createAddUI(pane);
			case EDIT -> createEditUI(pane);
			case DELETE -> createDeleteUI(pane);
			default -> SufuDialogHelper.errorDialog(parentFrame, "Incorrect dialog action: " + dialogAction.toString(), getTitle() + "error");
		}
	}

	@Override
	protected void onOK()
	{
		switch (dialogAction)
		{
			case ADD -> onOkAdd();
			case EDIT -> onOkEdit();
			case DELETE -> onOkDelete();
			default -> closeDialog();
		}
	}
	
	// Is methods
	
	private boolean isUserInputBlank()
	{
		return ((String)userInputCB.getEditor().getItem()).isBlank();
	}
	
	private boolean isUserInputEqual()
	{
		return SufuComponentTools.extractComboboxValue(mainCB).entity().equals(SufuComponentTools.extractComboboxValue(userInputCB).entity());
	}
	
	private boolean isUserInputEqual(SufuRow<Integer, String> row)
	{
		return SufuComponentTools.extractComboboxValue(mainCB).entity().equals(row.entity());
	}
	
	// On methods
	
	private void onOkAdd()
	{
		if (isUserInputBlank())
			return;
		data = new SufuPair<>(null, new SufuRow<>(0, (String)userInputCB.getEditor().getItem()));
		closeDialog();
	}
	
	private void onOkEdit()
	{
		SufuRow<Integer, String> row = new SufuRow<>(0, (String)userInputCB.getEditor().getItem());
		if (row.entity().isBlank() || isUserInputEqual(row))
			return;
		data = new SufuPair<>(SufuComponentTools.extractComboboxValue(mainCB), row);
		closeDialog();
	}
	
	private void onOkDelete()
	{
		if (isUserInputEqual())
			return;
		data = new SufuPair<>(SufuComponentTools.extractComboboxValue(mainCB), SufuComponentTools.extractComboboxValue(userInputCB));
		closeDialog();
	}
	
	// Create methods
	
	private void createAddUI(JPanel pane)
	{
		mainCB = SufuFactory.newCombobox(new SufuRow<>(0, "                    "));
		mainCB.setEnabled(false);
		userInputCB = SufuFactory.newCombobox(new SufuRow<>(0, ""));
		userInputCB.setEditable(true);
		SufuGuiTools.addComponents(this, 1, 0, mainCB, userInputCB);
	}
	
	private void createEditUI(JPanel pane)
	{
		createAddUI(pane);
		mainCB.removeAllItems();
		for (SufuRow<Integer, String> row : MarvusTableUtils.selectOrdered(tableController))
			mainCB.addItem(row);
		mainCB.setEnabled(true);
		mainCB.addActionListener(evt ->
			SufuComponentTools.setSelectedItemGeneric(userInputCB, SufuComponentTools.extractComboboxValue(mainCB)));
		mainCB.setSelectedIndex(0);
	}
	
	private void createDeleteUI(JPanel pane)
	{
		createEditUI(pane);
		userInputCB.setEditable(false);
		userInputCB.removeAllItems();
		for (int i = 0; i < mainCB.getItemCount(); i++)
			userInputCB.addItem(mainCB.getItemAt(i));
	}
}
