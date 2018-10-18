function Prompt(){
    var name=prompt("请输入你的姓名","")
    var sex=prompt("请输入您的性别","男")
    if(name!=null&&name!="")
    {
        if(sex=="男"){
            var str=name+"先生您好"
            alert(str)
        }
        else if(sex=="女"){
            var str=name+"女士您好"
            alert(str)
        }
        else alert("错误")
    }

}