<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<nav id="sidebarMenu"
	class="col-md-3 col-lg-2 d-md-block text-white bg-dark sidebar collapse">
	<div class="position-sticky pt-3">
		<a href="/admin"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
			<svg class="bi me-2" width="40" height="32">
				</svg> <span class="fs-4">Admin</span>
		</a>
		<hr>
		<ul class="nav flex-column nav-pills">
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/category/list"> <span data-feather="grid"></span>
					Danh mục
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/product/list"> <span data-feather="shopping-cart"></span>
					Sản phẩm
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/order/list"> <span data-feather="file"></span> Đơn
					hàng
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/supplier/list"> <span data-feather="layers"></span>
					Nhà cung cấp
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/purchase-order/list"> <span data-feather="truck"></span>
					Đơn nhập
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/post/list"> <span data-feather="file-text"></span>
					Bài viết
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/coupon/list"> <span data-feather="gift"></span> Mã
					giảm giá
			</a></li>
			<li class="nav-item"><a class="nav-link text-white"
				href="/admin/report/sales"> <span data-feather="bar-chart-2"></span>
					Thống kê
			</a></li>
		</ul>
	</div>
</nav>