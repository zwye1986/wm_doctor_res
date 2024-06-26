<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function save(){
            if(false==$("#editForm").validationEngine("validate")){
                return ;
            }
            var schStartDate =$("#startDate").val();
            var schEndDate =$("#endDate").val();
           if( schStartDate>schEndDate){
                jboxTip("结束时间小于开始时间，请重新选择！")
               return false;
            }
            var doctorFlow = $("input[name='doctorFlow']").val();
            var resultFlow = $("input[name='resultFlow']").val();
            //验证时间是否与其他科室重叠
            var checkUrl = '<s:url value="/res/doc/checkTheResultDate"/>';
            jboxPost(checkUrl,
                    {
                        doctorFlow:doctorFlow,
                        resultFlow:resultFlow,
                        startDate:schStartDate,
                        endDate:schEndDate
                    },
                    function(resp){
                        if("${GlobalConstant.FLAG_Y}" == resp){
                            toSave();
                        }else if("${GlobalConstant.FLAG_N}" == resp){
                            return jboxTip("该轮转时间与其他科室轮转时间重叠,请重新选择！");
                        }else{
                            console.error(resp);
                        }
                    },null,false);

        }
        function toSave(){
            //名称
            var groupName = $("select[name='groupFlow']").find("option:selected").text();
            $("input[name='groupName']").val(groupName);

            //标准科室名称
            var standardDeptName = $("select[name='standardDeptId']").find("option:selected").text();
            $("input[name='standardDeptName']").val(standardDeptName);

            var url = "<s:url value='/schTwo/arrange/saveCourseMaint'/>";
            var data = $('#editForm').serialize();
            jboxPost(url,data,function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].window.selDoc('',$("#doctorFlow").val());
                    top.jboxClose();
                }
            },null,true);
        }



        //加载标准科室
        function loadStandardDept(){

            var rotationFlow = '${rotationFlow}';

            var groupFlow = $("#groupFlow").find("option:selected").val();

            var standardDept = $("select[name='standardDeptId']");

            //首先要刷新一下标准科室下拉框
            standardDept.empty();

            var paramStandardDeptId= '${result.standardDeptId}';

            standardDept.append('<option value="">'+'请选择'+'</option>');

            var url = "<s:url value='/schTwo/loadStandardDept'/>?rotationFlow=" + rotationFlow+"&groupFlow="+groupFlow;
            jboxGet(url, null, function (data) {

                for (var i=0;i<data.length;i++) {
//                    if(data!=''){
                        var sel = "";
                        if (data[i].standardDeptId == paramStandardDeptId) {
                            sel = "selected";
                        }
                        standardDept.append('<option value="' + data[i].standardDeptId + '" ' + sel + '>' + data[i].standardDeptName + '</option>');
//                    }
                }
            }, null, false);
        }

        //切换机构 加载轮转科室
        function loadSchDept() {
            var orgFlow = $("#orgFlow").find("option:selected").val();
            if(orgFlow=='' || orgFlow==null){
                return;
            }
            //首先要刷新一下轮转科室下拉框
            $("#schDeptFlow").empty();

            var paramSchDeptFlow= '${result.schDeptFlow}';

            var schDept = $("#schDeptFlow");
            schDept.append('<option value="">'+'请选择'+'</option>');


            var url = "<s:url value='/schTwo/loadSchDept'/>?orgFlow=" + orgFlow;
            jboxGetAsync(url, null, function (data) {

                for (var i=0;i<data.length;i++) {
                    var sel = "";
                    if (data[i].schDeptFlow == paramSchDeptFlow) {
                        sel = "selected";
                    }
                    schDept.append('<option value="' + data[i].schDeptFlow + '" ' + sel + '>' + data[i].schDeptName + '</option>');

                }
            }, null, false);
            loadTeacherAndHead();
        }


        //本页select数据
        var selDatas = {
            'headSeller':'${process.headUserFlow}',
            'teacherSeller':'${process.teacherUserFlow}'
        };
        //根据科室查询带教、科主任
        function loadTeacherAndHead(){

            var obj  =  $("#schDeptFlow");;

            var schDeptFlow = $(obj).find("option:selected").val();

//            console.log(schDeptFlow);
            if($(obj).val()==null || $(obj).val()==''){
                $("#teacherUserFlow").empty();
                $("#teacherUserFlow").append('<option value="">请选择</option>');
                return;
            }

            $("#schDeptName").val($(obj).find('option:selected').text());

            var url = '<s:url value="/res/doc/loadTeacherAndHead"/>';
            jboxPost(url,{schDeptFlow:schDeptFlow},function(resp){
                if(resp){
                    var option = $('<option/>');
                    for(var key in resp){
                        var list = resp[key];
                        if(list){
                            var select = $('.'+key).empty();
                            if(select.length){
                                select.empty().append(option.clone());
                                for(var index in list){
                                    var data = list[index];
                                    var val = data.userFlow;
                                    var selData = selDatas[key];
                                    select.append(option.clone().val(val).text(data.userName).attr('selected',val==selData));
                                }
                            }
                        }
                    }
                }
            },null,false);
        }

        $(document).ready(function(){
            loadSchDept();
            loadStandardDept();
        });
    </script>
