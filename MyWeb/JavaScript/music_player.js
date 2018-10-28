$(document).ready(function () {

    //获取参数
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    function music_player() {
        //添加图片
        var picture="<img class='picture'src='http://api.bzqll.com/music/netease/pic?id="+id+"&key=579621905'/>";
        $("#music_picture").append(picture);
        //添加歌词
        var lyric={"lyrics":"http://api.bzqll.com/music/netease/lrc?id="+id+"&key=579621905"}
        var lyrics="<iframe src='"+lyric.lyrics+"'width='400px'height='700px'></iframe>";
        $("#music_lyrics").append(lyrics);
        //添加H5播放器
        var player="<audio class='player' id='player' src='http://api.bzqll.com/music/netease/url?id="+id+"&key=579621905'controls='controls' loop='loop' autoplay='autoplay'> 兄弟浏览器该更新了</audio>"
        $("#music_player").append(player);
        $("#player").css("width","100%");
    }
    var id = GetQueryString("id");//获取传递的参数
    //alert(id);
    music_player();

});
