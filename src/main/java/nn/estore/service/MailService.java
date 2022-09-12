package nn.estore.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nn.estore.jpa.entity.User;

public interface MailService {
	void queue(Mail mail);
	default void sendProduct(String email, Integer id) {
		String url = "http://localhost:8080/product/detail/" + id;
		String subject = "Thông tin sản phẩm";
		String text = "<a href='"+url+"'>Xem chi tiết</a>";
		Mail mail = new Mail(email, subject, text);
		this.queue(mail);
	}
	
	default void sendWelcome(User user) {
		String url = "http://localhost:8080/account/activate/" + user.getUsername();
		String subject = "Welcome to WebShop";
		String text = "<a href='"+url+"'>Kích hoạt tài khoản</a>";
		Mail mail = new Mail(user.getEmail(), subject, text);
		this.queue(mail);
	}
	
	default void sendPasswordToken(User user) {
		int hash = user.getPassword().hashCode();
		String url = "http://localhost:8080/account/reset/" + user.getUsername() + "/" + hash;
		String subject = "Reset password";
		String text = "<a href='"+url+"'>Reset password</a>";
		Mail mail = new Mail(user.getEmail(), subject, text);
		this.queue(mail);
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Mail{
		String from;
		String to;
		String subject;
		String text;
		String cc;
		String bcc;
		String attachments;
		
		public Mail(String to, String subject, String text) {
			this.from = "Nhất Nghệ <songlong2k@gmail.com>";
			this.to = to;
			this.subject = subject;
			this.text = text;
		}
	}

	

	
}
