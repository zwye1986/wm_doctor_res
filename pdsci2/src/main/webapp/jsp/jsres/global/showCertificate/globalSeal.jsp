<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	#div_table_4 table th,#div_table_4 table td{
		text-align: center;
	}
</style>
<script type="text/javascript">

    function chooseFile(){
        return $("#file").click();
    }
    $(function(){
        $("#file").live("change",function(){
            uploadFile();
        });
    });
    function uploadFile(){
        if(false == $("#fileForm").validationEngine("validate")){
            return false;
        }
        jboxStartLoading();
        var checkResult = checkFile($("#file")[0]);
        if(!checkResult){
            jboxEndLoading();
            return false;
        }
        var url = "<s:url value='/jsres/certificateManage/uploadSealFile'/>";
        jboxSubmit($("#fileForm"),url,function(resp){
            if("${GlobalConstant.UPLOAD_FAIL}" != resp){
                jboxEndLoading();
                var index = resp.indexOf("/");
                if(index != -1){
                    returnUrl(resp);
                }else{//验证文件信息
                    jboxInfo(resp);
                }
            }
        }, null, false);
    }
    /**
     * 检查文件格式
     */
    function checkFile(file){
        var filePath = file.value;
        var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
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
            jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
            return false;
        }else{
            return true;
        }
    }
    function returnUrl(filePath){
        flag = "Y";
        $("a[name='chooseFile']").text("重新上传");
//	$("#asseValue").val(filePath);
        var filePath = "${sysCfgMap['upload_base_url']}" +"/" + filePath;
        $("#filePath").val(filePath);
        var html="<a href='"+filePath+"' target='_blank'>" +
            "<img src='"+filePath+"' style='width: 100%;height: 90%;'>	</a>"+
            '<a style="margin-left: 110px;"  href="javascript:chooseFile();" >重新上传</a>';
        $("#publicImg").html(html);
        $("#fileSpan").show();
        $("#fileDelSpan").show();
        $("#fileSpan").find("a").attr('href',filePath);
    }

</script>
<div class="search_table" style="margin-top:20px;">
	<div class="main_bd" id="div_table_3" style="margin-top: 15px">
		<form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="text" name="productFlow" value="${userFlow}" style="display: none;"/>
			<input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
		</form>
		<h4 style="margin-bottom: 15px;">印章管理 </h4>
		<div style="width: 280px;height: 200px" id="publicImg">
			<c:if test="${not empty file.filePath}">
				<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">
					<img src="${sysCfgMap['upload_base_url']}/${file.filePath}" style="width: 90%;height: 90%;">
				</a>
				<a style="margin-left: 90px;" name="chooseFile"
				   href="javascript:chooseFile();">重新上传</a>
			</c:if>
			<c:if test="${ empty file.filePath}">
				<input id="filePath" type="hidden" value="${file.filePath}">
				<c:if test="${empty file}">
					<a  href="javascript:chooseFile();" >
						<img width="280px" height="180px"  src="<s:url value='/jsp/inx/jsres/images/add.png'/>">
					</a>&nbsp;
				</c:if>
			</c:if>
		</div>
	</div>
</div>
