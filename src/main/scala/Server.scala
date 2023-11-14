import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import graphql.QueryExecutor.graphQLEndpoint
import spray.json.JsValue
import utils.Configs

object Server extends App with Configs {

  implicit val system: ActorSystem = ActorSystem("graphql-server")

  private val route: Route = post {
    path("graphql") {
      entity(as[JsValue]) { requestJson =>
        graphQLEndpoint(requestJson)
      }
    }
  }

  Http().newServerAt(HOST, PORT).bind(route)
}
