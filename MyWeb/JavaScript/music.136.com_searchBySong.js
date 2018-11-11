$(document).ready(function () {
   $("#click").click(function () {
       var search=$("#search").val();
      //alert(search);
       if(search){
           $.ajax({
               type:"GET",
               url:"https://api.bzqll.com/music/netease/search?key=579621905&s="+search+"&type=song&limit=100&offset=0",
               dataType:"json",
               async:false,
               success:function (Data) {
                  var data=Data.data;
                if(Data.result){
                    function init(data) {
                        var a="<a href='../datilHTML/music_player.html?id="+data.id+"' target='_blank'><div id='a'></div></a>"
                        $("#refer").after(a);
                        var song="<div class='song' id='song'></div>"
                        $("#a").append(song);

                        var pic="<div class='pic'id='pic' ></div>"
                        var name="<div class='name'id='name' ></div>"
                        var singer="<div class='singer' id='singer'></div>"
                        $("#song").append(pic);
                        $("#song").append(name);
                        $("#song").append(singer);
                        var truly_pic="<img class='truly_pic' id='truly_pic'src='"+data.pic+"' rel='歌曲照片'/>";
                        var truly_name="<h3 class='truly_name' id='truly_name'>"+data.name+"</h3>";
                        var truly_singer="<h3 class='truly_singer' id='truly_singer'>"+data.singer+"</h3>";
                        $("#pic").append(truly_pic);
                        $("#name").append(truly_name);
                        $("#singer").append(truly_singer);
                    }
                        for(var i=data.length-1.;i>0;i--)
                            init(data[i]);







                }else alert("您输入的内容不合法请重新输入")
               }
           })
       }else alert("请输入内容")
   });
})