<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="parameters" value="${keyword != null ? '&keyword='+=keyword : ''}${minPrice != null ? '&minPrice='+=minPrice : ''}${maxPrice != null ? '&maxPrice='+=maxPrice : ''}${sortBy != null ? '&sortBy='+=sortBy : ''}" />

<div class="row">
	<aside class="col-sm-3">
		<div class="card">
  <div class="card-header">SẮP XẾP</div>
  <div class="card-body">
  	<form method="get">
  		<c:if test="${keyword != null}">
	  		<input type="hidden" name="keyword" value="${keyword}">
	  	</c:if>
  	
  		<c:if test="${minPrice != null && maxPrice != null}">
	  		<input type="hidden" name="minPrice" value="${minPrice}">
	  		<input type="hidden" name="maxPrice" value="${maxPrice}">
	  	</c:if>
	  	
	  	<select class="form-select" name="sortBy" onchange="this.form.submit();">
			<option value="default" ${param.sortBy == 'default' ? 'selected' : ''}>Mặc định</option>
			<option value="price-asc" ${param.sortBy == 'price-asc' ? 'selected' : ''}>Giá: thấp đến cao</option> 
			<option value="price-desc" ${param.sortBy == 'price-desc' ? 'selected' : ''}>Giá: cao đến thấp</option>                   
		</select>
	</form>
  </div>
</div>

<div class="card">
  <div class="card-header">TÌM KIẾM</div>
  <div class="card-body">
  	<form action="/product/search" method="get">
	  	<input value="${param.keyword}" name="keyword" class="form-control" placeholder="Keyword">
	</form>
  </div>
</div>

<div class="card">
  <div class="card-header">KHOẢNG GIÁ</div>
  <div class="card-body">
  	<form method="get">
  		<c:if test="${keyword != null}">
	  		<input type="hidden" name="keyword" value="${keyword}">
	  	</c:if>
  	
	  	<input value="${param.minPrice}" name="minPrice" class="form-control" placeholder="₫ Từ">
	  	<input value="${param.maxPrice}" name="maxPrice" class="form-control" placeholder="₫ Đến">
	  	
	  	<c:if test="${sortBy != null}">
	  		<input type="hidden" name="sortBy" value="${sortBy}">
	  	</c:if>
	  	<input type="submit" value="Áp dụng">
	</form>
  </div>
</div>

<div class="card">
  <div class="card-header">DANH MỤC</div>
  <div class="list-group">
  <c:forEach var="mainCategory" items="${mainCategories}">
  	<a href="/product/list/main-category/${mainCategory.id}" class="list-group-item list-group-item-action">${mainCategory.name}</a>
  </c:forEach>
	<a href="/product/list/best-selling" class="list-group-item list-group-item-action">Sản phẩm bán chạy</a>
	</div>
</div>
	</aside>
	<article class="col-sm-9">
		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
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
<nav aria-label="Page Navigation">
	<ul class="pagination">
		<c:if test="${currentPage > 1}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage - 1}${parameters}">Previous</a>
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
    					<a class="page-link" href="?page=${i}${parameters}">${i}</a>
    				</li>
				</c:otherwise>
			</c:choose>
		</c:forEach> 
		<c:if test="${currentPage < totalPages}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage + 1}${parameters}">Next</a>
			</li>
		</c:if>
	</ul>
</nav>
	</article>
</div>