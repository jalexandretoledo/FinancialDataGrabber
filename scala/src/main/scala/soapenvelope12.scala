// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
package soapenvelope12


case class Envelope(Header: Option[soapenvelope12.Header] = None,
  Body: soapenvelope12.Body,
  attributes: Map[String, scalaxb.DataRecord[Any]])


/** 
	  Elements replacing the wildcard MUST be namespace qualified, but can be in the targetNamespace
	  
*/
case class Header(any: Seq[scalaxb.DataRecord[Any]] = Nil,
  attributes: Map[String, scalaxb.DataRecord[Any]])


case class Body(any: Seq[scalaxb.DataRecord[Any]] = Nil,
  attributes: Map[String, scalaxb.DataRecord[Any]])


/** 
	    Fault reporting structure
	  
*/
case class Fault(Code: soapenvelope12.Faultcode,
  Reason: soapenvelope12.Faultreason,
  Node: Option[java.net.URI] = None,
  Role: Option[java.net.URI] = None,
  Detail: Option[soapenvelope12.Detail] = None)


case class Faultreason(Text: soapenvelope12.Reasontext*)


case class Reasontext(value: String,
  xmllang: String)


case class Faultcode(Value: soapenvelope12.FaultcodeEnum,
  Subcode: Option[soapenvelope12.Subcode] = None)

trait FaultcodeEnum

object FaultcodeEnum {
  def fromString(value: String, scope: scala.xml.NamespaceBinding): FaultcodeEnum = ({ val (ns, localPart) = scalaxb.Helper.splitQName(value, scope)
    new javax.xml.namespace.QName(ns.orNull, localPart).toString }) match {
    case "{http://www.w3.org/2003/05/soap-envelope}DataEncodingUnknown" => U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125DataEncodingUnknown
    case "{http://www.w3.org/2003/05/soap-envelope}MustUnderstand" => U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125MustUnderstand
    case "{http://www.w3.org/2003/05/soap-envelope}Receiver" => U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125Receiver
    case "{http://www.w3.org/2003/05/soap-envelope}Sender" => U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125Sender
    case "{http://www.w3.org/2003/05/soap-envelope}VersionMismatch" => U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125VersionMismatch

  }
}

case object U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125DataEncodingUnknown extends FaultcodeEnum { override def toString = "{http://www.w3.org/2003/05/soap-envelope}DataEncodingUnknown" }
case object U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125MustUnderstand extends FaultcodeEnum { override def toString = "{http://www.w3.org/2003/05/soap-envelope}MustUnderstand" }
case object U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125Receiver extends FaultcodeEnum { override def toString = "{http://www.w3.org/2003/05/soap-envelope}Receiver" }
case object U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125Sender extends FaultcodeEnum { override def toString = "{http://www.w3.org/2003/05/soap-envelope}Sender" }
case object U123httpu58u47u47wwwu46w3u46orgu472003u4705u47soapu45envelopeu125VersionMismatch extends FaultcodeEnum { override def toString = "{http://www.w3.org/2003/05/soap-envelope}VersionMismatch" }


case class Subcode(Value: javax.xml.namespace.QName,
  Subcode: Option[soapenvelope12.Subcode] = None)


case class Detail(any: Seq[scalaxb.DataRecord[Any]] = Nil,
  attributes: Map[String, scalaxb.DataRecord[Any]])


case class NotUnderstoodType(qname: javax.xml.namespace.QName)


case class SupportedEnvType(qname: javax.xml.namespace.QName)


case class UpgradeType(SupportedEnvelope: soapenvelope12.SupportedEnvType*)

