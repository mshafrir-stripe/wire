// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: external_message.proto
package com.squareup.wire.protos.simple;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.Syntax;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import okio.ByteString;

public final class ExternalMessage extends Message<ExternalMessage, ExternalMessage.Builder> {
  public static final ProtoAdapter<ExternalMessage> ADAPTER = new ProtoAdapter_ExternalMessage();

  private static final long serialVersionUID = 0L;

  public static final Float DEFAULT_F = 20f;

  public static final Integer DEFAULT_BAREXT = 0;

  public static final Integer DEFAULT_BAZEXT = 0;

  public static final SimpleMessage.NestedEnum DEFAULT_NESTED_ENUM_EXT = SimpleMessage.NestedEnum.FOO;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#FLOAT"
  )
  public final Float f;

  /**
   * Extension source: simple_message.proto
   */
  @WireField(
      tag = 125,
      adapter = "com.squareup.wire.ProtoAdapter#INT32",
      label = WireField.Label.REPEATED
  )
  public final List<Integer> fooext;

  /**
   * Extension source: simple_message.proto
   */
  @WireField(
      tag = 126,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer barext;

  /**
   * Extension source: simple_message.proto
   */
  @WireField(
      tag = 127,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer bazext;

  /**
   * Extension source: simple_message.proto
   */
  @WireField(
      tag = 128,
      adapter = "com.squareup.wire.protos.simple.SimpleMessage$NestedMessage#ADAPTER"
  )
  public final SimpleMessage.NestedMessage nested_message_ext;

  /**
   * Extension source: simple_message.proto
   */
  @WireField(
      tag = 129,
      adapter = "com.squareup.wire.protos.simple.SimpleMessage$NestedEnum#ADAPTER"
  )
  public final SimpleMessage.NestedEnum nested_enum_ext;

  public ExternalMessage(Float f, List<Integer> fooext, Integer barext, Integer bazext,
      SimpleMessage.NestedMessage nested_message_ext, SimpleMessage.NestedEnum nested_enum_ext) {
    this(f, fooext, barext, bazext, nested_message_ext, nested_enum_ext, ByteString.EMPTY);
  }

  public ExternalMessage(Float f, List<Integer> fooext, Integer barext, Integer bazext,
      SimpleMessage.NestedMessage nested_message_ext, SimpleMessage.NestedEnum nested_enum_ext,
      ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.f = f;
    this.fooext = Internal.immutableCopyOf("fooext", fooext);
    this.barext = barext;
    this.bazext = bazext;
    this.nested_message_ext = nested_message_ext;
    this.nested_enum_ext = nested_enum_ext;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.f = f;
    builder.fooext = Internal.copyOf(fooext);
    builder.barext = barext;
    builder.bazext = bazext;
    builder.nested_message_ext = nested_message_ext;
    builder.nested_enum_ext = nested_enum_ext;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof ExternalMessage)) return false;
    ExternalMessage o = (ExternalMessage) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(f, o.f)
        && fooext.equals(o.fooext)
        && Internal.equals(barext, o.barext)
        && Internal.equals(bazext, o.bazext)
        && Internal.equals(nested_message_ext, o.nested_message_ext)
        && Internal.equals(nested_enum_ext, o.nested_enum_ext);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (f != null ? f.hashCode() : 0);
      result = result * 37 + fooext.hashCode();
      result = result * 37 + (barext != null ? barext.hashCode() : 0);
      result = result * 37 + (bazext != null ? bazext.hashCode() : 0);
      result = result * 37 + (nested_message_ext != null ? nested_message_ext.hashCode() : 0);
      result = result * 37 + (nested_enum_ext != null ? nested_enum_ext.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (f != null) builder.append(", f=").append(f);
    if (!fooext.isEmpty()) builder.append(", fooext=").append(fooext);
    if (barext != null) builder.append(", barext=").append(barext);
    if (bazext != null) builder.append(", bazext=").append(bazext);
    if (nested_message_ext != null) builder.append(", nested_message_ext=").append(nested_message_ext);
    if (nested_enum_ext != null) builder.append(", nested_enum_ext=").append(nested_enum_ext);
    return builder.replace(0, 2, "ExternalMessage{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<ExternalMessage, Builder> {
    public Float f;

    public List<Integer> fooext;

    public Integer barext;

    public Integer bazext;

    public SimpleMessage.NestedMessage nested_message_ext;

    public SimpleMessage.NestedEnum nested_enum_ext;

    public Builder() {
      fooext = Internal.newMutableList();
    }

    public Builder f(Float f) {
      this.f = f;
      return this;
    }

    public Builder fooext(List<Integer> fooext) {
      Internal.checkElementsNotNull(fooext);
      this.fooext = fooext;
      return this;
    }

    public Builder barext(Integer barext) {
      this.barext = barext;
      return this;
    }

    public Builder bazext(Integer bazext) {
      this.bazext = bazext;
      return this;
    }

    public Builder nested_message_ext(SimpleMessage.NestedMessage nested_message_ext) {
      this.nested_message_ext = nested_message_ext;
      return this;
    }

    public Builder nested_enum_ext(SimpleMessage.NestedEnum nested_enum_ext) {
      this.nested_enum_ext = nested_enum_ext;
      return this;
    }

    @Override
    public ExternalMessage build() {
      return new ExternalMessage(f, fooext, barext, bazext, nested_message_ext, nested_enum_ext, super.buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_ExternalMessage extends ProtoAdapter<ExternalMessage> {
    public ProtoAdapter_ExternalMessage() {
      super(FieldEncoding.LENGTH_DELIMITED, ExternalMessage.class, "type.googleapis.com/squareup.protos.simple.ExternalMessage", Syntax.PROTO_2);
    }

    @Override
    public int encodedSize(ExternalMessage value) {
      return ProtoAdapter.FLOAT.encodedSizeWithTag(1, value.f)
          + ProtoAdapter.INT32.asRepeated().encodedSizeWithTag(125, value.fooext)
          + ProtoAdapter.INT32.encodedSizeWithTag(126, value.barext)
          + ProtoAdapter.INT32.encodedSizeWithTag(127, value.bazext)
          + SimpleMessage.NestedMessage.ADAPTER.encodedSizeWithTag(128, value.nested_message_ext)
          + SimpleMessage.NestedEnum.ADAPTER.encodedSizeWithTag(129, value.nested_enum_ext)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, ExternalMessage value) throws IOException {
      ProtoAdapter.FLOAT.encodeWithTag(writer, 1, value.f);
      ProtoAdapter.INT32.asRepeated().encodeWithTag(writer, 125, value.fooext);
      ProtoAdapter.INT32.encodeWithTag(writer, 126, value.barext);
      ProtoAdapter.INT32.encodeWithTag(writer, 127, value.bazext);
      SimpleMessage.NestedMessage.ADAPTER.encodeWithTag(writer, 128, value.nested_message_ext);
      SimpleMessage.NestedEnum.ADAPTER.encodeWithTag(writer, 129, value.nested_enum_ext);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public ExternalMessage decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.f(ProtoAdapter.FLOAT.decode(reader)); break;
          case 125: builder.fooext.add(ProtoAdapter.INT32.decode(reader)); break;
          case 126: builder.barext(ProtoAdapter.INT32.decode(reader)); break;
          case 127: builder.bazext(ProtoAdapter.INT32.decode(reader)); break;
          case 128: builder.nested_message_ext(SimpleMessage.NestedMessage.ADAPTER.decode(reader)); break;
          case 129: {
            try {
              builder.nested_enum_ext(SimpleMessage.NestedEnum.ADAPTER.decode(reader));
            } catch (ProtoAdapter.EnumConstantNotFoundException e) {
              builder.addUnknownField(tag, FieldEncoding.VARINT, (long) e.value);
            }
            break;
          }
          default: {
            reader.readUnknownField(tag);
          }
        }
      }
      builder.addUnknownFields(reader.endMessageAndGetUnknownFields(token));
      return builder.build();
    }

    @Override
    public ExternalMessage redact(ExternalMessage value) {
      Builder builder = value.newBuilder();
      if (builder.nested_message_ext != null) builder.nested_message_ext = SimpleMessage.NestedMessage.ADAPTER.redact(builder.nested_message_ext);
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
