package nn.estore.admin.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import nn.estore.jpa.entity.Category;
import nn.estore.jpa.entity.Product;
import nn.estore.jpa.service.CategoryService;
import nn.estore.jpa.service.ProductService;
import nn.estore.service.SessionService;
import nn.estore.service.UploadService;

@Controller
public class ProductAController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploadService uploadService;
	@Autowired
	SessionService sessionService;
	
	Page<Product> getPage(){
		Pageable pageable = PageRequest.of(sessionService.get("p"), 8);
		return productService.findAll(pageable);
	}
	
	@RequestMapping("/admin/product/index")
	public String index(Model model, 
			@RequestParam("p") Optional<Integer> popt) {
		sessionService.set("p", popt.orElse(sessionService.get("p")));
		Page<Product> page = this.getPage();
		
		model.addAttribute("form", new Product());
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		Page<Product> page = this.getPage();
		model.addAttribute("form", product);
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	@RequestMapping("/admin/product/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		productService.deleteById(id);
		Page<Product> page = this.getPage();
		model.addAttribute("form", new Product());
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	@RequestMapping("/admin/product/create")
	public String create(Model model, Product entity, @RequestPart("image_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File image = uploadService.save(file, "/images/items");
			entity.setImage(image.getName());
		} else {
			entity.setImage("new.jpg");
		}
		productService.create(entity);
		Page<Product> page = this.getPage();
		model.addAttribute("form", new Product());
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	@RequestMapping("/admin/product/update")
	public String update(Model model, 
			@ModelAttribute("form") Product entity, @RequestPart("image_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File image = uploadService.save(file, "/images/items");
			entity.setImage(image.getName());
		} 
		productService.update(entity);
		Page<Product> page = this.getPage();
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryService.findAll();
	}
}
