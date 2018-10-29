$(document).ready(function () {
    var data=undefined;//接收所有数据
    /**
     * 加载数据
     * @param .ajax
     */
    $.ajax({
        type:"GET",
       /* url:"../API/music.163.com/网易云古典音乐榜_18.10.27.json",*/
        url:"http://api.bzqll.com/music/netease/songList?key=579621905&id=71384707",
        dataType:"json",
        async:false,/*调整为同步请求*/
        success:function (Data) {
            var jsonData = JSON.stringify(Data);// 转成JSON格式
            var result = $.parseJSON(jsonData);// 转成JSON对象
            if(Data) {
                //alert("调用成功: "+result.result)
                data=result;
            }
        }
    });

    /**
     * 实现主要歌单的添加
     * @param song  每一个歌的传入
     * @param index
     */
    function init_song(song,index) {
        /**
         * 使用HTML动态生成数据
         * @type {string}
         */
        var demo="<div class='demo' id='demo'></div>";
        $("#top").after(demo);
        /*var a_all="<a class='a_all'id='a_all' href='"+song.url+"'></a>";
        $("#top").append(a_all)*/
        var  pic="<div class='pic' id='pic'></div>";
        var  name="<div class='name' id='name'></div>";
        var singer="<div class='singer'id='singer'></div>";
        $("#demo").append(pic);
        $("#demo").append(name);
        $('#demo').append(singer);
        var div_pic="<img class='div_pic'src='"+song.pic+"' rel='"+song.name+"'/>";
        var div_name="<a class='div_name' href='../datilHTML/music_player.html?id="+song.id+"' target='_blank'><h3>"+song.name+"</h3></a>";
        var div_singer="<h3 class='div_singer'>"+song.singer+"</h3>";
        $("#pic").append(div_pic);
        $("#name").append(div_name);
        $("#singer").append(div_singer);

        /*var url="<audio controls='controls' loop='loop' autoplay='autoplay'src='"+song.url+"'>兄弟该升级浏览器了火狐——在线听；谷歌离线下载</audio>"*/
    }
    function init_top_bar(data) {
        /**
         * 使用JQuery初始化
         */
        var image=$("<img src='"+data.songListPic+"'/>").addClass("top_img");
        $("#top").append(image);
        var describe=$("<p></p>").text(data.songListDescription).addClass("top_describe");
        $("#top").append(describe);
        var classifiy=$("<p></p>").html("<b><i>SUGGEST：Firefox——listen；Chrome——download</i></b>").addClass("top_classify")
        $("#top").append(classifiy);
    }
    /**
     * 加载函数，进行初始化
     * @function init_song()
     */
    if(data ){
        init_top_bar(data.data);
        for(var i=data.data.songs.length-1;i>=0;i--)
            init_song(data.data.songs[i],i);
    }
});