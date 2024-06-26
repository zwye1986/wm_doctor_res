<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    </jsp:include>

    <script type="text/javascript">
        var isShow;
        function changeShow(obj){
            if($(obj).attr("checked")){
                isShow = obj.value;
            }else{
                isShow = 'Y';
            }

            jboxPostNoLoad('<s:url value="/res/annualCheck/changeShow"/>',{
                isShow:isShow
            },null,null,false);
        }
    </script>

</head>
<body>



<form id="guideForm"  action='<s:url value="/res/annualCheck/getAnnualCheck"/>'>
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table  cellpadding="0" cellspacing="0" class="basic">
                        <h4 style="text-align: center">年度考核填写须知</h4>
                        <p style="text-indent: 27px">年度考核成绩评定实行百分制，日常考核、出科考核及培训手册占10%，理论考核成绩占40%，公共技能考核成绩占10%，技能考核成绩占40%，各科目及总成绩均≥60分为合格。
                        </br>各专业规培秘书需要登记本专业基地除本年度入培、培训年限为三年（如入培时间为2017年、入培年限为3年）、外院轮转人外所有住培学员的各项成绩。专业基地规培秘书需要填写日常考核及出科考核分数、培训手册填写分数、专业理论成绩分数、技能考核名称及该技能考核成绩、公共技能成绩以及上传各年度考核材料。同时，也可以通过导入的方式导入学员成绩。完成各项分数录入后，系统会自动计算总分数。
                        </br>评分方式：</br>
                        日常考核及出科考核：合格给5分，不合格给2分（满分5分）。</br>
                        培训手册填写：合格给5分，不合格给2分（满分5分）。</br>
                        专业理论成绩：实际专业理论成绩分数（百分制）。</br>
                        技能考核成绩：实际技能考核成绩分数（百分制）。</br>
                        公共技能成绩：实际公共技能成绩分数（百分制）。</br>
                        总成绩计算公式：</br>
                        日常考核及出科考核分数+培训手册填写分数+专业理论成绩分数*40%+技能考核成绩分数*40%+公共技能成绩分数*10%
                        </p>
                    </table>

                    <div class="button">
                        <input type="checkbox" value="N" name="isShow" onchange="changeShow(this)"/>不再显示
                        <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()">
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>