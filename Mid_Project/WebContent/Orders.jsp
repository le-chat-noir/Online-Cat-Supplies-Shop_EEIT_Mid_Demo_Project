<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
<%@ include file="commonFiles/css/stylesheet.css"%>
<style type="text/css">
.main_window {
background-image: url("commonFiles/images/Ogawa-Neko-3.png");
}


.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 200px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: 0xp auto;
  padding: 20px;
  border: 1px solid #888;
  width: 850px;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>
</head>
<body onload="showOrder();">
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window" >
		<h2>Orders</h2>
		<div id="orderDetailWindow" style="width: 600px;overflow: auto;"></div>
		<div id="orderArea" style="max-height: 500px; overflow: auto;"><table border='1' id="listCart" style="background-color: rgba(255,255,255,0.4);"></table></div>	
	</div>
	
	<!-- The Modal -->
	<div id="myModal" class="modal">
  		<!-- Modal content -->
  		<div class="modal-content">
    		<div id="modelText"></div>
			<span class="close">&times;</span>
			<br>
		</div>
	</div>

	


	<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		var order = NaN;
		var orderDetail = NaN;
		function showOrder() {
			$.ajax({
				url: "ShowOrder",
				success: function(data){
					order = (data);
					displayPage();
				}
			})
		}
		
		function displayPage() {
				var txt = "<tr><th>訂單編號<th>收件人<th>連絡電話<th>郵寄地址<th>總價<th>付款狀態</tr>";
				for (let i=0; i<order.length ;i++) {
					txt += "<tr>";
					txt += "<td style='width: 100px;'>" + order[i].orderID + "</td>";
					txt += "<td style='width: 100px;'>" + order[i].shipName + "</td>";
					txt += "<td style='width: 150px;'>" + order[i].shipPhone + "</td>";
					txt += "<td style='width: 300px;'>" + order[i].shipAddress + "<br>";
					txt += "<td style='width: 100px;'>" + order[i].totalAmount + "</td>";
					txt += "<td style='width: 80px;'>" + order[i].payStatus + "</td>";
					console.log(order[i].payStatus!="OK");
					txt += "<td>";
					if(order[i].payStatus!="OK"){
						txt += "<input type='button' style='background-color: red; color: white;' value='刪除' onclick='deleteOrder(" + order[i].orderID + ");' /><br>";
						txt += "<input type='button' style='background-color: blue; color: white;' value='付款' onclick='payOrder(" + order[i].orderID + ");' /><br><br>";
					}
					txt += "<input type='button' value='檢視' onclick='viewOrder(" + order[i].orderID + "," + i + ");' />";
					
					txt += "</td>";				
					txt += "</tr>";
				}
				console.log(txt);
				$("#listCart").html(txt);
				
		}
	
		function viewOrder(clicked_id, i) {
			$.ajax({
				url: 'ShowOrderDetail',
				data: { orderID: clicked_id},
				type: "post",
				success: function (data) {
					orderDetail = (data);
					displayDetail(i);
				}
			})
		}
		
		
		function displayDetail(input_id) {
			var txt = "<table style='border-bottom: 1px solid black;text-align:center ; width:500px;'><tr><th>編號<th>名稱<th>單價<th>數量<th>小計</tr>";
			for (let i=0; i<orderDetail.length ;i++) {
				txt += "<tr>";
				txt += "<td style='width: 50px;'>" + (i+1) + "</td>";
				txt += "<td style='width: 200px;'>" + orderDetail[i].pName + "</td>";
				txt += "<td style='width: 100px;'>" + orderDetail[i].unitPrice + "</td>";
				txt += "<td style='width: 50pxpx;'>" + orderDetail[i].quantity + "</td>";
				txt += "<td style='width: 100px;'>" + orderDetail[i].subTotal + "</td>";
				txt += "</tr>";
			}
			txt += "</table>";
			txt += "<table style='width:500px; padding-right:35px'><tr><td style='text-align: left'>總金額</td><td style='text-align: right'>" + order[input_id].totalAmount + "</td></tr></table>";
			txt += "</table>";
			txt+= "<br><div style='margin-left:150px;'>備註:</div>";
			txt += "<table style='border:1 ;width: 500px; text-align:center;'><tr><td>"
			if(order[input_id].msg!=undefined){
				txt += order[input_id].msg;
			}else {
				txt += "(無)";
			}
			txt += "</td></tr></table><hr>";
			//txt += "<input type='button' value='關閉' onclick='closeDetail();'><br><br>";
			modal.style.display = "block";
			$("#modelText").html(txt);
			//$("#orderDetailWindow").html(txt);
			//$("#orderArea").css("max-height", "300px");
			
		}
		
		function closeDetail() {
			$("#orderDetailWindow").html("");
			$("#orderArea").css({"max-height": "500px", "overflow": "auto"});
		}
		
		
		
		function payOrder(clicked_id) {
			window.location.href = "LinkPay?orderID=" + clicked_id;
		}
		
		function deleteOrder(clicked_id) {
			$.ajax({
				url: 'deleteOrder',
				type: 'POST',
				data:{ orderID : clicked_id},
				success: function() {
					showOrder();
				}
			})
		}
		
		// Get the modal
		var modal = document.getElementById("myModal");

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
 		
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
		  	}
		}
	
	</script>
</body>
</html>