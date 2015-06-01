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

  <title>Theme Template for Bootstrap</title>

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
                        required  = "true"
                        title     = "xxx@xxx.xx">
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

                        name      = "lastname"
                      >
            </div>

            <div        class     = "form-group">
              <label   >Ім'я</label>
              <input    type      = "text"
                        placeholder = "Ім'я"
                        class     = "form-control"

                        name      = "firstname"
                      >
            </div>

            <div        class     = "form-group">
              <label    >Телефон</label>
              <input    type      = "text"
                        placeholder = "(ХХХ) ХХ-ХХХ-ХХ"
                        class     = "form-control"

                        name      = "number"


                      >
            </div>

            <div        class     = "form-group">
              <label    >Логін:</label>
              <input    type      = "text"
                        placeholder = "Логін"
                        class     = "form-control"

                        name      = "login"
                      >
            </div>

            <div        class     = "form-group">
              <label   >Пароль</label>
              <input    type      = "text"
                        placeholder = "Пароль"
                        class     = "form-control"

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

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
