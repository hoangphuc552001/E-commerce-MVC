package nn.estore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nn.estore.jpa.service.ReportService;

@Controller
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@RequestMapping("/admin/report/inventory")
	public String inventory(Model model) {
		model.addAttribute("inventories", reportService.getInventoryData());
		return "admin/report/inventory";
	}
	
	@ResponseBody
	@GetMapping("/api/admin/report/inventory/data")
	public Object getInventoryData() {
		return reportService.getInventoryData();
	}
	
	@ResponseBody
	@GetMapping("/api/admin/report/revemue/month/data")
	public Object getRevenueByMonthData() {
		return reportService.getRevenueByMonthData();
	}
	
	@RequestMapping("/admin/report/revenue/category")
	public String getRevenueByCategory(Model model) {
		model.addAttribute("revenues", reportService.getRevenueByCategoryData());
		return "admin/report/revenue-by-category";
	}
	
	@RequestMapping("/admin/report/revenue/month")
	public String getRevenueByMonth(Model model) {
		model.addAttribute("revenues", reportService.getRevenueByMonthData());
		return "admin/report/revenue-by-month";
	}
	
	@RequestMapping("/admin/report/vip")
	public String getVIPCustomers(Model model) {
		model.addAttribute("revenues", reportService.getVIPCustomerData());
		return "admin/report/revenue-by-customer";
	}
}
