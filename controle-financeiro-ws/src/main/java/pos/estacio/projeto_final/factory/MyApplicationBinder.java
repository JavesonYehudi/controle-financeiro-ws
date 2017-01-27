/*package pos.estacio.projeto_final.factory;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import pos.estacio.projeto_final.dao.BankAccountDaoImpl;
import pos.estacio.projeto_final.dao.GenericDaoImpl;

public class MyApplicationBinder extends AbstractBinder {
	@Override
	protected void configure() {
		//bind(BankAccountDaoImpl.class).to(BankAccountDaoImpl.class);
		bindFactory(HttpSessionFactory.class).to(HttpSession.class).proxy(true).proxyForSameScope(false)
				.in(RequestScoped.class);
		bindFactory(EntityManagerProducer.class).to(EntityManager.class).in(Singleton.class);
		bindFactory(GenericDaoProducer.class).to(GenericDaoImpl.class).in(Singleton.class);
	}
}
*/