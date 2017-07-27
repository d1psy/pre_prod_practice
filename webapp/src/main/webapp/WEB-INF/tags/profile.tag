<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
       You are logged in as ${sessionScope.user.getFirstName()}
       <c:if test="${sessionScope.user.getIcon() == null}">
       <img src="images/base_avatar.png">
       </c:if>
       <c:if test="${sessionScope.user.getIcon() != null}">
       <img src="${applicationScope.FILES_RELATIVE_DIR}/${sessionScope.user.getIcon()}">
       </c:if>
       <div style="display: inline-block;">
       <form action="controller" method="post">
       <input type="hidden" name="command" value="logout">
       <input type="submit" value="Logout">
       </form>
       </div>
    </c:when>
    <c:otherwise>
       <li><a href="registered.jsp?name=&email="> Create Account </a></li>
       <li><a href="login.html">Login</a></li>
    </c:otherwise>
</c:choose>