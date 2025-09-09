
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-10">
  <h2>Product List</h2>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Discount Rate</th>
        <th>Image</th>
        <th>Category ID</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="product" items="${products}">
        <tr>
          <td>${product.productId}</td>
          <td>${product.productName}</td>
          <td>${product.productPrice}</td>
          <td>${product.discountRate}</td>
          <td>
            <c:if test="${not empty product.productImg}">
              <img src="/imgs/${product.productImg}" alt="${product.productName}" width="50">
            </c:if>
          </td>
          <td>${product.cateId}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
