package scalikejdbc4j

import scala.collection.convert.{ DecorateAsJava, DecorateAsScala }

/**
 * Java Converters that include Scala collection conversions.
 */
object AllJavaConverters
  extends JavaConverters with JavaConvertersImplicits
  with DecorateAsJava with DecorateAsScala
