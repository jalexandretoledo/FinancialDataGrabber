// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
package financialdatagrabber.services.cvm

import scala.concurrent.Future


trait WsDownloadInfsSoap {
  def retornaListaComptcDocs(retornaListaComptcDocs: financialdatagrabber.services.cvm.RetornaListaComptcDocs): Future[financialdatagrabber.services.cvm.RetornaListaComptcDocsOutput]
  def retornaListaComptcDocsPartic(retornaListaComptcDocsPartic: financialdatagrabber.services.cvm.RetornaListaComptcDocsPartic): Future[financialdatagrabber.services.cvm.RetornaListaComptcDocsParticOutput]
  def retornaListaComptcDocsAdm(retornaListaComptcDocsAdm: financialdatagrabber.services.cvm.RetornaListaComptcDocsAdm): Future[financialdatagrabber.services.cvm.RetornaListaComptcDocsAdmOutput]
  def solicAutorizDownloadCadastro(solicAutorizDownloadCadastro: financialdatagrabber.services.cvm.SolicAutorizDownloadCadastro): Future[financialdatagrabber.services.cvm.SolicAutorizDownloadCadastroOutput]
  def solicAutorizDownloadArqComptc(solicAutorizDownloadArqComptc: financialdatagrabber.services.cvm.SolicAutorizDownloadArqComptc): Future[financialdatagrabber.services.cvm.SolicAutorizDownloadArqComptcOutput]
  def solicAutorizDownloadArqEntrega(solicAutorizDownloadArqEntrega: financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntrega): Future[financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntregaOutput]
  def solicAutorizDownloadArqEntregaPorData(solicAutorizDownloadArqEntregaPorData: financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntregaPorData): Future[financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntregaPorDataOutput]
  def solicAutorizDownloadArqAnual(solicAutorizDownloadArqAnual: financialdatagrabber.services.cvm.SolicAutorizDownloadArqAnual): Future[financialdatagrabber.services.cvm.SolicAutorizDownloadArqAnualOutput]
  def retornaDtLmtEntrDocsArqsDisp(retornaDtLmtEntrDocsArqsDisp: financialdatagrabber.services.cvm.RetornaDtLmtEntrDocsArqsDisp): Future[financialdatagrabber.services.cvm.RetornaDtLmtEntrDocsArqsDispOutput]
  def retornaListaDownloadDocs(retornaListaDownloadDocs: financialdatagrabber.services.cvm.RetornaListaDownloadDocs): Future[financialdatagrabber.services.cvm.RetornaListaDownloadDocsOutput]
  def login(login: financialdatagrabber.services.cvm.Login): Future[financialdatagrabber.services.cvm.LoginOutput]
}

case class RetornaListaComptcDocsOutput(retornaListaComptcDocsResponse: financialdatagrabber.services.cvm.RetornaListaComptcDocsResponse)

case class RetornaListaComptcDocsParticOutput(retornaListaComptcDocsParticResponse: financialdatagrabber.services.cvm.RetornaListaComptcDocsParticResponse)

case class RetornaListaComptcDocsAdmOutput(retornaListaComptcDocsAdmResponse: financialdatagrabber.services.cvm.RetornaListaComptcDocsAdmResponse)

case class SolicAutorizDownloadCadastroOutput(solicAutorizDownloadCadastroResponse: financialdatagrabber.services.cvm.SolicAutorizDownloadCadastroResponse)

case class SolicAutorizDownloadArqComptcOutput(solicAutorizDownloadArqComptcResponse: financialdatagrabber.services.cvm.SolicAutorizDownloadArqComptcResponse)

case class SolicAutorizDownloadArqEntregaOutput(solicAutorizDownloadArqEntregaResponse: financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntregaResponse)

case class SolicAutorizDownloadArqEntregaPorDataOutput(solicAutorizDownloadArqEntregaPorDataResponse: financialdatagrabber.services.cvm.SolicAutorizDownloadArqEntregaPorDataResponse)

case class SolicAutorizDownloadArqAnualOutput(solicAutorizDownloadArqAnualResponse: financialdatagrabber.services.cvm.SolicAutorizDownloadArqAnualResponse)

case class RetornaDtLmtEntrDocsArqsDispOutput(retornaDtLmtEntrDocsArqsDispResponse: financialdatagrabber.services.cvm.RetornaDtLmtEntrDocsArqsDispResponse)

case class RetornaListaDownloadDocsOutput(retornaListaDownloadDocsResponse: financialdatagrabber.services.cvm.RetornaListaDownloadDocsResponse)

case class LoginOutput(loginResponse: financialdatagrabber.services.cvm.LoginResponse)

