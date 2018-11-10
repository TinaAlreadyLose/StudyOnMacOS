$(document).ready(function(){
    var data=undefined;

    /**
     * 加载数据
     */
    $.ajax({
        type:"GET",
        url:"../API/Choosen.json",
        dataType:"json",
        async:false,/*同步请求*/
        success:function (Data) {
            data=Data;
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert("XMLHttpRequest="+XMLHttpRequest+", textStatus="+textStatus+", errorThrown="+errorThrown)
        }
    });
    //alert(data.result);
    //alert(data.data.Webs[0].url);
     function init(data) {

        var Web="<div class='Web' id='Web' ></div>";
         $("#Reference").after(Web);
         var a="<a href='"+data.url+"' id='theA'></a>";
         $("#Web").append(a);
         var image="<img class='image' src='"+data.img+"'/>";
         $("#theA").append(image);
         var text="<div class='text' id='text'></div>"
         $("#theA").append(text);
         var title="<div class='title' id='title'><h1>"+data.title+"</h1></div>";
         $("#text").append(title);
         var describe="<div class='describe' id='describe'><span>"+data.describe+"</span></span></div>";
         $("#text").append(describe);

     }
   //document.write(data.data.Webs.length);
    //js渲染从上往下开始，如果倒序绚烂，append方法会直接绚烂错误
    //想用int了，一直报错
    for(var i=data.data.Webs.length-1;i>=0;i--){
        init(data.data.Webs[i]);
    }


});