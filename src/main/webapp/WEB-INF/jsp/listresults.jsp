<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
</head>
<body>
	<div class="container-fluid">
		<h1>List of results:</h1>
		<table class="table">
			<thead class="thead-inverse">
				<tr>
					<th>URL</th>
					<th>Status</th>

				</tr>
			</thead>
			<tbody>	
				<c:forEach var="result" items="${results}">
					<c:set var="rowClass" value=""/>
					<c:if test="${result.taskStatus eq 'FAILED'}"><c:set var="rowClass" value="table-danger"/></c:if>
					<c:if test="${result.taskStatus eq 'SUCCESS'}"><c:set var="rowClass" value="table-success"/></c:if>
					<c:if test="${result.taskStatus eq 'QUEUED'}"><c:set var="rowClass" value="table-info"/></c:if>
					
					<tr class="${rowClass}">
						<td>${result.url}</td>
						<td>${result.taskStatus}</td>
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
</body>
</html>
