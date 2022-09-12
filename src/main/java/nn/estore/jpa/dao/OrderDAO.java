package nn.estore.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nn.estore.jpa.entity.Order;
import nn.estore.jpa.entity.OrderState;
import nn.estore.jpa.entity.User;

public interface OrderDAO extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.customer=?1")
	List<Order> findByCustomer(User user);

	List<Order> findByOrderState(OrderState orderState);

}
