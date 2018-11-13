<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <style> <%@include file="/WEB-INF/css/libraryStyle.css"%> </style>
</head>

<html>

<body>
<h3>Welcome, book enthusiast!</h3>
<hr>
<h4>Books that you have on loan at the moment:</h4>

<c:choose>
    <c:when test="${not empty bookies}">
<table class="opus">
    <tr class="opusFirstRow">
        <th class="opusHeader">Title</th>
        <th class="opusHeader">Author</th>
        <th class="opusHeader">Action</th>
    </tr>

    <c:forEach var="book" items="${bookies}">
        <tr class="opusBookRow">
            <td class="opusData">${book.title}</td>
            <td class="opusData">${book.author}</td>
            <form:form action="returnBook" modelAttribute="book" method="POST">
            <td>
                <input type="hidden" value="${book.id}" name="id" />
                <input type="submit" class="opusAction" value="Return">
            </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
    </c:when>
    <c:otherwise>
    <p class="remark">None at the moment, maybe you should borrow some!</p>
    </c:otherwise>
</c:choose>

<c:if test="${returningSucceeded != null}">
    <p style="color:green;"><c:out value="${returningSucceeded}" /></p>
</c:if>

<hr>

<h4>Books that are available for you to borrow:</h4>

<c:choose>
    <c:when test="${not empty books}">
<table class="opus">
    <tr class="opusFirstRow">
        <th class="opusHeader">Title</th>
        <th class="opusHeader">Author</th>
        <th class="opusHeader">Action</th>
    </tr>

    <c:forEach var="book" items="${books}">
        <tr class="opusBookRow">
            <td class="opusData">${book.title}</td>
            <td class="opusData">${book.author}</td>
            <form:form action="borrowBook" modelAttribute="book" method="POST">
            <td>
                <input type="hidden" value="${book.id}" name="id" />
                <input type="submit" class="opusAction" value="Borrow">
            </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
    </c:when>
    <c:otherwise>
    <p class="remark">We are sorry, no books available at the moment.</p>
    <p class="remark">Please try again later!</p>
    </c:otherwise>
</c:choose>

<c:if test="${borrowingSucceeded != null}">
    <p style="color:green;"><c:out value="${borrowingSucceeded}" /></p>
</c:if>

<p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
</body>
</html>