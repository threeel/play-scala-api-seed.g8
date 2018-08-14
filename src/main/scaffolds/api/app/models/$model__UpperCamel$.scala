package models

import play.api.libs.json.{Json, OFormat}

object $model;format="UpperCamel"$ {
  implicit val jsonFormat: OFormat[$model;format="UpperCamel"$] = Json.format[$model;format="UpperCamel"$]
}

case class $model;format="UpperCamel"$(name: String) {}
