<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th,.grid th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td,.grid td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript">
    function showTable(obj) {
        var oDiv = document.getElementById(obj);
        var tDiv = document.getElementById(obj+"_");

        var aDisplay = oDiv.style.display;
        if ("none" == aDisplay) {
            oDiv.style.display = "";
            tDiv.style.display = "";

            var imgDivUp = document.getElementById(obj+"up");
            var imgDivDown = document.getElementById(obj+"down");
            imgDivUp.style.display = "";
            imgDivDown.style.display = "none";
        } else {
            oDiv.style.display = "none";
            tDiv.style.display = "none";
            var imgDivUp = document.getElementById(obj+"up");
            var imgDivDown = document.getElementById(obj+"down");
            imgDivUp.style.display = "none";
            imgDivDown.style.display = "";
        }
    }
</script>
<div
        <c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;" </c:if>
        <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;" </c:if>>
    <form id='BaseInfoForm' style="position:relative;">

        <div class="div_table">
            <h4 style="border-left: 4px solid #54b2e5">培训情况</h4>
            <c:forEach items="${resultList}" var="t" varStatus="status">
                <c:set var="trainingForm" value="${t.trainingForm}"/>
                <c:set var="fileMap" value="${t.fileMap}"/>
                <c:set var="files" value="${t.files}"/>
                <h4 style="height: 30px;line-height: 30px;margin-left: 20px">${t.standardDeptName}</h4>

                <img id="${status.index}down" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('${status.index}');"  title="展开"
                     style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
                <img id="${status.index}up" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('${status.index}');"  title="收缩"
                     style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>

                <div class="main_bd" id="${status.index}" style="display: none" >
                    <div class="div_table">
                        <h4>培训对象医疗工作量</h4>
                        <table cellspacing="0" cellpadding="0" class="base_info">
                            <colgroup>
                                <col width="20%"/>
                                <col width="30%"/>
                                <col width="20%"/>
                                <col width="30%"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th>轮转管床数：</th>
                                    <td>${trainingForm.lzcws}张</td>
                                    <th>日门诊量：</th>
                                    <td>${trainingForm.rmzl}人次</td>
                                </tr>
                                <tr>
                                    <th>日急诊量：</th>
                                    <td>${trainingForm.rjzl}人次</td>
                                    <th>轮转必选科室手写系统病历数：</th>
                                    <td>${trainingForm.lzbx}份/科</td>
                                </tr>
                                <tr>
                                    <th>近三年招收人数	：</th>
                                    <td>${trainingForm.zrs}人</td>
                                    <th>当前在培人数：</th>
                                    <td>${trainingForm.zprs}人</td>
                                </tr>
                                <tr>
                                    <th>住院医师规范化培训登记手册：</th>
                                    <td>
                                        <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_Y }">有</c:if>
                                        <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_N }">无</c:if>
                                    </td>
                                    <th>住院医师规范化培训考核手册：</th>
                                    <td>
                                        <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_Y }">有</c:if>
                                        <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_N }">无</c:if>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="main_bd" id="${status.index}_" style="display: none" >
                    <div class="div_table" style="margin-bottom: 40px">
                        <h4>科室各种培训活动记录（可另附表）</h4>
                        <span>近三年入科教育、轮转计划表、教学查房、疑难死亡病例讨论、小讲课、出科考核。</span>
                        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
                            <colgroup>
                                <col width="10%"/>
                                <col width="45%"/>
                                <col width="30%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th style="background-color: #f4f5f9">序号</th>
                                <th style="background-color: #f4f5f9">培训活动记录名称</th>
                                <th style="background-color: #f4f5f9">附件名称</th>
                                <th style="background-color: #f4f5f9">上传附件</th>
                            </tr>
                            <tbody id="workTb">
                            <c:if test="${empty trainingForm.trainingActivityForms}">
                                <tr>
                                    <td colspan="4">暂无数据</td>
                                </tr>
                            </c:if>
                            <c:forEach var="s" items="${trainingForm.trainingActivityForms}" varStatus="status">
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${s.activityName }</td>
                                    <td>
                                        <c:set var="file" value="${fileMap[s.appendix]}"></c:set>
                                        <c:if test="${empty file}">
                                            暂无
                                        </c:if>
                                        <c:if test="${not empty file}">
                                            <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                               target="_blank">${file.fileName}</a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:set var="file" value="${fileMap[s.appendix]}"></c:set>
                                        <c:if test="${empty file}">
                                            未上传
                                        </c:if>
                                        <c:if test="${not empty file}">
                                            已传
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
</div>


