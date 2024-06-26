
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<style type="text/css">
		table{ margin:10px 0;border-collapse: collapse;}
		caption,th,td{height:40px;}
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
		td{text-align:left; padding-left:5px; }
		[type='text']{width:150px;height: 22px;}
		select{width: 153px;height: 27px;}
	</style>

	<script type="text/javascript">

        $(document).ready(function(){
            toPage3(1);
        });
        function toPage3(page)
        {
            page=page||1;
            var url="<s:url value='/jszy/graduation/searchScore2'/>?doctorFlow=${doctorFlow}"+"&currentPage="+page;
            jboxLoad("examresults",url,true);
        }
	</script>
	<div id="examresults">

	</div>
      
