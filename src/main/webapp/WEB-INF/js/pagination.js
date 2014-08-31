$(function() {
	displayData(0);
	$("#pageNo").keydown(function(e) {
		if(e.keyCode == 13) { //回车
			displayData(this.value-1);
		}
	});

	$("#pageSize").change(function() {
		displayData(0);
	});

	$("#selectAll").click(function() {
		$(":checkbox[name='id']").attr("checked", this.checked);
	});
});
