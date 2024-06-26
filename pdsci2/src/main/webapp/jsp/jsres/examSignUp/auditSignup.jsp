<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <link rel="stylesheet" href="<s:url value="/jsp/jsres/css/detail.css"/>"/>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
        .grid.lz_table tbody th {padding:0}
        .sh_ul{
            margin: 36px 0;
        }
        .sh_ul li{
            height: 36px;
            line-height: 36px;
        }
        .sh_ul li .icon-blue{
            margin-right: 24px;
            color: #686868;
        }
        .sh_ul li>span{
            margin-right: 42px;
        }
        .icon-blue{
            width: 16px;
            height: 16px;
            border-radius: 100%;
            border: 1px solid #b4b4b4;
            background-color: lightgreen;
            box-shadow: inset 0 0 0 3px #f4f4f4;
            position: relative;
            top: 9px;
        }
    </style>
    <script type="text/javascript">
        function doclose() {
            top.jboxClose();
        }

        $(function () {
            var datas = [];//所有的分配的授课老师账号

            var arry = {"id": "无", "text": "无"};
            datas.push(arry);
            var arry = {"id": "可考试，公共课合格后发证", "text": "可考试，公共课合格后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，提供医师执业证书后发证", "text": "可考试，提供医师执业证书后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，提供医师执业证书，公共课合格后发证", "text": "可考试，提供医师执业证书，公共课合格后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，补轮转后发证", "text": "可考试，补轮转后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，变更执业范围后发证", "text": "可考试，变更执业范围后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，出科表上传完整后发证", "text": "可考试，出科表上传完整后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，出科表上传完整，提供医师执业证书后发证", "text": "可考试，出科表上传完整，提供医师执业证书后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，出科表上传完整，公共课合格后发证", "text": "可考试，出科表上传完整，公共课合格后发证"};
            datas.push(arry);
            var arry = {"id": "可考试，出科表上传完整，提供医师执业证书,公共课合格后发证", "text": "可考试，出科表上传完整，提供医师执业证书,公共课合格后发证"};
            datas.push(arry);
//	$.itemSelect("passReason",datas,null,null,null);
            var datas = [];
            var arry = {"id": "不通过，APP填写不符合要求或出科表上传不符合要求", "text": "不通过，APP填写不符合要求或出科表上传不符合要求"};
            datas.push(arry);
            var arry = {"id": "不通过，超出执业范围", "text": "不通过，超出执业范围"};
            datas.push(arry);
            var arry = {"id": "不通过，培训时间不足", "text": "不通过，培训时间不足"};
            datas.push(arry);
            var arry = {"id": "不通过，无材料或缺某一个材料", "text": "不通过，无材料或缺某一个材料"};
            datas.push(arry);
            var arry = {
                "id": "不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格",
                "text": "不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格"
            };
            datas.push(arry);
            var arry = {"id": "不通过，培训手册填写不符合要求", "text": "不通过，培训手册填写不符合要求"};
            datas.push(arry);
//	$.itemSelect("unpassReason",datas,null,null,null);
        });

        function  applyAudit(roleFlag) {
            var reason="";
            var reason2="";
            var id=$("input[name='auditStatusId']:checked").val();
            if(id==""||id==undefined) {
                jboxTip("请选择审核结果！！");
                return false;
            }
            if(id=="${jsResAuditStatusEnumPassed.id}") {
                reason=$("#passReason1").val();
                reason2=$("#passReason2").val();
            }
            if(id=="${jsResAuditStatusEnumNotPassed.id}") {
                reason=$("#unpassReason1").val();
                reason2=$("#unpassReason2").val();
            }
            if(!reason&&id=="${jsResAuditStatusEnumNotPassed.id}") {
                jboxTip("请选择原因！！");
                return false;
            }
            if(reason=="不通过，无材料或缺某一个材料"&&!reason2) {
                jboxTip("请输入具体原因！！");
                return false;
            }
            $("#auditReason").val(reason+" "+reason2);
            jboxConfirm("确认审核？" , function(){
                jboxStartLoading();
                jboxPost("<s:url value='/jsres/examSignUp/localSaveAudit'/>?roleFlag=" + roleFlag,
                    $("#myform").serialize(),
                    function(resp){
                        top.jboxEndLoading();
                        endloadIng();
                        jboxTip(resp);
                        if(resp=="审核成功"){
                            var currentPage=window.parent.$("#currentPage").val();
                            if(!currentPage)
                            {
                                currentPage=1;
                            }
                            var length=(window.parent.$("#dataTable tbody").find("tr").length||0)-1;
                            if(length<=0&&currentPage>1)
                            {
                                currentPage=currentPage-1;
                            }
                            window.parent.toPage(currentPage);
                            top.jboxClose();
                        }

                    },null,false);
            });
        }
        function endloadIng() {
            var openDialog = dialog.get('artLoading');
            if(openDialog !=null && openDialog.open){
                openDialog.close().remove();
            }
            openDialog = dialog.get('loadingDialog');
            if(openDialog !=null && openDialog.open){
                openDialog.close().remove();
            }
            var jboxMainIframeLoading = $("#jboxMainIframeLoading");
            if(jboxMainIframeLoading!=null){
                jboxMainIframeLoading.fadeOut(500,function(){
                    $(jboxMainIframeLoading).remove();
                });
            }
        }

        function check() {
            var id = $("input[name='auditStatusId']:checked").val();
            if (id == "${jsResAuditStatusEnumPassed.id}") {
                $("#passReason1").removeAttr("readonly");
                $("#passReason2").removeAttr("readonly");
                $("#unpassReason1").removeAttr("name");
                $("#unpassReason1").attr("readonly", "readonly");
                $("#unpassReason1").removeClass("validate[required]");
                $("#unpassReason1").val("");
                $("#unpassReason2").removeAttr("name");
                $("#unpassReason2").attr("readonly", "readonly");
                $("#unpassReason2").val("");
            }
            if (id == "${jsResAuditStatusEnumNotPassed.id}") {
                $("#passReason1").attr("readonly", "readonly");
                $("#passReason1").removeAttr("name");
                $("#passReason1").val("");
                $("#passReason2").attr("readonly", "readonly");
                $("#passReason2").removeAttr("name");
                $("#passReason2").val("");
                $("#unpassReason1").removeAttr("readonly");
                $("#unpassReason1").addClass("validate[required]");
                $("#unpassReason2").removeAttr("readonly");
            }
        }
    </script>
