package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.User;
import pos.estacio.projeto_final.service.UserService;
import pos.estacio.projeto_final.utils.TokenUtils;

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
	public Response delete(int id) {
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
	@Path(value = "log-in")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateToken(User user) {
		try {
			userService.find(user);

			String token = TokenUtils.generateToken(user.getLogin(), user.getPass());
			TokenUtils.verifyToken(token);

			return Response.status(200).entity("{\"token\": \"" + token + "\"}").build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}
