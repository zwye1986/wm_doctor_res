<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
    <jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script>
$(document).ready(function(){
	$('#regBeginDate , #regEndDate').datepicker();
	$('#swapBeginDate , #swapEndDate').datepicker();
	$('#wishBeginDate , #wishEndDate').datepicker();
	$('#admitBeginDate , #admitEndDate').datepicker();
	$('#graduationBeginDate , #graduationEndDate').datepicker();
    <c:forEach items="${speList}" var="spe">
        $("#${spe}").attr("checked","checked");
    </c:forEach>
});

function submitDateCfg(type){
	var beginDateId = type+"BeginDate";
	var endDateId = type+"EndDate";
	if(!$("#"+beginDateId).validationEngine("validate") && !$("#"+endDateId).validationEngine("validate")){
		var beginDate = $("#"+beginDateId).val();
		var endDate = $("#"+endDateId).val();
		if(beginDate>endDate){
			jboxTip("结束日期不可小于开始日期");
			$("#"+beginDateId).focus();
			return ;
		}
		var url = "<s:url value='/sczyres/manage/savedatecfg'/>";
		var reqdata = {};
		reqdata[beginDateId] = beginDate;
		reqdata[endDateId] = endDate;
		jboxConfirm("确认提交？" , function(){
			jboxPost(url , reqdata , function(){
				cfg();
			} , null , true);
		});
	}
	
}
function editDateCfg(type){
	var beginDateId = type+"BeginDate";
	var endDateId = type+"EndDate";
	$("#existSpan_"+type).hide();
	$("#editSpan_"+type).show();
	$("#"+beginDateId).val($("#"+beginDateId+"Span").html());
	$("#"+endDateId).val($("#"+endDateId+"Span").html());
}
function changSpeAllow(){
    var datas = "";
    $("[name='speAllow']:checked").each(function(){
        datas += $(this).val() + ",";
    })
    datas = datas.substr(0, datas.length - 1);
    jboxPost("<s:url value='/sczyres/manage/changSpeAllow'/>?datas="+datas,null,function(resp){
        if(resp==1){
            jboxTip('操作成功');
        }
    },null,false);
}
function changAllowReduction(){
    var data = $("[name='allowReduction']:checked").val();
    jboxPost("<s:url value='/sczyres/manage/changAllowReduction'/>?data="+data,null,function(resp){
        if(resp==1){
            jboxTip('操作成功');
        }
    },null,false);
}
</script>
        <div class="main_hd">
          <h2>招录设置</h2>
        </div>
        <div class="main_bd">
          <div class="bd_tips">
            <div class="desc">
            <h4>当前报名${regDateCfgMsg.msg}</h4>
            <p>报名截止时间：
                <c:if test='${recruitCfg.regBeginDate==null && recruitCfg.regEndDate==null}'>
                    <input type="text" readonly="readonly" class="validate[required] input" id="regBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="regEndDate" style="width: 100px;"/>
                    &#12288;&#12288;<a onclick="submitDateCfg('reg');">确认</a>
                </c:if>
                <c:if test='${recruitCfg.regBeginDate!=null && recruitCfg.regEndDate!=null}'>
                    <span id="existSpan_reg"><span id="regBeginDateSpan">${recruitCfg.regBeginDate}</span> ~ <span id="regEndDateSpan">${recruitCfg.regEndDate}</span>&#12288;&#12288;<a onclick="editDateCfg('reg');">修改</a></span>
                    <span id="editSpan_reg" style="display: none;">
                        <input type="text" readonly="readonly" class="validate[required] input" id="regBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="regEndDate" style="width: 100px;"/>
                        &#12288;&#12288;<a onclick="submitDateCfg('reg');">确认</a>
                    </span>
                </c:if> 
            </p>
            </div>
          </div>

            <%--<div class="bd_tips">--%>
                <%--<div class="desc">--%>
                    <%--<h4>未录取学员志愿填报日期${wishDateCfgMsg.msg}</h4>--%>
                    <%--<p>未录取学员志愿填报日期范围：--%>
                        <%--<c:if test='${recruitCfg.wishBeginDate==null && recruitCfg.wishEndDate==null}'>--%>
                            <%--<input type="text" readonly="readonly" class="validate[required] input" id="wishBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="wishEndDate" style="width: 100px;"/>--%>
                            <%--&#12288;&#12288;<a onclick="submitDateCfg('wish');">确认</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test='${recruitCfg.wishBeginDate!=null && recruitCfg.wishEndDate!=null}'>--%>
                            <%--<span id="existSpan_wish"><span id="wishBeginDateSpan">${recruitCfg.wishBeginDate}</span> ~ <span id="wishEndDateSpan">${recruitCfg.wishEndDate}</span>&#12288;&#12288;<a onclick="editDateCfg('wish');">修改</a></span>--%>
                    <%--<span id="editSpan_wish" style="display: none;">--%>
                        <%--<input type="text" readonly="readonly" class="validate[required] input" id="wishBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="wishEndDate" style="width: 100px;"/>--%>
                        <%--&#12288;&#12288;<a onclick="submitDateCfg('wish');">确认</a>--%>
                    <%--</span>--%>
                        <%--</c:if>--%>
                    <%--</p>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="bd_tips">
                <div class="desc">
                    <h4>学员确认日期${admitDateCfgMsg.msg}</h4>
                    <p>学员确认日期范围：
                        <c:if test='${recruitCfg.admitBeginDate==null && recruitCfg.admitEndDate==null}'>
                            <input type="text" readonly="readonly" class="validate[required] input" id="admitBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="admitEndDate" style="width: 100px;"/>
                            &#12288;&#12288;<a onclick="submitDateCfg('admit');">确认</a>
                        </c:if>
                        <c:if test='${recruitCfg.admitBeginDate!=null && recruitCfg.admitEndDate!=null}'>
                            <span id="existSpan_admit"><span id="admitBeginDateSpan">${recruitCfg.admitBeginDate}</span> ~ <span id="admitEndDateSpan">${recruitCfg.admitEndDate}</span>&#12288;&#12288;<a onclick="editDateCfg('admit');">修改</a></span>
                    <span id="editSpan_admit" style="display: none;">
                        <input type="text" readonly="readonly" class="validate[required] input" id="admitBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="admitEndDate" style="width: 100px;"/>
                        &#12288;&#12288;<a onclick="submitDateCfg('admit');">确认</a>
                    </span>
                        </c:if>
                    </p>
                </div>
            </div>

            <div class="bd_tips">
                <div class="desc">
                    <h4>当前学员调剂日期${swapDateCfgMsg.msg}</h4>
                    <p>学员调剂日期范围：
                        <c:if test='${recruitCfg.swapBeginDate==null && recruitCfg.swapEndDate==null}'>
                            <input type="text" readonly="readonly" class="validate[required] input" id="swapBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="swapEndDate" style="width: 100px;"/>
                            &#12288;&#12288;<a onclick="submitDateCfg('swap');">确认</a>
                        </c:if>
                        <c:if test='${recruitCfg.swapBeginDate!=null && recruitCfg.swapEndDate!=null}'>
                            <span id="existSpan_swap"><span id="swapBeginDateSpan">${recruitCfg.swapBeginDate}</span> ~ <span id="swapEndDateSpan">${recruitCfg.swapEndDate}</span>&#12288;&#12288;<a onclick="editDateCfg('swap');">修改</a></span>
                    <span id="editSpan_swap" style="display: none;">
                        <input type="text" readonly="readonly" class="validate[required] input" id="swapBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="swapEndDate" style="width: 100px;"/>
                        &#12288;&#12288;<a onclick="submitDateCfg('swap');">确认</a>
                    </span>
                        </c:if>
                    </p>
                </div>
            </div>

          <div class="bd_tips">
            <div class="desc">
            <h4>当前结业申请日期${graduationDateCfgMsg.msg}</h4>
            <p>学员结业申请范围：
                <c:if test='${recruitCfg.graduationBeginDate==null && recruitCfg.graduationEndDate==null}'>
                    <input type="text" readonly="readonly" class="validate[required] input" id="graduationBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="graduationEndDate" style="width: 100px;"/>
                    &#12288;&#12288;<a onclick="submitDateCfg('graduation');">确认</a>
                </c:if>
                <c:if test='${recruitCfg.graduationBeginDate!=null && recruitCfg.graduationEndDate!=null}'>
                    <span id="existSpan_graduation"><span id="graduationBeginDateSpan">${recruitCfg.graduationBeginDate}</span> ~ <span id="graduationEndDateSpan">${recruitCfg.graduationEndDate}</span>&#12288;&#12288;<a onclick="editDateCfg('graduation');">修改</a></span>
                    <span id="editSpan_graduation" style="display: none;">
                        <input type="text" readonly="readonly" class="validate[required] input" id="graduationBeginDate" style="width: 100px;"/> ~ <input type="text" readonly="readonly" class="validate[required] input" id="graduationEndDate" style="width: 100px;"/>
                        &#12288;&#12288;<a onclick="submitDateCfg('graduation');">确认</a>
                    </span>
                </c:if> 
            </p>
            </div>
          </div>

          <div class="bd_tips">
              <div class="desc">
                <h4>当前可填报培训专业：</h4>
              <div style="font-size: 15px;line-height: 20px;">
              <c:forEach items="${speCatEnumList}" var="spe">
                  <label><input type="checkbox" name="speAllow" id="${spe.id}" value="${spe.id}" onclick="changSpeAllow()">${spe.name}</label>
              </c:forEach>
              </div>
                </div>
          </div>

            <div class="bd_tips">
                <div class="desc">
                    <h4>学员年限减免申请省厅默认同意：</h4>
                    <div style="font-size: 15px;line-height: 20px;">
                         <label><input type="radio" name="allowReduction" value="Y" onclick="changAllowReduction()" ${allowReduction eq 'Y'?'checked':''}>是</label>
                         <label><input type="radio" name="allowReduction" value="N" onclick="changAllowReduction()" ${allowReduction eq 'N'?'checked':''}>否</label>
                    </div>
                </div>
            </div>
        </div>
   