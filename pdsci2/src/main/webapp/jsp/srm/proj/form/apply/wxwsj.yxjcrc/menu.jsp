<div style="overflow:hidden;">
    <ul id="tags">
        <li id="tag0"
            <c:if test='${param.pageName=="step0" || empty param.pageName}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step0')" href="javascript:void(0)">基本信息</a></li>
        <li id="tag1"
            <c:if test='${param.pageName=="step1"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step1')" href="javascript:void(0)">医学杰出人才个人概况</a></li>
        <li id="tag2"
            <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step2')" href="javascript:void(0)">医学杰出人才简历</a></li>
        <li id="tag3"
            <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step3')" href="javascript:void(0)">医学杰出人才主要业绩</a></li>
        <li id="tag4"
            <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step4')" href="javascript:void(0)">依托课题立题依据</a></li>
        <li id="tag5"
            <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step5')" href="javascript:void(0)">依托课题研究内容</a></li>
        <li id="tag6"
            <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6')" href="javascript:void(0)">课题组成员名单</a></li>
        <li id="tag7"
            <c:if test='${param.pageName=="step6_1"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step6_1')" href="javascript:void(0)">项目规划</a></li>
        <li id="tag8"
            <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step7')" href="javascript:void(0)">经费预算</a></li>
        <li id="tag9"
            <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step8')" href="javascript:void(0)">审核意见</a></li>
        <li id="tag10"
            <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a
                onclick="nextOpt('step9')" href="javascript:void(0)">附件信息</a></li>
    </ul>
</div>