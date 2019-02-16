<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="chushka.domain.models.binding.TubeCreateBindingModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 2/16/2019
  Time: 18:43
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
<% List<TubeCreateBindingModel> tcbm = (List<TubeCreateBindingModel>) request.getAttribute("tcbms"); %>
<div class="container">
    <div class="jumbotron">
        <div class="row">
            <div class="col col-md-12">
                <div class="row justify-content-center">
                    <h1>All Tubes</h1>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12">
                <div class="row justify-content-center">
                    <h3>Check our tubes below</h3>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-12">
                <% if (tcbm.size() > 0) {%>
                <ul>
                    <% for (TubeCreateBindingModel model : tcbm) { %>
                    <div class="row justify-content-center">
                        <li>
                            <a href="/tubes/details?name=<%=model.getName().replace(" ", "%20")%>"><%=model.getName()%>
                            </a>
                        </li>
                    </div>
                    <% } %>
                </ul>
                <% } else {%>
                <div class="row justify-content-center">
                    <span>No tubes -</span><a href="/tubes/create">Create some!</a>
                </div>
                <% }%>
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
