<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function back(){
            history.back();
        }
        function selectTag(showContent, selfObj) {
            if(false==$("#projAidForm").validationEngine("validate")){
                return false;
            }
            // 操作标签
            var tag = document.getElementById("tags").getElementsByTagName("li");
            var taglength = tag.length;
            for (var i = 0; i < taglength; i++) {
                tag[i].className = "";
            }
            $('#'+selfObj).addClass("selectTag");
            // 操作内容
            for (var i = 0; j = document.getElementById("tagContent" + i); i++) {
                j.style.display = "none";
            }
            document.getElementById(showContent).style.display = "block";
            $("#flag").val("flag");
        }

        function showDelayDate(projStatus){
            if(projStatus == "延期"){
                $("#delayDate").show();
            }else{
                $("#delayDate").hide();
            }
        }
        $(function(){
            $('#main input[type!=button]').attr('readonly', "readonly");
            $('#main .ctime').attr('disabled', "disabled");
            $('#main textarea').attr('readonly', "readonly");
            $('#main select').attr('disabled', "disabled");
            $("#main .ctime").removeAttr("onclick");
            showDelayDate("${resultMap.projStatus}");
        });
        <c:if test="${(auditFlag eq 'Y') && (empty fundInfo.fundIncomeTime) && (aidProjStatusEnumPass.id eq pubProj.projStatusId)}">
            $(function(){
                    jboxStartLoading();
                    jboxOpen("<s:url value='/srm/regis/proj/addRealityAmount?projFlow=${pubProj.projFlow}'/>", "到账经费", 350, 250);
            })
        </c:if>
    </script>

