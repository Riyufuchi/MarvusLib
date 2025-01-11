package riyufuchi.marvusLib.interfaces;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.records.MarvusCategoryStatistic;
import riyufuchi.marvusLib.records.MarvusDataStatistics;
import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.sufuLib.interfaces.IDatabase;
import riyufuchi.sufuLib.interfaces.SufuITableDB;

/**
 * @author riyufuchi
 * @since ?
 * @version 11.01.2025
 */
public interface MarvusDatabaseController
{
	// Remove
	boolean removeCategory(int categoryID, int replacementCategoryID);
	boolean removeEntity(int nameID, int replacementNameID);
	// Generic
	boolean updateAtribbute(String attr, String oldValue, String newValue);
	boolean updateAtribbute(String whereAttr, String whereValue, String targetAttr, String oldValue, String newValue);
	// Create
	MarvusDataStatistics createDataStatistics(int year);
	MarvusCategoryStatistic createCategoryStatistic(String category, int year);
	// Get other table controllers
	SufuITableDB<String, TransactionMacro> getMacrosTableController();
	IDatabase<Transaction> getTransactionsTableController();
	IDatabase<String> getCategoriesTableController();
	IDatabase<String> getEntitiesTableController();
}
