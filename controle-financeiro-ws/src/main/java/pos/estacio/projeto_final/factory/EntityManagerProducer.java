package pos.estacio.projeto_final.factory;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer implements Serializable{

	private static final String POSTGRES = "postgres";
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory(POSTGRES);
	}

	public void dispose(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}

	@RequestScoped
	@Produces
	public EntityManager provide() {
		return factory.createEntityManager();
	}

}