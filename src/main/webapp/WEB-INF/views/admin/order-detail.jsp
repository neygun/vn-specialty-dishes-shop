<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Chi tiết đơn hàng</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<div class="d-flex justify-content-between align-items-center mb-4">
	<p class="lead fw-normal mb-0" style="color: #a8729a;">Mã đơn hàng
		: ${order.id}</p>
	<p class="mb-0">
		Ngày đặt hàng:
		<f:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm" />
	</p>
</div>

<form:form action="/admin/order/update-status" modelAttribute="order" method="post">
	<form:hidden path="id" />
	<div class="row align-items-center pt-4 pb-3">
		<div class="col-md-1">
			<h6 class="mb-0">Trạng thái</h6>
		</div>
		<div class="col-md-3">
			<div class="input-group">
				<form:select path="status" class="form-select">
					<form:option value="Chờ xác nhận">Chờ xác nhận</form:option>
					<form:option value="Đang giao">Đang giao</form:option>
					<form:option value="Đã giao">Đã giao</form:option>
				</form:select>
				<button class="btn btn-primary" type="submit">Cập nhật</button>
			</div>
		</div>
	</div>
</form:form>

<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th scope="col" class="h5">Sản phẩm</th>
				<th scope="col">Đơn giá</th>
				<th scope="col">Số lượng</th>
				<th scope="col">Thành tiền</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderDetail" items="${orderDetails}">
				<tr>
					<th scope="row">
						<div class="d-flex align-items-center">
							<a href="/product/detail/${orderDetail.product.id}"> <img
								src="/static/images/products/${orderDetail.product.image}"
								class="img-fluid rounded-3" style="height: 40px; width: 40px;"
								alt="Item">
							</a>
							<div class="flex-column ms-4">
								<p class="mb-2">${orderDetail.product.name}</p>
								<p class="mb-0"></p>
							</div>
						</div>
					</th>
					<td class="align-middle">
						<p class="mb-0" style="font-weight: 500;">
							<span class="${orderDetail.discount != 0 ? 'price' : ''}">
								<f:formatNumber value="${orderDetail.unitPrice}" pattern="#,##0" />
								₫
							</span>
							<c:if test="${orderDetail.discount != 0}">
								<span> &nbsp;<f:formatNumber
										value="${orderDetail.discountedPrice}" pattern="#,##0" /> ₫
								</span>
							</c:if>
						</p>
					</td>
					<td class="align-middle">
						<p class="mb-0" style="font-weight: 500;">${orderDetail.quantity}</p>
					</td>
					<td class="align-middle">
						<p class="mb-0" style="font-weight: 500;">
							<f:formatNumber value="${orderDetail.subtotal}" pattern="#,##0" />
							₫
						</p>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="card shadow-2-strong mb-5 mb-lg-0 border-0">
	<div class="card-body p-4">
		<div class="row">
			<div class="col-md-6 col-lg-4 col-xl-6 mb-4 mb-md-0">
				<p class="text-muted mb-0">Địa chỉ: ${order.address}</p>
				<p class="text-muted mb-0">Điện thoại: ${order.phoneNumber}</p>
				<p class="text-muted mb-0">Ghi chú: ${order.note}</p>
				<p class="text-muted mb-0">Phương thức thanh toán: Tiền mặt</p>
			</div>
			<div class="col-md-6 col-lg-3 col-xl-3"></div>
			<div class="col-lg-5 col-xl-3">
				<div class="d-flex justify-content-between"
					style="font-weight: 500;">
					<p class="mb-0">Tạm tính</p>
					<p class="mb-0">
						<f:formatNumber value="${order.amount / (1-order.discount)}"
							pattern="#,##0" />
						₫
					</p>
				</div>

				<div class="d-flex justify-content-between"
					style="font-weight: 500;">
					<p class="mb-0">Giảm giá</p>
					<p class="mb-0">
						<f:formatNumber
							value="${-order.amount*order.discount / (1-order.discount)}"
							pattern="#,##0" />
						₫
					</p>
				</div>

				<div class="d-flex justify-content-between"
					style="font-weight: 500;">
					<p class="mb-0">Phí vận chuyển</p>
					<p class="mb-0">0 ₫</p>
				</div>

				<div class="d-flex justify-content-between mb-4"
					style="font-weight: 500;">
					<p class="mb-0">Tổng cộng</p>
					<p class="mb-0">
						<f:formatNumber value="${order.amount}" pattern="#,##0" />
						₫
					</p>
				</div>

			</div>
		</div>
	</div>
</div>
