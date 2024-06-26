<c:if test="${param.view != GlobalConstant.FLAG_Y}">

    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        $(document).ready(function(){
            if($(".teamMember tr").length<=0){
                add('teamMember');
            }
        });
        function add(templateId) {
            if (templateId) {
//                if ($('.' + templateId + ' .toDel').length < 10) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
                //projCount(templateId);
                /*} else {
                 jboxTip('该项最多新增10条！');
                 }*/
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    reSeq(templateId);
                    //projCount(templateId);
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step6"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">六、引进团队及依托科室人员组成及分工</font>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th style="text-align: left" colspan="7">
                (一)苏州市引进临床医学专家团队成员基本情况表
                <span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('teamMember')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('teamMember');"/>
				</span>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                <td width="5%"></td>
            </c:if>
            <td <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if> width="5%">序号</td>
            <td width="15%">类别</td>
            <td width="20%">姓名</td>
            <td width="25%">承担的主要工作</td>
            <td width="10%">是否全职在苏工作</td>
            <td width="25%">承诺每年在苏工作时间（单位：月）</td>
        </tr>
        <tbody class="teamMember">
        <c:forEach var="teamMember" items="${resultMap.teamMember}" varStatus="status">
            <tr>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                    <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
                </c:if>
                <td <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if> style="text-align: center;" class="seq">${status.count}</td>
                <td><input type="text" class="validate inputText" name="teamMember_type" value="${teamMember.objMap.teamMember_type}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_name" value="${teamMember.objMap.teamMember_name}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_content" value="${teamMember.objMap.teamMember_content}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="FullTimeInSZ" value="${teamMember.objMap.FullTimeInSZ}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_workTime" value="${teamMember.objMap.teamMember_workTime}" style="width: 90%;" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p style="line-height: 20px">
        备注：按照《苏州市“临床医学专家团队”引进计划实施方案》的要求，引进团队带头人：每年在苏工作时间不少于1个月；团队带头人之外的其他核心成员：每年每人累计在苏工作时间须在4个月以上。
    </p>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th style="text-align: left" colspan="4">（二）临床医学专家团队合作分工表</th>
        </tr>
        <tr>
            <th style="text-align: center">序号</th>
            <th style="text-align: center">职能</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">承担的主要工作</th>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">
                &#12288;&#12288;总负责
            </th>
        </tr>
        <tr>
            <td>1</td>
            <td>引进团队带头人</td>
            <td><input type="text" name="teamLeader" style="width: 97%" class="inputText" value="${resultMap.teamLeader}" ></td>
            <td><input type="text" name="teamLeaderMainWork" style="width: 97%" class="inputText" value="${resultMap.teamLeaderMainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>依托科室负责人</td>
            <td><input type="text" name="deptLeader" style="width: 97%" class="inputText" value="${resultMap.deptLeader}" ></td>
            <td><input type="text" name="deptLeaderMainWork" style="width: 97%" class="inputText" value="${resultMap.deptLeaderMainWork}" ></td>
        </tr>
        <tr>
            <td colspan="4">&#12288;&#12288;方向1:<input type="text" name="teamDirection1" style="width: 50%" class="inputText" value="${resultMap.teamDirection1}" ></td>
        </tr>
        <tr>
            <td>1</td>
            <td>技术指导（引进方）</td>
            <td><input type="text" name="techGuid1" style="width: 97%" class="inputText" value="${resultMap.techGuid1}" ></td>
            <td><input type="text" name="techGuid1MainWork" style="width: 97%" class="inputText" value="${resultMap.techGuid1MainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>骨干人才（依托方）</td>
            <td><input type="text" name="mainApplyPer1" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer1}" ></td>
            <td><input type="text" name="mainApplyPer1MainWork" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer1MainWork}" ></td>
        </tr>
        <tr>
            <td>3</td>
            <td>后备人才1（依托方）</td>
            <td><input type="text" name="firstReservePer1" style="width: 97%" class="inputText" value="${resultMap.firstReservePer1}" ></td>
            <td><input type="text" name="firstReservePer1MainWork" style="width: 97%" class="inputText" value="${resultMap.firstReservePer1MainWork}" ></td>
        </tr>
        <tr>
            <td>4</td>
            <td>后备人才2（依托方）</td>
            <td><input type="text" name="secondReservePer1" style="width: 97%" class="inputText" value="${resultMap.secondReservePer1}" ></td>
            <td><input type="text" name="secondReservePer1MainWork" style="width: 97%" class="inputText" value="${resultMap.secondReservePer1MainWork}" ></td>
        </tr>
        <tr>
            <td>5</td>
            <td>专科护理人才（依托方）</td>
            <td><input type="text" name="nursingPer1" style="width: 97%" class="inputText" value="${resultMap.nursingPer1}" ></td>
            <td><input type="text" name="nursingPer1MainWork" style="width: 97%" class="inputText" value="${resultMap.nursingPer1MainWork}" ></td>
        </tr>

        <tr>
            <td colspan="4">&#12288;&#12288;方向2:<input type="text" name="teamDirection2" style="width: 50%" class="inputText" value="${resultMap.teamDirection2}" ></td>
        </tr>
        <tr>
            <td>1</td>
            <td>技术指导（引进方）</td>
            <td><input type="text" name="techGuid2" style="width: 97%" class="inputText" value="${resultMap.techGuid2}" ></td>
            <td><input type="text" name="techGuid2MainWork" style="width: 97%" class="inputText" value="${resultMap.techGuid2MainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>骨干人才（依托方）</td>
            <td><input type="text" name="mainApplyPer2" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer2}" ></td>
            <td><input type="text" name="mainApplyPer2MainWork" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer2MainWork}" ></td>
        </tr>
        <tr>
            <td>3</td>
            <td>后备人才1（依托方）</td>
            <td><input type="text" name="firstReservePer2" style="width: 97%" class="inputText" value="${resultMap.firstReservePer2}" ></td>
            <td><input type="text" name="firstReservePer2MainWork" style="width: 97%" class="inputText" value="${resultMap.firstReservePer2MainWork}" ></td>
        </tr>
        <tr>
            <td>4</td>
            <td>后备人才2（依托方）</td>
            <td><input type="text" name="secondReservePer2" style="width: 97%" class="inputText" value="${resultMap.secondReservePer2}" ></td>
            <td><input type="text" name="secondReservePer2MainWork" style="width: 97%" class="inputText" value="${resultMap.secondReservePer2MainWork}" ></td>
        </tr>
        <tr>
            <td>5</td>
            <td>专科护理人才（依托方）</td>
            <td><input type="text" name="nursingPer2" style="width: 97%" class="inputText" value="${resultMap.nursingPer2}" ></td>
            <td><input type="text" name="nursingPer2MainWork" style="width: 97%" class="inputText" value="${resultMap.nursingPer2MainWork}" ></td>
        </tr>

        <tr>
            <td colspan="4">&#12288;&#12288;方向3:<input type="text" name="teamDirection3" style="width: 50%" class="inputText" value="${resultMap.teamDirection3}" ></td>
        </tr>
        <tr>
            <td>1</td>
            <td>技术指导（引进方）</td>
            <td><input type="text" name="techGuid3" style="width: 97%" class="inputText" value="${resultMap.techGuid3}" ></td>
            <td><input type="text" name="techGuid3MainWork" style="width: 97%" class="inputText" value="${resultMap.techGuid3MainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>骨干人才（依托方）</td>
            <td><input type="text" name="mainApplyPer3" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer3}" ></td>
            <td><input type="text" name="mainApplyPer3MainWork" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer3MainWork}" ></td>
        </tr>
        <tr>
            <td>3</td>
            <td>后备人才1（依托方）</td>
            <td><input type="text" name="firstReservePer3" style="width: 97%" class="inputText" value="${resultMap.firstReservePer3}" ></td>
            <td><input type="text" name="firstReservePer3MainWork" style="width: 97%" class="inputText" value="${resultMap.firstReservePer3MainWork}" ></td>
        </tr>
        <tr>
            <td>4</td>
            <td>后备人才2（依托方）</td>
            <td><input type="text" name="secondReservePer3" style="width: 97%" class="inputText" value="${resultMap.secondReservePer3}" ></td>
            <td><input type="text" name="secondReservePer3MainWork" style="width: 97%" class="inputText" value="${resultMap.secondReservePer3MainWork}" ></td>
        </tr>
        <tr>
            <td>5</td>
            <td>专科护理人才（依托方）</td>
            <td><input type="text" name="nursingPer3" style="width: 97%" class="inputText" value="${resultMap.nursingPer3}" ></td>
            <td><input type="text" name="nursingPer3MainWork" style="width: 97%" class="inputText" value="${resultMap.nursingPer3MainWork}" ></td>
        </tr>

        <tr>
            <td colspan="4">&#12288;&#12288;方向4:<input type="text" name="teamDirection4" style="width: 50%" class="inputText" value="${resultMap.teamDirection4}" ></td>
        </tr>
        <tr>
            <td>1</td>
            <td>技术指导（引进方）</td>
            <td><input type="text" name="techGuid4" style="width: 97%" class="inputText" value="${resultMap.techGuid4}" ></td>
            <td><input type="text" name="techGuid4MainWork" style="width: 97%" class="inputText" value="${resultMap.techGuid4MainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>骨干人才（依托方）</td>
            <td><input type="text" name="mainApplyPer4" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer4}" ></td>
            <td><input type="text" name="mainApplyPer4MainWork" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer4MainWork}" ></td>
        </tr>
        <tr>
            <td>3</td>
            <td>后备人才4（依托方）</td>
            <td><input type="text" name="firstReservePer4" style="width: 97%" class="inputText" value="${resultMap.firstReservePer4}" ></td>
            <td><input type="text" name="firstReservePer4MainWork" style="width: 97%" class="inputText" value="${resultMap.firstReservePer4MainWork}" ></td>
        </tr>
        <tr>
            <td>4</td>
            <td>后备人才2（依托方）</td>
            <td><input type="text" name="secondReservePer4" style="width: 97%" class="inputText" value="${resultMap.secondReservePer4}" ></td>
            <td><input type="text" name="secondReservePer4MainWork" style="width: 97%" class="inputText" value="${resultMap.secondReservePer4MainWork}" ></td>
        </tr>
        <tr>
            <td>5</td>
            <td>专科护理人才（依托方）</td>
            <td><input type="text" name="nursingPer4" style="width: 97%" class="inputText" value="${resultMap.nursingPer4}" ></td>
            <td><input type="text" name="nursingPer4MainWork" style="width: 97%" class="inputText" value="${resultMap.nursingPer4MainWork}" ></td>
        </tr>

        <tr>
            <td colspan="4">&#12288;&#12288;方向5:<input type="text" name="teamDirection5" style="width: 50%" class="inputText" value="${resultMap.teamDirection5}" ></td>
        </tr>
        <tr>
            <td>1</td>
            <td>技术指导（引进方）</td>
            <td><input type="text" name="techGuid5" style="width: 97%" class="inputText" value="${resultMap.techGuid5}" ></td>
            <td><input type="text" name="techGuid5MainWork" style="width: 97%" class="inputText" value="${resultMap.techGuid5MainWork}" ></td>
        </tr>
        <tr>
            <td>2</td>
            <td>骨干人才（依托方）</td>
            <td><input type="text" name="mainApplyPer5" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer5}" ></td>
            <td><input type="text" name="mainApplyPer5MainWork" style="width: 97%" class="inputText" value="${resultMap.mainApplyPer5MainWork}" ></td>
        </tr>
        <tr>
            <td>3</td>
            <td>后备人才1（依托方）</td>
            <td><input type="text" name="firstReservePer5" style="width: 97%" class="inputText" value="${resultMap.firstReservePer5}" ></td>
            <td><input type="text" name="firstReservePer5MainWork" style="width: 97%" class="inputText" value="${resultMap.firstReservePer5MainWork}" ></td>
        </tr>
        <tr>
            <td>4</td>
            <td>后备人才2（依托方）</td>
            <td><input type="text" name="secondReservePer5" style="width: 97%" class="inputText" value="${resultMap.secondReservePer5}" ></td>
            <td><input type="text" name="secondReservePer5MainWork" style="width: 97%" class="inputText" value="${resultMap.secondReservePer5MainWork}" ></td>
        </tr>
        <tr>
            <td>5</td>
            <td>专科护理人才（依托方）</td>
            <td><input type="text" name="nursingPer5" style="width: 97%" class="inputText" value="${resultMap.nursingPer5}" ></td>
            <td><input type="text" name="nursingPer5MainWork" style="width: 97%" class="inputText" value="${resultMap.nursingPer5MainWork}" ></td>
        </tr>

    </table>
    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
<div style="display: none;">
    <!-- 模板 -->
    <table >
        <tr id="teamMember">
            <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
            <td style="text-align: center;" class="seq">${status.count}</td>
            <td><input type="text" class="inputText" name="teamMember_type" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_name" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_content" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="FullTimeInSZ" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_workTime" style="width: 90%" /></td>
        </tr>
    </table>
</div>