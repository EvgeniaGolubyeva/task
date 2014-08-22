<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Message Managing System</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
</head>

<body>

    <div class="container">

        <div class="page-header">
            <h1 class="text-center">Main View</h1>
        </div>

        <div class="row">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>
                            Message Text
                        </th>
                        <th>
                            User Name
                        </th>
                        <th>
                            Actions
                        </th>
                    </tr>
                </thead>

                <tbody>
                <c:forEach var="message" items="${messages}">
                    <tr>
                        <td>
                            ${message.text}
                        </td>
                        <td>
                            ${message.user.name}
                        </td>
                        <td>
                            <a href="message/${message.id}" class="btn btn-default btn-sm">Edit</a>
                            <a href="message/${message.id}/delete" class="btn btn-default btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row text-center">
            <a href="message/new" class="btn btn-primary btn-sm">Add new message</a>
        </div>

    </div>

</body>
</html>