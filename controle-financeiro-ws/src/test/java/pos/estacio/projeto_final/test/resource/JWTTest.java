package pos.estacio.projeto_final.test.resource;

import org.junit.Before;
import org.junit.Test;

import pos.estacio.projeto_final.utils.TokenUtils;

public class JWTTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(TokenUtils.generateToken("javeson", "123"));
	}

}
