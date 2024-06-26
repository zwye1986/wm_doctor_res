<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">

        function back() {
            history.back();
        }
        function selectTag(showContent, selfObj) {
            if (false == $("#projRegisForm").validationEngine("validate")) {
                return false;
            }

            if(showContent != 'tagContent1' && $("#fundListTb").children().length <1){
                jboxTip("至少填一条项目经费！")
                return false;
            }
            // 操作标签
            var tag = document.getElementById("tags").getElementsByTagName("li");
            var taglength = tag.length;
            for (var i = 0; i < taglength; i++) {
                tag[i].className = "";
            }
            $('#' + selfObj).addClass("selectTag");
            // 操作内容
            for (var i = 0; j = document.getElementById("tagContent" + i); i++) {
                j.style.display = "none";
            }
            document.getElementById(showContent).style.display = "block";
            $("#flag").val("flag");
        }

        function add(tb) {
            $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());

            var length = $("#" + tb + "Tb").children().length;
            //序号
            $("#" + tb + "Tb").children("tr").last().children("td").eq(1).text(length);
        }
        <c:if test="${empty resultMap.fundList}">
            $(function(){
               add("fundList");
            });
        </c:if>
        function delTr(tb) {
            //alert("input[name="+tb+"Ids]:checked");
            var checkboxs = $("input[name='" + tb + "Ids']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    /*if($(n).next().val()){
                        deleteDetail($(n).next().val());
                        alert($(n).next().val());
                    }*/
                  //  deleteDetail($(n).parent('td').parent("tr").find("input[name='fundList_flow']").val());
                    $(n).parent('td').parent("tr").remove();
                });
                //删除后序号
                var serial = 0;
                $("." + tb + "Serial").each(function () {
                    serial++;
                    $(this).text(serial);
                });
            });
        }
        /*function deleteDetail(fundDetailFlow) {
                var requestData = {"fundDetailFlow": fundDetailFlow};
                var url = "<s:url value='/srm/payment/deleteDetail' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
//                        window.parent.frames['mainIframe'].window.search();
                        //window.location.reload();
                    }
                }, null, true);
        }*/
        function delFile(obj) {
            jboxConfirm("确认删除?", function () {
                $(obj).parent().parent().remove();
                //删除后序号
                var serial = 0;
                $(".fileListSerial").each(function () {
                    serial++;
                    $(this).text(serial);
                });
            });

        }
        function delFile1(obj) {
            jboxConfirm("确认删除?", function () {
                var $td = $(obj).parent();
                $td.html(null);
                $td.prev().html(null);
                var fileInput = "<input type='file' name='fileEdit_file'/>";
                $td.append(fileInput);
            });
        }

        function saveAudit(agreeFlag) {

            var form = $("#projRegisForm");
            if (false == form.validationEngine("validate")) {
                return false;
            }
            var tip = agreeFlag == "${GlobalConstant.FLAG_Y}" ? "审核通过" : "退回";
            var score = $("#scoreFlow").val();
            if(!score && agreeFlag == 'Y'){
                jboxTip("请选择积分项！");
                return false;
            }
            jboxConfirm("确认" + tip + "?", function () {

                jboxSubmit($('#projRegisForm'),"<s:url value="/srm/regis/proj/saveStepForKy/audit"/>?agreeFlag=" + agreeFlag ,function(resp){
                    window.parent.frames['mainIframe'].location.reload(true);
                    jboxClose();
                } , null , true);
            });
        }

        function nextOpt(step) {
            var projSubTypeName = $("select[name=projSubTypeId] :selected").text();
            $("#projSubTypeName").val(projSubTypeName);
            if (projSubTypeName == "请选择") {
                $("#projSubTypeName").val("");
            }
            var form = $("#projRegisForm");
            if (false == form.validationEngine("validate")) {
                return false;
            }
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            jboxSubmit( $('#projRegisForm'),"<s:url value="/srm/regis/proj/saveStepForKy/apply"/>" , function(resp){
                window.location.href="<s:url value='/srm/regis/proj/list'/>";
            } , null , true);
            /*$(form).attr("action", "<s:url value="/srm/regis/proj/saveStepForKy/apply"/>");  ///    pdsci2/srm/regis/proj/saveStepForKy
            jboxStartLoading();

            form.submit();*/
        }

        function getDeptName(obj) {
            $("#deptName").val($(obj).find("option:selected").text());
        }
        function initDept() {
            var datas=[];
            <c:forEach items="${deptList}" var="dept">
            var d={};
            d.id="${dept.deptFlow}";
            d.text="${dept.deptName}";
            datas.push(d);
            </c:forEach>

            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });
        function fundAmount(){
            var fund_xiabo = $("#fund").find("input[name='fund_xiabo']").val().trim();
            var fund_peitao = $("#fund").find("input[name='fund_peitao']").val().trim();
            if(!fund_xiabo.trim()){
                fund_xiabo=0;
            }
            if(!fund_peitao.trim()){
                fund_peitao=0;
            }
            var amount = parseFloat(fund_xiabo)+parseFloat(fund_peitao);
            $("#fund_amount").val(parseFloat(amount.toFixed(4)));
            var trs = $("#fundListTb").children();
            $.each(trs,function(i,n){
                calculator($(n).find("input[name='fundList_money']"));
            });
        }
        $(function(){scaleCalculator();})
        function scaleCalculator(){
            var trs = $("#fundListTb").children();
            var scales= 0;
            var moneys = 0;
            $.each(trs,function(i,n){
                var scale = $(n).find("input[name='fundList_scale']").val();
                var money = $(n).find("input[name='fundList_money']").val().trim();
                if(!scale){
                    scale=0;
                }
                if(!parseFloat(money)){
                    money=0;
                }
                scales +=parseFloat(scale);
                moneys +=parseFloat(money);
            });
//            if(scales>100){
                $("#point").html("* 当前金额：<span id='point' style='color: red;'>"+parseFloat(moneys.toFixed(4))+" （万）</span>"+",当前比例：<span id='point' style='color: red;'>"+parseFloat(scales.toFixed(2))+"%</span>")
 //           }
        }

        function calculator(obj){
            var money = $(obj).val().trim();
            if(!money){
                money = 0;
            }
            var scale = parseFloat(money)/parseFloat($("#fund_amount").val())*100;
            if(!scale){
                scale=0;
            }
            $(obj).parent().next().children().val(parseFloat(scale.toFixed(2)));
            scaleCalculator();
        }

        function getScoreName(obj) {
            $(obj).next().val($(obj).find("option:selected").text());
        }
    </script>
