package persistance.dao

import persistance.Connection.db
import persistance.component.DAL._
import persistance.model.Picture

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PicturesDAO {
  import slick.jdbc.PostgresProfile.api._

  def load(id: Int): Future[List[Picture]] =
    db.run(pictureTableQuery.filter(_.id === id).result).map(_.toList)

  def loadAll: Future[List[Picture]] =
    db.run(pictureTableQuery.result).map(_.toList)
}
