<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Đơn nhập</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/purchase-order/add-form" class="btn btn-primary btn-sm mb-3">Thêm đơn nhập</a>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Mã</th>
				<th>Nhà cung cấp</th>
				<th>Ngày mua</th>
				<th>Tổng tiền</th>
				<th>Xem chi tiết</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="purchaseOrder" items="${purchaseOrders}">
				<tr>
					<td>${purchaseOrder.id}</td>
					<td>${purchaseOrder.supplier.name}</td>
					<td><f:formatDate value="${purchaseOrder.purchaseDate}" pattern="dd-MM-yyyy" /></td>
					<td><f:formatNumber value="${purchaseOrder.amount}" pattern="#,##0" /> ₫</td>
					<td>
						<a href="/admin/purchase-order/detail/${purchaseOrder.id}"
							class="btn btn-success btn-sm">Xem chi tiết</a>
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