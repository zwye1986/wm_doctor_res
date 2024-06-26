<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
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
    <script type="text/javascript">
        function nextOpt(step){
            if(false==$("#projForm").validationEngine("validate")){
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            jboxStartLoading();
            form.submit();
        }

        //		$(document).ready(function(){
        //			if($("#mainUserFinRwardProListTb tr").length<=0){
        //				add('mainUserFinRwardProList');
        //			}
        //			if($("#labwareListTb tr").length<=0){
        //				add('labwareList');
        //			}
        //			if($("#mainUserFinDevProListTb tr").length<=0){
        //				add('mainUserFinDevProList');
        //			}
        //		});

        function add(tb){
            var length = $("#"+tb+"Tb").children().length;
            if(length > 9 && "labwareList" != tb){
                jboxTip("限填10项！");
                return false;
            }
            //计算行数
            $("#"+tb+"Num").val(length+1);
            //$("#total"+tb).val(length+1);

            $("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
            var length = $("#"+tb+"Tb").children().length;
            //序号
//			$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
            $("#"+tb+"Tb").children("tr").last().children("td").eq(1).children("input").val(length);
        }

        function delTr(tb){
            //alert("input[name="+tb+"Ids]:checked");
            var checkboxs = $("input[name='"+tb+"Ids']:checked");
            if(checkboxs.length==0){
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?",function () {
                var trs = $('#'+tb+'Tb').find(':checkbox:checked');
                $.each(trs , function(i , n){
                    $(n).parent('td').parent("tr").remove();
                });
                //删除后序号
                var serial = 0;
                $("."+tb+"Serial").each(function(){
                    serial += 1;
//					$(this).text(serial);
                    $(this).children("input").val(serial);
                });
                //计算行数
                var length = $("#"+tb+"Tb").children().length;
                $("#"+tb+"Num").val(length);
                //$("#total"+tb).val(length);
            });
        }

    </script>
    <style type="text/css">
        .bs_tb tbody td input{text-align: left;margin-left: 10px;}
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step9" />
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="8" class="theader">十一、近五年为主编/副主编出版的学术专著共<input type="text" name="totalmainAuthorProList" id="totalmainAuthorProList" value="${resultMap.totalmainAuthorProList}" class="inputText" style="width: 70px;"/>本。（限填10项代表作）
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('mainAuthorProList')"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('mainAuthorProList');"/></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <td width="50px"></td>
                            <td width="50px">序号</td>
                            <td>出版时间</td>
                            <td>专著名称</td>
                            <td>出版社</td>
                            <td>编写字数</td>
                            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                <td>主编</td>
                                <td>副主编</td>
                            </c:if>
                        </tr>
                        <tbody id="mainAuthorProListTb">
                        <c:if test="${not empty resultMap.mainAuthorProList}">
                            <c:forEach var="mainAuthorProList" items="${resultMap.mainAuthorProList}" varStatus="status">
                                <tr>
                                    <td width="50px" style="text-align: center;"><input name="mainAuthorProListIds" type="checkbox"/></td>
                                    <td width="50px" style="text-align: center;" class="mainAuthorProListSerial"><input name="mainAuthorProListSerialNum" type="text" value="${mainAuthorProList.objMap.mainAuthorProListSerialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                                    <td><input type="text" name="mainAuthorProList_date" value="${mainAuthorProList.objMap.mainAuthorProList_date}" class="inputText ctime invalidate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
                                    <td><input type="text" name="mainAuthorProList_name" value="${mainAuthorProList.objMap.mainAuthorProList_name}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="mainAuthorProList_publish" value="${mainAuthorProList.objMap.mainAuthorProList_publish}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="mainAuthorProList_qty" value="${mainAuthorProList.objMap.mainAuthorProList_qty}" class="inputText invalidate[required,custom[number]]" style="width: 90%"/></td>
                                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                    <td><input type="text" name="mainAuthorProList_mainAuthor" value="${mainAuthorProList.objMap.mainAuthorProList_mainAuthor}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="mainAuthorProList_deputyAuthor" value="${mainAuthorProList.objMap.mainAuthorProList_deputyAuthor}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="9" class="theader">十二、近五年学会任职情况，共<input type="text" name="totaluserDeptList" id="totaluserDeptList" value="${resultMap.totaluserDeptList}" class="inputText" style="width: 70px;"/>个职务。（限填10项最高职务）
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('userDeptList')"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('userDeptList');"/></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <td width="50px" rowspan="2"></td>
                            <td width="50px" rowspan="2">序号</td>
                            <td rowspan="2">任期年限</td>
                            <td rowspan="2">学会（分会、学组）名称</td>
                            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                            <td colspan="5">根据职务对应填写姓名</td>
                            </c:if>
                        </tr>
                        <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                        <tr>
                            <td>主委</td>
                            <td>副主委</td>
                            <td>组长</td>
                            <td>常委</td>
                            <td>委员</td>
                        </tr>
                        </c:if>
                        <tbody id="userDeptListTb">
                        <c:if test="${not empty resultMap.userDeptList}">
                            <c:forEach var="userDeptList" items="${resultMap.userDeptList}" varStatus="status">
                                <tr>
                                    <td width="50px" style="text-align: center;"><input name="userDeptListIds" type="checkbox"/></td>
                                    <td width="50px" style="text-align: center;" class="userDeptListSerial"><input name="userDeptListSerialNum" type="text" value="${userDeptList.objMap.userDeptListSerialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                                    <td><input type="text" name="userDeptList_year" value="${userDeptList.objMap.userDeptList_year}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="userDeptList_name" value="${userDeptList.objMap.userDeptList_name}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                    <td><input type="text" name="userDeptList_main" value="${userDeptList.objMap.userDeptList_main}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="userDeptList_deputy" value="${userDeptList.objMap.userDeptList_deputy}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="userDeptList_group" value="${userDeptList.objMap.userDeptList_group}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="userDeptList_gen" value="${userDeptList.objMap.userDeptList_gen}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="userDeptList_pe" value="${userDeptList.objMap.userDeptList_pe}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="7" class="theader">十三、专用实验室设备（限填10万以上的设备）<input type="hidden" name="totallabwareList" id="totallabwareList" class="inputText" style="width: 70px;"/>
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('labwareList')"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('labwareList');"/></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <td width="50px"></td>
                            <td width="50px">序号</td>
                            <td>设备名称</td>
                            <td>规格型号</td>
                            <td>购置时间</td>
                            <td>价格</td>
                            <td>备注</td>
                        </tr>
                        <tbody id="labwareListTb">
                        <c:if test="${not empty resultMap.labwareList}">
                            <c:forEach var="labwareList" items="${resultMap.labwareList}" varStatus="status">
                                <tr>
                                    <td width="50px" style="text-align: center;"><input name="labwareListIds" type="checkbox"/></td>
                                    <td width="50px" style="text-align: center;" class="labwareListSerial"><input name="labwareListSerialNum" type="text" value="${labwareList.objMap.labwareListSerialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                                    <td><input type="text" name="labwareList_name" value="${labwareList.objMap.labwareList_name}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="labwareList_model" value="${labwareList.objMap.labwareList_model}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="labwareList_date" value="${labwareList.objMap.labwareList_date}" class="inputText ctime invalidate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 90%"/></td>
                                    <td><input type="text" name="labwareList_price" value="${labwareList.objMap.labwareList_price}" class="inputText invalidate[required,custom[number]]" style="width: 90%"/></td>
                                    <td><input type="text" name="labwareList_remark" value="${labwareList.objMap.labwareList_remark}" class="inputText invalidate[required]" style="width: 90%"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                        <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
                    </div>
                </form>

                <div style="display: none">
                    <!-- 11.近五年为主编/副主编出版的学术专著模板 -->
                    <table class="basic" id="mainAuthorProListTemplate" style="width: 100%">
                        <tr>
                            <td width="50px" style="text-align: center;"><input name="mainAuthorProListIds" type="checkbox"/></td>
                            <td width="50px" style="text-align: center;" class="mainAuthorProListSerial"><input name="mainAuthorProListSerialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                            <td><input type="text" name="mainAuthorProList_date" class="inputText ctime invalidate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
                            <td><input type="text" name="mainAuthorProList_name" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="mainAuthorProList_publish" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="mainAuthorProList_qty" class="inputText invalidate[required,custom[number]]" style="width: 90%"/></td>
                            <td><input type="text" name="mainAuthorProList_mainAuthor" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="mainAuthorProList_deputyAuthor" class="inputText invalidate[required]" style="width: 90%"/></td>
                        </tr>
                    </table>
                    <!-- 12.近五年学会任职情况模板 -->
                    <table class="basic" id="userDeptListTemplate" style="width: 100%">
                        <tr>
                            <td width="50px" style="text-align: center;"><input name="userDeptListIds" type="checkbox"/></td>
                            <td width="50px" style="text-align: center;" class="userDeptListSerial"><input name="userDeptListSerialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                            <td><input type="text" name="userDeptList_year" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_name" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_main" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_deputy" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_group" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_gen" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="userDeptList_pe" class="inputText invalidate[required]" style="width: 90%"/></td>
                        </tr>
                    </table>
                    <!-- 13.专用实验室设备模板 -->
                    <table class="basic" id="labwareListTemplate" style="width: 100%">
                        <tr>
                            <td width="50px" style="text-align: center;"><input name="labwareListIds" type="checkbox"/></td>
                            <td width="50px" style="text-align: center;" class="labwareListSerial"><input name="labwareListSerialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
                            <td><input type="text" name="labwareList_name" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="labwareList_model" class="inputText invalidate[required]" style="width: 90%"/></td>
                            <td><input type="text" name="labwareList_date" class="inputText ctime invalidate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
                            <td><input type="text" name="labwareList_price" class="inputText invalidate[required,custom[number]]" style="width: 90%"/></td>
                            <td><input type="text" name="labwareList_remark" class="inputText invalidate[required]" style="width: 90%"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
