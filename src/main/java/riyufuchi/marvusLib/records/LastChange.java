package riyufuchi.marvusLib.records;

import riyufuchi.marvusLib.data.Transaction;
import riyufuchi.marvusLib.enums.MarvusAction;

public record LastChange(MarvusAction userAction, Transaction transactionInQuestion)
{
}
