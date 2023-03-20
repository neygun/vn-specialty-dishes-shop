<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Thêm sản phẩm vào đơn nhập</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/purchase-order/detail/${purchaseOrder.id}" class="btn btn-primary btn-sm mb-3">Xong</a>

<p>Nhà cung cấp: ${purchaseOrder.supplier.name}</p>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>
</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tên</th>
				<th>Hình ảnh</th>
				<th>Thêm</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.name}</td>
					<td><img src="/static/images/products/${product.image}" style="height: 40px; width: 40px;"></td>
					<td>
						<a href="/admin/purchase-order/add-product-form?productId=${product.id}&purchaseOrderId=${purchaseOrder.id}"
							class="btn btn-info btn-sm">Thêm</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>