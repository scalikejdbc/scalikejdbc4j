package scalikejdbc4j

import scala.language.implicitConversions
import java.util.Optional
import scalikejdbc4j.converter._

/**
 * Java Converters for ScalikeJDBC users.
 *
 * - Collections
 * - Options
 * - Primitives
 * - Either/Try?
 */
object JavaConverters extends JavaConverters with JavaConvertersImplicits

trait JavaConverters {

  type JavaOptional[A] = java.util.Optional[A]

  type JavaList[A] = java.util.List[A]

  type JavaInteger = java.lang.Integer
  type JavaLong = java.lang.Long
  type JavaBoolean = java.lang.Boolean
  type JavaByte = java.lang.Byte
  type JavaShort = java.lang.Short
  type JavaFloat = java.lang.Float
  type JavaDouble = java.lang.Double
  type JavaCharacter = java.lang.Character

  def toJavaOptional[T](scalaOption: Option[T]): Optional[T] = {
    if (scalaOption.isDefined) Optional.ofNullable(scalaOption.get) else Optional.empty()
  }

  def toJavaInteger(i: Int): JavaInteger = i.asInstanceOf[JavaInteger]

  def toJavaLong(l: Long): JavaLong = l.asInstanceOf[JavaLong]

  def toJavaBoolean(b: Boolean): JavaBoolean = b.asInstanceOf[JavaBoolean]

  def toJavaByte(b: Byte): JavaByte = b.asInstanceOf[JavaByte]

  def toJavaShort(s: Short): JavaShort = s.asInstanceOf[JavaShort]

  def toJavaFloat(f: Float): JavaFloat = f.asInstanceOf[JavaFloat]

  def toJavaDouble(d: Double): JavaDouble = d.asInstanceOf[JavaDouble]

  def toJavaCharacter(c: Char): JavaCharacter = c.asInstanceOf[JavaCharacter]

}

trait JavaConvertersImplicits {

  implicit def convertOptionAsJava[A](opt: Option[A]): OptionAsJava[A] = OptionAsJava(opt)

  implicit def convertOptionalAsScala[A](opt: Optional[A]): OptionalAsScala[A] = OptionalAsScala(opt)

  implicit def convertJavaIntegerAsScala(i: java.lang.Integer): IntegerAsScala = IntegerAsScala(i)

  implicit def convertJavaLongAsScala(l: java.lang.Long): LongAsScala = LongAsScala(l)

  implicit def convertJavaBooleanAsScala(b: java.lang.Boolean): BooleanAsScala = BooleanAsScala(b)

  implicit def convertJavaByteAsScala(b: java.lang.Byte): ByteAsScala = ByteAsScala(b)

  implicit def convertJavaShortAsScala(s: java.lang.Short): ShortAsScala = ShortAsScala(s)

  implicit def convertJavaFloatAsScala(f: java.lang.Float): FloatAsScala = FloatAsScala(f)

  implicit def convertJavaDoubleAsScala(d: java.lang.Double): DoubleAsScala = DoubleAsScala(d)

  implicit def convertJavaCharacterAsScala(c: java.lang.Character): CharacterAsScala = CharacterAsScala(c)

}

