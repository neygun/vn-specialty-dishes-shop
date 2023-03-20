function generateColor() {
	let r = parseInt(Math.random()*255);
	let g = parseInt(Math.random()*255);
	let b = parseInt(Math.random()*255);
	return `rgb(${r}, ${g}, ${b})`
}

function salesChart(id, labels = [], info = []) {
	let colors = []
	for (let i=0; i<info.length; i++) {
		colors.push(generateColor())
	}
	const data = {
		labels: labels,
		datasets: [{
			label: 'Doanh thu',
			data: info,
			backgroundColor: colors,
			borderColor: colors,
			hoverOffset: 4
		}]
	};

	const config = {
		type: 'bar',
		data: data,
	};

	let ctx = document.getElementById(id).getContext("2d")
	new Chart(ctx, config)
}