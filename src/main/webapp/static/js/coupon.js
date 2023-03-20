$(document).ready(function() {
	$(".btn-apply-coupon").on("click", function() {
		var couponCode = $(".coupon-code").val();
		$.ajax({
			type: "POST",
			url: "/coupon/apply/" + couponCode
		}).done(function(response) {
			var subTotal = $("#subtotal").attr("data-subtotal");
			var discount = response[1];
			$("#invalid-coupon").html(response[0]);
			$("#coupon-disc").html((-subTotal*discount).toLocaleString() + " ₫ (-" + discount*100 + "%)");
			$("#total").html((subTotal*(1-discount)).toLocaleString() + " ₫");
			if (response[2] != null) {
				$("#coupon-id").html(`<input type="hidden" name="couponId" value="` + response[2] + `">`);
			} else {
				$("#coupon-id").empty();
			}
		});
	});
});