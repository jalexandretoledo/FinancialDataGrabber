package scalaxb

import concurrent.Future
import scala.concurrent._, duration._

trait DispatchHttpClientsAsync extends HttpClientsAsync {
  lazy val httpClient = new DispatchHttpClient {}
  // https://github.com/AsyncHttpClient/async-http-client/blob/1.9.x/src/main/java/com/ning/http/client/AsyncHttpClientConfigDefaults.java
  def requestTimeout: Duration = 60.seconds
  def connectionTimeout: Duration = 5.seconds

  trait DispatchHttpClient extends HttpClient {
    import dispatch._, Defaults._

    // Keep it lazy. See https://github.com/eed3si9n/scalaxb/pull/279
    lazy val http = Http.configure(_.
      setRequestTimeoutInMs(requestTimeout.toMillis.toInt).
      setConnectionTimeoutInMs(connectionTimeout.toMillis.toInt))

    def request(in: String, address: java.net.URI, headers: Map[String, String]): concurrent.Future[String] = {
      val req = url(address.toString).setBodyEncoding("UTF-8") <:< headers << in
      http(req > as.String)
    }
  }
}
