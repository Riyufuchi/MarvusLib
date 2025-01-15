package riyufuchi.marvusLib.database;

import java.util.LinkedList;
import java.util.List;

import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.sufuLib.interfaces.SufuIDatabase;
import riyufuchi.sufuLib.records.SufuRow;

public final class MarvusTableUtils
{
	public static List<String> selectOrdered(List<String> table)
	{
		table.sort((t1, t2) -> { return t1.compareTo(t2); });
		return table;
	}
	
	public static <E> LinkedList<SufuRow<E, String>> selectOrdered(SufuIDatabase<E, String> data)
	{
		LinkedList<SufuRow<E, String>> dataList = data.getRows();
		dataList.sort((t1, t2) -> { return t1.entity().compareTo(t2.entity()); });
		return dataList;
	}
	
	public static LinkedList<SufuRow<String, TransactionMacro>> selectMacroOrdered(SufuIDatabase<String, TransactionMacro> macros)
	{
		return macros.getRows((t1, t2) -> t1.entity().name().compareTo(t2.entity().name()));
	}
}
