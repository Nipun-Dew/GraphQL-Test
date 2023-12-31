package graphql.schema

import persistance.dao.CommonDAO
import persistance.model.{Paper, Picture}
import sangria.schema._

object SchemaDefinition {
  private val PictureType: ObjectType[Unit, Picture] = ObjectType("Picture", fields[Unit, Picture](
    Field("id", IntType, resolve = _.value.id),
    Field("width", IntType, resolve = _.value.width),
    Field("height", IntType, resolve = _.value.height),
    Field("url", OptionType(StringType),
      description = Some("Picture CDN URL"),
      resolve = _.value.url)))

  private val PaperType: ObjectType[Unit, Paper] = ObjectType("Paper", fields[Unit, Paper](
    Field("id", IntType, resolve = _.value.id),
    Field("pictureId", IntType, resolve = _.value.pictureId),
    Field("author", StringType, resolve = _.value.author),
    Field("isbn", StringType, resolve = _.value.isbn)))

  private val Id = Argument("id", OptionInputType(IntType))
  private val PaperId = Argument("paperId", OptionInputType(IntType))

  private val QueryType: ObjectType[CommonDAO, Unit] = ObjectType("Query", fields[CommonDAO, Unit](
    Field("picture", ListType(PictureType),
      description = Some("Returns a picture with specific `id`."),
      arguments = Id :: PaperId :: Nil,
      resolve = c => PictureQuery.loadPicture(c, Id, PaperId)),

    Field("paper", ListType(PaperType),
      description = Some("Returns a paper with specific `id`."),
      arguments = PaperId :: Nil,
      resolve = c => c.ctx.papersDAO.load(c.arg(PaperId).get)),

    Field("pictures", ListType(PictureType),
      description = Some("Returns a list of all available pictures."),
      resolve = _.ctx.picturesDAO.loadAll),

    Field("papers", ListType(PaperType),
      description = Some("Returns a list of all available papers."),
      resolve = _.ctx.papersDAO.loadAll)))

  val schema: Schema[CommonDAO, Unit] = Schema(QueryType)
}
