 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <style type="text/css">
        .bg{
            width: 60px;
            height: 16px;
            background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
            margin-left: 15px;
        }
        .over{
            height:16px;
            background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
        }
    </style>
    <script>

        $(document).ready(function(){
            var n="签到学员（${fn:length(results)+0}）";
            $("#scanA").html(n);
        });
        function showEval(resultFlow)
        {
            $("."+resultFlow).toggle();
        }
        function exportSiginList(){
            var url = "<s:url value='/jszy/base/activityQuery/exportSiginList'/>?activityFlow=${param.activityFlow}";
            jboxExp(null, url);
            jboxEndLoading();
        }
        function effectiveInfo(resultFlow,isEffective)
        {
            var msg="";
            if(isEffective=="Y")
            {
                msg="确定认可此学员参加的活动信息？";
            }else{
                isEffective="N";
                msg="确定不认可此学员参加的活动信息？";
            }
            jboxConfirm(msg,function(){
                var url = "<s:url value='/jszy/base/activityQuery/effectiveResult'/>?resultFlow="+resultFlow+"&isEffective="+isEffective;
                jboxStartLoading();
                jboxPost(url,null,function(resp){
                    jboxEndLoading();
                    if(resp=="审核成功"){
                        if(isEffective=="Y")
                        {
                            $("#"+resultFlow+"rkname").html("认可");
                            $("#"+resultFlow+"brk").show();
                            $("#"+resultFlow+"rk").hide();
                        }else{
                            $("#"+resultFlow+"rkname").html("不认可");
                            $("#"+resultFlow+"rk").show();
                            $("#"+resultFlow+"brk").hide();
                        }
                    }
                },null,true);
            });
        }

        $("a").click(function(event){
            event.stopPropagation();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic">
        <div>
        <form id="addForm">

            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td width="100%" style="text-align: right;">
                        <input type="button" onclick="exportSiginList();" class="btn_green" value="导出签到学员"/>
                    </td>
                </tr>
            </table>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <c:forEach items="${targets}" var="t" varStatus="s">
                    <c:if test="${s.first}">
                        <tr>
                            <th style="text-align: right;border: 1px solid #e7e7eb;" rowspan="${fn:length(targets)}">指标均分：</th>
                            <td style="text-align: center;border: 1px solid #e7e7eb;">
                                ${t.targetName}
                            </td>
                            <td style="text-align: left;border: 1px solid #e7e7eb;">
                                <div class="bg">
                                    <div class="over" style="width:${12*t.evalScore}px"></div>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!s.first}">
                        <tr>
                            <td style="text-align: center;border: 1px solid #e7e7eb;">
                                    ${t.targetName}
                            </td>
                            <td style="text-align: left;border: 1px solid #e7e7eb;">
                                <div class="bg">
                                    <div class="over" style="width:${12*t.evalScore}px"></div>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td style="text-align: center;width:10%">姓名</td>
                    <td style="text-align: center;width:6%">年级</td>
                    <td style="text-align: center;width:20%">专业</td>
                    <td style="text-align: center;width:18% ">签到时间</td>
                    <td style="text-align: center;width:18% ">签退时间</td>
                    <td style="text-align: center;width:10%">评分</td>
                    <c:if test="${(param.roleFlag eq 'local' or  activity.speakerFlow eq user.userFlow) and activity.isEffective eq 'Y'}">
                        <td style="text-align: center;width:9%">状态</td>
                        <td style="text-align: center;width:9%">操作</td>
                    </c:if>
                </tr>
                <c:forEach items="${results}" var="r" varStatus="s">
                    <tr onclick="showEval('${r.resultFlow}')">
                        <th style="text-align: center;">
                             ${r.userName}
                        </th>
                        <th style="text-align: center;">
                             ${r.sessionNumber}
                        </th>
                        <th style="text-align: center;">
                             ${r.speName}
                        </th>
                        <th style="text-align: center;">
                             ${r.siginTime}
                        </th>
                        <th style="text-align: center;">
                             ${r.siginTime2}
                        </th>
                        <th style="text-align: center;">
                             ${r.evalScore}
                        </th>
                        <c:if test="${(param.roleFlag eq 'local' or  activity.speakerFlow eq user.userFlow) and activity.isEffective eq 'Y'}">
                            <th style="text-align: center;" id="${r.resultFlow}rkname">
                                ${r.isEffective eq 'Y'?'认可':'不认可'}
                            </th>
                            <th style="text-align: center;">
                                <a href="javascript:void(0);" onclick="effectiveInfo('${r.resultFlow}','Y');"id="${r.resultFlow}rk" style="display: ${r.isEffective eq 'Y'?'none':''}" >认可</a>
                                <a href="javascript:void(0);" onclick="effectiveInfo('${r.resultFlow}','N');"id="${r.resultFlow}brk" style="display: ${r.isEffective eq 'N'?'none':''}">不认可</a>
                            </th>
                        </c:if>
                    </tr>
                    <c:forEach items="${targets}" var="t" varStatus="s">
                        <tr class="${r.resultFlow}" style="display: none;">
                            <td></td>
                            <td colspan="2" style="text-align: center;">
                                ${t.targetName}
                            </td>
                            <td colspan="5" style="text-align: left;">
                                <c:set var="key" value="${r.resultFlow}${t.targetFlow}"></c:set>
                                <div class="bg">
                                    <div class="over" style="width:${12*evalDetailMap[key]}px"></div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                <c:if test="${empty results}">
                    <tr>
                        <td colspan="7">暂无学员评分</td>
                    </tr>
                </c:if>
            </table>
        </form>
        </div>
    </div>
</div>
</body>
</html>
