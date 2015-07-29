<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="pictures/icon.ico">

    <title>TTT</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

</head>


<style>
    body {
        background: url('pictures/backGround.jpg') repeat fixed left top;
    }
</style>
<body >


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar" datasrc="pictures/icon.jpg"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <ul class="nav navbar-nav">

                <li class="active"><a href="index.jsp">Головна</a></li>
                <li><a href="items.jsp">Продукція</a></li>

            </ul>
        </div>



        <% String authentication = (String) session.getAttribute("authentication");

        %>
        <div id="login_in">
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right">
                    <p class="buttons-set">
                        <img src="pictures/data.png">
                        <% if (authentication == null) {%>
                        <a class="btn btn-link" href="authorization.jsp" >Авторизація</a>
                        <% } else {%>
                        <% String userlogin = (String) session.getAttribute("userlogin"); %>
                            <a class="btn btn-link" href="authorization.jsp"><%=userlogin %></a>
                        <%}%>
                        <img src="pictures/cart.png">
                        <% if (session.getAttribute("total_cart_items") != null) {%>
                        <a class="btn btn-link" href="cart.jsp"><span><span>Корзина:  <%= session.getAttribute("total_cart_items")%></span></span></a></p>
                    <% } else {%>
                    <a class="btn btn-link" href="cart.jsp"><span><span>Корзина:  0  </span></span></a></p>

                    <% }%>

                </form>
            </div><!--/.nav-collapse -->
        </div>

    </div>

</nav>

<div class="page-header">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="pictures/slide_1.jpg" hspace="400" vspace="0" alt="First slide">
            </div>
            <div class="item">
                <img src="pictures/slide_4.jpg" hspace="400" alt="Second slide">
            </div>
            <div class="item">
                <img src="pictures/slide_5.jpg" hspace="400" alt="Third slide">
            </div>
        </div>
    </div>

    <jsp:include page="news.jsp" flush="true"/>

</div>


</body>
</html>
