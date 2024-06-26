<div style="overflow:hidden;">
    <ul id="tags">
        <li id="tag0"
            <c:if test='${param.pageName=="step1" || empty param.pageName}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step1')" href="javascript:void(0)">封面信息</a></li>
        <li id="tag1"
            <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step2')" href="javascript:void(0)">承诺书</a></li>
        <li id="tag2"
            <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step3')" href="javascript:void(0)">引进团队单位基本情况</a></li>
        <li id="tag3"
            <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step4')" href="javascript:void(0)">引进团队相关科室基本情况</a></li>
        <li id="tag4"
            <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step5')" href="javascript:void(0)">团队基本情况</a></li>
        <li id="tag5"
            <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6')" href="javascript:void(0)">引进团队学科建设总体目标</a></li>
        <li id="tag6"
            <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step7')" href="javascript:void(0)">引进团队临床学科建设</a></li>
        <li id="tag7"
            <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step8')" href="javascript:void(0)">引进团队学科建设规划表</a></li>
        <li id="tag8"
            <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step9')" href="javascript:void(0)">项目经费预算</a></li>
        <li id="tag9"
            <c:if test='${param.pageName=="step10"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step10')" href="javascript:void(0)">团队成员基本情况表</a></li>
        <li id="tag10"
            <c:if test='${param.pageName=="step11"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step11')" href="javascript:void(0)">附件信息</a></li>
    </ul>
</div>