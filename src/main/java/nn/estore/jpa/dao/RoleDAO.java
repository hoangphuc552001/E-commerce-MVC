package nn.estore.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nn.estore.jpa.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
