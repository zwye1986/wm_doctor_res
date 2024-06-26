<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .wrapper{width:300px; margin:10px auto; font:14px/1.5 arial;}
        /*tab*/
        #star{overflow:hidden;}
        #star li{float:left; width:20px; height:20px; margin:2px; display:inline; color:#999; font:bold 18px arial; cursor:pointer}
        #star .act{color: #ffb60b }
        #star_word{width:80px; height:30px; line-height:30px; border:1px solid #ccc; margin:10px; text-align:center; display:none}

    </style>
    <script type="text/javascript">
        $(function(){
            var i = $("#result").val();
            $("li").css("color","#999");
            for(var k=0;k<i;k++) {
                $("li").eq(k).css("color", "#ffb60b");
            }
        })
        <c:if test="${flag eq 'Y'}">
        //星星评分开始
        window.onload = function(){

            var star = document.getElementById("star");
            var star_li = star.getElementsByTagName("li");
            var star_word = document.getElementById("star_word");
            var result = document.getElementById("result");
            var i=0;
            var j=0;
            var len = star_li.length;
            var word = ['没听懂','同意','赞成',"棒极了","好听哭了"]

            for(i=0; i<len; i++){
                star_li[i].index = i;
                star_li[i].onmouseover = function(){
                    star_word.style.display = "block";
                    star_word.innerHTML = word[this.index];
                    for(i=0; i<=this.index; i++){
                        star_li[i].className = "act";

                    }
                }

                star_li[i].onmouseout = function(){
                    star_word.style.display = "none";
                    for(i=0; i<len; i++){
                        star_li[i].className = "";
                    }
                }

                star_li[i].onclick = function(){
//                    result.innerHTML = (this.index+1)+"分";
                    $("#result").val(this.index+1);
                    $("li").css("color","#999");
                    for(var k=0;k<i;k++) {
                        $("li").eq(k).css("color", "#ffb60b");
                    }
                }


            }

        }
        //星星评分结束
        </c:if>
        <c:if test="${flag eq 'N'}">
        $("#result").val(this.index+1);
        $("li").css("color","#999");
        for(var k=0;k<i;k++) {
            $("li").eq(k).css("color", "#ffb60b");
        }
        </c:if>
        function save(){
            if($("#result").val()==""||$("#result").val()==null){
                jboxTip("还没打星哦!");
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/lecture4qingpu/saveEvaluate'/>";
                var data = $('#addForm').serialize();
                top.jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.document.mainIframe.$("#history").click();
                        top.jboxCloseMessager();
                    }else{
                        top.jboxTip("操作失败");
                    }
                },null,false);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm">
            <input type="hidden" name="lectureFlow" value="${lectureFlow}"/>
            <input type="hidden" name="recordFlow" value="${resLectureEvaDetail.recordFlow}"/>
            <table class="basic" style="width:100%; margin-bottom: 5px; margin-top:5px;">
                <tr>
                    <th style="width: 35px">评分</th>
                    <td >
                        <div class="wrapper" style="margin-left: 5px;">
                            <input name="evaScore" type="hidden" id="result" value="${resLectureEvaDetail.evaScore}"/>
                            <ul id="star">
                                <li>★</li>
                                <li>★</li>
                                <li>★</li>
                                <li>★</li>
                                <li>★</li>
                            </ul>
                        </div>
                        <div id="star_word"  style=" position:absolute;z-index:999;left:300px;top:8px">赞成</div>
                    </td>
                </tr>
                <tr>
                    <th>评语</th>
                    <td style="height: 45px">
                        <c:if test="${flag eq 'Y'}">
                            <textarea name="evaContent" style="min-width: 96%;min-height: 110px;margin: 5px;">${resLectureEvaDetail.evaContent}</textarea>
                        </c:if>
                        <c:if test="${flag eq 'N'}">
                            <label style="min-width: 96%;min-height: 110px;margin: 5px;">${resLectureEvaDetail.evaContent}</label>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;padding-ntop: 10px">
            <c:if test="${flag eq 'Y'}">
                <input type="button" onclick="save();" class="search" value="保&#12288;存"/>
            </c:if>
            <input type="button" onclick="top.jboxCloseMessager();" class="search" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
