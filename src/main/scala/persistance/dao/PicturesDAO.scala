package persistance.dao

import persistance.Connection.db
import persistance.component.DAL._
import persistance.model.Picture

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait PicturesDAO {
  import slick.jdbc.PostgresProfile.api._

  def loadPicture(id: Int): Future[List[Picture]] =
    db.run(pictureTableQuery.filter(_.id === id).result).map(_.toList)

  def loadAllPictures: Future[List[Picture]] =
    db.run(pictureTableQuery.result).map(_.toList)
}
