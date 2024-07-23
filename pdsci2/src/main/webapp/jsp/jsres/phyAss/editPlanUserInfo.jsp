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
    <link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
    <script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
        chooseSpe();
        $('select[name=deptFlow]').UCFormSelect();
        $(".UCSelect").css("margin-left","5px");
        $(".SelectVal").css("width","305px");
        $(".SelectBox").css("width","305px");
    });

    function chooseSpe() {
        var planFlow = $("#planFlow").val();
        $.ajax({
            url: "<s:url value='/jsres/phyAss/searchPlanByPlanFlow'/>",
            type: "get",
            data: {"planFlow": planFlow},
            dataType: "json",
            success: function (res) {
                $('#speId').html("");
                var userSpeName = $("#speName").val();
                var parse = JSON.parse(res);
                for(var i in parse){
                    var speId = parse[i].speId;
                    var speName = parse[i].speName;
                    var html='';
                    if (speName==userSpeName){
                         html='<option selected value='+speId+'>'+speName+'</option>'
                    }else {
                         html='<option value='+speId+'>'+speName+'</option>'
                    }

                    $("#speId").append(html);
                }
            }
        });
    }

    function savePlanUserInfo() {
        if(false==$("#editForm").validationEngine("validate")){
            return false;
        }
        var speName = $("#speId").find("option:selected").text();
        var sexName = $("#sexId").find("option:selected").text();
        var speId = $("#speId").val();
        var sexId = $("#sexId").val();
        if (sexId==''){
            sexName="";
        }
        $("#speName").val(speName);
        $("#sexName").val(sexName);
        var url = "<s:url value='/jsres/phyAss/savePlanUserInfo '/>";
        jboxSubmit($('#editForm'),url,function(resp) {

            if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                window.parent.toPage(1);
                jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
                setTimeout(function(){
                    top.jboxClose();
                }, 1000);
            }
        }, null, true);
    }

    function checkFile(file){
        var filePath = file.value;
        var types = ".jpg,.png,.jpeg".split(",");
        var regStr = "/";
        for(var i = 0 ;i<types.length;i++){
            if(types[i]){
                if(i==(types.length-1)){
                    regStr = regStr+"\\"+types[i]+'$';
                }else{
                    regStr = regStr+"\\"+types[i]+'$|';
                }
            }
        }
        regStr = regStr+'/i';
        regStr = eval(regStr);
        if($.trim(filePath)!="" && !regStr.test(filePath)){
            file.value = "";
            jboxTip("请上传&nbsp;.jpg,.png,.jpeg格式的图片");
        }
        $("#fileFlow").val("Y");
    }


    function delImg(fileFlow){
        jboxConfirm("确认删除？", function(){
            var url = "<s:url value='/jsres/phyAss/deleteCerfiticateImg'/>?fileFlow=" + fileFlow;
            jboxPost(url,null,function(resp){
                if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                    $("input[name='certificateUrl']").val("");
                    reuploadImg();
                    jboxTip("删除图片成功！");
                }
            },null,true);
        });
    }

    function reuploadImg(){
        $("#viewImgLink").hide();
        $("#delImgLink").hide();
        $("#reuploadImgLink").hide();
        $("#newFile").show();
        $("#fileFlow").val("Y");
    }
