package nn.estore.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSecurityController{
	@RequestMapping("/security/login/form")
	public String login() {
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("/security/login/failure")
	public String failure(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("/security/logout/success")
	public String logout(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forward:/security/login/form";
	}
	
	@RequestMapping({"/security/access/denied"})
	public String denied(Model model) {
		model.addAttribute("message", "Không có quyền truy cập");
		return "forward:/security/login/form";
	}
}