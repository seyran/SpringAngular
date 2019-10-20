<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" modelAttribute="command" commandName="command"  action="/student/result">
    <table>
        <tr>
            <td><form:name path="firstName">Name</form:name></td>
            <td><form:input path="firstName" /></td>
        </tr>
        <tr>
            <td><form:name path="sex">Sex</form:name></td>
            <td><form:input path="sex" /></td>
        </tr>
        <tr>
            <td><form:name path="id">id</form:name></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