</script>
<div class="search_table" style="width: 100%;padding: 0 20px">
    <form id="editForm"  method="post" enctype="multipart/form-data"
          style="position: relative;height: 340px;overflow-y: auto" >
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="border: none;font-size: 14px">
            <input type="hidden" name="recordFlow" value="${map.recordFlow}"/>
            <input type="hidden" name="userFlow" value="${map.doctorFlow}"/>
            <input type="hidden" name="speName" id="speName" value="${map.speName}"/>
            <input type="hidden" name="sexName" id="sexName"/>
            <tr style="border: none">
                <td style="border: none;text-align: right;width: 10%"><span style="color:red;">*</span>培训计划：</td>
                <td style="border: none;width: 40%;text-align: left">
                    <select name="planFlow" id="planFlow" class="select" style="width:305px;margin-left: 5px" onchange="chooseSpe();">
                        <c:forEach items="${contentList}" var="c">
                            <option value="${c.planFlow}" ${planFlow eq c.planFlow?'selected':''}>${c.planContent}</option>
                        </c:forEach>
                    </select>
                </td>
                <td style="border: none;text-align: right;width: 10%">
                    <span style="color:red;">*</span>培训专业：
                </td>
                <td style="border: none;width: 40%;text-align: left">
                    <select name="speId" id="speId" class="select" style="width: 305px;margin-left: 5px">
                    </select>
                </td>
            </tr>
            <tr style="border: none">
                <td style="border: none;text-align: right"><span style="color:red;">*</span>基地：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px;background-color:#c1c1c1" class="input " type="text" value="${map.orgName}"  readonly/>
                    <input type="hidden" value="${map.orgFlow}"/>
                </td>
                <td style="border: none;text-align: right"><span style="color:red;">*</span>登录名：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px" class="input validate[required]" type="text" value="${map.doctorCode}"  name="doctorCode"/>
                </td>
            </tr>
            <tr style="border: none">
                <td style="border: none;text-align: right"><span style="color:red;">*</span>姓名：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px;background-color:#c1c1c1" class="input validate[required]" type="text" value="${map.doctorName}"  name="doctorName" readonly/>
                </td>
                <td style="border: none;text-align: right">性别：</td>
                <td style="border: none;text-align: left">
                    <select name="sexId" id="sexId" class="select" style="width:305px;margin-left: 5px">
                        <option value="">请选择性别</option>
                        <option value="Man" ${map.sexId eq 'Man'?'selected':''}>男</option>
                        <option value="Woman" ${map.sexId eq 'Woman'?'selected':''}>女</option>
                    </select>
                </td>
            </tr>
            <tr style="border: none">
                <td style="border: none;text-align: right">角色：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px;background-color:#c1c1c1" class="input" type="text" value="${map.roleName}" readonly/>
                </td>
                <td style="border: none;text-align: right">科室：</td>
                <td style="border: none;text-align: left">
                    <select name="deptFlow" class="select" id="deptFlow" style="width: 305px;margin-left: 5px" multiple="multiple">
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}"
                                    <c:if test="${pdfn:contains(map.deptFlow,dept.deptFlow)}">selected</c:if>
                            >${dept.deptName}</option>
                        </c:forEach>
                    </select>

                </td>
            </tr>
            <tr style="border: none">
                <td style="border: none;text-align: right">手机号：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px" class="input " type="text" value="${map.userPhone}"  name="userPhone"/>
                </td>
                <td style="border: none;text-align: right">身份证号：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px" class="input" type="text" value="${map.idNo}"  name="idNo"/>
                </td>
            </tr>
            <tr style="border: none">
                <td style="border: none;text-align: right">电子邮箱：</td>
                <td style="border: none;text-align: left">
                    <input style="width: 300px" class="input" type="text" value="${map.userEmail}"  name="userEmail"/>
                </td>
                <td style="border: none;text-align: right">附件：</td>
                <td style="border: none;text-align: left">
                    <c:if test="${empty file.filePath}" >
                        <input type="file" name="uploadFile" style="margin-right: 115px" onchange="checkFile(this);"/>
                    </c:if>
                    <c:if test="${not empty file.filePath}" >

                        <input type="hidden" name="certificateUrl" value="${file.filePath}"/>
                        <input type="hidden" id="fileFlow" name="fileFlow" value="${file.fileFlow}"/>
                        <input type="file" id="newFile" name="uploadFile" style="display: none;margin-right: 115px" onchange="checkFile(this);"/>
                        <a style="margin-right: 50px" id="viewImgLink" href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank" title="点击查看大图">
                            <img src="${sysCfgMap['upload_base_url']}/${file.filePath}" width="80px" height="80px"/>
                        </a>
                        <a id="delImgLink" href="javascript:void(0)" onclick="delImg('${file.fileFlow}')" >[删除图片]</a>
                        <a id="reuploadImgLink" href="javascript:void(0)" onclick="reuploadImg()">[重新上传图片]</a>
                    </c:if>
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center">
        <input type="button" class="btn_green" onclick="jboxClose();" value="取&#12288;消"/>
        <input type="button" class="btn_green" onclick="savePlanUserInfo();" value="保&#12288;存"/>
    </div>
</div>

