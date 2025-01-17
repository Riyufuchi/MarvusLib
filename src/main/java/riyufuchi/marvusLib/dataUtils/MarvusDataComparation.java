package riyufuchi.marvusLib.dataUtils;

import java.util.Comparator;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.enums.MarvusTransactionOrderBy;

/**
 * @author Riyufuchi
 * @since 18.04.2023
 * @version 17.01.2025
 */
public class MarvusDataComparation
{
	private MarvusDataComparation()
	{}
	
	@Deprecated
	public static Comparator<FinancialCategory> compareFinancialCategory(MarvusTransactionOrderBy compareMethod)
	{
		if(compareMethod == null)
			compareMethod = MarvusTransactionOrderBy.NAME;
		return switch (compareMethod)
		{
			case NAME -> (m1, m2) -> { return m1.getCategory().compareTo(m2.getCategory()); };
			case LOWEST_TO_HIGHEST -> (m1, m2) -> { return m1.getSum().compareTo(m2.getSum()); };
			case HIGHEST_TO_LOWEST -> (m1, m2) -> { return m2.getSum().compareTo(m1.getSum()); };
			default -> (m1, m2) -> { return m1.getCategory().compareTo(m2.getCategory()); };
		};
	}
	
	public static Comparator<FinancialCategorySafe> compareFinancialCategorySafe(MarvusTransactionOrderBy compareMethod)
	{
		if(compareMethod == null)
			compareMethod = MarvusTransactionOrderBy.NAME;
		return switch (compareMethod)
		{
			case NAME -> (m1, m2) -> { return m1.getCategory().compareTo(m2.getCategory()); };
			case LOWEST_TO_HIGHEST -> (m1, m2) -> { return m1.getSum().compareTo(m2.getSum()); };
			case HIGHEST_TO_LOWEST -> (m1, m2) -> { return m2.getSum().compareTo(m1.getSum()); };
			default -> (m1, m2) -> { return m1.getCategory().compareTo(m2.getCategory()); };
		};
	}
	
	
	public static Comparator<Transaction> compareBy(MarvusTransactionOrderBy compareMethod)
	{
		if(compareMethod == null)
			compareMethod = MarvusTransactionOrderBy.OLDEST_TO_NEWEST;
		return switch (compareMethod)
		{
			case NAME -> (m1, m2) -> { return m1.getName().compareTo(m2.getName()); };
			case CATEGORY -> (m1, m2) -> { return m1.getCategory().compareTo(m2.getCategory()); };
			case LOWEST_TO_HIGHEST -> (m1, m2) -> { return m1.getValue().compareTo(m2.getValue()); };
			case NEWEST_TO_OLDEST ->  (m1, m2) -> { return m2.getDate().compareTo(m1.getDate()); };
			case OLDEST_TO_NEWEST -> (m1, m2) -> { return m1.getDate().compareTo(m2.getDate()); };
			case HIGHEST_TO_LOWEST -> (m1, m2) -> { return m2.getValue().compareTo(m1.getValue()); };
			case ID -> (m1, m2) -> compareID(m1, m2);
			default -> (m1, m2) -> compareID(m1, m2);
		};
	}
	
	private static int compareID(Transaction m1, Transaction m2)
	{
		if (m1.getID() > m2.getID())
			return 1;
		if (m1.getID() < m2.getID())
			return -1;
		return 0;
	}
}
