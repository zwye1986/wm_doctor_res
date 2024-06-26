<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function doBack() {
            window.location.href = "<s:url value='/srm/expert/proj/list'/>";
        }
        function saveScore(status) {
            if (false == $("#itemForm").validationEngine("validate")) {
                return;
            }
            var gradeFlag = $("#gradeFlag").val();
            if ("${GlobalConstant.FLAG_N}" == gradeFlag) {
                jboxInfo("输入的分数不能小于0且不能超过设置的评分值！");
                return false;
            }
            var url = "<s:url value='/srm/expert/proj/saveScore?status='/>" + status;
            jboxStartLoading();
            jboxPost(url, $('#itemForm').serialize(), function () {
                window.location.reload(true);
            }, null, true);
        }
        function sumScore(obj) {
            var grade = $(obj).val();
            var gradeVal = parseInt($(obj).parent().prev().prev().prev().prev().text().trim());
            if (grade < 0 || grade > gradeVal) {
                $("#gradeFlag").val("${GlobalConstant.FLAG_N}");
                jboxInfo("输入的分数不能小于0且不能超过设置的评分值！");
                $(obj).val("");
                return false;
            } else {
                $("#gradeFlag").val("${GlobalConstant.FLAG_Y}");
            }
            var scoreSum = 0;
            $(".item").each(function () {
                if (!isNaN(parseFloat($(this).val()))) {
                    var scoreWeight = parseFloat($(this).parent().prev().text().trim());
                    if(isNaN(scoreWeight)){
                        scoreWeight = 1;
                    }
                    scoreSum += (parseFloat($(this).val()) * scoreWeight);
                }
            });
            $(".secScore").each(function(){
                if(!isNaN(parseFloat($(this).val()))){
                    scoreSum += parseFloat($(this).val());
                }
            });
            $("#scoreTotal").val(parseFloat(scoreSum.toFixed(3)));
        }
        function secondForm(itemFlow,formId){
            var url = "<s:url value='/srm/expert/proj/secondForm?expertProjFlow=${param.expertProjFlow}'/>&formId="+formId+"&itemFlow="+itemFlow;
            jboxOpen(url, "评分", 960, 600, true);
        }
    </script>
</head>
<body>
<input type="hidden" id="gradeFlag" name="gradeFlag"/>
<div class="mainright">
    <b class="click" id="closeleft" onclick="closed('closeleft')"></b>
    <form id="itemForm">
        <div class="content">
            <p style=" color:#c00; line-height:25px; background:url('<s:url
                    value='/css/skin/${skinPath}/images/tb.png'/>') no-repeat 20px -11px; padding:18px 0 6px 40px;">
                评分细则</p>
            <table class="xllist">
                <thead>
                <tr>
                    <th width="16%">评分内容</th>
                    <th width="7%">评分值</th>
                    <th width="20%">评分标准</th>
                    <th width="40%">评分要点</th>
                    <th width="7%">评分权重</th>
                    <th width="10%">评分</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${gradeItem }" var="item">
                    <tr>
                        <td style="vertical-align:middle;">${item.itemName }</td>
                        <td style="vertical-align:middle;">${item.itemScore}</td>
                        <td class="xlming">${item.itemScoreTip }</td>
                        <td style="text-align:left;">
                            <c:forEach items="${fn:split(item.itemDesc,' ')}" var="desc">${desc}<br/></c:forEach>
                        </td>
                        <td>${item.scoreWeight}</td>
                        <td style="vertical-align:middle;">
                            <c:if test="${item.itemType ne '2'}">
                                <input class="item validate[required,custom[number]]" type="text" name="${item.itemFlow }"
                                       onblur="sumScore(this);" value="${scoreItemMap[item.itemFlow] }" style="width:50px;text-align:center;"/>
                            </c:if>
                            <c:if test="${item.itemType eq '2'}">
                                <input type="hidden" class="secScore" value="${scoreItemMap[item.itemFlow]}">
                                <a href="javascript:secondForm('${item.itemFlow}','${item.secondLevelForm}');" id="itemFlow${item.itemFlow}" style="color:${!empty scoreItemMap[item.itemFlow]?'blue':''}">
                                    <c:if test="${empty scoreItemMap[item.itemFlow]}">二级评分表单</c:if>
                                    <c:if test="${!empty scoreItemMap[item.itemFlow]}">${scoreItemMap[item.itemFlow]}</c:if>
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>专家意见</td>
                    <td colspan="4" class="xlming">
                        <c:forEach items="${dictTypeEnumExpertScoreResultList}" var="dict">
                            <input type="radio" name="scoreResultId" id="${dict.dictId}"
                                   value="${dict.dictId}" class="validate[required]"
                                   <c:if test="${dict.dictId eq expertProj.scoreResultId}">checked</c:if>/><label
                                for="${dict.dictId}">${dict.dictName}</label>&#12288;
                        </c:forEach>

                        <textarea style="width:80%" class="validate[maxSize[300]] xltxtarea"
                                  name="expertOpinion">${expertProj.expertOpinion}</textarea></td>
                    <td style="color:red">总分：<input type="text" name="scoreTotal" id="scoreTotal"
                                                    class="validate[required,custom[number]]"
                                                    value="${expertProj.scoreTotal}" style="width: 50px"/>
                        <input type="hidden" name="expertProjFlow" value="${expertProj.expertProjFlow}">
                        <input type="hidden" name="schemeFlow" value="${schemeFlow}">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
    <div align="center">
        <input type="button" value="返回" class="dingdan-d" onclick="doBack();"/>
        <c:set var="isView" value="true" />
        <c:if test='${evaluationStatusEnumSave.id eq expertProj.evalStatusId || empty expertProj.evalStatusId}'>
            <c:set var="isView" value="false" />
            <input type="button" value="保存" class="dingdan-d" onclick="saveScore('${evaluationStatusEnumSave.id}');"/>
            <input type="button" value="提交" class="dingdan-d" onclick="saveScore('${evaluationStatusEnumSubmit.id}');"/>
        </c:if>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var isView = "${isView}";
        if(isView =="true"){
            $('input').attr("readonly", "readonly");
            $('textarea').attr("readonly", "readonly");
            $("select").attr("disabled", "disabled");
            $(":checkbox").attr("disabled", "disabled");
            $(":radio").attr("disabled", "disabled");
        }
    });
</script>

</html>
