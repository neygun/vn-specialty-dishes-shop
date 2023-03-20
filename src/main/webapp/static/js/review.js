$(function() {
  $("#rateYo").rateYo({
    rating: 0,
    fullStar: true,
    onChange: function(rating, rateYoInstance) {
      $("#rating").val(rating);
    }
  });
});

function addReview(productId) {
	
	fetch("/review/add", {
		method: "post",
		body: JSON.stringify({
			"productId": productId,
			"content": document.getElementById("content").value,
			"rating": document.getElementById("rating").value
		}),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(function(res) {
		return res.json();
	}).then(function(data) {
		let addedReviews = document.getElementById("addedReviews");
		let ratingStars = "";
		for (let i = 1; i <= data.rating; i++) {
  			ratingStars += `<i class="bi bi-star-fill" style="color: #f39c12;"></i>`;
		}
		for (let i = data.rating; i <= 4; i++) {
  			ratingStars += `<i class="bi bi-star-fill" style="color: #808080;"></i>`;
		}
		addedReviews.innerHTML = `<div class="card-body p-4">
            <div class="d-flex flex-start">
              <img class="rounded-circle shadow-1-strong me-3"
                src="/static/images/customers/${data.customer.avatar}" alt="avatar" width="60"
                height="60" />
              <div>
                <h6 class="fw-bold mb-1">${data.customer.fullName}</h6>
                <div class="d-flex align-items-center mb-3">
                  <p class="mb-0">
                    Vừa xong `
                    + ratingStars +
                  `</p>
                </div>
                <p class="mb-0">
                  ${data.content}
                </p>
              </div>
            </div>
          </div>
          <hr class="my-0" />` + addedReviews.innerHTML;
	});
}