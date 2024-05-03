<c:if test="${sessionScope.user != null }">
	<section class="space">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h2>
						Commande n�
						<c:out value="${ordersList[0].id }" />
						du
						<fmt:formatDate pattern="dd-MM-yyyy"
							value="${ordersList[0].date }" />
						du client
						<c:out value="${user.nickname }" />
					</h2>

					<c:set var="grandTotal" value="0" />
					<c:set var="totalQuantities" value="0" />

					<div class="table-responsive">
						<table class="table tournament-table"
							id="detailsList">
							<thead>
								<tr>
									<th class="border-top-0">n�</th>
									<th class="border-top-0">Jeu</th>
									<th class="border-top-0">Quantit�</th>
									<th class="border-top-0">Prix Unitaire</th>
									<th class="border-top-0">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${detailsList }" var="detail" varStatus="index">
									<tr>
										<td><span class="ml-2"><c:out
													value="${index.count }" /></span></td>
										<td><a href="game?id=${detail.gameId }"><span class="ml-2"><c:out
													value="${detail.name }" /></span></a></td>
										<td><span class="ml-2"><c:out
													value="${detail.totalQuantity }" /></span></td>
										<td><span class="ml-2"><fmt:formatNumber
													value="${detail.amount }" type="number" pattern="#,##0.00" />&#8364;</span></td>
										<td><span class="ml-2"><fmt:formatNumber
													value="${detail.totalQuantity*detail.amount }"
													type="number" pattern="#,##0.00" />&#8364;</span></td>
										<c:set var="grandTotal"
											value="${grandTotal + detail.totalQuantity*detail.amount}" />
										<c:set var="totalQuantities"
											value="${totalQuantities + detail.totalQuantity}" />
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<h5 style="color:white">
							Nombre de jeux :
							<c:out value="${totalQuantities }" />
							.
						</h5>
						<h5 style="color:white">
							Total de la commande :
							<fmt:formatNumber value="${grandTotal }" type="number"
								pattern="#,##0.00" />
							&#8364;
						</h5>

					</div>

				</div>
			</div>

		</div>

	</section>
</c:if>