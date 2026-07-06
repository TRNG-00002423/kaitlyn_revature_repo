
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.week6.StringUtils;
import com.example.week6.User;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    @DisplayName("Reverse string")
    void reverse_hello_get_olleh() {
        assertEquals("olleh", StringUtils.reverse("hello"), "The reverse of \"hello\" is \"olleh\"");
    }

    @Test
    @DisplayName("Reverse a one-character string")
    void reverse_char_getSame() {
        assertEquals("a", StringUtils.reverse("a"), "Reversing a single character should not change it");
    }

    @Test
    @DisplayName("Reverse an empty string")
    void reverse_empty_getEmpty() {
        assertEquals("", StringUtils.reverse(""), "Reversing an empty string should result in an empty string");
    }

    @Test
    @DisplayName("Empty strings correctly identified")
    void isEmpty_emptyString_getTrue() {
        assertTrue(StringUtils.isEmpty(""), "isEmpty should identify an empty string");
    }

    @Test
    @DisplayName("Non-empty strings with whitespace are not empty")
    void isEmpty_whitespaceString_getFalse() {
        assertFalse(StringUtils.isEmpty("      "), "isEmpty should be false with only spaces in string");
    }

    @Test
    @DisplayName("Non-empty strings identified as such")
    void isEmpty_content_getTrue() {
        assertFalse(StringUtils.isEmpty("The mitochondria is the powerhouse of the cell"),
                "isEmpty should identify non-empty string");
    }

    @Test
    @DisplayName("Find first where item exists")
    void findFirst_validElement_getNotNull() {
        String[] elems = new String[] { "beach ball", "ice cream", "sunscreen" };
        String target = "sun";
        assertNotNull(StringUtils.findFirst(elems, target), "sun prefix should be found in sunscreen");
    }

    @Test
    @DisplayName("Find first where no such item exists")
    void findFirst_noValidElement_getNull() {
        String[] elems = new String[] { "him", "her", "them" };
        String target = "sun";
        assertNull(StringUtils.findFirst(elems, target), "findFirst should return null without a valid element");
    }

    @Test
    @DisplayName("Split a simple array")
    void split_delimitedString_getParts() {
        assertArrayEquals(new String[] { "a", "b", "c" }, StringUtils.split("a,b,c", ","), "Commas should split array");
        assertArrayEquals(new String[] { "lemon", "melon", "cookie" },
                StringUtils.split("lemon melon cookie", " "), "split should function with space as delimiter");
        assertArrayEquals(new String[] { "harder,better,faster,stronger" },
                StringUtils.split("harder,better,faster,stronger", " "),
                "commas should not split when delimiter is not a comma");
    }

    @Test
    @DisplayName("Split empty string")
    void split_emptyString_lengthOneArray() {
        assertArrayEquals(new String[] { "" }, StringUtils.split("", ","),
                "empty string input should result in length one array with empty string");
    }

    @Test
    void user_allPropertiesValid() {
        User user = StringUtils.parseUser("John,Doe,30,john@test.com");

        assertAll("User properties",
                () -> assertEquals("John", user.getFirstName()),
                () -> assertEquals("Doe", user.getLastName()),
                () -> assertEquals(30, user.getAge()),
                () -> assertNotNull(user.getEmail()),
                () -> assertTrue(user.getEmail().contains("@")));
    }
}
