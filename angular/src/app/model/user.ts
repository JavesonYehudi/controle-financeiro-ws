import { ExternalConnection } from './external-connection'

export class User {
	id: number;
	login: string;
	pass: string;
	name: string;
	email: string;
	connections: Array<ExternalConnection>;

	constructor() {
		this.connections = new Array();
	}
}