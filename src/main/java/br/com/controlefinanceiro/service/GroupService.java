package br.com.controlefinanceiro.service;

import java.util.List;

import br.com.controlefinanceiro.model.Group;

public class GroupService extends GenericService<Group>{

	public Group create(Group group) {
		return dao.create(group);
	}

	public List<Group> list() {
		return dao.list(this.getUserSession().getUser());
	}

	public Group find(int id) {
		return dao.find(id);
	}

	public Group update(int id, Group group) {
		Group groupAux = dao.find(id);
		groupAux.setDescription(group.getDescription());
		return dao.update(groupAux);
	}
}
