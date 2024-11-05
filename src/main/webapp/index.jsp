<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教学评价系统登录</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./fontawesome/css/all.min.css">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<%--    <script src="./js/myIndex.js"></script>--%>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>登录</h1>
    </div>
    <div class="main">
        <form id="form" method="post" action="login">
            <%--            method="post" action="login" onsubmit="func1(this)"--%>
            <span>
                <i class="fas fa-user"></i>
                <input type="text" placeholder="account" name="snumber" id="name">
            </span><br>
            <span>
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="password" name="password" id="password">
                </span><br>
            <button type="submit" name="登录" id="btn">登 录</button>
        </form>

    </div>
</div>
<script>
    let tpform = document.getElementById('form');
    tpform.addEventListener('submit', function (event) {
        event.preventDefault();
        $.ajax({
            url: 'login',
            type: 'POST',
            data: $(this).serialize(), // 序列化表单数据
            success: function (body) {
                let snumber =document.getElementById('name');
                let password = document.getElementById('password');
                if (body.message != null) {
                    alert(body.message);
                    // if (body.data){
                    //     console.log(body.data);
                    // }
                    // snumber.value = '';
                    password.value = '';
                }
                else if(body.search("管理员界面")!=-1){
                    location.assign("admin.html");
                } else {
                    // alert(body.length)
                    location.assign("student.html");
                }

            }
        });
    })

</script>
</body>
</html>
