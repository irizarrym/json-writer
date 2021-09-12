# About

JsonWriter is a simple library which builds JSON strings in a type-safe way. It makes clever use of Java's type system to prevent you from constructing JSON strings incorrectly.

This is not intended to be a practical library by any means. It's fairly verbose and cumbersome to use. It also has rather poor performance particularly because it makes heavy use of typecasts. I simply had this idea and implemented it as an experiment.  This is the end result.

# Usage

Begin by calling `JsonWriter.with(...)` and passing an object which implements `Appendable` or `Consumer<String>`. For example, you may use a `StringBuilder` or simply `System.out::print`. 

Refer to the test cases in `JsonWriterTests` for usage examples.

# How it Works

There is only a single class named `JsonWriter` which implements a sort of builder pattern for building JSON strings via a chain of method calls. These methods are organized and split up into multiple interfaces, and `JsonWriter` implements all of these interfaces. 

Internally, when calling one of the methods in `JsonWriter`, it will typecast `this` to one of these interfaces, thereby only exposing methods which are valid for the current step/context. For example, if you begin by opening a JSON object, there should only be two valid ways to proceed, (1) append a key and value, or (3) close the object.

However, JSON objects and lists can be infinitely nested, so there needs to be a way of keeping track . The interfaces use generics which can be nested in order to keep track of previous states. For example, the "type" at some point might look like `JsonObjectOpen<JsonObjectAppend<JsonCloser>>`. Types are boxed and unboxed as needed to keep track of the current state.
