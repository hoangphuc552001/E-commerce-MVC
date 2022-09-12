package nn.estore.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.RoleDAO;
import nn.estore.jpa.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(String id) {
		return dao.getById(id);
	}
}
