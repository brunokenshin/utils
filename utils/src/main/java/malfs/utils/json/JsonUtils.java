package malfs.utils.json;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static Logger logger = Logger.getLogger(JsonUtils.class);

	public static JsonNode jsonParse(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readTree(json);
		} catch (Exception e) {
			logger.error("Error on Json serialization: " + json);
			throw new IllegalArgumentException("Error on Json serialization: " + json, e);
		}
	}
}
