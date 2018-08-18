package http.api

/**
 * Helper for pagination.
 */
case class Page[+A](items: Seq[A], page: Int, size: Int, total: Long) {
  def offset: Int = (page - 1) * size + 1
}
