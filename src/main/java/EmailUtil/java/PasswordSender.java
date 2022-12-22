package EmailUtil.java;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class PasswordSender
{
	public void sendEmail(String to,String email,String password1)
	{
		System.out.println("in TLS EMAIL");
		final String fromEmail = "mgmalumninetwork@gmail.com"; //requires valid gmail id
		final String password = "pdzkifkepflfqkbr"; // correct password for gmail id
		final String toEmail = to; // can be any email id 
		String msgbody="Hello Dear Employee your Profile is created by City Light please find your credentials and attempt first login to change your password email={ "+email+" } password={"+password1+"}";
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator()
		{
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail,"City Light Password", msgbody);
	}
}