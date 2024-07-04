<script>
	function showNext(obj)
	{
		var nextTr=$(obj).parent("tr").next("tr");
		if($(nextTr).is(":hidden")){
			$(nextTr).show();
		}else{
			$(nextTr).hide();
		}
	}
</script>
<table width="100%">
	<tr>
		<th colspan="4"  onclick="showNext(this);">科室评分标准</th></tr>
	<tr hidden><td>暂无标准</td></tr>
</table>