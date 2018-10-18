
function Document(){
var modifyday=new Date(document.lastModified)
var year=modifyday.getFullYear()
var month=modifyday.getMonth()+1
var date=modifyday.getDate()
document.write("<h2>本文档最后一次修改事件"+year+"年"+month+"月"+date+"日</h2>")
document.bgColor="black"
document.fgcolor="white"
document.linkColor="color"
document.alinkColor="white"
document.vlinkColor="yellow"
}