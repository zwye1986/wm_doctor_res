<div style="overflow:hidden;">
    <ul id="tags">
        <li id="tag0"
            <c:if test='${param.pageName=="step1" || empty param.pageName}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step1')" href="javascript:void(0)">封面信息</a></li>
        <li id="tag1"
            <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step2')" href="javascript:void(0)">基本情况</a></li>
        <li id="tag2"
            <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step3')" href="javascript:void(0)">课题组主要成员情况表</a></li>
        <li id="tag3"
            <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step4')" href="javascript:void(0)">立项依据</a></li>
        <li id="tag4"
            <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step5')" href="javascript:void(0)">研究目标、可行性分析</a></li>
        <li id="tag5"
            <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6')" href="javascript:void(0)">研究基础与工作条件</a></li>
        <li id="tag6"
            <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step7')" href="javascript:void(0)">实施计划、考核指标</a></li>
        <li id="tag7"
            <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step8')" href="javascript:void(0)">经费预算分类细目</a></li>
        <li id="tag8"
            <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step9')" href="javascript:void(0)">保证与审核</a></li>
        <li id="tag9"
            <c:if test='${param.pageName=="step10"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step10')" href="javascript:void(0)">附件目录</a></li>

    </ul>
</div>