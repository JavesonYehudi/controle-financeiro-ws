import { User } 		from "./user"
import { EFundsType } 	from "./e-funds-type"

export class Funds {
	id: string;
	description: string;
	user: User = new User();
	fundsType: EFundsType;
	balance: number;
	totalExpensePaid: number;
	totalIncomePaid: number;
}