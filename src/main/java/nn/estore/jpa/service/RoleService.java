package nn.estore.jpa.service;

import java.util.List;

import nn.estore.jpa.entity.Role;

public interface RoleService{

	List<Role> findAll();

	Role findById(String string);

}
