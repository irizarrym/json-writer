package io.github.mirizdev.jsonwriter;

public interface JsonObjectAppend<R extends Base> extends Base, JsonObjectClose<R> {
    JsonWriteValue<JsonObjectAppend<R>> appendKey(String s);
}
