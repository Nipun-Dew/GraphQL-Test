package persistance.component

import persistance.model.Picture
import slick.lifted.ProvenShape

trait PictureComponent {

  import slick.jdbc.PostgresProfile.api._

  class PictureTable(tag: Tag) extends Table[Picture](tag, _schemaName = Some("graphql"), _tableName="picture") {

    def id: Rep[Int] = column[Int]("id")

    def width: Rep[Int] = column[Int]("width")

    def height: Rep[Int] = column[Int]("height")

    def url: Rep[Option[String]] = column[Option[String]]("url")

    def * : ProvenShape[Picture] = (id, width, height, url) <> (Picture.tupled, Picture.unapply)
  }

  lazy val pictureTableQuery = TableQuery[PictureTable]
}
