package nn.estore.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import nn.estore.jpa.entity.Product;
import nn.estore.jpa.service.ProductService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	ProductService productService;
	
	Map<Integer, CartItem> map = new HashMap<>();
	
	@Override
	public void add(Integer id) {
		CartItem item = map.get(id);
		if(item != null) {
			item.setQuantity(item.getQuantity() + 1);
		} else {
			Product product = productService.findById(id);
			item = new CartItem(id, product.getName(), product.getUnitPrice(), 1, product.getDiscount());
			map.put(id, item);
		}
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public void update(Integer id, int quntity) {
		CartItem item = map.get(id);
		item.setQuantity(quntity);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<CartItem> getItems() {
		return map.values();
	}

}
