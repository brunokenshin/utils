package malfs.utils.json;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtilsTest {

	@Test
    public void test_create_object_from_valid_json() {
		JsonNode node = JsonUtils.jsonParse("{\"firstField\":\"first value\", \"secondField\":\"second value\"}");
		assertEquals("first value", node.get("firstField").asText());
		assertEquals("second value", node.get("secondField").asText());
	}

	@Test(expected = IllegalArgumentException.class)
    public void test_throw_exception_for_invalid_json() throws Exception {
		// no "," between fields
		JsonUtils.jsonParse("{\"firstField\":\"first value\" \"secondField\":\"second value\"}");
	}

	@Test(expected = IllegalArgumentException.class)
    public void test_throw_exception_for_invalid_json2() throws Exception {
		// no ":" between field and value
		JsonUtils.jsonParse("{\"firstField\"\"first value\", \"secondField\"\"second value\"}");
	}

    @Test
    public void test_create_object_from_json_file() throws Exception {
        Book book = JsonUtils.parseJsonFile("src/test/resources/json/book.json", Book.class);
        assertEquals(7, book.id);
        assertEquals("The Silmarillion", book.title);
        assertEquals("John Ronald Reuel Tolkien", book.author);
        assertEquals("Fantasy", book.categories.get(0));
        assertEquals("Romance", book.categories.get(1));
        assertEquals("Tolkien series", book.categories.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_throw_exception_when_parsing_invalid_json_file() throws Exception {
        JsonUtils.parseJsonFile("src/test/resources/json/invalid_json_book.json", Book.class);
    }
}

class Book {
    public int id;
    public String title;
    public String author;
    public List<String> categories;
}