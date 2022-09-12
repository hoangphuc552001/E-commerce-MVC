$(document).ready(function(){
	$(".nn-cart-update").on("input", function(){
		var id = $(this).closest("[data-id]").attr("data-id");
		var price = $(this).closest("[data-id]").attr("data-price");
		var disc = $(this).closest("[data-id]").attr("data-discount");
		var qty = $(this).val();
		var url = `/api/cart/update/${id}/${qty}`;
		$.getJSON(url).then(resp => {
			showCartInfo();
			var amt = (price * qty * (1-disc)).toFixed(2);
			$(this).closest("[data-id]").find(".nn-item-amt").html(amt);
		});
	});
	
	$(".nn-cart-remove").on("click", function(){
		var id = $(this).closest("[data-id]").attr("data-id");
		var url = `/api/cart/remove/${id}`;
		$.getJSON(url).then(resp => {
			showCartInfo();
			$(this).closest("[data-id]").hide(300);
		});
	});
	
	$(".nn-cart-add").on("click", function(){
		var id = $(this).closest("[data-id]").attr("data-id");
		var url = `/api/cart/add/${id}`;
		$.getJSON(url).then(resp => {
			showCartInfo();
		});
	});
	
	function showCartInfo(){
		var url = "/api/cart/info?"+Math.random();
		$.getJSON(url).then(resp => {
			$("#cart-cnt").html(resp.count);
			$("#cart-amt").html(resp.amount.toFixed(2));
		});
	}
	showCartInfo();
	
	$(".nn-like").on("click", function(){
		var id = $(this).closest("[data-id]").attr("data-id");
		var url = `/product/like/${id}`;
		$.getJSON(url).then(resp => {
			alert(resp);
		});
	});
	
	$(".nn-share").on("click", function(){
		id = $(this).closest("[data-id]").attr("data-id");
	});
	$(".nn-send").on("click", function(){
		var data = {
			id: id,
			email: $("#email").val()
		}
		var url = `/product/send`;
		$.post(url, data).then(resp => {
			alert(resp);
		});
	})
})