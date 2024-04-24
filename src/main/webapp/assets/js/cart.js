

const priceUDOM = document.querySelectorAll(".priceU");
const quantityXpriceDOM = document.querySelectorAll(".quantityXprice");
const subTotalDOM = document.getElementById("cartSubTotal");
const totalDOM = document.getElementById("cartTotal");



if(quantityXpriceDOM.length > 0){
	let subTotal = 0;
	let i = 0;
	quantityXpriceDOM.forEach(price => {
		price.innerHTML = price.innerHTML.replace(",", ".");
		subTotal += parseFloat(price.innerHTML);
		priceUDOM[i].innerHTML = priceUDOM[i].innerHTML + " €";
		quantityXpriceDOM[i].innerHTML = quantityXpriceDOM[i].innerHTML + " €";
		i++;
	});
	
	
	subTotal = subTotal * 100;
	console.log(subTotal);
	subTotal = subTotal.toFixed(0);
	console.log(subTotal);
	subTotal = subTotal / 100;
	console.log(subTotal);
	subTotalDOM.innerHTML = subTotal + " €";
	totalDOM.innerHTML = subTotal + " €";
}
