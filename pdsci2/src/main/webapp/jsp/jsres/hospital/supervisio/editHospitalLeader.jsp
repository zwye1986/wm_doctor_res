<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        /**
         *模糊查询加载
         */
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


        //页面加载完成时调用
        $(function(){
            $("#orgSel").likeSearchInit({});
        });


        $(function () {
            var edit = $('#edit').val();
            if (edit=='edit'){
                var subjectAddFalg = $("#userLevelId").find("option:selected").val();
                var select;
                var info = $('#info').val();
                for(var i=0; i<select.options.length; i++){
                    if(select.options[i].value == info){
                        select.options[i].selected = true;
                        break;
                    }
                }
            }
        })
        function save() {
            jboxStartLoading();
            debugger;
            if (false == $("#editForm").validationEngine("validate")) {
                jboxEndLoading();
                return false;
            }
            var speName = $("#speId").find("option:selected").text();
            if(speName =="请选择"){
                jboxTip("请选择专业！");
                jboxEndLoading();
                return false;
            }

            var url = "<s:url value='/jsres/supervisio/saveHospitalLeader'/>?&resTrainingSpeName=" + speName;
            var data = $('#editForm').serialize();

            jboxPost(url, data, function (resp) {

                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功！初始密码为'Njpd@2022!!!'");
                    window.parent.toPage(1);
                    setTimeout(function () {
                        jboxClose();
                    }, 2000);
                } else if (resp == '${GlobalConstant.OPERATE_FAIL}') {
                    jboxTip("保存失败,手机号码已存在！");
                } else if (resp == '${GlobalConstant.UPDATE_SUCCESSED}') {
                    jboxTip(resp);
                    setTimeout(function () {
                        jboxClose();
                    }, 2000);
                } else if (resp == '${GlobalConstant.SAVE_FAIL}') {
                    jboxTip(resp);
                }
                jboxEndLoading();
            }, null, false);
        }

        function toDeptFlow(deptFlow) {
            $("#deptFlow").val(deptFlow);
        }
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;height: 480px;" method="post">
            <input type="hidden" name="userFlow" value="${user.userFlow }"/>
            <input type="hidden" id="edit" value="${type}"/>
            <input type="hidden" id="info" value="${user.resTrainingSpeId}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>专家姓名：</th>
                    <td><input class="validate[required,maxSize[10]] input" name="userName" type="text" placeholder="请输入专家名称"
                               value="${user.userName}" style="width: 300px"/></td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>专&#12288;&#12288;业：</th>
                    <td>
                        <select name="resTrainingSpeId" id="speId" class="select" style="width: 307px;margin-left: 4px" >
                            <option selected..... value="">请选择</option>
                            <option selected..... value="-">-</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${user.resTrainingSpeId eq dict.dictId?'selected':''}
                                        <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                        <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>科&#12288;&#12288;室：</th>
                    <td>
                        <input type="hidden" id="deptFlow" name="deptFlow" value="${user.deptFlow}">
                        <input id="orgSel" class="toggleView input" type="text"  placeholder="请选择科室"
                               style="width: 300px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 262px -4px;"
                               name="deptName" value="${user.deptName}" autocomplete="off" title="${param.deptName}" onmouseover="this.title = this.value"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
                            <div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${deptList}" var="dept">
                                    <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>手机号码：</th>
                    <td><input class="validate[required,custom[mobile]] input" name="userPhone" type="text"
                         placeholder="请输入手机号码"      value="${user.userPhone}" style="width: 300px"/></td>
                </tr>
                </tbody>
            </table>
            <div class="button" style="margin-top: 165px;">
                <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>