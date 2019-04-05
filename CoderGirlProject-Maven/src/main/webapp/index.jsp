<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.awt.List"%>
<%@ page import="java.groceryListJava.*"%>

<html>
    <link href='https://fonts.googleapis.com/css?family=Arima Madurai' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet'>
    <style>
        body {
            font-family: 'Arima Madurai', monospace;
            color: #004466;
            background-image: url=("/Users/paigewilliams/IdeaProjects/CoderGirlProject-Maven/src/main/webapp/mixedFruit.jpg");
            background-color: #e0ebeb;
            width: 100%;
            height: 100%;
        }
        h1 {
            font-family: 'Sofia', cursive;
            font-size: 38px;
            color: #4169e1;
            text-align: center;
        }
        h2 {
            color: #63639c;
        }
    </style>
    <head>
        <title>Grocery List</title>
    </head>
    <body>
        <center>
        <h1 id="appName" onclick="colorChange()">Welcome to Grocery List!</h1>
            <script>
                function colorChange() {
                    document.getElementById("appName").style.color = "#66a3ff";
                }
            </script>
        <h2>
            <a href="/new">Add Grocery Item</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Items</a>
        </h2>
        </center>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>Grocery List</h2></caption>
                <tr>
                    <th>Item</th>
                    <th>Amount</th>
                    <th>Isle</th>
                    <th>Coupon</th>
                    <th>Sale</th>
                </tr>
                <c:forEach var="grocery" items="${groceryList}" >
                    <tr>
                        <td><c:out value="${grocery.item}" /></td>
                        <td><c:out value="${grocery.amount}" /></td>
                        <td><c:out value="${grocery.isle}" /></td>
                        <td><c:out value="${grocery.hasCoupon}" /></td>
                        <td><c:out value="${grocery.onSale}" /></td>
                        <td>
                            <a href="/edit?id=<c:out value='${grocery.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/delete?id=<c:out value='${grocery.id}' />">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>