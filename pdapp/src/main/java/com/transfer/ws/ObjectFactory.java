
package com.transfer.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.transfer.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SynDeptInfoResponse_QNAME = new QName("http://transfer.pd.com/", "synDeptInfoResponse");
    private final static QName _SynEmpInfoResponse_QNAME = new QName("http://transfer.pd.com/", "synEmpInfoResponse");
    private final static QName _GetResrecDataResponse_QNAME = new QName("http://transfer.pd.com/", "getResrecDataResponse");
    private final static QName _GetHRRResponse_QNAME = new QName("http://transfer.pd.com/", "getHRRResponse");
    private final static QName _GetResrecData_QNAME = new QName("http://transfer.pd.com/", "getResrecData");
    private final static QName _SynDeptInfo_QNAME = new QName("http://transfer.pd.com/", "synDeptInfo");
    private final static QName _SynEmpInfo_QNAME = new QName("http://transfer.pd.com/", "synEmpInfo");
    private final static QName _GetHRR_QNAME = new QName("http://transfer.pd.com/", "getHRR");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.transfer.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHRR }
     * 
     */
    public GetHRR createGetHRR() {
        return new GetHRR();
    }

    /**
     * Create an instance of {@link SynEmpInfo }
     * 
     */
    public SynEmpInfo createSynEmpInfo() {
        return new SynEmpInfo();
    }

    /**
     * Create an instance of {@link SynDeptInfo }
     * 
     */
    public SynDeptInfo createSynDeptInfo() {
        return new SynDeptInfo();
    }

    /**
     * Create an instance of {@link GetResrecData }
     * 
     */
    public GetResrecData createGetResrecData() {
        return new GetResrecData();
    }

    /**
     * Create an instance of {@link GetHRRResponse }
     * 
     */
    public GetHRRResponse createGetHRRResponse() {
        return new GetHRRResponse();
    }

    /**
     * Create an instance of {@link GetResrecDataResponse }
     * 
     */
    public GetResrecDataResponse createGetResrecDataResponse() {
        return new GetResrecDataResponse();
    }

    /**
     * Create an instance of {@link SynEmpInfoResponse }
     * 
     */
    public SynEmpInfoResponse createSynEmpInfoResponse() {
        return new SynEmpInfoResponse();
    }

    /**
     * Create an instance of {@link SynDeptInfoResponse }
     * 
     */
    public SynDeptInfoResponse createSynDeptInfoResponse() {
        return new SynDeptInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynDeptInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "synDeptInfoResponse")
    public JAXBElement<SynDeptInfoResponse> createSynDeptInfoResponse(SynDeptInfoResponse value) {
        return new JAXBElement<SynDeptInfoResponse>(_SynDeptInfoResponse_QNAME, SynDeptInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynEmpInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "synEmpInfoResponse")
    public JAXBElement<SynEmpInfoResponse> createSynEmpInfoResponse(SynEmpInfoResponse value) {
        return new JAXBElement<SynEmpInfoResponse>(_SynEmpInfoResponse_QNAME, SynEmpInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResrecDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "getResrecDataResponse")
    public JAXBElement<GetResrecDataResponse> createGetResrecDataResponse(GetResrecDataResponse value) {
        return new JAXBElement<GetResrecDataResponse>(_GetResrecDataResponse_QNAME, GetResrecDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHRRResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "getHRRResponse")
    public JAXBElement<GetHRRResponse> createGetHRRResponse(GetHRRResponse value) {
        return new JAXBElement<GetHRRResponse>(_GetHRRResponse_QNAME, GetHRRResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResrecData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "getResrecData")
    public JAXBElement<GetResrecData> createGetResrecData(GetResrecData value) {
        return new JAXBElement<GetResrecData>(_GetResrecData_QNAME, GetResrecData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynDeptInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "synDeptInfo")
    public JAXBElement<SynDeptInfo> createSynDeptInfo(SynDeptInfo value) {
        return new JAXBElement<SynDeptInfo>(_SynDeptInfo_QNAME, SynDeptInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynEmpInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "synEmpInfo")
    public JAXBElement<SynEmpInfo> createSynEmpInfo(SynEmpInfo value) {
        return new JAXBElement<SynEmpInfo>(_SynEmpInfo_QNAME, SynEmpInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHRR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transfer.pd.com/", name = "getHRR")
    public JAXBElement<GetHRR> createGetHRR(GetHRR value) {
        return new JAXBElement<GetHRR>(_GetHRR_QNAME, GetHRR.class, null, value);
    }

}
