package io.github.mirizdev.jsonwriter;

public interface JsonListAppend<R extends Base> extends Base, JsonListClose<R> {
    JsonWriteValue<JsonListAppend<R>> append();
}
