<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div style="overflow:hidden;">
<ul id="tags">
    <li id='tag0' <c:if test='${param.pageName=="step1" || empty param.pageName}'>class="selectTag"</c:if>><a onclick="nextOpt('step1')" href="javascript:void(0)">项目概况</a></li>
    <li id='tag1' <c:if test='${param.pageName=="step2"}'>class="selectTag"</c:if>><a onclick="nextOpt('step2')" href="javascript:void(0)">名称/团队合作简介</a></li>
    <li id='tag2' <c:if test='${param.pageName=="step3"}'>class="selectTag"</c:if>><a onclick="nextOpt('step3')" href="javascript:void(0)">引进团队/依托科室简介</a></li>
    <li id='tag3' <c:if test='${param.pageName=="step4"}'>class="selectTag"</c:if>><a onclick="nextOpt('step4')" href="javascript:void(0)">团队合作周期目标</a></li>
    <li id='tag4' <c:if test='${param.pageName=="step5"}'>class="selectTag"</c:if>><a onclick="nextOpt('step5')" href="javascript:void(0)">团队合作年度目标</a></li>
    <li id='tag5' <c:if test='${param.pageName=="step6"}'>class="selectTag"</c:if>><a onclick="nextOpt('step6')" href="javascript:void(0)">引进团队及依托科室人员组成及分工</a></li>
    <li id='tag6' <c:if test='${param.pageName=="step7"}'>class="selectTag"</c:if>><a onclick="nextOpt('step7')" href="javascript:void(0)">合作协议</a></li>
    <li id='tag7' <c:if test='${param.pageName=="step8"}'>class="selectTag"</c:if>><a onclick="nextOpt('step8')" href="javascript:void(0)">经费预算</a></li>
    <li id='tag8' <c:if test='${param.pageName=="step9"}'>class="selectTag"</c:if>><a onclick="nextOpt('step9')" href="javascript:void(0)">专家团队项目合同审核</a></li>
</ul>
</div>