package pos.estacio.projeto_final.predicate;

import java.util.Calendar;
import java.util.function.Predicate;

import pos.estacio.projeto_final.model.FinancialTransaction;

public class FinancialTransactionsPredicate {

	public static Predicate<? extends FinancialTransaction> getMaturityOfTheMonth() {
		Calendar calendar = Calendar.getInstance();
		
		return transaction -> transaction.getDayOfMaturityCalendar().get(Calendar.DATE) == calendar.get(Calendar.DATE);
		
	}
}