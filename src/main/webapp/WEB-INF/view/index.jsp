<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Truck Service</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<!-- navbar -->

<nav class="navbar navbar-expand-lg fixed-top ">
    <a class="navbar-brand" href="#">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-4">

            <li class="nav-item">
                <a class="nav-link" data-value="about" href="#">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " data-value="portfolio" href="#">Portfolio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " data-value="blog" href="#">Blog</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " data-value="team" href="#">
                    Team</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " data-value="contact" href="#">Contact</a>
            </li>
        </ul>

    </div>
</nav>
<!-- header -->
<header class="header ">
    <div class="overlay"></div>
    <div class="container">
        <div class="description ">
            <h1>
                Hello ,Welcome To My official Website
                <p>
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                <button class="btn btn-outline-secondary btn-lg">See more</button>
            </h1>
        </div>
    </div>

</header>

<!-- about section -->
<div class="about" id="about">
    <div class="container">
        <h1 class="text-center">About Me</h1>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/team-3.jpg" class="img-fluid">
                <span class="text-justify">S.Web Developer</span>
            </div>
            <div class="col-lg-8 col-md-8 col-sm-12 desc">

                <h3>D.John</h3>
                <p>
                    ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
        </div>
    </div>
</div>

<!-- portfolio -->
<div class="portfolio" id="portfolio">
    <h1 class="text-center">Portfolio</h1>
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port13.png" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port1.png" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port6.png" class="img-fluid">
            </div>

            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port3.png" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port11.png" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/electric.png" class="img-fluid">
            </div>

            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/Classic.jpg" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port1.png" class="img-fluid">
            </div>
            <div class="col-lg-4 col-md-4 col-sm-12">
                <img src="images/portfolio/port8.png" class="img-fluid">
            </div>
        </div>
    </div>
</div>


<!-- Posts section -->
<div class="blog" id="blog">
    <div class="container">
        <h1 class="text-center">Blog</h1>
        <div class="row">
            <div class="col-md-4 col-lg-4 col-sm-12">
                <div class="card">
                    <div class="card-img">
                        <img src="images/posts/polit.jpg" class="img-fluid">
                    </div>

                    <div class="card-body">
                        <h4 class="card-title">Post Title</h4>
                        <p class="card-text">

                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="" class="card-link">Read more</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-lg-4 col-sm-12">
                <div class="card">
                    <div class="card-img">
                        <img src="images/posts/images.jpg" class="img-fluid">
                    </div>

                    <div class="card-body">
                        <h4 class="card-title">Post Title</h4>
                        <p class="card-text">

                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="" class="card-link">Read more</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-lg-4 col-sm-12">
                <div class="card">
                    <div class="card-img">
                        <img src="images/posts/imag2.jpg" class="img-fluid">
                    </div>

                    <div class="card-body">
                        <h4 class="card-title">Post Title</h4>
                        <p class="card-text">

                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="" class="card-link">Read more</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Team section -->
<div class="team" id="team">
    <div class="container">
        <h1 class="text-center">Our Team</h1>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-12 item">
                <img src="images/team-2.jpg" class="img-fluid" alt="team">
                <div class="des">
                    Sara
                </div>
                <span class="text-muted">Manager</span>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 item">
                <img src="images/team-3.jpg" class="img-fluid" alt="team">
                <div class="des">
                    Chris
                </div>
                <span class="text-muted">S.enginner</span>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 item">
                <img src="images/team-2.jpg" class="img-fluid" alt="team">
                <div class="des">
                    Layla
                </div>
                <span class="text-muted">Front End Developer</span>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 item">
                <img src="images/team-3.jpg" class="img-fluid" alt="team">
                <div class="des">
                    J.Jirard
                </div>
                <span class="text-muted">Team Manger</span>
            </div>
        </div>
    </div>
</div>

<!-- Contact form -->
<div class="contact-form" id="contact">
    <div class="container">
        <form>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-12">
                    <h1>Get in Touch</h1>
                </div>
                <div class="col-lg-8 col-md-8 col-sm-12 right">
                    <div class="form-group">
                        <input type="text" class="form-control form-control-lg" placeholder="Your Name" name="">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control form-control-lg" placeholder="YourEmail@email.com" name="email">
                    </div>
                    <div class="form-group">
				   	 <textarea class="form-control form-control-lg">

				   	 </textarea>
                    </div>
                    <input type="submit" class="btn btn-secondary btn-block" value="Send" name="">
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
