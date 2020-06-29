// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: roots.proto
package com.squareup.wire.protos.roots;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.Syntax;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class E extends Message<E, E.Builder> {
  public static final ProtoAdapter<E> ADAPTER = new ProtoAdapter_E();

  private static final long serialVersionUID = 0L;

  public static final G DEFAULT_G = G.FOO;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.protos.roots.E$F#ADAPTER"
  )
  public final F f;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.protos.roots.G#ADAPTER"
  )
  public final G g;

  public E(F f, G g) {
    this(f, g, ByteString.EMPTY);
  }

  public E(F f, G g, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.f = f;
    this.g = g;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.f = f;
    builder.g = g;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof E)) return false;
    E o = (E) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(f, o.f)
        && Internal.equals(g, o.g);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (f != null ? f.hashCode() : 0);
      result = result * 37 + (g != null ? g.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (f != null) builder.append(", f=").append(f);
    if (g != null) builder.append(", g=").append(g);
    return builder.replace(0, 2, "E{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<E, Builder> {
    public F f;

    public G g;

    public Builder() {
    }

    public Builder f(F f) {
      this.f = f;
      return this;
    }

    public Builder g(G g) {
      this.g = g;
      return this;
    }

    @Override
    public E build() {
      return new E(f, g, super.buildUnknownFields());
    }
  }

  public static final class F extends Message<F, F.Builder> {
    public static final ProtoAdapter<F> ADAPTER = new ProtoAdapter_F();

    private static final long serialVersionUID = 0L;

    public static final Integer DEFAULT_I = 0;

    @WireField(
        tag = 1,
        adapter = "com.squareup.wire.ProtoAdapter#INT32"
    )
    public final Integer i;

    public F(Integer i) {
      this(i, ByteString.EMPTY);
    }

    public F(Integer i, ByteString unknownFields) {
      super(ADAPTER, unknownFields);
      this.i = i;
    }

    @Override
    public Builder newBuilder() {
      Builder builder = new Builder();
      builder.i = i;
      builder.addUnknownFields(unknownFields());
      return builder;
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) return true;
      if (!(other instanceof F)) return false;
      F o = (F) other;
      return unknownFields().equals(o.unknownFields())
          && Internal.equals(i, o.i);
    }

    @Override
    public int hashCode() {
      int result = super.hashCode;
      if (result == 0) {
        result = unknownFields().hashCode();
        result = result * 37 + (i != null ? i.hashCode() : 0);
        super.hashCode = result;
      }
      return result;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      if (i != null) builder.append(", i=").append(i);
      return builder.replace(0, 2, "F{").append('}').toString();
    }

    public static final class Builder extends Message.Builder<F, Builder> {
      public Integer i;

      public Builder() {
      }

      public Builder i(Integer i) {
        this.i = i;
        return this;
      }

      @Override
      public F build() {
        return new F(i, super.buildUnknownFields());
      }
    }

    private static final class ProtoAdapter_F extends ProtoAdapter<F> {
      public ProtoAdapter_F() {
        super(FieldEncoding.LENGTH_DELIMITED, F.class, "type.googleapis.com/squareup.protos.roots.E.F", Syntax.PROTO_2);
      }

      @Override
      public int encodedSize(F value) {
        return ProtoAdapter.INT32.encodedSizeWithTag(1, value.i)
            + value.unknownFields().size();
      }

      @Override
      public void encode(ProtoWriter writer, F value) throws IOException {
        ProtoAdapter.INT32.encodeWithTag(writer, 1, value.i);
        writer.writeBytes(value.unknownFields());
      }

      @Override
      public F decode(ProtoReader reader) throws IOException {
        Builder builder = new Builder();
        long token = reader.beginMessage();
        for (int tag; (tag = reader.nextTag()) != -1;) {
          switch (tag) {
            case 1: builder.i(ProtoAdapter.INT32.decode(reader)); break;
            default: {
              reader.readUnknownField(tag);
            }
          }
        }
        builder.addUnknownFields(reader.endMessageAndGetUnknownFields(token));
        return builder.build();
      }

      @Override
      public F redact(F value) {
        Builder builder = value.newBuilder();
        builder.clearUnknownFields();
        return builder.build();
      }
    }
  }

  private static final class ProtoAdapter_E extends ProtoAdapter<E> {
    public ProtoAdapter_E() {
      super(FieldEncoding.LENGTH_DELIMITED, E.class, "type.googleapis.com/squareup.protos.roots.E", Syntax.PROTO_2);
    }

    @Override
    public int encodedSize(E value) {
      return F.ADAPTER.encodedSizeWithTag(1, value.f)
          + G.ADAPTER.encodedSizeWithTag(2, value.g)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, E value) throws IOException {
      F.ADAPTER.encodeWithTag(writer, 1, value.f);
      G.ADAPTER.encodeWithTag(writer, 2, value.g);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public E decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.f(F.ADAPTER.decode(reader)); break;
          case 2: {
            try {
              builder.g(G.ADAPTER.decode(reader));
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
    public E redact(E value) {
      Builder builder = value.newBuilder();
      if (builder.f != null) builder.f = F.ADAPTER.redact(builder.f);
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
