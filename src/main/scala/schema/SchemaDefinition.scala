package schema

import persistance.dao.{CommonDAO, PicturesDAO}
import persistance.model.{Paper, Picture}
import sangria.schema._

object SchemaDefinition {
  private val PictureType: ObjectType[Unit, Picture] = ObjectType(
    "Picture",
    "The product picture",

    fields[Unit, Picture](
      Field("id", IntType, resolve = _.value.id),
      Field("width", IntType, resolve = _.value.width),
      Field("height", IntType, resolve = _.value.height),
      Field("url", OptionType(StringType),
        description = Some("Picture CDN URL"),
        resolve = _.value.url)))

  private val PaperType: ObjectType[Unit, Paper] = ObjectType(
    "Paper",
    "The product paper",

    fields[Unit, Paper](
      Field("id", IntType, resolve = _.value.id),
      Field("pictureId", IntType, resolve = _.value.pictureId),
      Field("author", StringType, resolve = _.value.author),
      Field("isbn", StringType, resolve = _.value.isbn)))

  private val Id: Argument[Int] = Argument("id", IntType)

  private val QueryType: ObjectType[CommonDAO, Unit] = ObjectType("Query", fields[CommonDAO, Unit](
    Field("picture", ListType(PictureType),
      description = Some("Returns a picture with specific `id`."),
      arguments = Id :: Nil,
      resolve = c => c.ctx.loadPicture(c arg Id)),

    Field("paper", ListType(PaperType),
      description = Some("Returns a paper with specific `id`."),
      arguments = Id :: Nil,
      resolve = c => c.ctx.loadPaper(c arg Id)),

    Field("pictures", ListType(PictureType),
      description = Some("Returns a list of all available pictures."),
      resolve = _.ctx.loadAllPictures),

    Field("papers", ListType(PaperType),
      description = Some("Returns a list of all available papers."),
      resolve = _.ctx.loadAllPapers)))

  val schema: Schema[CommonDAO, Unit] = Schema(QueryType)
}
