package riyufuchi.marvusLib.dataUtils;

import riyufuchi.marvusLib.data.Money;
import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.dataCalculations.MoneyCalculationsGeneric;

/**
 * Class representing transaction category and unites them into sum<br><br>
 *
 * @author Riyufuchi
 * @since 18.04.2023
 * @version 17.01.2025
 */
@Deprecated
public class FinancialCategory extends MoneyCalculationsGeneric<Transaction>
{
	private String category;
	
	/**
	 *  Sets category name to <code>this.category = transaction.getName();</code> and this class dosn't cache sum
	 *  
	 * @param transaction
	 */
	public FinancialCategory(Transaction transaction)
	{
		super();
		if (transaction == null)
			transaction = new Transaction();
		this.category = transaction.getName();
		add(transaction);
	}
	
	public FinancialCategory(String name, Transaction transaction)
	{
		super();
		this.category = name;
		if (transaction == null)
			transaction = new Transaction();
		add(transaction);
	}
	
	public String getCategory()
	{
		return category;
	}

	@Override
	public String toString()
	{
		if (!isEmpty())
			return category + " " + getSum() + " " + getFirst().getCurrency();
		return category + " " + getSum() + " " + Money.getDefaultCurrency();
	}
}
