package riyufuchi.marvusLib.records;

import java.math.BigDecimal;

/**
 * @author riyufuchi
 * @since 07.01.2025
 * @version 07.01.2025
 */
public record MarvusCategoryStatistic(String category, BigDecimal total, BigDecimal yearAverage, BigDecimal dailyAverage) 
{
}
