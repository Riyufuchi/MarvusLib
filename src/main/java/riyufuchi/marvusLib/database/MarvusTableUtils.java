package riyufuchi.marvusLib.database;

import java.util.LinkedList;
import java.util.List;

import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.sufuLib.records.SufuSimpleRow;
import riyufuchi.sufuLib.database.SufuTableDB;
import riyufuchi.sufuLib.records.SufuRowDB;

public final class MarvusTableUtils
{
	public static List<String> selectOrdered(List<String> table)
	{
		table.sort( (t1, t2) -> { return t1.compareTo(t2); });
		return table;
	}
	
	public static LinkedList<SufuSimpleRow<String>> selectOrdered(MarvusDatabaseTable<String> table)
	{
		return table.getRows((t1, t2) -> t1.entity().compareTo(t2.entity()));
	}
	
	public static LinkedList<SufuRowDB<String, TransactionMacro>> selectMacroOrdered(SufuTableDB<String, TransactionMacro> macros)
	{
		return macros.getRows((t1, t2) -> t1.entity().name().compareTo(t2.entity().name()));
	}
}
