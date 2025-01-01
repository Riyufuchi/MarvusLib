package riyufuchi.marvusLib.interfaces;

import riyufuchi.marvusLib.records.MarvusDataStatistics;

/**
 * This interface should make easier to make application work with SQL database
 *
 * @author riyufuchi
 * @since 09.09.2024
 * @version 01.01.2025
 */
public interface MarvusQuerriable
{
	boolean updateAtribbute(String attr, String oldValue, String newValue);
	boolean updateAtribbute(String whereAttr, String whereValue, String targetAttr, String oldValue, String newValue);
	MarvusDataStatistics createDataStatistics(int year);
}
