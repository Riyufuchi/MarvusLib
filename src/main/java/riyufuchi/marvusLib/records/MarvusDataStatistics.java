package riyufuchi.marvusLib.records;

import java.math.BigDecimal;

/**
 * @author riyufuchi
 * @since 01.01.2025
 * @version 01.01.2025
 */
public record MarvusDataStatistics(int transactionsTotal, BigDecimal avgTransactionsPerYear, BigDecimal avgTransactionsPerDay,
		BigDecimal totalIncome, BigDecimal totalSpendigs, BigDecimal totalOutcome,
		BigDecimal avgIncome, BigDecimal avgSpendings, BigDecimal avgOutcome,
		BigDecimal avdDailyIncome, BigDecimal avgDailySpendings, BigDecimal avgDailyOutcome)
{
}
