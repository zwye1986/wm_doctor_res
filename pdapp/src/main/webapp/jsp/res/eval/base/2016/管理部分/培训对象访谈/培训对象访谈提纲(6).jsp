<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
 <title>培训对象访谈提纲（6）</title>
 <style type="text/css">
 .th_group .left{
  text-align: left;
}
.th_group .right{
  text-align: right;
}
.th_group th {
  background-color: rgba(0,0,0,0.07);
  border: 1px solid rgba(0,0,0,0.07);
  text-align: center;
  vertical-align:middle;
}
.td_group td {
  border: 1px solid rgba(0,0,0,0.07);
  text-align: left;
  vertical-align:middle;
}
.td_group th {
  text-align: center;
  vertical-align:middle;
}
.table-inner{
  width:100%;
  border-collapse: collapse;
  border: 1px solid rgba(0,0,0,0.07);
  padding:0;
  border-top: hidden;
  border-left: hidden;
  border-right: hidden;
  border-bottom: hidden;
}
.inputText{
  border:0; 
  border-bottom:1px solid #ccc;
  text-align: left;
  width: 250px;
}
body {
  width:100%;
  overflow:auto;
}
</style>
</head>
<body >
  <div data-role="page" id="pageone">
    <div data-role="header">
     <h1>培训对象访谈提纲（6）</h1>
   </div>
   <div data-role="main" class="ui-content">
     <div style="margin-bottom: 20px;border:1px solid rgba(0,0,0,0.07);overflow:auto;">
       <form id="noteInfoForm">
         <input name="orgFlow" value="${param.orgFlow}" type="hidden">
         <input name="cfgFlow" value="${param.cfgFlow}" type="hidden">
         <input name="userFlow" value="${param.userFlow}" type="hidden">
         <input name="evalYear" value="${param.evalYear}" type="hidden">
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="4" style="vertical-align:middle;"><b>一、基本信息</b></td>
           </tr>
           <tr class="td_group">
             <td class="right">
               培训基地：
             </td>
             <td colspan="3">
               <input class="inputText" style="width: 300px;"  type="text" name="trainingBase" value="${formDataMap['trainingBase']}" data-role="none"/>
             </td>
           </tr>
           <tr class="td_group">
             <td class="right">
               姓&#12288;&#12288;名：
             </td>
             <td>
               <input class="inputText" style="width: 200px;" type="text" name="userName" value="${formDataMap['userName']}" placeholder="（可以不询问）" data-role="none"/>
             </td>
             <td class="right">
               性&#12288;&#12288;别：
             </td>
             <td class="left">
               <input id="male"  name="gender" <c:if test="${formDataMap['gender'] eq 'male'}">checked="checked"</c:if> type="radio" value="male" data-role="none"/>男&#12288;
               <input id="female"  name="gender"<c:if test="${formDataMap['gender'] eq 'female'}">checked="checked"</c:if> type="radio" value="female" data-role="none"/>女
             </td>
           </tr>
           <tr class="td_group">
             <td class="right" >
               年&#12288;&#12288;龄：
             </td>
             <td>
               <input class="inputText" style="width: 200px;" type="text" name="userAge" value="${formDataMap['userAge']}" data-role="none"/>
             </td>
             <td class="right">
               毕业院校：
             </td>
             <td>
               <input class="inputText" style="width: 200px;" type="text" name="graduateInstitutions" value="${formDataMap['graduateInstitutions']}" data-role="none"/>
             </td>
           </tr>
           <tr class="td_group">
             <td class="right">
               毕业专业：
             </td>
             <td class="left">
               <input class="inputText" style="width: 200px;" type="text" name="graduationMajor" value="${formDataMap['graduationMajor']}" data-role="none"/>
             </td>
             <td class="right">
               培训年限：
             </td>
             <td>
               <input class="inputText" style="width: 200px;" type="text" name="trainingYears" value="${formDataMap['trainingYears']}" data-role="none"/>
             </td>
           </tr>
           <tr class="td_group">
             <td class="left" colspan="4">
               &#12288;&#12288;&#12288;现处于住院医师培训第
               <input class="inputText" style="width: 200px;" type="text" name="trainingPhase" value="${formDataMap['trainingPhase']}" data-role="none"/>
               阶段第
               <input class="inputText" style="width: 200px;" type="text" name="trainingYear" value="${formDataMap['trainingYear']}" data-role="none"/>
               年
             </td>
           </tr>
         </table>
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="4" style="vertical-align:middle;"><b>二、相关信息（共性）</b></td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;1、是否组织入院教育（是
               <input name="ifAdmissionEducation" type="radio" value="Y" <c:if test="${formDataMap['ifAdmissionEducation'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifAdmissionEducation" type="radio" value="N" <c:if test="${formDataMap['ifAdmissionEducation'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;内容有哪些（培训政策
               <input name="admissionEducationContent1" type="checkbox" <c:if test="${formDataMap['admissionEducationContent1'] eq 'trainingPolicy'}">checked="checked"</c:if> value="trainingPolicy" data-role="none"/>
               、培训待遇
               <input name="admissionEducationContent2" type="checkbox" <c:if test="${formDataMap['admissionEducationContent2'] eq 'trainingSalary'}">checked="checked"</c:if> value="trainingSalary" data-role="none"/>
               、管理规定
               <input name="admissionEducationContent3" type="checkbox" <c:if test="${formDataMap['admissionEducationContent3'] eq 'managementRegulation'}">checked="checked"</c:if> value="managementRegulation" data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;2、是否有专职培训管理人员（是
               <input name="ifTrainingManager" type="radio" value="Y" <c:if test="${formDataMap['ifTrainingManager'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifTrainingManager" type="radio" value="N" <c:if test="${formDataMap['ifTrainingManager'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;3、进入专业基地（入科）是否有专人负责培训工作（是
               <input name="ifPersonChargeTraining" type="radio" value="Y" <c:if test="${formDataMap['ifPersonChargeTraining'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifPersonChargeTraining" type="radio" value="N" <c:if test="${formDataMap['ifPersonChargeTraining'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;是否有日常考核（是
               <input name="ifRoutineAssessment" type="radio" value="Y" <c:if test="${formDataMap['ifRoutineAssessment'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifRoutineAssessment" type="radio" value="N" <c:if test="${formDataMap['ifRoutineAssessment'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;4、是否安排严格的科室轮转（是
               <input name="ifDepartmentRotation" type="radio" value="Y" <c:if test="${formDataMap['ifDepartmentRotation'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifDepartmentRotation" type="radio" value="N" <c:if test="${formDataMap['ifDepartmentRotation'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;医院图书馆（是
               <input name="ifProvideForBorrowing" type="radio" value="Y" <c:if test="${formDataMap['ifProvideForBorrowing'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifProvideForBorrowing" type="radio" value="N" <c:if test="${formDataMap['ifProvideForBorrowing'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）提供借阅书籍和信息检索；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;5、是否有出科考核（是
               <input name="ifExamination" type="radio" value="Y" <c:if test="${formDataMap['ifExamination'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifExamination" type="radio" value="N" <c:if test="${formDataMap['ifExamination'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;出科考核内容（理论
               <input name="ExaminationContent1" type="checkbox" <c:if test="${formDataMap['ExaminationContent1'] eq 'theory'}">checked="checked"</c:if> value="theory" data-role="none"/>
               、技能操作
               <input name="ExaminationContent2" type="checkbox" <c:if test="${formDataMap['ExaminationContent2'] eq 'SkillOperation'}">checked="checked"</c:if> value="SkillOperation" data-role="none"/>
               、病例分析
               <input name="ExaminationContent3" type="checkbox" <c:if test="${formDataMap['ExaminationContent3'] eq 'CaseAnalysis'}">checked="checked"</c:if> value="CaseAnalysis" data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;6、各科（是
               <input name="isIn" type="radio" value="Y" <c:if test="${formDataMap['isIn'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="isIn" type="radio" value="N" <c:if test="${formDataMap['isIn'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）有教学查房、小讲课和疑难危重病例讨论；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;7、是否在医院模拟训练中心进行过训练（是
               <input name="ifOrganizeRounds" type="radio" value="Y" <c:if test="${formDataMap['ifOrganizeRounds'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifOrganizeRounds" type="radio" value="N" <c:if test="${formDataMap['ifOrganizeRounds'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;最近一次大概时间和培训内容：
               <input class="inputText" style="width:300px;" name="LatestTrainingContent" type="text" value="${formDataMap['LatestTrainingContent']}" data-role="none"/>；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;8、是否有开展优秀指导老师、培训学员遴选（是
               <input name="ifExcellentSelection" type="radio" value="Y" <c:if test="${formDataMap['ifExcellentSelection'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifExcellentSelection" type="radio" value="N" <c:if test="${formDataMap['ifExcellentSelection'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;是否有经费表彰（是
               <input name="ifGrantRewards" type="radio" value="Y" <c:if test="${formDataMap['ifGrantRewards'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifGrantRewards" type="radio" value="N" <c:if test="${formDataMap['ifGrantRewards'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;9、是否提供住宿（是
               <input name="ifProvideAccommodation" type="radio" value="Y" <c:if test="${formDataMap['ifProvideAccommodation'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifProvideAccommodation" type="radio" value="N" <c:if test="${formDataMap['ifProvideAccommodation'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;若否，是否提供住宿、交通补贴（是
               <input name="ifProvideSubsidies" type="radio" value="Y" <c:if test="${formDataMap['ifProvideSubsidies'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifProvideSubsidies" type="radio" value="N" <c:if test="${formDataMap['ifProvideSubsidies'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ），每月多少
               <input class="inputText" name="subsidieNumber" type="text" value="${formDataMap['subsidieNumber']}" data-role="none"/>；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;10、是否发放过夜班费（是
               <input name="ifNightOvertimePay" type="radio" value="Y" <c:if test="${formDataMap['ifNightOvertimePay'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifNightOvertimePay" type="radio" value="N" <c:if test="${formDataMap['ifNightOvertimePay'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）、加班费（是
               <input name="ifOvertimePay" type="radio" value="Y" <c:if test="${formDataMap['ifOvertimePay'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifOvertimePay" type="radio" value="N" <c:if test="${formDataMap['ifOvertimePay'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;11、指导老师是否指导你接诊新病人（是
               <input name="ifGuideAcceptPatients" type="radio" value="Y" <c:if test="${formDataMap['ifGuideAcceptPatients'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifGuideAcceptPatients" type="radio" value="N" <c:if test="${formDataMap['ifGuideAcceptPatients'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;&#12288;&#12288;是否指导你书写病历（是
               <input name="ifInstructionWritingCase" type="radio" value="Y" <c:if test="${formDataMap['ifInstructionWritingCase'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifInstructionWritingCase" type="radio" value="N" <c:if test="${formDataMap['ifInstructionWritingCase'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;12、是否有对指导老师开展过评价（是
               <input name="ifEvaluateTheTeacher" type="radio" value="Y" <c:if test="${formDataMap['ifEvaluateTheTeacher'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifEvaluateTheTeacher" type="radio" value="N" <c:if test="${formDataMap['ifEvaluateTheTeacher'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
         </table>
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="6" style="vertical-align:middle;"><b>三、满意度</b></td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;对医院整体培训安排是否满意（是
               <input name="ifSatisfactionTrainingArrangement" type="radio" value="Y" <c:if test="${formDataMap['ifSatisfactionTrainingArrangement'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifSatisfactionTrainingArrangement" type="radio" value="N" <c:if test="${formDataMap['ifSatisfactionTrainingArrangement'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;不满意部分（入院教育
               <input name="notSatisfaction1" type="checkbox"<c:if test="${formDataMap['notSatisfaction1'] eq 'admissionHospitalEducation'}">checked="checked"</c:if> value="admissionHospitalEducation" data-role="none"/>
               、经费保障
               <input name="notSatisfaction2" type="checkbox"<c:if test="${formDataMap['notSatisfaction2'] eq 'fundsGuarantee'}">checked="checked"</c:if> value="fundsGuarantee" data-role="none"/>
               、入科教育
               <input name="notSatisfaction3" type="checkbox"<c:if test="${formDataMap['notSatisfaction3'] eq 'admissionDeptEducation'}">checked="checked"</c:if> value="admissionDeptEducation" data-role="none"/>
               </br>
               &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;、轮转安排
               <input name="notSatisfaction4" type="checkbox"<c:if test="${formDataMap['notSatisfaction4'] eq 'rotationArrangement'}">checked="checked"</c:if> value="rotationArrangement" data-role="none"/>
               、老师带教
               <input name="notSatisfaction5" type="checkbox"<c:if test="${formDataMap['notSatisfaction5'] eq 'teacher'}">checked="checked"</c:if> value="teacher" data-role="none"/>
               、其他
               <input name="notSatisOther" class="inputText" type="text" value="${formDataMap['notSatisOther']}" data-role="none"/>
               ）；
             </td>
           </tr>
         </table>
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="4" style="vertical-align:middle;"><b>四、社会人调查信息</b></td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;1、是否与培训基地签订培训合同或协议（是
               <input name="ifSignTrainingContract" type="radio" value="Y" <c:if test="${formDataMap['ifSignTrainingContract'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifSignTrainingContract" type="radio" value="N" <c:if test="${formDataMap['ifSignTrainingContract'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;2、培训基地是否协助参加国家医师资格考试、办理执业注册、变更等（是
               <input name="ifAssistExamination" type="radio" value="Y" <c:if test="${formDataMap['ifAssistExamination'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifAssistExamination" type="radio" value="N" <c:if test="${formDataMap['ifAssistExamination'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;3、培训基地是否协助办理人事档案和相关社会保险（是
               <input name="ifAssistArchives" type="radio" value="Y" <c:if test="${formDataMap['ifAssistArchives'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifAssistArchives" type="radio" value="N" <c:if test="${formDataMap['ifAssistArchives'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;4、工资奖金是否与培训基地同年资住院医师同等待遇（是
               <input name="ifBonus" type="radio" value="Y" <c:if test="${formDataMap['ifBonus'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifBonus" type="radio" value="N" <c:if test="${formDataMap['ifBonus'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
         </table>
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="4" style="vertical-align:middle;"><b>五、委托培养调查信息</b></td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;1、原单位工资（有
               <input name="ifOldWages" type="radio" value="Y" <c:if test="${formDataMap['ifOldWages'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               无
               <input name="ifOldWages" type="radio" value="N" <c:if test="${formDataMap['ifOldWages'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               &#12288;&#12288;&#12288;2、培训基地是否提供与培训基地同年资住院医师同等待遇（奖金部分）（是
               <input name="ifOrgBonus" type="radio" value="Y" <c:if test="${formDataMap['ifOrgBonus'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
               否
               <input name="ifOrgBonus" type="radio" value="N" <c:if test="${formDataMap['ifOrgBonus'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
               ）；
             </td>
           </tr>
         </table>
         <table data-role="table" data-mode="" style="margin-top: 10px;"  class="ui-responsive table-stroke">
           <tr class="td_group">
             <td class="left" colspan="4" style="vertical-align:middle;"><b>六、意见和建议</b></td>
           </tr>
           <tr class="td_group">
             <td colspan="4" class="left">
               <textarea style="height: 200px;resize: none;" name="opinion">${formDataMap['opinion']}</textarea>
             </td>
           </tr>
           <tr class="td_group">
             <td colspan="4">
               <input value="保存" id="save" type="button">
             </td>
           </tr>
         </table>
       </form>
     </div>

	<script>
	
		$(document).ready(function(){
		$(".left").css("text-align","left");
		$(".right").css("text-align","right");
			$("#save").on("click",function(){
				save();
			});
		});
        function submitData()
        {
            jboxPost("<s:url value='/res/eval/saveEvalResult2'/>",$("#noteInfoForm").serialize(),function(resp){
                if(resp=="保存成功！"){
				setTimeout(function(){$.DialogByZ.Alert({Title: "", Content: resp,BtnL:"确定", FunL: function(){androidRefresh.onRefreshFragment();location.reload(true);}});},500);

                }else{setTimeout(function(){jboxInfo(resp);},500);}
            },null,false);
        }
		function save()
		{
            $.DialogByZ.Confirm({Title: "", Content: "确认保存评分？",FunL:function(){
                closeDialog();
                submitData();
            },FunR:closeDialog})
		}
	</script>
  </div>

 <div data-role="footer">
   <h1></h1>
 </div>
</div> 
</body>
</html>