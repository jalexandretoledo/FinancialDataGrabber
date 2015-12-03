package financialdatagrabber.main

import dispatch._, Defaults._

object Teste extends App {
  override def main(args: Array[String]){
    println("Hello!")
    /*
    val svc = url("http://api.hostip.info/country.php")
    val country = Http(svc OK as.String)
    
    for (c <- country)
      println(c)
    */  
    
    val id = sys.env("FINANCIAL_DATA_GRABBER__CVM_ID")
    val pwd = sys.env("FINANCIAL_DATA_GRABBER__CVM_PWD")
    
    val content = """<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/"><s:Body xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><Login xmlns="http://www.cvm.gov.br/webservices/"><iNrSist>""" + id + """</iNrSist><strSenha>""" + pwd + """</strSenha></Login></s:Body></s:Envelope>"""
    
    val contentLength = content.length()
    println("Length: " + contentLength);
    
    
    val req = url("http://sistemas.cvm.gov.br/webservices/Sistemas/SCW/CDocs/WsDownloadInfs.asmx")
    val cvm0 = req.POST
    val cvm1 = cvm0.setContentType("text/xml", "utf-8")
    val cvm2 = cvm1.setFollowRedirects(true)
    
    val cvm3 = cvm2.addQueryParameter("op", "Login")
    val cvm4 = cvm3 << Map(
        "SOAPAction" -> "http://www.cvm.gov.br/webservices/Login",
        "Content-Length" -> ("" + contentLength)
    )
    
    
    val cvm5 = cvm4 << content
    
    val result = Http(cvm5)
    for (c <- result) {
      println(c)
      println("---------------------------------------------------")
      println(c.getResponseBody())
      println("---------------------------------------------------")
    }
    
    
  }  
}