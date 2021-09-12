package io.github.mirizdev.jsonwriter;

public interface JsonListOpen<R extends Base> extends Base, JsonListClose<R> {
    JsonWriteValue<JsonListAppend<R>> writer();
}
