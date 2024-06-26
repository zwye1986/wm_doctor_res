<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="slideRight" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style>
        a{color:#4195c5}

        .fileTable td{
            border: 0px;
        }
    </style>
    <script>

    </script>
</head>
<body>
<div class="mainright" style="max-height: 400px;overflow: auto">
    <div id="base" class="main_bd clearfix" style="margin-top:20px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width: 94px;">考核部分</th>
                <th style="width: 100px;">考站</th>
                <th style="width: 183px;">考核内容</th>
                <th style="width: 60px;">分值（分）</th>
            </tr>
            <tbody id="appendTbody">
                <c:forEach items="${subjectStations}" var="item" varStatus="s">
                    <tr id="stationFrom_${s.index}-">
                        <td>
                            <select class="input validate[required]" style="width: 85%;height: 30px"name="partFlow" disabled="disabled">
                                <option/>
                                <c:forEach items="${dictTypeEnumExamPartList}" var="dict">
                                   <option value="${dict.dictId}"
                                   <c:if test="${item.partFlow eq dict.dictId}">selected</c:if>
                                   >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input readonly="readonly" class="input validate[required]" style="width: 85%;" type="text" name="stationName" value="${item.stationName}">
                        </td>
                        <td>
                            <input readonly="readonly" class="input" style="width: 85%;" type="text" name="examinedContent" value="${item.examinedContent}">
                        </td>
                        <td>
                            <input readonly="readonly" class="input validate[custom[number]] validate[maxSize[5]] validate[required]" style="width: 85%;" type="text" name="stationScore" value="${item.stationScore}">
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div style="text-align: center;margin-top: 15px">
            <input type="button" value="关&#12288;闭" class="btn_green" onclick="jboxClose();">
        </div>
    </div>
</div>
</body>
</html>
