<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
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

            $("#leader").hide();
            if (${subject.orderAction eq 'appoint'}){
                $("#leader").show();
                leader('1');
            }
            if (${subject.orderAction eq 'free'}){
                $("#leader").hide();
                leader('2');
            }

            chooseSpe();

        });

        function chooseActivity() {
            var speName =  $("#speId").find("option:selected").text();
            if (speName==null || speName=='' || speName== undefined){
                jboxTip("请选择专业！");
                return;
            }
            var activityFlows = $("#activityFlows").val();

            var url = "<s:url value='/jsres/supervisio/chooseActivity'/>?speName="+ speName+"&activityFlows="+activityFlows;
            jboxOpen(url, "选择教学活动", 1050, 550);
        }

        function chooseLeader() {
            var userFlows = $("#userFlow").val();
            var url = "<s:url value='/jsres/supervisio/chooseLeader'/>?userFlows="+userFlows;
            jboxOpen(url, "专家选择", 580, 475);
        }

        function leader(num) {
            if (num=='1'){
                $("#leader").show();
            }else {
                $("#leader").hide();
            }
            $("#orderActionValue").val(num);
        }


        function saveSubject() {
            jboxStartLoading();
            var orderActionValue = $("#orderActionValue").val();
            if (orderActionValue=="" || orderActionValue ==undefined || orderActionValue==null){
                jboxEndLoading();
                jboxTip("请选择评审方式！");
                return;
            }
            if (orderActionValue=="1"){
                if ($("#userName").val() == "" || $("#userName").val() == undefined){
                    jboxEndLoading();
                    jboxTip("请选择检查专家！");
                    return;
                }
            }
            if ($("#scoreTable").val()==null || $("#scoreTable").val()==''){
                jboxEndLoading();
                jboxTip("暂无当前对应模板的评分表！");
                return;
            }
            var speName = $('#speId option:selected').text();
            var fileRoute =$('#scoreTable option:selected').val();
            var data = $('#editForm').serialize();
            var url =  "<s:url value='/jsres/supervisio/saveHospitalSubject'/>?speName="+speName+"&fileRoute="+fileRoute;
            jboxPost(url, data, function (resp) {
                if (resp=='${GlobalConstant.SAVE_SUCCESSED}' || resp=='${GlobalConstant.UPDATE_SUCCESSED}'){
                    window.parent.toPage(1);
                    setTimeout(function () {
                        top.jboxCloseMessager();
                    }, 3000);
                    jboxEndLoading();
                    jboxTip(resp);
                }else if (resp=='${GlobalConstant.SAVE_FAIL}' || resp=='${GlobalConstant.UPDATE_FAIL}'){
                    jboxEndLoading();
                    jboxTip(resp);
                }
            });
        }

        function viewTable() {
            var fileRoute =$('#scoreTable option:selected').val();
            if (fileRoute=='' || fileRoute==null){
                jboxTip("请选择表单！");
            }
            var url = "<s:url value ='/jsres/supervisio/viewTable'/>?fileRoute=" + fileRoute+"&edit=N";
            top.jboxOpen(url, "评分表预览", 1100, 670);
        }

        function chooseSpe() {
            $("#scoreTable").children().remove();
            var speId = $("#speId").val();
            var inspectionType = $("#inspectionType").val();
            $.ajax({
                url: "<s:url value='/jsres/supervisio/chooseTable'/>",
                type: "get",
                data: {"speId": speId, "inspectionType": inspectionType},
                dataType: "json",
                success: function (res) {
                    var tableChoose=$("#table").val();
                    if (res.length==0){
                        $("#ylb").css("display","none");
                    }else {
                        $("#ylb").css("display","");
                    }
                    var option="";
                    for (score of res){
                        if (score.tablePathName== tableChoose){
                            option= "<option selected='selected' value='"+score.tablePathName+"'>"+score.tableName+"</option>"
                        }else {
                            option="<option value='"+score.tablePathName+"'>"+score.tableName+"</option>"
                        }
                        $("#scoreTable").append(option);
                    }
                }
            });

            /*   var speId = $('#speId option:selected').val();
               //没有表单不给新建项目
               if (speId=='0700' || speId=='1900' || speId=='0700' ||speId=='2000' || speId=='2100' || speId=='2200' || speId=='2300'
                   || speId=='2400' ||speId=='2800' ||speId=='2900' ||speId=='3000' ||speId=='3100' ||speId=='3200' ||speId=='3300' ||
                   speId=='3400'){
                   $("#speId")[0].selectedIndex=0;
                   jboxEndLoading();
                   jboxTip("该专业的表单正在维护中！");
                   return;
               }*/
        }
    /*    /!**
         *模糊查询加载
         *!/
        (function($){
            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        $(function(){
            $("#orgSel").likeSearchInit({});
        });*/
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;margin-left: -100px;margin-top: -40px;margin-right: -30px;" method="post">
            <input type="hidden" name="subjectFlow" value="${subject.subjectFlow }"/>
            <input type="hidden" id="table" value="${subject.scoreTable}"/>
            <input type="hidden" name="inspectionType" id="inspectionType" value="${subject.inspectionType }"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;border: none">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th style="background-color: white;border: none"><span style="color:red;">*</span>评审方式：</th>
                    <td style="border: none">
                        <input type="hidden" id="orderActionValue" value="">
                        <input type="radio" value="appoint" name="orderAction"  onclick="leader('1');" id="orderAction1" style="cursor: pointer"
                               <c:if test="${subject.orderAction eq 'appoint'}">checked</c:if> />指定专家&#12288;
                        <input type="radio" value="free"  name="orderAction"  onclick="leader('2');" id="orderAction2" style="cursor: pointer"
                               <c:if test="${subject.orderAction eq 'free'}">checked</c:if>/>自选专家&#12288;
                    </td>
                </tr>
                <tr id="leader">
                    <th style="background-color: white;border: none"><span style="color:red;">*</span>检查专家：</th>
                    <td  style="border: none">
                        <%--为了逗号分开两个专家的信息--%>
                        <c:if test="${empty subject.leaderOneName}">
                            <input class="input" style="width: 348px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 310px -4px;cursor: pointer;" name="userName" id="userName" type="text"
                                   onclick="chooseLeader();" value="">
                            <input hidden="hidden"  id="userFlow" name="userFlow" value="" type="text">
                        </c:if>
                        <c:if test="${not empty subject.leaderOneName}">
                            <input class="input" style="width: 348px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 310px -4px;cursor: pointer;" name="userName" id="userName" type="text"
                                   onclick="chooseLeader();" value="${subject.leaderOneName}<c:if test="${not empty subject.leaderTwoName}">,${subject.leaderTwoName}</c:if>
                                                                    <c:if test="${not empty subject.leaderThreeName}">,${subject.leaderThreeName}</c:if>
                                                                    <c:if test="${not empty subject.leaderFourName}">,${subject.leaderFourName}</c:if>">
