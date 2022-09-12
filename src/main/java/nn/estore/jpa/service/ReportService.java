package nn.estore.jpa.service;

import java.util.List;

import nn.estore.jpa.entity.ReportItem;

public interface ReportService {
	public List<ReportItem> getInventoryData();

	public List<ReportItem> getRevenueByCategoryData();

	public List<ReportItem> getRevenueByMonthData();

	public List<ReportItem> getVIPCustomerData();
}
