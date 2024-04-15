<main>
	<c:out value="TEST" />
	<table>
		<c:forEach var="user" items="${usersList }">
			<tr>
				<td>
					<c:out value="${user.id }"/>
				</td>
				<td>
					<c:out value="${user.nickname }"/>
				</td>
				<td>
					<c:out value="${user.firstname != null ? user.firstname : 'Non renseigné' }"/>
				</td>
				<td>
					<c:out value="${user.lastname != null ? user.lastname : 'Non renseigné' }"/>
				</td>
		</c:forEach>
	</table>
</main>
