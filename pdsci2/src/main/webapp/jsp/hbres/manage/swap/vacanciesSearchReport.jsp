<script>
    $(function(){
        $('#swapContent').slideInit({
            width:1000,
            speed:500,
            outClose:false,
            haveZZ:true
        });
    });
    function toPage(page){
        if(page){
            getAdminSwapContent('',page);
        }
    }
    function filtterOrg(){
        var orgCode = $('#orgCode').val();
        var orgName = $('#orgName').val();
        var isVacancies = $('#isVacancies').attr('checked')?'Y':'';
        var inShortSpe = $('#inShortSpe').attr('checked')?'Y':'';
        $('.orgTab').hide();
        var selector = '';
        if(orgCode){
            selector+=('[orgCode*="'+orgCode+'"]');
        }
        if(orgName){
            selector+=('[orgName*="'+orgName+'"]');
        }
        if(isVacancies){
            selector+=('[isVacancies="'+isVacancies+'"]');
        }
        if(inShortSpe){
            $(".speDiv").hide();
            <c:forEach items="${dictTypeEnumTrainingSpeInShortList}" var="dict">
                var dictId = '${dict.dictId}';
                $(".speDiv_"+dictId).show();
            </c:forEach>
        }else{
            $(".speDiv").show();
        }
        if(selector){
            $(selector).show();
        }else{
            $('.orgTab').show();
        }
    }
    function setSwapContent(data,func){
        jboxStartLoading();
        jboxPost('<s:url value="/hbres/singup/orgSwapDocList"/>',data,function(resp){
            jboxEndLoading();
            $('#swapContent').html(resp);
            if(func){
                func(resp);
            }
        },jboxEndLoading,false);
    }
    function openSwapContent(orgFlow,speId){
        var data = {};
        data.orgFlow = orgFlow || '';
        data.speId = speId || '';
        setSwapContent(data,function(resp){
            $('#swapContent').rightSlideOpen();
        });
    }
    function closeTheSlide(){
        reloadCurrPage();
        $('#swapContent').rightSlideClose(function(){
            $('#swapContent').empty();
        });
    }
    function getSwapContent(){
        setSwapContent($('#swapDocForm').serialize());
    }

    //原功能地址:hbres/hospital/doctors
    function getInfo(doctorFlow,recruitFlow){
        jboxOpen("<s:url value='/hbres/singup/hospital/doctorInfoByDocFlow'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow,"人员信息",1000,550);
    }

    function toSwap(doctorFlow,orgFlow,speId,examResult){
        orgFlow = orgFlow || '';
        speId = speId || '';
        examResult = examResult || '';
        jboxOpen("<s:url value='/hbres/singup/toSwap'/>?doctorFlow="+doctorFlow+"&orgFlow="+orgFlow+"&speId="+speId+'&examResult='+examResult,"调剂学员",500,250);
    }

    function delSwap(doctorFlow,recruitFlow,orgName,speName){
        jboxConfirm('确认撤销调剂？',function(){
            jboxStartLoading();
            jboxPost('<s:url value="/hbres/singup/saveSwap"/>',{
                doctorFlow:doctorFlow || '',
                recruitFlow:recruitFlow || '',
                recruitFlag:'${GlobalConstant.FLAG_N}',
                confirmFlag:'',
                adminSwapFlag:'${GlobalConstant.FLAG_N}',
                recordStatus:'${GlobalConstant.FLAG_N}',
                msg:getMsg(orgName,speName,true) || ''
            },function(resp){
                jboxEndLoading();
                if(resp>0){
                    getSwapContent();
                    jboxTip('操作成功！');
                }
            },jboxEndLoading,false);
        });
    }

    function reloadCurrPage(){
        getAdminSwapContent($('#vacancyForm').serialize(),'');
    }

    function getMsg(orgName,speName,isCancel){
        if(isCancel){
            return '你被省厅调剂至培训基地：'+orgName+'、培训专业：'+speName+'的信息现已撤销，作废！';
        }else{
            return '你已被省厅调剂至培训基地：'+orgName+'、培训专业：'+speName+'，请尽快与培训基地招录负责老师联系相关培训报道事宜。';
        }
    }
    //调剂信息导出
    function exportHbSwapInfo(){
        var url = "<s:url value='/hbres/singup/exportSwapDocList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#swapDocForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline">缺额查询及调剂</h2>
</div>
<div id="search" class="div_search" style="height: 25px;">
	<form id="vacancyForm">
        <div style="float: left;margin-left: 10px;">
            基地编号：<input id="orgCode" name="orgCode" type="text" class="input" value="${param.orgCode}" onchange="filtterOrg()"/>
        </div>
        <div style="float: left;margin-left: 10px;">
            基地名称：<input id="orgName" name="orgName" type="text" class="input" value="${param.orgName}" onchange="filtterOrg()"/>
        </div>
        <div style="float: left;margin-left: 10px;padding-top: 5px;">
            <label>
                <input id="isVacancies" name="isVacancies" type="checkbox" style="float: left;"
                       value="${GlobalConstant.FLAG_Y}" onchange="filtterOrg()"
                       <c:if test="${param.isVacancies eq GlobalConstant.FLAG_Y}">checked</c:if>
                />只看有缺额
            </label>
        </div>
        <div style="float: left;margin-left: 10px;padding-top: 5px;">
            <label>
                <input id="inShortSpe" name="inShortSpe" type="checkbox" style="float: left;"
                       value="${GlobalConstant.FLAG_Y}" onchange="filtterOrg()"
                       <c:if test="${param.inShort eq GlobalConstant.FLAG_Y}">checked</c:if>
                />只看紧缺专业
            </label>
        </div>
        <div style="float: left;">&#12288;
            <c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
            <c:if test="${!isFree}">
                <input class="btn_green" onclick="openSwapContent();" type="button" value="调&#12288;剂">
            </c:if>
        </div>
	</form>
</div>
<div style="padding: 0 10px;">
    <c:forEach items="${orgList}" var="org" varStatus="s">
        <table id="table${org.orgFlow}" orgCode="${org.orgCode}" orgName="${org.orgName}" isVacancies="N" border="0" cellpadding="0" cellspacing="0" class="grid orgTab" style="margin-top: 5px;">
            <tr>
                <th>
                    <div style="float: left;margin-left: 20px;">
                        ${s.index+1}.&#12288;<span style="font-weight: bold;">基地编号：</span>
                        <label>${org.orgCode}</label>
                    </div>
                    <div style="float: left;margin-left: 20px;">
                        <span style="font-weight: bold;">基地名称：</span>
                        <label>${org.orgName}</label>
                    </div>
                    <div style="float: left;margin-left: 20px;">
                        <script>
                            $(function(){
                                var sum = 0;
                                $('.vacancy${org.orgFlow}').each(function(){
                                    var text = $.trim(this.dataset.vacancyNum)-0;
                                    if(text>0){
                                        sum+=text;
                                    }
                                });
                                $('#vacancy${org.orgFlow}').text(sum);
                                if(sum>0){
                                    $('#table${org.orgFlow}').attr('isVacancies','Y');
                                }
                            });
                        </script>
                        <span style="font-weight: bold;">缺额总数：</span>
                        <label id="vacancy${org.orgFlow}"></label>
                    </div>
                    <div style="float: right;margin-right: 10px;">
                        <c:if test="${!isFree}">
                        [<a style="color: blue;" onclick="openSwapContent('${org.orgFlow}');">调剂</a>]
                        </c:if>
                    </div>
                </th>
            </tr>
            <tr>
                <td style="text-align: left;">
                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="speDict">
                        <c:set var="k" value="${org.orgFlow}${speDict.dictId}"/>
                        <c:set var="assignNum" value="${speAssignMap[k]}"/>
                        <c:set var="recruitNum" value="${recruitDocMap[k]}"/>
                        <c:set var="vacancyNum" value="${assignNum-recruitNum}"/>
                        <c:if test="${assignNum>0 && vacancyNum>0}">
                            <div style="float: left;margin-left: 20px;width: 170px;" class="speDiv speDiv_${speDict.dictId}">
                                <a onclick="openSwapContent('${org.orgFlow}','<c:if test="${vacancyNum>0}">${speDict.dictId}</c:if>');">
                                    ${speDict.dictName}
                                    (
                                        <label class="vacancy${org.orgFlow}" data-vacancy-num="${vacancyNum}" style="cursor: pointer;">
                                            <c:if test="${vacancyNum>0}">
                                                ${vacancyNum}
                                            </c:if>
                                            <c:if test="${!(vacancyNum>0)}">
                                                已满
                                            </c:if>
                                        </label>
                                    )
                                </a>
                            </div>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </c:forEach>
</div>
<div class="page" style="text-align: center">
    <input id="currentPage" type="hidden" name="currentPage" value=""/>
    <c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
<script>
    $(function(){
        filtterOrg();
    });
</script>
<div id="swapContent"></div>

