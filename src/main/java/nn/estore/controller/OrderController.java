package nn.estore.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nn.estore.jpa.entity.Order;
import nn.estore.jpa.entity.Product;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.service.OrderService;
import nn.estore.jpa.service.OrderStateService;
import nn.estore.jpa.service.ProductService;
import nn.estore.security.AuthService;
import nn.estore.service.ShoppingCartService;

@Controller
public class OrderController {
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	AuthService authService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderStateService stateService;
	@Autowired
	ProductService productService;
	
	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setAmount(cartService.getAmount());
		order.setCustomer(authService.getUser());
		order.setOrderState(stateService.findById(0));
		
		model.addAttribute("order", order);
		model.addAttribute("cart", cartService);
		return "order/checkout";
	}
	
	@RequestMapping("/order/purchase")
	public String purchase(Model model,
			@ModelAttribute("order") Order order) {
		try {
			orderService.purchase(order, cartService.getItems());
			model.addAttribute("cart", cartService);
			cartService.clear();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model) {
		User user = authService.getUser();
		List<Order> orders = orderService.findByCustomer(user);
		model.addAttribute("orders", orders);
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "order/detail";
	}
	
	@RequestMapping("/order/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		order.setOrderState(stateService.findById(-1));
		return "forward:/order/detail/"+id;
	}
	
	@RequestMapping("/order/items")
	public String findPurchasedItems(Model model) {
		User user = authService.getUser();
		Page<Product> page = productService.findByUser(user, Pageable.unpaged());
		model.addAttribute("page", page);
		return "product/list";
	}
}
