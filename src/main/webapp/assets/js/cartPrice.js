/**
 * 
 */
console.info("cartPrice.js");

const uPricesDOM = document.querySelectorAll(".priceU");
const quantitysDOM = document.querySelectorAll(".qty-input");
const quantityXpriceDOM = document.querySelectorAll(".quantityXprice");
const subTotalDOM = document.getElementById("cartSubTotal");
const TotalDOM = document.getElementById("cartTotal");

const freeShipRate = document.getElementById("free_shipping");
const flatRate = document.getElementById("flat_rate");

quantitysDOM.forEach(function(quantity) {
	quantity.addEventListener('change', function(event) {
		updateAll();
	})
})

updateAll();

function updateTotal(subTotalCart) {

	if (subTotalCart > 0) {
		if (flatRate.checked == true) {
			TotalDOM.innerHTML = subTotalCart + 7.99;
		} else {
			TotalDOM.innerHTML = subTotalCart;
		}

		
	}else{
		TotalDOM.innerHTML = "0.00";
	}
	TotalDOM.innerHTML += " €";

}

function updateAll() {
	let uPriceXquantity;
	let subTotalCart = 0;
	let i = 0;
	uPricesDOM.forEach((element) => {
		uPriceXquantity = parseFloat(element.innerHTML) * parseInt(quantitysDOM[i].value);
		quantityXpriceDOM[i].innerHTML = uPriceXquantity + " €";
		subTotalCart += uPriceXquantity;

		element.innerHTML += " €";

		i++;
	});

	if (subTotalCart > 0) {
		subTotalDOM.innerHTML = subTotalCart + " €";
	} else {
		subTotalDOM.innerHTML = "0.00 €";
	}
	updateTotal(subTotalCart);

}


