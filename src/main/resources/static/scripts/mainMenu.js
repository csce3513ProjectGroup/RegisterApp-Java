console.log(1);
document.addEventListener("DOMContentLoaded", function(event) {
	getStartTransactionActionElement().addEventListener(
		"click",
		() => { displayError("Functionality has not yet been implemented."); });

	getViewProductsActionElement().addEventListener(
		"click",
		() => { window.location.assign("/productListing"); });

	getCreateEmployeeActionElement().addEventListener(
		"click",
		() => { window.location.assign("/employeeDetail"); });

	getProductSalesReportActionElement().addEventListener(
		"click",
		() => { displayError("Functionality has not yet been implemented."); });

	getCashierSalesReportActionElement().addEventListener(
		"click",
		() => { displayError("Functionality has not yet been implemented."); });

	// if ($isElevatedUser)
	// {
	// 	document.getElementById("buttons").style="visible"
	// }
});
// console.log(2);
// console.log(isElevatedUser);
// Getters and setters
function getViewProductsActionElement() {
	return document.getElementById("viewProductsButton");
}

function getCreateEmployeeActionElement() {
	return document.getElementById("createEmployeeButton");
}

function getStartTransactionActionElement() {
	return document.getElementById("startTransactionButton");
}

function getProductSalesReportActionElement() {
	return document.getElementById("productSalesReportButton");
}

function getCashierSalesReportActionElement() {
	return document.getElementById("cashierSalesReportButton");
}
// End getters and setters

function makeButtonsAppear()
{
	if ($isisElevatedUser)
	{
		document.getElementById("buttons").style="visible"
	}
}
