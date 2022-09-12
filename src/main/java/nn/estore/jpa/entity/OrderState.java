package nn.estore.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orderstates")
public class OrderState {
	@Id
	Integer id;
	String name;
	
	@OneToMany(mappedBy = "orderState")
	List<Order> orders;
}
