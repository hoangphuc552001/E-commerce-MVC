package nn.estore.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nn.estore.jpa.entity.OrderState;

public interface OrderStateDAO extends JpaRepository<OrderState, Integer>{

}