</head>
<body>
<div class="mainright" style="max-height: 600px;overflow: auto;">
    <div class="search_table" >
        <div class="main_bd" >
            <h4 >基本信息</h4>
        <form id="addForm" style="position: relative;">

            <input hidden name="signupFlow" value="${param.signupFlow}"/>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;">姓&#12288;&#12288;名：</th>
                    <td>
                        ${user.userName}
                    </td>
                    <th style="text-align: right;width: 88px;">申请年份：</th>
                    <td>
                        ${signup.signupYear}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">证件类型：</th>
                    <td>
                        ${user.cretTypeName}
                    </td>
                    <th style="text-align: right;width: 88px;">证件号码：</th>
                    <td>
                        ${user.idNo}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训年级：</th>
                    <td>
                        <input hidden name="sessionNumber" value="${doctor.sessionNumber}"/>
                        ${signup.sessionNumber}
                    </td>
                    <th style="text-align: right;width: 88px;">培养年限：</th>
                    <td>
                        <c:if test="${'OneYear' eq signup.trainingYears}">一年</c:if>
                        <c:if test="${'TwoYear' eq signup.trainingYears}">两年</c:if>
                        <c:if test="${'ThreeYear' eq signup.trainingYears}">三年</c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训基地：</th>
                    <td>
                        ${signup.orgName}
                    </td>
                    <th style="text-align: right;width: 88px;">培训类别：</th>
                    <td>
                        ${signup.trainingTypeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训专业：</th>
                    <td>
                        ${signup.trainingSpeName}
                    </td>
                    <th style="text-align: right;width: 88px;">报考专业：</th>
                    <td>
                        ${signup.changeSpeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">补考类型：</th>
                    <td>
                        <c:if test="${signup.signupTypeId eq 'Theory'}">理论补考</c:if>
                        <c:if test="${signup.signupTypeId eq 'Skill'}">技能补考</c:if>
                    </td>
                    <th style="text-align: right;width: 88px;">提交时间：</th>
                    <td >${pdfn:transDateTime(signup.createTime)}</td>
                </tr>
            </table>
        </form>
        </div>
        <div class="main_bd" style="margin-top: 30px">
            <h4 >补考科目</h4>
            <span style="padding-top: 40px">
                <c:if test="${signup.signupTypeId eq 'Skill'}">技能考试</c:if>
                <c:if test="${signup.signupTypeId eq 'Theory'}">理论考试</c:if>
            </span>
        </div>
        <div class="main_bd" style="margin-top: 30px">
            <h4>考试记录</h4>
            <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;" class="base_info">
                <colgroup>
                    <col width="20%"/>
                    <col width="40%"/>
                    <col width="40%"/>
                </colgroup>
                <c:if test="${ empty skillList &&  empty theoryList}">
                        <span style="padding-top: 40px">
                            无考试记录！
                        </span>
                </c:if>
                <c:if test="${not empty skillList}">
                    <c:forEach items="${skillList}" var="skill">
                        <c:set value="N" var="flag"></c:set>
                        <tr>
                            <th>${skill.testId}</th>
                            <td>技能考试：
                                <c:if test="${skill.skillScore eq '1'}">合格</c:if>
                                <c:if test="${skill.skillScore eq '0'}">不合格</c:if>
                            </td>
                            <td>理论考试：
                                <c:if test="${empty theoryList}">缺考</c:if>
                                <c:if test="${not empty theoryList}">
                                    <c:forEach items="${theoryList}" var="theory">
                                        <c:if test="${skill.testId eq theory.testId}">
                                            <c:set value="Y" var="flag"></c:set>
                                            <c:if test="${theory.theoryScore eq '1'}">合格</c:if>
                                            <c:if test="${theory.theoryScore eq '0'}">不合格</c:if>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag ne 'Y'}">
                                        缺考
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty theoryList}">
                        <c:forEach items="${theoryList}" var="theory">
                            <c:set value="N" var="flagNew"></c:set>
                            <c:forEach items="${skillList}" var="skill">
                                <c:if test="${skill.testId eq theory.testId}">
                                    <c:set value="Y" var="flagNew"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flagNew ne 'Y'}">
                            <tr>
                                <th>${theory.testId}</th>
                                <td>技能考试：
                                    缺考
                                </td>
                                <td>
                                    理论考试：
                                    <c:if test="${theory.theoryScore eq '1'}">合格</c:if>
                                    <c:if test="${theory.theoryScore eq '0'}">不合格</c:if>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${empty skillList && not empty theoryList}">
                    <c:forEach items="${theoryList}" var="theory">
                        <tr>
                            <th>${theory.testId}</th>
                            <td>技能考试：
                                缺考
                            </td>
                            <td>理论考试：
                                <c:if test="${theory.theoryScore eq '1'}">合格</c:if>
                                <c:if test="${theory.theoryScore eq '0'}">不合格</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

            </table>
            <span style="padding-top: 40px">
                <c:if test="${signup.signupTypeId eq 'Skill'}">技能考试</c:if>
                <c:if test="${signup.signupTypeId eq 'Theory'}">理论考试</c:if>
            </span>
        </div>
        <div class="main_bd" style="margin-top: 30px">
            <h4>审核意见</h4>
            <form id="myform">
                <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;border: none;"class="base_info" >
                    <colgroup>
                        <col width="25%"/>
                        <col width="25%"/>
                    </colgroup>
                    <tbody>
                    <tr style=" width:100%;text-align: center;">
                        <td style="text-align: left;border:none;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumPassed.id}" onclick="check();">通过 </th>
                        <td style="text-align: left;;border:none;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumNotPassed.id}" onclick="check();">不通过</td>
                    </tr>
                    <tr style=" width:100%;text-align: center;">
                        <td style="border:none;text-align: left;padding-left: 30px;">
                            <%--<div style="min-width: 250px;float: left;border:1px">--%>
                            <%--<input name="auditReason" id="passReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
                            <%--</div>--%>
                            <select id="passReason1" class="select" style="width: 250px;;margin-bottom: 12px;">
                                <option value=""></option>
                                <option value="无">无</option>
                                <option value="可考试，公共课合格后发证">可考试，公共课合格后发证</option>
                                <option value="可考试，提供医师执业证书后发证">可考试，提供医师执业证书后发证</option>
                                <option value="可考试，提供医师执业证书，公共课合格后发证">可考试，提供医师执业证书，公共课合格后发证</option>
                                <option value="可考试，补轮转后发证">可考试，补轮转后发证</option>
                                <option value="可考试，变更执业范围后发证">可考试，变更执业范围后发证</option>
                                <option value="可考试，出科表上传完整后发证">可考试，出科表上传完整后发证</option>
                                <option value="可考试，出科表上传完整，提供医师执业证书后发证">可考试，出科表上传完整，提供医师执业证书后发证</option>
                                <option value="可考试，出科表上传完整，公共课合格后发证">可考试，出科表上传完整，公共课合格后发证</option>
                                <option value="可考试，出科表上传完整，提供医师执业证书,公共课合格后发证">可考试，出科表上传完整，提供医师执业证书,公共课合格后发证</option>
                                <option value="其他">其他</option>
                            </select>
                            <textarea id="passReason2" ></textarea>
                        </td>
                        <td style="border:none;text-align: left;padding-left: 30px;" >
                            <%--<div style="min-width: 250px;float: left;border:1px">--%>
                            <%--<input name="auditReason" id="unpassReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
                            <%--</div>--%>
                            <select id="unpassReason1" class="select" style="width: 250px;;margin-bottom: 12px;">
                                <option value=""></option>
                                <option value="不通过，APP填写不符合要求或出科表上传不符合要求">不通过，APP填写不符合要求或出科表上传不符合要求</option>
                                <option value="不通过，超出执业范围">不通过，超出执业范围</option>
                                <option value="不通过，培训时间不足">不通过，培训时间不足</option>
                                <option value="不通过，无材料或缺某一个材料">不通过，无材料或缺某一个材料</option>
                                <option value="不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格">不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格</option>
                                <option value="不通过，培训手册填写不符合要求">不通过，培训手册填写不符合要求</option>
                                <option value="其他">其他</option>
                            </select>
                            <textarea id="unpassReason2"  ></textarea>
                        </td>
                    </tr>
                    <input name="auditReason" id="auditReason" value="" hidden>
                    <input name="signupFlow" value="${param.signupFlow}" hidden>
                    </tbody>
                </table>
            </form>
            <c:set var="k" value="${signup.testId}_make_up"/>
            <c:if test="${not  empty logs}">
                <ul class="sh_ul">
                    <c:forEach items="${logs}" var="log">
                        <c:choose>
                            <c:when test="${sysCfgMap[k] eq 'Y'}">
                                <li class="flex">
                                    <div class="icon-blue"></div>
                                    <span>${log.auditTime}</span>
                                    <span>${log.auditRoleName}</span>
                                    <span>${log.auditStatusName}</span>
                                    <span>${log.auditReason}</span>
                                </li>
                            </c:when>
                            <c:when test="${sysCfgMap[k] eq 'N' or empty sysCfgMap[k]}">
                                <c:if test="${(roleFlag ne 'global' and log.auditRoleId ne 'global') or roleFlag eq 'global'}">
                                    <li class="flex">
                                        <div class="icon-blue"></div>
                                        <span>${log.auditTime}</span>
                                        <span>${log.auditRoleName}</span>
                                        <span>${log.auditStatusName}</span>
                                        <span>${log.auditReason}</span>
                                    </li>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </ul>
            </c:if>
           <%-- <c:if test="${not  empty logs}">
                <div style="height: 200px;overflow: auto;">
                    <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info">
                        <colgroup>
                            <col width="15%"/>
                            <col width="15%"/>
                            <col width="15%"/>
                            <col width="55%"/>
                        </colgroup>
                        <tr>
                            <th style="text-align: center;">审核角色</th>
                            <th style="text-align: center;">审核时间</th>
                            <th style="text-align: center;">审核结果</th>
                            <th style="text-align: center;">审核意见</th>
                        </tr>
                        <c:forEach items="${logs}" var="log">
                            <tr>
                                <td style="text-align: center;">${log.auditRoleName}</td>
                                <td style="text-align: center;">${log.auditTime}</td>
                                <td style="text-align: center;">${log.auditStatusName}</td>
                                <td style="text-align: center;">${log.auditReason}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty logs}">
                            <tr>
                                <td colspan="4">暂无审核意见</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </c:if>--%>
            <div class="main_bd" id="div_table_1" >
                <div align="center" style="margin-top: 20px; margin-bottom:20px;">
                    <c:if test="${param.flag eq 'Y'}">
                        <input type="button" id="" class="btn_green" onclick="applyAudit('${roleFlag}');" value="提交"/>&nbsp;
                    </c:if>
                    <input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
