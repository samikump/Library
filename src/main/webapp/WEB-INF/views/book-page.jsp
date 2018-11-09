<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
<h3>Welcome, book enthusiast!</h3>
<hr>
<h4>Books that you have on loan at the moment:</h4>

<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <c:forEach var="book" items="${bookies}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>
                <form:form action="returnBook" modelAttribute="book" method="POST">
                    <input type="hidden" value="${book.id}" name="id" />
                    <input type="submit" value="Return">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${returningSucceeded != null}">
    <p style="color:green;"><c:out value="${returningSucceeded}" /></p>
</c:if>

<hr>

<h4>Books that are available for you to borrow:</h4>

<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>
                <form:form action="borrowBook" modelAttribute="book" method="POST">
                    <input type="hidden" value="${book.id}" name="id" />
                    <input type="submit" value="Borrow">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${borrowingSucceeded != null}">
    <p style="color:green;"><c:out value="${borrowingSucceeded}" /></p>
</c:if>

<p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
</body>
</html>