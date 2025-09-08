<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-10">
  <h2>Cust Get Page</h2>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Regdate</th>
      <th>Update</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${clist.list}">
      <tr>
        <td><a href="/cust/detail?id=${c.custId}">${c.custId}</a></td>
        <td>${c.custName}</td>
        <td>
          <fmt:parseDate value="${ c.custRegdate }"
                         pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
          <fmt:formatDate pattern="yyyy년MM월dd일 HH:mm" value="${ parsedDateTime }" />
        </td>
        <td>
          <fmt:parseDate value="${c.custUpdate}"
                         pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
          <fmt:formatDate pattern="yyyy년MM월dd일 HH:mm" value="${ parsedDateTime }" />
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <ul class="pagination">
    <c:if test="${clist.hasPreviousPage}">
      <li><a href="${target}/getpage?pageNo=${clist.prePage}">&laquo;</a></li>
    </c:if>
    <c:forEach var="page" items="${clist.navigatepageNums}">
      <c:choose>
        <c:when test="${clist.pageNum eq page}">
          <li class="active"><a href="#">${page}</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${target}/getpage?pageNo=${page}">${page}</a></li>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:if test="${clist.hasNextPage}">
      <li><a href="${target}/getpage?pageNo=${clist.nextPage}">&raquo;</a></li>
    </c:if>
  </ul>
</div>
