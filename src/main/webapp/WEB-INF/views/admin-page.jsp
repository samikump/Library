<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
<h3>Welcome, admin!</h3>
<hr>
<h4>Add new book</h4>

    <c:if test="${bookAdded != null}">
        <p style="color:green;"><c:out value="${bookAdded}" /></p>
    </c:if>

    <form:form action="saveBook" modelAttribute="book" method="POST">

            <input type="number" name="id" value="0" style="display: none" />
            <label>Title: </label><input type="text" name="title" />
            <label>Author: </label><input type="text" name="author" />
            <input type="input" name="status" value="available" style="display: none" />
            <input type="input" name="borrowedBy" value="" style="display: none" />
            <input type="submit" value="Submit" />

    </form:form>

<p><a href="${pageContext.request.contextPath}/books">Book page</a></p>
<p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
</body>
</html>