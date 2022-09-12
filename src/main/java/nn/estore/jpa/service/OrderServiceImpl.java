package nn.estore.jpa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nn.estore.jpa.dao.OrderDAO;
import nn.estore.jpa.dao.OrderDetailDAO;
import nn.estore.jpa.dao.ProductDAO;
import nn.estore.jpa.entity.Order;
import nn.estore.jpa.entity.OrderDetail;
import nn.estore.jpa.entity.OrderState;
import nn.estore.jpa.entity.User;
import nn.estore.service.ShoppingCartService.CartItem;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	@Autowired
	ProductDAO pdao;
	@Autowired
	OrderDetailDAO ddao;

	@Transactional
	@Override
	public void purchase(Order order, Collection<CartItem> items) {
		dao.save(order);
		List<OrderDetail> details = items.stream().map(item -> {
			OrderDetail detail = new OrderDetail();
			detail.setDiscount(item.getDiscount());
			detail.setOrder(order);
			detail.setProduct(pdao.getById(item.getId()));
			detail.setQuantity(item.getQuantity());
			detail.setUnitPrice(item.getPrice());
			return detail;
		}).toList();
		ddao.saveAll(details);
	}

	@Override
	public List<Order> findByCustomer(User user) {
		return dao.findByCustomer(user);
	}

	@Override
	public Order findById(Long id) {
		return dao.getById(id);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void update(Order entity) {
		dao.save(entity);
	}

	@Override
	public List<Order> findByOrderState(OrderState orderState) {
		return dao.findByOrderState(orderState);
	}
}
