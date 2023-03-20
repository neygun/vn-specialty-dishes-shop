<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<c:set var="parameters" value="${keyword != null ? '&keyword='+=keyword : ''}${sortBy != null ? '&sortBy='+=sortBy : ''}" />

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Mã giảm giá</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/coupon/add-form" class="btn btn-primary btn-sm mb-3">Thêm mã giảm giá</a>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form action="/admin/coupon/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>
	
	<!-- Sort -->
	<form method="get" class="col-md-2 p-2">
  		<c:if test="${keyword != null}">
	  		<input type="hidden" name="keyword" value="${keyword}">
	  	</c:if>
	  	
	  	<select class="form-select" name="sortBy" onchange="this.form.submit();">
			<option value="default" ${param.sortBy == 'default' ? 'selected' : ''}>Mặc định</option>
			<option value="limit-asc" ${param.sortBy == 'limit-asc' ? 'selected' : ''}>Số lượng: thấp đến cao</option> 
			<option value="limit-desc" ${param.sortBy == 'limit-desc' ? 'selected' : ''}>Số lượng: cao đến thấp</option>
			<option value="discount-asc" ${param.sortBy == 'discount-asc' ? 'selected' : ''}>Giảm giá: thấp đến cao</option> 
			<option value="discount-desc" ${param.sortBy == 'discount-desc' ? 'selected' : ''}>Giảm giá: cao đến thấp</option>                 
		</select>
	</form>
</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tên</th>
				<th>Mã</th>
				<th>Giảm giá</th>
				<th>Số lượng</th>
				<th>Ngày bắt đầu</th>
				<th>Ngày kết thúc</th>
				<th>Cập nhật/Xóa</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="coupon" items="${coupons}">
				<tr>
					<td>${coupon.name}</td>
					<td>${coupon.code}</td>
					<td><f:formatNumber value="${coupon.discount}" type="percent" /></td>
					<td>${coupon.limit}</td>
					<td><f:formatDate value="${coupon.startDate}" pattern="dd-MM-yyyy HH:mm" /></td>
					<td><f:formatDate value="${coupon.endDate}" pattern="dd-MM-yyyy HH:mm" /></td>
					<td>
						<a href="/admin/coupon/update-form?couponId=${coupon.id}"
							class="btn btn-info btn-sm">Cập nhật</a>
						<a href="/admin/coupon/delete?couponId=${coupon.id}"
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