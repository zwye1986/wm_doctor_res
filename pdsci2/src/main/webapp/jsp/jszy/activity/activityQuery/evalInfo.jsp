<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_datePicker" value="true"/>
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

        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            var evals=[];
            <c:forEach items="${targets}" var="t" varStatus="s">
            var param= checks["${t.targetFlow}"]||0;
            if(param==0)
            {
                jboxTip("【${t.targetName}】未打分！");
                return false;
            }else{
                var eval={};
                eval.resultFlow="${result.resultFlow}";
                eval.ordinal="${t.ordinal}";
                eval.targetFlow="${t.targetFlow}";
                eval.targetName="${t.targetName}";
                eval.evalScore=param;
                eval.recordStatus="Y";
                evals.push(eval);
            }
            </c:forEach>
            var param={
            };
            param.evals=JSON.stringify(evals);
            param.resultFlow="${result.resultFlow}";

            jboxStartLoading();
            jboxPost("<s:url value='/jszy/base/activityQuery/saveEvalInfo'/>",param,function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.toPage(1);
                    setTimeout(function(){
                        jboxClose();
                    },500);
                }
            },null,true);
        }
    </script>
    <script type="text/javascript">
        var checks={};
        /*over()是鼠标移过事件的处理方法*/
        function over(param,targetFlow){
            $("." + targetFlow).find("img[class='star']").each(function () {
                $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star.png'/>");
            });
            if(param!=null&&param!=""&&param!=undefined) {
                $("." + targetFlow).find("img[class='star']:lt(" + param + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star_red.png'/>");//第一颗星星亮起来，下面以此类推
                });
            }
            if(param == 1){
                $("#"+targetFlow+"Message").html("很差");//设置提示语，下面以此类推
            }else if(param == 2){
                 $("#"+targetFlow+"Message").html("比较差");
            }else if(param == 3){
                 $("#"+targetFlow+"Message").html("一般");
            }else if(param == 4){
                 $("#"+targetFlow+"Message").html("比较好");
            }else if(param == 5){
                 $("#"+targetFlow+"Message").html("很好");
            }
        }
        /*out 方法是鼠标移除事件的处理方法，当鼠标移出时，恢复到我的打分情况*/
        function out(check,targetFlow){
            $("." + targetFlow).find("img[class='star']").each(function () {
                $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star.png'/>");
            });
            $("#"+targetFlow+"Message").html("");
            showClick(targetFlow);
        }
        function showClick(targetFlow)
        {
            var param= checks[targetFlow]||0;
            if(param!=null&&param!=""&&param!=undefined) {
                $("." + targetFlow).find("img[class='star']:lt(" + param + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star_red.png'/>");//第一颗星星亮起来，下面以此类推
                });
            }
            var check=( checks[targetFlow]||0)-1;
            if(check!=null&&check!=""&&check!=undefined&&check>=0) {
                $("." + targetFlow).find("img[class='star']:gt(" + check + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star.png'/>");
                });
            }else if (check<0)
            {
                $("." + targetFlow).find("img[class='star']").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/jsres/activity/img/star.png'/>");
                });
            }
            if(param>0)
            $("#"+targetFlow+"Message").html("("+param+"分)");
        }
        /*click()点击事件处理，记录打分*/
        function click(param,targetFlow){
            checks[targetFlow]=param;
            showClick(targetFlow);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic">
        <div style="min-height: 260px;max-height: 260px;overflow: auto;">
        <form id="addForm">
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td style="text-align: center;width: 40%" >指标</td>
                    <td style="text-align: center;width: 60%" colspan="2">评分&#12288;&#12288;&#12288;</td>
                </tr>
                    <c:forEach items="${targets}" var="t" varStatus="s">
                        <tr>
                            <td style="text-align: center;">
                                ${t.targetName}
                            </td>
                            <td style="text-align: right;width: 30%">
                                <div class="${t.targetFlow} targetDiv" flow="${t.targetFlow}">
                                    <a href="javascript:click(1,'${t.targetFlow}')"><img src="<s:url value='/jsp/jsres/activity/img/star.png'/>" class="star" id="${t.targetFlow}1" onMouseOver="over(1,'${t.targetFlow}')" onMouseOut="out(0,'${t.targetFlow}')"/></a>
                                    <a href="javascript:click(2,'${t.targetFlow}')"><img src="<s:url value='/jsp/jsres/activity/img/star.png'/>" class="star" id="${t.targetFlow}2" onMouseOver="over(2,'${t.targetFlow}')" onMouseOut="out(1,'${t.targetFlow}')" /></a>
                                    <a href="javascript:click(3,'${t.targetFlow}')"><img src="<s:url value='/jsp/jsres/activity/img/star.png'/>" class="star" id="${t.targetFlow}3" onMouseOver="over(3,'${t.targetFlow}')" onMouseOut="out(2,'${t.targetFlow}')" /></a>
                                    <a href="javascript:click(4,'${t.targetFlow}')"><img src="<s:url value='/jsp/jsres/activity/img/star.png'/>" class="star" id="${t.targetFlow}4" onMouseOver="over(4,'${t.targetFlow}')" onMouseOut="out(3,'${t.targetFlow}')"/></a>
                                    <a href="javascript:click(5,'${t.targetFlow}')"><img src="<s:url value='/jsp/jsres/activity/img/star.png'/>" class="star" id="${t.targetFlow}5" onMouseOver="over(5,'${t.targetFlow}')" onMouseOut="out(4,'${t.targetFlow}')"/></a>
                                </div>
                            </td>
                            <td style="text-align: left;width: 30%">
                                <span id="${t.targetFlow}Message"></span>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </form>
        </div>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
