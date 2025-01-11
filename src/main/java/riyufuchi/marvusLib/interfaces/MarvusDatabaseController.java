package riyufuchi.marvusLib.interfaces;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.records.MarvusCategoryStatistic;
import riyufuchi.marvusLib.records.MarvusDataStatistics;
import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.sufuLib.interfaces.IDatabase;
import riyufuchi.sufuLib.interfaces.SufuDatabaseInterface;

/**
 * @author riyufuchi
 * @since ?
 * @version 11.01.2025
 */
public interface MarvusDatabaseController
{
	//Insert
	boolean insertCategory(String category);
	boolean insertEntity(String name);
	//Update
	boolean updateCategory(int categoryID, String replacementCategory);
	boolean updateEntity(int nameID, String replacementName);
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
	SufuDatabaseInterface<String, TransactionMacro> getMacrosTableController();
	IDatabase<Transaction> getTransactionsTableController();
	SufuDatabaseInterface<Integer, String> getCategoriesTableController();
	SufuDatabaseInterface<Integer, String> getEntitiesTableController();
}
