<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Mã giảm giá</h1>
</div>   
    
<form:form action="/admin/coupon/save" modelAttribute="coupon" method="post">
	<form:hidden path="id" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="name">Tên</label>
      	<form:input path="name" id="name" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="code">Mã</label>
      	<form:input path="code" id="code" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="discount">Giảm giá</label>
      	<form:input path="discount" id="discount" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="limit">Số lượng</label>
      	<form:input path="limit" id="limit" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="startDate">Ngày bắt đầu</label>
		<form:input type="datetime-local" path="startDate" id="startDate" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="endDate">Ngày kết thúc</label>
		<form:input type="datetime-local" path="endDate" id="endDate" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>