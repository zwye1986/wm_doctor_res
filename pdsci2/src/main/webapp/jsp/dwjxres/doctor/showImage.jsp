<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script>
    var deg=0;
    function rotateImg(d)
    {
        deg=deg+d;
        $("#img").css("transform", "rotate("+deg+"deg)");
    }
</script>
<div style="width: 100%;max-height:90%;height: 90%;overflow-y: auto;overflow-x: auto;border: 1px solid black;text-align: center;">
    <img src="${param.imgUrl}" id="img" style="">
</div>
<div style="text-align: center;">
    <input class="btn_green" onclick="rotateImg(-90);" type="button" value="向左旋转"/>
    <input class="btn_green" onclick="rotateImg(90);" type="button" value="向右旋转"/>
</div>