</head>

<body>
<div class="title1 clearfix">
    <form id="editForm">
        <input type="hidden" id="doctorFlow" name="doctorFlow" value="${doctorFlow}">
        <input type="hidden"  name="rotationFlow" value="${rotationFlow}">
        <input type="hidden"  name="resultFlow" value="${result.resultFlow}">
        <table class="basic" width="600" cellpadding="0" cellspacing="0">

        <tr>
            <th width="15%">培训基地：</th>
            <td width="35%">
                <select style="width:167px;" class="validate[required] xlselect" id="orgFlow" name="orgFlow" onchange="loadSchDept();">
                    <option value="">请选择</option>
                    <c:forEach var="org" items="${applicationScope.sysOrgList}">
                    <option value="${org.orgFlow}" <c:if test="${result.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                    </c:forEach>
                </select>
            </td>
            <th width="15%">轮转科室：</th>
            <td width="35%">
                <select class="validate[required] xlselect" id="schDeptFlow" name="schDeptFlow" onchange="loadTeacherAndHead()">
                    <option value="">请选择</option>
                </select>
            </td>
        </tr>

        <tr>
            <th width="15%">方案组：</th>
            <td width="35%">
                <select class="validate[required] xlselect" id="groupFlow" name="groupFlow" onchange="loadStandardDept()">
                    <option value="">请选择</option>
                    <c:forEach items="${rotationGroupList}" var="rotationGroup">
                        <option value="${rotationGroup.groupFlow}" <c:if test="${rotationGroup.groupFlow eq result.standardGroupFlow}">selected="selected"</c:if>>${rotationGroup.groupName}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="groupName" value="">
            </td>
            <th width="15%">标准科室：</th>
            <td width="35%">
                <select class="validate[required] xlselect" name="standardDeptId" >
                    <option value="">请选择</option>
                </select>
                <input type="hidden" name="standardDeptName" value="${result.standardDeptName}">
            </td>
        </tr>
        <tr>
            <th width="15%">带教老师：</th>
            <td width="35%">
                <select name="teacherUserFlow" id="teacherUserFlow" class="teacherSeller  xlselect">
                    <option value="">请选择</option>
                </select>
            </td>
            <th width="15%">科主任：</th>
            <td width="35%">
                <select name="headUserFlow" id="headUserFlow" class="headSeller xlselect">
                    <option value="">请选择</option>
                </select>
            </td>
        </tr>
        <tr>
            <th width="15%">开始时间：</th>
            <td width="35%">
                <input class="validate[required] xltext" type="text" name="schStartDate" id="startDate"
                       value="${result.schStartDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       readonly="readonly" />
            </td>
            <th width="15%">结束时间：</th>
            <td width="35%">
                <input class="validate[required] xltext" type="text" name="schEndDate" id="endDate"
                       value="${result.schEndDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       readonly="readonly" />
            </td>
        </tr>

    </table>
    </form>
    <div class="button" style="width: 600px;">
        <input class="search" type="button" value="保&#12288;存" onclick="save();" />
        <input class="search" type="button" value="关&#12288;闭" onclick="top.jboxClose();" />
    </div>
</div>


</body>
</html>