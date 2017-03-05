package pos.estacio.projeto_final.dao;

import pos.estacio.projeto_final.model.User;

public class UserDao extends GenericDao<User> {

	public UserDao() {
		super(User.class);
	}

	@Override
	public User findBy(Object...objects){
		return (User) this.entityManager.createQuery("select u from User u where u.login = :login").setParameter("login", objects[0]).getSingleResult();
	}
}
