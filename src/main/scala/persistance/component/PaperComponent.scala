package persistance.component

import persistance.model.Paper
import slick.lifted.ProvenShape

trait PaperComponent {

  import slick.jdbc.PostgresProfile.api._

  class PaperTable(tag: Tag) extends Table[Paper](tag, _schemaName = Some("graphql"), _tableName = "paper") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    def pictureId: Rep[Int] = column[Int]("pictureId")

    def author: Rep[String] = column[String]("author")

    def isbn: Rep[String] = column[String]("isbn")

    def * : ProvenShape[Paper] = (id, pictureId, author, isbn) <> (Paper.tupled, Paper.unapply)
  }

  lazy val paperTableQuery = TableQuery[PaperTable]

}
