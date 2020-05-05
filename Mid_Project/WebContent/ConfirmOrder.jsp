<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Meow~</title>
		<%@ include file="commonFiles/css/stylesheet.css" %>
	</head>
	<body onload="listCart();">
		<%@ include file="commonFiles/Function.jsp"%>
		<%@ include file="commonFiles/Header.jsp"%>
		<%@ include file="commonFiles/Menu.jsp"%>
		<div id="main_window" class="main_window">
			<div id="jsp_content">
				<h1>購物車明細</h1>
				<table border='1' id="listCart" style="width: 600px;"></table>
				<table border='1' id="listSum" style="width: 600px; text-align: right;" ></table>
				<br>
				<hr>
				<br>
				<form action="CreateOrder" method="post">
					Name:<input type="text" name="shipName" required="required"><br>
					Phone:<input type="text" name="shipPhone" required="required"><br>
					Address:<input type="text" name="shipAddress" required="required"><br>
					<input style="display: none;" type="text" name="cartType" value="${param.cartType}" readonly="readonly"><br>
					訂單備註:<textarea id="orderNote" name="orderNote" cols="15" rows="2"></textarea><br>
					<input type="button" value="DEMO" style="background-color: green; color: white;" onclick="demo();">
					<input type="submit" value="成立訂單">
					<input type="reset" value="清除重填">
				</form>
			</div>
		</div>
		
		<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		function demo() {
			$("[name$='shipName']").val("Orange 貓");
			$("[name$='shipPhone']").val("0987654321");
			$("[name$='shipAddress']").val("橘子市橘子路一段2號3樓");
			$("#orderNote").val("橘貓很會吃...\n橘 貓 真 的 很 會 吃 ");
		}
		
		function listCart() {
			$.ajax({
				url: "ShowCart",
				data: {cartType: '${param.cartType}'},
				success: function(data){
					console.log(data);
					product = data;
					displayPage();			
				}
			})
		}
		
		function displayPage() {
			var txt = "<tr style='font-weight: bold;'><th>品名<th>單價<th>數量<th>小計</tr>";
			var total = 0;
			for (let i=0; i<product.length ;i++) {
				txt += "<tr>";
				txt += "<td style='width:100px;'>" + product[i].pName + "</td>";
				txt += "<td style='width:100px;'>" + product[i].pPrice + "</td>";
				txt += "<td style='width:100px;'>" + product[i].quantity + "</td>";
				txt += "<td style='width:100px;'>" + product[i].pPrice*product[i].quantity + "</td>";
				txt += "</tr>";
				total += product[i].pPrice*product[i].quantity;
			}
			console.log(txt);
			$("#listCart").html(txt);
			$("#listSum").html("<td style='border: 0px; text-align: left'>總金額</td><<td style='border: 0px;'>" + total + "</td>");	
		}
		
	
		</script>
	</body>
</html>