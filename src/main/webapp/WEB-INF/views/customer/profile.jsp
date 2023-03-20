<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<form:form action="/customer/profile" modelAttribute="customer" method="post" enctype="multipart/form-data">
	<form:hidden path="id" />
	
	<div class="form-outline mb-4">
		<label class="form-label" for="username">Tên tài khoản</label>
      	<form:input path="username" id="username" readonly="true" class="form-control" />
	</div>
	
  <div class="row mb-4">
    <div class="col">
      <div class="form-outline">
      	<label class="form-label" for="firstName">Tên</label>
      	<form:input path="firstName" id="firstName" class="form-control" />
      </div>
    </div>
    <div class="col">
      <div class="form-outline">
        <label class="form-label" for="lastName">Họ</label>
      	<form:input path="lastName" id="lastName" class="form-control" />
      </div>
    </div>
  </div>

	<div class="form-outline mb-4">
		<label class="form-label" for="phoneNumber">Số điện thoại</label>
		<form:input path="phoneNumber" id="phoneNumber" class="form-control" />
	</div>

  	<div class="form-outline mb-4">
		<label class="form-label" for="address">Địa chỉ</label>
		<form:input path="address" id="address" class="form-control" />
	</div>

	<div class="form-outline mb-4">
		<label class="form-label" for="avatar">Avatar</label>
		<img src="/static/images/customers/${customer.avatar}" class="avatar" >
		<input type="file" name="imageFile" accept="image/*" id="avatar" class="form-control" />
		<form:hidden path="avatar" />
	</div>

  <button type="submit" class="btn btn-primary btn-block mb-4">Cập nhật thông tin</button>
</form:form>