package riyufuchi.marvusLib.lib;

import riyufuchi.sufuLib.records.SufuLibInfo;

public class MarvusLib
{
	public static final String VERSION = "1.4";
	private static final String licensedTo =  "This version is under general license - /riyufuchi/marvusLib/lib/LICENSE.TXT";
	public static final SufuLibInfo INFO = new SufuLibInfo("MarvusLib", VERSION, "Riyufuchi", "2022 - 2024", licensedTo, 
			MarvusLib.class.getResourceAsStream("/riyufuchi/marvusLib/lib/LICENSE.TXT"));
	
	public static void main(String[] args)
	{
	}
}
