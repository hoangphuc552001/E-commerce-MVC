package nn.estore.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nn.estore.jpa.entity.Role;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.entity.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer>{

	List<UserRole> findByUser(User user);

	UserRole findByUserAndRole(User user, Role role);

}
