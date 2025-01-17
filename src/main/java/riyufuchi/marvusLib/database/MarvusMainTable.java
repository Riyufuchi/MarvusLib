package riyufuchi.marvusLib.database;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.dataStructures.MarvusDataTable;
import riyufuchi.marvusLib.dataUtils.FinancialCategorySafe;
import riyufuchi.marvusLib.dataUtils.MarvusDataComparation;
import riyufuchi.marvusLib.enums.MarvusTransactionOrderBy;
import riyufuchi.marvusLib.records.MarvusYearOverview;
import riyufuchi.sufuLib.interfaces.SufuIDatabase;
import riyufuchi.sufuLib.records.SufuRow;

/**
 * This class doesn't represent actual connection to database, just "simulates" it
 * 
 * @author Riyufuchi
 * @since 1.95 - 12.02.2024
 * @version 17.01.2025
 */
public class MarvusMainTable extends MarvusDataTable implements SufuIDatabase<Integer, Transaction>
{
	private static final long serialVersionUID = 3L;
	protected transient Consumer<String> errorHandler;
	private transient Comparator<FinancialCategorySafe> sorter;
	
	public MarvusMainTable()
	{
		this(e -> System.out.println(e));
	}
	
	public MarvusMainTable(Consumer<String> errorHandler)
	{
		super();
		this.sorter = MarvusDataComparation.compareFinancialCategorySafe(MarvusTransactionOrderBy.NAME);
		if (errorHandler == null)
			this.errorHandler = e -> System.out.println(e);
		else
			this.errorHandler = errorHandler;
	}
	
	public void sort()
	{
		for (Month i : Month.values())
			Collections.sort(getCategorizedMonth(i), sorter);
	}
	
	// SETTERS
	
	/**
	 * Set new comparator and sort by it
	 * 
	 * @param comp
	 */
	public void setComparator(Comparator<FinancialCategorySafe> comp)
	{
		if (comp == null)
			return;
		sorter = comp;
		sort();
	}
	
	public void setErrorHandler(Consumer<String> errorHandler)
	{
		if (errorHandler == null)
			return;
		this.errorHandler = errorHandler;
	}
	
	// GETTERS
	
	public LinkedList<FinancialCategorySafe> getCategorizedYearByCategories(int year)
	{
		LinkedList<FinancialCategorySafe> list = new LinkedList<>();
		FinancialCategorySafe holder = null;
		for (Transaction t : this)
		{
			if (t.getDate().getYear() != year)
				continue;
			holder = new FinancialCategorySafe(t);
			for (FinancialCategorySafe mc : list)
			{
				if (mc.getCategory().equals(holder.getCategory()))
				{
					mc.add(t);
					holder = null;
					break;
				}
			}
			if(holder != null)
				list.add(holder);
		}
		return list;
	}
	
	//TODO: Optimize this more
	public MarvusYearOverview getYearOverview(int year)
	{
		BigDecimal[] incomes = new BigDecimal[12];
		BigDecimal[] spendings = new BigDecimal[12];
		BigDecimal[] outcomes = new BigDecimal[12];
		BigDecimal[] totalOutcomes = new BigDecimal[12];
		final BigDecimal ZERO = BigDecimal.ZERO;
		int index = 0;
		for (int i = 0; i < 12; i++)
		{
			incomes[i] = new BigDecimal(0);
			spendings[i] = new BigDecimal(0);
		}
		for (Transaction t : this)
		{
			if (t.getDate().getYear() == year)
			{
				index = t.getDate().getMonthValue() - 1;
				switch (t.getValue().compareTo(ZERO))
				{
					case 1 -> incomes[index] = incomes[index].add(t.getValue());
					case -1 -> spendings[index] = spendings[index].add(t.getValue());
					case 0 -> errorHandler.accept("Zero value detected for:\n" + t.getID() + " -> " + t.toString());
				}
			}
		}
		BigDecimal totalIncome = new BigDecimal(0);
		BigDecimal totalSpendings = new BigDecimal(0);
		int x = 1;
		totalOutcomes[0] = incomes[0].add(spendings[0]);
		outcomes[0] = totalOutcomes[0].add(BigDecimal.ZERO);
		totalIncome = totalIncome.add(incomes[0]);
		totalSpendings = totalSpendings.add(spendings[0]);
		for (int i = 1; i < 12; i++)
		{	
			totalIncome = totalIncome.add(incomes[i]);
			totalSpendings = totalSpendings.add(spendings[i]);
			outcomes[i] = incomes[i].add(spendings[i]);
			totalOutcomes[x] = totalOutcomes[x - 1].add(outcomes[i]);
			x++;
		}
		return new MarvusYearOverview(year, incomes, spendings, outcomes, totalIncome, totalSpendings, totalIncome.add(totalSpendings), totalOutcomes);
	}
	
	public LinkedList<FinancialCategorySafe> getCategorizedMonth(Month month)
	{
		LinkedList<FinancialCategorySafe> list = new LinkedList<>();
		if (month == null)
			return list;
		FinancialCategorySafe holder = null;
		for (Transaction t : getMonth(month))
		{
			holder = new FinancialCategorySafe(t);
			for (FinancialCategorySafe mc : list)
			{
				if (mc.getCategory().equals(holder.getCategory()))
				{
					mc.add(t);
					holder = null;
					break;
				}
			}
			if(holder != null)
				list.add(holder);
		}
		return list;
	}
	
	public LinkedList<FinancialCategorySafe> getCategorizedMonthByNames(Month month)
	{
		LinkedList<FinancialCategorySafe> list = new LinkedList<>();
		if (month == null)
			return list;
		FinancialCategorySafe holder = null;
		for (Transaction t : getMonth(month))
		{
			holder = new FinancialCategorySafe(t.getName(), t);
			for (FinancialCategorySafe mc : list)
			{
				if (mc.getCategory().equals(holder.getCategory()))
				{
					mc.add(t);
					holder = null;
					break;
				}
			}
			if(holder != null)
				list.add(holder);
		}
		return list;
	}

	@Override
	public Optional<Transaction> getByID(final Integer ID)
	{
		for(Month i : Month.values())
		{
			for(Transaction t : getMonth(i))
			{
				if (t.getID() == ID)
					return Optional.of(t);
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean delete(Integer ID)
	{
		Transaction t = new Transaction();
		t.setID(ID);
		return super.remove(t);
	}

	@Override
	public boolean set(Integer ID, Transaction e)
	{
		return set(e);
	}

	@Override
	public List<Transaction> getData()
	{
		return toList();
	}

	@Override
	public LinkedList<SufuRow<Integer, Transaction>> getRows()
	{
		LinkedList<Transaction> list = toList();
		LinkedList<SufuRow<Integer, Transaction>> listRows = new LinkedList<>();
		for (Transaction t : list)
			listRows.add(new SufuRow<>(t.getID(), t));
		return listRows;
	}

	@Override
	public boolean add(Integer ID, Transaction e)
	{
		return add(e);
	}

	@Override
	public int getCount()
	{
		return size();
	}

	@Override
	public LinkedList<SufuRow<Integer, Transaction>> getRows(Comparator<SufuRow<Integer, Transaction>> comparator)
	{
		return null;
	}
	
	public Consumer<String> getErrorHandler()
	{
		return errorHandler;
	}
}
