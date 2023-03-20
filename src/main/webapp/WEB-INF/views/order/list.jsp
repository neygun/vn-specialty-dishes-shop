<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form action="/order/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.orderId}" name="orderId" 
				class="form-control" placeholder="Tìm theo mã...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>
	
	<div class="dropdown p-2">
	  <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownorder" data-bs-toggle="dropdown" aria-expanded="false">
	    Trạng thái đơn hàng
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdownorder">
		<li><a class="dropdown-item" href="/order/status/pending">Chờ xác nhận</a></li>
		<li><a class="dropdown-item" href="/order/status/shipping">Đang giao</a></li>
		<li><a class="dropdown-item" href="/order/status/completed">Đã giao</a></li>
	  </ul>
	</div>
</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Mã</th>
				<th>Ngày đặt hàng</th>
				<th>Trạng thái</th>
				<th>Tổng tiền</th>
				<th>Giảm giá</th>
				<th>Chi tiết</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders}">
				<tr>
					<td>${order.id}</td>
					<td><f:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm" /></td>
					<td>${order.status}</td>
					<td><f:formatNumber value="${order.amount}" pattern="#,##0" /> ₫</td>
					<td><f:formatNumber value="${order.discount}" type="percent" /></td>
					<td>
						<a href="/order/detail/${order.id}"
							class="btn btn-success btn-sm">Chi tiết</a>
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
				<a class="page-link" href="?page=${currentPage - 1}">Previous</a>
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
    					<a class="page-link" href="?page=${i}">${i}</a>
    				</li>
				</c:otherwise>
			</c:choose>
		</c:forEach> 
		<c:if test="${currentPage < totalPages}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage + 1}">Next</a>
			</li>
		</c:if>
	</ul>
</nav>