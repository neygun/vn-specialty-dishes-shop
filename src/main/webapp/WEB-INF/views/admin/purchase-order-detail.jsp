<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Chi tiết đơn nhập</h1>
</div>

<p>Nhà cung cấp: ${purchaseOrder.supplier.name}</p>
<p>Ngày mua: <f:formatDate value="${purchaseOrder.purchaseDate}" pattern="dd-MM-yyyy" /></p>
<p>Tổng tiền: <f:formatNumber value="${purchaseOrder.amount}" pattern="#,##0" /> ₫</p>
<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tên sản phẩm</th>
				<th>Giá mua</th>
				<th>Số lượng</th>
				<th>Ngày sản xuất</th>
				<th>Hạn sử dụng</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="pod" items="${purchaseOrder.purchaseOrderDetails}">
				<tr>
					<td>${pod.product.name}</td>
					<td><f:formatNumber value="${pod.unitPrice}" pattern="#,##0" /> ₫</td>
					<td>${pod.quantity}</td>
					<td><f:formatDate value="${pod.manufacturingDate}" pattern="dd-MM-yyyy" /></td>
					<td><f:formatDate value="${pod.expiryDate}" pattern="dd-MM-yyyy" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>