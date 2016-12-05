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
        <link href="css/posts.css" rel="stylesheet">
    </head>

    <body>
        <#include "../layouts/nav.ftl">

        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <#list posts as post>
                        <div class="blog-post">
                            <h2 class="blog-post-title">${post.title}</h2>
                            <p class="blog-post-meta">${post.publishedAt} by ${post.author.name}</p>

                            <p>${post.body}</p>
                        </div>
                    </#list>
                </div>

                <div class="col-sm-4">
                    <div class="col-sm-12 mb-1">
                        <#if user??>
                            <a href="/posts/create" class="btn btn-primary btn-block">
                                <strong>Add Post</strong>
                                <i class="fa fa-plus" aria-hidden="true"></i>
                            </a>
                        <#else>
                            <div class="alert alert-warning mb-0" role="alert">
                                <strong>Warning!</strong> Login to add a post.
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    </body>
</html>