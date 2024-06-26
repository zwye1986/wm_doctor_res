<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step){
            if(false==$("#projForm").validationEngine("validate")){
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            jboxStartLoading();
            form.submit();
        }


        function delTr(tb){
            var trs = $('#'+tb).find(':checkbox:checked');
            if(trs.length==0){
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除？" , function(){
                $.each(trs , function(i , n){
                    $(n).parent('td').parent("tr").remove();

                });

            });
        }

        function add(templeteId){
            if (templeteId) {
                $('.'+templeteId).append($('#'+templeteId).clone().attr("id",""));
            }
        }

        function del(templeteId) {
            if (templeteId) {
                if (!$('.' + templeteId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templeteId + ' .toDel:checked').closest('tr').remove();
                }, null);
            }
        }
    </script>
</c:if>
<style type="text/css">
    .borderNone{border-bottom-style: none;}
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step3" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
    <c:if test="${not empty projRec.pageGroupName}">
        <input type="hidden"  name="pageGroupName" value="${projRec.pageGroupName}"/>
    </c:if>
    <font style="font-size: 14px; font-weight:bold;color: #333; ">项目组成员变更附表</font>
    <table class="basic" style="width: 100%">
        <tr>
            <p style="color: red;font-size: 12px">&#12288;注：<br/>&#12288;&#12288;1.已立项的课题，原则上不予变更课题组主要成员。如实施过程中确有需要变更，必须有充分的变更理由。第一负责人变更应按照规定流程向项目主管部门提交变更申请，其他主要成员变更应向科技处提交变更申请。
                <br/>&#12288;&#12288;2.课题组主要成员变更原则上应是课题组原有成员（以主管部门批准的任务书或合同书为准）或在实施过程中新引进人才。
                <br/>&#12288;&#12288;3.课题组主要成员应参与至少三分之二以上的课题实施过程，因此，成员变更应在项目中期考核前提交申请。
                <br/>&#12288;&#12288;4.原有成员调整顺序按实际工作贡献大小，新增人员排末尾，涉及变更的课题组主要成员均需在《变更申请表》上签字确认，经院内公示无异议后，在科技处备案方可变更。</p>
        </tr>
    </table>
    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="6">
                退出人员
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('outmemberList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('outmemberList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
            <th width="4%" style="text-align: center">选择</th>
            </c:if>
            <th width="15%" style="text-align: center">退出人员姓名</th>
            <th width="10%" style="text-align: center">排名</th>
            <th width="30%" style="text-align: center">变更理由</th>
            <%--<th width="15%" style="text-align: center">本人签名</th>--%>
            <th width="26%" style="text-align: center">备注</th>
        </tr>
        <tbody class="outmemberList">
        <c:forEach var="outmemberList" items="${resultMap.outmemberList}" varStatus="outmemberListStatus">
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="outmemberList_name"
                               value="${outmemberList.objMap.outmemberList_name}" style="width: 90%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="outmemberList_ranking"
                               value="${outmemberList.objMap.outmemberList_ranking}" style="width: 90%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="outmemberList_reason"
                               value="${outmemberList.objMap.outmemberList_reason}" style="width: 90%"/>
                    </td>
                    <%--<td>
                        <input type="text" class="inputText &lt;%&ndash;validate[required]&ndash;%&gt;" name="outmemberList_sign"
                               value="${outmemberList.objMap.outmemberList_sign}" style="width: 90%"/>
                    </td>--%>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="outmemberList_memo"
                               value="${outmemberList.objMap.outmemberList_memo}" style="width: 90%"/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                <tr>
                    <td>${outmemberList.objMap.outmemberList_name}</td>
                    <td>${outmemberList.objMap.outmemberList_ranking}</td>
                    <td>${outmemberList.objMap.outmemberList_reason}</td>
                    <%--<td>${outmemberList.objMap.outmemberList_sign}</td>--%>
                    <td>${outmemberList.objMap.outmemberList_memo}</td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="6">
                变更后成员
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('changememberList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('changememberList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
            <th width="4%" style="text-align: center">选择</th>
            </c:if>
            <th width="15%" style="text-align: center">变更后全部人员姓名</th>
            <th width="10%" style="text-align: center">排名</th>
            <th width="30%" style="text-align: center">承担主要研究任务</th>
            <%--<th width="15%" style="text-align: center">本人签名</th>--%>
            <th width="26%" style="text-align: center">备注</th>
        </tr>
        <tbody class="changememberList">
        <c:forEach var="changememberList" items="${resultMap.changememberList}" varStatus="changememberListStatus">
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="changememberList_name"
                               value="${changememberList.objMap.changememberList_name}" style="width: 90%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[<%--required,--%>custom[number]]" name="changememberList_ranking"
                               value="${changememberList.objMap.changememberList_ranking}" style="width: 90%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="changememberList_mainTask"
                               value="${changememberList.objMap.changememberList_mainTask}" style="width: 90%"/>
                    </td>
                    <%--<td>
                        <input type="text" class="inputText &lt;%&ndash;validate[required]&ndash;%&gt;" name="changememberList_sign"
                               value="${changememberList.objMap.changememberList_sign}" style="width: 90%"/>
                    </td>--%>
                    <td>
                        <input type="text" class="inputText <%--validate[required]--%>" name="changememberList_memo"
                               value="${changememberList.objMap.changememberList_memo}" style="width: 90%"/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                <tr>
                    <td>${changememberList.objMap.changememberList_name}</td>
                    <td>${changememberList.objMap.changememberList_ranking}</td>
                    <td>${changememberList.objMap.changememberList_mainTask}</td>
                    <%--<td>${changememberList.objMap.changememberList_sign}</td>--%>
                    <td>${changememberList.objMap.changememberList_memo}</td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
        <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
        </c:if>
        <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            <%--医院管理员编辑完成标识符更改--%>
            <input id="nxt" type="button" onclick="nextOpt('localFinish')" class="search" value="完&#12288;成"/>
        </c:if>
    </div>

    </form>
<table style="display:none">
    <tr id="outmemberList">
        <td><input type="checkbox" class="toDel"></td>
        <td><input type="text" name="outmemberList_name" value="" class="inputText " style="width: 90%;"/></td>
        <td><input type="text" name="outmemberList_ranking" value="" class="inputText validate[<%--required,--%>custom[number]]" style="width: 90%;"/></td>
        <td><input type="text" name="outmemberList_reason" value="" class="inputText " style="width: 90%;"/></td>
        <%--<td><input type="text" name="outmemberList_sign" value="" class="inputText " style="width: 90%;"/></td>--%>
        <td><input type="text" name="outmemberList_memo" value="" class="inputText " style="width: 90%;"/></td>
    </tr>

    <tr id="changememberList">
        <td><input type="checkbox" class="toDel"></td>
        <td><input type="text" name="changememberList_name" value="" class="inputText " style="width: 90%;"/></td>
        <td><input type="text" name="changememberList_ranking" value="" class="inputText validate[<%--required,--%>custom[number]]" style="width: 90%;"/></td>
        <td><input type="text" name="changememberList_mainTask" value="" class="inputText " style="width: 90%;"/></td>
        <%--<td><input type="text" name="changememberList_sign" value="" class="inputText " style="width: 90%;"/></td>--%>
        <td><input type="text" name="changememberList_memo" value="" class="inputText " style="width: 90%;"/></td>
    </tr>
</table>
