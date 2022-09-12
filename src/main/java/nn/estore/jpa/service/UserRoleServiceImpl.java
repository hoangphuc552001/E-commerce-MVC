package nn.estore.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.UserRoleDAO;
import nn.estore.jpa.entity.Role;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.entity.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	UserRoleDAO dao;

	@Override
	public List<UserRole> findAll() {
		return dao.findAll();
	}

	@Override
	public UserRole findByUserAndRole(User user, Role role) {
		return dao.findByUserAndRole(user, role);
	}

	@Override
	public void delete(UserRole userRole) {
		dao.delete(userRole);
	}

	@Override
	public void create(UserRole userRole) {
		dao.save(userRole);
	}
}
