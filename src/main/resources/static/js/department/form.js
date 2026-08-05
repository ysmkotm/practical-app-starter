(function () {
	const deleteForm = document.getElementById('department-delete-form');
	if (!deleteForm) {
		return;
	}

	deleteForm.addEventListener('submit', function (event) {
		const message = deleteForm.getAttribute('data-confirm-message');
		if (!window.confirm(message)) {
			event.preventDefault();
		}
	});
})();
