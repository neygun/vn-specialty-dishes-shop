<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Chi tiết đơn nhập</h1>
</div>   
    
<form:form action="/admin/purchase-order/save-detail" modelAttribute="pod" method="post">
	<form:hidden path="id" />
	<form:hidden path="purchaseOrder.id" />
	<form:hidden path="product.id" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="unitPrice">Giá mua</label>
      	<form:input path="unitPrice" id="unitPrice" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="quantity">Số lượng</label>
      	<form:input path="quantity" id="quantity" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="manufacturingDate">Ngày sản xuất</label>
		<form:input type="date" path="manufacturingDate" id="manufacturingDate" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="expiryDate">Hạn sử dụng</label>
		<form:input type="date" path="expiryDate" id="expiryDate" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>