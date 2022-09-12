package nn.estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nn.estore.jpa.entity.Role;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.entity.UserRole;
import nn.estore.jpa.service.RoleService;
import nn.estore.jpa.service.UserRoleService;
import nn.estore.jpa.service.UserService;

@Controller("authorize")
public class AuthorizeAController {
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	
	@RequestMapping("/admin/authorize")
	public String index(Model model) {
		return "admin/authorize/index";
	}
	
	@ResponseBody
	@RequestMapping("/api/admin/authorize/{pair}")
	public Object authorize(@PathVariable("pair") String pair) {
		String[] pairs = pair.split("[-]");
		User user = userService.findByUsername(pairs[0]);
		Role role = roleService.findById(pairs[1]);
		try {
			UserRole userRole = userRoleService.findByUserAndRole(user, role);
			// Xóa
			userRoleService.delete(userRole);
		} catch (Exception e) {
			// thêm
			UserRole userRole = new UserRole(null, role, user);
			userRoleService.create(userRole);
		}
		
		return List.of();
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		return roleService.findAll();
	}
	
	@ModelAttribute("users")
	public List<User> getMaster(){
		return userService.findMasters(Pageable.unpaged()).toList();
	}
	
	@ModelAttribute("userRoles")
	public List<UserRole> getUserRoles(){
		return userRoleService.findAll();
	}
	
	public boolean isInRole(User user, Role role) {
		List<UserRole> userRoles = user.getUserRoles();
		if(userRoles == null) {
			return false;
		}
		return userRoles.stream()
				.anyMatch(ur -> ur.getRole().getId().equals(role.getId()));
	}
}
