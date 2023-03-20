<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<section class="">
		<div class="container py-5 h-100">
			<div class="row justify-content-center align-items-center h-100">
				<div class="col-12 col-lg-9 col-xl-7">
					<div class="card shadow-2-strong card-registration"
						style="border-radius: 15px;">
						<div class="card-body p-4 p-md-5">
							<h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Đăng ký</h3>
							
							<form:form action="/register" modelAttribute="account" method="post">
								<div class="form-outline mb-4">
									<form:input path="username" placeholder="Tên đăng nhập" class="form-control form-control-lg" />
									<form:errors path="username" cssClass="error" />
								</div>

								<div class="row">
									<div class="col-md-6 mb-4">
										<div class="form-outline">
											<form:input type="password" path="password" placeholder="Mật khẩu" class="form-control form-control-lg" />
											<form:errors path="password" cssClass="error" />
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="form-outline">
											<form:input type="password" path="matchingPassword" placeholder="Nhập lại mật khẩu" class="form-control form-control-lg" />
											<form:errors path="matchingPassword" cssClass="error" />
											<form:errors path="" cssClass="error" />
										</div>
									</div>
								</div>
								
								<div class="form-outline mb-4">
									<form:input path="email" placeholder="Email" class="form-control form-control-lg" />
									<form:errors path="email" cssClass="error" />
								</div>

								<div class="mt-4 pt-2">
									<input class="btn btn-primary btn-lg" type="submit" value="ĐĂNG KÝ" />
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>