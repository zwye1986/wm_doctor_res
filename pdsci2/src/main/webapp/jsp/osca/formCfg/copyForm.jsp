<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <style>
        a{color:#4195c5}
    </style>
    <script>
        function save(){
            if($("#fromName").val()==null || $("#fromName").val()==''){
                top.jboxTip("请填写表单名称！");
                return false;
            }
            jboxConfirm("确定复制吗？",function(){
                var url = '<s:url value="/osca/formCfg/toCopyFrom"/>';
                jboxPost(url,$("#contentForm").serialize(),function(resp){
                    if(resp == '${GlobalConstant.DUPLICATE_NAME}'){
                        top.jboxTip("表单名称已存在！");
                    }else if (resp =='${GlobalConstant.OPRE_SUCCESSED}'){
                        top.jboxTip("操作成功！");
                        window.parent.frames['mainIframe'].window.search();
                    }else if (resp == '${GlobalConstant.OPRE_FAIL}'){
                        top.jboxTip("操作失败！");
                    }else {
                        top.jboxTip("网络异常，请稍后再试！");
                    }
                },null,false);

            });
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content" style="text-align: center;">
        <form id="contentForm" method="post">
            <input type="hidden" name="fromFlow" value="${fromFlow}">
            <input type="hidden" name="type" value="${type}">
            <br/><br/>
            表单名称：<input type="text" id="fromName" name="fromName" style="min-width: 270px;max-width: 280px;"
                        value="${fromName}" class="xltext validate[required,maxSize[50]]">
        </form>
    </div>
    <div style="text-align: center;margin-top: 30px">
        <input type="button" class="search" value="保&#12288;存" onclick="save()">
    </div>
</div>
</body>
</html>
