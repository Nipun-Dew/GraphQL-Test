package graphql.schema

import persistance.dao.CommonDAO
import persistance.model.Picture
import sangria.schema._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PictureQuery {
 def loadPicture(ctx: Context[CommonDAO, Unit],
                 id: Argument[Option[Int]],
                 paperId: Argument[Option[Int]]): Future[List[Picture]] = {

   if (ctx.args.argOpt(id.name).isDefined) {
     ctx.ctx.loadPicture(ctx.arg(id).get)
   } else if (ctx.args.argOpt(paperId.name).isDefined) {
     ctx.ctx.loadPictureByPaperId(ctx.arg(paperId).get)
   } else {
     Future(List())
   }
 }
}
