<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-8 text-left">
    <div class="container">
        <div class="row content">
            <div class="col-sm-6  text-left ">
                <h3>Cust All Page</h3>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>PWD</th>
                        <th>NAME</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty clist.list}">
                        <tr>
                            <td colspan="3">No customers found.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="c" items="${clist.list}">
                        <tr>
                            <td><a href="/cust/get?id=${c.custId}">${c.custId}</a></td>
                            <td>${c.custPwd}</td>
                            <td>${c.custName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="../page.jsp"/>
            </div>
        </div> <!-- -->
    </div>
</div>