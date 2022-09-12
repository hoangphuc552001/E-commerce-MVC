package nn.estore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import nn.estore.jpa.service.CategoryService;

@Service
public class AppInterceptor implements HandlerInterceptor{
	@Autowired
	CategoryService categoryService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null) {
			modelAndView.addObject("categories", categoryService.findAll());
		}
	}
}
