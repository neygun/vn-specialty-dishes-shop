<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Thêm danh mục</h1>
</div>   
    
<form:form action="/admin/category/save" modelAttribute="category" method="post" class="col-md-4">
	<form:hidden path="id" />
	
	<div class="mb-4">
		<label class="form-label" for="name">Tên danh mục</label>
      	<form:input path="name" id="name" class="form-control" />
	</div>
	
	<div class="mb-4">
		<label class="form-label" for="mainCategory">Miền</label>
		<form:select path="parent.id" id="mainCategory" class="form-select">
			<form:options items="${mainCategories}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>