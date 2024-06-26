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
    <script type="text/javascript">
        function save(){
            if(!$("#saveForm").validationEngine("validate")){
                return ;
            }
            jboxConfirm("确认提交?",function(){
                jboxPost("<s:url value='/res/liveTraining/saveOpinions'/>",$("#saveForm").serialize(),function(resp){
                    if(resp=='${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
                        jboxTip("保存成功!");
                        location.reload();
                    }else{
                        jboxTip("保存失败!");
                    }
                },null,false);
            });
        }
        function edit(trainingOpinionFlow){
            jboxOpen("<s:url value='/res/liveTraining/editOpinions'/>?trainingOpinionFlow="+trainingOpinionFlow,"编辑意见",500,200);
        }
        function del(trainingOpinionFlow){
        jboxConfirm("确认删除?",function(){
            jboxPost("<s:url value='/res/liveTraining/delOpinions'/>?trainingOpinionFlow="+trainingOpinionFlow,null,function(resp){
                if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                    jboxTip("删除成功!");
                    location.reload();
                }
            },null,false);
        });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="saveForm">
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: center">
                        <span style="font-size: 18px">住培意见反馈</span>
                        <span style="font-size: 12px">(反馈意见仅住培管理员可查看)</span>
                    </th>
                </tr>
                <tr>
                    <td style="padding: 0">
                        <textarea class="validate[required]" placeholder="输入你的反馈意见!" name="opinionUserContent" style="width: 100%;height: 100px;"></textarea>
                    </td>
                </tr>
            </table>
            <p style="text-align: right"><input type="button" onclick="save();" class="search" value="提&#12288;交"/></p>
        </form>
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th colspan="3">
                   历史意见
                </th>
            </tr>
            <tr>
                <th style="width: 5%">序号</th>
                <th style="width: 60%">内容</th>
                <th style="width: 5%">操作</th>
            </tr>
            <c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
                <tr>
                    <th rowspan="2">${status.index+1}</th>
                    <td style="text-align: left">${trainingOpinion.opinionUserContent}</td>
                    <td>
                        <c:if test="${empty trainingOpinion.opinionReplyContent}">
                            <a style="cursor: pointer;color: blue;" onclick="edit('${trainingOpinion.trainingOpinionFlow}')">编辑</a>
                            <a style="cursor: pointer;color: blue;" onclick="del('${trainingOpinion.trainingOpinionFlow}')">删除</a>
                        </c:if>
                        <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                            管理员已回复
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: left">
                        管理员回复:${trainingOpinion.opinionReplyContent}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
