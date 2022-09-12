package nn.estore.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nn.estore.jpa.entity.OrderDetail;
import nn.estore.jpa.entity.ReportItem;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	@Query("SELECT d.product.category.nameVn AS group, "
			+ " sum(d.quantity) AS count, "
			+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS value,"
			+ " min(d.unitPrice) AS min, "
			+ " max(d.unitPrice) AS max, "
			+ " avg(d.unitPrice) AS avg "
			+ " FROM OrderDetail d "
			+ " GROUP BY d.product.category.nameVn")
	List<ReportItem> getRevenueByCategoryData();
	
	@Query("SELECT month(d.order.createDate) AS group, "
			+ " sum(d.quantity) AS count, "
			+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS value,"
			+ " min(d.unitPrice) AS min, "
			+ " max(d.unitPrice) AS max, "
			+ " avg(d.unitPrice) AS avg "
			+ " FROM OrderDetail d "
			+ " GROUP BY month(d.order.createDate)"
			+ " ORDER BY month(d.order.createDate)")
	List<ReportItem> getRevenueByMonthData();
	
	@Query("SELECT d.order.customer.username AS group, "
			+ " sum(d.quantity) AS count, "
			+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS value,"
			+ " min(d.unitPrice) AS min, "
			+ " max(d.unitPrice) AS max, "
			+ " avg(d.unitPrice) AS avg "
			+ " FROM OrderDetail d "
			+ " GROUP BY d.order.customer.username"
			+ " ORDER BY sum(d.quantity*d.unitPrice*(1-d.discount)) DESC")
	List<ReportItem> getVIPCustomerData();
}
