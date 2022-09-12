package nn.estore.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nn.estore.jpa.entity.Product;
import nn.estore.jpa.entity.User;

public interface ProductService{

	Page<Product> findByCategory(Integer id, Pageable pageable);

	Page<Product> findByName(String keyword, Pageable pageable);

	Page<Product> findByDiscount(Pageable pageable);

	Page<Product> findByBest(Pageable pageable);

	Page<Product> findByFavorite(Pageable pageable);

	Page<Product> findByLatest(Pageable pageable);

	Product findById(Integer id);

	void update(Product product);

	Page<Product> findByUser(User user, Pageable pageable);

	List<Product> findAll();

	void deleteById(Integer id);

	void create(Product entity);

	Page<Product> findAll(Pageable pageable);
	
}
