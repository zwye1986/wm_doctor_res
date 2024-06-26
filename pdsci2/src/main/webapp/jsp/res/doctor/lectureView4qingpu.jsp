<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
<script type="text/javascript">
    $(function(){
        $("#init").click();
    });
    function selTag(tag,inx){
        $(tag).siblings("li").removeClass('selectTag');
        $(tag).addClass('selectTag');
        var url = '<s:url value="/res/lecture4qingpu/"/>'+inx;
        jboxLoad("tagContent",url,true);
    }
</script>
<style>
    /*#tags li {*/
        /*margin-top: 5px;*/
    /*}*/
    /*#tags li a.selTag{ background-position: right top; color: #000; line-height: 28px; height:28px;}*/

    /*.selTag{background-color:#3385ff;color: white}*/
    /*.tag{width:90px;height: 30px;border: 1px darkgrey solid;float: left;margin-right: 8px;*/
        /*font-size: medium;text-align: center; line-height:30px;-moz-border-radius: 5px;*/
        /*-webkit-border-radius: 5px;*/
        /*border-radius:5px;  }*/
</style>
</head>
<body>
<div class="mainright" align="center" style="height: 100%;background-color: white;">
    <div  class="content">
        <div class="title1 clearfix" align="left">
            <div style="float: left;width: 100%">
                <ul id="tags">
                    <li onclick="selTag(this,'getNewLectures');">
                        <a id="init" style="cursor: pointer;">最新讲座</a>
                    </li>
                    <li onclick="selTag(this,'getHistoryLectures');">
                        <a id="history" style="cursor: pointer;">历史讲座</a>
                    </li>
                </ul>
                <div id="tagContent">
                </div>
            </div>
        </div>

        <%--<div class="queryDiv">
            <div class="inputDiv">
                <label class="qlable">起始时间：</label>
                <input  type="text" class="qtime" name="lectureStartTime" value="${param.lectureStartTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm'});"/>
                ~
                <input  type="text" class="qtime" name="lectureEndTime" value="${param.lectureEndTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm'});"/>
            </div>
            <div class="inputDiv">
                <label class="qlable">讲座日期：</label>
                <input  type="text" class="qtext" name="lectureTrainDate" value="${param.lectureTrainDate}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
            </div>
            <div class="inputDiv">
                <label class="qlable">讲座地点：</label>
                <input  type="text" class="qtext" name="lectureTrainPlace" value="${param.lectureTrainPlace}" />
            </div>
            <input type="button" class="searchInput" value="查&#12288;询" onclick="selTag">
        </div>--%>
    </div>
</div>
</body>
</html>