<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Chọn lô hàng bán</h1>
</div>
<p>Sản phẩm: ${product.name}</p>
<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Mã đơn nhập</th>
				<th>Ngày sản xuất</th>
				<th>Hạn sử dụng</th>
				<th>Giá mua</th>
				<th>Số lượng</th>
				<th>Đã bán</th>
				<th>Còn lại</th>
				<th>Chọn lô hàng</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="pod" items="${product.purchaseOrderDetails}">
				<tr>
					<td>${pod.purchaseOrder.id}</td>
					<td><f:formatDate value="${pod.manufacturingDate}" pattern="dd-MM-yyyy" /></td>
					<td><f:formatDate value="${pod.expiryDate}" pattern="dd-MM-yyyy" /></td>
					<td><f:formatNumber value="${pod.unitPrice}" pattern="#,##0" /> ₫</td>
					<td>${pod.quantity}</td>
					<td>${pod.sold}</td>
					<td>${pod.inStock}</td>
					<td>
						<c:if test="${product.currentPO == pod.purchaseOrder.id}">
							Đã chọn
						</c:if>
						<c:if test="${product.currentPO != pod.purchaseOrder.id && pod.inStock != 0}">
							<a href="/admin/product/set-purchase-order?productId=${product.id}&purchaseOrderId=${pod.purchaseOrder.id}"
								class="btn btn-info btn-sm">Chọn</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>