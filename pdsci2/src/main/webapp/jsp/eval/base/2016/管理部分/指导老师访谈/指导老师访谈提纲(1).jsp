<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
 <title>指导老师访谈提纲</title>
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
     <h1>指导老师访谈提纲（1）</h1>
   </div>
   <div data-role="main" class="ui-content">
    <div style="margin-bottom: 20px;border:1px solid rgba(0,0,0,0.07);overflow:auto;">
      <form id="noteInfoForm">
          <input name="orgFlow" value="${param.orgFlow}" type="hidden">
          <input name="cfgFlow" value="${param.cfgFlow}" type="hidden">
          <input name="userFlow" value="${param.userFlow}" type="hidden">
          <input name="evalYear" value="${param.evalYear}" type="hidden">
        <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
          <tr class="td_group">
            <td class="left" colspan="4" style="vertical-align:middle;"><b>一、基本信息</b></td>
          </tr>
          <tr class="td_group">
            <td class="right">
              培训基地：
            </td>
            <td>
              <input class="inputText" type="text" name="trainingBase" value="${formDataMap['trainingBase']}" data-role="none"/>
            </td>
            <td class="right">
              科&#12288;&#12288;室：
            </td>
            <td>
              <input class="inputText" type="text" name="deptName" value="${formDataMap['deptName']}" data-role="none"/>
            </td>
          </tr>
          <tr class="td_group">
            <td class="right" >
              姓&#12288;&#12288;名：
            </td>
            <td style="width:25%">
              <input class="inputText" type="text" name="userName" value="${formDataMap['userName']}" placeholder="（可以不询问）" data-role="none"/>
            </td>
            <td class="right" >
              职&#12288;&#12288;称：
            </td>
            <td class="left" style="width:26%">
              <input class="inputText" type="text" name="title" value="${formDataMap['title']}" data-role="none"/>
            </td>
          </tr>
          </table>
          <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
          <tr class="td_group">
            <td class="left" colspan="4" style="vertical-align:middle;"><b>二、其他信息</b></td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;1、医院是否组织过住院医师专项师资培训和资格认定（是
              <input name="ifHabilitating" type="radio" value="Y" <c:if test="${formDataMap['ifHabilitating'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifHabilitating" type="radio" value="N" <c:if test="${formDataMap['ifHabilitating'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）；
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;2、专业基地（科室）开设住院医师规范化培训相关课程（是
              <input name="ifTrainingCourse" type="radio" value="Y" <c:if test="${formDataMap['ifTrainingCourse'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifTrainingCourse" type="radio" value="N" <c:if test="${formDataMap['ifTrainingCourse'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）；
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;3、专业基地（科室）（是
              <input name="ifOrganizeRounds" type="radio" value="Y" <c:if test="${formDataMap['ifOrganizeRounds'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifOrganizeRounds" type="radio" value="N" <c:if test="${formDataMap['ifOrganizeRounds'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）组织教学查房、小讲课和疑难危重病例讨论；
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;4、专业基地（科室）针对培训学员进行日常考核（是
              <input name="ifRoutineAssessment" type="radio" value="Y" <c:if test="${formDataMap['ifRoutineAssessment'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifRoutineAssessment" type="radio" value="N" <c:if test="${formDataMap['ifRoutineAssessment'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）、出科考核（是
              <input name="ifExamination" type="radio" value="Y" <c:if test="${formDataMap['ifExamination'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifExamination" type="radio" value="N" <c:if test="${formDataMap['ifExamination'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）;
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;5、专业基地（科室）（是
              <input name="ifSimulationTrainingProgram" type="radio" value="Y" <c:if test="${formDataMap['ifSimulationTrainingProgram'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifSimulationTrainingProgram" type="radio" value="N" <c:if test="${formDataMap['ifSimulationTrainingProgram'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）有模拟培训计划，（是
              <input name="ifImplementTrainingProgram" type="radio" value="Y" <c:if test="${formDataMap['ifImplementTrainingProgram'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifImplementTrainingProgram" type="radio" value="N" <c:if test="${formDataMap['ifImplementTrainingProgram'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）真正实施；
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;6、医院是否将住院医师培训带教情况纳入绩效考核（是
              <input name="ifSituationBringintoExam" type="radio" value="Y" <c:if test="${formDataMap['ifSituationBringintoExam'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifSituationBringintoExam" type="radio" value="N" <c:if test="${formDataMap['ifSituationBringintoExam'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ），是否发放补贴（是
              <input name="ifGrantSubsidies" type="radio" value="Y" <c:if test="${formDataMap['ifGrantSubsidies'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifGrantSubsidies" type="radio" value="N" <c:if test="${formDataMap['ifGrantSubsidies'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）;
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;7、医院是否有优秀指导老师评选（是
              <input name="ifExcellentTeacherSelection" type="radio" value="Y" <c:if test="${formDataMap['ifExcellentTeacherSelection'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifExcellentTeacherSelection" type="radio" value="N" <c:if test="${formDataMap['ifExcellentTeacherSelection'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ），是否发放经费奖励（是
              <input name="ifGrantRewards" type="radio" value="Y" <c:if test="${formDataMap['ifGrantRewards'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifGrantRewards" type="radio" value="N" <c:if test="${formDataMap['ifGrantRewards'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）;
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;8、医院是否有对指导老师评价（是
              <input name="ifEvaluateTheTeacher" type="radio" value="Y" <c:if test="${formDataMap['ifEvaluateTheTeacher'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifEvaluateTheTeacher" type="radio" value="N" <c:if test="${formDataMap['ifEvaluateTheTeacher'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ），评价对象（学员
              <input name="evaluationObject1" type="checkbox"  <c:if test="${formDataMap['evaluationObject1'] eq 'student'}">checked="checked"</c:if>  value="student" data-role="none"/>
              、同级医师
              <input name="evaluationObject2" type="checkbox" <c:if test="${formDataMap['evaluationObject2'] eq 'peerDoctor'}">checked="checked"</c:if>  value="peerDoctor" data-role="none"/>
              、上级医师
              <input name="evaluationObject3" type="checkbox" <c:if test="${formDataMap['evaluationObject3'] eq 'superDoctor'}">checked="checked"</c:if>  value="superDoctor" data-role="none"/>
              ）；
            </td>
          </tr>
          </table>
        <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
          <tr class="td_group">
            <td class="left" colspan="4" style="vertical-align:middle;"><b>三、满意度</b></td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;对医院培训安排、带教奖励等是否满意（是
              <input name="ifSatisfactionAward" type="radio" value="Y" <c:if test="${formDataMap['ifSatisfactionAward'] eq 'Y'}">checked="checked"</c:if> data-role="none"/>
              否
              <input name="ifSatisfactionAward" type="radio" value="N" <c:if test="${formDataMap['ifSatisfactionAward'] eq 'N'}">checked="checked"</c:if> data-role="none"/>
              ）;
            </td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left" style="vertical-align:middle;">
              &#12288;&#12288;&#12288;不满意部分（培训安排
              <input name="notSatisfaction1" type="checkbox" <c:if test="${formDataMap['notSatisfaction1'] eq 'train'}">checked="checked"</c:if>  value="train" data-role="none"/>
              、带教奖励
              <input name="notSatisfaction2" type="checkbox" <c:if test="${formDataMap['notSatisfaction2'] eq 'teacherAward'}">checked="checked"</c:if>  value="teacherAward" data-role="none"/>
              、学员质量
              <input name="notSatisfaction3" type="checkbox" <c:if test="${formDataMap['notSatisfaction3'] eq 'studentQuality'}">checked="checked"</c:if>  value="studentQuality" data-role="none"/>
              、其他
              <input name="notSatisOther" class="inputText" type="text" value="${formDataMap['notSatisOther']}" data-role="none"/>
              ）；
            </td>
          </tr>
        </table>
        <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
          <tr class="td_group">
            <td class="left" colspan="4" style="vertical-align:middle;"><b>四、意见和建议</b></td>
          </tr>
          <tr class="td_group">
            <td colspan="4" class="left">
              <textarea style="height: 200px;resize: none;" name="opinion">${formDataMap['opinion']}</textarea>
            </td>
          </tr>
        </table>
      </form>
    </div>

	<script>

      $(document).ready(function(){
        $(".left").css("text-align","left");
        $(".right").css("text-align","right");
        $("input").attr("readonly","readonly");
        $("textarea").attr("disabled","disabled");
        $("textarea").css("height","200px");
      });
	</script>
  </div>

 <div data-role="footer">
   <h1></h1>
 </div>
</div> 
</body>
</html>