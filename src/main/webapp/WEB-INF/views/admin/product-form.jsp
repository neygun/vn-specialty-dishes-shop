<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Sản phẩm</h1>
</div>   
    
<form:form action="/admin/product/save" modelAttribute="product" method="post" enctype="multipart/form-data">
	<form:hidden path="id" />
	<form:hidden path="expiryDate" />
	<form:hidden path="available" />
	<form:hidden path="quantity" />
	<form:hidden path="currentPO" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="name">Tên sản phẩm</label>
      	<form:input path="name" id="name" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="unitPrice">Giá</label>
      	<form:input path="unitPrice" id="unitPrice" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="image">Hình ảnh</label>
		<img src="/static/images/products/${product.image}" class="image" alt=" ">
		<input type="file" name="imageFile" accept="image/*" id="image" class="form-control" />
		<form:hidden path="image" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="category">Danh mục</label>
		<form:select path="category.id" id="category" class="form-select">
			<form:options items="${categories}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="supplier">Nhà cung cấp</label>
		<form:select path="supplier.id" id="supplier" class="form-select">
			<form:options items="${suppliers}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="discount">Giảm giá</label>
      	<form:input path="discount" id="discount" class="form-control" />
	</div>
	
	<div class="mb-4">
		<label class="form-label" for="description">Mô tả</label>
      	<form:textarea path="description" id="description" rows="10" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>

<script type="text/javascript">
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>