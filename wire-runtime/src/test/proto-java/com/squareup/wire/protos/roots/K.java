// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/roots.proto at 79:1
package com.squareup.wire.protos.roots;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import okio.ByteString;

public final class K extends Message<K> {
  public static final ProtoAdapter<K> ADAPTER = ProtoAdapter.newMessageAdapter(K.class);

  private static final long serialVersionUID = 0L;

  public static final Integer DEFAULT_I = 0;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer i;

  public K(Integer i) {
    this(i, ByteString.EMPTY);
  }

  public K(Integer i, ByteString unknownFields) {
    super(unknownFields);
    this.i = i;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof K)) return false;
    K o = (K) other;
    return equals(unknownFields(), o.unknownFields())
        && equals(i, o.i);
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

  public static final class Builder extends com.squareup.wire.Message.Builder<K, Builder> {
    public Integer i;

    public Builder() {
    }

    public Builder(K message) {
      super(message);
      if (message == null) return;
      this.i = message.i;
    }

    public Builder i(Integer i) {
      this.i = i;
      return this;
    }

    @Override
    public K build() {
      return new K(i, buildUnknownFields());
    }
  }
}
