package financialdatagrabber.main

import dispatch._, Defaults._
import scala.concurrent.duration._

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
    
    val content = """<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
      <s:Body xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
      <Login xmlns="http://www.cvm.gov.br/webservices/">
      <iNrSist>""" + id + """</iNrSist>
      <strSenha>""" + pwd + """</strSenha>
      </Login>
      </s:Body></s:Envelope>"""
    
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
      
      val loginData = getLoginData(c.getResponseBody())
      println("Guid: [" + loginData._1 + "]")
      println("Id: [" + loginData._2 + "]")
      println("---------------------------------------------------")
      
      getFundosURL(loginData._1, loginData._2)
    }
    
    println("Done !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
  }  
  
  def getLoginData(body: String) = {
    println("-- 14 ------------------")
    //val guid = """<Guid>(.*)<.Guid>""".r
    //val id = """<IdSessao>(.*)<.IdSessao>""".r    
    val guid = """<Guid>(.*)<.Guid>""".r.unanchored
    val id = """<IdSessao>(.*)<.IdSessao>""".r.unanchored

   
    
    val guidFound = body match {
      case guid(guidStr) => guidStr
      case _ => "???"
    }
    
    val idFound = body match {
      case id(idStr) => idStr
      case _ => "???"
    }
    
    println("Login Data: " + guidFound + ", " + idFound)
    
    (guidFound, idFound)
  }
  
  def getFundosURL(guid: String, idSessao: String) = {
    val content = """<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
      <s:Header>
        <h:sessaoIdHeader xmlns="http://www.cvm.gov.br/webservices/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:h="http://www.cvm.gov.br/webservices/">
          <Guid>""" + guid + """</Guid>
          <IdSessao>""" +  idSessao + """</IdSessao>
        </h:sessaoIdHeader>
      </s:Header>
      <s:Body xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        <solicAutorizDownloadCadastro xmlns="http://www.cvm.gov.br/webservices/">
          <strDtRefer>""" + "2015-12-08" + """</strDtRefer>
          <strMotivoAutorizDownload>Teste de Sistema</strMotivoAutorizDownload>
        </solicAutorizDownloadCadastro>
      </s:Body>
    </s:Envelope>"""
      
    val contentLength = content.length()
    println("Length: " + contentLength);
    println("Content: " + content)
    
    val req = url("http://sistemas.cvm.gov.br/webservices/Sistemas/SCW/CDocs/WsDownloadInfs.asmx")
    val cvm0 = req.POST
    val cvm1 = cvm0.setContentType("text/xml", "utf-8")
    val cvm2 = cvm1.setFollowRedirects(true)
    
    val cvm4 = cvm2 << Map(
        "SOAPAction" -> "http://www.cvm.gov.br/webservices/solicAutorizDownloadCadastro",
        "Content-Length" -> ("" + contentLength)
    )
    
    val cvm5 = cvm4 << content
    val result = Http(cvm5)
    
    for (c <- result) {
      println(c)
      println("-[Download Cadastro de Fundos]--------------------------------------------------")
      println(c.getResponseBody())
      println("--------------------------------------------------------------------------------")
      
      val urlRE = """<solicAutorizDownloadCadastroResult>(.*)<.solicAutorizDownloadCadastroResult>""".r.unanchored
      
      val url = c.getResponseBody() match {
        case urlRE(endereco) => endereco
      }
      
      println("URL: " + url.replaceAll("&amp;", "&"))
    }
  }
    
}