<div class="th-cart-wrapper space-top space-extra-bottom"
	style="padding-top: 1em">
	<div class="container">
		<c:if test="${ 1 > 2 }">
			<div class="woocommerce-notices-wrapper">
				<div class="woocommerce-message">Shipping costs updated.</div>
			</div>
		</c:if>
		<form action="#" class="woocommerce-cart-form">
			<table class="cart_table">
				<thead>
					<tr>
						<th class="cart-col-image">Image</th>
						<th class="cart-col-productname"
							style="text-align: left; max-width: 20%">Product Name</th>
						<th class="cart-col-price">Price</th>
						<th class="cart-col-quantity">Quantity</th>
						<th class="cart-col-total">Total</th>
						<th class="cart-col-remove">Remove</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${NbGamesInCart < 1 }">
						<tr class="cart_item">
							<td colspan=6>Panier vide... tristesse</td>
						</tr>
					</c:if>
					<c:if test="${NbGamesInCart > 0}">
						<c:forEach var="game" items="${sessionScope.cart }" varStatus="i">
							<tr class="cart_item">
								<td data-title="Product"><a class="cart-productimage"
									href="shop-detailis.html"><img width="91" height="91"
										src="assets/img/product/product_thumb_1_1.png" alt="Image"></a>
								</td>
								<td data-title="Name" style="text-align: left; max-width: 20%"><a
									class="cart-productname" href="game?id=${game.key.id }"><c:out
											value="${game.key.title }" /></a></td>
								<td data-title="Price"><span class="amount priceU">
										<c:out value="${game.key.price }" />
								</span></td>
								<td data-title="Quantity">
									<div class="quantity">
										<button class="quantity-minus qty-btn">
											<i class="far fa-minus"></i>
										</button>
										<input type="number" class="qty-input" value="${game.value }"
											min="1" max="99">
										<button class="quantity-plus qty-btn">
											<i class="far fa-plus"></i>
										</button>
									</div>
								</td>
								<td data-title="Total"><span class="amount quantityXprice">

								</span></td>
								<td data-title="Remove"><a href="cart?del=${game.key.id }"
									class="remove"><i class="fal fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6" class="actions">
								<button type="submit" class="th-btn">Mettre &#xE0; jour
									le panier</button> <a href="store" class="th-btn">Continuer le
									shopping</a>
							</td>
						</tr>
					</c:if>

				</tbody>
			</table>
		</form>
		<div class="row justify-content-end">
			<div class="col-md-8 col-lg-7 col-xl-6">
				<h2 class="h4 summary-title">Total du panier</h2>
				<c:if test="${NbGamesInCart > 0}">
					<table class="cart_totals">
						<tbody>
							<tr>
								<td>Sous-total du panier</td>
								<td data-title="Cart Subtotal"><span class="amount"><bdi
											id="cartSubTotal"></bdi></span></td>
							</tr>
							<tr class="shipping">
								<th>Pr&#233;paration et envoi</th>
								<td data-title="Shipping and Handling">
									<ul class="woocommerce-shipping-methods list-unstyled">
										<li><input type="radio" id="free_shipping"
											name="shipping_method" class="shipping_method"> <label
											for="free_shipping">Livraison en 3 mois (GRATUIT)</label></li>
										<li><input type="radio" id="flat_rate"
											name="shipping_method" class="shipping_method"
											checked="checked"> <label for="flat_rate">Livraison
												en 3 jours (7.99 &#8364;)</label></li>
									</ul>
									<p class="woocommerce-shipping-destination">&#199;a
										co&#251;te d'aller vite !</p>
								</td>
							</tr>


						</tbody>
						<tfoot>
							<tr class="order-total">
								<td>Super total</td>
								<td data-title="Total"><strong><span
										class="amount"> <bdi id="cartTotal">0 &#8364;</bdi>
									</span></strong></td>
							</tr>
						</tfoot>

					</table>
				</c:if>
				<div class="wc-proceed-to-checkout mb-30">
					<c:if test="${NbGamesInCart < 1}">
						<a href="store" class="th-btn">Faire du shopping !</a>
					</c:if>
					<c:if test="${NbGamesInCart > 0}">
						<a href="checkout.html" class="th-btn">Confirmer l'achat !</a>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</div>
<script src="assets/js/cartPrice.js"></script>