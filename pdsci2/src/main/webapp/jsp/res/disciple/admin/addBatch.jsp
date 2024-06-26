<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
    </jsp:include>
    <style type="text/css">
        .xllist td{
            text-align: left;height: 35px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){

        });

        function save(discipleTypeId,obj) {
            var trainingTypeId = $("#trainingTypeId").val();
            var sessionNumber = $("#sessionNumber").val();
            if(!trainingTypeId){
                jboxTip("请选择培训专业！");
                $(obj).val("");
                return;
            }
            if(!sessionNumber){
                jboxTip("请选择年级！");
                $(obj).val("");
                return;
            }
            var reqNumber = $(obj).val();
            var url = "<s:url value='/res/discipleAdminAudit/updateDiscipleReq?discipleTypeId='/>" + discipleTypeId+ "&reqNumber=" + reqNumber;
            var data = $('#editForm').serialize();
            jboxStartLoading();
            jboxPost(url, data, function(resp) {
                if(resp == 'cannotInsert'){
                    jboxTip("该配置项要求数已存在请检查！");
                    jboxEndLoading();
                    return false;
                }
                jboxTip("操作成功！");
            }, null, false);
        }


        function myClose(){
            window.parent.frames['mainIframe'].toPage(1);
            jboxClose();
        }
    </script>
</head>
<body onunload="alert('The onunload event was triggered')">
<div class="infoAudit">
    <form id="editForm" style="position: relative;padding-top: 20px;" method="post">
        <div id="infoDiv" class="div_table" style="padding-top: 5px;">
            <input type="hidden" id="orgFlow" name="orgFlow" value="${currentOrg.orgFlow }"/>
            <table class="xllist">
                <tbody>
                <tr>
                    <th>培训专业：</th>
                    <td>&nbsp;
                        <select id="trainingTypeId" name="trainingTypeId" class="validate[required] qselect"  >
                            <option value="">--请选择--</option>
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                    <option value="${category.id}">${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <th>年&#12288;&#12288;级：</th>
                    <td>&nbsp;
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy'})" id="sessionNumber" name="sessionNumber" class="validate[required] qtext" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">记录类型：</th>
                    <th colspan="2">年要求数：</th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        跟师记录
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('FollowTeacherRecord',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        学习笔记
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('Note',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        跟师心得
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('Experience',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        医籍学习记录
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('BookRecord',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        医籍学习体会
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('BookExperience',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        跟师医案
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('TypicalCases',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        年度考核表
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('AnnualAssessment',this)"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        结业考核表
                    </td>
                    <td colspan="2" style="text-align: center;">&nbsp;
                        <input type="text" name="reqNumber" class="validate[required ,custom[integer]] qtext" onchange="save('GraduationAssessment',this)"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="button">
                <input type="button" class="search" onclick="myClose();" value="关&#12288;闭"/>
            </div>
        </div>
    </form>
</div>

</body>
</html>