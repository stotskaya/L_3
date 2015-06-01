<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="Model.Item" %>
<%@ page import="Model.ItemsBeans" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="Dao.*" %>
<%@ page import="Exception.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Demo Project</title>
  <meta   charset = "utf-8">
  <meta   name    = "viewport"
          content = "width=device-width, initial-scale=1">
  <link   rel     = "stylesheet"
          href    = "css/bootstrap.min.css">
  <script src     = "js/jquery-1.11.1.min.js"></script>
  <script src     = "js/bootstrap.min.js"></script>

  <style>
    body {
      background-image: url('pictures/backGround.jpg');
      background-repeat: repeat;
      background-attachment: fixed;
      background-position: left top;
    }
  </style>

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">

    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar" href="pictures/icon.jpg"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Головна</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Продукція <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Чохли бронежилетів</a></li>
            <li><a href="#">РПС</a></li>
            <li><a href="#">Платформи</a></li>
            <li><a href="#">Підсумки</a></li>
          </ul>
        </li>
        <li><a href="#contact">Новини</a></li>
        <li><a href="#contact">Контакти</a></li>
      </ul>
    </div>

    <div id="navbar" class="navbar-collapse collapse">
      <form class="navbar-form navbar-right">
        <a class="buttons-set">
          <img src="pictures/data.png">
          <a class="btn btn-link" href="authorization.jsp"  title="Register"><span><span>Авторизація</span></span></a></a>
      </form>
    </div>

  </div>
</nav>

<div class="page-header">
  <div class="container theme-showcase" role="main" >
    <div class="card signin-card clearfix">
      <div id="cc_iframe_parent"></div>
      <h2 class="form-signin-heading">Чохли бронежилетів</h2>
      <p></p>
      <div class="thumbnail">

        <div class="row">
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/500*500/auto" src="pictures/items/stan/1_big.jpg" alt="stan_1" class="img-responsive">
          </div>
          <div class="col-xs-6 col-sm-3 placeholder">
            <img data-src="holder.js/500x500/auto" src="pictures/items/stan/2_big.jpg" alt="stan_2" class="img-responsive">
          </div>
          <div class="caption">
            <h3>Бронежилет "Стан"</h3>
            <p></p> <label>Призначення:</label></p>
            <p></p><label>Особливості:</label></p>
            <p><label>Технічні характеристики:</label></p>
            <input type="submit" value="Замовити" />
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="container">
    <%
    ItemsBeans itemsBeans;
    if (request.getAttribute("modele") != null){
      itemsBeans = (ItemsBeans) request.getAttribute("modele");
    } else{
      ItemDao items = new ItemDaoJdbc();
      itemsBeans = new ItemsBeans();
      try {
        itemsBeans.setItems(items.selectAll());
      } catch (DBSystemException e) {
        e.printStackTrace();
      }
    }
  %>

  <form action="addItem" method="post">
    <table border="1" width="30%">
      <tr>
        <td>Naimenovanie</td>
        <td><input type="text" name="naimenovanie"></td>
      </tr>
      <tr>
        <td>Price</td>
        <td><input type="text" name="price"></td>
      </tr>
      <tr>
        <input type="hidden" name="action" value="addItem">
        <td colspan="2"><input type="submit" value="add"></td>
      </tr>
    </table>

  </form>

  <table border = "1" width="60%">
    <tr>
      <th>Наименование</th>
      <th>Цена</th>
      <th>Удалить</th>
    </tr>

    <%
      Iterator<Item> list = itemsBeans.getItems().iterator();
      while(list.hasNext()){
        Item i = list.next();
    %>
    <tr>
      <td><%=i.getName()%></td>
      <td><%=i.getPrice() %></td>
      <td>
        <form action="addItem" method="post">
          <input type="hidden" name="id" value="<%=i.getId()%>">
          <input type="hidden" name="action" value="delete">
          <input type="submit" value="delete" />
        </form>
      </td>
    </tr>

    <%
      }
    %>

  </table>
</div>



</body>
</html>