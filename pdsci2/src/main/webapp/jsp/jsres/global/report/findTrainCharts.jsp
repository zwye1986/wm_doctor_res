<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
</c:if>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
require.config({
    paths: {
        echarts: "<s:url value='/js/echarts'/>"
    }
});

require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('doctorNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];
 	var value = [];
		<c:forEach items="${orgs}" var="org">
			lineLabel.push("${org.orgName}");
			<c:set var="count" value="0"></c:set>
			<c:if test="${not empty orgCountMap[org.orgFlow]}">
				<c:set var="count" value="${orgCountMap[org.orgFlow]+count}"></c:set>
			</c:if>
			<c:forEach items="${orgMap[org.orgFlow]}" var="joinOrg">
				<c:if test="${not empty orgCountMap[joinOrg.jointOrgFlow]}">
					<c:set var="count" value="${orgCountMap[joinOrg.jointOrgFlow]+count}"></c:set>
				</c:if>
			</c:forEach>
			lineValue.push("${count}");
		</c:forEach>

	//console.log(lineLabel);
	//console.log(lineValue);
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	}
	var title = "江苏省住院医师规范化培训退培人员统计";
option = {
		 title : {
		        text: title,
		        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    calculable : true,
		grid:{
			zlevel:0,
			height:350
		},
	    xAxis : [
	        {
	            type : 'category',
				axisLabel:{
					interval:0,
					rotate:-90,
					margin:10
				},
				name:'基地',
				boundaryGap : true,
	            data : lineLabel
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            interval: 'auto',
				name:'退培人数',
	            axisLabel : {
					formatter: function(value)
					{
						return parseInt(value)+'人';
					}
            	},
	        }
	    ],
	    series : [
	        {
	            name:'退培人数',
	            type:'bar',
	            data:lineValue,
				barWidth : 15,
	            itemStyle: {
                    normal: {
                        label : {
                            show: true, position: 'top',
                            formatter:function(a,b,c){
                            	return c;
                            }
                        }
                    }
           		}
	        }
	    ]
	};

myChart.setOption(option);
});

</script>
</head>
<body>
<div class="search_table">
	<div id="doctorNum" style="height: 550px;width:100%;">

	</div>
</div>
</div>
</body>
</html>
