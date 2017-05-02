/**
 * Created by jsychen on 2017/4/28.
 */
$(function(){
//绿色、黄色区域鼠标悬浮显示备注
    $(".scheduleForm").find(".bgGreen,.bgYellow").hover(function(){
        var str = '<div class="remarks">';
        str += '<p class="head">假/矿工</p>';
        str += '<p class="con">2017年4月11日</p>';
        str += '<p class="con">备注：因个人原因请假一天</p>';
        str += '</div>';
        $(this).css("z-index",100).append(str);
    },function(){
        $(this).css("z-index",1).find(".remarks").remove();
    })
    //右击展示菜单
    document.oncontextmenu = function(e){
        //e.preventDefault();
    };
    $(".scheduleForm td").dblclick(function(e){
        //if(e.button == 2){
            var str = '<div class="rightMenu">';
            str += '<a href="#editHoliday" data-toggle="modal" data-target="#editHoliday">假期编辑</a>';
            str += '<a href="#shiftChange" data-toggle="modal" data-target="#shiftChange">班次变更</a>';
            str += '<a href="#provisionalDisposition" data-toggle="modal" data-target="#provisionalDisposition">临时安排</a>';
            str += '<a href="#minersAbsence" data-toggle="modal" data-target="#minersAbsence">矿工缺勤</a>';
            str += '<a href="#overtimeWork" data-toggle="modal" data-target="#overtimeWork">补班加班</a>';
            str += '</div>';
        //}
        $(".scheduleForm td").find(".rightMenu").remove();
        $(this).css("z-index",10).append(str);
    })
    /*$("*").not('.rightMenu').click(function(){
        console.log("666");
        $(".scheduleForm td").removeAttr("style");
        $(".scheduleForm td").find(".rightMenu").remove();
    })*/
    $(document).on("click",".rightMenu a",function(){
        var target = $(this).attr("href").substring(1);
        console.log(target);
    })
})
