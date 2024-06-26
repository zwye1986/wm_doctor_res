<%--
  Created by IntelliJ IDEA.
  User: www.0001.Ga
  Date: 2016/9/12
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function(){
            if(${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}){
                if(${sysCfg.cfgValue eq 'N'} || ${empty sysCfg}){
                    $("input[name='beSpeAllScore']").attr("readOnly",true);
                }
            }
            if(${type eq 'CountryOrg'} && ${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}){
                $("input[name='beSpeAllScore']").attr("readOnly",true);
            }
            if(${seeFlag eq 'Y'}){
                $("input[name='beSpeAllScore']").attr("readOnly",true);
            }
            if(${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope} && !${type eq 'CountryOrg'}){
                $("input[name='beSpeAllScore']").attr("readOnly",true);
            }
        });
        function toPage(page) {
            $("#currentPage").val(page);
            var url="<s:url value='/jsres/evaluation/baseAssessInfo'/>?type=${param.type}&currentPage="+page;
            if(${seeFlag eq 'Y'}){
                url="<s:url value='/jsres/evaluation/joinOrgEvaluationInfo'/>?type=${param.type}&currentPage="+page;;
            }
            jboxLoad("hosContent",url,false);
        }
        function baseEvaluationInfo(recordFlow,orgFlow){
            var type = "${type}";
            var url="<s:url value='/jsres/evaluation/showBaseAssessInfo'/>?recordFlow="+recordFlow+"&orgFlow="+orgFlow+"&type="+type+"&currentPage=${page}";
            jboxPostLoad("hosContent",url,false);
        }
        function insertAllScore(orgFlow,exp1){
            var speScore = exp1.value;
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if(speScore>=0 && speScore<=110 && reg.test(speScore)){
                if(reg1.test(speScore)){
                    var speScore1 = parseInt(speScore);
                }else {
                    var speScore1 = speScore;
                }
                var url = "<s:url value='/jsres/evaluation/insertAllScore'/>?orgFlow="+orgFlow+"&speScore="+speScore1;
                jboxPost(url, null, function(resp) {
                    if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
                        top.toPage(1);
                        top.jboxTip(resp);
                        top.search();
                    }else{
                        top.toPage(1);
                        top.jboxTip(resp);
                        top.search();
                    }

                }, null, false);
            }else{
                jboxTip("评分在0-110之间，且只能是正整数或含有一位小数");
                top.toPage(1);
            }
        }
    </script>
</head>
<body>
<div class="main_bd">
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="40%"  style="text-align: center;"/>
                <col width="30%"  style="text-align: center;"/>
                <col width="20%" style="text-align: center;"/>
            </colgroup>
            <tr>
                <th>基地名称</th>
                <th>基地编号</th>
                <th>总得分</br>(2019年)</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${baseEvaluationList }" var="baseEvaluation">
                <tr>
                    <td>${baseEvaluation.orgName}</td>
                    <td>${orgCodeMap[baseEvaluation.orgFlow]}</td>
                    <td>
                        <input type="text" class="input" value="${baseEvaluation.speAllScore}" name="beSpeAllScore" onchange="insertAllScore('${baseEvaluation.orgFlow}',this)" style="width: 100px; text-align: center;"/>
                    </td>
                    <td>
                            <%--<input type="button" class="btn_grey" onclick="baseEvaluationInfo('${baseEvaluation.recordFlow}','${baseEvaluation.orgFlow}');" value="详情" style="width:118px; height:38px;"/>--%>
                        <a class="btn" onclick="baseEvaluationInfo('${baseEvaluation.recordFlow}','${baseEvaluation.orgFlow}');">详情</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty baseEvaluationList}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:if test="${param.flag !=GlobalConstant.FLAG_Y}">
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(baseEvaluationList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </c:if>
</div>
</body>
</html>
