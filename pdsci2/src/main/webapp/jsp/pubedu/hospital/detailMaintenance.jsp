<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<%@ taglib uri="http://www.njpdkj.com/pdfn" prefix="pdfn"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/pubedu/css/basic.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="false"/>
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
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function hideObj(objName){
            $(objName).hide();
            $("#operation").html("");
        }
        function submitAddForm(){
            var addForm = $("#addForm");
            if(addForm.validationEngine("validate")) {
                var url = "<s:url value='/pubedu/hospital/saveCourseDetailInfo'/>";
            jboxSubmit($("#addForm"),url,function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    location.reload();
                    jboxClose();
                }
            },null,true)
            }
        }
        function submitEditForm(){
            var editForm = $("#editForm");
            var flag = $("#editForm input[name='flag']").val();
            var type = $("#editForm input[name='type']").val();
            if(editForm.validationEngine("validate")){
                var url = "<s:url value='/pubedu/hospital/saveCourseDetail'/>?flag="+flag+"&type="+type;
                jboxSubmit($("#editForm"),url,function(resp){
                    if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                        location.reload();
                        jboxClose();
                    }
                },null,true)

            }
        }

        function add(courseFlow,detailFlow){
            $("#operation").html("一添加资料");
            $("#editTable").hide();
            $("#addTable").show();
            $("#addTable input[name='courseFlow']").val(courseFlow);
            $("#addTable input[name='detailFlow']").val(detailFlow);
        }

        function edit(type,detailTypeId,detailTypeName,flag,recordFlow,courseFlow,detailFlow,detailName,detailUrl,pagerNumber){
            var editTable = $("#editTable");
            var typeName;
            if(type=='DataPPT'){
                typeName='一编辑资料PPT';
            }else if (type=='DataHandout'){
                typeName='一编辑资料讲义';
            }else if(type=='ChapterVideo'){
                typeName='一编辑资料视频';
            }else if(type=='CoursePPT'){
                typeName='一编辑课程PPT';
            }else if(type=='ChapterHandout'){
                typeName='一编辑课程讲义';
            }

            $("#operation").html(typeName);
            $("#addTable").hide();
            editTable.show();
            $("#editTable input[name='type']").val(type);
            $("#editTable input[name='courseFlow']").val(courseFlow);
            $("#editTable input[name='detailFlow']").val(detailFlow);
            $("#editTable input[name='recordFlow']").val(recordFlow);
            $("#editTable input[name='detailTypeId']").val(detailTypeId);
            $("#editTable input[name='detailTypeName']").val(detailTypeName);
            $("#editTable input[name='detailName']").val(detailName);
            $("#editTable input[name='detailUrl']").val(detailUrl);
            $("#editTable input[name='pagerNumber']").val(pagerNumber);
            $("#editTable input[name='flag']").val(flag);

            var typeValue =$("#editTable input[name='type']").val();

            if((typeValue=='DataPPT')||(typeValue=='CoursePPT')){
                $("#page2").show();
            }else {
                document.getElementById("page2").style.display = "none";

            }
        }
        /**
         * 清空课程
         * @param detailFlow
         * @param courseFlow
         * @param detailTypeId
         * @param detailTypeName
         */
        function delDetailCourse(detailFlow,courseFlow,detailTypeId,detailTypeName){
            if(detailFlow){
                jboxConfirm("确认删除此条记录?", function(){
                    var url = "<s:url value='/pubedu/hospital/deleCourseDetail'/>?detailFlow="+detailFlow+"&courseFlow="+courseFlow+"&detailTypeId="+detailTypeId+"&detailTypeName="+encodeURI(encodeURI(detailTypeName));
                    jboxPost(url, null, function(resp){
                        if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                            location.reload(); //重新加载当前文档
                        }
                    }, null, false);
                });
            }else{
                jboxTip("没有可以删除的信息！");
            }

        }
        function del(recordFlow){
            jboxConfirm("确认删除此条记录?", function(){
                var url = "<s:url value='/pubedu/hospital/deleCourseDetailInfo'/>?recordFlow="+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload(); //重新加载当前文档
                    }
                }, null, false);
            });
        }


        function hidePageNum(){
            if($("#addTable #detailTypeId").find('option:selected').text()!='资料PPT'){
                document.getElementById("page").style.display = "none";
            }else{
                $("#page").show();
            }
        }

    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div class="clearfix">
                <table width="100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="top" width="450">
                            <table width="450" cellspacing="0" cellpadding="0">
                                <tbody class="tree">
                                <tr>
                                    <th><h3 class="maintain">课程维护</h3></th>
                                </tr>
                                <tr>
                                    <td>
                                        <div>
                                                <dl>
                                                    <dt>
                                                        <a href=""><img class="xz" src="<s:url value='/jsp/pubedu/images/basic/pic1.png'/>" >${courseName}</a></dt>
                                                    <dd>
                                                        <img class="pic" src="<s:url value='/jsp/pubedu/images/basic/pic2.png'/> ">
                                                        <a class="cls" href="">课程PPT</a>
                                                        <a class="edit" onclick="edit('CoursePPT','${detailMap[courseDetailTypeEnumCoursePPT.id].detailTypeId}','${detailMap[courseDetailTypeEnumCoursePPT.id].detailTypeName}','detail','','${courseFlow}','${detailMap[courseDetailTypeEnumCoursePPT.id].detailFlow}','${detailMap[courseDetailTypeEnumCoursePPT.id].detailName}','${pdfn:encodeString2(detailMap[courseDetailTypeEnumCoursePPT.id].detailUrl)}','${detailMap[courseDetailTypeEnumCoursePPT.id].pagerNumber}')">
                                                            <img src="<s:url value='/jsp/pubedu/images/basic/edit.png'/> ">
                                                        </a>
                                                        <a class="" onclick="delDetailCourse('${detailMap[courseDetailTypeEnumCoursePPT.id].detailFlow}','${courseFlow}','${detailMap[courseDetailTypeEnumCoursePPT.id].detailTypeId}','${detailMap[courseDetailTypeEnumCoursePPT.id].detailTypeName}')">
                                                            <img src="<s:url value='/jsp/pubedu/images/basic/clear.png'></s:url> ">
                                                        </a>
                                                    </dd>

                                                    <dd>
                                                        <img class="pic" src="<s:url value='/jsp/pubedu/images/basic/pic2.png'/> ">
                                                        <a class="cls" href="">课程讲义</a>
                                                        <a class="edit" onclick="edit('ChapterHandout','${detailMap[courseDetailTypeEnumChapterHandout.id].detailTypeId}','${detailMap[courseDetailTypeEnumChapterHandout.id].detailTypeName}','detail','','${courseFlow}','${detailMap[courseDetailTypeEnumChapterHandout.id].detailFlow}','${detailMap[courseDetailTypeEnumChapterHandout.id].detailName}','${pdfn:encodeString2(detailMap[courseDetailTypeEnumChapterHandout.id].detailUrl)}','${detailMap[courseDetailTypeEnumChapterHandout.id].pagerNumber}')">
                                                            <img src="<s:url value='/jsp/pubedu/images/basic/edit.png'/> ">
                                                        </a>
                                                        <a class="" onclick="delDetailCourse('${detailMap[courseDetailTypeEnumChapterHandout.id].detailFlow}','${courseFlow}','${detailMap[courseDetailTypeEnumChapterHandout.id].detailTypeId}','${detailMap[courseDetailTypeEnumChapterHandout.id].detailTypeName}')">
                                                            <img src="<s:url value='/jsp/pubedu/images/basic/clear.png'></s:url> ">
                                                        </a>
                                                    </dd>

                                                    <dd>
                                                        <img class="xz" src="<s:url value='/jsp/pubedu/images/basic/pic1.png'/> ">
                                                        <a class="cls" href="">课程资料</a>
                                                        <a class="add" onclick="add('${courseFlow}','${detailMap[courseDetailTypeEnumCourseData.id].detailFlow}')">
                                                            <img src="<s:url value='/jsp/pubedu/images/basic/add.png'/> ">
                                                        </a>
                                                    </dd>

                                                    <dl style="padding-left:15px">
                                                        <dd>
                                                            <img class="pic" src="<s:url value='/jsp/pubedu/images/basic/pic2.png'/> ">
                                                            <a class="cls" href="">资料-PPT</a>
                                                            </br>
                                                            <c:forEach items="${detailInfoCoursePPT}" var="CoursePPT">
                                                                <span style="padding-left: 15px">${CoursePPT.detailName}</span>
                                                                <a class="edit" onclick="edit('DataPPT','${CoursePPT.detailTypeId}','${CoursePPT.detailTypeName}','detailInfo','${CoursePPT.recordFlow}','${CoursePPT.courseFlow}','${CoursePPT.detailFlow}','${CoursePPT.detailName}','${pdfn:encodeString2(CoursePPT.detailUrl)}','${CoursePPT.pagerNumber}')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/edit.png'/> ">
                                                                </a>
                                                                <a class="" onclick="del('${CoursePPT.recordFlow}')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/clear.png'></s:url> ">
                                                                </a>
                                                                </br>
                                                            </c:forEach>
                                                        </dd>

                                                        <dd>
                                                            <img class="pic" src="<s:url value='/jsp/pubedu/images/basic/pic2.png'/> ">
                                                            <a class="cls" href="">资料-讲义</a>
                                                            </br>
                                                            <c:forEach items="${detailInfoChapterHandout}" var="ChapterHandout">
                                                                <span style="padding-left: 15px">${ChapterHandout.detailName}</span>
                                                                <a class="edit" onclick="edit('DataHandout','${ChapterHandout.detailTypeId}','${ChapterHandout.detailTypeName}','detailInfo','${ChapterHandout.recordFlow}','${ChapterHandout.courseFlow}','${ChapterHandout.detailFlow}','${ChapterHandout.detailName}','${pdfn:encodeString2(ChapterHandout.detailUrl)}','${ChapterHandout.pagerNumber}')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/edit.png'/> ">
                                                                </a>
                                                                <a class="" onclick="del('${ChapterHandout.recordFlow}')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/clear.png'></s:url> ">
                                                                </a>
                                                                </br>
                                                            </c:forEach>

                                                        </dd>

                                                        <dd>
                                                            <img class="pic" src="<s:url value='/jsp/pubedu/images/basic/pic2.png'/> ">
                                                            <a class="cls" href=""> 资料-视频</a>
                                                            </br>
                                                            <c:forEach items="${detailInfoChapterVideo}" var="ChapterVideo">
                                                                <span style="padding-left: 15px">${ChapterVideo.detailName}</span>
                                                                <a class="edit" onclick="edit('ChapterVideo','${ChapterVideo.detailTypeId}','${ChapterVideo.detailTypeName}','detailInfo','${ChapterVideo.recordFlow}','${ChapterVideo.courseFlow}','${ChapterVideo.detailFlow}','${ChapterVideo.detailName}','${pdfn:encodeString2(ChapterVideo.detailUrl)}','')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/edit.png'/> ">
                                                                </a>
                                                                <a class="" onclick="del('${ChapterVideo.recordFlow}')">
                                                                    <img src="<s:url value='/jsp/pubedu/images/basic/clear.png'></s:url> ">
                                                                </a>
                                                                </br>
                                                            </c:forEach>
                                                        </dd>
                                                    </dl>

                                                </dl>


                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                        <td width="10">
                        </td>
                        <td valign="top">
                            <table width="450" cellspacing="0"
                                   cellpadding="0" class="bs_tb">
                                <tr>
                                    <th colspan="11" class="bs_name">相关操作<span id="operation"></span></th>
                                </tr>
                                <tr>
                                    <td>
                                        <form id="addForm">

                                            <table class="basic" style="display: none;" id="addTable">

                                                <tr>
                                                    <th>资料类型：</th>
                                                    <td>
                                                        <input type="hidden" name="courseFlow" value="">
                                                        <input type="hidden" name="detailFlow" value="">
                                                        <select  id="detailTypeId" name="detailTypeId" class="validate[required] xlselect" onblur="hidePageNum()">
                                                            <option value="">请选择</option>
                                                            <option value="${courseDetailTypeEnumDataPPT.id}">资料PPT</option>
                                                            <option value="${courseDetailTypeEnumDataHandout.id}">资料讲义</option>
                                                            <option value="${courseDetailTypeEnumChapterVideo.id}">资料视频</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>资料名称：</th>
                                                    <td>
                                                        <input type="text" id="detailName" name="detailName" value="${param.detailName}" class="validate[required] text-input xltext">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>资料地址：</th>
                                                    <td><input type="text" id="detailUrl" name="detailUrl" value="${param.detailUrl}" class="validate[required] xltext"></td>
                                                </tr>

                                                <tr id="page" style="display:none">
                                                    <th>总页数</th>
                                                    <td><input type="text" id="pagerNumber" name="pagerNumber" value="${param.pagerNumber}" class="validate[required,custom[number],min[1]] xltext"></td>
                                                </tr>

                                                <tr>
                                                    <td colspan="2"><input type="button" class="search" value="保 存" onclick="submitAddForm()">&#12288;&#12288;<input type="button" value="取 消" class="search"  onclick="hideObj('#addTable')"></td>
                                                </tr>
                                            </table>
                                        </form>
                                        <form id="editForm">

                                            <table class="basic" style="display: none;" id="editTable">
                                                <tr>
                                                    <th>资料名称：</th>
                                                    <td>
                                                        <input type="hidden" name="type" value="">
                                                        <input type="hidden" name="courseFlow" value="">
                                                        <input type="hidden" name="detailFlow" value="">
                                                        <input type="hidden" name="flag" value="">
                                                        <input type="hidden" name="recordFlow" value="">
                                                        <input type="hidden" name="detailTypeId" value="">
                                                        <input type="hidden" name="detailTypeName" value="">
                                                        <input type="text" id="detailName" name="detailName" value="${param.detailName}" class="validate[required] text-input xltext">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>资料地址：</th>
                                                    <td><input type="text" id="detailUrl" name="detailUrl" class="validate[required] xltext"></td>
                                                </tr>
                                                <tr id="page2" style="display: none">
                                                    <th>总页数</th>
                                                    <td><input type="text" id="pagerNumber" name="pagerNumber" class="validate[required,custom[number],min[1]] xltext"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2"><input type="button" class="search" value="保 存" onclick="submitEditForm()">&#12288;&#12288;<input type="button" value="取 消" class="search"  onclick="hideObj('#editTable')"></td>
                                                </tr>
                                            </table>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>