</head>
<body>
<div id="main" >
    <div class="mainright">
        <div class="content">
            <ul id="tags">
                <li class="selectTag" id="tag0"><a onclick="selectTag('tagContent0','tag0')" href="javascript:void(0)">项目基本情况</a></li>
                <li id="tag1"><a onclick="selectTag('tagContent1','tag1')" href="javascript:void(0)">项目经费</a></li>
                <li id="tag2"><a onclick="selectTag('tagContent2','tag2')" href="javascript:void(0)">附件信息</a></li>
            </ul>

            <input type="hidden" id="projFlow" name="projFlow" value="${resultMap.projFlow }">
            <input type="hidden" name="pageName" value="step1">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table width="100%" class="bs_tb">
                        <tr>
                            <th class="theader" colspan="4" style="text-align: left;padding-left: 20px">项目信息</th>
                        </tr>
                        <tbody>
                        <tr>
                            <td style="text-align: right;"><span style="color: red;">*</span>项目名称：</td>
                            <td colspan="3">
                                <input type="text" class="validate[required] inputText" name="projName"
                                       value="${resultMap.projName}" style="width:90%;text-align: left;"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="15%" style="text-align: right;">序号：</td>
                            <td width="35%">
                                <input type="text" class="inputText" name="projSerialNum" value="${resultMap.projSerialNum}" style="width:90%;text-align: left;"/>
                            </td>
                            <td style="text-align: right;">项目级别：</td>
                            <td>
                                <select name="projRank" class="inputText" style="width:90%;text-align: left;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyProjRankList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${resultMap.projRank eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">项目来源：</td>
                            <td>
                                <select name="projDeclarerFlow" class="inputText"
                                        style="width:90%;text-align: left;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyProjSourceList}" var="dict" >
                                        <option value="${dict.dictId}"
                                                <c:if test="${resultMap.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: right;">项目类别：</td>
                            <td>
                                <select name="projSubTypeId" class="inputText" style="width:90%;text-align: left;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyProjTypeList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${resultMap.projSubTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">工号：</td>
                            <td>
                                <input type="text" class="inputText" name="jobNumber"
                                       value="${resultMap.jobNumber}" style="width:90%;text-align: left;"/>
                            </td>
                            <td style="text-align: right;">项目负责人：</td>
                            <td>
                                <input type="text" class="inputText" name="projLeader" value="${resultMap.projLeader}" style="width:90%;text-align: left;"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">承担科室：</td>
                            <td>
                                <select class="inputText" name="applyDeptFlow" style="width:90%;text-align: left;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${deptList}" var="dept">
                                        <option value="${dept.deptFlow}"
                                                <c:if test="${(empty resultMap.applyDeptFlow) and (sessionScope.currUser.deptFlow eq dept.deptFlow)}">selected="selected"</c:if>
                                                <c:if test="${resultMap.applyDeptFlow eq dept.deptFlow}">selected="selected"</c:if>
                                        >${dept.deptName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: right;">所在支部：</td>
                            <td>
                                <select name="branchId" class="inputText" style="width:90%;text-align: left;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumWxeyBranchList}">
                                    <option value="${dict.dictId}"
                                            <c:if test='${resultMap.branchId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">项目年度：</td>
                            <td style="text-align: left;">
                                <input type="text" name="projYear"
                                       value="${resultMap.projYear}"
                                       readonly="readonly" class="inputText ctime"
                                       onClick="WdatePicker({dateFmt:'yyyy'})" style="margin-left: 20px;">
                            </td>

                            <td style="text-align: right;">起始日期：</td>
                            <td style="text-align: left;">
                                <input type="text"  name="projStartTime" value="${resultMap.projStartTime }" readonly="readonly" class="inputText ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-left: 20px;">
                                ~<input type="text" name="projEndTime" value="${resultMap.projEndTime}" readonly="readonly" class="inputText ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-left: 20px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <table width="100%" class="bs_tb" style="margin-top: 20px;">
                        <tr>
                            <th class="theader" style="text-align: left;padding-left: 20px">项目简介</th>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="projIntr" placeholder="此处填写项目简介" rows="10" cols="20" class="xltxtarea">${resultMap.projIntr }</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;padding-left: 20px;">备注</th>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="projRemark" placeholder="这里填写备注信息" rows="10" cols="20" class="xltxtarea">${resultMap.projRemark }</textarea>
                            </td>
                        </tr>
                    </table>
                </div>



                <div class="tagContent" id="tagContent1">
                    <div id="fund">
                        <table width="100%" class="bs_tb" >
                            <tr>
                                <th class="theader" colspan="7">项目经费:<input type="text"
                                                                                  class="validate[custom[number]] inputText"
                                                                                  style="width: 50px" name="fund_amount"
                                                                                  value="${resultMap.fund_amount}"> 万=
                                    <input type="text" class="validate[custom[number]] inputText" name="fund_xiabo"
                                           style="width: 50px" value="${resultMap.fund_xiabo}">万（拨款金额）+
                                    <input type="text" class="validate[custom[number]] inputText" name="fund_peitao"
                                           style="width: 50px" value="${resultMap.fund_peitao}">万（配套金额）</th>
                            </tr>
                            <tr>
                                <td width="5%">序号</td>
                                <td width="30%">费用类别</td>
                                <td width="10%">金  额</td>
                                <td width="10%">最高比例</td>
                                <td width="40%">备  注</td>
                            </tr>
                            <tbody id="fundsTb">
                            <c:forEach items="${resultMap.fundList}" var="fundList" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                   <td><input type="text" class="validate[required] inputText" style="width:90%;" name="fund_type" value="${fundList.objMap.fundList_type}"></td>
                                    <td><input type="text" class="validate[custom[number]] inputText" style="width:90%;" name="fund_money" value="${fundList.objMap.fundList_money}"></td>
                                    <td><input type="text" class="validate[custom[number]] inputText" style="width:90%;" name="fund_scale" value="${fundList.objMap.fundList_scale}"></td>
                                    <td><input type="text" class="inputText" style="width:90%;" name="fund_remark" value="${fundList.objMap.fundList_remark}"></td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty resultMap.fundList}">
                                <tr>
                                    <td colspan="7">无记录</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="tagContent" id="tagContent2">
                    <c:set var="file1"/>
                    <c:forEach items="${resultMap.fileEdit}" var="fe">
                        <c:if test="${fe.objMap.fileEdit_fileRemark eq '1'}">
                            <c:set var="file1" value="${fe.objMap.fileEdit_file}"/>
                        </c:if>
                    </c:forEach>
                    <table class="bs_tb" style="width: 100%">
                        <tr>
                            <th colspan="5" class="theader">附件
                            </th>
                        </tr>
                        <tr>
                            <td width="5%" style="font-weight:bold;padding: 0px;">序号</td>
                            <td width="80%" style="font-weight:bold;padding: 0px;">附件名称</td>
                            <td>有（√）</td>
                        </tr>
                        <tr>
                            <td style="text-align: center;" class="fileListSerial">1</td>
                            <td>
                                <c:if test="${not empty file1}">
                                    <a href='<s:url value="/pub/file/down?fileFlow=${file1}"/>'>立项文件</a>
                                    <input type="hidden" name="fileEdit_fileRemark" value="1"/>
                                    <input type="hidden" name="fileEdit_fileName" value="立项文件"/>
                                </c:if>
                                <c:if test="${empty file1}">立项文件</c:if>
                            </td>
                            <td>
                                <c:if test="${not empty file1}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/>
                                </c:if>
                            </td>
                        </tr>

                        <c:set var="count" value="1"/>
                        <c:forEach items="${resultMap.fileEdit}" var="fe">
                            <c:if test="${fe.objMap.fileEdit_fileRemark =='other'}">
                                <tr>
                                    <td class="fileListSerial">${count+1}<c:set var="count" value="${count+1}"/></td>
                                    <td>
                                        <a href='<s:url value="/pub/file/down?fileFlow=${fe.objMap.fileEdit_file}"/>'>${fe.objMap.fileEdit_fileName}</a>
                                        <input type="hidden" name="fileEdit_fileRemark" value="${fe.objMap.fileEdit_fileRemark}"/>
                                        <input type="hidden" name="fileEdit_fileName" value="${fe.objMap.fileEdit_fileName}"/>
                                        <input type="hidden" name="fileEdit_file" value="${fe.objMap.fileEdit_file}"/>
                                    </td>
                                    <td><img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>