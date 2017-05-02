/**
 * Created by chen yun  on 2016/7/8.
 */
$(function () {
    load();
    $(window).resize(function(){
        load();
    })
    //刷新功能
    $(".btn-refresh").click(function () {
        window.location.reload();//刷新当前页面
    })
    //左侧菜单点击时间
    $(".panel a").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    })
})
function load(){
    var h = $("body").height();
    if($("body>.wrapper").siblings().length == 0){
        $("body>.wrapper").height(h - 40);
    }
}