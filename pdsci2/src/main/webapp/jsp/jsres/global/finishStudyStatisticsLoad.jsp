<div class="statistics-row row-doctor">
    <div class="statistics-row-title">住院医师</div>

    <div class="statistics-row-item">
        <div>应考人数</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>实考人数</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['realExam'] ? 0 : doctorTrainingMap['realExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>缺考人数</div>
        <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']) - (empty doctorTrainingMap['realExam'] ? 0 : doctorTrainingMap['realExam'])}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>首考人数</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['firstExam'] ? 0 : doctorTrainingMap['firstExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>补考人数</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['secondExam'] ? 0 : doctorTrainingMap['secondExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>首考通过率</div>
        <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['firstPassPercent'] ? 0 : doctorTrainingMap['firstPassPercent'])}</span>%</div>
    </div>
    <div class="statistics-row-item">
        <div>补考通过率</div>
        <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['secondPassPercent'] ? 0 : doctorTrainingMap['secondPassPercent'])}</span>%</div>
    </div>
    <div class="statistics-row-item">
        <div>综合通过率</div>
        <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['PassPercent'] ? 0 : doctorTrainingMap['PassPercent'])}</span>%</div>
    </div>
</div>

<div class="statistics-row row-master">
    <div class="statistics-row-title">在校专硕</div>

    <div class="statistics-row-item">
        <div>应考人数</div>
        <div><span class="statistics-row-num green">${empty graduateMap['all'] ? 0 : graduateMap['all']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>实考人数</div>
        <div><span class="statistics-row-num green">${empty graduateMap['realExam'] ? 0 : graduateMap['realExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>缺考人数</div>
        <div><span class="statistics-row-num green">${(empty graduateMap['all'] ? 0 : graduateMap['all']) - (empty graduateMap['realExam'] ? 0 : graduateMap['realExam'])}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>首考人数</div>
        <div><span class="statistics-row-num green">${empty graduateMap['firstExam'] ? 0 : graduateMap['firstExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>补考人数</div>
        <div><span class="statistics-row-num green">${empty graduateMap['secondExam'] ? 0 : graduateMap['secondExam']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>首考通过率</div>
        <div><span class="statistics-row-num green">${(empty graduateMap['firstPassPercent'] ? 0 : graduateMap['firstPassPercent'])}</span>%</div>
    </div>
    <div class="statistics-row-item">
        <div>补考通过率</div>
        <div><span class="statistics-row-num green">${(empty graduateMap['secondPassPercent'] ? 0 : graduateMap['secondPassPercent'])}</span>%</div>
    </div>
    <div class="statistics-row-item">
        <div>综合通过率</div>
        <div><span class="statistics-row-num green">${(empty graduateMap['PassPercent'] ? 0 : graduateMap['PassPercent'])}</span>%</div>
    </div>
</div>

<div class="table-container">
    <table cellspacing="0" border="1" class="table">
        <thead>
        <tr>
            <td rowspan="3" class="total-all-td">城市划分</td>
            <td rowspan="3" class="total-all-td-left">培训基地</td>
            <td colspan="2">应考人数</td>
            <td rowspan="3" class="total-td">合计</td>
            <td colspan="2">实考人数</td>
            <td rowspan="3" class="total-td">合计</td>
            <td colspan="2">首考人数</td>
            <td rowspan="3" class="total-td">合计</td>
            <td colspan="2">补考人数</td>
            <td rowspan="3" class="total-td">合计</td>
            <td colspan="2">缺考人数</td>
            <td rowspan="3" class="total-td">合计</td>
            <td colspan="4" style="width: 220px">首考通过率</td>
            <td rowspan="3" class="total-td">平均通过率</td>
            <td colspan="4" style="width: 220px">补考通过率</td>
            <td rowspan="3" class="total-td">平均通过率</td>
            <td colspan="4" style="width: 220px">综合通过率</td>
            <td rowspan="3" class="total-td">平均通过率</td>
        </tr>
        <tr>
            <td rowspan="2">理论考试</td>
            <td rowspan="2">技能考试</td>
            <td rowspan="2">理论考试</td>
            <td rowspan="2">技能考试</td>
            <td rowspan="2">理论考试</td>
            <td rowspan="2">技能考试</td>
            <td rowspan="2">理论考试</td>
            <td rowspan="2">技能考试</td>
            <td rowspan="2">理论考试</td>
            <td rowspan="2">技能考试</td>
            <td colspan="2">理论考试</td>
            <td colspan="2">技能考试</td>
            <td colspan="2">理论考试</td>
            <td colspan="2">技能考试</td>
            <td colspan="2">理论考试</td>
            <td colspan="2">技能考试</td>
        </tr>
        <tr>
            <td>住院医师</td>
            <td>在校专硕</td>
            <td>住院医师</td>
            <td>在校专硕</td>
            <td>住院医师</td>
            <td>在校专硕</td>
            <td>住院医师</td>
            <td>在校专硕</td>
            <td>住院医师</td>
            <td>在校专硕</td>
            <td>住院医师</td>
            <td>在校专硕</td>
        </tr>
        </thead>
        <!-- 主体 -->
        <tbody>
        <c:forEach items="${resultList}" var="result">
            <tr>
                <td class="total-all-td">${result['orgCityName']}</td>
                <td class="total-all-td-left">${result['orgName']}</td>
                <td>${empty result['Theory'] ? 0 : result['Theory']}</td>
                <td>${empty result['Skill'] ? 0 : result['Skill']}</td>
                <td class="total-td-text">${empty result['all'] ? 0 : result['all']}</td>
                <td>${empty result['realExamTheory'] ? 0 : result['realExamTheory']}</td>
                <td>${empty result['realExamSkill'] ? 0 : result['realExamSkill']}</td>
                <td class="total-td-text">${empty result['realExamAll'] ? 0 : result['realExamAll']}</td>
                <td>${(empty result['realExamGraduationTheoryFirst'] ? 0 : result['realExamGraduationTheoryFirst']) + (empty result['realExamDoctorTheoryFirst'] ? 0 : result['realExamDoctorTheoryFirst'])}</td>
                <td>${(empty result['realExamGraduationSkillFirst'] ? 0 : result['realExamGraduationSkillFirst']) + (empty result['realExamDoctorSkillFirst'] ? 0 : result['realExamDoctorSkillFirst'])}</td>
                <td class="total-td-text">${empty result['firstExam'] ? 0 : result['firstExam']}</td>
                <td>${(empty result['realExamGraduationTheorySecond'] ? 0 : result['realExamGraduationTheorySecond']) + (empty result['realExamDoctorTheorySecond'] ? 0 : result['realExamDoctorTheorySecond'])}</td>
                <td>${(empty result['realExamGraduationSkillSecond'] ? 0 : result['realExamGraduationSkillSecond']) + (empty result['realExamDoctorSkillSecond'] ? 0 : result['realExamDoctorSkillSecond'])}</td>
                <td class="total-td-text">${empty result['secondExam'] ? 0 : result['secondExam']}</td>
                <td>${empty result['missTheory'] ? 0 : result['missTheory']}</td>
                <td>${empty result['missSkill'] ? 0 : result['missSkill']}</td>
                <td class="total-td-text">${empty result['missAll'] ? 0 : result['missAll']}</td>
                <td>${empty result['doctorTheoryFirstPassPercent'] ? 0 : result['doctorTheoryFirstPassPercent']}%</td>
                <td>${empty result['graduationTheoryFirstPassPercent'] ? 0 : result['graduationTheoryFirstPassPercent']}%</td>
                <td>${empty result['doctorSkillFirstPassPercent'] ? 0 : result['doctorSkillFirstPassPercent']}%</td>
                <td>${empty result['graduationSkillFirstPassPercent'] ? 0 : result['graduationSkillFirstPassPercent']}%</td>
                <td class="total-td-text">${empty result['firstPassPercent'] ? 0 : result['firstPassPercent']}%</td>
                <td>${empty result['doctorTheorySecondPassPercent'] ? 0 : result['doctorTheorySecondPassPercent']}%</td>
                <td>${empty result['graduationTheorySecondPassPercent'] ? 0 : result['graduationTheorySecondPassPercent']}%</td>
                <td>${empty result['doctorSkillSecondPassPercent'] ? 0 : result['doctorSkillSecondPassPercent']}%</td>
                <td>${empty result['graduationSkillSecondPassPercent'] ? 0 : result['graduationSkillSecondPassPercent']}%</td>
                <td class="total-td-text">${empty result['secondPassPercent'] ? 0 : result['secondPassPercent']}%</td>
                <td>${empty result['doctorTheoryPassPercent'] ? 0 : result['doctorTheoryPassPercent']}%</td>
                <td>${empty result['graduationTheoryPassPercent'] ? 0 : result['graduationTheoryPassPercent']}%</td>
                <td>${empty result['doctorSkillPassPercent'] ? 0 : result['doctorSkillPassPercent']}%</td>
                <td>${empty result['graduationSkillPassPercent'] ? 0 : result['graduationSkillPassPercent']}%</td>
                <td class="total-td-text">${empty result['PassPercent'] ? 0 : result['PassPercent']}%</td>
            </tr>
        </c:forEach>
        </tbody>

        <!-- footer -->
        <tfoot>
        <td colspan="2" class="total-all-td">总计</td>
        <td>${empty footMap['Theory'] ? 0 : footMap['Theory']}</td>
        <td>${empty footMap['Skill'] ? 0 : footMap['Skill']}</td>
        <td class="total-td-text">${empty footMap['all'] ? 0 : footMap['all']}</td>
        <td>${empty footMap['realExamTheory'] ? 0 : footMap['realExamTheory']}</td>
        <td>${empty footMap['realExamSkill'] ? 0 : footMap['realExamSkill']}</td>
        <td class="total-td-text">${empty footMap['realExamAll'] ? 0 : footMap['realExamAll']}</td>
        <td>${(empty footMap['realExamGraduationTheoryFirst'] ? 0 : footMap['realExamGraduationTheoryFirst']) + (empty footMap['realExamDoctorTheoryFirst'] ? 0 : footMap['realExamDoctorTheoryFirst'])}</td>
        <td>${(empty footMap['realExamGraduationSkillFirst'] ? 0 : footMap['realExamGraduationSkillFirst']) + (empty footMap['realExamDoctorSkillFirst'] ? 0 : footMap['realExamDoctorSkillFirst'])}</td>
        <td class="total-td-text">${empty footMap['firstExam'] ? 0 : footMap['firstExam']}</td>
        <td>${(empty footMap['realExamGraduationTheorySecond'] ? 0 : footMap['realExamGraduationTheorySecond']) + (empty footMap['realExamDoctorTheorySecond'] ? 0 : footMap['realExamDoctorTheorySecond'])}</td>
        <td>${(empty footMap['realExamGraduationSkillSecond'] ? 0 : footMap['realExamGraduationSkillSecond']) + (empty footMap['realExamDoctorSkillSecond'] ? 0 : footMap['realExamDoctorSkillSecond'])}</td>
        <td class="total-td-text">${empty footMap['secondExam'] ? 0 : footMap['secondExam']}</td>
        <td>${empty footMap['missTheory'] ? 0 : footMap['missTheory']}</td>
        <td>${empty footMap['missSkill'] ? 0 : footMap['missSkill']}</td>
        <td class="total-td-text">${empty footMap['missAll'] ? 0 : footMap['missAll']}</td>
        <td>${empty footMap['doctorTheoryFirstPassPercent'] ? 0 : footMap['doctorTheoryFirstPassPercent']}%</td>
        <td>${empty footMap['graduationTheoryFirstPassPercent'] ? 0 : footMap['graduationTheoryFirstPassPercent']}%</td>
        <td>${empty footMap['doctorSkillFirstPassPercent'] ? 0 : footMap['doctorSkillFirstPassPercent']}%</td>
        <td>${empty footMap['graduationSkillFirstPassPercent'] ? 0 : footMap['graduationSkillFirstPassPercent']}%</td>
        <td class="total-td-text">${empty footMap['firstPassPercent'] ? 0 : footMap['firstPassPercent']}%</td>
        <td>${empty footMap['doctorTheorySecondPassPercent'] ? 0 : footMap['doctorTheorySecondPassPercent']}%</td>
        <td>${empty footMap['graduationTheorySecondPassPercent'] ? 0 : footMap['graduationTheorySecondPassPercent']}%</td>
        <td>${empty footMap['doctorSkillSecondPassPercent'] ? 0 : footMap['doctorSkillSecondPassPercent']}%</td>
        <td>${empty footMap['graduationSkillSecondPassPercent'] ? 0 : footMap['graduationSkillSecondPassPercent']}%</td>
        <td class="total-td-text">${empty footMap['secondPassPercent'] ? 0 : footMap['secondPassPercent']}%</td>
        <td>${empty footMap['doctorTheoryPassPercent'] ? 0 : footMap['doctorTheoryPassPercent']}%</td>
        <td>${empty footMap['graduationTheoryPassPercent'] ? 0 : footMap['graduationTheoryPassPercent']}%</td>
        <td>${empty footMap['doctorSkillPassPercent'] ? 0 : footMap['doctorSkillPassPercent']}%</td>
        <td>${empty footMap['graduationSkillPassPercent'] ? 0 : footMap['graduationSkillPassPercent']}%</td>
        <td class="total-td-text">${empty footMap['PassPercent'] ? 0 : footMap['PassPercent']}%</td>
        </tfoot>
    </table>
</div>