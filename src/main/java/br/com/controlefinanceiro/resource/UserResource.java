package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.controlefinanceiro.enumeration.EExternalConnections;
import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.service.UserService;

@Path(value = "/user")
public class UserResource extends GenericResource<User> {

	@Inject
	private UserService userService;

	@Override
	public Response create(User user) {
		try {
			return Response.status(201).entity(userService.create(user)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, User t) {
		return null;
	}

	@Override
	public Response find(int id) {
		return null;
	}

	@Override
	public Response list() {
		return null;
	}

	@POST
	@Path(value = "login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateToken(User user) {
		try {
			user = userService.find(user);
			return Response.status(200).entity(user).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@POST
	@Path(value = "facebook-login/{facebookId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateTokenFacebook(@PathParam("facebookId")String facebookId, User user) {
		try {
			user = userService.login(facebookId, EExternalConnections.FACEBOOK, user);
			return Response.status(200).entity(user).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}
