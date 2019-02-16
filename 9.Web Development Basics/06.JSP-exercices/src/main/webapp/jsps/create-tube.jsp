<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 2/16/2019
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>MeTube</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <form action="/tubes/create" method="post">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Create Tube!</h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <label for="titleInput">Title</label>
                        </div>
                        <div class="row justify-content-center">
                            <input type="text" id="titleInput" name="title">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <label for="descriptionInput">Description</label>
                        </div>
                        <div class="row justify-content-center">
                            <textarea id="descriptionInput" name="description"
                                      placeholder="Please specify..."></textarea>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <label for="youtubeLinkInput">YouTube Link</label>
                        </div>
                        <div class="row justify-content-center">
                            <input type="text" id="youtubeLinkInput" name="youtubeLink">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <label for="uploaderInput">Uploader</label>
                        </div>
                        <div class="row justify-content-center">
                            <input id="uploaderInput" name="uploader">
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <button type="submit" class="btn btn-primary">Create Tube</button>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row justify-content-center">
                            <a href="/">Back to Home</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
