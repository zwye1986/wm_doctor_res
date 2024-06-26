<%@page import="com.neusoft.education.tp.sso.client.filter.CASFilter"%>
<%@page import="com.neusoft.education.tp.sso.client.CASReceipt"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  HttpServletRequest httpRequest =(HttpServletRequest) request;
  String httpurl=httpRequest.getRequestURL().toString();
  String servletPath=httpRequest.getServletPath();
  String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath));
  Object obj=session.getAttribute(CASFilter.SSO_FILTER_RECEIPT);
  if(obj!=null)
  {
      CASReceipt receipt = (CASReceipt)obj;
      out.println("cardid=");
      out.println(receipt.getUserName());
      out.println("<br>");

      Map userMap = receipt.getUserMap();
      Iterator keys = userMap.keySet().iterator();
      while (keys.hasNext()) {
        String key = (String)keys.next();
        out.println(key);
        out.println("=");
        out.println(userMap.get(key));
        out.println("<br>");
      }
      //向应用的Session中赋值
      //.................................
     String url = hostUrl + "/inx/gzykdx/index";
     response.sendRedirect(url);
  }else{
    String url = hostUrl + "/inx/gzykdx";
     response.sendRedirect(url);
  }
%>

