<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Tạo đơn nhập</h1>
</div>   
    
<form:form action="/admin/purchase-order/save" modelAttribute="purchaseOrder" method="post">
	<form:hidden path="id" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="supplier">Nhà cung cấp</label>
		<form:select path="supplier.id" id="supplier" class="form-select">
			<form:options items="${suppliers}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="purchaseDate">Ngày mua</label>
		<form:input type="date" path="purchaseDate" id="purchaseDate" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Tiếp</button>
</form:form>