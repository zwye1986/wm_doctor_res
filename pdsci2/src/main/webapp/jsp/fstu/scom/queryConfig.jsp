<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        .content div{margin-top:5px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <table class="basic" style="width: 100%;">
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学分类别：</th>
                    <td colspan="3">
                        <select name="scoreTypeId" style="width:140px;" disabled="disabled" class="xlselect">
                            <option/>
                            <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.scoreTypeId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>项目大类：</th>
                    <td colspan="3">
                        <select name="projTypeId" style="width:140px;" disabled="disabled" class="xlselect">
                            <option/>
                            <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                    <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test="${scope.dictId eq scoreCfg.projTypeId and dict.dictId eq scoreCfg.scoreTypeId}">selected</c:if>>${scope.dictName}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>评分项：</th>
                    <td colspan="3">
                        <select name="scoreItemId" style="width:140px;" disabled="disabled" class="xlselect">
                            <option/>
                            <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.scoreItemId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>完成情况：</th>
                    <td colspan="3">
                        <select name="executionId" style="width:140px;" class="validate[required] xlselect" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.executionId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;" ><span style="color:red;">*</span>分值：</th>
                    <td colspan="3">
                        <input type="text"  style="width:132px;" value="${scoreCfg.score}" readonly="readonly" class="xltext">
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>最大分值：</th>
                    <td colspan="3">
                        <input type="text" style="width:132px;" value="${scoreCfg.maxScore}" readonly="readonly" class="xltext">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">是否科研部审核：</th>
                    <td colspan="3">
                        <input type="radio" name="miurAuditFlag" value="N" <c:if test="${scoreCfg.miurAuditFlag eq 'N'}">checked="checked"</c:if> disabled="disabled">否&#12288;&#12288;
                        <input type="radio" name="miurAuditFlag" value="Y" <c:if test="${scoreCfg.miurAuditFlag eq 'Y'}">checked="checked"</c:if> disabled="disabled">是
                    </td>
                    <th style="width: 20%;">是否关联：</th>
                    <td colspan="3">
                        <input type="radio" name="reltiveType" value="thesis" <c:if test="${scoreCfg.reltiveType eq 'thesis'}">checked="checked"</c:if>disabled="disabled">论文
                        <input type="radio" name="reltiveType" value="book" <c:if test="${scoreCfg.reltiveType eq 'book'}">checked="checked"</c:if>disabled="disabled">著作
                        <input type="radio" name="reltiveType" value="award" <c:if test="${scoreCfg.reltiveType eq 'award'}">checked="checked"</c:if>disabled="disabled">奖项
                        <input type="radio" name="reltiveType" value="patent" <c:if test="${scoreCfg.reltiveType eq 'patent'}">checked="checked"</c:if>disabled="disabled">科研项目
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">备注：</th>
                    <td colspan="8">
                        <textarea type="text" name="remark" style="width:98%;height:80px;" readonly="readonly">${scoreCfg.remark}</textarea>
                    </td>
                </tr>
            </table>

        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
       </div>
    </div>
</div>
</body>
</html>