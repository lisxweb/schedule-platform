/**
 * Created by chen yun  on 2016/12/28.
 */
$(function(){
    //iframe的高度
    calendar_h();
    $(window).resize(function () {
        calendar_h();
    })
    //顶部下拉菜单点击事件
    $(".header .dropdown").click(function(){
        if($(this).hasClass("open")){
            $(this).parent().css("z-index",2);
        }else{
            $(this).parent().css("z-index",12);
        }
    })
})
function calendar_h() {
    var h = $("body").height() - 60;
    var w = $("body").width() - $(".tab-content").width();
    $("#myiframe").width(w-2).height(h);
}