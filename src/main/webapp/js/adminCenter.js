
function checkLogin() {
    $.ajax({
        type: "get",
        url: "login",
        success: function (body) {
            if (body.snumber) {
                sessionStorage.setItem('snumber', body.snumber);
                sessionStorage.setItem('id', body.id);
                sessionStorage.setItem('age', body.age);
                sessionStorage.setItem('gender', body.gender);
                sessionStorage.setItem('classId', body.classId);
                sessionStorage.setItem('name', body.name);
                // alert("登录成功");
                $("#right-h1").text("请在右侧选择对应的操作");
                $("#top-span").text(body.name);
            } else {
                //未登录
                location.assign("index.jsp");
                // alert("未登录")
            }
        }
    });
}
checkLogin();
function getInformation(){
    $.ajax({
        url: 'admin',
        type: 'get',
        data: "snumber="+sessionStorage.getItem('snumber'), // 序列化表单数据
        success: function(body) {
            if (body.message){
                alert(body.message);
            }else{
                // alert("成功拿到学生信息"+body);
                document.getElementById("input1").value=body.snumber;
                document.getElementById("input2").value=body.name;
                document.getElementById("input3").value=body.gender;
                document.getElementById("input4").value=body.age;
                document.getElementById("input5").value=body.phone;
                document.getElementById("input7").value=body.password;
            }
        }
    })
}

$(".span").click(function () {
    $(".span").removeClass("clicked");
    $(this).addClass("clicked");
});

$("#l1").click(function (){
    $(".content-right").empty();
    let tpform = document.createElement("form");
    tpform.action="admin";
    tpform.method="post";

    let div1 = document.createElement("div");
    let div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    let label1 = document.createElement("label");
    label1.innerHTML = "工号";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    let div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    let input1 = document.createElement("input");
    input1.type = "number";
    input1.name = "snumber";
    input1.id="input1";
    input1.readOnly=true;
    div1_subdiv2.appendChild(input1);
    div1.appendChild(div1_subdiv2);
    tpform.appendChild(div1);

    let div2 = document.createElement("div");
    let div2_subdiv1 = document.createElement("div");
    div2_subdiv1.className = "row";
    let label2 = document.createElement("label");
    label2.innerHTML = "姓名";
    div2_subdiv1.appendChild(label2);
    div2.appendChild(div2_subdiv1);
    let div2_subdiv2 = document.createElement("div");
    div2_subdiv2.className = "row";
    let input2 = document.createElement("input");
    input2.type = "text";
    input2.name = "name";
    input2.id="input2";
    div2_subdiv2.appendChild(input2);
    div2.appendChild(div2_subdiv2);
    tpform.appendChild(div2);

    let div3 = document.createElement("div");
    let div3_subdiv1 = document.createElement("div");
    div3_subdiv1.className = "row";
    let label3 = document.createElement("label");
    label3.innerHTML = "性别";
    div3_subdiv1.appendChild(label3);
    div3.appendChild(div3_subdiv1);
    let div3_subdiv2 = document.createElement("div");
    div3_subdiv2.className = "row";
    let input3 = document.createElement("input");
    input3.type = "text";
    input3.name = "gender";
    input3.id="input3";
    div3_subdiv2.appendChild(input3);
    div3.appendChild(div3_subdiv2);
    tpform.appendChild(div3);

    let div4 = document.createElement("div");
    let div4_subdiv1 = document.createElement("div");
    div4_subdiv1.className = "row";
    let label4 = document.createElement("label");
    label4.innerHTML = "年龄";
    div4_subdiv1.appendChild(label4);
    div4.appendChild(div4_subdiv1);
    let div4_subdiv2 = document.createElement("div");
    div4_subdiv2.className = "row";
    let input4 = document.createElement("input");
    input4.type = "number";
    input4.name = "age";
    input4.id="input4";
    div4_subdiv2.appendChild(input4);
    div4.appendChild(div4_subdiv2);
    tpform.appendChild(div4);

    let div5 = document.createElement("div");
    let div5_subdiv1 = document.createElement("div");
    div5_subdiv1.className = "row";
    let label5 = document.createElement("label");
    label5.innerHTML = "电话号码";
    div5_subdiv1.appendChild(label5);
    div5.appendChild(div5_subdiv1);
    let div5_subdiv2 = document.createElement("div");
    div5_subdiv2.className = "row";
    let input5 = document.createElement("input");
    input5.type = "number";
    input5.name = "classId";
    input5.id="input5";
    div5_subdiv2.appendChild(input5);
    div5.appendChild(div5_subdiv2);
    tpform.appendChild(div5);

    let div7 = document.createElement("div");
    let div7_subdiv1 = document.createElement("div");
    div7_subdiv1.className = "row";
    let label7 = document.createElement("label");
    label7.innerHTML = "密码";
    div7_subdiv1.appendChild(label7);
    div7.appendChild(div7_subdiv1);
    let div7_subdiv2 = document.createElement("div");
    div7_subdiv2.className = "row";
    let input7 = document.createElement("input");
    input7.type = "text";
    input7.name = "password";
    input7.id="input7";
    div7_subdiv2.appendChild(input7);
    div7.appendChild(div7_subdiv2);
    tpform.appendChild(div7);

    getInformation();

    let div6 = document.createElement("div");
    let div6_sub = document.createElement("div");
    div6_sub.className = "row";
    let sub = document.createElement("input");
    sub.type = "submit";
    div6_sub.appendChild(sub);
    div6.appendChild(div6_sub);
    tpform.appendChild(div6);

    tpform.addEventListener('submit', function (event){
        event.preventDefault();
        let name = document.getElementById('input2').value;
        let age = document.getElementById('input4').value;
        let gender = document.getElementById('input3').value;
        let snumber = document.getElementById('input1').value;
        let phone = document.getElementById('input5').value;
        let password = document.getElementById('input7').value;
        let dataString = "snumber="+snumber+"&"+"name="+name+"&"+"age="+age+"&"+"gender="+gender+"&"+"phone="+phone+"&"+"password="+password+"&"+"action=update";
        // alert(dataString);
        $.ajax({
            url: 'admin',
            type: 'POST',
            data: dataString, // 序列化表单数据
            success: function(body) {
                alert(body.message);
                checkLogin();
                getInformation();
            }
        })
    })
    $(".content-right").append(tpform);
});

