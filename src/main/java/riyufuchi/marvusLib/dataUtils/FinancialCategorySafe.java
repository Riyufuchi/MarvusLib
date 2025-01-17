package riyufuchi.marvusLib.dataUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import riyufuchi.marvusLib.data.Money;
import riyufuchi.marvusLib.data.Transaction;

/**
 * Class representing transaction category and unites them into sum (cached value)
 * 
 * @author Riyufuchi
 * @since 17.01.2025
 * @version 17.01.2025
 */
public class FinancialCategorySafe implements Iterable<Transaction>
{
	private String category;
	private BigDecimal sum;
	private LinkedList<Transaction> transactions;
	
	/**
	 * Sets category name to <code>this.category = transaction.getCategory();</code>
	 * 
	 * @param transaction
	 */
	public FinancialCategorySafe(Transaction transaction)
	{
		if (transaction == null)
			transaction = new Transaction();
		this.transactions = new LinkedList<>();
		this.sum = new BigDecimal(0);
		this.category = transaction.getCategory();
		if (category == null || category.isBlank())
			this.category = "BlankCategory";
		add(transaction);
	}
	
	public FinancialCategorySafe(String name, Transaction transaction)
	{
		this(transaction);
		this.category = name;
		if (category == null || category.isBlank())
			this.category = "BlankCategory";
	}
	
	public void add(Transaction t)
	{
		transactions.add(t);
		sum = sum.add(t.getValue());
	}
	
	public void addAll(Iterable<Transaction> c)
	{
		Iterator<Transaction> it = c.iterator();
		while (it.hasNext())
		{
			add(it.next());
		}
	}
	
	public void set(int index, Transaction t)
	{
		transactions.set(index, t);
		sum = sum.add(t.getValue());
	}
	
	public void remove(Transaction t)
	{
		if (transactions.remove(t))
			sum = sum.subtract(t.getValue());
	}
	
	public void removeIf(Predicate<? super Transaction> filter)
	{
		Iterator<Transaction> it = transactions.iterator();
		Transaction t = null;
		while (it.hasNext())
		{
			t = it.next();
			if (filter.test(t))
			{
				sum = sum.subtract(t.getValue());
				it.remove();
			}
		}
	}
	
	public Transaction getFirst()
	{
		return transactions.getFirst();
	}
	
	public Transaction getLast()
	{
		return transactions.getLast();
	}
	
	public boolean isEmpty()
	{
		return transactions.isEmpty();
	}
	
	/**
	 * @return category sum
	 */
	public BigDecimal getSum()
	{
		return sum;
	}
	
	public String getCategory()
	{
		return category;
	}

	@Override
	public String toString()
	{
		if (transactions.isEmpty())
			return category + " 0 " + Money.getDefaultCurrency();
		return category + " " + sum.toPlainString() + " " + transactions.getFirst().getCurrency();
	}

	@Override
	public Iterator<Transaction> iterator()
	{
		return new Iterator<Transaction>()
		{
			Iterator<Transaction> it = transactions.iterator();
			
			@Override
			public Transaction next()
			{
				return it.next();
			}
			
			@Override
			public boolean hasNext()
			{
				return it.hasNext();
			}
		};
	}
}
