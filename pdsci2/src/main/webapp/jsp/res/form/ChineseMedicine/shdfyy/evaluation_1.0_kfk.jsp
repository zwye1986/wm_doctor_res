<table class="basic" width="100%" style="margin-top: 10px;">
    <tr>
        <th colspan="2" style="text-align: left;">日常考核项目</th>
        <th style="width: 30px;">权重</th>
        <th colspan="2" style="text-align: left;">评价分数</th>
        <th style="width: 35px;">得分</th>
    </tr>
    <tr>
        <td colspan="2">是否遵守院纪院规、科室规章制度</td>
        <td>1</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox"  onchange="single(this)" name="rOne" value="1" <c:if test="${formDataMap['rOne_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox"  onchange="single(this)" name="rOne" value="2" <c:if test="${formDataMap['rOne_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox"  onchange="single(this)" name="rOne" value="3" <c:if test="${formDataMap['rOne_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox"  onchange="single(this)" name="rOne" value="4" <c:if test="${formDataMap['rOne_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox"  onchange="single(this)" name="rOne" value="5" <c:if test="${formDataMap['rOne_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rOne']}">
                    ${formDataMap['rOne']}
                </c:if>
                <input type="hidden" name="rOne" value="${formDataMap['rOne']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rOne']}">${formDataMap['rOne']}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">工作态度是否端正积极</td>
        <td>2</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rTwo" value="1" <c:if test="${formDataMap['rTwo_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTwo" value="2" <c:if test="${formDataMap['rTwo_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTwo" value="3" <c:if test="${formDataMap['rTwo_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTwo" value="4" <c:if test="${formDataMap['rTwo_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTwo" value="5" <c:if test="${formDataMap['rTwo_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rTwo']}">
                    ${formDataMap['rTwo']}
                </c:if>
                <input type="hidden" name="rTwo" value="${formDataMap['rTwo']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rTwo']}">${formDataMap['rTwo']*2}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">完成临床工作的质、量和效率</td>
        <td>3</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rThree" value="1" <c:if test="${formDataMap['rThree_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rThree" value="2" <c:if test="${formDataMap['rThree_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rThree" value="3" <c:if test="${formDataMap['rThree_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rThree" value="4" <c:if test="${formDataMap['rThree_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rThree" value="5" <c:if test="${formDataMap['rThree_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rThree']}">
                    ${formDataMap['rThree']}
                </c:if>
                <input type="hidden" name="rThree" value="${formDataMap['rThree']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rThree']}">${formDataMap['rThree']*3}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">临床思辨能力</td>
        <td>3</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rFour" value="1" <c:if test="${formDataMap['rFour_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFour" value="2" <c:if test="${formDataMap['rFour_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFour" value="3" <c:if test="${formDataMap['rFour_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFour" value="4" <c:if test="${formDataMap['rFour_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFour" value="5" <c:if test="${formDataMap['rFour_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rFour']}">
                    ${formDataMap['rFour']}
                </c:if>
                <input type="hidden" name="rFour" value="${formDataMap['rFour']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rFour']}">${formDataMap['rFour']*3}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">操作技能水平</td>
        <td>3</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rFive" value="1" <c:if test="${formDataMap['rFive_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFive" value="2" <c:if test="${formDataMap['rFive_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFive" value="3" <c:if test="${formDataMap['rFive_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFive" value="4" <c:if test="${formDataMap['rFive_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rFive" value="5" <c:if test="${formDataMap['rFive_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rFive']}">
                    ${formDataMap['rFive']}
                </c:if>
                <input type="hidden" name="rFive" value="${formDataMap['rFive']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rFive']}">${formDataMap['rFive']*3}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">医患沟通能力（有无病患投诉）</td>
        <td>2</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rSix" value="1" <c:if test="${formDataMap['rSix_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSix" value="2" <c:if test="${formDataMap['rSix_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSix" value="3" <c:if test="${formDataMap['rSix_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSix" value="4" <c:if test="${formDataMap['rSix_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSix" value="5" <c:if test="${formDataMap['rSix_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rSix']}">
                    ${formDataMap['rSix']}
                </c:if>
                <input type="hidden" name="rSix" value="${formDataMap['rSix']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rSix']}">${formDataMap['rSix']*2}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">本科室中的合作团队精神</td>
        <td>2</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rSeven" value="1" <c:if test="${formDataMap['rSeven_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSeven" value="2" <c:if test="${formDataMap['rSeven_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSeven" value="3" <c:if test="${formDataMap['rSeven_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSeven" value="4" <c:if test="${formDataMap['rSeven_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rSeven" value="5" <c:if test="${formDataMap['rSeven_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rSeven']}">
                    ${formDataMap['rSeven']}
                </c:if>
                <input type="hidden" name="rSeven" value="${formDataMap['rSeven']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rSeven']}">${formDataMap['rSeven']*2}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">与外界沟通协调能力</td>
        <td>2</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rEight" value="1" <c:if test="${formDataMap['rEight_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rEight" value="2" <c:if test="${formDataMap['rEight_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rEight" value="3" <c:if test="${formDataMap['rEight_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rEight" value="4" <c:if test="${formDataMap['rEight_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rEight" value="5" <c:if test="${formDataMap['rEight_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rEight']}">
                    ${formDataMap['rEight']}
                </c:if>
                <input type="hidden" name="rEight" value="${formDataMap['rEight']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rEight']}">${formDataMap['rEight']*2}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">应变能力</td>
        <td>1</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rNine" value="1" <c:if test="${formDataMap['rNine_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rNine" value="2" <c:if test="${formDataMap['rNine_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rNine" value="3" <c:if test="${formDataMap['rNine_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rNine" value="4" <c:if test="${formDataMap['rNine_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rNine" value="5" <c:if test="${formDataMap['rNine_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rNine']}">
                    ${formDataMap['rNine']}
                </c:if>
                <input type="hidden" name="rNine" value="${formDataMap['rNine']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rNine']}">${formDataMap['rNine']}</c:if></td>
    </tr>
    <tr>
        <td colspan="2">自我学习能力</td>
        <td>1</td>
        <td colspan="2">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <label><input type="checkbox" onchange="single(this)" name="rTen" value="1" <c:if test="${formDataMap['rTen_id']=='1'}">checked</c:if> class="autoValue"/>&nbsp;5&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTen" value="2" <c:if test="${formDataMap['rTen_id']=='2'}">checked</c:if> class="autoValue"/>&nbsp;4&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTen" value="3" <c:if test="${formDataMap['rTen_id']=='3'}">checked</c:if> class="autoValue"/>&nbsp;3&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTen" value="4" <c:if test="${formDataMap['rTen_id']=='4'}">checked</c:if> class="autoValue"/>&nbsp;2&#12288;&#12288;</label>
                <label><input type="checkbox" onchange="single(this)" name="rTen" value="5" <c:if test="${formDataMap['rTen_id']=='5'}">checked</c:if> class="autoValue"/>&nbsp;1&#12288;&#12288;</label>
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['rTen']}">
                    ${formDataMap['rTen']}
                </c:if>
                <input type="hidden" name="rTen" value="${formDataMap['rTen']}"/>
            </c:if>
        </td>
        <td class="cSum"><c:if test="${!empty formDataMap['rTen']}">${formDataMap['rTen']}</c:if></td>
    </tr>
    <tr>
        <td colspan="5">日常考核表（总分100）</td>
        <td id="cSum"></td>
    </tr>
</table>

<table class="basic" width="100%" style="margin-top: 10px;">
    <tr>
        <th colspan="4" style="text-align: left;">出科考核项目</th>
        <th style="text-align: left;">实际得分(百分制)</th>
        <th style="text-align: left;">所占<br/>比例</th>
        <th style="width: 35px;" style="text-align: left;">折算<br/>得分</th>
    </tr>
    <tr>
        <td colspan="4">日常工作表现（10%)</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cZero" value="${formDataMap['cZero']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cZero']}">
                    ${formDataMap['cZero']}
                </c:if>
                <input type="hidden" name="cZero" value="${formDataMap['cZero']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cZero']*0.1}</td>
    </tr>
    <tr>
        <td rowspan="3" colspan="2">常规考核（30%)</td>
        <td colspan="2">病史书写</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cOne" value="${formDataMap['cOne']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cOne']}">
                    ${formDataMap['cOne']}
                </c:if>
                <input type="hidden" name="cOne" value="${formDataMap['cOne']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cOne']*0.1}</td>
    </tr>
    <tr>
        <td colspan="2">出勤情况</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cTwo" value="${formDataMap['cTwo']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cTwo']}">
                    ${formDataMap['cTwo']}
                </c:if>
                <input type="hidden" name="cTwo" value="${formDataMap['cTwo']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cTwo']*0.1}</td>
    </tr>
    <tr>
        <td colspan="2">完成轮转计划、登记手册填写</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cThree" value="${formDataMap['cThree']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cThree']}">
                    ${formDataMap['cThree']}
                </c:if>
                <input type="hidden" name="cThree" value="${formDataMap['cThree']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cThree']*0.1}</td>
    </tr>
    <tr>
        <td rowspan="5" colspan="2">临床综合能力考核（60%）</td>
        <td colspan="2">理论考试</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cFour" value="${formDataMap['cFour']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cFour']}">
                    ${formDataMap['cFour']}
                </c:if>
                <input type="hidden" name="cFour" value="${formDataMap['cFour']}"/>
            </c:if>
        </td>
        <td>20.0%</td>
        <td class="sSum">${formDataMap['cFour']*0.2}</td>
    </tr>
    <tr>
        <td colspan="2">病史采集</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cFive" value="${formDataMap['cFive']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cFive']}">
                    ${formDataMap['cFive']}
                </c:if>
                <input type="hidden" name="cFive" value="${formDataMap['cFive']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cFive']*0.1}</td>
    </tr>
    <tr>
        <td colspan="2">体格检查</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cSix" value="${formDataMap['cSix']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cSix']}">
                    ${formDataMap['cSix']}
                </c:if>
                <input type="hidden" name="cSix" value="${formDataMap['cSix']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cSix']*0.1}</td>
    </tr>
    <tr>
        <td colspan="2">康复评定</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cTen" value="${formDataMap['cTen']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cTen']}">
                    ${formDataMap['cTen']}
                </c:if>
                <input type="hidden" name="cTen" value="${formDataMap['cTen']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cTen']*0.1}</td>
    </tr>
    <tr>
        <td colspan="2">读书报告</td>
        <td style="text-align: center">
            <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
                <input class="validate[custom[integer],min[0],max[100]]"type="text" name="cNine" value="${formDataMap['cNine']}" onchange="count(this)"
                       style="width: 50%;">
            </c:if>
            <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
                <c:if test="${!empty formDataMap['cNine']}">
                    ${formDataMap['cNine']}
                </c:if>
                <input type="hidden" name="cNine" value="${formDataMap['cNine']}"/>
            </c:if>
        </td>
        <td>10.0%</td>
        <td class="sSum">${formDataMap['cNine']*0.1}</td>
    </tr>
    <tr>
        <td colspan="6">出科考核总分(100分)</td>
        <td id="sSum"></td>
    </tr>
</table>