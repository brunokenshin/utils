package malfs.utils.mail;

import malfs.utils.json.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Email {

	@JsonIgnore
	private final Logger logger = Logger.getLogger(this.getClass());

	private final String receiver;
	private final String subject;
	private final String message;

	private Email(String receiver, String subject, String message) {
		emailAddressValidation(receiver);
		messageValidation(message);

		this.receiver = receiver;
		this.subject = subject;
		this.message = message;
	}

	public static Email parse(String emailJson) {
		JsonNode node = JsonUtils.jsonParse(emailJson);

		JsonNode receiverField = node.get("receiver");
		JsonNode subjectField = node.get("subject");
		JsonNode messageField = node.get("message");

		validateJsonFields(receiverField, messageField);

		return new Email(receiverField.asText(), getSubject(subjectField), messageField.asText());
	}

	private static void validateJsonFields(JsonNode receiverField, JsonNode messageField) {
		if (null == receiverField) {
			throw new IllegalArgumentException("Email receiver cant be null");
		}

		if (null == messageField) {
			throw new IllegalArgumentException("Email message cant be null");
		}
	}

	private static String getSubject(JsonNode subjectField) {
		return (subjectField == null) ? "" : subjectField.asText();
	}

	private void emailAddressValidation(String email) {
		if (!EmailValidator.getInstance().isValid(email)) {
			logger.error("Invalid email address: " + email);
			throw new IllegalArgumentException("Invalid email address: " + email);
		}
	}

	private void messageValidation(String message) {
		if (StringUtils.isBlank(message)) {
			throw new IllegalArgumentException("Email message cant be empty");
		}
	}

	public String getReceiver() {
		return receiver;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

}