$(document).ready(function () {
	$('#department-list-table').DataTable({
		searching: false,
		paging: true,
		ordering: true,
		pageLength: 10,
		order: [[2, 'asc']],
		dom: '<"d-flex flex-wrap justify-content-between align-items-center gap-2 mb-2"' +
			'<"d-flex flex-wrap align-items-center gap-3"li>' +
			'p' +
			'>rt',
		language: {
			url: 'https://cdn.datatables.net/plug-ins/1.13.8/i18n/ja.json'
		}
	});
});
