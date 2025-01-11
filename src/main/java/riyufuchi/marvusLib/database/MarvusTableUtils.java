package riyufuchi.marvusLib.database;

import java.util.LinkedList;
import java.util.List;

import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.sufuLib.records.SufuSimpleRow;
import riyufuchi.sufuLib.interfaces.IDatabase;
import riyufuchi.sufuLib.interfaces.SufuITableDB;
import riyufuchi.sufuLib.records.SufuRowDB;

public final class MarvusTableUtils
{
	public static List<String> selectOrdered(List<String> table)
	{
		table.sort((t1, t2) -> { return t1.compareTo(t2); });
		return table;
	}
	
	public static LinkedList<SufuSimpleRow<String>> selectOrdered(IDatabase<String> data)
	{
		LinkedList<SufuSimpleRow<String>> dataList = data.getRows();
		dataList.sort((t1, t2) -> { return t1.entity().compareTo(t2.entity()); });
		return dataList;
	}
	
	public static LinkedList<SufuRowDB<String, TransactionMacro>> selectMacroOrdered(SufuITableDB<String, TransactionMacro> macros)
	{
		return macros.getRows((t1, t2) -> t1.entity().name().compareTo(t2.entity().name()));
	}
}
