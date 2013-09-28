<%-- 
    Document   : index
    Created on : Sep 25, 2013, 7:47:33 PM
    Author     : Ganga Chathuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href="css/index.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript">
 
            function fetchResult(subject) {
                var xmlhttp = new XMLHttpRequest();
                $.get('fetchResult?subject='+subject, function(data) {
                    document.getElementById("data").innerHTML = data;
                });
            }

            $(document).ready(function () {
                $('.selectWrap select').on('change', function (e) {
                    var wrap = $(e.target).parents('.selectWrap');
                    document.getElementById("seleectD").innerHTML = "&nbsp;" + this.options[this.selectedIndex].innerHTML + "<span class='dwn' >▼</span>";
                });
            });
            fetchResult('numeracy');
      
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>School's Results</title>
    </head>
    <body>
        <h1>School's Results</h1>

        <label> Subject </label><br/>
        <div class="selectWrap">
            <form action="">
                <select name="Subject" onchange="fetchResult(this.value)">
                    <option value="numeracy"> Numeracy </option>
                    <option value="spelling "> Spelling </option>
                    <option value="reading"> Reading </option>
                </select>
            </form>
            <div id ="seleectD"> &nbsp; Numeracy <span class="dwn" >▼</span></div>

        </div>
        <br/><br/><br/><br/>
        <div id="data" align="center" >
        </div>
    </body>
</html>
