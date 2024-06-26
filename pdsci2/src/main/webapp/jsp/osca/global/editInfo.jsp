
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function saveSetDept(orgFlow){
            var count =0;
            var data = [];
//            $("input[type='checkbox']:checked").each(function(){
//                console.log(this);
//                var chVal=$(this).attr("value");
//                data=data+"/pdnsp/"+chVal;
//                count=count+1;
//            });
            var obj = document.getElementsByName("setDept");
            for(var k in obj){
                if(obj[k].checked) {
                    data.push(obj[k].value);
                    count=count+1;
                }
            }
            console.log(data);
            if(count==0){
                $(".log_tips").html("未勾选专业无法保存!");
                return false;
            }
            var url = "<s:url value='/osca/orgSpeGlobal/saveEditInfo'/>?orgFlow="+orgFlow+"&data="+data;
            jboxPost(url,null, function(resp) {
                if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].location.reload(true);
                    jboxClose();
                }else{
                    jboxTip(resp);
                }
            },null,false);

        }

        //模糊查询
        function likeSearch(name) {
            if (name) {
                $("[speName]").hide();
                $("[speName*='" + name + "']").show();
            } else {
                $("[speName]").show();
            }
        }
    </script>
</head>
<body>
    <div style="width: 100%;height: 40px">
        <span>查找：</span>
        <input placeholder="按专业查找" type="text" value="" style="width: 60%" onkeyup="likeSearch(this.value);"/><br/>
    </div>
    <div id="base" style="overflow: auto;height:75%">
        <table  style="border:1px solid #e3e3e3; width:100%;" align="center">
            <c:if test="${not empty oscaOrgSpeExtList}">
                <c:forEach items="${oscaOrgSpeExtList}" var="orgSpe">
                    <tr speName="${orgSpe.speName}">
                        <td style="text-align: left; border-right:1px solid #e3e3e3; border-bottom:1px solid #e3e3e3;line-height:25px; height:30px;">
                            &#12288;&#12288;
                            <input name="setDept" value="${orgSpe.speId}" <c:if test="${orgSpe.asMajor eq GlobalConstant.RECORD_STATUS_Y}">checked="checked"</c:if> type="checkbox" />
                            &#12288;${orgSpe.speName}&#12288;&#12288;
                        </td>
                    </tr>
               </c:forEach>
            </c:if>
        </table>
    </div>
    <div>
        <font class="log_tips" style="color: red"></font>
        <br/>
        <p align="center" style="margin-top: 10px;">
            <input type="button" id="saveBtn" value="保&#12288;存" onclick="saveSetDept('${orgFlow}');" class="search"/>
            <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
        </p>
    </div>
</body>
</html>
