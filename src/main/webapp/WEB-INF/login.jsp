<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <title>Login</title>

  <link rel="stylesheet" href="/resources/css/bootstrap.v3.3.5.css">
  <link rel="stylesheet" href="/resources/css/styles.css">

</head>

<body>

  <div class="login">
    <form class="input-group" method="post">
      <input id="wallet-number" class="form-control" name="wallet-number" type="text" placeholder="Wallet id">
      <span class="input-group-btn">
        <button id="login-btn" class="btn btn-success" type="submit">Login</button>
      </span>
    </form>

    <c:if test="${requestScope.showWarning}">
      <p class="warn">This wallet number was not registered.</p>
    </c:if>
  </div>

</body>

</html>