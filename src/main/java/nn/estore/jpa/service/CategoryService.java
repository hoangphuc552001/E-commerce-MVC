package nn.estore.jpa.service;

import java.util.List;

import nn.estore.jpa.entity.Category;

public interface CategoryService{

	List<Category> findAll();

	Category findById(Integer id);

	void create(Category entity);

	void update(Category entity);

	void deleteById(Integer id);
}
