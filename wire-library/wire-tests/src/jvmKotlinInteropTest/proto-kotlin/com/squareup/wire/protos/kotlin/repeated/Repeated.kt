// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: repeated.proto
package com.squareup.wire.protos.kotlin.repeated

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.Syntax.PROTO_2
import com.squareup.wire.WireField
import com.squareup.wire.internal.checkElementsNotNull
import com.squareup.wire.internal.redactElements
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.jvm.JvmField
import okio.ByteString

class Repeated(
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.protos.kotlin.repeated.Thing#ADAPTER",
    label = WireField.Label.REPEATED
  )
  @JvmField
  val things: List<Thing> = emptyList(),
  unknownFields: ByteString = ByteString.EMPTY
) : Message<Repeated, Repeated.Builder>(ADAPTER, unknownFields) {
  override fun newBuilder(): Builder {
    val builder = Builder()
    builder.things = things
    builder.addUnknownFields(unknownFields)
    return builder
  }

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Repeated) return false
    if (unknownFields != other.unknownFields) return false
    if (things != other.things) return false
    return true
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + things.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (things.isNotEmpty()) result += """things=$things"""
    return result.joinToString(prefix = "Repeated{", separator = ", ", postfix = "}")
  }

  fun copy(things: List<Thing> = this.things, unknownFields: ByteString = this.unknownFields):
      Repeated = Repeated(things, unknownFields)

  class Builder : Message.Builder<Repeated, Builder>() {
    @JvmField
    var things: List<Thing> = emptyList()

    fun things(things: List<Thing>): Builder {
      checkElementsNotNull(things)
      this.things = things
      return this
    }

    override fun build(): Repeated = Repeated(
      things = things,
      unknownFields = buildUnknownFields()
    )
  }

  companion object {
    @JvmField
    val ADAPTER: ProtoAdapter<Repeated> = object : ProtoAdapter<Repeated>(
      FieldEncoding.LENGTH_DELIMITED, 
      Repeated::class, 
      "type.googleapis.com/com.squareup.wire.protos.kotlin.repeated.Repeated", 
      PROTO_2
    ) {
      override fun encodedSize(value: Repeated): Int {
        var size = value.unknownFields.size
        size += Thing.ADAPTER.asRepeated().encodedSizeWithTag(1, value.things)
        return size
      }

      override fun encode(writer: ProtoWriter, value: Repeated) {
        Thing.ADAPTER.asRepeated().encodeWithTag(writer, 1, value.things)
        writer.writeBytes(value.unknownFields)
      }

      override fun decode(reader: ProtoReader): Repeated {
        val things = mutableListOf<Thing>()
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> things.add(Thing.ADAPTER.decode(reader))
            else -> reader.readUnknownField(tag)
          }
        }
        return Repeated(
          things = things,
          unknownFields = unknownFields
        )
      }

      override fun redact(value: Repeated): Repeated = value.copy(
        things = value.things.redactElements(Thing.ADAPTER),
        unknownFields = ByteString.EMPTY
      )
    }
  }
}
