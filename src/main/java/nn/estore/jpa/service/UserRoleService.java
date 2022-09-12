package nn.estore.jpa.service;

import java.util.List;

import nn.estore.jpa.entity.Role;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.entity.UserRole;

public interface UserRoleService{

	List<UserRole> findAll();

	UserRole findByUserAndRole(User user, Role role);

	void delete(UserRole userRole);

	void create(UserRole userRole);

}
