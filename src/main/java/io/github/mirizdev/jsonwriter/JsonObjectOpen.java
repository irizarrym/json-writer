package io.github.mirizdev.jsonwriter;

public interface JsonObjectOpen<R extends Base> extends Base, JsonObjectClose<R> {
    JsonWriteValue<JsonObjectAppend<R>> writeKey(String s);
}
