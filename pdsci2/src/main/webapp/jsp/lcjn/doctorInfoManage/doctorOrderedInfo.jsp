<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <script>
        function toPage1(page){
            $("#currentPage1").val(page);
            jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
        }
        //复选框事件
        //全选、取消全选、反选的事件
        function selectAll(){
            var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
            var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
            if (checkedsub == chsub || checkedsub == 0) {
                if ($("#SelectAll").attr("checked")) {
                    $(":checkbox").attr("checked", true);
                } else {
                    $(":checkbox").attr("checked", false);
                }
            }else {
                $("input[type='checkbox'][id='subcheck']").each(function(){
                    if($(this).attr("checked"))
                    {
                        $(this).removeAttr("checked");
                    }
                    else
                    {
                        $(this).attr("checked","true");
                    }
                });
            }
        }
        //子复选框的事件
        function setSelectAll(){
            //当没有选中某个子复选框时，SelectAll取消选中
            if (!$("#subcheck").checked) {
                $("#SelectAll").attr("checked", false);
            }
            var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
            var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
            if (checkedsub == chsub) {
                $("#SelectAll").attr("checked", true);
            }
        }
        function auditOpt(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选预约学员信息！");
                return;
            }else{
                var len = 0;
                $(":checkbox[class='check']:checked").each(function(){
                    if($(this).attr("statusId") != "Passing"){
                        len ++;
                    }
                });
                if(len > 0){
                    jboxTip("只能审核待审核状态的记录！");
                    return;
                }
            }
            var recordLst = [];
            $(":checkbox[class='check']:checked").each(function(){
                recordLst.push(this.value);
            })
            jboxButtonConfirm("学员能否成功预约该培训课程？","通过","不通过", function(){//通过
                var json = {"recordLst":recordLst,"auditStatusId":"Passed"};
                var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    setTimeout(function(){
                        jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
                    },1000);
                }, null, true);
            },function(){//不通过
                var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
                jboxOpen("<s:url value='/jsp/lcjn/doctorInfoManage/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"信息审核",250,150,true);
            },300);
        }
        function editOneInfo(recordFlow,type){
            var recordLst = [];
            recordLst.push(recordFlow);
            if(type == "audit"){
                jboxButtonConfirm("学员能否成功预约该培训课程？","通过","不通过", function(){//通过
                    var json = {"recordLst":recordLst,"auditStatusId":"Passed"};
                    var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
                    jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                        setTimeout(function(){
                            jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
                        },300);
                    }, null, true);
                },function(){//不通过
                    var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
                    jboxOpen("<s:url value='/jsp/lcjn/doctorInfoManage/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"信息审核",250,150,true);
                },300);
            }
            if(type == "edit"){
                jboxConfirm("确认撤销成待审核状态？",function() {//通过
                    var json = {"recordLst": recordLst, "auditStatusId": "Passing"};
                    var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
                    jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                        if("信息审核成功！"== resp){
                            jboxTip("状态撤销成功！");
                        }else{
                            jboxTip("状态撤销失败！");
                        }
                        setTimeout(function(){
                            jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
                        },1000);
                    }, null, false);
                });
            }
        }
        function revokeAudit(recordFlow,auditStatus){
            var courseFlow=$("input[name='courseFlow']").val();
            if(auditStatus == 'UnPassed'){
                var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/selectOrderNum?courseFlow='/>" + courseFlow;
                jboxPost(url, null, function (resp) {
                    if("N"== resp){
                        jboxTip("该课程预约人数已满，无法撤销！")
                        return;
                    }
                    if("Y"== resp){
                        editOneInfo(recordFlow,'edit');
                    }
                }, null, false);
            }else{
                editOneInfo(recordFlow,'edit');
            }
        }
        function addDoctors(){
            var courseFlow=$("input[name='courseFlow']").val();
            var courseName=$("input[name='courseName']").val();
            jboxStartLoading();
            jboxOpen("<s:url value='/lcjn/lcjnDoctorTrainInfo/showLocalDoctors'/>?courseFlow="+courseFlow+"&courseName="+encodeURI(encodeURI(courseName)), "添加学员", 400, 380,false);
        }
        function exportDoctorInfo(){
            var auditStatusId=$("select[name='auditStatusId']").val();
            var courseFlow='${courseFlow}';
            var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportDoctorInfo'/>?courseFlow="+courseFlow+"&auditStatusId="+auditStatusId;
            jboxTip("导出中....");
            $("#exportA1").attr("href",url);
            $("#outToExcelSpan1").click();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
            <div id="appointDiv" class="labelDiv">
            <form id="appointForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>" method="post">
                <div class="choseDivNewStyle">
                <a class="btn" id="exportA1" type="hidden"><span id="outToExcelSpan1"> </span></a>
                <input type="hidden" name="courseFlow" value="${courseFlow}"/>
                <input type="hidden" name="courseName" value="${courseName}"/>
                <input id="currentPage1" type="hidden" name="currentPage1" value="${currentPage1}"/>
                <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                    <tr>
                        <td style="border:0px;">
                            <span style="margin-left: -10px;"></span>审核状态：
                            <select name="auditStatusId" style="width:137px;" class="select">
                                <option value="">全部</option>
                                <c:forEach items="${lcjnAuditStatusEnumList}" var="status">
                                    <option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
                                </c:forEach>
                            </select>
                            <span style="padding-left:20px;"></span>
                            <input type="button" class="search" value="查&#12288;询" onclick="toPage1(1)"/>
                            <input type="button" class="search" value="添&#12288;加" onclick="addDoctors()"/>
                            <input type="button" class="search" value="导&#12288;出" onclick="exportDoctorInfo()"/><br/>
                            <input type="checkbox" id="SelectAll" onclick="selectAll()" style="margin-left: -10px;">&nbsp;全选/反选&nbsp;
                            <input type="button" class="search" value="审&#12288;核" onclick="auditOpt()"/>
                        </td>
                    </tr>
                </table>
                </div>
            </form>
            <table class="xllist" style="width:100%;">
                <colgroup>
                    <col width="5%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="5%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>培训专业</th>
                    <th>身份证号</th>
                    <th>工作单位</th>
                    <th>所在科室</th>
                    <th>联系方式</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${doctorOrderInfoList}" var="info" varStatus="i">
                    <tr>
                        <td><input type="checkbox" id="subcheck" class="check" value="${info.RECORD_FLOW}" statusId="${info.AUDIT_STATUS_ID}" onclick="setSelectAll()">&nbsp;${i.index + 1}</td>
                        <td>${info.USER_CODE}</td>
                        <td>${info.USER_NAME}</td>
                        <td>${info.SEX_NAME}</td>
                        <td>${info.LCJN_SPE_NAME}</td>
                        <td>${info.ID_NO}</td>
                        <td>${info.ORG_NAME}</td>
                        <td>${info.DEPT_NAME}</td>
                        <td>${info.USER_PHONE}</td>
                        <td>${info.AUDIT_STATUS_NAME}</td>
                        <td>
                            <c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumPassing}">
                                <a onclick="editOneInfo('${info.RECORD_FLOW}','audit')" style="cursor:pointer;color:#4195c5;">审核</a>&#12288;
                                <a style="color:#b9c0c6;">撤销</a>
                            </c:if>
                            <c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumPassed or info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumUnPassed}">
                                <a style="color:#b9c0c6;">审核</a>&#12288;
                                <a onclick="revokeAudit('${info.RECORD_FLOW}','${info.AUDIT_STATUS_ID}')" style="cursor:pointer;color:#4195c5;">撤销</a>
                            </c:if>
                            <c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumInvalid}">
                                <a style="color:#b9c0c6;">审核</a>&#12288;
                                <a style="color:#b9c0c6;">撤销</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty doctorOrderInfoList}">
                    <tr><td colspan="99">暂无记录</td></tr>
                </c:if>
            </table>
            <div id="detail"></div>
            <div style="margin-top:100px;">
                <c:set var="pageView" value="${pdfn:getPageView(doctorOrderInfoList)}" scope="request"/>
                <pd:pagination toPage="toPage1"/>
            </div>
    </div>
</div>
</div>
</body>
</html>
