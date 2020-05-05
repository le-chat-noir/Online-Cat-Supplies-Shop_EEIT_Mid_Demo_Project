<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>簡易意見處理後台</title>
	<%@ include file="../commonFiles/css/stylesheet.css"%>
	<script src="../commonFiles/js/jquery-3.4.1.min.js"></script>
	<style type="text/css">
		* { margin-left: 20px;}
	</style>
</head>
<body onload="listAllOpinions();">

	<div id="listOpinions">
		<table border='1' id="demo"></table>
		<div>
			<button onclick="prev()">Prev</button>
			<button onclick="next()">Next</button>
		</div>
		Page: <span id="pagenum"></span> / <span id="total"></span>
		<p id="entries"></p>
	</div>

	<script type="text/javascript">
	var page = 1;
	var totalPageNum = 1;
	var opinion = NaN;
	function listAllOpinions() {
		$.ajax({
			url: "../ListOpinion",
			
			success: function(data){
				opinion = (data);
				totalPageNum = Math.ceil(opinion.length/2);
				displayPage();			
				document.getElementById("total").innerHTML = totalPageNum;
				document.getElementById("entries").innerHTML = "Total entrie: "+opinion.length;
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
		var txt = "<tr><th>UID<th>Name<th>Email<th>Title<th>Text<th>Solution<th>Case Status";
		for (let i=(page-1)*2; i<(page*2)&&i<opinion.length ;i++) {
			txt += "<tr>";
			
			if(opinion[i].uid != undefined){
				txt += "<td>" + opinion[i].uid + "</td>";
			}else{
				txt += "<td></td>";
			}
			
			txt += "<td>" + opinion[i].userName + "</td>";
			
			if(opinion[i].userMail != undefined){
				txt += "<td>" + opinion[i].userMail + "</td>";
			}else{
				txt += "<td></td>";
			}
			if(opinion[i].opinionTitle != undefined){
				txt += "<td>" + opinion[i].opinionTitle + "</td>";
			}else{
				txt += "<td></td>";
			}
			txt += "<td>" + opinion[i].opinionText; "</td>";
			if(opinion[i].solution != undefined){
				txt += "<td>" + opinion[i].solution + "</td>";
			}else{
				txt += "<td></td>";
			}
			if(opinion[i].caseStatus != undefined){
				txt += "<td>" + opinion[i].caseStatus + "</td>";
			}else{
				txt += "<td></td>";
			}
			
			txt += "<td><input id='" + i + "' type='button' value='修改' onclick='editMe(this.id)'/>";
			txt += "<input id='PID" + opinion[i].pid + "' type='button' value='刪除' onclick='deleteMe(this.id)'/></td></tr>";
		}
		document.getElementById("demo").innerHTML = txt;
		document.getElementById("pagenum").innerHTML = page;
	}

	function deleteMe(clicked_id) {
		$.ajax({
			url: "Deleteopinion",
			data:{ pid: clicked_id},
			success: function (data) {
				listAllopinions();
			}
		})	
	}


	function editMe(clicked_id) {
		$("#pid").val(opinion[clicked_id].pid);
		$("#catID").val(opinion[clicked_id].catID);
		$("#pName").val(opinion[clicked_id].pName);
		$("#pDescription").val(opinion[clicked_id].pDescription);
		$("#pPrice").val(opinion[clicked_id].pPrice);
	}


	</script>

</body>
</html>