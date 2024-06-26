<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

$(document).ready(function(){
	showCanvas('1','100');
	showCanvas('2','100');
	showCanvas('3','100');
	showCanvas('4','80');
});
function uploadCkkkb(){
	var url = "<s:url value='/jsp/jsres/doctor/uploadCkkhb.jsp'/>";
	jboxOpen(url, "出科考核表",500,200);
}
function showCanvas (id,per){
    var myChart = echarts.init(document.getElementById('chart_'+id)); 
    var labelTop = {
    	    normal : {
    	        label : {
    	            show : true,
    	            position : 'center',
    	            formatter : '{b}',
    	            textStyle: {
    	                baseline : 'bottom'
    	            }
    	        },
    	        labelLine : {
    	            show : false
    	        }
    	    }
    	};
    	var labelFromatter = {
    	    normal : {
    	        label : {
    	            formatter : function (a,b,c){return per+"%"},
    	            textStyle: {
    	                baseline : 'center'
    	            }
    	        }
    	    },
    	}
    	var labelBottom = {
    	    normal : {
    	        color: '#ccc',
    	        label : {
    	            show : true,
    	            position : 'center'
    	        },
    	        labelLine : {
    	            show : false
    	        }
    	    },
    	    emphasis: {
    	        color: 'rgba(0,0,0,0)'
    	    }
    	};
    	var radius = [25, 28];
    	option = {
    	    series : [
    	        {
    	            type : 'pie',
    	            radius : radius,
    	            x: '0%', // for funnel
    	            itemStyle : labelFromatter,
    	            data : [
						{name:'other', value:100-per,itemStyle : labelBottom},
						{name:'', value:per, itemStyle : labelTop},
    	            ]
    	        }
    	    ]
    	};

    // 为echarts对象加载数据 
    myChart.setOption(option); 
}
</script>
<div class="main_hd">
    <h2><B>培训基地：</B>江苏省人民医院&#12288;<B>培训专业：</B>内科&#12288;<B>轮转时间：</B>33月&#12288;
   <B> 轮转方案：</B>2014国家西医内科培训方案</h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent" >
    	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	 <colgroup>
	        		<col width="15%"/>
	        		<col width="10%"/>
	        		<col width="15%"/>
	        		<col width="15%" />
	        		<col width="25%"/>
	        		<col width="25%"/>
	        	</colgroup>
            <tr>
                <th>标准科室</th>
                <th>规定时间(月)</th>
                <th>入科时间</th>
                <th>出科时间</th>
                <th>培训进度</th>
                <th style="text-align: left;">出科考核表</th>
            </tr>
	             <tr>
	                <td>内科</td>
	                <td>22</td>
	                <td>2012-09-01</td>
	                <td>2014-07-01</td>
	               <td width="120px;"><div id="chart_1" style="width: 100%;height: 80px;"></div></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">出科考核表</a></td>
	            </tr>
	             <tr>
	                <td>急诊医学科</td>
	                <td>3</td>
	                <td>2014-07-03</td>
	                <td>2014-10-03</td>
	                 <td width="120px;"><div id="chart_2" style="width: 100%;height: 80px;"></div></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">出科考核表</a></td>
	            </tr>
	             <tr>
	                <td>重症监护(ICU)</td>
	                <td>2</td>
	                 <td>2014-10-04</td>
	                <td>2014-12-04</td>
	                 <td width="120px;"><div id="chart_3" style="width: 100%;height: 80px;"></div></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">出科考核表</a></td>
	            </tr>
	             <tr>
	                <td>感染疾病科</td>
	                <td>2</td>
	                <td>2014-12-05</td>
	                <td>2015-01-05</td>
	                 <td width="120px;"><div id="chart_4" style="width: 100%;height: 80px;"></div></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">出科考核表</a></td>
	            </tr>
	             <tr>
	                <td>外科</td>
	                <td>1</td>
	                <td>未入科</td>
	                <td></td>
	                <td></td>
	                 <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">上传</a>&#12288;<a class="btn" href="<s:url value='/jsp/jsres/doctor/ckkhb_empty.xlsx'/>">下载</a>
	            </tr>
	             <tr>
	                <td>皮肤科</td>
	                <td>1</td>
	                <td>未入科</td>
	                <td></td>
	                <td></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">上传</a>&#12288;<a class="btn" href="<s:url value='/jsp/jsres/doctor/ckkhb_empty.xlsx'/>">下载</a>
	            </tr>
	             <tr>
	                <td>肿瘤科</td>
	                <td>1</td>
	               <td>未入科</td>
	                <td>${doctorRecruit.trainYear}</td>
	                <td></td>
	                 <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">上传</a>&#12288;<a class="btn" href="<s:url value='/jsp/jsres/doctor/ckkhb_empty.xlsx'/>">下载</a>
	            </tr>
	             <tr>
	                <td>放射科</td>
	                <td>1</td>
	               <td>未入科</td>
	                <td></td>
	                <td></td>
	                <td style="text-align: left;"><a class="btn" onclick="uploadCkkkb();">上传</a>&#12288;<a class="btn" href="<s:url value='/jsp/jsres/doctor/ckkhb_empty.xlsx'/>">下载</a>
	            </tr>
        </table>
        
    </div>
    </div>
</div>
    
