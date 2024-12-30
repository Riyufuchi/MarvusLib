package riyufuchi.marvusLib.dataUtils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import riyufuchi.marvusLib.data.Transaction;

public class FinancialCategoryTest
{
	private Transaction t1, t2;
	
	public FinancialCategoryTest()
	{
		t1 = new Transaction("T1", "C1", "300.125", "16.05.2023", "");
		t2 = new Transaction("T2", "C2", "300.125", "17.05.2023", "Note");
	}
	
	@Test
	public void testToString01()
	{
		assertEquals("T1 300.125 CZK", new FinancialCategory(t1).toString());
	}
	
	@Test
	public void testToString02()
	{
		FinancialCategory fc = new FinancialCategory(t1);
		fc.add(t2);
		assertEquals("T1 600.250 CZK", fc.toString());
	}
}
