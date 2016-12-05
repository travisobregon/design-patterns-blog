<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Blog</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" />
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/app.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
    </head>

    <body>
        <#include "layouts/nav.ftl">

        <div class="container">
            <div class="home">
                <h1>Welcome to the Developers' Blog!</h1>
                <p class="lead">Share your views and opinions about the industry.
                    <br>
                    What are you working on? What are you interested in learning?
                </p>
                <a href="/posts" class="btn btn-primary btn-lg">
                    <i class="fa fa-eye" aria-hidden="true"></i>
                    View Posts
                </a>
            </div>

            <div class="tutorials">
                <h1 class="text-center">Tutorials</h1>
                <p class="tutorials-meta">${numberOfTutorials} tutorials and counting...</p>
                <#list tutorials>
                    <ul class="list-group">
                        <#items as tutorial>
                            <li class="list-group-item">
                                <a href="${tutorial.url}">${tutorial.title}</a>
                                <#if tutorial.duration??>
                                    <span class="pull-right">
                                        <i class="fa fa-clock-o" aria-hidden="true"></i>
                                        ${tutorial.duration}
                                    </span>
                                </#if>
                            </li>
                        </#items>
                    </ul>
                </#list>
            </div>

            <div class="conferences">${conferences.displayConferences()}</div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    </body>
</html>
