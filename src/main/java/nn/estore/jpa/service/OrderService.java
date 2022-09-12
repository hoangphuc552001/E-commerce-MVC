package nn.estore.jpa.service;

import java.util.Collection;
import java.util.List;

import nn.estore.jpa.entity.Order;
import nn.estore.jpa.entity.OrderState;
import nn.estore.jpa.entity.User;
import nn.estore.service.ShoppingCartService.CartItem;

public interface OrderService{

	void purchase(Order order, Collection<CartItem> items);

	List<Order> findByCustomer(User user);

	Order findById(Long id);

	List<Order> findAll();

	void deleteById(Long id);

	void update(Order entity);

	List<Order> findByOrderState(OrderState orderState);
}
