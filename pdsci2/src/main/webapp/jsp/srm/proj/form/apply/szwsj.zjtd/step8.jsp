<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        $(document).ready(function(){
            if($(".plannedSchedule tr").length<=0){
                add('plannedSchedule');
            }
        });
        function add(templateId) {
            if (templateId) {
               // if ($('.' + templateId + ' .toDel').length < 10) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                    //projCount(templateId);
              //  } else {
                    //jboxTip('该项最多新增10条！');
               // }
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    //projCount(templateId);
                }, null);
            }
        }
    </script>
</c:if>
<style type="text/css">
    .basic tbody th{
        text-align: center;
    }
</style>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step8"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight: bold; color: #333;">（四）引进团队学科建设规划表</font>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th style="text-align: left" colspan="4">规划目标</th>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">1.学科建设总体规划</th>
        </tr>
        <tr>
            <th width="25%">目标与任务</th>
            <th width="25%">规划指标</th>
            <th width="25%">现&#12288;状</th>
            <th width="25%">规划目标</th>
        </tr>
        <tr>
            <td>重点学科建设</td>
            <td>国家医学重点学科、专科</td>
            <td><input type="text" class="inputText" name="subjBuildNow" value="${resultMap.subjBuildNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="subjBuildTarget" value="${resultMap.subjBuildTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>学科国内地位</td>
            <td>学科在国内专科排名</td>
            <td><input type="text" class="inputText" name="subjDegreeNow" value="${resultMap.subjDegreeNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="subjDegreeTarget" value="${resultMap.subjDegreeTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">2.学科方向规划</th>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">2.1 重点发展的学科方向（3-5个）</th>
        </tr>
        <tr>
            <th colspan="2">学科方向名称</th>
            <th>学术带头人</th>
            <th>人&#12288;数</th>
        </tr>
        <tr>
            <td colspan="2">方向Ⅰ：<input type="text" class="inputText" name="developSubjDirection1" value="${resultMap.developSubjDirection1}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqLeader1" value="${resultMap.directionqLeader1}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqPeopNum1" value="${resultMap.directionqPeopNum1}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">方向Ⅱ：<input type="text" class="inputText" name="developSubjDirection2" value="${resultMap.developSubjDirection2}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqLeader2" value="${resultMap.directionqLeader2}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqPeopNum2" value="${resultMap.directionqPeopNum2}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">方向Ⅲ：<input type="text" class="inputText" name="developSubjDirection3" value="${resultMap.developSubjDirection3}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqLeader3" value="${resultMap.directionqLeader3}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqPeopNum3" value="${resultMap.directionqPeopNum3}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">方向Ⅳ：<input type="text" class="inputText" name="developSubjDirection4" value="${resultMap.developSubjDirection4}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqLeader4" value="${resultMap.directionqLeader4}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqPeopNum4" value="${resultMap.directionqPeopNum4}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">方向Ⅴ：<input type="text" class="inputText" name="developSubjDirection5" value="${resultMap.developSubjDirection5}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqLeader5" value="${resultMap.directionqLeader5}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="directionqPeopNum5" value="${resultMap.directionqPeopNum5}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">2.2 国内外标杆单位（分别明确1所标杆医院）</th>
        </tr>
        <tr>
            <th>类&#12288;别</th>
            <th>标杆医院</th>
            <th colspan="2">国内外地位及优势学科方向</th>
        </tr>
        <tr>
            <td>国际标杆</td>
            <td><input type="text" class="inputText" name="InModelHospital" value="${resultMap.InModelHospital}" style="width: 80%" /></td>
            <td colspan="2"><input type="text" class="inputText" name="internationalStatus" value="${resultMap.internationalStatus}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>国内标杆</td>
            <td><input type="text" class="inputText" name="homeModelHospital" value="${resultMap.homeModelHospital}" style="width: 80%" /></td>
            <td colspan="2"><input type="text" class="inputText" name="domesticStatus" value="${resultMap.domesticStatus}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">3.学科队伍建设规划</th>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">
                3.1 高端人才引进与培养<span style="font-weight: normal ">（包括院士、国家千人计划、长江学者特聘教授、杰青基金获得者以及省、市高层次人才）</span>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('topTalent')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('topTalent');"/>
				</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
               <th></th>
            </c:if>
            <th <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if>>专家类别</th>
            <th>现&#12288;状</th>
            <th>规划目标</th>
        </tr>
        <tbody class="topTalent">
        <c:forEach var="topTalent" items="${resultMap.topTalent}" varStatus="status">
            <tr>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                    <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
                </c:if>
                <td <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if>><input type="text" class="inputText" name="topTalent_type" value="${topTalent.objMap.topTalent_type}" style="width: 80%" /></td>
                <td><input type="text" class="inputText" name="topTalent_Now" value="${topTalent.objMap.topTalent_Now}" style="width: 80%" /></td>
                <td><input type="text" class="inputText" name="topTalent_target" value="${topTalent.objMap.topTalent_target}" style="width: 80%" /></td>
            </tr>
        </c:forEach>
        </tbody>
        <tr>
            <th style="text-align: left" colspan="4">3.2学术影响力</th>
        </tr>
        <tr>
            <th colspan="2">类&#12288;别</th>
            <th>现&#12288;状</th>
            <th>规划目标</th>
        </tr>
        <tr>
            <td colspan="2">国家、省专业学会主委或副主委</td>
            <td><input type="text" class="inputText" name="chairmanNow" value="${resultMap.chairmanNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="chairmanTarget" value="${resultMap.chairmanTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">SCI或中华系列学术刊物任职</td>
            <td><input type="text" class="inputText" name="officeNow" value="${resultMap.officeNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="officeTarget" value="${resultMap.officeTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">主办学术会议（国际、国内、省）</td>
            <td><input type="text" class="inputText" name="hostMeetingNow" value="${resultMap.hostMeetingNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="hostMeetingTarget" value="${resultMap.hostMeetingTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">3.3学科人才培养</th>
        </tr>
        <tr>
            <th colspan="2">类&#12288;别</th>
            <th>现&#12288;状</th>
            <th>规划目标</th>
        </tr>
        <tr>
            <td colspan="2">学科博士生的比例</td>
            <td><input type="text" class="inputText" name="doctorScaleNow" value="${resultMap.doctorScaleNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="doctorScaleTarget" value="${resultMap.doctorScaleTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">学科人员到国外著名医院进修半年比例</td>
            <td><input type="text" class="inputText" name="outStudyScaleNow" value="${resultMap.outStudyScaleNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="outStudyScaleTarget" value="${resultMap.outStudyScaleTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">学科人员到国内著名医院进修半年比例</td>
            <td><input type="text" class="inputText" name="StudyScaleNow" value="${resultMap.StudyScaleNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="StudyScaleTarget" value="${resultMap.StudyScaleTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">4.医疗服务能力与水平</th>
        </tr>
        <tr>
            <td colspan="2">国际或国内领先的医疗技术</td>
            <td><input type="text" class="inputText" name="serviceNow" value="${resultMap.serviceNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="serviceTarget" value="${resultMap.serviceTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">新业务、新技术研究与开发</td>
            <td><input type="text" class="inputText" name="newTechNow" value="${resultMap.newTechNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="newTechTarget" value="${resultMap.newTechTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td colspan="2">市外患者就医的辐射能力比例（%）</td>
            <td><input type="text" class="inputText" name="abilityScaleNow" value="${resultMap.abilityScaleNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="abilityScaleTarget" value="${resultMap.abilityScaleTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">5.科研规划</th>
        </tr>
        <tr>
            <th>目标与任务</th>
            <th>规划指标</th>
            <th>现&#12288;状</th>
            <th>规划目标</th>
        </tr>
        <tr>
            <th rowspan="3">5.1实验室建设</th>
            <td>国家级基地</td>
            <td><input type="text" class="inputText" name="countryBaseNow" value="${resultMap.countryBaseNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="countryBaseTarget" value="${resultMap.countryBaseTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>省部级基地</td>
            <td><input type="text" class="inputText" name="jsBaseNow" value="${resultMap.jsBaseNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="jsBaseTarget" value="${resultMap.jsBaseTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>苏州市级基地</td>
            <td><input type="text" class="inputText" name="szBaseNow" value="${resultMap.szBaseNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="szBaseTarget" value="${resultMap.szBaseTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th rowspan="3">5.2获&#12288;奖</th>
            <td>国家科学技术奖</td>
            <td><input type="text" class="inputText" name="countrySatNow" value="${resultMap.countrySatNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="countrySatTarget" value="${resultMap.countrySatTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>江苏省和教育部奖</td>
            <td><input type="text" class="inputText" name="jsSatNow" value="${resultMap.jsSatNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="jsSatTarget" value="${resultMap.jsSatTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>苏州市科学技术奖</td>
            <td><input type="text" class="inputText" name="szSatNow" value="${resultMap.szSatNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="szSatTarget" value="${resultMap.szSatTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th rowspan="5">5.3 项&#12288;目</th>
            <td>主持国家级纵向项目数（其中重大项目）</td>
            <td><input type="text" class="inputText" name="hostProjNow" value="${resultMap.hostProjNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="hostProjTarget" value="${resultMap.hostProjTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>主持省级纵向项目数</td>
            <td><input type="text" class="inputText" name="hostJSProjNow" value="${resultMap.hostJSProjNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="hostJSProjTarget" value="${resultMap.hostJSProjTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>到账科研总经费</td>
            <td><input type="text" class="inputText" name="arrivalFundsNow" value="${resultMap.arrivalFundsNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="arrivalFundsTarget" value="${resultMap.arrivalFundsTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>SCI论文数</td>
            <td><input type="text" class="inputText" name="SCI_now" value="${resultMap.SCI_now}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="SCI_target" value="${resultMap.SCI_target}" style="width: 80%" /></td>
        </tr>
        <tr>
            <td>中文核心期刊论文数</td>
            <td><input type="text" class="inputText" name="CSSCI_now" value="${resultMap.CSSCI_now}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="CSSCI_target" value="${resultMap.CSSCI_target}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>5.4专&#12288;著</th>
            <td>专著数（主编或副主编）</td>
            <td><input type="text" class="inputText" name="bookNow" value="${resultMap.bookNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="bookTarget" value="${resultMap.bookTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>5.5专&#12288;利</th>
            <td>获得国家发明专利/应用专利数</td>
            <td><input type="text" class="inputText" name="patentNow" value="${resultMap.patentNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="patentTarget" value="${resultMap.patentTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th style="text-align: left" colspan="4">6.教学规划</th>
        </tr>
        <tr>
            <th>目标与任务</th>
            <th>规划指标</th>
            <th>现&#12288;状</th>
            <th>规划目标</th>
        </tr>
        <tr>
            <th>6.1博士研究生</th>
            <td>招收博士研究生数</td>
            <td><input type="text" class="inputText" name="recruitDoctorNow" value="${resultMap.recruitDoctorNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="recruitDoctorTarget" value="${resultMap.recruitDoctorTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>6.2硕士研究生</th>
            <td>招收硕士研究生数</td>
            <td><input type="text" class="inputText" name="recruitMasterNow" value="${resultMap.recruitMasterNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="recruitMasterTarget" value="${resultMap.recruitMasterTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>6.3规范化培训医师</th>
            <td>规范化培训医师数</td>
            <td><input type="text" class="inputText" name="recruitResNow" value="${resultMap.recruitResNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="recruitResTarget" value="${resultMap.recruitResTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>6.4 进修生数</th>
            <td>进修生数</td>
            <td><input type="text" class="inputText" name="recruitStuNow" value="${resultMap.recruitStuNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="recruitStuTarget" value="${resultMap.recruitStuTarget}" style="width: 80%" /></td>
        </tr>
        <tr>
            <th>6.5 本科生实习</th>
            <td>本科生实习数</td>
            <td><input type="text" class="inputText" name="recruitTraineeNow" value="${resultMap.recruitTraineeNow}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="recruitTraineeTarget" value="${resultMap.recruitTraineeTarget}" style="width: 80%" /></td>
        </tr>
    </table>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th colspan="4" style="text-align: left">
                <p style="line-height: 30px">
                    计划进度
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('plannedSchedule')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('plannedSchedule');"/>
				</span>
            </c:if>
                </p>
                <p style="font-weight: normal">
                    （围绕学科建设目标和内容，简述不同阶段的工作任务和实现的具体目标，包括时间进度安排、工作任务、具体目标等。目标应该清晰、正确地定性或定量描述。每年为一个阶段，原则上以五年为期限。)
                </p>
            </th>
        </tr>

        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
            <td style="font-weight: bold;text-align: center" width="7%"></td>
            </c:if>
            <td style="font-weight: bold;text-align: center" width="13%">阶&#12288;段</td>
            <td style="font-weight: bold;text-align: center" width="25%">起止时间</td>
            <td style="font-weight: bold;text-align: center" width="60%">工作任务（临床技术、人才培养和科研工作）与预期目标</td>
        </tr>
        <tbody class="plannedSchedule">
        <c:forEach var="plannedSchedule" items="${resultMap.plannedSchedule}" varStatus="status">
            <tr>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
                </c:if>
                <td><input type="text" class="inputText" name="plannedSchedule_stage" value="${plannedSchedule.objMap.plannedSchedule_stage}" style="width: 80%" /></td>
                <td>
                    <input type="text" class="inputText" name="plannedSchedule_startTime" value="${plannedSchedule.objMap.plannedSchedule_startTime}" style="width: 30%" />至&#12288;
                    <input type="text" class="inputText" name="plannedSchedule_endTime" value="${plannedSchedule.objMap.plannedSchedule_endTime}" style="width: 30%" />
                </td>
                <td>
                    <textarea class="xltxtarea" name="plannedSchedule_content" style="width:98%;height: 100px;">${plannedSchedule.objMap.plannedSchedule_content}</textarea>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<div class="button" style="width:100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none;</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
</div>

<div style="display: none;">
    <!-- 模板 -->
    <table >
        <tr id="plannedSchedule">
            <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
            <td><input type="text" class="inputText" name="plannedSchedule_stage" style="width: 80%" /></td>
            <td>
                <input type="text" class="inputText" name="plannedSchedule_startTime" style="width: 30%" />至&#12288;
                <input type="text" class="inputText" name="plannedSchedule_endTime"  style="width: 30%" />
            </td>
            <td>
                <textarea class="xltxtarea" name="plannedSchedule_content" style="width:98%;height: 100px;"></textarea>
            </td>
        </tr>
        <tr id="topTalent">
            <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
            <td ><input type="text" class="inputText" name="topTalent_type" value="${topTalent.objMap.topTalent_type}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="topTalent_Now" value="${topTalent.objMap.topTalent_Now}" style="width: 80%" /></td>
            <td><input type="text" class="inputText" name="topTalent_target" value="${topTalent.objMap.topTalent_target}" style="width: 80%" /></td>
        </tr>
    </table>
</div>