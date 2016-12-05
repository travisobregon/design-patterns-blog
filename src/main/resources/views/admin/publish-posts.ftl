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
        <link href="../css/app.css" rel="stylesheet">
        <link href="../css/posts.css" rel="stylesheet">
    </head>

    <body>
        <#include "../layouts/nav.ftl">

        <div class="container">
            <div class="row mb-2">
                <div class="col-md-6 offset-md-3">
                    <div class="col-md-12">
                        <h2 class="text-center">Publish Posts</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-10 offset-md-1">
                    <#list posts as post>
                        <div class="blog-post">
                            <form class="pull-right" action="/posts/${post.id}" method="POST">
                                <button type="submit" class="btn btn-sm btn-primary">Publish</button>
                            </form>

                            <h2 class="blog-post-title">${post.title}</h2>

                            <p class="blog-post-meta">by ${post.author.name}</p>

                            <p>${post.body}</p>
                        </div>
                    </#list>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    </body>
</html>