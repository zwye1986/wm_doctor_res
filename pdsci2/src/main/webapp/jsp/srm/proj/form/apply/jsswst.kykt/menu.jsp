<div style="overflow:hidden;">
    <ul id="tags">
        <li id="tag0"
            <c:if test='${param.pageName=="step1" || empty param.pageName}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step1')" href="javascript:void(0)">封面信息</a></li>
        <li id="tag1"
            <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step2')" href="javascript:void(0)">立项依据</a></li>
        <li id="tag2"
            <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step3')" href="javascript:void(0)">科研假说或技术构思</a></li>
        <li id="tag3"
            <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step4')" href="javascript:void(0)">研究试验方法及技术路线</a></li>
        <li id="tag4"
            <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step5')" href="javascript:void(0)">现有工作条件和基础</a></li>
        <li id="tag5"
            <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6')" href="javascript:void(0)">计划进度</a></li>
        <li id="tag6"
            <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step7')" href="javascript:void(0)">意见及具体分工</a></li>
        <li id="tag7"
            <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step8')" href="javascript:void(0)">经费概算</a></li>
        <li id="tag8"
            <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step9')" href="javascript:void(0)">主要研究人员</a></li>
        <li id="tag9"
            <c:if test='${param.pageName=="step10"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step10')" href="javascript:void(0)">单位意见</a></li>
        <li id="tag10"
            <c:if test='${param.pageName=="step11"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step11')" href="javascript:void(0)">附件信息</a></li>
    </ul>
</div>