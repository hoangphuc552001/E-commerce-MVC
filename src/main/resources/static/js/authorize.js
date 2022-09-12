$(document).ready(function(){
	$(":checkbox").on("click", function(){
		var pair = $(this).val();
		var url = `/api/admin/authorize/${pair}`
		$.getJSON(url).then(resp => {
			alert("OK")
		})
	})
})