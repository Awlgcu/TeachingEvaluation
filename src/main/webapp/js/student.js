$.ajax({
    type: "get",
    url: "stuEvaluation",
    success: function (body) {
        // alert("成功收到响应,共"+body.length+"名老师");
        let ul = document.getElementById("left-ul");
        for (let teacher of body) {
            let li = document.createElement("li");
            let span = document.createElement("span");
            span.className = "span";
            span.innerHTML = teacher.name;
            span.addEventListener("click", () => spanClick(teacher.tnumber));
            li.appendChild(span);
            ul.appendChild(li);
        }
    }
});

function spanClick(id) {
//根据id请求问题并放在右边
    $.ajax({
        type: "get",
        url: "evaluation",
        data: "id=" + id,
        success: function (body) {
            // alert("第一次"+id);
            if (body.message) {
                // alert(body.message);
                let form = document.getElementById("only-form");
                while (form.firstChild) {
                    form.removeChild(form.firstChild);
                }
                let h1 = document.getElementById("information");
                h1.innerHTML = body.message;
            } else {
                let hh1 = document.getElementById("information");
                hh1.innerHTML = "请开始评价";
                //不要用jquery去获取form???
                let form = document.getElementById("only-form");
                while (form.firstChild) {
                    form.removeChild(form.firstChild);
                }

                var indicators = new Array();
                for (let ev of body) {
                    indicators.push(ev.id);
                    let div = document.createElement("div");
                    let div1 = document.createElement("div");
                    div1.className = "row";
                    let label = document.createElement("label");
                    label.innerHTML = ev.indicator;
                    div1.appendChild(label);
                    let div2 = document.createElement("div");
                    div2.className = "row";
                    let input = document.createElement("input");
                    input.type = "number";
                    input.name = "marks";//名字都一样,在后端得到数组
                    input.placeholder = "请输入评分(0-100)";
                    input.min = "0";
                    input.max = "100";
                    input.required = true;
                    div2.appendChild(input);
                    div.appendChild(div1);
                    div.appendChild(div2);
                    form.appendChild(div);
                }

                // 创建一个隐藏字段,以便把老师tnumber传给后端
                let hidde_input = document.createElement("input");
                hidde_input.type = "hidden";
                hidde_input.name = "tnumber";
                hidde_input.value = id;
                // alert("tnumber:"+JSON.stringify(id));
                form.appendChild(hidde_input);

                let hidde_indicators = document.createElement("input");
                hidde_indicators.type = "hidden";
                hidde_indicators.name = "indicators";
                // hidde_indicators.value = JSON.stringify(indicators);
                hidde_indicators.value = indicators;
                form.appendChild(hidde_indicators);

                let div3 = document.createElement("div");
                let div4 = document.createElement("div");
                div4.className = "row";
                let input = document.createElement("input");
                input.type = "submit";

                div4.appendChild(input);
                div3.appendChild(div4);
                form.appendChild(div3);
                form.appendChild(input);
                form.addEventListener('submit', function(event){
                    event.preventDefault();
                    $.ajax({
                        url: 'evaluation',
                        type: 'POST',
                        data: $(this).serialize(), // 序列化表单数据
                        success: function(body) {
                            alert("提交成功");
                            location.reload();
                        }
                    });
                });
            }
        }
    });
}

$(".span").click(function () {
    $(".span").removeClass("clicked");
    $(this).addClass("clicked");
})