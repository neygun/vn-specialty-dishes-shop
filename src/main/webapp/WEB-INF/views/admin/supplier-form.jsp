<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Nhà cung cấp</h1>
</div>   
    
<form:form action="/admin/supplier/save" modelAttribute="supplier" method="post">
	<form:hidden path="id" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="name">Tên</label>
      	<form:input path="name" id="name" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="phoneNumber">Số điện thoại</label>
      	<form:input path="phoneNumber" id="phoneNumber" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="address">Địa chỉ</label>
      	<form:input path="address" id="address" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>