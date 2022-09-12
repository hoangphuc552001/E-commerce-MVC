package nn.estore.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nn.estore.jpa.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