<%--                            <c:if test="${not empty subject.leaderOneFlow && not empty subject.leaderTwoFlow}">--%>
<%--                                <input class="input" style="width: 348px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 310px -4px;cursor: pointer;" name="userName" id="userName" type="text"--%>
<%--                                       onclick="chooseLeader();" value="${subject.leaderOneName},${subject.leaderTwoName}">--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${ empty subject.leaderOneFlow ||  empty subject.leaderTwoFlow}">--%>
<%--                                <input class="input" style="width: 348px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 310px -4px;cursor: pointer;" name="userName" id="userName" type="text"--%>
<%--                                       onclick="chooseLeader();" value="${subject.leaderOneName}${subject.leaderTwoName}">--%>
<%--                            </c:if>--%>

                            <input hidden="hidden"  id="userFlow" name="userFlow" value="${subject.leaderOneFlow},${subject.leaderTwoFlow},${subject.leaderThreeFlow},${subject.leaderFourFlow}" type="text">
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <th style="background-color: white;border: none">专&#12288;&#12288;业：</th>
                    <td style="border: none">
         <%--               <input type="hidden" id="speId" name="speId" value="${subject.speId}">
                        <input id="orgSel" class="toggleView input" type="text"
                               style="width: 355px;margin-left: 0px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 317px -4px;"
                               name="speName" placeholder="请选择专业"
                               value="${subject.speName}" autocomplete="off" title="${param.speName}" onmouseover="this.title = this.value"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:0px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 170px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <p class="item" flow="${dict.dictId}" value="${dict.dictId}" onclick="toSpeId('${dict.dictId}');" style="height: 30px;padding-left: 10px;text-align: left;">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>--%>
                        <select id="speId" name="speId" class="select" style="width: 355px;margin-left: 4px" onchange="chooseSpe();">
                            <option value="">-</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${subject.speId eq dict.dictId}">selected</c:if>
                                        <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                        <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="background-color: white;border: none"><span style="color:red;">*</span>评&nbsp;&nbsp;分&nbsp;表：</th>
                    <td style="border: none">
                        <select id="scoreTable" name="table" class="select" style="width: 355px;margin-left: 4px"></select>
                        <a id="ylb" style="color:blue;" href="javascript:viewTable();" >预览表</a>
                    </td>
                </tr>

                </tbody>
            </table>
        </form>

        <div class="button">
            <input type="button" class="btn_green" onclick="saveSubject();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>