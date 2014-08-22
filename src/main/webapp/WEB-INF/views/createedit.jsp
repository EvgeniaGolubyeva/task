<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Message Managing System</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
</head>

<body>

<div class="container">

    <div class="page-header">
        <h1 class="text-center">Create/Edit View</h1>
    </div>

    <sf:form class="form-horizontal" method="POST" modelAttribute="message"
             action="${pageContext.request.contextPath}/message/update">
        <sf:input type="hidden" path="id"/>
        <sf:input type="hidden" path="user.id"/>

        <div class="form-group">
            <div class="col-xs-3">
                <label for="userName">User Name</label>
            </div>
            <div class="col-xs-9">
                <sf:input class="form-control" id="userName" path="user.name"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-12">
                <label for="messageText">Message Text</label>
            </div>
            <div class="col-xs-12">
                <sf:textarea class="form-control" rows="7" id="messageText" path="text"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-3 col-xs-offset-6">
                <button type="submit" class="btn btn-primary form-control" name = "save">Save</button>
            </div>
            <div class="col-xs-3">
                <button class="btn btn-primary form-control" name = "cancel">Cancel</button>
            </div>
        </div>
    </sf:form>

</div>

</body>
</html>