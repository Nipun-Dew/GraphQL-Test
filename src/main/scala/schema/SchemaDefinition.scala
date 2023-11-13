package schema

import persistance.dao.PicturesDAO
import persistance.model.Picture
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

  private val Id: Argument[Int] = Argument("id", IntType)

  private val QueryType: ObjectType[PicturesDAO, Unit] = ObjectType("Query", fields[PicturesDAO, Unit](
    Field("picture", ListType(PictureType),
      description = Some("Returns a picture with specific `id`."),
      arguments = Id :: Nil,
      resolve = c => c.ctx.load(c arg Id)),

    Field("pictures", ListType(PictureType),
      description = Some("Returns a list of all available pictures."),
      resolve = _.ctx.loadAll)))

  val schema: Schema[PicturesDAO, Unit] = Schema(QueryType)
}
