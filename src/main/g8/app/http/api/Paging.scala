package http.api

import play.api.libs.json._


object Paging {

  case class Indexed[+T](idx: Long, obj: T)

  object Indexed {
    implicit def jsonFormat[T: Reads] = Json.reads[Indexed[T]]

    implicit def jsonWrite[T: Writes] = new Writes[Indexed[T]] {
      def writes(obj: Indexed[T]): JsObject = {

        val x: JsObject = Json.toJson(obj.obj).as[JsObject]
        val y: JsObject = Json.obj(
          "idx" -> obj.idx)
        x ++ y
      }
    }

  }


  def paginateWithResult[T](items: Seq[T], pagedRequest: PageRequest): PaginatedResult[T] = {

    val itemsResult = items.slice(pagedRequest.offset, pagedRequest.offset + pagedRequest.size)
    PaginatedResult[T](itemsResult, items.size, pagedRequest.page, pagedRequest.size)
  }

  case class PageRequest(
                          page: Int = 1,
                          size: Int = 25,
                          sortFields: Option[List[String]] = None,
                          sortDirections: Option[List[String]] = None,
                          filters: Seq[ModelFilter] = Seq.empty
                        ) {
    def offset: Int = (page - 1) * size
  }

  case class PagedResponse[+T](total: Long = 0, perPage: Int = 0, currentPage: Int = 0, totalPages: Int = 0, from: Long = 0, to: Long = 0, count: Int, items: Seq[Indexed[T]]) {

    def this(paginatedResult: PaginatedResult[T]) = {
      this(
        items = {
          val from_ = ((paginatedResult.page - 1) * paginatedResult.size).toLong
          val to_ = if (paginatedResult.page * paginatedResult.size > paginatedResult.total) paginatedResult.total.toLong
          else (paginatedResult.page * paginatedResult.size).toLong

          paginatedResult.items.zip((from_ + 1) to (to_ + 1)).map(x => Indexed[T](x._2, x._1))

        },
        total = paginatedResult.total.toLong,
        perPage = paginatedResult.size,
        currentPage = paginatedResult.page,
        totalPages = Math.ceil(paginatedResult.total.toDouble / paginatedResult.size).toInt,
        from = ((paginatedResult.page - 1) * paginatedResult.size).toLong,
        to = if (paginatedResult.page * paginatedResult.size > paginatedResult.total) paginatedResult.total.toLong else (paginatedResult.page * paginatedResult.size).toLong,
        count = {
          val from_ = ((paginatedResult.page - 1) * paginatedResult.size).toLong
          val to_ = if (paginatedResult.page * paginatedResult.size > paginatedResult.total) paginatedResult.total.toLong
          else (paginatedResult.page * paginatedResult.size).toLong
          (to_ - from_).toInt
        })
    }
  }

  case class PaginatedResult[T](items: Seq[T], total: Int, page: Int, size: Int)

  object PagedResponse {
    implicit def jsonFormat[T: Format]: OFormat[PagedResponse[T]] = Json.format[PagedResponse[T]]


  }

  implicit class CollectionPaging[T: Format](collection: Seq[T]) {

    def paginate(request: PageRequest): PagedResponse[T] = {
      new PagedResponse[T](paginateWithResult(collection, request))
    }
  }

}