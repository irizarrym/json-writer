package io.github.mirizdev.jsonwriter;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

import static io.github.mirizdev.jsonwriter.JsonStringQuote.quote;

public class JsonWriter<R extends Base> implements
        Base,
        JsonWriteValue<R>,
        JsonObjectOpen<R>,
        JsonObjectAppend<R>,
        JsonObjectClose<R>,
        JsonListOpen<R>,
        JsonListAppend<R>,
        JsonListClose<R>,
        JsonCloser {

    private final Consumer<String> appender;
    private final Runnable closer;

    private JsonWriter(Consumer<String> appender, Runnable closer) {
        this.appender = appender;
        this.closer = closer;
    }

    public static JsonWriteValue<JsonCloser> with(Consumer<String> appender) {
        return new JsonWriter<JsonCloser>(appender, null);
    }

    public static JsonWriteValue<JsonCloser> with(Consumer<String> appender, Runnable closer) {
        return new JsonWriter<JsonCloser>(appender, closer);
    }

    public static JsonWriteValue<JsonCloser> with(Appendable appendable) {
        return with(appendable, false);
    }

    public static JsonWriteValue<JsonCloser> with(Appendable appendable, boolean autoClose) {
        return with(
                appendable,
                autoClose && appendable instanceof Closeable ? (() -> {
                    try {
                        ((Closeable) appendable).close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }) : null
        );
    }

    public static JsonWriteValue<JsonCloser> with(Appendable appendable, Runnable closer) {
        return new JsonWriter<JsonCloser>(
                (String s) -> {
                    try {
                        appendable.append(s);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                closer
        );
    }


    @Override
    public R write(boolean b) {
        appender.accept(b ? "true" : "false");
        return (R) this;
    }

    @Override
    public R write(int i) {
        appender.accept(Integer.toString(i));
        return (R) this;
    }

    @Override
    public R write(float f) {
        appender.accept(Float.toString(f));
        return (R) this;
    }

    @Override
    public R write(double d) {
        appender.accept(Double.toString(d));
        return (R) this;
    }

    @Override
    public R write(String s) {
        appender.accept(quote(s));
        return (R) this;
    }

    @Override
    public R writeNull() {
        appender.accept("null");
        return (R) this;
    }

    @Override
    public JsonObjectOpen<R> openObject() {
        appender.accept("{");
        return this;
    }

    @Override
    public JsonListOpen<R> openList() {
        appender.accept("[");
        return this;
    }

    @Override
    public JsonWriteValue<JsonObjectAppend<R>> writeKey(String s) {
        appender.accept(quote(s) + ":");
        return (JsonWriteValue<JsonObjectAppend<R>>) this;
    }

    @Override
    public JsonWriteValue<JsonListAppend<R>> writer() {
        return (JsonWriteValue<JsonListAppend<R>>) this;
    }

    @Override
    public JsonWriteValue<JsonObjectAppend<R>> appendKey(String s) {
        appender.accept("," + quote(s) + ":");
        return (JsonWriteValue<JsonObjectAppend<R>>) this;
    }

    @Override
    public JsonWriteValue<JsonListAppend<R>> append() {
        appender.accept(",");
        return (JsonWriteValue<JsonListAppend<R>>) this;
    }

    @Override
    public R closeObject() {
        appender.accept("}");
        return (R) this;
    }

    @Override
    public R closeList() {
        appender.accept("]");
        return (R) this;
    }

    @Override
    public void close() {
        if (closer != null) closer.run();
    }
}
