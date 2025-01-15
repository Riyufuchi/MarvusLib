package riyufuchi.marvusLib.records;

import java.math.BigDecimal;

/**
 * @param spendigs must be negative values
 */
public record MarvusYearOverview(int year, BigDecimal[] income, BigDecimal[] spendigs, BigDecimal[] outcomes,
		BigDecimal totalIncome, BigDecimal totalSpendings, BigDecimal totalResult, BigDecimal[] totals)
{
}
