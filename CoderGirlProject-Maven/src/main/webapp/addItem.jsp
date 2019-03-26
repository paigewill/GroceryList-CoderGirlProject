<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<body>
    <center>
        <h1>Manage Grocery Items</h1>
    </center>
    <div align="center">
        <c:if test="${grocery != null}">
            <form action="update" method="POST">
        </c:if>
        <c:if test="${grocery == null}">
            <form action="insert" method="POST">
        </c:if>
            <caption>
                <h2>
                    <c:if test="${grocery != null">
                        Edit Item
                    </c:if>
                    <c:if test="${grocery == null}">
                        Add New Grocery Item
                    </c:if>
                </h2>
            </caption>
                <c:if test="${grocery != null">
                    <input type="hidden" name="id" value="<c:out value='${grocery.id}' />" />
                </c:if>
        Food item: <input type="text" name="item" />
        <br/><br/>
        Amount: <input type="text" name="amount" />
        <br/><br/>
        Do you have a coupon?
        <br/><br/>
        <input type="radio" name="hasCoupon" value="false" checked> no
        <input type="radio" name="hasCoupon" value="true"> yes
        <br/><br/>
        Is this item on sale?
        <br/><br/>
        <input type="radio" name="onSale" value="false" checked> unknown
        <input type="radio" name="onSale" value="false"> no
        <input type="radio" name="onSale" value="true"> yes
        <br/><br/>
        Isle: <select name="isle">
            <option value="bakery">Bakery</option>
            <option value="deli">Deli</option>
            <option value="seafood">Seafood</option>
            <option value="produce">Produce</option>
            <option value="one">Isle 1 []</option>
            <option value="two">Isle 2 []</option>
            <option value="three">Isle 3 []</option>
            <option value="four">Isle 4 []</option>
            <option value="five">Isle 5 []</option>
            <option value="six">Isle 6 []</option>
            <option value="seven">Isle 7 []</option>
            <option value="eight">Isle 8 []</option>
        </select>
        <input type="submit" value="Add to list" />
    </form>

            <h2>
                <a href="/new">Add New Item</a>
                &nbsp;&nbsp;&nbsp;
                <a href="/list">List All Items</a>

            </h2>
</body>
</html>