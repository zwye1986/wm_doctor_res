<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<c:if test="${param.compatible=='true'}">
	<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge,IE=10;">
</c:if>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">

<c:set var="min" value=".min"></c:set>

<c:if test="${param.basic=='true'}">
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<%@ include file="/jsp/token.jsp" %>
</c:if>
<c:if test="${param.jbox=='true'}">

	<c:choose>
		<c:when test="${empty applicationScope.sysCfgMap['sys_jbox'] or applicationScope.sysCfgMap['sys_jbox']=='jbox'}">
			<link rel="stylesheet" type="text/css" href="<s:url value='/css/jBox/Blue/jbox.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
			<script type="text/javascript" src="<s:url value='/js/jquery.jBox.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/jquery.jBox-zh-CN.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/common-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.core.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.position.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
		</c:when>
		<c:when test="${applicationScope.sysCfgMap['sys_jbox']=='art'}">
			<link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
			<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
			<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
		</c:when>
	</c:choose>
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/slideRight.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/slideRight.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script>
        //自动获取id对应的值,用于res表单存值使用,
        function autoValue(form,clazz,inputValueMethod){
            if(!form){
                return false;
            }
            if(!clazz){
                return false;
            }
            inputValueMethod = inputValueMethod || {
                "select" : function(select){
                    var text = $("option:selected",select).text();
                    return $.trim(text);
                },
                ":radio:checked" : function(radio){
                    var text = $(radio).parent().text();
                    return $.trim(text);
                },
                ":checkbox:checked" : function(checkbox){
                    var text = $(checkbox).parent().text();
                    return $.trim(text);
                }
            };
            var hideInput = $('<input type="hidden" />');
            for(var key in inputValueMethod){
                $(key+"."+clazz).each(function(){
                    var text = inputValueMethod[key](this);
                    $(form).append(hideInput.attr({name:this.name+"_name",value:text}).clone());
                });
            }
        }

        //ajax自由取值方法START
        function groupCustomSqlData(data){
            data = data || [];
            var len = data.length;
            if(len){
                var dataStr = '';
                if(len==1){
                    dataStr+=('${GlobalConstant.EXECUTE_IDS}='+data[0].id);
                    var values = data[0].values;
                    for(var index in values){
                        dataStr+=('&${GlobalConstant.ARGUMENTS}='+values[index]);
                    }
                }else if(len>1){
                    for(var index in data){
                        if(index+0){
                            dataStr+='&';
                        }

                        var id = data[index].id;
                        dataStr+=('${GlobalConstant.EXECUTE_IDS}='+id);

                        var values = data[index].values;
                        if(values){
                            for(var vIndex in values){
                                dataStr+=('&'+id+'.${GlobalConstant.ARGUMENTS}='+values[vIndex]);
                            }
                        }
                    }
                }
                return dataStr;
            }
        }

        //参数格式[{id:'xxx',values:['111','2222']}]
        function jboxCustomSqlData(datas,success){
            jboxPostNoLoad('<s:url value="/common/util/getDatas"/>',groupCustomSqlData(datas),function(resp){
                if(success){
                    success(resp);
                }
            },null,false);
        }
        //ajax自由取值方法END




        $(document).ready(function(){
            jboxEndLoading();
        });
	</script>
</c:if>

<c:if test="${param.jquery_form=='true'}">
	<script type="text/javascript" src="<s:url value='/js/jsres/jquery.form${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>


<c:if test="${param.bootstrap=='true'}">
	<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/bootstrap-3.3.7-dist/css/bootstrap.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
	<script src="<s:url value='/jsp/inx/tjres/jquery-1.9.1.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script src="<s:url value='/jsp/inx/tjres/jquery-migrate-1.4.1.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script src="<s:url value='/jsp/inx/tjres/bootstrap-3.3.7-dist/js/bootstrap.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>