package nn.estore.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.OrderDetailDAO;
import nn.estore.jpa.dao.ProductDAO;
import nn.estore.jpa.entity.ReportItem;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	ProductDAO pdao;
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public List<ReportItem> getInventoryData() {
		return pdao.getInventoryData();
	}

	@Override
	public List<ReportItem> getRevenueByCategoryData() {
		return ddao.getRevenueByCategoryData();
	}

	@Override
	public List<ReportItem> getRevenueByMonthData() {
		return ddao.getRevenueByMonthData();
	}

	@Override
	public List<ReportItem> getVIPCustomerData() {
		return ddao.getVIPCustomerData();
	}

}
