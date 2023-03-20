<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="p-3 bg-dark text-white">
<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/"
				class="d-flex align-items-center mb-2 mb-lg-0 col-lg-1 text-white text-decoration-none">
				<svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" fill="currentColor" class="bi bi-bag-fill" viewBox="0 0 16 16">
  					<path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5z"/>
				</svg>
			</a>

			<form class="col-12 col-lg-6 mb-3 mb-lg-0 me-lg-3" action="/product/search" method="get">
				<input type="search" 
					class="form-control form-control-dark" 
					value="${param.keyword}" name="keyword" 
					placeholder="Search..." 
					aria-label="Search">
			</form>

			<div class="col-lg-4" style="text-align:center;">
				<sec:authorize access="isAnonymous()">
					<a href="/login" class="btn btn-outline-light me-2">Đăng nhập</a>
					<a href="/register" class="btn btn-success">Đăng ký</a>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<div class="dropdown">
		          <a href="#" class="text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false" style="font-size: 1rem;">
		            <i class="bi bi-person" style="font-size: 2rem; vertical-align: middle;"></i> ${customer.username}
		          </a>
		          <ul class="dropdown-menu" aria-labelledby="dropdownUser1">
		            <li><a class="dropdown-item" href="/customer/profile">Tài khoản của tôi</a></li>
		            <li><a class="dropdown-item" href="/order/list">Đơn hàng của tôi</a></li>
		            <li><a class="dropdown-item" href="/logout">Đăng xuất</a></li>
		          </ul>
		        </div>
				</sec:authorize>
			</div>
			
			<div class="text-end">
				<a href="/cart" class="position-relative text-white">
					<i class="bi bi-cart3" style="font-size: 2.5rem;"></i>
  					<span id="cart-count" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				    ${cartCount}
  					</span>
				</a>	
			</div>
		</div>
	
	<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
  
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav menu" style="font-size: 20px;">
      	<li class="nav-item">
          <a class="nav-link" href="/">Trang chủ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/about">Giới thiệu</a>
        </li>
        <c:forEach var="mainCategory" items="${mainCategories}">
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="/product/list/main-category/${mainCategory.id}" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Đặc sản ${mainCategory.name}
	          </a>
	          <ul class="dropdown-menu">            
	            <c:forEach var="subCategory" items="${mainCategory.subCategories}">
	            	<li><a class="dropdown-item" href="/product/list/category/${subCategory.id}">Đặc sản ${subCategory.name}</a></li>
	  			</c:forEach>
	          </ul>
	        </li>
  		</c:forEach>
        <li class="nav-item">
          <a class="nav-link" href="/product/list/all">Đặc sản 3 miền</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/list/best-selling">Sản phẩm bán chạy</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/post/list">Tin tức</a>
        </li>
        
      </ul>
    </div>
  
</nav>
</div>
</header>