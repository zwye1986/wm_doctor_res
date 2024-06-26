/**
 * SMsgServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pinde.sci.wsld.gyxjgl;

public class SMsgServiceLocator extends org.apache.axis.client.Service implements com.pinde.sci.wsld.gyxjgl.SMsgService {

    public SMsgServiceLocator() {
    }


    public SMsgServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SMsgServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SMsg
    private java.lang.String SMsg_address = "http://10.158.211.35/axis/services/SMsg";

    @Override
    public java.lang.String getSMsgAddress() {
        return SMsg_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SMsgWSDDServiceName = "SMsg";

    public java.lang.String getSMsgWSDDServiceName() {
        return SMsgWSDDServiceName;
    }

    public void setSMsgWSDDServiceName(java.lang.String name) {
        SMsgWSDDServiceName = name;
    }

    @Override
    public com.pinde.sci.wsld.gyxjgl.SMsg_PortType getSMsg() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SMsg_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSMsg(endpoint);
    }

    @Override
    public com.pinde.sci.wsld.gyxjgl.SMsg_PortType getSMsg(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.pinde.sci.wsld.gyxjgl.SMsgSoapBindingStub _stub = new com.pinde.sci.wsld.gyxjgl.SMsgSoapBindingStub(portAddress, this);
            _stub.setPortName(getSMsgWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSMsgEndpointAddress(java.lang.String address) {
        SMsg_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.pinde.sci.wsld.gyxjgl.SMsg_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.pinde.sci.wsld.gyxjgl.SMsgSoapBindingStub _stub = new com.pinde.sci.wsld.gyxjgl.SMsgSoapBindingStub(new java.net.URL(SMsg_address), this);
                _stub.setPortName(getSMsgWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SMsg".equals(inputPortName)) {
            return getSMsg();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.158.211.35/axis/services/SMsg", "SMsgService");
    }

    private java.util.HashSet ports = null;

    @Override
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.158.211.35/axis/services/SMsg", "SMsg"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
        if ("SMsg".equals(portName)) {
                    setSMsgEndpointAddress(address);
        }
        else  { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
