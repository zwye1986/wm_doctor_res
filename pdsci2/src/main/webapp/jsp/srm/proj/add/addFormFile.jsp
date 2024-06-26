<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ztree" value="true"/>
</jsp:include>
    <script type="text/javascript">
        function saveFormFile() {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var url = "<s:url value='/srm/proj/add/saveFormFile'/>";
            var form = $('#projForm');
            jboxSubmit(form,url,function(resp){
                if(resp =='${GlobalConstant.OPRE_SUCCESSED}') {
                }
//                window.parent.frames['mainIframe'].window.searchProj();
                window.parent.frames['mainIframe'].location.reload(true);
            },null,true);

//            var action = form.attr('action');
//            form.attr("action", action);
//            form.submit();
        }


        $(document).ready(function(){
            if($("#projFileTb tr").length<=0){
                addFile('projFileTb');
            }
        });

        function addFile(tb){
            var myDate = new Date();
            var createTime = myDate.getTime();
            var html = '<tr>'+
                    '<td style="text-align: center;"><input type="checkbox"/></td>'+
                    '<td style="text-align: left;"><input name="fileAddTime" type="hidden" value="'+createTime+'"/>&#12288;<input type="file" name="file" class="validate[required]"/></td>'+
                    '</tr>';
            $('#'+tb).append(html);
        }

        function delTr(tb){
            var trs = $('#'+tb).find(':checkbox:checked');
            jboxConfirm("确认删除？" , function(){
                $.each(trs , function(i , n){
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }
    </script>
<style type="text/css">
    /*.basic tbody th {
        text-align: center;
    }*/
    .readonlycss {
        background-color: #EEEEEE;
    }
</style>
<form action="<s:url value='/srm/proj/add/saveFormFile'/>" method="post" id="projForm" enctype="multipart/form-data">
    <input type="hidden" id="projFlow" name="projFlow" value="${projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${recTypeId}"/>

    <div class="content">
        <table width="100%" cellspacing="0" cellpadding="0" class="bs_tb">
            <tr>
                <th colspan="2" class="theader">${recTypeName}附件
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile('projFileTb')"/></a>&#12288;<a href="javascript:void(0)"><img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('projFileTb')"/></a></span></c:if>
                </th>
            </tr>
            <tr>
                <td width="10%" style="font-weight:bold;"></td>
                <td width="90%" style="font-weight:bold;">附件名称</td>
            </tr>
            <tbody id="projFileTb">
            <c:forEach var="file" items="${pageFileMap}" varStatus="status">
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                        <tr>
                            <td style="text-align: center;">${status.count}</td>
                            <td ><input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="text-align: center;"><input type="checkbox"/></td>
                            <td style="text-align: left;">&#12288;<input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
                        </tr>
                        <tr style="display: none">
                            <td><input type="hidden" name="file_flow" value="${file.key}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="jboxClose()" class="search" value="关&#12288;闭"/>
        <input id="nxt" type="button" onclick="saveFormFile()" class="search" value="添加${recTypeName}"/>
    </div>
</c:if>