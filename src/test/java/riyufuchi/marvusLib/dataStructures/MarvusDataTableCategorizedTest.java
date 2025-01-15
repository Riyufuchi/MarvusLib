package riyufuchi.marvusLib.dataStructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import riyufuchi.marvusLib.data.Money;
import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.libTesting.TestData;

@SuppressWarnings("deprecation")
public class MarvusDataTableCategorizedTest
{
	
	private MarvusDataTableCategorized ct = new MarvusDataTableCategorized();
	
	public MarvusDataTableCategorizedTest()
	{
		this.ct = new MarvusDataTableCategorized();
	}
	
	@Test
	public void testAdd01()
	{
		ct.add(TestData.transaction1);
		assertEquals(TestData.transaction1, ct.get(4, 0).get(0));
	}
	
	@Test
	public void testAdd02()
	{
		ct.add(TestData.transaction1);
		ct.add(TestData.transaction1);
		assertEquals(2, ct.get(4, 0).size());
	}
	
	@Test
	public void testAdd03()
	{
		ct.add(TestData.transaction1);
		ct.add(TestData.transaction2);
		assertEquals(true, ct.add(new Transaction()));
	}
	
	@Test
	public void testAdd04()
	{
		ct.add(TestData.transaction1);
		ct.add(new Transaction());
		assertEquals(true, ct.add(TestData.transaction2));
	}
	
	@Test
	public void testAdd05()
	{
		ct.add(TestData.transaction1);
		ct.add(new Transaction());
		assertEquals(true, ct.add(TestData.transaction1));
	}
	
	@Test
	public void testAdd06()
	{
		assertEquals(false, ct.add(null));
	}
	
	@Test
	public void testSet01()
	{
		ct.add(TestData.transaction1);
		Transaction t = TestData.transaction1;
		t.setValue("1000");
		ct.set(t);
		t = null;
		assertEquals(1000, ct.get(4, 0).getFirst().getValue().intValueExact());
	}
	@Test
	public void testSet02()
	{
		ct.add(TestData.transaction1);
		Transaction t = TestData.transaction1;
		t.setValue("1000");
		ct.set(t);
		assertEquals(1, ct.get(4, 0).size());
	}
	
	@Test
	public void testRemove01()
	{
		ct.add(TestData.transaction1);
		ct.remove(TestData.transaction1);
		assertEquals(0, ct.size());
	}
	
	@Test
	public void testRemove02()
	{
		ct.add(TestData.transaction2);
		ct.remove(TestData.transaction2.getDate().getMonthValue(), TestData.transaction2.getName(), TestData.transaction2.getID());
		assertEquals(0, ct.get(4, 0).size());
	}
	
	@Test
	public void testRemove03()
	{
		Transaction t = new Transaction("T", "CT", "10", "01.02.2023", "note"); 
		Transaction tr = new Transaction("T", "CT", "10", "01.02.2023", "note");
		ct.add(t);
		ct.add(tr);
		ct.add(new Transaction("T", "CT", "10", "01.02.2023", "note"));
		ct.remove(tr.getDate().getMonthValue(), tr.getName(), tr.getID());
		assertEquals(t.getID() , ct.get(1, 0).get(0).getID());
	}
	
	@Test
	public void testRemove04()
	{
		Transaction t = new Transaction("T", "CT", "10", "01.02.2023", "note"); 
		Transaction tr = new Transaction("T", "CT", "10", "01.02.2023", "note");
		ct.add(t);
		ct.add(tr);
		ct.add(new Transaction("T", "CT", "10", "01.02.2023", "note"));
		ct.remove(tr);
		assertEquals(t.getID() , ct.get(1, 0).get(0).getID());
	}
	
	@Test
	public void testRemove05()
	{
		assertEquals(false , ct.remove(null));
	}
	
	@Test
	public void testRemove06()
	{
		assertEquals(false , ct.remove(new Money(0)));
	}
	
	@Test
	public void testRemove07()
	{
		assertEquals(false , ct.remove(new Transaction()));
	}
	
	@Test
	public void testRemove08()
	{
		Transaction t = new Transaction("T", "CT", "10", "01.02.2023", "note"); 
		Transaction tr = new Transaction("T", "CT", "10", "01.02.2023", "note");
		ct.add(t);
		ct.add(tr);
		ct.remove(tr);
		assertEquals(false , ct.remove(tr));
	}
	
	@Test
	public void testSize01()
	{
		ct.add(TestData.transaction1);
		assertEquals(1, ct.size());
	}
	
	@Test
	public void testSize02()
	{
		ct.add(TestData.transaction1);
		ct.add(TestData.transaction2);
		assertEquals(2, ct.size());
	}
	
	@Test
	public void testSize03()
	{
		ct.add(TestData.transaction1);
		ct.add(TestData.transaction2);
		ct.remove(TestData.transaction2);
		assertEquals(1, ct.size());
	}
	
	@Test
	public void testIsEmpty01()
	{
		assertEquals(true, ct.isEmpty());
	}
	
	@Test
	public void testIsEmpty02()
	{
		ct.add(TestData.transaction1);
		assertEquals(false, ct.isEmpty());
	}
	
	@Test
	public void testContains01()
	{
		assertEquals(false, ct.contains(new Transaction()));
	}
	
	@Test
	public void testContains02()
	{
		ct.add(TestData.transaction1);
		ct.add(TestData.transaction2);
		ct.add(new Transaction());
		assertEquals(true, ct.contains(TestData.transaction2));
	}
}
