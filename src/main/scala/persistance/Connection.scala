package persistance

import slick.jdbc.PostgresProfile.api._

object Connection {
  lazy val db = Database.forConfig("postgres")
}
