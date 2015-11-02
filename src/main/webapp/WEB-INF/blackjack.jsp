<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <title>BlackJack</title>

  <link rel="stylesheet" href="/resources/css/bootstrap.v3.3.5.css">
  <link rel="stylesheet" href="/resources/css/styles.css">

  <script src="/resources/scripts/jquery.v2.1.4.js"></script>
  <script src="/resources/scripts/scripts.js"></script>

</head>

<body>

  <div class="table">

    <div class="dealer">

      <figure class="icon dealer-icon">
        <img src="/resources/images/dealer.jpg" alt="dealer">
        <figcaption>
          <h4>Dealer</h4>
          <p id="bet-value">Bet: 0 $</p>
          <p id="dealer-status">Status:</p>
          <p id="dealer-score">Total: 0</p>
        </figcaption>
      </figure>

      <div class="dealer-cards card-container">
        <div class="center-wrapper"></div>
      </div>

    </div>

    <div class="player">

      <div class="player-cards card-container">
        <div class="center-wrapper"></div>
      </div>

      <figure class="icon player-icon">
        <img src="/resources/images/bender.jpg" alt="player">
        <figcaption id="player-data">
          <h4>${sessionScope.player.name}</h4>
          <p id="wallet">Money: ${sessionScope.player.money} $</p>
          <p id="player-status">Status:</p>
          <p id="player-score">Total: 0</p>
        </figcaption>
      </figure>

    </div>

  </div>

  <div id="controllers" class="controllers">

    <div class="controller bet-controller input-group">
      <c:choose>
        <c:when test="${sessionScope.player.money < 100}">
          <input id="bet-val" class="bet form-control" type="number" value="10" min="1" max="${sessionScope.playerEntity.money}">
        </c:when>
        <c:otherwise>
          <input id="bet-val" class="bet form-control" type="number" value="10" min="1" max="100">
        </c:otherwise>
      </c:choose>
      <span class="input-group-btn">
        <button id="bet" class="btn btn-success" type="button">Bet</button>
      </span>
      <span class="input-group-btn">
        <button class="btn btn-gold fill-up" type="button">Fill Up</button>
      </span>
    </div>

    <div class="controller game-controller input-group">
      <span class="input-group-btn">
        <button id="hit" class="btn btn-danger" type="button">Hit</button>
      </span>
      <span class="input-group-btn">
        <button id="stand" class="btn btn-primary" type="button">Stand</button>
      </span>
      <span class="input-group-btn">
        <button class="btn btn-gold fill-up" type="button">Fill Up</button>
      </span>
    </div>

  </div>

</body>

</html>
