<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Deposit</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link rel='stylesheet' href='${pageContext.request.contextPath}/resources/css/sweetalert2.min.css'>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <style>
        label.error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <caption><h2>Form Deposit Customers</h2></caption>
        <a href="/customers">Back to customer list</a>
        <%--    <h2><a href="/banking_system">Manager Customer</a></h2>--%>

        <form id="frmDeposit" method="post">

            <div class="row mb-3" style="text-align: left;">
                <c:if test="${success !=null}">
                        <span style="color: green;"><c:out value="${success}"/></span>
                </c:if>
                <c:if test="${error !=null}">
                <span style="color: red;"><c:out value="${error}"/></span>
                </c:if>
            </div>

            <div class="row mb-3">
                <div class="col-6">
                    <label>Name: </label>
                    <input type="text" name="name" size="45" class="form-control" value="<c:out value='${customer.getName()}'/>" readonly >

                </div>
                <div class="col-6">
                    <label>Email:</label>
                    <input type="text" name="email" id="email" size="45" value="<c:out value='${customer.getEmail()}'/>" class="form-control" readonly>

                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label>Phone:</label>
                    <input type="text" name="phone" id="phone" size="45" value="<c:out value='${customer.getPhone()}'/>" class="form-control" readonly>

                </div>
                <div class="col-md-6">
                    <label>Address:</label>
                    <input type="text" name="address" id="address" size="45" value="<c:out value='${customer.getAddress()}'/>" class="form-control" readonly>

                </div>
                <div class="col-md-6">
                    <label>Balance</label>
                    <input type="number" name="balance" class="form-control"/>
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-3">
                    <button type="submit" class="btn btn-warning">Deposit</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        $(document).ready(function () {
            //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
            $("#frmDeposit").validate({
                rules: {
                    balance: {
                        "required": true,
                        "min": 50,
                        "max": 1000,
                        //"range": (50,10000)
                    }
                },
                messages: {
                    balance: {
                        required: "Vui lòng nhập số tiền",
                        min: "Số tiền phải lớn hơn 49",
                        max: " Số tiền phải nhỏ hơn 1001"
                        //range: "Số tiền phải từ 50 - 10000"
                    }
                }
            });
        });
    </script>
</body>
</html>

