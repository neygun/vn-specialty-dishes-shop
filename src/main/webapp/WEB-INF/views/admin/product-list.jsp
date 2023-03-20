<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<c:set var="parameters" value="${keyword != null ? '&keyword='+=keyword : ''}${minPrice != null ? '&minPrice='+=minPrice : ''}${maxPrice != null ? '&maxPrice='+=maxPrice : ''}${sortBy != null ? '&sortBy='+=sortBy : ''}" />

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Sản phẩm</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/product/add-form" class="btn btn-primary btn-sm mb-3">Thêm sản phẩm</a>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form action="/admin/product/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>
	
	<!-- Price filter -->
	<form method="get" class="row p-2">
	  <c:if test="${keyword != null}">
	  	<input type="hidden" name="keyword" value="${keyword}">
	  </c:if>
	  <c:if test="${sortBy != null}">
	  	<input type="hidden" name="sortBy" value="${sortBy}">
	  </c:if>
	  <div class="col">
	    <input value="${param.minPrice}" name="minPrice" class="form-control" placeholder="₫ Từ">
	  </div>
	  <div class="col">
	    <input value="${param.maxPrice}" name="maxPrice" class="form-control" placeholder="₫ Đến">
	  </div>
	  <div class="col">
	    <button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	  </div>
	</form>
	
	<!-- Sort -->
	<form method="get" class="col-md-2 p-2">
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

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tên</th>
				<th>Hình ảnh</th>
				<th>Giá</th>
				<th>HSD</th>
				<th>Trạng thái</th>
				<th>Số lượng</th>
				<th>Giảm giá</th>
				<th>Lô đang bán</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.name}</td>
					<td><img src="/static/images/products/${product.image}" style="height: 40px; width: 40px;"></td>
					<td><f:formatNumber value="${product.unitPrice}" pattern="#,##0" /> ₫</td>
					<td><f:formatDate value="${product.expiryDate}" pattern="dd-MM-yyyy" /></td>
					<td>${product.available ? 'Còn hàng' : 'Hết hàng'}</td>
					<td>${product.quantity}</td>
					<td><f:formatNumber value="${product.discount}" type="percent" /></td>
					<td>${product.currentPO}</td>
					<td>
						<a href="/admin/product/update-form?productId=${product.id}"
							class="btn btn-info btn-sm">Cập nhật</a>
						<a href="/admin/product/view-purchase-order?productId=${product.id}"
							class="btn btn-success btn-sm">Chọn lô hàng</a>
						<a href="/admin/product/delete?productId=${product.id}"
							class="btn btn-danger btn-sm">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
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