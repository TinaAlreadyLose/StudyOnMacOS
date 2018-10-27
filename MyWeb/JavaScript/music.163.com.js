$(document).ready(function () {
    var data=undefined;
    $.ajax({
        type:"GET",
        url:"API/music.163.com/网易云热歌榜_18.10.27.json",
        dataType:"json",
        async:false,/*调整为同步请求*/
        success:function (Data) {
            var jsonData = JSON.stringify(Data);// 转成JSON格式
            var result = $.parseJSON(jsonData);// 转成JSON对象
            if(Data) {
                alert("调用成功: "+result.result)
                data=result;
            }
        }
    });
    function init(song,index) {
        var demo="<div class='demo' id='demo' style='background-color: gray'></div>";
        $("#songs").after(demo);

        var  pic="<div class='pic' id='pic' style='background-color: lightgreen'></div>";
        var  name="<div class='name' id='name' style='background-color: snow'></div>";
        var singer="<div class='singer'id='singer' style='background-color: aqua'></div>";
        $("#demo").append(pic);
        $("#demo").append(name);
        $('#demo').append(singer);

       var div_pic="<img class='div_pic'src='"+song.pic+"' rel='"+song.name+"'/>";
        var div_name="<a class='div_name' href='"+song.url+"'><h3>"+song.name+"</h3></a>";
        var div_singer="<h3 class='div_singer'>"+song.singer+"</h3>";
        $("#pic").append(div_pic);
        $("#name").append(div_name);
        $("#singer").append(div_singer);

        var url="<embed class='music'src='"+song.url+"'/>";
    }

    /**
     * 传递两个参数，第一个是数据参数，第二个是当期的下标
     */
    if(data ){
        for(var i=data.data.songs.length-1;i>=0;i--)
        init(data.data.songs[i],i);
    }
});