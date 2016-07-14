package malfs.utils.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private SmtpMailSender mailSender;

    @Autowired
    public EmailController(SmtpMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String sendMail(@RequestBody String emailJson) throws Exception {
        Email email = Email.parse(emailJson);

		mailSender.send(email);

        final String response = String.format("Successfull email sent: [to=%s, subject=%s, message=%s]", 
                                              email.getReceiver(), 
                                              email.getSubject(), 
                                              email.getMessage());
        System.out.println(response);
        return response;
    }

}