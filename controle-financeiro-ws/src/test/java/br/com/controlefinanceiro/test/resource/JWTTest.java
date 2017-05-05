package br.com.controlefinanceiro.test.resource;

import org.junit.Before;
import org.junit.Test;

import br.com.controlefinanceiro.utils.TokenUtils;

public class JWTTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(TokenUtils.generateToken("javeson", "123"));
	}

}
