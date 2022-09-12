package nn.estore.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	JavaMailSender sender;
	
	public void send(Mail mail) {
		try {
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setFrom(mail.getFrom()); // người gửi
			helper.setReplyTo(mail.getFrom());
			helper.setTo(mail.getTo()); // người nhận
			if(mail.getCc() != null) {
				helper.setCc(mail.getCc()); // những người đồng nhận
			}
			if(mail.getBcc() != null) {
				helper.setBcc(mail.getBcc());
			}
			helper.setSubject(mail.getSubject()); // tiêu đề email
			helper.setText(mail.getText(), true); // nội dung email
			if(mail.getAttachments() != null) {
				String attachs = mail.getAttachments();
				if(attachs != null && attachs.trim().length() > 0) {
					String[] paths = attachs.split("[;,]+");
					for(String path: paths) {
						File file = new File(path);
						helper.addAttachment(file.getName(), file);
					}
				}
			}
			sender.send(msg);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Scheduled(fixedDelay = 2000)
	public void run() {
		while(!mails.isEmpty()) {
			Mail mail = mails.remove(0);
			try {
				this.send(mail);
			}
			catch (Exception e) {
				System.out.println("Lỗi: " + mail.getTo());
			}
		}
	}
	
	List<Mail> mails = new ArrayList<>();
	@Override
	public void queue(Mail mail) {
		mails.add(mail);
	}
}
