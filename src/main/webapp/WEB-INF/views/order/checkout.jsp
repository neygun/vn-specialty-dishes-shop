<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row g-5">
      <div class="col-md-5 col-lg-8 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
        </h4>
        
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
            <c:forEach var="item" items="${cartItems}">
				<tr>
                <th scope="row">
                  <div class="d-flex align-items-center">
                  <a href="/product/detail/${item.product.id}">
                    <img src="/static/images/products/${item.product.image}" class="img-fluid rounded-3"
                      style="height: 40px; width: 40px;" alt="Item">
                  </a>
                    <div class="flex-column ms-4">
                      <p class="mb-2">${item.product.name}</p>
                      <p class="mb-0"></p>
                    </div>
                  </div>
                </th>
                <td class="align-middle">
                  <p class="mb-0" style="font-weight: 500;">
                  	<span class="${item.product.discount != 0 ? 'price' : ''}">
	                  <f:formatNumber value="${item.product.unitPrice}" pattern="#,##0" /> ₫
	                </span>
	                <c:if test="${item.product.discount != 0}">
	                <span>
	                  &nbsp;<f:formatNumber value="${item.product.discountedPrice}" pattern="#,##0" /> ₫
	                </span>
					</c:if>
                  </p>
                </td>
                <td class="align-middle">
                  <p class="mb-0" style="font-weight: 500;">
                  	${item.quantity}
                  </p>
                </td>
                <td class="align-middle">
                  <p class="mb-0" style="font-weight: 500;">
                  	<f:formatNumber value="${item.subtotal}" pattern="#,##0" /> ₫
                  </p>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        
          <div class="input-group">
            <input type="text" class="form-control coupon-code" placeholder="Nhập mã giảm giá">
            <button type="button" class="btn btn-secondary btn-apply-coupon">Áp dụng</button>
          </div>
          <div id="invalid-coupon"></div>
        
         <div class="card shadow-2-strong mb-5 mb-lg-0 border-0">
          <div class="card-body p-4">
        	<div class="row">
              <div class="col-md-6 col-lg-4 col-xl-3 mb-4 mb-md-0"></div>
              <div class="col-md-6 col-lg-3 col-xl-4"></div>
              <div class="col-lg-5 col-xl-5">

                <div class="d-flex justify-content-between" style="font-weight: 500;">
                  <p class="mb-2">Tạm tính</p>
                  <p class="mb-2" id="subtotal" data-subtotal="${subTotal}">
                  	<f:formatNumber value="${subTotal}" pattern="#,##0" /> ₫
                  </p>
                </div>
                
                <div class="d-flex justify-content-between" style="font-weight: 500;">
                  <p class="mb-0">Giảm giá</p>
                  <p class="mb-0" id="coupon-disc">0 ₫</p>
                </div>

                <div class="d-flex justify-content-between" style="font-weight: 500;">
                  <p class="mb-0">Phí vận chuyển</p>
                  <p class="mb-0">0 ₫</p>
                </div>

                <hr class="my-4">

                <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">
                  <p class="mb-2">Tổng cộng</p>
                  <p class="mb-2" id="total">
                  	<f:formatNumber value="${subTotal}" pattern="#,##0" /> ₫
                  </p>
                </div>

              </div>
            </div>
        	</div>
        </div>
      </div>
      <div class="col-md-7 col-lg-4">
        <h4 class="mb-3">Thanh toán</h4>
        <form:form action="/order/checkout" modelAttribute="order" method="post">
          <div class="row g-3">          
            <div class="col-12">
              <label for="phoneNumber" class="form-label">Số điện thoại</label>
              <form:input path="phoneNumber" id="phoneNumber" class="form-control" />
            </div>

            <div class="col-12">
              <label for="address" class="form-label">Địa chỉ</label>
              <form:textarea path="address" id="address" class="form-control" />
            </div>
            
            <div class="col-12">
              <label for="note" class="form-label">Ghi chú</label>
              <form:textarea path="note" id="note" class="form-control" />
            </div>
          </div>
          
          <div id="coupon-id"></div>

          <hr class="my-4">

          <h4 class="mb-3">Phương thức thanh toán</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="cash" name="paymentMethod" type="radio" class="form-check-input" checked required>
              <label class="form-check-label" for="cash">Tiền mặt</label>
            </div>
          </div>

          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" type="submit">Đặt hàng</button>
        </form:form>
      </div>
    </div>
    
