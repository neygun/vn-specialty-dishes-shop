<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Bài viết</h1>
</div>   
    
<form:form action="/admin/post/save" modelAttribute="post" method="post" enctype="multipart/form-data">
	<form:hidden path="id" />
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="title">Tiêu đề</label>
      	<form:input path="title" id="title" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-6">
		<label class="form-label" for="image">Hình ảnh</label>
		<img src="/static/images/posts/${post.image}" class="image" alt=" ">
		<input type="file" name="imageFile" accept="image/*" id="image" class="form-control" />
		<form:hidden path="image" />
	</div>
	
	<div class="mb-4">
		<label class="form-label" for="content">Nội dung</label>
      	<form:textarea path="content" id="content" rows="10" class="form-control" />
	</div>

  	<button type="submit" class="btn btn-primary btn-block mb-4">Lưu</button>
</form:form>

<script type="text/javascript">
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>