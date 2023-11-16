package utils

import com.typesafe.config.ConfigFactory

trait Configs {
  private val conf = ConfigFactory.load()

  val PORT: Int = conf.getInt("http.port")
  val HOST: String = conf.getString("http.host")

}
