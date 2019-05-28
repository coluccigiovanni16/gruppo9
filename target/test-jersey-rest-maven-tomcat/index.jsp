<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Homepage</h2>

<form action="Initialize" method="post">
    First name:<br>
    <input type="text" name="firstname">
    <br>
    Last name:<br>
    <input type="text" name="lastname" required><br>
    Identifier:<br>
    <input type="int" name="identifier" required><br><br>
    <input type="radio" name="categories" value="TeacherService" checked> Teacher
    <input type="radio" name="categories" value="StudentService"> Student<br>
    <br>
    <input type="submit" value="Log In" >
</form>
</body>
</html>