package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Group;
import br.com.controlefinanceiro.service.GroupService;

@Path(value = "group")
public class GroupResource extends GenericResource<Group> {

	@Inject
	private GroupService groupService;

	@Override
	public Response create(Group group) {
		try {
			return Response.status(201).entity(groupService.create(group)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(ObjectId id, Group group) {
		try {
			return Response.status(200).entity(groupService.update(id, group)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(ObjectId id) {
		try {
			return Response.status(200).entity(groupService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(groupService.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}