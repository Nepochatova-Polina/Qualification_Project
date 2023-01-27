<%@attribute name="numberOfPages" type="java.lang.Integer" required="true"%>
<%@attribute name="currentPage" type="java.lang.Integer" required="true"%>
<table class="pagination">
    <tr>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td class="pageItem">
                        <form method="get" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="catalogue"/>
                            <input type="hidden" name="page-path" value="/catalogue.jsp">
                            <input type="hidden" name="page" value="${i}">
                            <button type="submit">${i}</button>
                        </form>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>