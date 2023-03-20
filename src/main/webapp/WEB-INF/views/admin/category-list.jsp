<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Danh mục</h1>
</div>

<c:if test="${message != null}">
	<div class="alert alert-info text-center">
		<i>${message}</i>
	</div>
</c:if>

<a href="/admin/category/add-form" class="btn btn-primary btn-sm mb-3">Thêm danh mục</a>

<div class="d-flex flex-row mb-3">
	<!-- Search box -->
	<form action="/admin/category/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
	</form>

	<div class="dropdown p-2">
	  <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdowncate" data-bs-toggle="dropdown" aria-expanded="false">
	    Miền
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdowncate">
		<c:forEach var="mainCategory" items="${mainCategories}">
			<li><a class="dropdown-item" href="/admin/category/main-category/${mainCategory.id}">${mainCategory.name}</a></li>
		</c:forEach>
	  </ul>
	</div>

</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tên</th>
				<th>Cập nhật/Xóa</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.name}</td>
					<td>
						<a href="/admin/category/update-form?categoryId=${category.id}"
							class="btn btn-info btn-sm">Cập nhật</a>
						<a href="/admin/category/delete?categoryId=${category.id}"
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
				<a class="page-link" href="?page=${currentPage - 1}${keyword != null ? '&keyword='+=keyword : ''}">Previous</a>
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
    					<a class="page-link" href="?page=${i}${keyword != null ? '&keyword='+=keyword : ''}">${i}</a>
    				</li>
				</c:otherwise>
			</c:choose>
		</c:forEach> 
		<c:if test="${currentPage < totalPages}">
			<li class="page-item">
				<a class="page-link" href="?page=${currentPage + 1}${keyword != null ? '&keyword='+=keyword : ''}">Next</a>
			</li>
		</c:if>
	</ul>
</nav>