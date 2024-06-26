<script type="text/javascript" >
$(document).ready(function(){
	var token="<input type='hidden' name='csrftoken' value='${csrftoken}'/>";
	$("form").each(function(){
		$(token).appendTo($(this))
	});
});
</script>