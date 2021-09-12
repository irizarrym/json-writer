package io.github.mirizdev.jsonwriter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonWriterTests {
    @Test
    void example() {
        JsonWriter.with(System.out::print)
                .openObject()
                .writeKey("Hello").write("World!")
                .closeObject()
                .close();
        System.out.println();
    }

    @Test
    void emptyObject() {
        final String expected = "{}";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .openObject()
                .closeObject()
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void emptyList() {
        final String expected = "[]";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .openList()
                .closeList();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeInt() {
        final String expected = "100";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write(100)
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeFloat() {
        final String expected = "-12.345";
        final float f = -12.345f;

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write(f)
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeDouble() {
        final String expected = "-12.345";
        final double d = -12.345d;

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write(d)
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeEmptyString() {
        final String expected = "\"\"";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write("")
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeBasicString() {
        final String expected = "\"Hello World!\"";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write("Hello World!")
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeQuotedString() {
        final String expected = "\"He said he was \\\"normal\\\"...\"";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write("He said he was \"normal\"...")
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeNull() {
        final String expected = "null";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .writeNull()
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeTrue() {
        final String expected = "true";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write(true)
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeFalse() {
        final String expected = "false";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .write(false)
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeObject1() {
        final String expected = "{\"name\":{\"first\":\"John\",\"last\":\"Doe\"},\"age\":25,\"dob\":{\"month\":12,\"day\":31,\"year\":1995}}";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .openObject()
                .writeKey("name").openObject()
                .writeKey("first").write("John")
                .appendKey("last").write("Doe")
                .closeObject()
                .appendKey("age").write(25)
                .appendKey("dob").openObject()
                .writeKey("month").write(12)
                .appendKey("day").write(31)
                .appendKey("year").write(1995)
                .closeObject()
                .closeObject()
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }

    @Test
    void writeListPrimes() {
        final String expected = "[2,3,5,7,11,13,17,19]";

        StringBuilder stringBuilder = new StringBuilder();
        JsonWriter.with(stringBuilder)
                .openList()
                .writer().write(2)
                .append().write(3)
                .append().write(5)
                .append().write(7)
                .append().write(11)
                .append().write(13)
                .append().write(17)
                .append().write(19)
                .closeList()
                .close();

        Assertions.assertEquals(expected, stringBuilder.toString());
    }
}
