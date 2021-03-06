package br.com.controlefinanceiro.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.com.controlefinanceiro.dao.UserDao;
import br.com.controlefinanceiro.session.UserSession;
import br.com.controlefinanceiro.utils.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;

@Provider
public class UserFilter implements ContainerRequestFilter {

	@Inject
	private UserDao dao;
	@Inject
	private UserSession userSession;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException{
		if (!isOptionsRequest(requestContext) && !isUserRequest(requestContext)) {
			try{
				String login = TokenUtils.verifyToken(requestContext.getHeaderString("Authorization"));
				userSession.setUser(dao.find(login));
				if(userSession.getUser() == null)
					requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			}catch (ExpiredJwtException e) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			}catch (IllegalArgumentException e) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("{\"error\" : \"" + "Please, insert the Authorization code." + "\"}").build());
			}
		}
	}

	private boolean isOptionsRequest(ContainerRequestContext requestContext){
		return requestContext.getMethod().equalsIgnoreCase("options");
	}
	
	private boolean isUserRequest(ContainerRequestContext requestContext){
		return requestContext.getUriInfo().getPath().contains("user");
	}
}