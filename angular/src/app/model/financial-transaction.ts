import { Maturity } 					from "./maturity";
import { Payment } 						from "./payment";
import { Funds } 						from "./funds";
import { Group } 						from "./group";
import { EFinancialTransactionType } 	from "./e-financial-transaction-type";

export class FinancialTransaction {
	id: string;
	description: string;
	valueTransaction: number;
	maturityList: Array<Maturity> ;
	payments: Array<Payment>;
	funds: Funds;
	firstMaturity: Date;
	fixedTransaction: boolean;
	recurrent: number;
	calendarPeriod: number;
	group: Group;
	eFinancialTransactionType: EFinancialTransactionType;
}