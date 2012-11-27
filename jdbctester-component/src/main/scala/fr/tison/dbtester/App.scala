package fr.tison.dbtester

import java.sql.{SQLException, DriverManager, Connection}

/**
 * TODO Comment
 */
object App {

  def testdb(user:String, password:String, url:String, driverName:String):Int =
  {
    //val driver = "oracle.jdbc.OracleDriver"

    // there's probably a better way to do this
    var connection:Connection = null

    try {
      // make the connection
      Class.forName(driverName)
      connection = DriverManager.getConnection(url, user, password)

      // create the statement, and run the select query
//      val statement = connection.createStatement()
//      val resultSet = statement.executeQuery("SELECT * from dual")
/*      while ( resultSet.next() ) {
        val host = resultSet.getString("host")
        val user = resultSet.getString("user")
        println("host, user = " + host + ", " + user)
      }
*/
      if(connection!=null)
      {
        connection.close()
        0 // Connection established. 0=nagios return code to say OK
      }
      else 2 // Connection failed. 2=nagios plugin return code to say CRITICAL
    } catch {
      case e:SQLException => 2 // CRITICAL
      case e => 3 // UNKOWN return code
    }

  }



  def main(args : Array[String]) : Int =
  {
    def readArgument(argName:String):String =
    {
      try args(args.indexOf(argName)+1)
      catch {
        case _:java.lang.IndexOutOfBoundsException => println("missing mandatory value for " + argName); argName+ "value not Found"
      }
    }
    val user = readArgument("--user")
    val password = readArgument("--password")
    val url = readArgument("--url")
    val driverName = readArgument("--driver")

    sys.exit(testdb(user, password, url, driverName))
  }

}
