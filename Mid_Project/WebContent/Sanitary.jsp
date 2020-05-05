<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sanitary</title>
		<%@ include file="commonFiles/css/stylesheet.css" %>
		<style type="text/css">
.main_window {
background-image: url("commonFiles/images/Ogawa-Neko-5.png");
}
</style>
</head>
<body onload="getSanitary();">
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
		<h2>Sanitary</h2>
		<div id="Product_window">
			<table border='1' id="listProduct"></table>
		</div>
		<div>
			<button id="prevPage" onclick="prev()" disabled="disabled">上一頁</button>
			<button id="nextPage" onclick="next()" disabled="disabled">下一頁</button>
		</div>
		<div>
			Page: <span id="pagenum"></span> / <span id="total"></span>
			<p id="entries"></p>
		</div>
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
	function getSanitary() {
		$.ajax({
			url: "ListProduct",
			data:{catID: 5},
			success: function(data){
				product = (data);
				totalPageNum = Math.ceil(product.length/2);
				displayPage();			
				$("#total").html(totalPageNum);
				$("#entries").html("Total entrie: " + product.length);
				if(totalPageNum>1){
					$("#nextPage").removeAttr("disabled", "disabled");
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
		var txt = "<tr><th>Image<th>Name<th>Description<th>Price</tr>";
		for (let i=(page-1)*2; i<(page*2)&&i<product.length ;i++) {
			txt += "<tr>";
			txt += "<td>" + "<img src='data:image/jpg;base64," + product[i].pImg + "' style='height: 200px; width: 200px;'/></td>";
			txt += "<td style='width: 150px; font-weight:bold; font-size: 18px;'>" + product[i].pName + "</td>";
			txt += "<td style='width: 200px;'>" + product[i].pDescription + "</td>";
			txt += "<td style='width: 50px;'>" + product[i].pPrice + "</td>";
			txt += "<td><input type='button' id='" + product[i].pid + "' onclick='addCart(this.id);' value='加入購物車'></td>";
			txt += "</tr>";
		}
		$("#listProduct").html(txt);
		$("#pagenum").html(page);
	}

	function addCart(clicked_id) {
		$.ajax({
			url: "AddCart",
			data: { pid: clicked_id },
			success: function(data) {
				window.alert("商品已加到購物車")
			}
		});
	}

	</script>
</body>
</html>