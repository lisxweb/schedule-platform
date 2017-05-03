/**
 * Created by chen yun  on 2016/12/28.
 */
$(function(){
<<<<<<< HEAD
    //iframe的高度
=======
>>>>>>> 77c2e683df85cce13546fdedddfe262053bb345b
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