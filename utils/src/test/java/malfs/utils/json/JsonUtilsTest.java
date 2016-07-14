package malfs.utils.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtilsTest {

	@Test
	public void test_cria_objeto_a_partir_de_json_valido() {
		JsonNode node = JsonUtils.jsonParse("{\"firstField\":\"first value\", \"secondField\":\"second value\"}");
		assertEquals("first value", node.get("firstField").asText());
		assertEquals("second value", node.get("secondField").asText());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_json_for_invalido() throws Exception {
		// no "," between fields
		JsonUtils.jsonParse("{\"firstField\":\"first value\" \"secondField\":\"second value\"}");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_lanca_erro_se_json_for_invalido2() throws Exception {
		// no ":" between field and value
		JsonUtils.jsonParse("{\"firstField\"\"first value\", \"secondField\"\"second value\"}");
	}

}
