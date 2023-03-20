<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<c:set var="parameters" value="${keyword != null ? '&keyword='+=keyword : ''}${sortBy != null ? '&sortBy='+=sortBy : ''}" />

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Bài viết</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/post/add-form" class="btn btn-primary btn-sm mb-3">Thêm bài viết</a>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form action="/admin/post/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>
	
	<!-- Sort -->
	<form method="get" class="col-md-2 p-2">
  		<c:if test="${keyword != null}">
	  		<input type="hidden" name="keyword" value="${keyword}">
	  	</c:if>
	  	
	  	<select class="form-select" name="sortBy" onchange="this.form.submit();">
			<option value="default" ${param.sortBy == 'default' ? 'selected' : ''}>Mặc định</option>
			<option value="date-asc" ${param.sortBy == 'date-asc' ? 'selected' : ''}>Cũ nhất</option> 
			<option value="date-desc" ${param.sortBy == 'date-desc' ? 'selected' : ''}>Mới nhất</option>                   
		</select>
	</form>
</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tiêu đề</th>
				<th>Ngày tạo</th>
				<th>Cập nhật/Xóa</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${posts}">
				<tr>
					<td>${post.title}</td>
					<td><f:formatDate value="${post.postDate}" pattern="dd-MM-yyyy" /></td>
					<td>
						<a href="/admin/post/update-form?postId=${post.id}"
							class="btn btn-info btn-sm">Cập nhật</a>
						<a href="/admin/post/delete?postId=${post.id}"
							class="btn btn-danger btn-sm">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<nav aria-label="Page Navigation">
	<ul class="pagination">
		<c:if test="${currentPage > 1}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage - 1}${parameters}">Previous</a>
    		</li>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="i">
			<c:choose>
				<c:when test="${currentPage == i}">
					<li class="page-item active" aria-current="page">
    					<span class="page-link">${i}</span>
    				</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
    					<a class="page-link" href="?page=${i}${parameters}">${i}</a>
    				</li>
				</c:otherwise>
			</c:choose>
		</c:forEach> 
		<c:if test="${currentPage < totalPages}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage + 1}${parameters}">Next</a>
			</li>
		</c:if>
	</ul>
</nav>