// 当修改密码被点击时触发事件,修改右边界面
//todo 注意-----下面的action还需要修改
$("#l2").click(function () {
    $(".content-right").empty();
    var tpform = document.createElement("form");
    tpform.action = "admin";
    tpform.method = "post";

    var div1 = document.createElement("div");
    var div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    var label1 = document.createElement("label");
    label1.innerHTML = "请输入旧密码";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    var div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    var input1 = document.createElement("input");
    input1.type = "password";
    input1.name = "oldPassword";
    input1.required = "true";
    div1_subdiv2.appendChild(input1);
    div1.appendChild(div1_subdiv2);
    tpform.appendChild(div1);

    var div2 = document.createElement("div");
    var div2_subdiv1 = document.createElement("div");
    div2_subdiv1.className = "row";
    var label2 = document.createElement("label");
    label2.innerHTML = "请输入新密码";
    div2_subdiv1.appendChild(label2);
    div2.appendChild(div2_subdiv1);
    var div2_subdiv2 = document.createElement("div");
    div2_subdiv2.className = "row";
    var input2 = document.createElement("input");
    input2.type = "password";
    input2.name = "newPassword1";
    input2.required = "true";
    div2_subdiv2.appendChild(input2);
    div2.appendChild(div2_subdiv2);
    tpform.appendChild(div2);

    var div3 = document.createElement("div");
    var div3_subdiv1 = document.createElement("div");
    div3_subdiv1.className = "row";
    var label3 = document.createElement("label");
    label3.innerHTML = "请确认新密码";
    div3_subdiv1.appendChild(label3);
    div3.appendChild(div3_subdiv1);
    var div3_subdiv2 = document.createElement("div");
    div3_subdiv2.className = "row";
    var input3 = document.createElement("input");
    input3.type = "password";
    input3.name = "newPassword2";
    input3.required = "true";
    div3_subdiv2.appendChild(input3);
    div3.appendChild(div3_subdiv2);
    tpform.appendChild(div3);

    var div4 = document.createElement("div");
    var div4_sub = document.createElement("div");
    div4_sub.className = "row";
    var sub = document.createElement("input");
    sub.type = "submit";
    div4_sub.appendChild(sub);
    div4.appendChild(div4_sub);
    tpform.appendChild(div4);

    tpform.addEventListener('submit', function (event){
        event.preventDefault();
        $.ajax({
            url: 'admin',
            type: 'POST',
            data: $(this).serialize(), // 序列化表单数据
            success: function(body) {
                //
                if (body.message==='修改成功,请重新登录!'){
                    alert(body.message);
                    location.assign("index.jsp");
                }else{
                    alert(body.message);
                }
            }
        });
    })

    $(".content-right").append(tpform);
});