<div style="overflow:hidden;">
    <ul id="tags">
        <li id="tag0"
            <c:if test='${param.pageName=="step1" || empty param.pageName}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step1')" href="javascript:void(0)">封面信息</a></li>
        <li id="tag1"
            <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step2')" href="javascript:void(0)">简表</a></li>
        <li id="tag2"
            <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step3')" href="javascript:void(0)">推广项目内容</a></li>
        <li id="tag3"
            <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step4')" href="javascript:void(0)">推广方式方法</a></li>
        <li id="tag4"
            <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step5')" href="javascript:void(0)">推广计划进度</a></li>
        <li id="tag5"
            <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6')" href="javascript:void(0)">推广工作条件保障</a></li>
        <li id="tag6"
            <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step7')" href="javascript:void(0)">推广工作目标与效益</a></li>
        <li id="tag7"
            <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step8')" href="javascript:void(0)">推广单位项目组成员</a></li>
        <li id="tag8"
            <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step9')" href="javascript:void(0)">受推单位及项目负责人</a></li>
        <li id="tag9"
            <c:if test='${param.pageName=="step10"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step10')" href="javascript:void(0)">项目经费使用预算</a></li>
        <li id="tag10"
            <c:if test='${param.pageName=="step11"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step11')" href="javascript:void(0)">审核意见</a></li>
    </ul>
</div>