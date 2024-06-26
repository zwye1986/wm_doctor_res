<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>

<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $("#projForm");
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        $(document).ready(function(){
            initUEditer("direction1");
            initUEditer("direction2");
            initUEditer("direction3");
            initUEditer("direction4");
            initUEditer("direction5");
        });
    </script>
</c:if>
<style type="text/css">
    .title_sp {
        padding-left: 10px;
        font-size: 14px;
        margin-bottom: 10px;
        font-weight: bold;
        color: #333;
    }

</style>


<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step4"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <div class="title_sp">五、团队合作目标</div>
    <div>
    <span style="font-size: 16px;font-weight: bold">（一）周期目标</span>
        <p style="line-height: 30px"><span style="font-size: 14px;font-weight: bold">1.总体目标（500字以内）：</span>描述依托科室引进该团队开展5年的合作后，预期达到的目标。</p>
    <p >&#12288;&#12288;&#12288;&#12288;责任人：<input type="text" name="cycleTargetUser" style="width: 100px" class="validate[required] inputText" value="${resultMap.cycleTargetUser}" >
        引进团队带头人：<input type="text" name="cycleLeaderName" style="width: 100px" class="validate[required] inputText" value="${resultMap.cycleLeaderName}" >
        依托科室负责人：<input type="text" name="supportDeptCycle" style="width: 100px" class="validate[required] inputText" value="${resultMap.supportDeptCycle}" >
    </p>
    <table style="margin-top: 8px" width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <td>
                <textarea name="totalTarget" class="xltxtarea validate[required,maxSize[500]]" style="height: 150px;">${resultMap.totalTarget}</textarea>
            </td>
        </tr>
    </table>
    </div>
    <div style="margin-top: 20px">
        <p style="font-size: 14px;font-weight: bold">2.具体目标</p>
        <p>需明确至少3个合作方向。合作方向须聚焦到三级及以上学科或专病。每一方向，须明确在此方向上的医疗、教学、科研三方面的具体目标。同时，为做好技术人才梯队建设，每一方向，需确定引进方1名专家作为技术指导，依托科室1名人员作为骨干人才，至少2名人员作为后备人才，临床科室需同时配备1名专科护理人才。
        </p>
        <table style="margin-top: 8px" width="100%" cellspacing="0" cellpadding="0" class="basic">
            <tr>
                <td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                    <p style="line-height: 30px">方向1：名称（须为三级及以上学科方向或专病方向）</p>
                    <c:choose>
                        <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                            ${resultMap.direction1}
                        </c:when>
                        <c:otherwise>
                            <script id="direction1" name="direction1" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.direction1}</script>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <p style="line-height: 30px">方向2：</p>
                    <c:choose>
                        <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                            ${resultMap.direction2}
                        </c:when>
                        <c:otherwise>
                            <script id="direction2" name="direction2" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.direction2}</script>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <p style="line-height: 30px">方向3：</p>
                    <c:choose>
                        <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                            ${resultMap.direction3}
                        </c:when>
                        <c:otherwise>
                            <script id="direction3" name="direction3" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.direction3}</script>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <p style="line-height: 30px">方向4：</p>
                    <c:choose>
                        <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                            ${resultMap.direction4}
                        </c:when>
                        <c:otherwise>
                            <script id="direction4" name="direction4" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.direction4}</script>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <p style="line-height: 30px">方向5：</p>
                    <c:choose>
                        <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                            ${resultMap.direction5}
                        </c:when>
                        <c:otherwise>
                            <script id="direction5" name="direction5" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.direction5}</script>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </div>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px; width: 100%">
            <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
