package nn.estore.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Users")
public class User {
	@Id
	String username;
	String password;
	String fullname;
	String photo;
	String email;
	boolean enabled;
	
	@OneToMany(mappedBy = "customer")
	List<Order> customerOrders;
	
	@OneToMany(mappedBy = "master")
	List<Order> masterOrders;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<UserRole> userRoles;
}
