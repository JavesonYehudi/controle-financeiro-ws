import { Maturity } 			from "./maturity";
import { FinancialTransaction } from "./financial-transaction";

export class Payment {
	id: string;
	valuePaid: number;
	datePayment: Date;
	maturity: Maturity;
	financialTransaction: FinancialTransaction;
}