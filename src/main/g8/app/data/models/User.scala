package data.models

object User {
  implicit val jsonFormat: OFormat[User] = Json.format[User]
}

case class User(id: Long, name: String, email: String, hashedPassword: String) {}
