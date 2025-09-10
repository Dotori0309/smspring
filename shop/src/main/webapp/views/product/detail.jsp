<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-9">
    <h2>Product Detail</h2>
    <form action="<c:url value='/product/updateimpl'/>" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>Image:</label>
            <img src="/imgs/${p.productImg}" width="200px">
        </div>
        <div class="form-group">
            <label for="productId">Id:</label>
            <input type="text" class="form-control" id="productId" name="productId" value="${p.productId}" readonly>
        </div>
        <div class="form-group">
            <label for="productName">Name:</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${p.productName}">
        </div>
        <div class="form-group">
            <label for="productPrice">Price:</label>
            <input type="number" class="form-control" id="productPrice" name="productPrice" value="${p.productPrice}">
        </div>
        <div class="form-group">
            <label for="discountRate">Discount Rate:</label>
            <input type="number" step="0.01" class="form-control" id="discountRate" name="discountRate" value="${p.discountRate}">
        </div>
        <div class="form-group">
            <label for="productImg">Product Img:</label>
            <input type="text" class="form-control" id="productImg" name="productImg" value="${p.productImg}" readonly>
        </div>
        <div class="form-group">
            <label for="productImgFile">New Image:</label>
            <input type="file" class="form-control-file" id="productImgFile" name="productImgFile">
        </div>
        <div class="form-group">
            <label for="cateId">Cate Id:</label>
            <input type="number" class="form-control" id="cateId" name="cateId" value="${p.cateId}">
        </div>
        <div class="form-group">
            <label>Register Date:</label>
            <input type="text" class="form-control" value="<fmt:formatDate value='${p.productRegdate}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly>
        </div>
        <div class="form-group">
            <label>Update Date:</label>
            <input type="text" class="form-control" value="<fmt:formatDate value='${p.productUpdate}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="<c:url value='/product/delete?id=${p.productId}'/>" class="btn btn-danger">Delete</a>
    </form>
</div>