<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Shop</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <link href="/static/css/styles.css" rel="stylesheet" />
  <link href="/static/css/jquery.rateyo.min.css" rel="stylesheet" />
</head>
<body>

<tiles:insertAttribute name="header" />

<div class="container">
<div class="row mt-2">
	<div class="col-sm-5 text-center">
		<img class="img-fluid" src="/static/images/products/${product.image}">
	</div>
	<div class="col-sm-7">
		<ul class="detail-info">
			<li style="font-size: 1.875em;">${product.name}</li>
			<li class="py-3" style="font-size: 2em;">
				<span class="${product.discount != 0 ? 'price' : ''}">
	            	<f:formatNumber value="${product.unitPrice}" pattern="#,##0" /> ₫
	            </span>
				<c:if test="${product.discount != 0}">
	            <span>
	            	&nbsp;<f:formatNumber value="${product.discountedPrice}" pattern="#,##0" /> ₫
	            </span>
	            <span style="color: red;">
	            	&nbsp;-<f:formatNumber value="${product.discount}" type="percent" />
	            </span>
				</c:if>
			</li>
			<li>Hạn sử dụng: <f:formatDate value="${product.expiryDate}" pattern="dd-MM-yyyy" /></li>
			<li>Danh mục: Đặc sản ${product.category.name}</li>
			<li>Tình trạng: ${product.available ? 'Còn hàng' : 'Hết hàng'}</li>
		</ul>
		<c:if test="${product.available}">
		<div class="d-flex flex-row align-items-center px-4" data-product-id="${product.id}">
			<button class="btn btn-link px-2"
	        	onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
	        	<i class="bi bi-dash-square"></i>
	        </button>
	
	        <input min="1" value="1" type="number"
	        	class="form-control form-control-sm" style="width: 50px;" />
	
	        <button class="btn btn-link px-2"
	        	onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
	        	<i class="bi bi-plus-square"></i>
	        </button>
	      
	        <sec:authorize access="isAuthenticated()">
            <button type="button" class="btn btn-success btn-add-to-cart">Thêm vào giỏ</button>
            </sec:authorize>
                    
            <sec:authorize access="isAnonymous()">
			<a href="/login" class="btn btn-success">Thêm vào giỏ</a>
			</sec:authorize>
			<span class="px-2">${product.quantity} sản phẩm có sẵn</span>
        </div>
        </c:if>
        		
	</div>
</div>

<div class="text-justify">
	<p style="white-space: pre-line">${product.description}</p>
</div>

    <div class="row d-flex">
      <div class="col-md-12 col-lg-12">
        <div class="card text-dark">
        <div class="card-body p-4">
        	<h4 class="mb-0">Đánh giá</h4>
        </div>
        <sec:authorize access="isAuthenticated()">
        <div class="card-body p-4">
        	<form>
	        <div class="form-outline">
	        	<div id="rateYo"></div>
	        	<input type="hidden" id="rating" name="rating">
	        	<textarea id="content" class="form-control" placeholder="Nhập đánh giá của bạn..."></textarea>
	        	<br>
	            <input type="button" value="Gửi" onclick="addReview(${product.id})" class="btn btn-success">
	        </div>
	        </form>
        </div>
        </sec:authorize>
        <hr class="my-0" />
        
        <div id="addedReviews"></div>
        <c:forEach var="review" items="${reviews}">
        	<div class="card-body p-4">
            <div class="d-flex flex-start">
              <img class="rounded-circle shadow-1-strong me-3"
                src="/static/images/customers/${review.customer.avatar}" alt="avatar" width="60"
                height="60" />
              <div>
                <h6 class="fw-bold mb-1">${review.customer.fullName}</h6>
                <div class="d-flex align-items-center mb-3">
                  <p class="mb-0">
                  	<f:formatDate value="${review.reviewDate}" pattern="dd-MM-yyyy HH:mm" /> 
                    <c:forEach begin="1" end="${review.rating}">
      					<i class="bi bi-star-fill" style="color: #f39c12;"></i>
   					</c:forEach>
				    <c:forEach begin="${review.rating}" end="4">
				    	<i class="bi bi-star-fill" style="color: #808080;"></i>
				    </c:forEach>
                  </p>
                </div>
                <p class="mb-0">
                  ${review.content}
                </p>
              </div>
            </div>
          </div>
          <hr class="my-0" />
          
          
        </c:forEach>
        
        <nav aria-label="Page Navigation">
				<ul class="pagination">
					<c:if test="${currentPage > 1}">
						<li class="page-item">
							<a class="page-link" href="/product/detail/${productId}?page=${currentPage - 1}">Previous</a>
			    		</li>
					</c:if>
					<c:forEach begin="1" end="${totalPages}" var="i">
						<c:choose>
							<c:when test="${currentPage == i}">
								<li class="page-item active" aria-current="page">
			    					<span class="page-link">${i}</span>
			    				</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
			    					<a class="page-link" href="/product/detail/${productId}?page=${i}">${i}</a>
			    				</li>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
					<c:if test="${currentPage < totalPages}">
						<li class="page-item">
							<a class="page-link" href="/product/detail/${productId}?page=${currentPage + 1}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
        
        </div>
      </div>
    </div>
    
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h2>Sản phẩm liên quan</h2>
	</div>
	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-5">
		<c:forEach var="product" items="${products}">
			<div class="col mt-3">
				<div class="card card-product">
		                <div class="img-wrapper">
			                <a href="/product/detail/${product.id}">
			                	<img src="/static/images/products/${product.image}">
			                </a>
		                </div>
		                <div class="card-body">
		                    <h6 class="card-title">${product.name}</h6>
		                    <p class="card-text">
			                    <span class="${product.discount != 0 ? 'price' : ''}">
			                    	<f:formatNumber value="${product.unitPrice}" pattern="#,##0" /> ₫
			                    </span>
			                    <c:if test="${product.discount != 0}">
			                    <span>
			                    	&nbsp;<f:formatNumber value="${product.discountedPrice}" pattern="#,##0" /> ₫
			                    </span>
								</c:if>
		                    </p>
		                </div>
		        </div>
		    </div>
		</c:forEach>
	</div>
</div>

<tiles:insertAttribute name="footer" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/static/js/main.js"></script>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/jquery.rateyo.min.js"></script>
<script src="/static/js/review.js"></script>

</body>
</html>