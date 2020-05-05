<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<%@ include file="commonFiles/css/stylesheet.css"%>
<style type="text/css">
.main_window {
background-image: url("commonFiles/images/Ogawa-Neko-2.png");
}
</style>
<body onload="showCart();">
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
		<h2>Cart</h2>
		<div id="Cart_window">
			<table border='1' id="listCart"></table>
		</div>
		<div id="sum" style="width: 550px; text-align: right; font-size: 20px;">
		</div>
		<div>
			<button id="prevPage" onclick="prev()" disabled="disabled">上一頁</button>
			<button id="nextPage" onclick="next()" disabled="disabled">下一頁</button>
		</div>
		<div>
			Page: <span id="pagenum"></span> / <span id="total"></span>
			<p id="entries"></p>
		</div>
		<div><input type="button" id="checkOutBtn" value="Check Out" onclick="checkOut();" disabled="disabled" style="visibility: hidden;"> </div>
	</div>



	<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		var page = 1;
		var totalPageNum = 1;
		var product = NaN;
		
		function showCart() {
			$.ajax({
				url: "ShowCart",
				success: function(data){
					var total = 0;
					product = (data);
					totalPageNum = Math.ceil(product.length/2);
					if(totalPageNum!=0){
						displayPage();			
						$("#total").html(totalPageNum);
						$("#entries").html("Total entrie: " + product.length);
						for (let i=0; i<product.length; i++){
							total += product[i].pPrice*product[i].quantity
						}
						$("#sum").html("價格總計: " + total);	
						$("#checkOutBtn").removeAttr("disabled", "disabled").css("visibility", "visible");
						if(totalPageNum>1){
							$("#nextPage").removeAttr("disabled", "disabled");
							
						}
					}else {
						$("#total").html(1);
						$("#entries").html("Total entrie: " + product.length);
						$("#Cart_window").html("購物車內沒有商品").css("font-size", "22px");
						$("#pagenum").html(1);
					}
				}
			})
		}

		function next() {
			$("#prevPage").removeAttr("disabled", "disabled");
			if (page < totalPageNum)
			{
				page++
				displayPage();
				if(page==totalPageNum){
					$("#nextPage").attr("disabled", "disabled");
				}
			}
		}

		function prev() {
			$("#nextPage").removeAttr("disabled", "disabled");
			if (page > 1) {
				page--;
				displayPage();
				if(page==1){
					$("#prevPage").attr("disabled", "disabled");
				}
			}
		}

		function displayPage() {
			var txt = "<tr><th>Image<th>Name<th>Price<th>Quantity<th>Total</tr>";
			for (let i=(page-1)*2; i<(page*2)&&i<product.length ;i++) {
				txt += "<tr>";
				txt += "<td>" + "<img src='data:image/jpg;base64," + product[i].pImg + "' style='height: 150px; width: 150px;'/></td>";
				txt += "<td style='width:100px;'>" + product[i].pName + "</td>";
				txt += "<td style='width:100px;'>" + product[i].pPrice + "</td>";
				txt += "<td style='width:100px;'><input id='" + product[i].pid + "' type='number' value='" + product[i].quantity + "' min='1' max='10' onchange='changeItem(this.id, this.value);' style='width: 50px'/><br>";
				txt += "<input type='button' id='" + product[i].pid + "' onclick='deleteItem(this.id);' value='刪除'></td>";
				txt += "<td style='width:100px;'>" + product[i].pPrice*product[i].quantity + "</td>";
				txt += "</tr>";
			}
			$("#listCart").html(txt);
			$("#pagenum").html(page);
		}

		function changeItem(clicked_id, clicked_val) {
			console.log(clicked_val);
			$.ajax({
				url: "ModCart",
				data: { pid: clicked_id, quantity: clicked_val },
				success: function(data) {
					console.log("mod");
					showCart();
				}
			});
		}
		
		function deleteItem(clicked_id) {
			$.ajax({
				url: "DeleteItem",
				data: { pid: clicked_id },
				success: function(data) {
					console.log("remove");
					showCart();
				}
			});
		}
		
		function checkOut() {
			window.location.href= "checkQuickCart";
		}
		
	
		</script>
</body>
</html>