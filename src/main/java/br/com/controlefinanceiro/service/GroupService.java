package br.com.controlefinanceiro.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Group;

public class GroupService extends GenericService<Group>{

	public Group create(Group group) {
		return dao.create(group);
	}

	public List<Group> list() {
		return dao.list();
	}

	public Group find(ObjectId id) {
		return dao.find(id);
	}

	public Group update(ObjectId id, Group group) {
		Group groupAux = dao.find(id);
		groupAux.setDescription(group.getDescription());
		return dao.update(groupAux);
	}
}
