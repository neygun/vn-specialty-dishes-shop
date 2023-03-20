$(document).ready(function() {
	$(".btn-add-to-cart").on("click", function() {
		var productId = $(this).closest("div").attr("data-product-id");
		var quantity = $(this).siblings("input").val();
		$.ajax({
			type: "POST",
			url: "/cart/add/" + productId + "/" + quantity
		}).done(function(response) {
			$("#cart-count").html(response[0]);
		});
	});
	
	$(".btn-add-1").on("click", function() {
		var productId = $(this).closest("div").attr("data-product-id");
		$.ajax({
			type: "POST",
			url: "/cart/add/" + productId + "/" + 1
		}).done(function(response) {
			$("#cart-count").html(response[0]);
		});
	});
	
	$("tr[data-item-id] input").on("input", function() {
		var cartItemId = $(this).closest("tr").attr("data-item-id");
		var quantity = $(this).val();
		$.ajax({
			type: "POST",
			url: "/cart/update/" + cartItemId + "/" + quantity
		}).done((response) => {
			$("#cart-count").html(response[0]);
			$("#cart-amount").html(response[1].toLocaleString() + " ₫");
			$(this).closest("tr").find("p.subtotal").html(response[2].toLocaleString() + " ₫");
		});
	});
	
	$(".btn-quantity").on("click", function() {
		var cartItemId = $(this).closest("tr").attr("data-item-id");
		var quantity = $(this).siblings("input").val();
		$.ajax({
			type: "POST",
			url: "/cart/update/" + cartItemId + "/" + quantity
		}).done((response) => {
			$("#cart-count").html(response[0]);
			$("#cart-amount").html(response[1].toLocaleString() + " ₫");
			$(this).closest("tr").find("p.subtotal").html(response[2].toLocaleString() + " ₫");
		});
	});
	
	$(".btn-cart-remove").on("click", function() {
		var cartItemId = $(this).closest("tr").attr("data-item-id");
		$.ajax({
			type: "POST",
			url: "/cart/remove/" + cartItemId
		}).done(function(response) {
			$("#cart-count").html(response[0]);
			$("#cart-amount").html(response[1].toLocaleString() + " ₫");
		});
		$(this).closest("tr").remove();
	});
});