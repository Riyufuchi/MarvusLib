package riyufuchi.marvusLib.database;

import javax.swing.table.AbstractTableModel;

import riyufuchi.marvusLib.data.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author riyufuchi
 * @since 26.11.2024
 * @version 30.12.2024
 */
public final class TransactionTableModel extends AbstractTableModel
{
	private static final String[] COLUMN_NAMES = { "Name", "Category", "Value", "Currency", "Date", "Note" };
	private final List<Transaction> transactions;

	public TransactionTableModel(List<Transaction> transactions)
	{
		this.transactions = transactions;
	}

	@Override
	public int getRowCount()
	{
		return transactions.size();
	}

	@Override
	public int getColumnCount()
	{
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Transaction t = transactions.get(rowIndex);
		switch (columnIndex)
		{
			case 0: return t.getName();
			case 1: return t.getCategory();
			case 2: return t.getValue();
			case 3: return t.getCurrency();
			case 4: return t.getStringDate();
			case 5: return t.getNote();
			default: return null;
		}
	}
	
	public Transaction getTransactionAt(int rowIndex)
	{
		return transactions.get(rowIndex);
	}

	@Override
	public String getColumnName(int column)
	{
		return COLUMN_NAMES[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		if (columnIndex == 2)
			return BigDecimal.class; // For numeric sorting
		return String.class;
	}
}
