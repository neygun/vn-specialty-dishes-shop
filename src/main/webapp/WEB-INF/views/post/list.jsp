<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<h1>Bài viết</h1>
<form action="/post/search" method="get" class="input-group p-2">
		<div class="form-outline">
			<input type="search" value="${param.keyword}" name="keyword" 
				class="form-control" placeholder="Tìm kiếm...">
		</div>
		<button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
</form>
	
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <c:forEach var="post" items="${posts}">
        <div class="col">
          <div class="card shadow-sm">
			<div>
	          <a href="/post/detail/${post.id}">
	            <img src="/static/images/posts/${post.image}" style="width: 100%; height: 225px;">
	          </a>
            </div>
            <div class="card-body">
              <h4 class="card-text">${post.title}</h4>
              <div class="d-flex justify-content-between align-items-center">
                <small class="text-muted"><f:formatDate value="${post.postDate}" pattern="dd-MM-yyyy" /></small>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
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