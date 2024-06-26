<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function() {
            $("#consultQuestion").on("input propertychange", function() {
                var $this = $(this),
                    _val = $this.val(),
                    count = "";
                if(_val.length > 500) {
                    $this.val(_val.substring(0, 500));
                }
                count = 500 - $this.val().length;
                $("#text-count").text(count);
            });
        })

        function loadConsultSonType() {
            document.getElementById("zxType").style.visibility="visible ";//显示
            //获取一级单选框id
            var consultTypeId =  $('input[name="consultTypeId"]:checked').val();
            if(consultTypeId=='' || consultTypeId==null){
                $("#consultTypeSonDiv").empty();
                return;
            }
            //每次点击前，将所有二级单选框清空
            $("#consultTypeSonDiv").empty();
            var title=document.getElementById("consultTypeSonDiv");
            var consultTypeId = 'ConsultType.'+$('input[name="consultTypeId"]:checked').val();
            if (consultTypeId) {
                var url = "<s:url value='/jsres/consult/loadTitle'/>?consultTypeId=" + consultTypeId;
                $.ajax({
                    type : "get",
                    url : url,
                    cache : false,
                    success : function (data) {
                        $("#consultTypeSonDiv").empty();
                        for (var i=0;i<data.length;i++) {
                            title.innerHTML += '<input type="radio" name="consultTypeSonId" id='+ data[i].dictId +' value=' + data[i].dictId + '>';
                            title.innerHTML += '<label for='+ data[i].dictId +'>'+data[i].dictName+'</label>';
                        }
                    }
                })
            }
        }

        function addConsultInfo(){
            if(false==$("#consultInfoForm").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/jsres/consult/addConsultInfo'/>";
            var consultInfo = $("#consultInfoForm").serialize();
            jboxPost(url,consultInfo,function (resp) {
                if ("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                    jboxTip("提交成功!");
                    window.parent.toPage(1)
                    setTimeout(function(){jboxClose()},1000);
                }else {
                    jboxTip("提交失败!");
                }
            }, null, false);
        }

        function doClose() {
            top.jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright">
    <form id="consultInfoForm" method="post" style="height: 450px;overflow-y: auto;">
        <div class="content bordbox" style="padding: 0px 19px;">
            <div class="flex consult-ques">
                <div  style="min-width: 50px">
                    <label>
                        问题:
                    </label>
                </div>
                <div class="consult-textbox" >
                    <textarea name="consultQuestion" id="consultQuestion"  maxlength="500"  class="validate[required] gp-textarea" placeholder="请输入您的问题!" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的问题!'" ></textarea>
                    <div class="fs14 gp-tip"><span id="text-count">500</span>/500</div>
                </div>
            </div>
            <div class="consult-style">
                <label>请选择所问问题类型:</label>
                <div id="consultTypeDiv">
                    <c:forEach items="${dictTypeEnumConsultTypeList}" var="dict" varStatus="status">
                        <c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
                        <input class="consultTypeRadio" type="radio" id="${dict.dictId}" value="${dict.dictId}" name="consultTypeId" onclick="loadConsultSonType()">
                        <label for="${dict.dictId}">${dict.dictName}</label>
                    </c:forEach>
                </div>
            </div>
            <div  class="consult-style">
                <div class="td_left" id="zxType" style="visibility:hidden;">请选择具体类型：</div>
                <div id="consultTypeSonDiv"></div>
            </div>
        </div>
        <div align="center " class="consult-close">
            <input type="button" value="提&#12288;交" class="btn_green" onclick="addConsultInfo();"/>
            <input type="button" value="关&#12288;闭" class="btn_green" onclick="doClose();"/>
        </div>
    </form>
</div>
</body>
</html>