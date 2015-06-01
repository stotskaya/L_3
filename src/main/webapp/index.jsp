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

                <li class="active"><a href="#">Головна</a></li>
                <li class="dropdown">
                    <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Продукція <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="items.jsp">Чохли бронежилетів</a></li>
                        <li><a href="#">РПС</a></li>
                        <li><a href="#">Платформи</a></li>
                        <li><a href="#">Підсумки</a></li>
                    </ul>
                </li>
                <li><a href="#">Новини</a></li>
                <li><a href="#">Контакти</a></li>

            </ul>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <p class="buttons-set">
                    <img src="pictures/data.png">
                    <a class="btn btn-link" href="authorization.jsp"  title="Register"><span><span>Авторизація</span></span></a></p>

            </form>
        </div><!--/.nav-collapse -->
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


    <div class="container theme-showcase" role="main">
        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Запрошуємо! <p class="text-muted">Виставка "Волонтерський воєнпром" </p></h2>
                <p class="lead">В цю пятницю і суботу (з 10-00 по 18-00) ми приймаємо участь у другій виставці "Волонтерський воєнпром". З часів першої виставки ми встигли наростити потужності виробництва і підвищити якість продукції. Крім того, розширився наш асортимент. Те, що ми не публікували на сторінці ТТТ - ви зможете власноруч оцінити.
                    Завітайте - будемо раді!
                    <a class="btn btn-link" href="http://expo.voenprom.org.ua/visit.html" title="Register"><span><span>Детальніше</span></span></a></p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" src="pictures/Team_kiev.jpg" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 col-md-push-5">
                <h2 class="featurette-heading">Вдосконалення пудсумків.</h2>
                <p class="lead">Магазини на 45 патронів ми переносили лише у відкритому підсумку, відтепер і в закритому. Навіть у такому маленькому виробі, як підсумок для магазинів, ми продовжуємо робити вдосконалення: на цей раз відмова від стрічки в користь кордури.</p>
            </div>
            <div class="col-md-5 col-md-pull-7">
                <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" src="pictures/pidsumki.jpg" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading"> РПС "ТРИТОН". <p class="text-muted">Декілька слів про технології</p></h2>
                <p class="lead">Тактичний пояс виконує роль платформи для розміщення спорядження на поясі користувача. Для комфорного носіння поясу, ми використали дихаючу сітку зовні та м'який демпфер всередині поясу, а також жорстку вставку, яка підтримує форму поясу при великих навантаженнях.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto"src="pictures/rps.jpg" alt="Generic placeholder image">
            </div>
        </div>

        <div class="well">
            <img width="100%"  src="pictures/wells.jpg">
        </div>

        <footer>
            <p class="pull-right"><a href="#">Back to top</a></p>
            <p>&copy; 2015, TTT. </p>
        </footer>


    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</div>

</body>
</html>
