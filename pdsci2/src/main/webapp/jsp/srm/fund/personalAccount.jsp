<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="false"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function () {

        });
        var dg;
        function selectAuthor(fundFlow) {
            dg = dialog({
                id: 'openDialog',
                fixed: true,
                padding: 5,
                title: "添加报销",
                url: "<s:url value='/srm/surplus/addReimburse'/>?fundFlow="+fundFlow,
                width: 600,
                height: 300,
                cancelDisplay: false,
                cancelValue: '关闭',
                backdropOpacity: 0.1,
            });
            dg.showModal();
        }
        function closeEditPage() {
            dg.close().remove();
            window.location.reload();
        }
        function doClose(){
            dg.close().remove();
        }
    </script>
    <style type="text/css">
        a{  color: #00a0ff;  }
        a:hover{color: #ff6600}
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <p style="padding-top: 15px; padding-bottom:10px; font-weight: bold;">个人科研账户管理</p>
            <div>
                <span>姓名：${user.userName}&#12288;&#12288;</span>
                <span>个人账号：${user.accountNo}&#12288;&#12288;</span>
                <span>个人经费总额：${empty personFundInfo.realityAmount ? 0:pdfn:transMultiply(personFundInfo.realityAmount,10000)}（元）&#12288;&#12288;</span>
                <span>已报销金额：${empty personFundInfo.yetPaymentAmount ? 0:pdfn:transMultiply(personFundInfo.yetPaymentAmount,10000)}（元）&#12288;&#12288;</span>
                <span>剩余金额：${empty personFundInfo.realityBalance ? 0:pdfn:transMultiply(personFundInfo.realityBalance,10000)}（元）</span>
            </div>
            <p>
                <c:if test="${sessionScope.projListScope eq 'finance'}">
                <a href="javascript:void(0)" onclick="selectAuthor('${personFundInfo.fundFlow}')" style="float: right;margin-right: 30px;font-size: 16px">[添加报销]</a>
                </c:if>
            </p>
            <div style="height:300px;overflow: auto; width: 100%" >
                <table style="width: 98%;" class="bs_tb">
                    <tr>
                        <th width="20%">报销项目</th>
                        <th width="10%">报销金额(元)</th>
                        <th width="10%">报销方式</th>
                        <th width="15%">报销时间</th>
                        <th width="15%">经办人</th>
                        <th>报销内容</th>
                    </tr>
                    <tbody id="appendTbody">
                        <c:forEach items="${reimburseDetailList}" var="detail" varStatus="status">
                            <tr>
                                <td>${detail.itemName}</td>
                                <td>${pdfn:transMultiply(detail.realmoney,10000)}</td>
                                <td>
                                    <c:forEach var="dict" items="${dictTypeEnumFundRetypeList}">
                                        <c:if test="${detail.fundRetype eq dict.dictId}">${dict.dictName}</c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                        ${pdfn:transDateTime(detail.provideDateTime)}
                                </td>
                                <td>
                                        ${detail.fundOperator}
                                </td>
                                <td>
                                        <textarea class="xltxtarea" style="/*min-height:30px;*/height: 30px;border:0 none" readonly="readonly">${detail.content}</textarea>
                                </td>

                            </tr>
                        </c:forEach>
                    <c:if test="${empty reimburseDetailList}">
                        <tr>
                            <td colspan="6">暂无报销信息</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <c:if test="${sessionScope.projListScope ne 'personal'}">
            <p align="center" style="width:100%;padding-top: 10px;">
                <input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
            </p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>