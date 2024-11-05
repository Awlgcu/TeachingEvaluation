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
                snumber.value = '';
                password.value = '';
            }
            else if(body.search("管理员界面")!=-1){
                // body.length>=35734
                location.assign("admin.html");
            } else {
                // alert(body.length)
                location.assign("student.html");
            }

        }
    });
})