/**
 * 
 */
console.info("cartPrice.js");

let uPricesDOM = document.querySelectorAll(".priceU");
let quantitysDOM = document.querySelectorAll(".qty-input");
let quantityXpriceDOM = document.querySelectorAll(".quantityXprice");
let subTotalDOM = document.getElementById("cartSubTotal");
let TotalDOM = document.getElementById("cartTotal");

quantitysDOM.forEach(function(quantity){
    quantity.addEventListener('change', function(event){
        update();
    })
})



update();

function update(){
	let uPriceXquantity;
	let subTotalCart = 0;
	let i = 0;
	uPricesDOM.forEach((element) => {
		console.info(quantitysDOM[i].value);
		console.warn(parseFloat(element.innerHTML));
		uPriceXquantity = parseFloat(element.innerHTML)*parseInt(quantitysDOM[i].value);
		quantityXpriceDOM[i].innerHTML = uPriceXquantity + " €";
		subTotalCart += uPriceXquantity;
		
		element.innerHTML += " €";
		
		i++;
	});
	
	subTotalDOM.innerHTML = subTotalCart + " €";
	
	/*prices.forEach((element) => {
		price = parseFloat(element.innerHTML.replace(",", "."));
		subTotalCart += price;
	});
	subTotalDOM.innerHTML = subTotalCart + " €";*/
}


