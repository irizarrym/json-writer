package io.github.mirizdev.jsonwriter;

public interface JsonWriteValue<R extends Base> extends Base {
    R write(int i);

    R write(float f);

    R write(double d);

    R write(String s);

    R write(boolean b);

    R writeNull();

    JsonObjectOpen<R> openObject();

    JsonListOpen<R> openList();
}
