package samples

import org.junit._
import Assert._
import fr.tison.dbtester.App
import scala.App

@Test
class AppTest
{

  @Test
  def testOK() =
  {
    new App {}.main(List("--user","monuser","--password","monpassord","--url","monurl").toArray)
  }

  @Test
  def testKO() =
  {
    new App {}.main(List("--user","monuser","--password","mypassword","--url").toArray)
  }

  @Test
  def testJavaDatabase() =
  {
    new App {}.main(List("--user","monuser","--password","mypassword","--url", "java:derby:dbtest;create=true", "--driver", "org.apache.derby.jdbc.EmbeddedDriver").toArray)
  }


  @Test
  def testClassNotFound() =
  {
    assertEquals( 3, App.testdb("monuser","mypassword","java:derby:target/dbtest;create=true", "org.apache.derby.jdbc.NexistepasDriver"))
  }

  @Test
  def testDbNotFound() =
  {
    assertEquals( 2, App.testdb("monuser","mypassword","jdbc:derby:target/dbnotexist;create=false", "org.apache.derby.jdbc.EmbeddedDriver"))
  }

  @Test
  def testDbExist() =
  {
    assertEquals( 0, App.testdb("monuser","mypassword","jdbc:derby:target/dbtest;create=true", "org.apache.derby.jdbc.EmbeddedDriver"))
  }
}


