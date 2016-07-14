package malfs.utils.mail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmailTest {

	@Test
	public void test_cria_email_valido() throws Exception {
		Email email = Email.parse(getEmailJson("bruce.wayne@waynecorp.com"));

		assertEquals("bruce.wayne@waynecorp.com", email.getReceiver());
		assertEquals("Teste", email.getSubject());
		assertEquals("Testando criacao de email", email.getMessage());
	}

	@Test
	public void test_cria_email_valido_mesmo_sem_assunto() throws Exception {
		String emailJson = "{\"receiver\":\"bruce.wayne@waynecorp.com\", \"message\":\"Testando criacao de email\"}";
		Email email = Email.parse(emailJson);

		assertEquals("bruce.wayne@waynecorp.com", email.getReceiver());
		assertEquals("Testando criacao de email", email.getMessage());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_destinatario_tiver_formato_invalido() throws Exception {
		Email.parse(getEmailJson("bruce.wayne@waynecorp"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_destinatario_tiver_formato_invalido2() throws Exception {
		Email.parse(getEmailJson("bruce.waynewaynecorp.com"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_nao_houver_campo_receiver() throws Exception {
		String emailJson = "{\"subject\":\"Teste\", \"message\":\"Testando criacao de email\"}";
		Email.parse(emailJson);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_nao_houver_campo_message() throws Exception {
		String emailJson = "{\"receiver\":\"" + "bruce.wayne@waynecorp.com\", " + "\"subject\":\"Teste\"}";
		Email.parse(emailJson);
	}

	private String getEmailJson(String emailAddress) {
		String json = "{\"receiver\":\"" + emailAddress + "\", " + "\"subject\":\"Teste\", "
				+ "\"message\":\"Testando criacao de email\"}";
		return json;
	}

}