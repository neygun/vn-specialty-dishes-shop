<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Doanh thu</h1>
</div>

<form action="" method="get">
	<div class="mb-4 col-md-4">
		<label class="form-label" for="startDate">Từ ngày</label>
		<input type="date" name="startDate" id="startDate" class="form-control" />
	</div>
	
	<div class="mb-4 col-md-4">
		<label class="form-label" for="endDate">Đến ngày</label>
		<input type="date" name="endDate" id="endDate" class="form-control" />
	</div>
	
	<button type="submit" class="btn btn-primary btn-block mb-4">Thống kê</button>
</form>

<div>
	<canvas id="mySalesChart"></canvas>
</div>

<div class="table-responsive">
	<table class="table align-middle mb-0 bg-white">
		<thead class="bg-light">
			<tr>
				<th>Tháng</th>
				<th>Doanh thu</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="r" items="${salesReports}">
				<tr>
					<td>${r.month}/${r.year}</td>
					<td><f:formatNumber value="${r.sales}" pattern="#,##0" /> ₫</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script src="/static/js/chart.js"></script>

<script>
	let labels = [], info = []
	<c:forEach var="r" items="${salesReports}">
		labels.push('${r.month}/${r.year}')
		info.push(${r.sales})
	</c:forEach>
		
	window.onload = function() {
		salesChart("mySalesChart", labels, info);
	}
</script>

