package br.com.controlefinanceiro.dao;

import br.com.controlefinanceiro.model.Payment;

public class PaymentDao extends GenericDao<Payment> {

	public PaymentDao() {
		super(Payment.class);
	}

}