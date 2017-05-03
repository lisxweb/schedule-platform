/**
 * Created by chen yun  on 2016/12/28.
 */
$(function(){
    //iframe的高度
    calendar_h();
    $(window).resize(function(){
        calendar_h();
    })
})
function calendar_h() {
    var h = $("body").height() - 60;
    var w = $("body").width() - $(".tab-content").width();
    $("#myiframe").width(w-2).height(h);
}