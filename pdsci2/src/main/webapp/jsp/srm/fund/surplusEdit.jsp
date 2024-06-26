<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        function surplusBalance(projTypeId, fundFlow) {
            var tip = '';
            if(projTypeId == 'jsszyy.ywxm'){
                tip = '院外项目的结转按照10%到K001账号，90%到个人账号的比例结转，';
            }else if(projTypeId == 'jsszyy.kyxm'){
                tip = '院内项目结转按照80%到K001-1账号，20%到个人账号的比例结转，';
            }
            jboxConfirm(tip + "确认结余剩余经费?", function () {
                jboxStartLoading();
                var data = {
                    fundFlow: fundFlow
                };
                var url = "<s:url value='/srm/surplus/surplusBalance'/>";
                jboxPost(url, data, function () {
                    window.location.reload();
                }, null, true);
            });
        }
    </script>
    <style type="text/css">
        a {
            color: #00a0ff;
        }

        a:hover {
            color: #ff6600
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
            <div style="margin-bottom: 10px">
            <table class="xllist">
                <tr>
                    <th style="text-align: left;padding-left: 10px" colspan="5">个人经费信息</th>
                </tr>
                <tr>
                    <th>姓名</th>
                    <th>个人账号</th>
                    <th>个人经费总额</th>
                    <th>已报销金额</th>
                    <th>剩余金额</th>
                </tr>
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.accountNo}</td>
                    <td>${empty personFundInfo.realityAmount ? 0:pdfn:transMultiply(personFundInfo.realityAmount,10000)}（元）</td>
                    <td>${empty personFundInfo.yetPaymentAmount ? 0:pdfn:transMultiply(personFundInfo.yetPaymentAmount,10000)}（元）</td>
                    <td>${empty personFundInfo.realityBalance ? 0:pdfn:transMultiply(personFundInfo.realityBalance,10000)}（元）</td>
                </tr>
            </table>
                </div>
            <div style="overflow: auto">
                <table class="xllist">
                    <thead>
                    <tr>
                        <th style="text-align: left;padding-left: 10px" colspan="7">已完成项目信息</th>
                    </tr>
                    <tr>
                        <th>项目名称</th>
                        <th>项目状态</th>
                        <th>到账经费</th>
                        <th>报销经费</th>
                        <th>剩余经费</th>
                        <th>已结余经费</th>
                        <th>操作剩余经费</th>
                    </tr>
                    </thead>
                    <tbody id="fundTb">
                    <c:forEach var="projExt" items="${pubProjExtList}">
                        <tr>
                            <td>${projExt.projName}</td>
                            <td>${projExt.projStatusName}</td>
                            <td>${pdfn:transMultiply(projExt.projFundInfo.realityAmount,10000)}</td>
                            <td>${pdfn:transMultiply(projExt.projFundInfo.yetPaymentAmount,10000)}</td>
                            <td>${pdfn:transMultiply(projExt.projFundInfo.realityBalance,10000)}</td>
                            <td>${pdfn:transMultiply(projExt.projFundInfo.surplusFund,10000)}</td>
                            <td>
                                <c:if test="${projExt.projFundInfo.realityBalance > 0}">
                                    <a href="javascript:void(0)"
                                       onclick="surplusBalance('${projExt.projTypeId}','${projExt.projFundInfo.fundFlow}')">[结余剩余经费]</a>
                                </c:if>
                                <c:if test="${projExt.projFundInfo.realityBalance == 0}">
                                    没有可结余经费
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty pubProjExtList}">
                        <tr>
                            <td colspan="7">无数据！</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <%--<p style="padding-top: 15px; padding-bottom:10px; font-weight: bold;"></p>--%>
            <div style="height:200px;overflow: auto;margin-top: 10px">
                <table style="width: 100%;" class="xllist">
                    <tr>
                        <th style="text-align: left;padding-left: 10px" colspan="5">操作过程</th>
                    </tr>
                    <tr>
                        <th width="30%">项目名称</th>
                        <th width="15%">结余金额</th>
                        <th width="20%">操作时间</th>
                        <th width="20%">操作内容</th>
                        <th width="15%">操作人</th>
                    </tr>
                    <tbody id="appendTbody">
                    <c:forEach items="${surplusDetailList}" var="detail" varStatus="status">
                        <tr>
                            <td>${detail.reimburseName}</td>
                            <td>${pdfn:transMultiply(detail.money,10000)}</td>
                            <td>
                                    ${pdfn:transDateTime(detail.createTime)}
                            </td>
                            <td>
                                    ${detail.content}
                            </td>
                            <td>
                                    ${detail.fundOperator}
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty surplusDetailList}">
                        <tr>
                            <td colspan="5">无数据！</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <p align="center" style="width:100%;padding-top: 10px;">
                <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
            </p>

        </div>
    </div>
</div>
</body>
</html>