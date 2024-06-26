<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red}
    </style>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function doSave(){
            if(false==$("#addForm").validationEngine("validate")){
                return ;
            }
            var courseTypeText = $("select[name='courseTypeId'] option:selected").text();
            $("input[name='courseTypeName']").val(courseTypeText);
            var questionTbody = $('#questionTbody');
            var trs = questionTbody.children();
            var datas = [];
            $.each(trs , function(i , n){
                var detail = $(n).find("input[name='detailJsonData']").val();
                var data = {
                    "questionDetailExt":detail
                };
                datas.push(data);
            });
            var t = {'questionDetailExtList':datas};
            $("#jsondata").val(JSON.stringify(t));
            var url="<s:url value='/xjgl/questionnaire/saveCourseQuestion'/>";
            jboxPost(url,$('#addForm').serialize(),function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxCloseMessager();
                }
            },null,true);
        }
        function addDetail(){
            var url = "<s:url value='/xjgl/questionnaire/showQuestionDetail'/>";
            jboxOpen(url, '题目详情',500,400,true);
        }
        function editThis(obj){
            var temp=$(obj).parent().next().val();
            var url = "<s:url value='/xjgl/questionnaire/showQuestionDetail'/>?flag=edit&jsondata="+encodeURI(encodeURI(temp));
            jboxOpen(url, '题目详情',500,400,true);
        }
        function delThis(obj){
            jboxConfirm("确定删除此题目吗？", function() {
                $(obj).parent().parent().remove();
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm" >
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;" colspan="2">
                        &ensp;问卷名称：<input class="validate[required]" name="questionName" style="width: 500px;text-align: left;"
                                          value="${specialTopic.questionName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left;" colspan="2">
                        &ensp;课程类型：<select class="validate[required]" name="courseTypeId" style="width: 504px;">
                        <c:forEach items="${dictTypeEnumXjCourseTypeList}" var="dict">
                            <option <c:if test="${param.courseTypeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select><input type="hidden" name="courseTypeName">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:center;width: 70%;">问卷题目</td>
                    <td style="text-align:center;"><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addDetail()' title='添加'></td>
                </tr>
                <tbody id="questionTbody">

                </tbody>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="保&#12288;存" onclick="doSave();"/>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
    </div>
</div>
</body>
</html>