package pos.estacio.projeto_final.service;

import java.util.List;

import javax.inject.Inject;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Group;

public class GroupService {
	@Inject
	private GenericDao<Group> groupDao;

	public Group create(Group funds) {
		return groupDao.create(funds);
	}

	public List<Group> list() {
		return groupDao.list();
	}

	public Group find(int id) {
		return groupDao.find(id);
	}

	public Group update(int id, Group group) {
		Group groupAux = groupDao.find(id);
		groupAux.setDescription(group.getDescription());
		return groupDao.update(groupAux);
	}

	public void delete(int id) {
		groupDao.delete(id);
	}
}
