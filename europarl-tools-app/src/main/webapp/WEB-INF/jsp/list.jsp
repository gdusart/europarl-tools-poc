<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

<link rel="stylesheet" href="/css/ui.css">
</head>

<body>
	<div class="container-fluid">
		<h1>${list.title}</h1>
		<table class="table">
			<thead class="thead-inverse">
				<c:forEach var="listheader" items="${list.headers}">
					<th>${listheader}</th>
				</c:forEach>
			</thead>
			<tbody>
				<c:forEach var="row" items="${list.rows}">

					<c:set var="rowClass" value="${empty row.link ? '' : 'clickable-row'}"/>
					<c:if test="${!empty row.level}">
						<c:set var="rowClass" value="${rowClass} table-${fn:toLowerCase(row.level)}" />
					</c:if>

					<tr class="${rowClass}" data-href="${row.link}">

					<c:forEach var="column" items="${row.columns}">
						<td>${column}</td>
					</c:forEach>
					</tr>

				</c:forEach>
			</tbody>
		</table>

		<!-- jQuery first, then Tether, then Bootstrap JS. -->
		<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
			integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
			integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
			crossorigin="anonymous"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
			crossorigin="anonymous"></script>
	</div>

	<script>
		jQuery(document).ready(function($) {
			$(".clickable-row").click(function() {
				window.location = $(this).data("href");
			});
		});
	</script>
</body>
</html>