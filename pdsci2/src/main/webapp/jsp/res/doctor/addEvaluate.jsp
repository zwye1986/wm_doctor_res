<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <style type="text/css">
        .wrapper {
            width: 200px;
            height: 30px;
            font: 14px/1.5 arial;
            background: #fff;
            overflow-y: hidden;
            overflow-x: hidden;
            position: absolute;
            top: 11px;
        }

        /*tab*/
        #star {
            overflow: hidden;
        }

        #star li {
            float: left;
            width: 20px;
            height: 20px;
            margin: 2px;
            display: inline;
            color: #999;
            font: bold 18px arial;
            cursor: pointer
        }

        #star .act {
            color: #ffb60b
        }

        #star_word {
            width: 80px;
            height: 30px;
            line-height: 30px;
            border: 1px solid #ccc;
            margin: 10px;
            text-align: center;
            display: none
        }

    </style>
    <script type="text/javascript">
        <%--$(function () {--%>
            <%--var i = $("#result").val();--%>
            <%--$("li").css("color", "#999");--%>
            <%--for (var k = 0; k < i; k++) {--%>
                <%--$("li").eq(k).css("color", "#ffb60b");--%>
            <%--}--%>
        <%--});--%>
        <%--<c:if test="${flag eq 'Y'}">--%>
        <%--//星星评分开始--%>
        <%--window.onload = function () {--%>

            <%--var star = document.getElementById("star");--%>
            <%--var star_li = star.getElementsByTagName("li");--%>
            <%--var star_word = document.getElementById("star_word");--%>
            <%--var result = document.getElementById("result");--%>
            <%--var i = 0;--%>
            <%--var j = 0;--%>
            <%--var len = star_li.length;--%>
            <%--var word = ['没听懂', '同意', '赞成', "棒极了", "好听哭了"]--%>

            <%--for (i = 0; i < len; i++) {--%>
                <%--star_li[i].index = i;--%>
                <%--star_li[i].onmouseover = function () {--%>
                    <%--star_word.style.display = "block";--%>
                    <%--star_word.innerHTML = word[this.index];--%>
                    <%--for (i = 0; i <= this.index; i++) {--%>
                        <%--star_li[i].className = "act";--%>

                    <%--}--%>
                <%--}--%>

                <%--star_li[i].onmouseout = function () {--%>
                    <%--star_word.style.display = "none";--%>
                    <%--for (i = 0; i < len; i++) {--%>
                        <%--star_li[i].className = "";--%>
                    <%--}--%>
                <%--}--%>

                <%--star_li[i].onclick = function () {--%>
<%--//                    result.innerHTML = (this.index+1)+"分";--%>
                    <%--$("#result").val(this.index + 1);--%>
                    <%--$("li").css("color", "#999");--%>
                    <%--for (var k = 0; k < i; k++) {--%>
                        <%--$("li").eq(k).css("color", "#ffb60b");--%>
                    <%--}--%>
                <%--}--%>


            <%--}--%>

        <%--}--%>
        <%--//星星评分结束--%>
        <%--</c:if>--%>
        <%--<c:if test="${flag eq 'N'}">--%>
        <%--$("#result").val(this.index + 1);--%>
        <%--$("li").css("color", "#999");--%>
        <%--</c:if>--%>

        function save() {
            // if ($("#result").val() == "" || $("#result").val() == null) {
            //     jboxTip("还没打星哦!");
            //     return;
            // }
            var sum = 0;
            var evals=[];
            <c:forEach items="${lectureTargetList}" var="t" varStatus="s">
                var param = checks["${t.targetFlow}"]||0;
                if(param == 0) {
                    jboxTip("【${t.targetName}】未打分！");
                    return false;
                }else{
                    var eval={};
                    eval.lectureFlow = "${scanRegist.lectureFlow}";
                    eval.scanRegistFlow = "${scanRegist.recordFlow}";
                    eval.ordinal = "${t.ordinal}";
                    eval.targetFlow = "${t.targetFlow}";
                    eval.targetName = "${t.targetName}";
                    eval.evaScore = param;
                    evals.push(eval);
                    sum += eval.evaScore;
                }
            </c:forEach>
            var data = {};
            data.evals = JSON.stringify(evals);
            data.recordFlow = "${scanRegist.recordFlow}";
            data.avgScore = Math.round(sum/'${lectureTargetList.size()}');
            jboxStartLoading();
            jboxPost("<s:url value='/res/manager/saveLectureEval'/>",data,function(resp){
                jboxEndLoading();
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    top.jboxTip("操作成功！");
                    window.parent.search('history');
                    top.jboxCloseMessager();
                } else {
                    top.jboxTip(resp);
                }
            },null,false);

            <%--jboxConfirm("确认保存？", function () {--%>
                <%--var url = "<s:url value='/res/manager/saveEvaluate'/>";--%>
                <%--var data = $('#addForm').serialize();--%>
                <%--top.jboxPost(url, data, function (resp) {--%>
                    <%--if (resp == '1') {--%>
                        <%--top.jboxTip("操作成功！");--%>
                        <%--window.parent.search('history');--%>
                        <%--top.jboxCloseMessager();--%>
                    <%--} else {--%>
                        <%--top.jboxTip(resp);--%>
                    <%--}--%>
                <%--}, null, false);--%>
            <%--});--%>
        }


        var checks={};
        /*over()是鼠标移过事件的处理方法*/
        function over(param,targetFlow){
            $("." + targetFlow).find("img[class='star']").each(function () {
                $(this).attr("src", "<s:url value='/jsp/res/activity/img/star.png'/>");
            });
            if(param!=null&&param!=""&&param!=undefined) {
                $("." + targetFlow).find("img[class='star']:lt(" + param + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/res/activity/img/star_red.png'/>");//第一颗星星亮起来，下面以此类推
                });
            }
            if(param == 1){
                $("#"+targetFlow+"Message").html("没听懂");//设置提示语，下面以此类推
            }else if(param == 2){
                $("#"+targetFlow+"Message").html("同意");
            }else if(param == 3){
                $("#"+targetFlow+"Message").html("赞成");
            }else if(param == 4){
                $("#"+targetFlow+"Message").html("棒极了");
            }else if(param == 5){
                $("#"+targetFlow+"Message").html("好听哭了");
            }
        }
        /*out 方法是鼠标移除事件的处理方法，当鼠标移出时，恢复到我的打分情况*/
        function out(check,targetFlow){
            $("." + targetFlow).find("img[class='star']").each(function () {
                $(this).attr("src", "<s:url value='/jsp/res/activity/img/star.png'/>");
            });
            $("#"+targetFlow+"Message").html("");
            showClick(targetFlow);
        }
        function showClick(targetFlow)
        {
            var param= checks[targetFlow]||0;
            if(param!=null&&param!=""&&param!=undefined) {
                $("." + targetFlow).find("img[class='star']:lt(" + param + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/res/activity/img/star_red.png'/>");//第一颗星星亮起来，下面以此类推
                });
            }
            var check=( checks[targetFlow]||0)-1;
            if(check!=null&&check!=""&&check!=undefined&&check>=0) {
                $("." + targetFlow).find("img[class='star']:gt(" + check + ")").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/res/activity/img/star.png'/>");
                });
            }else if (check<0)
            {
                $("." + targetFlow).find("img[class='star']").each(function () {
                    $(this).attr("src", "<s:url value='/jsp/res/activity/img/star.png'/>");
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
<form id="addForm">
    <input type="hidden" name="lectureFlow" value="${scanRegist.lectureFlow}"/>
    <input type="hidden" name="recordFlow" value="${scanRegist.recordFlow}"/>
    <table class="grid" style="width:100%; margin-bottom: 10px; ">
        <tr>
            <th style="text-align: center;width: 40%" >指标</th>
            <th style="text-align: center;width: 60%" colspan="2">评分&#12288;&#12288;&#12288;</th>
        </tr>
        <c:forEach items="${lectureTargetList}" var="t" varStatus="s">
            <tr>
                <td style="text-align: center;">
                        ${t.targetName}
                </td>
                <td style="text-align: right;width: 30%">
                    <div class="${t.targetFlow} targetDiv" flow="${t.targetFlow}">
                        <a href="javascript:click(1,'${t.targetFlow}')"><img src="<s:url value='/jsp/res/activity/img/star.png'/>" class="star" id="${t.targetFlow}1" onMouseOver="over(1,'${t.targetFlow}')" onMouseOut="out(0,'${t.targetFlow}')"/></a>
                        <a href="javascript:click(2,'${t.targetFlow}')"><img src="<s:url value='/jsp/res/activity/img/star.png'/>" class="star" id="${t.targetFlow}2" onMouseOver="over(2,'${t.targetFlow}')" onMouseOut="out(1,'${t.targetFlow}')" /></a>
                        <a href="javascript:click(3,'${t.targetFlow}')"><img src="<s:url value='/jsp/res/activity/img/star.png'/>" class="star" id="${t.targetFlow}3" onMouseOver="over(3,'${t.targetFlow}')" onMouseOut="out(2,'${t.targetFlow}')" /></a>
                        <a href="javascript:click(4,'${t.targetFlow}')"><img src="<s:url value='/jsp/res/activity/img/star.png'/>" class="star" id="${t.targetFlow}4" onMouseOver="over(4,'${t.targetFlow}')" onMouseOut="out(3,'${t.targetFlow}')"/></a>
                        <a href="javascript:click(5,'${t.targetFlow}')"><img src="<s:url value='/jsp/res/activity/img/star.png'/>" class="star" id="${t.targetFlow}5" onMouseOver="over(5,'${t.targetFlow}')" onMouseOut="out(4,'${t.targetFlow}')"/></a>
                    </div>
                </td>
                <td style="text-align: left;width: 30%">
                    <span id="${t.targetFlow}Message"></span>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<p style="text-align: center;padding-ntop: 10px">
    <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
    <input type="button" onclick="top.jboxCloseMessager();" class="btn_green" value="关&#12288;闭"/>
</p>
</body>
</html>
