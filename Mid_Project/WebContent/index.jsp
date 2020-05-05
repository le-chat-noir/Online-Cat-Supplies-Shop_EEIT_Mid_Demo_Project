<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Meow~</title>
		<%@ include file="commonFiles/css/stylesheet.css" %>
		<style type="text/css">
		.wrapper{
			padding-top: 50px;         
            width:600px;
            height:550px;
            overflow: hidden;
        }
        ul{
            margin:0;
            padding: 0;
            position: relative;
        }
        li{
            margin:0;
            padding: 0;
            list-style: none;
        }
        ul.slides{
            width: 4200px;
            left: 0px;
            transition: all .5s;
        }
        ul.slides li{
            width:600px;
            height:480px;
            overflow: hidden;
            float: left;
        }
        ul.slides li img{
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .dot{
            position: relative;
            bottom:80px;
            width:100%;
            display: flex;
            justify-content: center;
            background-color: transparent;
        }
        
        .dot li{
            border: 0px;
            margin: 10px;/
            width:20px;
            height: 10px;
        }
        
        .dot i {
            padding: 5px;
            font-size: 40px;
            border-radius: 50%;
            background-color: rgba(65, 65, 65, 0.719);
        }

        
		</style>
	</head>
	<body>
		<%@ include file="commonFiles/Function.jsp"%>
		<%@ include file="commonFiles/Header.jsp"%>
		<%@ include file="commonFiles/Menu.jsp"%>
		<div id="main_window" class="main_window">
			
			<div class="wrapper">
				
        		<ul class="slides">
            		<li><img src="commonFiles/images/slide1.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide2.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide3.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide4.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide5.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide6.JPG" alt=""></li>
            		<li><img src="commonFiles/images/slide7.JPG" alt=""></li>
        		</ul>
        		<ul class="dot">
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
            		<li><i class="fa fa-paw"></i></li>
        		</ul>
        		
    		</div>
    	
		</div>
		
		<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		$(function(){
            let slideNum=0;
            let slideCount=$(".slides li").length;
            let lastIndex=slideCount-1;
            let updateSlide = setInterval(goNextSlide, 2000);

            function display() {
                $("ul.slides").css("left", slideNum*(-600));
                $(".dot li").eq(slideNum).css("color","white")
                    .siblings().css("color","rgba(255, 148, 228, 0.8)");
            }
            function goNextSlide() {
                slideNum = slideNum+1;
                if(slideNum>lastIndex) slideNum =0;
                display();
            }
            move_wrapper();
            $(window).resize(move_wrapper);

                function move_wrapper() {
                    windowHeight = $(window).height();
                    windowWidth = $(window).width();
                    wrapperHeight = $(".wrapper").height();
                    wrapperWidth = $(".wrapper").width();
                    $(".wrapper").css("top", ($(window).height()-$(".wrapper").height())/2 );
                    $(".wrapper").css("left", ($(window).width()-$(".wrapper").width())/2 );
            }   

            $(".dot li").eq(0).css("color","white").siblings().css("color","rgba(255, 148, 228, 0.8)");
            
            $(".dot i").mouseover(function () {
                slideNum=$(this).parent().index();
                display();             
            })
            
            $(".wrapper").hover(function() {
                clearInterval(updateSlide);
                }, function(){
                updateSlide = setInterval(goNextSlide, 2000);
            });

        });
		
		
	
		</script>
	</body>
</html>