package riyufuchi.marvusLib.records;

import java.io.Serializable;

import riyufuchi.sufuLib.interfaces.CSVable;

public record TransactionMacro(String name, String category, String value) implements CSVable, Serializable
{
	@Override
	public TransactionMacro fromCSV(String[] arr)
	{
		return new TransactionMacro(arr[0], arr[1], arr[2]);
	}

	@Override
	public String toCSV()
	{
		return name + ";" + category + ";" + value;
	}
}
