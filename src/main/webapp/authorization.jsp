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
  <link href="css/signin.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

</head>

<body >

<!-- Fixed navbar -->

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
          String info = (String) session.getAttribute("info");
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

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="page-header">
  <div class="container theme-showcase" role="main" >
    <div class="card signin-card clearfix">
      <div id="cc_iframe_parent"></div>

      <h2 class="form-signin-heading">Авторизація</h2>
      <div class="row">
          <%    if (authentication != null) { if(authentication.equals("Error"))
          {
          %>
          <div id="wrong_user"> <span style="color:red"> Username/Password is wrong! </span> </div>
          <% } }%>
          <%    if (info != null) {
          {
          %>
          <div id="wrong_user"> <span style="color:red"> <%=info %> </span> </div>
          <% } }%>

        <div            class     = "col-sm-3 col-md-6 col-lg-4">
          <form    action = "authorization"  method="post"   role      = "form">

            <div        class     = "form-group">
              <label    for       = "login">Логін:</label>
              <input    type      = "text"
                        placeholder = "Логін"
                        class     = "form-control"
                        id        = "login"
                        name      = "login"
                        required  = "true">
            </div>

            <div        class     = "form-group">
              <label    for       = "password">Пароль</label>
              <input    type      = "text"
                        placeholder = "Пароль"
                        class     = "form-control"
                        id        = "password"
                        name      = "password"
                        required  = "true">
            </div>

            <form >
              <input type="hidden" name="action" value="authorization">
              <input type="submit" value="Вхід" />
            </form>
          </form>
        </div>
      </div>

      <h2 class="form-signin-heading">Реєстрація</h2>
      <div class="row">

        <div            class     = "col-sm-3 col-md-6 col-lg-4">
          <form    action = "registration"  method="post"   role      = "form">
            <div        class     = "form-group">
              <label   >Призвіще:</label>
              <input    type      = "text"
                        placeholder = "Призвіще"
                        class     = "form-control"
                        required  = "true"
                        name      = "lastname"
                      >
            </div>

            <div        class     = "form-group">
              <label   >Ім'я</label>
              <input    type      = "text"
                        placeholder = "Ім'я"
                        class     = "form-control"
                        required  = "true"
                        name      = "firstname"
                      >
            </div>

            <div        class     = "form-group">
              <label    >Телефон</label>
              <input    type      = "text"
                        placeholder = "(ХХХ) ХХ-ХХХ-ХХ"
                        class     = "form-control"
                        required  = "true"
                        name      = "number"
                        title     = "(ХХХ) ХХ-ХХХ-ХХ">

            </div>

            <div        class     = "form-group">
              <label    >Логін:</label>
              <input    type      = "text"
                        placeholder = "Логін"
                        class     = "form-control"
                        required  = "true"
                        name      = "login"
                      >
            </div>

            <div        class     = "form-group">
              <label   >Пароль</label>
              <input    type      = "text"
                        placeholder = "Пароль"
                        class     = "form-control"
                        required  = "true"
                        name      = "password"
                      >
            </div>

            <form >
              <input type="hidden" name="action" value="registration">
              <input type="submit" value="Реєстрація" />
            </form>

          </form>
        </div>

      </div>

    </div>

    <p></p>

    <footer>
      <p>&copy; 2015, TTT. </p>
    </footer>
  </div>
</div>

</body>
</html>
