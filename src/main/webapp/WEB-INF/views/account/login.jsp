<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

		<div class="row align-items-center justify-content-center">
			<div class="col-md-6">
				<form action="/login" method="POST">
					<div class="text-center mb-5">
						<h3>Đăng nhập</h3>
					</div>
					
					<c:if test="${message != null}">
						<div class="alert alert-info text-center">
							<i>${message}</i>
						</div>
					</c:if>
					
					<c:if test="${param.error != null}">
						<div class="alert alert-danger text-center">
							<i>Tên đăng nhập hoặc Mật khẩu không đúng, vui lòng thử lại</i>
						</div>
					</c:if>
					
					<c:if test="${param.logout != null}">
						<div class="alert alert-success text-center">
							<i>Bạn đã đăng xuất</i>
						</div>
					</c:if>
					
					<div class="input-group mb-3">
						<span class="input-group-text" id="person-icon"><i class="bi bi-person-fill"></i></span>
						<input type="text" class="form-control" name="username" placeholder="Username"
							aria-label="Username" aria-describedby="person-icon">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text" id="lock-icon"><i class="bi bi-lock-fill"></i></span>
						<input type="password" class="form-control" name="password" placeholder="Password"
							aria-label="Password" aria-describedby="lock-icon">
					</div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="remember-me"
							name="remember-me"> <label class="form-check-label"
							for="remember-me">Remember me</label>
					</div>
					<div class="d-grid gap-2">
						<button type="submit" class="btn btn-primary">ĐĂNG NHẬP</button>
					</div>
				</form>
			</div>
		</div>