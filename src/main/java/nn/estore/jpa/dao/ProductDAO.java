package nn.estore.jpa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nn.estore.jpa.entity.ReportItem;
import nn.estore.jpa.entity.Product;
import nn.estore.jpa.entity.User;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByCategory(Integer id, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.name LIKE ?1 "
			+ " OR p.category.name LIKE ?1 OR p.category.nameVn LIKE ?1")
	Page<Product> findByName(String keyword, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC")
	Page<Product> findByDiscount(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.likeCount > 0 ORDER BY p.likeCount DESC")
	Page<Product> findByFavorite(Pageable pageable);

	@Query("SELECT p FROM Product p ORDER BY p.createDate DESC")
	Page<Product> findByLatest(Pageable pageable);
	
	@Query("SELECT d.product.id FROM OrderDetail d "
			+ " GROUP BY d.product.id "
			+ " ORDER BY sum(d.unitPrice*d.quantity*(1-d.discount))")
	List<Integer> findByBestIds(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.id IN ?1")
	Page<Product> findByIds(List<Integer> ids, Pageable pageable);

	@Query("SELECT DISTINCT d.product FROM OrderDetail d WHERE d.order.customer=?1")
	Page<Product> findByUser(User user, Pageable pageable);
	
	@Query("SELECT p.category.nameVn AS group, "
			+ " sum(p.quantity) AS count, "
			+ " sum(p.quantity*p.unitPrice) AS value,"
			+ " min(p.unitPrice) AS min, "
			+ " max(p.unitPrice) AS max, "
			+ " avg(p.unitPrice) AS avg "
			+ " FROM Product p GROUP BY p.category.nameVn")
	List<ReportItem> getInventoryData();
	
}
