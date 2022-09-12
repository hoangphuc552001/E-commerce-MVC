package nn.estore.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.ProductDAO;
import nn.estore.jpa.entity.Product;
import nn.estore.jpa.entity.User;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO dao;

	@Override
	public Page<Product> findByCategory(Integer id, Pageable pageable) {
		return dao.findByCategory(id, pageable);
	}

	@Override
	public Page<Product> findByName(String keyword, Pageable pageable) {
		return dao.findByName("%"+keyword+"%", pageable);
	}

	@Override
	public Page<Product> findByDiscount(Pageable pageable) {
		return dao.findByDiscount(pageable);
	}

	@Override
	public Page<Product> findByBest(Pageable pageable) {
		List<Integer> ids = dao.findByBestIds(pageable);
		return dao.findByIds(ids, pageable);
	}

	@Override
	public Page<Product> findByFavorite(Pageable pageable) {
		return dao.findByFavorite(pageable);
	}

	@Override
	public Page<Product> findByLatest(Pageable pageable) {
		return dao.findByLatest(pageable);
	}

	@Override
	public Product findById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void update(Product product) {
		dao.save(product);
	}

	@Override
	public Page<Product> findByUser(User user, Pageable pageable) {
		return dao.findByUser(user, pageable);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public void create(Product entity) {
		dao.save(entity);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
}
