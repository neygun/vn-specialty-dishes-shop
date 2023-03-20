<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<section class="h-100 h-custom">
  <div class="container h-100 py-5">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">

        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col" class="h5">Sản phẩm</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Thành tiền</th>
                <th scope="col">Xóa</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cartItems}">
				<tr data-item-id="${item.id}">
                <th scope="row">
                  <div class="d-flex align-items-center">
                  <a href="/product/detail/${item.product.id}">
                    <img src="/static/images/products/${item.product.image}" class="img-fluid rounded-3"
                      style="width: 120px;" alt="Item">
                  </a>
                    <div class="flex-column ms-4">
                      <p class="mb-2">${item.product.name}</p>
                      <p class="mb-0 error">
                      ${item.quantity > item.product.quantity ? 'Còn '+=item.product.quantity+=' sản phẩm' : ''}
                      </p>
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
                  <div class="d-flex flex-row">
                    <button class="btn btn-link px-2 btn-quantity"
                      onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                      <i class="bi bi-dash-square"></i>
                    </button>

                    <input min="0" value="${item.quantity}" type="number"
                      class="form-control form-control-sm" style="width: 50px;" />

                    <button class="btn btn-link px-2 btn-quantity"
                      onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                      <i class="bi bi-plus-square"></i>
                    </button>
                  </div>
                </td>
                <td class="align-middle">
                  <p class="mb-0 subtotal" style="font-weight: 500;">
                  	<f:formatNumber value="${item.subtotal}" pattern="#,##0" /> ₫
                  </p>
                </td>
                <td class="align-middle"> 
					<i class="bi bi-trash btn-cart-remove"></i>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>

        <div class="card shadow-2-strong mb-5 mb-lg-0 border-0">
          <div class="card-body p-4">

            <div class="row">
              <div class="col-md-6 col-lg-4 col-xl-3 mb-4 mb-md-0"></div>
              <div class="col-md-6 col-lg-4 col-xl-6"></div>
              <div class="col-lg-4 col-xl-3">

                <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">
                  <p class="mb-2">Tổng cộng: </p>
                  <p id="cart-amount" class="mb-2">
                  	<f:formatNumber value="${cartAmount}" pattern="#,##0" /> ₫
                  </p>
                </div>

                <a href="/order/checkout" class="btn btn-primary btn-lg">Mua hàng</a>

              </div>
            </div>

          </div>
        </div>

      </div>
    </div>
  </div>
</section>