<%@ page import="chushka.domain.models.binding.TubeCreateBindingModel" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 2/16/2019
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<% TubeCreateBindingModel model = (TubeCreateBindingModel) request.getAttribute("viewModel"); %>
<div class="container">
    <div class="jumbotron">
        <div class="row">
            <div class="co col-md-12">
                <div class="row justify-content-center">
                    <h3>
                        <%= model.getName()%>
                    </h3>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12">
                <div class="row justify-content-center">
                    <%= model.getDescription()%>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-6">
                <div class="row justify-content-center">
                    <a href="<%=model.getYoutubeLink()%>" target="_blank">Link to Video</a>
                </div>
            </div>
            <div class="col col-md-6">
                <div class="row justify-content-center">
                    <%=model.getUploader()%>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12">
                <div class="row justify-content-center">
                    <a href="/">Back to Home</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
