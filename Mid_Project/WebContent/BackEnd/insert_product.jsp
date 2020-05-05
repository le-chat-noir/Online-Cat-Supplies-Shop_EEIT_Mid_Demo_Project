<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>簡易產品後台</title>
<%@ include file="../commonFiles/css/stylesheet.css"%>
<script src="../commonFiles/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
* {
	margin-left: 20px;
}
</style>
</head>
<body onload="listAllProducts();">

	簡易後台 (新增物品的話除了PID都必須填入)
	<form action="../CreateProduct" method="post" enctype="multipart/form-data">
		PID:<input type="text" id="pid" name="pid" style="background-color: lightgray">輸入PID修改現有項目，空白新增項目<br> 
		catID:<select id="catID" name="catID">
			<option value="1">(1) 罐頭</option>
			<option value="2">(2) 乾糧</option>
			<option value="3">(3) 點心</option>
			<option value="4">(4) 玩具</option>
			<option value="5">(5) 貓砂</option>
		</select><br>
		Name:<input type="text" id="pName" name="pName"><br> 
		Description:<input type="text" id="pDescription" name="pDescription"><br> 
		Price:<input type="text" id="pPrice" name="pPrice"><br> 
		Image:<input type="file" id="pImage" name="pImage">空白則不修改已有圖片 <br><br>
		<input type="submit" value="新增/修改"> <input type="reset" value="清除重填"><br><br>
		<hr>
	</form>

	<div id="listProducts" style="height: 500px;">
		<table border='1' id="demo" ></table>
	</div>
		<div>
			<button onclick="prev()">prev</button>
			<button onclick="next()">next</button>
		<br>
		Page: <span id="pagenum"></span> / <span id="total"></span>
		<p id="entries"></p>
	</div>

</body>

<script type="text/javascript">
var page = 1;
var totalPageNum = 1;
var product = NaN;
function listAllProducts() {
	$.ajax({
		url: "../ListProduct",
		data:{catID: 0},
		success: function(data){
			product = (data);
			totalPageNum = Math.ceil(product.length/2);
			displayPage();			
			document.getElementById("total").innerHTML = totalPageNum;
			document.getElementById("entries").innerHTML = "Total entrie: "+product.length;
			console.log(data);
		}
	})
}

function next() {
	if (page <= totalPageNum)
	{
		if(page==totalPageNum){
			page=0;
		}
		page++;
		displayPage();
	}
}

function prev() {
	if (page >= 1) {
		if(page==1){
			page=totalPageNum+1;
		}
		page--;
		displayPage();
	}
}

function displayPage() {
	var txt = "<tr style='height:40px'><th>PID<th>catID<th>Name<th>Description<th>Price<th>Image";
	for (let i=(page-1)*2; i<(page*2)&&i<product.length ;i++) {
		txt += "<tr style='height:200px;'><td>" + product[i].pid + "</td>";
		txt += "<td>" + product[i].catID + "</td>"; 
		txt += "<td>" + product[i].pName + "</td>"; 
		txt += "<td>" + product[i].pDescription; "</td>"; 
		txt += "<td>" + product[i].pPrice; "</td>"; 
		txt += "<td>" + "<img src='data:image/jpg;base64," + product[i].pImg + "' style='height: 200px; width: 200px;'/></td>";
		txt += "<td><input id='" + i + "' type='button' value='修改' onclick='editMe(this.id)'/><br><br>";
		txt += "<input id='PID" + product[i].pid + "' type='button' value='刪除' onclick='deleteMe(this.id)' style='background-color:red; color:white'/></td></tr>";
	}
	document.getElementById("demo").innerHTML = txt;
	document.getElementById("pagenum").innerHTML = page;
}

function deleteMe(clicked_id) {
	console.log(clicked_id)
	$.ajax({
		url: "DeleteProduct",
		data:{ pid: clicked_id},
		type: "post",
		success: function (data) {
			listAllProducts();
		}
	})	
}


function editMe(clicked_id) {
	$("#pid").val(product[clicked_id].pid);
	$("#catID").val(product[clicked_id].catID);
	$("#pName").val(product[clicked_id].pName);
	$("#pDescription").val(product[clicked_id].pDescription);
	$("#pPrice").val(product[clicked_id].pPrice);
}


</script>
</html>