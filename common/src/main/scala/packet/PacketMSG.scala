/**
 * Generated by Scrooge
 *   version: 4.5.0
 *   rev: 014664de600267b36809bbc85225e26aec286216
 *   built at: 20160203-205352
 */
package packet

import com.twitter.scrooge.{
  LazyTProtocol,
  TFieldBlob, ThriftException, ThriftStruct, ThriftStructCodec3, ThriftStructFieldInfo,
  ThriftStructMetaData, ThriftUtil}
import org.apache.thrift.protocol._
import org.apache.thrift.transport.{TMemoryBuffer, TTransport}
import java.nio.ByteBuffer
import java.util.Arrays
import scala.collection.immutable.{Map => immutable$Map}
import scala.collection.mutable.Builder
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, Buffer => mutable$Buffer,
  HashMap => mutable$HashMap, HashSet => mutable$HashSet}
import scala.collection.{Map, Set}


object PacketMSG extends ThriftStructCodec3[PacketMSG] {
  private val NoPassthroughFields = immutable$Map.empty[Short, TFieldBlob]
  val Struct = new TStruct("PacketMSG")
  val IdField = new TField("id", TType.I16, 1)
  val IdFieldManifest = implicitly[Manifest[Short]]
  val DataField = new TField("data", TType.STRING, 2)
  val DataFieldManifest = implicitly[Manifest[ByteBuffer]]

  /**
   * Field information in declaration order.
   */
  lazy val fieldInfos: scala.List[ThriftStructFieldInfo] = scala.List[ThriftStructFieldInfo](
    new ThriftStructFieldInfo(
      IdField,
      false,
      false,
      IdFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    ),
    new ThriftStructFieldInfo(
      DataField,
      false,
      false,
      DataFieldManifest,
      _root_.scala.None,
      _root_.scala.None,
      immutable$Map.empty[String, String],
      immutable$Map.empty[String, String]
    )
  )

