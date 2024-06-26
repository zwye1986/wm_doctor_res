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
        <jsp:param name="jquery_cxselect" value="true"/>
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
    <style>
        .edit3{text-align: center;border:none;}
        .cont_list{
            background:none;
            background-color: #f8f8f8;
            border:1px solid #e3e3e3;
            border-bottom-style: none;
        }
        .cont_list .left .name{
            color: #333;
        }
        [type='text']{width:150px;height: 22px;}
        select{width: 153px;height: 27px;}
        a{color:#4195c5}
    </style>
    <script>
        function scoreCount(titleId){
            var trs = $("#"+titleId+"Td").children();
            var scoreCount = 0;
            $.each(trs, function(i,n){
                var score = $(n).find("input[name='score']").val();
                if (score == null || score == undefined || score == '' || isNaN(score)){
                    score = 0;
                }
                scoreCount += parseFloat(score);
            });
            $("#scoreCount_"+titleId).text(scoreCount);
        }
        $(document).ready(function(){
            <c:forEach items="${titleFormList}" var="title">
            scoreCount("${title.id}");
            </c:forEach>
        });
    </script>
</head>
<body>
    <div>
        <form id="cfgForm" method="post" style="position: relative;margin-top: 10px;width: 1200px;height: 550px;overflow: auto">
            <input type="hidden" name="fromFlow" value="${from.fromFlow}"/>
            <!-- 标题 -->
            <c:forEach items="${titleFormList}" var="title" varStatus="s">
                <div class="cont_list" >
                    <div class="left" style="width: 880px;cursor: auto">
                                <input id="assessOrder_${title.id}" class="bigOrder_${s.index}" type="hidden" value="${title.orderNum}"/>
                        评分项目：
                            <span class="name" style="width: 150px;">
                            <font>${title.name}</font>
                            <input id="assessType_${title.id}" type="text" class="validate[required]" style="display: none;" onblur="saveFromTitle('${title.id}','${s.index-1}');" value="${title.name}"/>
                            </span>
                        评分子项目：
                            <span class="name" style="width: 400px;">
                            <font>${title.typeName}</font>
                            <input id="assessTitle_${title.id}" type="text" class="validate[required]" style="display: none;" onblur="saveFromTitle('${title.id}','${s.index}');" value="${title.typeName}"/>
                            </span>
                        总&#12288;分：<font id="scoreCount_${title.id}"></font>
                    </div>
                </div>
                <div style="padding-top: 0px;">
                    <table class="xllist"  cellpadding="0" cellspacing="0" style="width: 100%">
                        <colgroup>
                            <col width="80%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                        </colgroup>
                        <tr>
                            <th style="text-align: center;">评分要素</th>
                            <th style="text-align: center;">分数</th>
                            <th style="text-align: center;">操作</th>
                        </tr>
                        <!-- item -->
                        <tbody id="${title.id}Td" class="itemTd">
                        <c:forEach items="${title.itemList}" var="item" varStatus="status">
                            <tr>
                                <td>
                                    <input type="hidden" class="littleOrder" name="order" value="${item.order}"/>
                                    <input type="text" class="validate[required] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.name}" value="${item.name}" readonly="readonly"/>
                                </td>
                                <td>
                                    <input type="text" name="score" class="validate[required,custom[xiaoshu1]] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.score}" value="${item.score}" readonly="readonly"/>
                                </td>
                                <td>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </form>
    </div>
</body>
</html>