<style type="text/css">
    #trainDept-suggest{
        text-align: left;
    }
</style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <ul id="tags">
                <li class="selectTag" id="tag0"><a onclick="selectTag('tagContent0','tag0')" href="javascript:void(0)">项目基本情况</a>
                </li>
                <li id="tag1"><a onclick="selectTag('tagContent1','tag1')" href="javascript:void(0)">项目经费</a></li>
                <li id="tag2"><a onclick="selectTag('tagContent2','tag2')" href="javascript:void(0)">附件上传</a></li>
            </ul>
            <form method="post" id="projRegisForm" enctype="multipart/form-data">
                <input type="hidden" id="pageName" name="pageName" value="step1">
                <input type="hidden" id="projFlow" name="projFlow" value="${pubProj.projFlow}"/>
                <%--<input type="hidden" name="projYear" value="${pdfn:getCurrYear()}"/>--%>
                <input type="hidden" id="projSubTypeName" name="projSubTypeName"/>

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
                                           value="${resultMap.projName}" style="float:left;width:90%;text-align: left;margin-left: 20px"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="15%" style="text-align: right;"><span style="color: red;">*</span>项目编号：</td>
                                <td width="35%">
                                    <input type="text" class="validate[required] inputText" name="projSerialNum"
                                           value="${resultMap.projSerialNum}" style="width:90%;text-align: left;"/>
                                </td>
                                <td style="text-align: right;"><span style="color: red;">*</span>项目级别：</td>
                                <td>
                                    <select name="projRank" class="validate[required] inputText" style="width:90%;text-align: left;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyProjRankList}" var="dict">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${resultMap.projRank eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td style="text-align: right;"><span style="color: red;">*</span>项目来源：</td>
                                <td>
                                    <select name="projDeclarerFlow" class="validate[required] inputText"
                                            style="width:90%;text-align: left;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyProjSourceList}" var="dict" >
                                            <option value="${dict.dictId}"
                                                    <c:if test="${resultMap.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: right;"><span style="color: red;">*</span>项目类别：</td>
                                <td>
                                    <select name="projSubTypeId" class="validate[required] inputText" style="width:90%;text-align: left;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyProjTypeList}" var="dict">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${resultMap.projSubTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td style="text-align: right;"><span style="color: red;">*</span>工号：</td>
                                <td>
                                    <input type="text" class="validate[required] inputText" name="jobNumber"
                                           value="${resultMap.jobNumber}" style="width:90%;text-align: left;"/>
                                </td>
                                <td style="text-align: right;"><span style="color: red;">*</span>项目负责人：</td>
                                <td>
                                    <input type="text" class="validate[required] inputText" name="projLeader"
                                           value="${empty resultMap.projLeader?sessionScope.currUser.userName:resultMap.projLeader}"
                                           style="width:90%;text-align: left;"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;"><span style="color: red;">*</span>承担科室：</td>
                                <td>
                                    <%--<select class="validate[required] inputText" name="applyDeptFlow" style="width:90%;text-align: left;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${deptList}" var="dept">
                                            <option value="${dept.deptFlow}"
                                                    <c:if test="${(empty resultMap.applyDeptFlow) and (sessionScope.currUser.deptFlow eq dept.deptFlow)}">selected="selected"</c:if>
                                                    <c:if test="${resultMap.applyDeptFlow eq dept.deptFlow}">selected="selected"</c:if>
                                            >${dept.deptName}
                                            </option>
                                        </c:forEach>
                                    </select>--%>

                                    <input id="trainDept" class="validate[required] inputText" style="width:90%;text-align: left;" name="applyDeptName" type="text"
                                           value="<c:if test="${empty pubProj.applyDeptName}">${sessionScope.currUser.deptName}</c:if>${pubProj.applyDeptName}" autocomplete="off"/>
                                    <input id="deptFlow" name="applyDeptFlow" class="input" value="<c:if test="${empty pubProj.applyDeptFlow}">${sessionScope.currUser.deptFlow}</c:if>${pubProj.applyDeptFlow}" type="text"
                                           hidden style="margin-left: 0px;"/>
                                </td>
                                <td style="text-align: right;"><span style="color: red;">*</span>所在支部：</td>
                                <td>

                                    <%--<input type="text" class="inputText" name="branch" value="${resultMap.branch}" style="width:90%;text-align: left;"/>--%>
                                    <select name="branchId" class="validate[required] inputText" style="width:90%;text-align: left;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumWxeyBranchList}">
                                        <option value="${dict.dictId}"
                                                <c:if test='${resultMap.branchId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;"><span style="color: red;">*</span>项目年度：</td>
                                <td style="text-align: left;">
                                    <input type="text" name="projYear"
                                           value="${resultMap.projYear}"
                                           readonly="readonly" class="validate[required] inputText ctime"
                                           onClick="WdatePicker({dateFmt:'yyyy'})" style="margin-left: 20px;text-align: left;">
                                </td>
                                <td style="text-align: right;"><span style="color: red;">*</span>起始日期：</td>
                                <td style="text-align: left;">
                                    <input type="text" name="projStartTime"
                                           value="${empty resultMap.projStartTime?pdfn:getCurrDate():resultMap.projStartTime}"
                                           readonly="readonly" class="validate[required] inputText ctime"
                                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-left: 20px;text-align: left;">~
                                    <input type="text" name="projEndTime"
                                           value="${empty resultMap.projEndTime?pdfn:getCurrDate():resultMap.projEndTime}"
                                           readonly="readonly" class="validate[required] inputText ctime"
                                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-left: 20px;text-align: left;">
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <table width="100%" class="bs_tb" style="margin-top: 20px;">
                            <tr>
                                <th class="theader" style="text-align: left;padding-left: 20px"><span style="color: red;">*</span>项目简介</th>
                            </tr>
                            <tr>
                                <td>
                                    <textarea name="projIntr" placeholder="此处填写项目简介" rows="10" cols="20"
                                              class="validate[required] xltxtarea">${resultMap.projIntr }</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: left;padding-left: 20px;"><span style="color: red;">*</span>备注</th>
                            </tr>
                            <tr>
                                <td>
                                    <textarea name="projRemark" placeholder="这里填写备注信息" rows="10" cols="20"
                                              class="validate[required] xltxtarea">${resultMap.projRemark }</textarea>
                                </td>
                            </tr>
                        </table>
                        <c:if test="${editFlag != 'audit'}">
                            <div class="button" style="width: 100%; text-align: center;margin-top: 20px;">
                                <input type="button" onclick="selectTag('tagContent1','tag1')" class="search"
                                       value="下一步"/>
                            </div>
                        </c:if>
                    </div>

                    <div class="tagContent" id="tagContent1">
                        <div id="fund">
                            <table width="100%" class="bs_tb">
                                <tr>
                                    <th class="theader" colspan="17"> <span style="color: red;">*</span>项目经费：<input type="text" readonly="readonly"
                                                                                  class="inputText" style="width: 50px" id="fund_amount" name="fund_amount"
                                                                                  value="${resultMap.fund_amount}"> 万=
                                        <input type="text" class="validate[required,custom[number]] inputText" name="fund_xiabo" onchange="fundAmount()"
                                               style="width: 50px" value="${resultMap.fund_xiabo}">万（拨款金额）+
                                        <input type="text" class="validate[required,custom[number]] inputText" name="fund_peitao" onchange="fundAmount()"
                                               style="width: 50px" value="${resultMap.fund_peitao}">万（配套金额）
                                        <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
										<span style="float: right;padding-right: 10px">
										<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                             style="cursor: pointer;" onclick="add('fundList')"/>&#12288;
										<img title="删除" style="cursor: pointer;"
                                             src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                             onclick="delTr('fundList');"/></span>
                                        </c:if>
                                    </th>
                                </tr>
                                <tr>
                                    <td width="5%"></td>
                                    <td width="5%">序号</td>
                                    <td width="30%"><span style="color: red;">*</span>费用类别</td>
                                    <td width="10%"><span style="color: red;">*</span>金 额</td>
                                    <td width="10%"><span style="color: red;">*</span>最高比例</td>
                                    <td width="40%"><span style="color: red;">*</span>备 注</td>
                                </tr>
                                <tbody id="fundListTb">
                                <c:forEach items="${resultMap.fundList}" var="fundList" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;"><input name="fundListIds" type="checkbox"/>
                                            <%--<input type="hidden" name="fundList_flow" value="${fundList.objMap.fundList_flow}"/>--%>
                                        </td>
                                        <td style="text-align: center;" class="fundListSerial">${status.count}</td>
                                        <td><input type="text" class="validate[required] inputText" style="width:90%;"
                                                   name="fundList_type" value="${fundList.objMap.fundList_type}"></td>
                                        <td><input type="text" class="validate[required,custom[number]] inputText"
                                                   style="width:90%;" name="fundList_money"
                                                   value="${fundList.objMap.fundList_money}" onchange="calculator(this)"></td>
                                        <td><input type="text" class="validate[required,custom[number]] inputText"
                                                   style="width:90%;" name="fundList_scale" readonly="readonly"
                                                   value="${fundList.objMap.fundList_scale}"></td>
                                        <td><input type="text" class="validate[required] inputText" style="width:90%;" name="fundList_remark"
                                                   value="${fundList.objMap.fundList_remark}"></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <p id="point"></p>
                        </div>
                        <c:if test="${editFlag != 'audit'}">
                            <div class="button" style="width: 100%;text-align: center;margin-top: 20px;">
                                <input type="button" onclick="selectTag('tagContent0','tag0')" class="search"
                                       value="上一步"/>
                                <input type="button" onclick="selectTag('tagContent2','tag2')" class="search"
                                       value="下一步"/>
                            </div>
                        </c:if>
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
                                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                					<span style="float: right;padding-right: 10px">
									<a href="javascript:void(0)"><img title="新增"
                                                                      src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                                                      style="cursor: pointer;"
                                                                      onclick="add('fileList')"></img></a>&#12288;
									<a href="javascript:void(0)"><img title="删除" style="cursor: pointer;"
                                                                      src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                                      onclick="delTr('fileList')"></img></a>
									</span>
                                    </c:if>
                                </th>
                            </tr>
                            <tr>
                                <td width="3%"></td>
                                <td width="5%" style="font-weight:bold;padding: 0px;">序号</td>
                                <td width="50%" style="font-weight:bold;padding: 0px;">附件名称</td>
                                <td width="15%">有（√）</td>
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <td width="30%">操作</td>
                                </c:if>
                            </tr>
                            <tbody id="fileListTb">
                            <tr>
                                <td width="3%"></td>
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
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <td style="padding-left: 20px;">
                                        <c:if test="${not empty file1}">
                                            [<a href="javascript:void(0)" onclick="delFile1(this)">删除</a>]
                                            <input type="hidden" name="fileEdit_file" value="${file1}"/>
                                        </c:if>
                                        <c:if test="${empty file1}">
                                            <input type="file" name="fileEdit_file"/>
                                            <input type="hidden" name="fileEdit_fileRemark" value="1"/>
                                            <input type="hidden" name="fileEdit_fileName" value="立项文件"/>
                                        </c:if>
                                    </td>
                                </c:if>
                            </tr>

                            <c:set var="count" value="1"/>
                            <c:forEach items="${resultMap.fileEdit}" var="fe">
                                <c:if test="${fe.objMap.fileEdit_fileRemark =='other'}">
                                    <tr>
                                        <td></td>
                                        <td class="fileListSerial">${count+1}<c:set var="count"
                                                                                    value="${count+1}"/></td>
                                        <td>
                                            <a href='<s:url value="/pub/file/down?fileFlow=${fe.objMap.fileEdit_file}"/>'>${fe.objMap.fileEdit_fileName}</a>
                                            <input type="hidden" name="fileEdit_fileRemark"
                                                   value="${fe.objMap.fileEdit_fileRemark}"/>
                                            <input type="hidden" name="fileEdit_fileName"
                                                   value="${fe.objMap.fileEdit_fileName}"/>
                                            <input type="hidden" name="fileEdit_file"
                                                   value="${fe.objMap.fileEdit_file}"/>
                                        </td>
                                        <td><img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/></td>
                                        <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                            <td style="padding-left: 20px;">
                                                [<a href="javascript:void(0)" onclick="delFile(this)">删除</a>]
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${editFlag != 'audit'}">
                            <div class="button" style="width: 100%;text-align: center; margin-top: 20px;">
                                <input type="button" onclick="selectTag('tagContent1','tag1')" class="search"
                                       value="上一步"/>
                                <input type="button" onclick="nextOpt('finish')" class="search" value="保&#12288;存"/>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${editFlag eq 'audit'}">
                        <div>
                            <h2>审核意见：</h2>
                            <hr/>
                            <div style="text-align: center;">
                                到账经费：<input type="text" class="validate[required,custom[number],max[100000000]] inputText" name="realityAmount" />&#12288;&#12288;&#12288;&#12288;
                                到账时间：<input type="text" name="fundIncomeTime"
                                       readonly="readonly" class="validate[required] inputText ctime"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                                积分项：
                                <select name="scoreFlow" id="scoreFlow" class="xlselect" onchange="getScoreName(this);">
                                    <option value="">请选择</option>
                                    <c:forEach items="${srmAchScoreList}" var="score">
                                        <option value="${score.scoreFlow}" <c:if test="${score.scoreFlow eq author.scoreFlow}">selected="selected"</c:if>>${score.scoreName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="scoreName" value="${author.scoreName}"/>
                            <textarea id="auditContent" name="auditContent" style="width: 100%"
                                      rows="7"></textarea><br/><br/><br/>
                                <input class='search'
                                       onclick="saveAudit('${GlobalConstant.FLAG_Y}')"
                                       type='button' value='同&#12288;意'/>&#12288;
                                <input class='search'
                                       onclick="saveAudit('${GlobalConstant.FLAG_N}')"
                                       type='button' value='退&#12288;回'/>&#12288;
                            </div>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>

<div style="display: none;">
    <!-- 项目经费  模板 -->
    <table id="fundListTemplate" width="100%" class="bs_tb" style="margin-top: 20px;">
        <tr>
            <td width="20px" style="text-align: center;"><input name="fundListIds" type="checkbox"/></td>
            <td width="40px" style="text-align: center;" class="fundListSerial"></td>
            <td><input type="text" class="validate[required] inputText" style="width:90%;" name="fundList_type"></td>
            <td><input type="text" class="validate[required,custom[number]] inputText" onchange="calculator(this)" style="width:90%;" name="fundList_money"></td>
            <td><input type="text" class="validate[required,custom[number]] inputText" readonly="readonly" style="width:90%;" name="fundList_scale"></td>
            <td><input type="text" class="validate[required] inputText" style="width:90%;" name="fundList_remark">
            </td>
        </tr>
    </table>

    <!-- 文件 -->
    <table class="basic" id="fileListTemplate" style="width: 100%">
        <tr>
            <td width="3%" style="text-align: center;"><input name="fileListIds" type="checkbox"/></td>
            <td width="5%" style="text-align: center;" class="fileListSerial"></td>
            <td>
                <input type="text" name="fileEdit_fileName" class="inputText" style="width: 80%"/>
                <input type="hidden" name="fileEdit_fileRemark" class="validate[required]" value="other"/>
            </td>
            <td></td>
            <td style="padding-left: 20px;">
                <input type="file" name="fileEdit_file" class="validate[required]"/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>