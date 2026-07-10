// Auto-hide flash/error alert banners after 3 seconds.
document.addEventListener("DOMContentLoaded", function () {
	var alerts = document.querySelectorAll(".alert");
	alerts.forEach(function (el) {
		setTimeout(function () {
			el.style.transition = "opacity 0.4s ease";
			el.style.opacity = "0";
			setTimeout(function () {
				el.style.display = "none";
			}, 400);
		}, 3000);
	});
});
