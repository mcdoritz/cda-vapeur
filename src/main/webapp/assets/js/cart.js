const quantityXpriceDOM = document.querySelectorAll(".quantityXprice");
const subTotalDOM = document.getElementById("cartSubTotal");
const totalDOM = document.getElementById("cartTotal");



if(quantityXpriceDOM.length > 0){
	let subTotal = 0;
	quantityXpriceDOM.forEach(price => {
   
	subTotal += parseFloat(price.innerHTML);

	});
	
	subTotal = subTotal.toFixed(2);
	console.log(subTotal);
	subTotalDOM.innerHTML = subTotal + " €";
	totalDOM.innerHTML = subTotal + " €";
}
