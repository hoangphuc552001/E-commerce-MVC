package nn.estore.jpa.service;

import java.util.List;

import nn.estore.jpa.entity.OrderState;

public interface OrderStateService{

	OrderState findById(int i);

	List<OrderState> findAll();

}
