package http.api

import http.api.Api._
import http.api.Paging.PagedResponse
import play.api.libs.json._

/*
* Successful response for an ApiRequest.
*/
case class ApiResponse(status: Int, json: JsValue, headers: Seq[(String, String)]) extends ApiResult

object ApiResponse {

  //////////////////////////////////////////////////////////////////////
  // Status Codes

  final val STATUS_OK = 200
  final val STATUS_CREATED = 201
  final val STATUS_ACCEPTED = 202
  final val STATUS_NOCONTENT = 204

  //////////////////////////////////////////////////////////////////////
  // Predefined responses

  def ok(json: JsValue, headers: (String, String)*) = apply(STATUS_OK, json, headers)

  def ok[A](json: JsValue, page: PagedResponse[A], headers: (String, String)*) = apply(STATUS_OK, json, headers ++ Seq(
    HEADER_PAGE -> page.currentPage.toString,
    HEADER_PAGE_FROM -> page.from.toString,
    HEADER_PAGE_SIZE -> page.perPage.toString,
    HEADER_PAGE_TOTAL -> page.total.toString
  ))

  def created(json: JsValue, headers: (String, String)*) = apply(STATUS_CREATED, json, headers)

  def created(headers: (String, String)*) = apply(STATUS_CREATED, JsNull, headers)

  def accepted(json: JsValue, headers: (String, String)*) = apply(STATUS_ACCEPTED, json, headers)

  def accepted(headers: (String, String)*) = apply(STATUS_ACCEPTED, JsNull, headers)

  def noContent(headers: (String, String)*) = apply(STATUS_NOCONTENT, JsNull, headers)

}