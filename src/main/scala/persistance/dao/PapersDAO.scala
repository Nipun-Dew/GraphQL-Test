package persistance.dao

import persistance.Connection.db
import persistance.component.DAL._
import persistance.model.{Paper, Picture}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait PapersDAO {

  import slick.jdbc.PostgresProfile.api._

  def loadPaper(id: Int): Future[List[Paper]] =
    db.run(paperTableQuery.filter(_.id === id).result).map(_.toList)

  def loadAllPapers: Future[List[Paper]] =
    db.run(paperTableQuery.result).map(_.toList)

  def loadPictureByPaperId(id: Int): Future[List[Picture]] = {
    val query = for {
      queryResults <- (paperTableQuery.filter(_.id === id) join
        pictureTableQuery on (_.pictureId === _.id)).result
    } yield queryResults.map(_._2).toList
    db.run(query)
  }
}
