
/**
 * css切换
 */
$(".span").click(function () {
    $(".span").removeClass("clicked");
    $(this).addClass("clicked");
    alert($(this).attr("id"));
});
$(document).ready(function () {
    $("#student").click(function () {
        $(".stu").slideToggle("fast");
    });
    $("#teacher").click(function () {
        $(".tea").slideToggle("fast");
    });
    $("#admin").click(function () {
        $(".adm").slideToggle("fast");
    });
    $("#result").click(function () {
        $(".res").slideToggle("fast");
    });
    $("#indicator").click(function () {
        $(".indi").slideToggle("fast");
    })
});

/**
 * 修改查看学生
 */
$("#updateStudent").click(function () {
    // let forms = document.getElementById("content-right");
    // while (forms.firstChild) {
    //     forms.removeChild(forms.firstChild);
    // }
    $.ajax({
        url: 'adminStudent',
        method: 'get',
        success: function (data) {
            $(".content-right").empty();

            var i=0;
            for (stu of data) {
                i = i+1;
                let tpform = document.createElement("form");
                tpform.action = "adminStudent";
                tpform.method = "post";

                let div1 = document.createElement("div");
                let div1_subdiv1 = document.createElement("div");
                div1_subdiv1.className = "row";
                let label1 = document.createElement("label");
                label1.innerHTML = "学号";
                div1_subdiv1.appendChild(label1);
                div1.appendChild(div1_subdiv1);
                let div1_subdiv2 = document.createElement("div");
                div1_subdiv2.className = "row";
                let input1 = document.createElement("input");
                input1.type = "number";
                input1.name = "snumber";
                input1.id = "input1"+i;
                input1.value = stu.snumber
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
                input2.id = "input2"+i;
                input2.value = stu.name;
                div2_subdiv2.appendChild(input2);
                div2.appendChild(div2_subdiv2);
                tpform.appendChild(div2);

                let div3 = document.createElement("div");
                let div3_subdiv1 = document.createElement("div");
                div3_subdiv1.className = "row";
                let label3 = document.createElement("label");
                label3.innerHTML = "年龄";
                div3_subdiv1.appendChild(label3);
                div3.appendChild(div3_subdiv1);
                let div3_subdiv2 = document.createElement("div");
                div3_subdiv2.className = "row";
                let input3 = document.createElement("input");
                input3.type = "number";
                input3.name = "age";
                input3.id = "input3"+i;
                input3.value = stu.age;
                div3_subdiv2.appendChild(input3);
                div3.appendChild(div3_subdiv2);
                tpform.appendChild(div3);

                let div4 = document.createElement("div");
                let div4_subdiv1 = document.createElement("div");
                div4_subdiv1.className = "row";
                let label4 = document.createElement("label");
                label4.innerHTML = "性别";
                div4_subdiv1.appendChild(label4);
                div4.appendChild(div4_subdiv1);
                let div4_subdiv2 = document.createElement("div");
                div4_subdiv2.className = "row";
                let input4 = document.createElement("input");
                input4.type = "text";
                input4.name = "gender";
                input4.id = "input4"+i;
                input4.value = stu.gender;
                div4_subdiv2.appendChild(input4);
                div4.appendChild(div4_subdiv2);
                tpform.appendChild(div4);

                let div5 = document.createElement("div");
                let div5_subdiv1 = document.createElement("div");
                div5_subdiv1.className = "row";
                let label5 = document.createElement("label");
                label5.innerHTML = "班级";
                div5_subdiv1.appendChild(label5);
                div5.appendChild(div5_subdiv1);
                let div5_subdiv2 = document.createElement("div");
                div5_subdiv2.className = "row";
                let input5 = document.createElement("input");
                input5.type = "number";
                input5.name = "classId";
                input5.id = "input5"+i;
                input5.value = stu.classId;
                div5_subdiv2.appendChild(input5);
                div5.appendChild(div5_subdiv2);
                tpform.appendChild(div5);

                let div6 = document.createElement("div");
                let div6_subdiv1 = document.createElement("div");
                div6_subdiv1.className = "row";
                let label6 = document.createElement("label");
                label6.innerHTML = "密码";
                div6_subdiv1.appendChild(label6);
                div6.appendChild(div6_subdiv1);
                let div6_subdiv2 = document.createElement("div");
                div6_subdiv2.className = "row";
                let input6 = document.createElement("input");
                input6.type = "text";
                input6.name = "password";
                input6.id = "input6"+i;
                input6.value = stu.password;
                div6_subdiv2.appendChild(input6);
                div6.appendChild(div6_subdiv2);
                tpform.appendChild(div6);

                let lastRow = document.createElement("div");
                lastRow.className = "row";
                let last1 = document.createElement("input");
                last1.type = "submit";
                last1.className = "updateBtn";
                last1.id="last1"+i;
                last1.value = "保存";
                lastRow.appendChild(last1);
                let last2 = document.createElement("input");
                last2.type = "submit";
                last2.className = "deleteBtn";
                last2.id = "last2"+i;
                last2.value = "删除";
                lastRow.appendChild(last2);
                tpform.appendChild(lastRow);

                let hidde_input = document.createElement("input");
                hidde_input.type = "hidden";
                hidde_input.name = "id";
                hidde_input.id = "id"+i;
                hidde_input.value = stu.id;
                tpform.appendChild(hidde_input);

                tpform.addEventListener('submit', function (event){
                    event.preventDefault();
                    // alert($(this).serialize());
                    $.ajax({
                        url: 'adminStudent',
                        method: 'post',
                        data: $(this).serialize(),
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                })
                last2.addEventListener('click', function (event) {
                    event.preventDefault();
                    let id=$(this).parent().parent().find("input[name='id']").val();
                    let bodyString = "id=" + id + "&" + "action=delete";
                    // alert(bodyString);
                    $.ajax({
                        url: 'adminStudent',
                        method: 'post',
                        data: bodyString,
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                });
                let outer = document.getElementById('right');
                outer.appendChild(tpform);
            }
        }
    })
});

/**
 * 添加学生
 */
$("#addStudent").click(function () {
    $(".content-right").empty();
    let tpform = document.createElement("form");
    tpform.action = "adminStudent";
    tpform.method = "post";
    tpform.className = "fullForm";

    let div1 = document.createElement("div");
    let div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    let label1 = document.createElement("label");
    label1.innerHTML = "学号(六位)";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    let div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    let input1 = document.createElement("input");
    input1.className = "fullInput";
    input1.required = true;
    input1.type = "number";
    input1.name = "snumber";
    input1.id = "input1";
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
    input2.id = "input2";
    input2.className = "fullInput";
    input2.required = true;
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
    input3.id = "input3";
    input3.className = "fullInput";
    input3.required = true;
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
    input4.id = "input4";
    input4.className = "fullInput";
    input4.required = true;
    div4_subdiv2.appendChild(input4);
    div4.appendChild(div4_subdiv2);
    tpform.appendChild(div4);

    let div5 = document.createElement("div");
    let div5_subdiv1 = document.createElement("div");
    div5_subdiv1.className = "row";
    let label5 = document.createElement("label");
    label5.innerHTML = "班级";
    div5_subdiv1.appendChild(label5);
    div5.appendChild(div5_subdiv1);
    let div5_subdiv2 = document.createElement("div");
    div5_subdiv2.className = "row";
    let input5 = document.createElement("input");
    input5.type = "number";
    input5.name = "classId";
    input5.id = "input5";
    input5.className = "fullInput";
    input5.required = true;
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
    input7.id = "input7";
    input7.className = "fullInput";
    input7.required = true;
    div7_subdiv2.appendChild(input7);
    div7.appendChild(div7_subdiv2);
    tpform.appendChild(div7);

    let div6 = document.createElement("div");
    let div6_sub = document.createElement("div");
    div6_sub.className = "row";
    let sub = document.createElement("input");
    sub.type = "submit";
    sub.className = "onlySubmit";
    sub.value = "确认添加";
    div6_sub.appendChild(sub);
    div6.appendChild(div6_sub);
    tpform.appendChild(div6);

    sub.addEventListener('click', function (event) {
        event.preventDefault();
        // alert("点击提交");
        let snumber = document.getElementById('input1').value;
        let name = document.getElementById('input2').value;
        let gender = document.getElementById('input3').value;
        let age = document.getElementById('input4').value;
        let classId = document.getElementById('input5').value;
        let password = document.getElementById('input7').value;
        let dataString = "snumber=" + snumber + "&" + "name=" + name + "&" + "age=" + age + "&" + "gender=" + gender + "&" + "classId=" + classId + "&" + "password=" + password + "&" + "action=insert";
        // alert(dataString);
        $.ajax({
            url: 'adminStudent',
            type: 'POST',
            data: dataString,
            success: function (body) {
                alert(body.message);
            }
        });
    })
    $(".content-right").append(tpform);
});

/**
 * 查看修改老师
 */
$("#updateTeacher").click(function () {
    $.ajax({
        url: 'adminTeacher',
        method: 'get',
        success: function (data) {
            $(".content-right").empty();
            // alert("查到的老师数量"+data.length);
            console.log(data);
            for (teacher of data) {
                let tpform = document.createElement("form");
                tpform.action = "adminTeacher";
                tpform.method = "post";

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
                input1.name = "tnumber";
                input1.id = "input1";
                input1.value = teacher.tnumber
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
                input2.id = "input2";
                input2.value = teacher.name;
                div2_subdiv2.appendChild(input2);
                div2.appendChild(div2_subdiv2);
                tpform.appendChild(div2);

                let div3 = document.createElement("div");
                let div3_subdiv1 = document.createElement("div");
                div3_subdiv1.className = "row";
                let label3 = document.createElement("label");
                label3.innerHTML = "年龄";
                div3_subdiv1.appendChild(label3);
                div3.appendChild(div3_subdiv1);
                let div3_subdiv2 = document.createElement("div");
                div3_subdiv2.className = "row";
                let input3 = document.createElement("input");
                input3.type = "number";
                input3.name = "age";
                input3.id = "input3";
                input3.value = teacher.age;
                div3_subdiv2.appendChild(input3);
                div3.appendChild(div3_subdiv2);
                tpform.appendChild(div3);

                let div4 = document.createElement("div");
                let div4_subdiv1 = document.createElement("div");
                div4_subdiv1.className = "row";
                let label4 = document.createElement("label");
                label4.innerHTML = "性别";
                div4_subdiv1.appendChild(label4);
                div4.appendChild(div4_subdiv1);
                let div4_subdiv2 = document.createElement("div");
                div4_subdiv2.className = "row";
                let input4 = document.createElement("input");
                input4.type = "text";
                input4.name = "gender";
                input4.id = "input4";
                input4.value = teacher.gender;
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
                input5.type = "text";
                input5.name = "phone";
                input5.id = "input5";
                input5.value = teacher.phone;
                div5_subdiv2.appendChild(input5);
                div5.appendChild(div5_subdiv2);
                tpform.appendChild(div5);

                let div6 = document.createElement("div");
                let div6_subdiv1 = document.createElement("div");
                div6_subdiv1.className = "row";
                let label6 = document.createElement("label");
                label6.innerHTML = "简介";
                div6_subdiv1.appendChild(label6);
                div6.appendChild(div6_subdiv1);
                let div6_subdiv2 = document.createElement("div");
                div6_subdiv2.className = "row";
                let input6 = document.createElement("input");
                input6.type = "text";
                input6.name = "introduction";
                input6.id = "input6";
                input6.value = teacher.introduction;
                div6_subdiv2.appendChild(input6);
                div6.appendChild(div6_subdiv2);
                tpform.appendChild(div6);

                let lastRow = document.createElement("div");
                lastRow.className = "row";
                let last1 = document.createElement("input");
                last1.type = "submit";
                last1.className = "updateBtn";
                last1.value = "保存";
                lastRow.appendChild(last1);
                let last2 = document.createElement("input");
                last2.type = "submit";
                last2.className = "deleteBtn";
                last2.value = "删除";
                lastRow.appendChild(last2);
                tpform.appendChild(lastRow);

                let hidde_input = document.createElement("input");
                hidde_input.type = "hidden";
                hidde_input.name = "id";
                hidde_input.id = "id";
                hidde_input.value = teacher.id;
                tpform.appendChild(hidde_input);
                tpform.addEventListener('submit', function (event){
                    event.preventDefault();
                    // alert($(this).serialize());
                    $.ajax({
                        url: 'adminTeacher',
                        method: 'post',
                        data: $(this).serialize(),
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                })
                last2.addEventListener('click', function (event) {
                    event.preventDefault();
                    let id=$(this).parent().parent().find("input[name='id']").val();
                    let bodyString = "id=" + id + "&" + "action=delete";
                    // alert(bodyString);
                    $.ajax({
                        url: 'adminTeacher',
                        method: 'post',
                        data: bodyString,
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                });
                let outer = document.getElementById('right');
                outer.appendChild(tpform);
            }
        }

    })
});

/**
 * 查看修改评价指标
 */
$("#updateIndicator").click(function () {
    $.ajax({
        url: 'adminIndicator',
        method: 'get',
        success: function (data) {
            $(".content-right").empty();
            // alert("查到的指标数量"+data.length);
            for (indi of data) {
                let tpform = document.createElement("form");
                tpform.action = "adminIndicator";
                tpform.method = "post";

                let div1 = document.createElement("div");
                let div1_subdiv1 = document.createElement("div");
                div1_subdiv1.className = "row";
                let label1 = document.createElement("label");
                label1.innerHTML = "评价指标";
                div1_subdiv1.appendChild(label1);
                div1.appendChild(div1_subdiv1);
                let div1_subdiv2 = document.createElement("div");
                div1_subdiv2.className = "row";
                let input1 = document.createElement("input");
                input1.type = "text";
                input1.name = "indicator";
                input1.id = "input1";
                input1.value = indi.indicator;
                div1_subdiv2.appendChild(input1);
                div1.appendChild(div1_subdiv2);
                tpform.appendChild(div1);

                let lastRow = document.createElement("div");
                lastRow.className = "row";
                let last1 = document.createElement("input");
                last1.type = "submit";
                last1.className = "updateBtn";
                last1.value = "保存";
                lastRow.appendChild(last1);
                let last2 = document.createElement("input");
                last2.type = "submit";
                last2.className = "deleteBtn";
                last2.value = "删除";
                lastRow.appendChild(last2);
                tpform.appendChild(lastRow);

                let hidde_input = document.createElement("input");
                hidde_input.type = "hidden";
                hidde_input.name = "id";
                hidde_input.id = "id";
                hidde_input.value = indi.id;
                tpform.appendChild(hidde_input);

                tpform.addEventListener('submit', function (event){
                    event.preventDefault();
                    // alert($(this).serialize());
                    $.ajax({
                        url: 'adminIndicator',
                        method: 'post',
                        data: $(this).serialize(),
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                })
                last2.addEventListener('click', function (event) {
                    event.preventDefault();
                    let id=$(this).parent().parent().find("input[name='id']").val();
                    let bodyString = "id=" + id + "&" + "action=delete";
                    // alert(bodyString);
                    $.ajax({
                        url: 'adminIndicator',
                        method: 'post',
                        data: bodyString,
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                });
                let outer = document.getElementById('right');
                outer.appendChild(tpform);
            }
        }

    })
});

/**
 * 查看评价结果
 */
$("#showResult").click(function () {
    $.ajax({
        url: 'adminResult',
        method: 'get',
        success: function (data) {
            $(".content-right").empty();
            // alert("查到的结果数量"+data.length);
            for (res of data) {
                let tpform = document.createElement("form");

                let div1 = document.createElement("div");
                let div1_subdiv1 = document.createElement("div");
                div1_subdiv1.className = "row";
                let label1 = document.createElement("label");
                label1.innerHTML = "老师姓名";
                div1_subdiv1.appendChild(label1);
                div1.appendChild(div1_subdiv1);
                let div1_subdiv2 = document.createElement("div");
                div1_subdiv2.className = "row";
                let input1 = document.createElement("input");
                input1.type = "text";
                input1.name = "tname";
                input1.id = "input1";
                input1.value = res.tname
                input1.readOnly = true;
                div1_subdiv2.appendChild(input1);
                div1.appendChild(div1_subdiv2);
                tpform.appendChild(div1);

                let div2 = document.createElement("div");
                let div2_subdiv1 = document.createElement("div");
                div2_subdiv1.className = "row";
                let label2 = document.createElement("label");
                label2.innerHTML = "学生姓名";
                div2_subdiv1.appendChild(label2);
                div2.appendChild(div2_subdiv1);
                let div2_subdiv2 = document.createElement("div");
                div2_subdiv2.className = "row";
                let input2 = document.createElement("input");
                input2.type = "text";
                input2.name = "sname";
                input2.id = "input2";
                input2.value = res.sname;
                input2.readOnly = true;
                div2_subdiv2.appendChild(input2);
                div2.appendChild(div2_subdiv2);
                tpform.appendChild(div2);

                let div3 = document.createElement("div");
                let div3_subdiv1 = document.createElement("div");
                div3_subdiv1.className = "row";
                let label3 = document.createElement("label");
                label3.innerHTML = "结果";
                div3_subdiv1.appendChild(label3);
                div3.appendChild(div3_subdiv1);
                let div3_subdiv2 = document.createElement("div");
                div3_subdiv2.className = "row";
                let input3 = document.createElement("input");
                input3.type = "number";
                input3.name = "results";
                input3.id = "input3";
                input3.value = res.results;
                input3.readOnly = true;
                div3_subdiv2.appendChild(input3);
                div3.appendChild(div3_subdiv2);
                tpform.appendChild(div3);

                let div4 = document.createElement("div");
                let div4_subdiv1 = document.createElement("div");
                div4_subdiv1.className = "row";
                let label4 = document.createElement("label");
                label4.innerHTML = "指标号";
                div4_subdiv1.appendChild(label4);
                div4.appendChild(div4_subdiv1);
                let div4_subdiv2 = document.createElement("div");
                div4_subdiv2.className = "row";
                let input4 = document.createElement("input");
                input4.type = "number";
                input4.name = "indicator";
                input4.id = "input4";
                input4.value = res.indicatorId;
                input4.readOnly = true;
                div4_subdiv2.appendChild(input4);
                div4.appendChild(div4_subdiv2);
                tpform.appendChild(div4);

                $(".content-right").append(tpform);
            }
        }

    })
});

/**
 * 添加老师
 */
$("#addTeacher").click(function () {
    $(".content-right").empty();
    let tpform = document.createElement("form");
    tpform.action = "adminTeacher";
    tpform.method = "post";
    tpform.className = "fullForm";

    let div1 = document.createElement("div");
    let div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    let label1 = document.createElement("label");
    label1.innerHTML = "工号(六位)";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    let div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    let input1 = document.createElement("input");
    input1.className = "fullInput";
    input1.required = true;
    input1.type = "number";
    input1.name = "snumber";
    input1.id = "input1";
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
    input2.id = "input2";
    input2.className = "fullInput";
    input2.required = true;
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
    input3.id = "input3";
    input3.className = "fullInput";
    input3.required = true;
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
    input4.id = "input4";
    input4.className = "fullInput";
    input4.required = true;
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
    input5.name = "phone";
    input5.id = "input5";
    input5.className = "fullInput";
    input5.required = true;
    div5_subdiv2.appendChild(input5);
    div5.appendChild(div5_subdiv2);
    tpform.appendChild(div5);

    let div7 = document.createElement("div");
    let div7_subdiv1 = document.createElement("div");
    div7_subdiv1.className = "row";
    let label7 = document.createElement("label");
    label7.innerHTML = "简介";
    div7_subdiv1.appendChild(label7);
    div7.appendChild(div7_subdiv1);
    let div7_subdiv2 = document.createElement("div");
    div7_subdiv2.className = "row";
    let input7 = document.createElement("input");
    input7.type = "text";
    input7.name = "introduction";
    input7.id = "input7";
    input7.className = "fullInput";
    input7.required = true;
    div7_subdiv2.appendChild(input7);
    div7.appendChild(div7_subdiv2);
    tpform.appendChild(div7);

    let div6 = document.createElement("div");
    let div6_sub = document.createElement("div");
    div6_sub.className = "row";
    let sub = document.createElement("input");
    sub.type = "submit";
    sub.className = "onlySubmit";
    sub.value = "确认添加";
    div6_sub.appendChild(sub);
    div6.appendChild(div6_sub);
    tpform.appendChild(div6);

    sub.addEventListener('click', function (event) {
        event.preventDefault();
        let tnumber = document.getElementById('input1').value;
        let name = document.getElementById('input2').value;
        let gender = document.getElementById('input3').value;
        let age = document.getElementById('input4').value;
        let phone = document.getElementById('input5').value;
        let introduction = document.getElementById('input7').value;
        let dataString = "tnumber=" + tnumber + "&" + "name=" + name + "&" + "age=" + age + "&" + "gender=" + gender + "&" + "phone=" + phone + "&" + "introduction=" + introduction + "&" + "action=insert";
        $.ajax({
            url: 'adminTeacher',
            type: 'POST',
            data: dataString,
            success: function (body) {
                alert(body.message);
            }
        });
    })
    $(".content-right").append(tpform);
});

/**
 * 添加评价指标
 */
$("#addIndicator").click(function () {
    $(".content-right").empty();
    let tpform = document.createElement("form");
    tpform.action = "adminIndicator";
    tpform.method = "post";
    tpform.className = "fullForm";

    let div1 = document.createElement("div");
    let div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    let label1 = document.createElement("label");
    label1.innerHTML = "评价指标";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    let div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    let input1 = document.createElement("input");
    input1.className = "fullInput";
    input1.required = true;
    input1.type = "text";
    input1.name = "indicator";
    input1.id = "input1";
    div1_subdiv2.appendChild(input1);
    div1.appendChild(div1_subdiv2);
    tpform.appendChild(div1);



    let div6 = document.createElement("div");
    let div6_sub = document.createElement("div");
    div6_sub.className = "row";
    let sub = document.createElement("input");
    sub.type = "submit";
    sub.className = "onlySubmit";
    sub.value = "确认添加";
    div6_sub.appendChild(sub);
    div6.appendChild(div6_sub);
    tpform.appendChild(div6);

    sub.addEventListener('click', function (event) {
        event.preventDefault();
        let indicator = document.getElementById('input1').value;

        let dataString =  "indicator=" + indicator + "&" + "action=insert";
        // alert(dataString);
        $.ajax({
            url: 'adminIndicator',
            type: 'POST',
            data: dataString,
            success: function (body) {
                alert(body.message);
            }
        });
    })
    $(".content-right").append(tpform);
});

/**
 * 修改查看管理员
 */
$("#updateAdmin").click(function () {
    $.ajax({
        url: 'adminAdmin',
        method: 'get',
        success: function (data) {
            $(".content-right").empty();
            for (admin of data) {
                let tpform = document.createElement("form");
                tpform.action = "adminAdmin";
                tpform.method = "post";

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
                input1.id = "input1";
                input1.value = admin.snumber
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
                input2.id = "input2";
                input2.value = admin.name;
                div2_subdiv2.appendChild(input2);
                div2.appendChild(div2_subdiv2);
                tpform.appendChild(div2);

                let div3 = document.createElement("div");
                let div3_subdiv1 = document.createElement("div");
                div3_subdiv1.className = "row";
                let label3 = document.createElement("label");
                label3.innerHTML = "年龄";
                div3_subdiv1.appendChild(label3);
                div3.appendChild(div3_subdiv1);
                let div3_subdiv2 = document.createElement("div");
                div3_subdiv2.className = "row";
                let input3 = document.createElement("input");
                input3.type = "number";
                input3.name = "age";
                input3.id = "input3";
                input3.value = admin.age;
                div3_subdiv2.appendChild(input3);
                div3.appendChild(div3_subdiv2);
                tpform.appendChild(div3);

                let div4 = document.createElement("div");
                let div4_subdiv1 = document.createElement("div");
                div4_subdiv1.className = "row";
                let label4 = document.createElement("label");
                label4.innerHTML = "性别";
                div4_subdiv1.appendChild(label4);
                div4.appendChild(div4_subdiv1);
                let div4_subdiv2 = document.createElement("div");
                div4_subdiv2.className = "row";
                let input4 = document.createElement("input");
                input4.type = "text";
                input4.name = "gender";
                input4.id = "input4";
                input4.value = admin.gender;
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
                input5.name = "phone";
                input5.id = "input5";
                input5.value = admin.phone;
                div5_subdiv2.appendChild(input5);
                div5.appendChild(div5_subdiv2);
                tpform.appendChild(div5);

                let div6 = document.createElement("div");
                let div6_subdiv1 = document.createElement("div");
                div6_subdiv1.className = "row";
                let label6 = document.createElement("label");
                label6.innerHTML = "密码";
                div6_subdiv1.appendChild(label6);
                div6.appendChild(div6_subdiv1);
                let div6_subdiv2 = document.createElement("div");
                div6_subdiv2.className = "row";
                let input6 = document.createElement("input");
                input6.type = "text";
                input6.name = "password";
                input6.id = "input6";
                input6.value = admin.password;
                div6_subdiv2.appendChild(input6);
                div6.appendChild(div6_subdiv2);
                tpform.appendChild(div6);

                let lastRow = document.createElement("div");
                lastRow.className = "row";
                let last1 = document.createElement("input");
                last1.type = "submit";
                last1.className = "updateBtn";
                last1.value = "保存";
                lastRow.appendChild(last1);
                let last2 = document.createElement("input");
                last2.type = "submit";
                last2.className = "deleteBtn";
                last2.value = "删除";
                lastRow.appendChild(last2);
                tpform.appendChild(lastRow);

                let hidde_input = document.createElement("input");
                hidde_input.type = "hidden";
                hidde_input.name = "id";
                hidde_input.id = "id";
                hidde_input.value = admin.id;
                tpform.appendChild(hidde_input);
                tpform.addEventListener('submit', function (event){
                    event.preventDefault();
                    // alert($(this).serialize());
                    $.ajax({
                        url: 'adminAdmin',
                        method: 'post',
                        data: $(this).serialize(),
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                })
                last2.addEventListener('click', function (event) {
                    event.preventDefault();
                    let id=$(this).parent().parent().find("input[name='id']").val();
                    let bodyString = "id=" + id + "&" + "action=delete";
                    // alert(bodyString);
                    $.ajax({
                        url: 'adminAdmin',
                        method: 'post',
                        data: bodyString,
                        success: function (rt) {
                            alert(rt.message);
                            // location.reload();
                        }
                    })
                });
                let outer = document.getElementById('right');
                outer.appendChild(tpform);
            }
        }

    })
});

/**
 * 添加管理员
 */
$("#addAdmin").click(function () {
    $(".content-right").empty();
    let tpform = document.createElement("form");
    tpform.action = "adminAdmin";
    tpform.method = "post";
    tpform.className = "fullForm";

    let div1 = document.createElement("div");
    let div1_subdiv1 = document.createElement("div");
    div1_subdiv1.className = "row";
    let label1 = document.createElement("label");
    label1.innerHTML = "工号(四位)";
    div1_subdiv1.appendChild(label1);
    div1.appendChild(div1_subdiv1);
    let div1_subdiv2 = document.createElement("div");
    div1_subdiv2.className = "row";
    let input1 = document.createElement("input");
    input1.className = "fullInput";
    input1.required = true;
    input1.type = "number";
    input1.name = "snumber";
    input1.id = "input1";
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
    input2.id = "input2";
    input2.className = "fullInput";
    input2.required = true;
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
    input3.id = "input3";
    input3.className = "fullInput";
    input3.required = true;
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
    input4.id = "input4";
    input4.className = "fullInput";
    input4.required = true;
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
    input5.name = "phone";
    input5.id = "input5";
    input5.className = "fullInput";
    input5.required = true;
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
    input7.id = "input7";
    input7.className = "fullInput";
    input7.required = true;
    div7_subdiv2.appendChild(input7);
    div7.appendChild(div7_subdiv2);
    tpform.appendChild(div7);

    let div6 = document.createElement("div");
    let div6_sub = document.createElement("div");
    div6_sub.className = "row";
    let sub = document.createElement("input");
    sub.type = "submit";
    sub.className = "onlySubmit";
    sub.value = "确认添加";
    div6_sub.appendChild(sub);
    div6.appendChild(div6_sub);
    tpform.appendChild(div6);

    sub.addEventListener('click', function (event) {
        event.preventDefault();
        let snumber = document.getElementById('input1').value;
        let name = document.getElementById('input2').value;
        let gender = document.getElementById('input3').value;
        let age = document.getElementById('input4').value;
        let phone = document.getElementById('input5').value;
        let password = document.getElementById('input7').value;
        let dataString = "snumber=" + snumber + "&" + "name=" + name + "&" + "age=" + age + "&" + "gender=" + gender + "&" + "phone=" + phone + "&" + "password=" + password + "&" + "action=insert";
        // alert(dataString);
        $.ajax({
            url: 'adminAdmin',
            type: 'POST',
            data: dataString,
            success: function (body) {
                alert(body.message);
            }
        });
    })
    $(".content-right").append(tpform);
});
