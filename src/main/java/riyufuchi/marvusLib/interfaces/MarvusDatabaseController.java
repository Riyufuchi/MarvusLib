package riyufuchi.marvusLib.interfaces;

import java.time.Month;
import java.util.Collection;
import java.util.LinkedList;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.dataUtils.FinancialCategorySafe;
import riyufuchi.marvusLib.records.MarvusCategoryStatistic;
import riyufuchi.marvusLib.records.MarvusDataStatistics;
import riyufuchi.marvusLib.records.TransactionMacro;
import riyufuchi.marvusLib.records.MarvusYearOverview;
import riyufuchi.sufuLib.interfaces.SufuIDatabase;

/**
 * @author riyufuchi
 * @since ?
 * @version 17.01.2025
 */
public interface MarvusDatabaseController
{
	//Insert
	boolean insertAllTransactions(Collection<Transaction> transactionList);
	boolean insertTransaction(Transaction transaction);
	boolean insertCategory(String category);
	boolean insertEntity(String name);
	//Update
	boolean updateTransaction(Transaction transaction);
	boolean updateCategory(int categoryID, String replacementCategory);
	boolean updateEntity(int nameID, String replacementName);
	// Remove
	boolean removeTransaction(Transaction transactionID);
	boolean removeCategory(int categoryID, int replacementCategoryID);
	boolean removeEntity(int nameID, int replacementNameID);
	// Generic
	boolean updateAtribbute(String attr, String oldValue, String newValue);
	boolean updateAtribbute(String whereAttr, String whereValue, String targetAttr, String oldValue, String newValue);
	// Calculations
	int assumeYear();
	// Create
	boolean createBackup();
	MarvusDataStatistics createDataStatistics(int year);
	MarvusCategoryStatistic createCategoryStatistic(String category, int year);
	MarvusYearOverview createYearOverview(int year);
	// Create and get
	LinkedList<Transaction> getMonth(Month month);
	LinkedList<FinancialCategorySafe> getCategorizedMonth(Month month);
	LinkedList<FinancialCategorySafe> getCategorizedMonthByNames(Month month);
	LinkedList<FinancialCategorySafe> getCategorizedYearByCategories(int year);
	// Get other table controllers
	SufuIDatabase<String, TransactionMacro> getMacrosTable();
	SufuIDatabase<Integer, Transaction> getTransactionsTable();
	SufuIDatabase<Integer, String> getCategoriesTable();
	SufuIDatabase<Integer, String> getEntitiesTable();
}
