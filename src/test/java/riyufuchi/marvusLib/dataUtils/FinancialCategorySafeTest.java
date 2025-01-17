package riyufuchi.marvusLib.dataUtils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import riyufuchi.marvusLib.data.Transaction;

public class FinancialCategorySafeTest
{
	private Transaction t1, t2;
	
	public FinancialCategorySafeTest()
	{
		t1 = new Transaction("T1", "C1", "300.125", "16.05.2023", "");
		t2 = new Transaction("T2", "C2", "300.125", "17.05.2023", "Note");
	}
	
	@Test
	public void testSum01()
	{
		FinancialCategorySafe fc = new FinancialCategorySafe(t1);
		fc.add(t2);
		fc.removeIf(t -> t.getName().equals("T1"));
		assertEquals("C1 300.125 CZK", fc.toString());
	}
	
	@Test
	public void testSum02()
	{
		FinancialCategorySafe fc = new FinancialCategorySafe(t1);
		fc.remove(t1);
		assertEquals("C1 0 CZK", fc.toString());
	}
	
	@Test
	public void testSum03()
	{
		FinancialCategorySafe fc = new FinancialCategorySafe(t1);
		fc.remove(t1);
		assertEquals("C1 0 CZK", fc.toString());
	}
	
	@Test
	public void testToString01()
	{
		assertEquals("C1 300.125 CZK", new FinancialCategorySafe(t1).toString());
	}
	
	@Test
	public void testToString02()
	{
		FinancialCategorySafe fc = new FinancialCategorySafe(t1);
		fc.add(t2);
		assertEquals("C1 600.250 CZK", fc.toString());
	}
}
