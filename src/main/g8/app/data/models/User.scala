package data.models
import play.api.libs.json.{Json, OFormat}

object User {
  implicit val jsonFormat: OFormat[User] = Json.format[User]
}

case class User(id: Long, name: String, email: String, hashedPassword: String) {}
