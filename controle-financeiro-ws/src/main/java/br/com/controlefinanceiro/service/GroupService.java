package br.com.controlefinanceiro.service;

import java.util.List;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Group;

public class GroupService extends BaseService{
	@Inject
	private GenericDao<Group> groupDao;

	public Group create(Group group) {
		return groupDao.create(group);
	}

	public List<Group> list() {
		return groupDao.list(this.getUserSession().getUser());
	}

	public Group find(int id) {
		return groupDao.find(id);
	}

	public Group update(int id, Group group) {
		Group groupAux = groupDao.find(id);
		groupAux.setDescription(group.getDescription());
		return groupDao.update(groupAux);
	}
}
