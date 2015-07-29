<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="Model.Item" %>
<%@ page import="Model.ItemsBeans" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Dao.*" %>
<%@ page import="Exception.*" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList itemname = new ArrayList();%>
<% ArrayList quantityno = new ArrayList();%>
<% ArrayList itemprice = new ArrayList();%>
<% ArrayList itemtotal = new ArrayList();%>
<% Integer totalPrice = 0;%>
<% Integer totalQuantity = 0;%>

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
  <link href="css/signin.css" rel="stylesheet">

  <style>

    .div-margin-top {
      margin-top: 80px;
      margin-left: 380px;
      margin-right: 350px;
    }
  </style>

</head>
<body>

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

<% itemname = (ArrayList) session.getAttribute("product_in_cart");
  quantityno = (ArrayList) session.getAttribute("quantity_in_cart");
  itemprice = (ArrayList) session.getAttribute("prices_in_cart");
  itemtotal = (ArrayList) session.getAttribute("total_prices_in_cart");
%>

if (itemname != null) {%>
<div class="page-header">
  <div class="div-margin-top" >

<table class="table" border = "1" width="100%">
  <tr>
    <th>#</th>
    <th>Наименование</th>
    <th>Цена</th>
    <th>Количество</th>
    <th>Итого</th>
  </tr>
  <%  if (itemname == null) {%>
  <p><span style="color: red"> There are no items in your cart now.. </span></p>
  <%} else {%>
      <% for (int i = 1; i <= itemname.size(); i++) {%>
  <tr>
    <td> <%=i%> </td>
    <td>  <%= itemname.get(i - 1)%>   </td>
    <td>  <%= itemprice.get(i - 1)%>   </td>
    <td> <%= quantityno.get(i - 1)%> </td>
    <td> <%= itemtotal.get(i - 1)%> </td>
    <% totalPrice = totalPrice + (Integer)itemtotal.get(i-1);%>
    <% totalQuantity= totalQuantity + Integer.parseInt((String)quantityno.get(i-1));%>
  </tr>
  <%}%>
    <tr>
      <td>  </td>
  <td>  </td>
      <td>   </td>
  <td> <%= totalQuantity %> </td>

  <td> <%= totalPrice%> </td>
  </tr>
  <%}%>
</table>

  </tr>

</table>
    <footer>
      <p>&copy; 2015, TTT. </p>
    </footer>
</div>

</div>






</body>
</html>