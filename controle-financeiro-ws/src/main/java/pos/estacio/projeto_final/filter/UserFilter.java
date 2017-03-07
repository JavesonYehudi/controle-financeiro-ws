package pos.estacio.projeto_final.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.User;
import pos.estacio.projeto_final.session.UserSession;
import pos.estacio.projeto_final.utils.TokenUtils;

@Provider
public class UserFilter implements ContainerRequestFilter {

	@Inject
	private GenericDao<User> genericDao;
	@Inject
	private UserSession userSession;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!requestContext.getMethod().equalsIgnoreCase("options")) {
			String login = TokenUtils.verifyToken(requestContext.getHeaderString("Authorization"));
			userSession.setUser(genericDao.findBy(login));
		}
	}

}