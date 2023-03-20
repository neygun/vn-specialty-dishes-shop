<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
<article class="col-sm-10">
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Đặc sản miền Bắc</h1>
</div>
<div id="carouselExampleControls1" class="carousel" data-bs-ride="carousel">
  <div class="carousel-inner carousel-inner1">
  <c:forEach var="product" items="${mienBac}">
  	<div class="carousel-item carousel-item1">
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
                    <c:if test="${product.available}">
                    <sec:authorize access="isAuthenticated()">
                    <div class="d-grid gap-2" data-product-id="${product.id}">
					  <button type="button" class="btn btn-success btn-add-1">Thêm vào giỏ</button>
					</div>
                    </sec:authorize>
                    
                    <sec:authorize access="isAnonymous()">
					<a href="/login" class="btn btn-success">Thêm vào giỏ</a>
					</sec:authorize>
					</c:if>
					<c:if test="${!product.available}">
					<button type="button" class="btn btn-light">Hết hàng</button>
					</c:if>
                </div>
        </div>
    </div>
  </c:forEach>
  </div>
  <button class="carousel-control-prev carousel-control-prev1" type="button" data-bs-target="#carouselExampleControls1" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next carousel-control-next1" type="button" data-bs-target="#carouselExampleControls1" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Đặc sản miền Trung</h1>
</div>
<div id="carouselExampleControls2" class="carousel" data-bs-ride="carousel">
  <div class="carousel-inner carousel-inner2">
  <c:forEach var="product" items="${mienTrung}">
  	<div class="carousel-item carousel-item2">
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
                    <c:if test="${product.available}">
                    <sec:authorize access="isAuthenticated()">
                    <div class="d-grid gap-2" data-product-id="${product.id}">
					  <button type="button" class="btn btn-success btn-add-1">Thêm vào giỏ</button>
					</div>
                    </sec:authorize>
                    
                    <sec:authorize access="isAnonymous()">
					<a href="/login" class="btn btn-success">Thêm vào giỏ</a>
					</sec:authorize>
					</c:if>
					<c:if test="${!product.available}">
					<button type="button" class="btn btn-light">Hết hàng</button>
					</c:if>
                </div>
        </div>
    </div>
  </c:forEach>
  </div>
  <button class="carousel-control-prev carousel-control-prev2" type="button" data-bs-target="#carouselExampleControls2" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next carousel-control-next2" type="button" data-bs-target="#carouselExampleControls2" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Đặc sản miền Nam</h1>
</div>
<div id="carouselExampleControls3" class="carousel" data-bs-ride="carousel">
  <div class="carousel-inner carousel-inner3">
  <c:forEach var="product" items="${mienNam}">
  	<div class="carousel-item carousel-item3">
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
                    <c:if test="${product.available}">
                    <sec:authorize access="isAuthenticated()">
                    <div class="d-grid gap-2" data-product-id="${product.id}">
					  <button type="button" class="btn btn-success btn-add-1">Thêm vào giỏ</button>
					</div>       
                    </sec:authorize>
                    
                    <sec:authorize access="isAnonymous()">
					<a href="/login" class="btn btn-success">Thêm vào giỏ</a>
					</sec:authorize>
					</c:if>
					<c:if test="${!product.available}">
					<button type="button" class="btn btn-light">Hết hàng</button>
					</c:if>
                </div>
        </div>
    </div>
  </c:forEach>
  </div>
  <button class="carousel-control-prev carousel-control-prev3" type="button" data-bs-target="#carouselExampleControls3" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next carousel-control-next3" type="button" data-bs-target="#carouselExampleControls3" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</article>
<aside class="col-sm-2">
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h3>Tin tức</h3>
</div>
<ul>
<c:forEach var="post" items="${posts}">
	<li><a href="/post/detail/${post.id}" class="post-link">${post.title}</a></li>
</c:forEach>
</ul>
</aside>
</div>