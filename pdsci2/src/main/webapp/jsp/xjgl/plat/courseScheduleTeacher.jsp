<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
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
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript">
        $(function(){
            $("#fixedtableheader0").remove();
            combine();
        })

        function search(){
            $("#assumeOrgFlow").val("");
            if($("#assumeOrgName").val()!=""){
                $("#assumeOrgFlow").val($("#assumeOrgName").attr("flow"));
            }
            if($('#termStartTime').val()>$('#termEndTime').val()){
                jboxTip("时段设置有误")
                return;
            }
            $("#recSearchForm").submit();
        }
        function next(){
            jboxStartLoading();
            $("#termStartTime").val("${nextDay}");
            $("#termEndTime").val("${nextDay2}");
            $("#recSearchForm").submit();
            jboxEndLoading();
        }
        function before(){
            $("#termStartTime").val("${beforeDay}");
            $("#termEndTime").val("${beforeDay2}");
            $("#recSearchForm").submit();
        }
        function details(recordFlow,courseMajorId,count){
            var url="<s:url value='/xjgl/teacher/details'/>?recordFlow="+recordFlow+"&courseMajorId="+courseMajorId+"&count="+count;
            jboxOpen(url,"课表信息",450,300);
        }

        function combine(){
            for(var i=$(".weekTd").eq(0).text();true;i++){
                var weekOrders = $(".week"+i);
                var tdCount = weekOrders.length;
                if(tdCount){
                    if(i==""||i==null){
                        weekOrders.filter(':first').attr('colspan', tdCount).text('非该学期时间段');
                        weekOrders.filter(':gt(0)').remove();
                    }else {
                        weekOrders.filter(':first').attr('colspan', tdCount).text('第' + i + '周');
                        weekOrders.filter(':gt(0)').remove();
                    }
                }else {
                    break;
                }
            }
        }

        /**
         *模糊查询加载
         */
        (function($){
            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    searchInput.attr("flow",$(this).attr("flow"));
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        $(function(){
            $("#assumeOrgName").likeSearchInit({
            });
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table style="width:100%;min-width: 1080px;margin: 10px 0px;border: none;">
            <tr>
                <td style="line-height: 260%;">
                    <form id="recSearchForm" method="post" action="<s:url value='/xjgl/teacher/teacherSchedule'/>">
                        年&#12288;&#12288;份：
                        <input type="text" name="courseSession" value="${empty param.courseSession?year:param.courseSession}" class="validate[required]"
                                       onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;margin: 0">
                        &#12288;
                        学&#12288;&#12288;期：
                        <select name="gradeTermId" class="validate[required]" style="width: 141px;">
                            <c:forEach items="${dictTypeEnumTermSeasonList}" var="dict">
                                <option value="${dict.dictId}"
                                    <c:if test="${dict.dictId eq (empty param.gradeTermId?grade:param.gradeTermId)}">selected</c:if>
                                >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        &#12288;
                        开始日期：
                        <input type="text" name="termStartTime" id="termStartTime" value="${empty param.termStartTime?startDate:param.termStartTime}" class="validate[required]"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 137px;">
                        &#12288;
                        结束日期：
                        <input type="text" name="termEndTime" id="termEndTime" value="${empty param.termEndTime?endDate:param.termEndTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 137px;">
                        <br/>班&#12288;&#12288;级：
                        <select style="width:141px;"  name="classId">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumXjClassList}" var="xjclass">
                                <option value="${xjclass.dictId}" <c:if test="${param.classId == xjclass.dictId}">selected="selected"</c:if> >${xjclass.dictName}</option>
                            </c:forEach>
                        </select>
                        &#12288;
                        承担单位：
                        <input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}" style="width: 137px;"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:298px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${orgMap}" var="org">
                                    <p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
                        <%--<select  name="assumeOrgFlow" style="width: 141px;">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${orgMap}" var="org">--%>
                                <%--<option value="${org.key}" <c:if test="${param.assumeOrgFlow==org.key}">selected="selected"</c:if>>${org.value}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        &#12288;
                        授课老师：
                        <input type="text" name="classTeacherName" class="input" value="${param.classTeacherName}" style="width: 137px;">
                        &nbsp;
                        <input type="button"  value="查&#12288;询" class="search" onclick="search()"/>
                    </form>
                </td>
            </tr>
        </table>
        <table class="xllist" id="reslut" width="100%">
            <tr>
                <td>周序</td>
                <c:forEach items="${days}" var="day">
                    <c:set var="weeKey" value="${day}"/>
                    <td class="weekTd week${weekOrderMaps[weeKey]}">${weekOrderMaps[weeKey]}</td>
                </c:forEach>
            </tr>
            <tr>
                <td>日期</td>
                <c:forEach items="${days}" var="day">
                    <td>${day}</td>
                </c:forEach>
            </tr>
            <tr>
                <td>星期</td>
                <c:forEach items="${days}" var="day">
                    <c:set var="dayKey" value="${day}"/>
                    <td>${weekMaps[dayKey]}</td>
                </c:forEach>
            </tr>
            <c:forEach items="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'?classOrderEnumOfGzList:classOrderEnumList}" var="classOrder">
            <tr>
                <td>${classOrder.name}<br/>${classOrder.time}</td>
                <c:forEach items="${days}" var="day">
                    <c:set var="key" value="${day}${classOrder.name}"></c:set>
                        <td>
                        <c:forEach items="${resultMap[key]}" var="reslut">
                        <a onclick="details('${reslut["recordFlow"]}','${reslut["courseMajorId"]}','${reslut["count"]}')" style="cursor: pointer">
                            [${reslut['classCourseName']}]</a><br/>
                        </c:forEach>
                    </td>
                </c:forEach>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <span style="float: right;padding-right: 15px;">[<a style="cursor: pointer;" class="weekNum" onclick="next()">下一页</a>]</span>
        <span style="float: right;padding-right: 15px;">[<a style="cursor: pointer;" class="weekNum" onclick="before()">上一页</a>]</span>
    </div>
</div>
</body>
</html>