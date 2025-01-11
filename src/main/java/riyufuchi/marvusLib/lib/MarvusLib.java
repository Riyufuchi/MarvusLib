package riyufuchi.marvusLib.lib;

import riyufuchi.sufuLib.records.SufuLibInfo;

/**
 * @author riyufuchi
 * @since 30.12.2024
 * @version 31.12.2024
 */
public final class MarvusLib
{
	public static final SufuLibInfo INFO = new SufuLibInfo("MarvusLib", "1.7", "Riyufuchi", "2022 - 2025",
		"This version is under general license - /riyufuchi/marvusLib/lib/LICENSE.TXT", 
			MarvusLib.class.getResourceAsStream("/riyufuchi/marvusLib/lib/LICENSE.TXT"));
	
	private MarvusLib() {}
}
