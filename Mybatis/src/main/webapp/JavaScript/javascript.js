$(function () {
    var availableTags = [
        "Amazon Parrot",
        "Angelfish",
    "Bulldog" ,
    "Chihuahua",
    "Dalmation" ,
    "Finch",
    "Golden Retriever",
    "Goldfish",
    "Iguana" ,
    "Koi",
    "Labrador Retriever",
    "Manx" ,
    "Persian",
    "Poodle",
    "Rattlesnake",
    "Tiger Shark"
    ];
    $("#Search_input").autocomplete({source:availableTags});

    $(".spinner").spinner({min:1});

    $(".spinner").on("blur focus",function () {
        $(".spinner").each(function () {
            if (this.value == "")
                $(this).val(1);
        })
        var param = "array=";
        var num = "";
        var i = 0;
        $(".spinner").each(function () {
            param  = param + this.value + "a" ;
            num = num + this.value + "a";
        })
        $.get('changeNumber',param);
        var single = "";
        $(".single_price").each(function () {
            single = single + $(this).html();
        })
        single = single.split("$");
        num = num.split("a");
        i = 1;
        var sub_total = 0;
        $(".total_price").each(function () {
            $(this).html("$" + parseFloat(num[i-1]) * parseFloat(single[i]));
            sub_total = sub_total + parseFloat(num[i-1]) * parseFloat(single[i]);
            i++;
        })
        $("#sub_total").text("$"+sub_total);
    })

    $(".ui-button-icon").on("click",function () {
        $(".spinner").each(function () {
            if (this.value == "")
                $(this).val(1);
        })
        var param = "array=";
        var num = "";
        var i = 0;
        $(".spinner").each(function () {
            param  = param + this.value + "a" ;
            num = num + this.value + "a";
        })
        $.get('changeNumber',param);
        var single = "";
        $(".single_price").each(function () {
            single = single + $(this).html();
        })
        single = single.split("$");
        num = num.split("a");
        i = 1;
        var sub_total = 0;
        $(".total_price").each(function () {
            $(this).html("$" + parseFloat(num[i-1]) * parseFloat(single[i]));
            sub_total = sub_total + parseFloat(num[i-1]) * parseFloat(single[i]);
            i++;
        })
        $("#sub_total").text("$"+sub_total);

    })

    $("button").on('click',function (e) {
        e.preventDefault();
        var itemid = "workingItemId=" + this.id;
        $.get('removeItem',itemid, function (data) {
            $("#sub_total").text(data);
        })
        var id = "#tr_" + this.id;
        $(id).fadeOut(500);
    })

    $("#re-password").on('blur',function () {
        var new_password = document.getElementById("new_password").value;
        var re_password = document.getElementById("re-password").value;
        var info = document.getElementById("re-password_info");
        if (new_password != re_password){
            info.style.display = "block";
        }else{
            info.style.display = "none";
        }
    })

    $("#checkbox_banner").on('click',function () {
        if ($(this).prop("checked")){
            $("#banner_img").show();
        } else {
            $("#banner_img").hide();
        }
    })

    $("#register_re_password").on('blur',function () {
        var new_password = document.getElementById("register_password").value;
        var re_password = document.getElementById("register_re_password").value;
        var info = document.getElementById("re-password_info");
        if (new_password != re_password){
            info.style.display = "block";
        }else{
            info.style.display = "none";
        }
    })

    $("#register_username").on('blur',function () {
        var username = document.getElementById("register_username").value;
        var param = "username_data=" + username;
        var info = document.getElementById("warning");
        if (username == ""){
            $("#warning").text("Please enter your username!!");
            info.style.display = "block";
        } else {
            $.get('checkUserName', param, function (data) {
                if (data == "no") {
                    info.style.display = "none";
                } else if (data == "yes") {
                    $("#warning").text("Username has exited!");
                    info.style.display = "block";
                }
            })
        }
    })

    $("#sign_on_username").on('blur',function () {
        var username = document.getElementById("sign_on_username").value;
        var param = "username_data=" + username;
        var info = document.getElementById("warning");
        if (username == ""){
            $("#warning").text("Please enter your username!!");
            info.style.display = "block";
        } else {
            $.get('checkUserName', param, function (data) {
                if (data == "no") {
                    $("#warning").text("Username not exits!");
                    info.style.display = "block";
                } else if (data == "yes") {
                    info.style.display = "none";
                }
            })
        }
    })

    $("#check_code_input").on('blur',function () {
        var check_code = document.getElementById("check_code_input").value;
        var param = "check_code="  + check_code;
        if (check_code == ""){
            $("#warning").text("Please enter the checkcode!!");
            document.getElementById("warning").style.display = "block";
        } else {
            $.get('check_checkcode',param,function (data) {
                if (data == "no") {
                    $("#warning").text("Wrong Checkcode!!!");
                    document.getElementById("warning").style.display = "block";
                } else if (data == "yes") {
                    document.getElementById("warning").style.display = "none";
                }
            })
        }
    })

    $("#checkcode_img").on('click',function () {
        document.getElementById("checkcode_img").src = "JavaScript/CheckCode.jsp?" + new Date().getTime();
    })

    $("#main_image").hide().delay(200).fadeIn(400);

    $('li').hide().each(function (index) {
        $(this).delay(500 * index).fadeIn(500);
    });

    $('tr').hide().each(function (index) {
        $(this).delay(100 * index).fadeIn(700);
    });
})