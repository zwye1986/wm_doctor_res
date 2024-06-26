<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function daoRu(){
            if(false==$("#excelForm").validationEngine("validate")){
                return false;
            }
            var checkFileFlag = $("#checkFileFlag").val();
            if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
                jboxTip("请上传Excel文件！");
                return false;
            }
            jboxStartLoading();
            var url = "<s:url value='/nyzl/retestStudent/importRetestStudent'/>";
            jboxSubmit(
                    $('#excelForm'),
                    url,
                    function(resp){
                        top.jboxInfo(resp);
                        if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
                            window.parent.frames['mainIframe'].location.reload();
                            jboxClose();
                        }
                    },
                    function(resp){
                        top.jboxEndLoading();
                        top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                    },false);
        }

        function checkFile(file){
            var filePath = file.value;
            var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
            if("xlsx" == suffix || "xls" == suffix){
                $("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
            }else{
                $("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
                $(file).val(null);
                jboxTip("请上传Excel文件");
            }
        }
    </script>
</head>
<body>
    <form id="excelForm">
        <input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
        <input type="hidden" name="stuSignFlag" value="${stuSignFlag}"/>
        <input type="hidden" name="adminFlag" value="${adminFlag}"/>
        <div class="mainright" style="text-align: center;">
            <a href="<s:url value='/jsp/nyzl/temeplete/importDoctoralInfo1.xls'/>">[全日制博士生导入模板下载]</a>
            <a href="<s:url value='/jsp/nyzl/temeplete/importDoctoralInfo0.xls'/>">[在职博士生导入模板下载]</a>
        <br/><br/>
            博士类型:
            <select name="educationTypeId" class="validate[required] xlselect">
                <option value="" >请选择</option>
                <option value="1">全日制</option>
                <option value="0">在职</option>
            </select>
            <p>
        </p>
        <br>
            批次选择:
            <select name="swapBatchId" class="xlselect">
                <option value="">请选择</option>
                <c:forEach items="${dictTypeEnumNyzlBatchList}" var="batch">
                    <option value="${batch.dictId}">${batch.dictName}</option>
                </c:forEach>
            </select>
            <p>
        </p>
        <br>
            请选择导入：<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/><br>
        <br>
        <input type="button" class="search" onclick="daoRu();" value="导&#12288;入">
        <input type="button" class="search" onclick="jboxClose()" value="关&#12288;闭">
    </div>
    </form>
</body>
</html>
