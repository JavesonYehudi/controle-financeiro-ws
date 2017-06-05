import { Payment } 				from "./payment";
import { FinancialTransaction } from "./financial-transaction";

export class Maturity {
	id: string;
	value: number;
	date: Date;
	payment: Payment;
	financialTransaction: FinancialTransaction;
}