package utils

import http.api.ApiRequest
import play.api.{DefaultMarkerContext, MarkerContext}

trait RequestMarkerContext {

  // Adding 'implicit request' enables implicit conversion chaining
  // See http://docs.scala-lang.org/tutorials/FAQ/chaining-implicits.html
  implicit def requestHeaderToMarkerContext(implicit request: ApiRequest[_]): MarkerContext = {
    import net.logstash.logback.marker.LogstashMarker
    import net.logstash.logback.marker.Markers._

    val requestMarkers: LogstashMarker = append("host", request.host)
      .and(append("path", request.path))

    MarkerContext(requestMarkers)
  }

}