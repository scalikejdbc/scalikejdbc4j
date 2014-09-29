## ScalikeJDBC in Java apps

ScalikeJDBC is a tidy JDBC wrapper library in Scala.

http://scalikejdbc.org/

This is a proof of concept of ScalikeJDBC Java adapter. 

If you're interested in using ScalikeJDBC in Java apps, please feel free to send me your feedback.

### Prerequisites

- Java SE 8
- Maven 3

### Examples

You can build application basically in Java except DAO code in Scala.

- [Usage in Java](https://github.com/scalikejdbc/scalikejdbc4j/blob/master/src/test/java/SimpleUsageTest.java)
- [Entities in Java](https://github.com/scalikejdbc/scalikejdbc4j/blob/master/src/test/java/model)
- [DAO in Scala](https://github.com/scalikejdbc/scalikejdbc4j/blob/master/src/test/scala/dao)
 
```sh
sbt test
```

### License

Copyright 2014 scalikejdbc.org

Licensed under the Apache License, Version 2.0 (the "License")

