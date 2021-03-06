<%@ page contentType="text/html;charset=utf-8" %>

<%@ page import="model.*" %>
<%@ page import="model.ItemsBeans" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="dao.*" %>
<%@ page import="exception.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<% List product_name = new ArrayList();%>
<% List product_price = new ArrayList();%>

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
    <% String authentication = (String) session.getAttribute("authentication");%>
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

<div class="container">

<div class="page-header">
  <div class="div-margin-top" >
    <form action="getGroup" method="post">
      <select class="form-control" name="GroupItems" >
        <option value="1"  >Чохли бронежилетів</option>
        <option value="2">РПС</option>
        <option value="3" selected>Підсумки</option>
        <option value="4">Платформи</option>
        <option value="5">Аптечки</option>
      </select>
      <p></p>
      <input type="hidden" name="action" value="getGroup" >
      <input type="submit" value="OK" align="center">
    </form>
  </div>
</div>


  <% Integer group_ID =(Integer) session.getAttribute("groupID");
    List<Item> u;
    ItemDao items = new ItemDaoJdbc();
    ItemsBeans itemsBeans = new ItemsBeans();
    try {
      if (group_ID != null) {
          u = items.selectGroupItems(group_ID);
        itemsBeans.setItems(u);
      } else {
        u = items.selectGroupItems(3);
        itemsBeans.setItems(u);
      }
      for (Item j : u){
        product_name.add(j.getName());
        product_price.add(j.getPrice());
      }
    } catch (DBSystemException e) {
      throw new ServletException(e);
    }
  %>

  <table border = "1" width="60%">
    <tr>
    <% for(Item i: itemsBeans.getItems()){ %>
      <form action="addtocart" method="post">
        <div class="card signin-card clearfix">
          <div id="cc_iframe_parent"></div>
          <p></p>
          <div class="thumbnail">
            <div class="row">
              <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/500*500/auto" src="pictures/items/<%=i.getImageOne()%>.jpg"
                     class="img-responsive">
              </div>
              <% if (i.getImageTwo() != null) {%>
              <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/500x500/auto" src="pictures/items/<%=i.getImageTwo()%>.jpg" class="img-responsive">
              </div>
              <%}%>
              <div class="caption">
                <h3><span style="color: #6E7645"><%=i.getName()%></span></h3>
                <p><label>Опис: </label> <span style="font-size: small"><%=i.getDescription()%> </span></p>
                <p><label>Ціна: </label> <%=i.getPrice()%>  грв</p>
                <p><label>Кількість: </label> <span id="quantity"> <input type="text" name="quantity" value="" size="10"> </span></p>
                <input type="hidden" name="action" value="addtocart">
                <input type="submit" value="Замовити" align="center">
                <%
                  String info = (String) session.getAttribute("info_product");
                  session.removeAttribute("info_product");
                  if (info != null){
                %>
                <div class="alert alert-warning">
                  <%=info%>
                </div>
                <%
                }
                %>
              </div>
            </div>
          </div>
        </div>
        <%  session.setAttribute("product_name", product_name);
            session.setAttribute("product_price", product_price);
        }%>
    </form>
  </table>

  <footer>
    <p>&copy; 2015, TTT. </p>
  </footer>

</div>