  lazy val structAnnotations: immutable$Map[String, String] =
    immutable$Map.empty[String, String]

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: PacketMSG): Unit = {
  }

  def withoutPassthroughFields(original: PacketMSG): PacketMSG =
    new Immutable(
      id =
        {
          val field = original.id
          field
        },
      data =
        {
          val field = original.data
          field
        }
    )

  override def encode(_item: PacketMSG, _oproto: TProtocol): Unit = {
    _item.write(_oproto)
  }

  private[this] def lazyDecode(_iprot: LazyTProtocol): PacketMSG = {

    var id: Short = 0
    var data: ByteBuffer = null

    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false
    val _start_offset = _iprot.offset

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.I16 =>
    
                id = readIdValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I16
                throw new TProtocolException(
                  "Received wrong type for field 'id' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.STRING =>
    
                data = readDataValue(_iprot)
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'data' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new LazyImmutable(
      _iprot,
      _iprot.buffer,
      _start_offset,
      _iprot.offset,
      id,
      data,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  override def decode(_iprot: TProtocol): PacketMSG =
    _iprot match {
      case i: LazyTProtocol => lazyDecode(i)
      case i => eagerDecode(i)
    }

  private[this] def eagerDecode(_iprot: TProtocol): PacketMSG = {
    var id: Short = 0
    var data: ByteBuffer = null
    var _passthroughFields: Builder[(Short, TFieldBlob), immutable$Map[Short, TFieldBlob]] = null
    var _done = false

    _iprot.readStructBegin()
    while (!_done) {
      val _field = _iprot.readFieldBegin()
      if (_field.`type` == TType.STOP) {
        _done = true
      } else {
        _field.id match {
          case 1 =>
            _field.`type` match {
              case TType.I16 =>
                id = readIdValue(_iprot)
              case _actualType =>
                val _expectedType = TType.I16
                throw new TProtocolException(
                  "Received wrong type for field 'id' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case 2 =>
            _field.`type` match {
              case TType.STRING =>
                data = readDataValue(_iprot)
              case _actualType =>
                val _expectedType = TType.STRING
                throw new TProtocolException(
                  "Received wrong type for field 'data' (expected=%s, actual=%s).".format(
                    ttypeToString(_expectedType),
                    ttypeToString(_actualType)
                  )
                )
            }
          case _ =>
            if (_passthroughFields == null)
              _passthroughFields = immutable$Map.newBuilder[Short, TFieldBlob]
            _passthroughFields += (_field.id -> TFieldBlob.read(_field, _iprot))
        }
        _iprot.readFieldEnd()
      }
    }
    _iprot.readStructEnd()

    new Immutable(
      id,
      data,
      if (_passthroughFields == null)
        NoPassthroughFields
      else
        _passthroughFields.result()
    )
  }

  def apply(
    id: Short,
    data: ByteBuffer
  ): PacketMSG =
    new Immutable(
      id,
      data
    )

  def unapply(_item: PacketMSG): _root_.scala.Option[scala.Product2[Short, ByteBuffer]] = _root_.scala.Some(_item)


  @inline private def readIdValue(_iprot: TProtocol): Short = {
    _iprot.readI16()
  }

  @inline private def writeIdField(id_item: Short, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(IdField)
    writeIdValue(id_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeIdValue(id_item: Short, _oprot: TProtocol): Unit = {
    _oprot.writeI16(id_item)
  }

  @inline private def readDataValue(_iprot: TProtocol): ByteBuffer = {
    _iprot.readBinary()
  }

  @inline private def writeDataField(data_item: ByteBuffer, _oprot: TProtocol): Unit = {
    _oprot.writeFieldBegin(DataField)
    writeDataValue(data_item, _oprot)
    _oprot.writeFieldEnd()
  }

  @inline private def writeDataValue(data_item: ByteBuffer, _oprot: TProtocol): Unit = {
    _oprot.writeBinary(data_item)
  }


  object Immutable extends ThriftStructCodec3[PacketMSG] {
    override def encode(_item: PacketMSG, _oproto: TProtocol): Unit = { _item.write(_oproto) }
    override def decode(_iprot: TProtocol): PacketMSG = PacketMSG.decode(_iprot)
    override lazy val metaData: ThriftStructMetaData[PacketMSG] = PacketMSG.metaData
  }

  /**
   * The default read-only implementation of PacketMSG.  You typically should not need to
   * directly reference this class; instead, use the PacketMSG.apply method to construct
   * new instances.
   */
  class Immutable(
      val id: Short,
      val data: ByteBuffer,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends PacketMSG {
    def this(
      id: Short,
      data: ByteBuffer
    ) = this(
      id,
      data,
      Map.empty
    )
  }

  /**
   * This is another Immutable, this however keeps strings as lazy values that are lazily decoded from the backing
   * array byte on read.
   */
  private[this] class LazyImmutable(
      _proto: LazyTProtocol,
      _buf: Array[Byte],
      _start_offset: Int,
      _end_offset: Int,
      val id: Short,
      val data: ByteBuffer,
      override val _passthroughFields: immutable$Map[Short, TFieldBlob])
    extends PacketMSG {

    override def write(_oprot: TProtocol): Unit = {
      _oprot match {
        case i: LazyTProtocol => i.writeRaw(_buf, _start_offset, _end_offset - _start_offset)
        case _ => super.write(_oprot)
      }
    }


    /**
     * Override the super hash code to make it a lazy val rather than def.
     *
     * Calculating the hash code can be expensive, caching it where possible
     * can provide significant performance wins. (Key in a hash map for instance)
     * Usually not safe since the normal constructor will accept a mutable map or
     * set as an arg
     * Here however we control how the class is generated from serialized data.
     * With the class private and the contract that we throw away our mutable references
     * having the hash code lazy here is safe.
     */
    override lazy val hashCode = super.hashCode
  }

  /**
   * This Proxy trait allows you to extend the PacketMSG trait with additional state or
   * behavior and implement the read-only methods from PacketMSG using an underlying
   * instance.
   */
  trait Proxy extends PacketMSG {
    protected def _underlying_PacketMSG: PacketMSG
    override def id: Short = _underlying_PacketMSG.id
    override def data: ByteBuffer = _underlying_PacketMSG.data
    override def _passthroughFields = _underlying_PacketMSG._passthroughFields
  }
}

trait PacketMSG
  extends ThriftStruct
  with scala.Product2[Short, ByteBuffer]
  with java.io.Serializable
{
  import PacketMSG._

  def id: Short
  def data: ByteBuffer

  def _passthroughFields: immutable$Map[Short, TFieldBlob] = immutable$Map.empty

  def _1 = id
  def _2 = data


  /**
   * Gets a field value encoded as a binary blob using TCompactProtocol.  If the specified field
   * is present in the passthrough map, that value is returned.  Otherwise, if the specified field
   * is known and not optional and set to None, then the field is serialized and returned.
   */
  def getFieldBlob(_fieldId: Short): _root_.scala.Option[TFieldBlob] = {
    lazy val _buff = new TMemoryBuffer(32)
    lazy val _oprot = new TCompactProtocol(_buff)
    _passthroughFields.get(_fieldId) match {
      case blob: _root_.scala.Some[TFieldBlob] => blob
      case _root_.scala.None => {
        val _fieldOpt: _root_.scala.Option[TField] =
          _fieldId match {
            case 1 =>
              if (true) {
                writeIdValue(id, _oprot)
                _root_.scala.Some(PacketMSG.IdField)
              } else {
                _root_.scala.None
              }
            case 2 =>
              if (data ne null) {
                writeDataValue(data, _oprot)
                _root_.scala.Some(PacketMSG.DataField)
              } else {
                _root_.scala.None
              }
            case _ => _root_.scala.None
          }
        _fieldOpt match {
          case _root_.scala.Some(_field) =>
            val _data = Arrays.copyOfRange(_buff.getArray, 0, _buff.length)
            _root_.scala.Some(TFieldBlob(_field, _data))
          case _root_.scala.None =>
            _root_.scala.None
        }
      }
    }
  }

  /**
   * Collects TCompactProtocol-encoded field values according to `getFieldBlob` into a map.
   */
  def getFieldBlobs(ids: TraversableOnce[Short]): immutable$Map[Short, TFieldBlob] =
    (ids flatMap { id => getFieldBlob(id) map { id -> _ } }).toMap

  /**
   * Sets a field using a TCompactProtocol-encoded binary blob.  If the field is a known
   * field, the blob is decoded and the field is set to the decoded value.  If the field
   * is unknown and passthrough fields are enabled, then the blob will be stored in
   * _passthroughFields.
   */
  def setField(_blob: TFieldBlob): PacketMSG = {
    var id: Short = this.id
    var data: ByteBuffer = this.data
    var _passthroughFields = this._passthroughFields
    _blob.id match {
      case 1 =>
        id = readIdValue(_blob.read)
      case 2 =>
        data = readDataValue(_blob.read)
      case _ => _passthroughFields += (_blob.id -> _blob)
    }
    new Immutable(
      id,
      data,
      _passthroughFields
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetField(_fieldId: Short): PacketMSG = {
    var id: Short = this.id
    var data: ByteBuffer = this.data

    _fieldId match {
      case 1 =>
        id = 0
      case 2 =>
        data = null
      case _ =>
    }
    new Immutable(
      id,
      data,
      _passthroughFields - _fieldId
    )
  }

  /**
   * If the specified field is optional, it is set to None.  Otherwise, if the field is
   * known, it is reverted to its default value; if the field is unknown, it is removed
   * from the passthroughFields map, if present.
   */
  def unsetId: PacketMSG = unsetField(1)

  def unsetData: PacketMSG = unsetField(2)


  override def write(_oprot: TProtocol): Unit = {
    PacketMSG.validate(this)
    _oprot.writeStructBegin(Struct)
    writeIdField(id, _oprot)
    if (data ne null) writeDataField(data, _oprot)
    if (_passthroughFields.nonEmpty) {
      _passthroughFields.values.foreach { _.write(_oprot) }
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    id: Short = this.id,
    data: ByteBuffer = this.data,
    _passthroughFields: immutable$Map[Short, TFieldBlob] = this._passthroughFields
  ): PacketMSG =
    new Immutable(
      id,
      data,
      _passthroughFields
    )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[PacketMSG]

  override def equals(other: Any): Boolean =
    canEqual(other) &&
      _root_.scala.runtime.ScalaRunTime._equals(this, other) &&
      _passthroughFields == other.asInstanceOf[PacketMSG]._passthroughFields

  override def hashCode: Int = _root_.scala.runtime.ScalaRunTime._hashCode(this)

  override def toString: String = _root_.scala.runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 2

  override def productElement(n: Int): Any = n match {
    case 0 => this.id
    case 1 => this.data
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "PacketMSG"
}