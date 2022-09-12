package nn.estore.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.OrderStateDAO;
import nn.estore.jpa.entity.OrderState;

@Service
public class OrderStateServiceImpl implements OrderStateService{
	@Autowired
	OrderStateDAO dao;

	@Override
	public OrderState findById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<OrderState> findAll() {
		return dao.findAll();
	}
}
