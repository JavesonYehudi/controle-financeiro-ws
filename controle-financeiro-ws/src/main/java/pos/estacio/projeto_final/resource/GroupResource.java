package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Group;

@Path(value = "group")
public class GroupResource extends GenericResource<Group> {

	@Inject
	private GenericDao<Group> groupDao;

	@Override
	public Response create(Group group) {
		try {
			return Response.status(201).entity(groupDao.create(group)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, Group group) {
		group.setId(id);
		try {
			return Response.status(200).entity(groupDao.update(group)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response delete(int id) {
		try {
			groupDao.delete(id);
			return Response.status(200).entity(true).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(groupDao.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(groupDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}