<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>

    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#subjectYear').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
            showAskContent();
        });

        function addTr(tr) {
            var html ="";
            if ('msgInfo'==tr){
                html =
                    '<tr>'+
                        '<td style="text-align: center">\n' +
                            '<select name="planMsgList.speId"  class="select  validate[required]" style="width: 100px;">\n' +
                                '<option value="">请选择</option>\n' +
                                '<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">\n' +
                                '<option value="${dict.dictId}">${dict.dictName}</option>\n' +
                                '</c:forEach>\n' +
                            '</select>\n' +
                        '</td>'+
                        '<td style="text-align: center">\n' +
                            '<input style="width: 80%" class="input  validate[required]" type="text"  name="planMsgList.classNum"/>\n' +
                        '</td>'+
                        '<td style="text-align: center">\n' +
                            '<input style="width: 80%" class="input  validate[required]" type="text"  name="planMsgList.peopleNum"/>\n' +
                        '</td>'+
                        '<td style="text-align: center">\n' +
                            '<input style="width: 80%" class="input  validate[required]" type="text"  name="planMsgList.adress"/>\n' +
                        '</td>'+
                        '<td style="text-align: left">\n' +
                            '<input type="checkbox" name="planMsgList.planTarget" value="1"/>主任医师&#12288;\n' +
                            '<input type="checkbox" name="planMsgList.planTarget" value="2"/>副主任医师&#12288;<br>\n' +
                            '<input type="checkbox" name="planMsgList.planTarget" value="3"/>主治医师&#12288;\n' +
                            '<input type="checkbox" name="planMsgList.planTarget" value="4"/>住院医师&#12288;<br>\n' +
                            '<input type="checkbox" name="planMsgList.planTarget" value="5"/>医士&#12288;\n' +
                        '</td>'+
                        '<td style="text-align: center">\n' +
                            '<input style="width: 80%" class="input" type="text"  name="planMsgList.remark"/>\n' +
                        '</td>'+
                        '<td style="text-align: center">\n' +
                            '<img title="编辑" width="25px" src="<s:url value="/img/u164.png" />" onclick="delTr(this);"/>\n' +
                        '</td>'+
                    '</tr>';
            }else {
                html =
                    '<tr>'+
                    '<td style="text-align: center">\n' +
                    '<input type="file" id="file" name="files" accept=".doc,.docx,.pdf" class="validate[required]">\n' +
                    '</td>'+
                    '<td style="text-align: center">\n' +
                    '<img width="25px" src="<s:url value="/img/u164.png" />" onclick="delTr(this);"/>\n' +
                    '</td>'+
                    '</tr>';
            }

            $('#'+tr).append(html);
        }

        function delTr(ele) {
            jboxConfirm("确认删除？" , function(){
                $(ele).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj,fileFlow) {
            var url = '<s:url value="/jsres/phyAss/delPlanFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }


        function savePlan(statusId,statusName) {
            var introduce = $("#introduce").val();
            if (null == introduce || introduce==''){
                top.jboxTip("请填写培训介绍！");
                return;
            }
            if(false==$("#editForm").validationEngine("validate")){
                return false;
            }
            var enrollStartTime = $("#enrollStartTime").val();
            var enrollEndTime = $("#enrollEndTime").val();
            var planStartTime = $("#planStartTime").val();
            var planEndTime = $("#planEndTime").val();
            if (enrollStartTime > enrollEndTime){
                top.jboxTip("报名开始时间不能大于报名结束时间！");
                $("#enrollEndTime").val('')
                return;
            }
            if (enrollEndTime > planStartTime){
                top.jboxTip("报名结束时间不能大于培训开始时间！");
                $("#planStartTime").val('')
                return;
            }
            if (planStartTime > planEndTime){
                top.jboxTip("培训开始时间不能大于培训结束时间！");
                $("#planEndTime").val('')
                return;
            }


            var trs = $('#msgInfo').children();
            var planMsgData = [];
            $.each(trs , function(i , n){
                var speId = $(n).find("select[name='planMsgList.speId']").find("option:selected").val();
                var speName = $(n).find("select[name='planMsgList.speId']").find("option:selected").text();
                var classNum =  $(n).find("input[name='planMsgList.classNum']").val();
                var peopleNum =  $(n).find("input[name='planMsgList.peopleNum']").val();
                var tar = $(n).find("input[name='planMsgList.planTarget']");
                var planTarget="";
                for (t in tar){
                    if (tar[t].checked){
                        planTarget=planTarget+tar[t].value+",";
                    }
                }
                if (planTarget==""){
                    top.jboxTip("请选择培训对象！");
                    return;
                }
                var adress =  $(n).find("input[name='planMsgList.adress']").val();
                var remark =  $(n).find("input[name='planMsgList.remark']").val();
                var data = {
                    "speId":speId,
                    "speName":speName,
                    "classNum":classNum,
                    "peopleNum":peopleNum,
                    "planTarget":planTarget,
                    "adress":adress,
                    "remark":remark
                };
                planMsgData.push(data);
            });
            var reason = $("#askContent").val();
            var reg = new RegExp("\\n","g");//g,表示全部替换。
            reason=reason.replace(reg,"<br/>");
            reason = encodeURIComponent(reason);
            var planInfo = {
                "introduce":introduce,
                "planFlow":$("#planFlow").val(),
                "planContent":$("#planContent").val(),
                "enrollStartTime":enrollStartTime,
                "enrollEndTime":enrollEndTime,
                "planStartTime":planStartTime,
                "planEndTime":planEndTime,
                "askContent":reason,
                "planStatusId":statusId,
                "planStatusName":statusName
            };
            var sendData = {'planMsgList':planMsgData,'plan':planInfo};
            $('#jsondata').val(JSON.stringify(sendData));
            var url = "<s:url value='/jsres/phyAss/editPlannedRelease'/>";
            jboxSubmit($('#editForm'),url,function(resp) {
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    window.parent.plannedRelease();
                    jboxTip(resp);
                    top.jboxClose();
                }else {
                    jboxTip(resp);
                    top.jboxClose();
                }
            }, null, true);
        }

        function checkPlanContent() {
            var planContent = $("#planContent").val();
            var url = "<s:url value ='/jsres/phyAss/checkPlanContent'/>";
            $.ajax({
                url: url,
                type: "get",
                data: {"planContent": planContent},
                dataType: "json",
                success: function (res) {
                    if(res=='${GlobalConstant.FLAG_Y}'){
                        $("#planContent").val('');
                        jboxTip("该培训计划已存在！");
                    }
                }
            });
        }

        function showAskContent() {
            var planFlow = $("#planFlow").val();
            var url = "<s:url value ='/jsres/phyAss/showAskContent'/>";
            $.ajax({
                url: url,
                type: "get",
                data: {"planFlow": planFlow},
                dataType: "json",
                success: function (res) {
                    var itemIdList2 = $("textarea");
                    for (var i = 0; i < itemIdList2.length; i++) {
                        if (itemIdList2[i].getAttribute("itemId") == "askContent") {
                            var reason= res;
                            var reg = new RegExp("<br/>","g");//g,表示全部替换。
                            reason=reason.replace(reg,"\n");
                            itemIdList2[i].innerHTML= reason;
                        }
                    }
                }
            });
        }
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table" style="height: 700px">
        <form id="editForm"  method="post"
              style="position: relative;margin-left: -100px;margin-right: -30px;margin-bottom: 25px;height: 600px;overflow-y: auto" >
            <input type="hidden" id="jsondata" name="jsondata">
            <input type="hidden" id="type" name="type" value="${type}">
            <input type="hidden" id="planFlow" name="planFlow" value="${plan.planFlow}">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="border: none">
                <colgroup>
                    <col width="20%"/>
                    <col width="75%"/>
                    <col width="5%"/>
                </colgroup>
                <tbody>
                    <tr>
                        <th style="background-color: white;border: none"><span style="color:red;">*</span>&#12288;培训介绍：</th>
                        <td>
                            <textarea name="plan.introduce" id="introduce" style="width: 100%;height: 80px;border: 0px solid;text-indent: 0px" >${plan.introduce}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="background-color: white;border: none"><span style="color:red;">*</span>&#12288;培训计划：</th>
                        <td style="border: none">
                            <input style="width: 100%;margin-left: -5px" class="input validate[required]" type="text" id="planContent"
                                   name="plan.planContent" value="${plan.planContent}"
                                   onchange="checkPlanContent();"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th style="background-color: white;border: none"><span style="color:red;">*</span>&#12288;报名日期：</th>
                        <td style="border: none">
                            <input name="plan.enrollStartTime" style="margin-left: -5px;width: 165px;" placeholder="请选择报名开始时间"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="enrollStartTime"
                                   value="${plan.enrollStartTime}" class="input validate[required]">
                            &#12288;至&#12288;
                            <input name="plan.enrollEndTime"  style="margin-left: 0px;width: 165px;" placeholder="请选择报名结束时间"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="enrollEndTime"
                                   value="${plan.enrollEndTime}" class="input validate[required]">
                        </td>
                    </tr>
                    <tr>
                        <th style="background-color: white;border: none"><span style="color:red;">*</span>&#12288;培训日期：</th>
                        <td style="border: none">
                            <input name="plan.planStartTime" style="margin-left: -5px;width: 165px;" placeholder="请选择培训开始时间"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="planStartTime"
                                   value="${plan.planStartTime}" class="input validate[required]">
                            &#12288;至&#12288;
                            <input name="plan.planEndTime"  style="margin-left: 0px;width: 165px;" placeholder="请选择培训结束时间"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="planEndTime"
                                   value="${plan.planEndTime}" class="input validate[required]">
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="margin-top: 10px">
                <span style="font-size: 14px;font-weight: bold;color:#333;margin-left: 13%">培训计划：</span>
            </div>


            <table border="0" cellpadding="0" cellspacing="0" class="base_info" id="plan"
                   style="margin-top: -20px;margin-left: 20%;width: 75%">
                <colgroup>
                    <col width="8%"/>
                    <col width="8%"/>
                    <col width="10%"/>
                    <col width="14%"/>
                    <col width="19%"/>
                    <col width="10%"/>
                    <col width="6%"/>
                </colgroup>
                    <tr>
                        <th style="border: 1px solid #d7d7d7;text-align: center"><span style="color:red;border: 1px solid #e7e7eb">*</span>培训专业</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center"><span style="color:red;">*</span>计划班级个数</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center"><span style="color:red;">*</span>计划（每）班级人数</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center"><span style="color:red;">*</span>培训地点</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center"><span style="color:red;">*</span>培训对象</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center">备注</th>
                        <th style="border: 1px solid #d7d7d7;text-align: center">
                            <span>操作</span>
                            <img style="margin-top: -20px"  width="14px" src="<s:url value="/img/add.png" />" onclick="addTr('msgInfo')"/>
                        </th>
                    </tr>
                <tbody id="msgInfo">
                    <c:if test="${empty msgList}">
                        <tr>
                            <td style="text-align: center">
                                <select name="planMsgList.speId"  class="select validate[required]" style="width: 100px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option value="${dict.dictId}">${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: center">
                                <input style="width: 80%" class="input validate[required]" type="text"  name="planMsgList.classNum"/>
                            </td>
                            <td style="text-align: center">
                                <input style="width: 80%" class="input validate[required]" type="text"  name="planMsgList.peopleNum"/>
                            </td>
                            <td style="text-align: center">
                                <input style="width: 88%" class="input validate[required]" type="text"  name="planMsgList.adress"/>
                            </td>
                            <td style="text-align: left">
                                <input type="checkbox" name="planMsgList.planTarget" value="1"/>主任医师&#12288;
                                <input type="checkbox" name="planMsgList.planTarget" value="2"/>副主任医师&#12288;<br>
                                <input type="checkbox" name="planMsgList.planTarget" value="3"/>主治医师&#12288;
                                <input type="checkbox" name="planMsgList.planTarget" value="4"/>住院医师&#12288;<br>
                                <input type="checkbox" name="planMsgList.planTarget" value="5"/>医士&#12288;
                            </td>
                            <td style="text-align: center">
                                <input style="width: 84%" class="input " type="text"  name="planMsgList.remark"/>
                            </td>
                            <td style="text-align: center">
                                <img width="25px" src="<s:url value="/img/u164.png" />" onclick="delTr(this);"/>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty msgList}">
                        <c:forEach items="${msgList}" var="m">
                            <tr>
                                <td style="text-align: center">
                                    <select name="planMsgList.speId"  class="select  validate[required]" style="width: 100px;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                            <option value="${dict.dictId}" ${m.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: center">
                                    <input style="width: 80%" class="input  validate[required]" type="text"  name="planMsgList.classNum" value="${m.classNum}"/>
                                </td>
                                <td style="text-align: center">
                                    <input style="width: 80%" class="input  validate[required]" type="text"  name="planMsgList.peopleNum" value="${m.peopleNum}"/>
                                </td>
                                <td style="text-align: center">
                                    <input style="width: 88%" class="input  validate[required]" type="text"  name="planMsgList.adress" value="${m.adress}"/>
                                </td>
                                <td style="text-align: left">
                                    <input type="checkbox" name="planMsgList.planTarget" value="1"
                                           <c:if test="${pdfn:contains(m.planTarget,'1')}">checked="checked"</c:if>
                                    />主任医师&#12288;
                                    <input type="checkbox" name="planMsgList.planTarget" value="2"
                                           <c:if test="${pdfn:contains(m.planTarget,'2')}">checked="checked"</c:if>
                                    />副主任医师&#12288;<br>
                                    <input type="checkbox" name="planMsgList.planTarget" value="3"
                                           <c:if test="${pdfn:contains(m.planTarget,'3')}">checked="checked"</c:if>
                                    />主治医师&#12288;
                                    <input type="checkbox" name="planMsgList.planTarget" value="4"
                                           <c:if test="${pdfn:contains(m.planTarget,'4')}">checked="checked"</c:if>
                                    />住院医师&#12288;<br>
                                    <input type="checkbox" name="planMsgList.planTarget" value="5"
                                           <c:if test="${pdfn:contains(m.planTarget,'5')}">checked="checked"</c:if>
                                    />医士&#12288;
                                </td>
                                <td style="text-align: center">
                                    <input style="width: 84%" class="input" type="text"  name="planMsgList.remark" value="${m.remark}"/>
                                </td>
                                <td style="text-align: center">
                                    <img width="25px" src="<s:url value="/img/u164.png" />" onclick="delTr(this);"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>


                </tbody>
            </table>

            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;border: none">
                <colgroup>
                    <col width="20%"/>
                    <col width="75%"/>
                    <col width="5%"/>
                </colgroup>
                <tbody>
                    <tr>
                        <th style="background-color: white;border: none">培训要求：</th>
                        <td>
                            <textarea itemid="askContent" name="plan.askContent" id="askContent" style="width: 100%;height: 80px;border: 0px solid;text-indent: 0px" ></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div style="margin-top: 20px">
                <span style="font-size: 14px;font-weight: bold;color:#333;margin-left: 15.5%">附件：</span>
            </div>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info"
                   style="margin-top: -20px;margin-left: 20%;width: 75%">
                <colgroup>
                    <col width="50%"/>
                    <col width="50%"/>
                </colgroup>
                <tbody id="appendix">
                <tr>
                    <th style="border: 1px solid #d7d7d7;text-align: center">文件名</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">
                        <span>操作</span>
                        <img style="margin-top: -20px"  width="14px" src="<s:url value="/img/add.png" />" onclick="addTr('appendix')"/>
                    </th>
                </tr>


                <c:if test="${not empty fileList}">
                    <c:forEach items="${fileList}" var="f">
                        <tr>
                            <td style="text-align: left">
                                <a id="down" style="margin-left: 10px"
                                   href='<s:url value="/res/manager/downAdmisEduFile?fileFlow=${f.fileFlow}"/>'>${f.fileName}</a>
                                <input type="hidden" name="fileFlow" value="${f.fileFlow}"/>
                            </td>
                            <td style="text-align: center">
                                <img width="25px" src="<s:url value="/img/u164.png" />" onclick="delFile(this,'${f.fileFlow}');"/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>


                </tbody>
            </table>
        </form>
        <div class="button">
            <input type="button" class="btn_green" onclick="top.jboxClose();" value="取&#12288;消"/>
            <input type="button" class="btn_green" onclick="savePlan('Staging','暂存');" value="暂&#12288;存"/>
            <input type="button" class="btn_green" onclick="savePlan('Publish','发布');" value="发&#12288;布"/>
        </div>
    </div>
</div>
</body>
</html>