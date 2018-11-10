function login() {
    var username =document.getElementById("username");
    var password=document.getElementById("password");
    var error=document.getElementById("error_box");
    if(username.value=="admin"&&password.value==123456){
       //alert("登入成功");
       window.open("datilHTML/Choosen.html","_blank");
    }else if(username.value.length>20||username.value.length<5){
       alert("用户名在5~20位之间");
    }else if(password.value.length>20||password.value.length<5){
        alert("登入密码为5~2zhzhhijian");
    }else alert("位置错误");
}