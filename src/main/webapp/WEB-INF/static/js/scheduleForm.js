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
        $(this).css("z-index",10).append(str);
    },function(){
        $(this).find(".remarks").remove();
    })
    //右击展示菜单
    document.oncontextmenu = function(e){
        e.preventDefault();
    };
    $(".scheduleForm td").mousedown(function(e){
        var index = $(this).index();
        var L = $(this).siblings().length;
        if(e.button == 2){
            if(index != 0 && index != 1 && index != L && index != L-1 && index != L-2){
                var str = '<div class="rightMenu">';
                str += '<a href="#editHoliday" data-toggle="modal" data-target="#editHoliday">假期编辑</a>';
                str += '<a href="#shiftChange" data-toggle="modal" data-target="#shiftChange">班次变更</a>';
                str += '<a href="#provisionalDisposition" data-toggle="modal" data-target="#provisionalDisposition">临时安排</a>';
                str += '<a href="#minersAbsence" data-toggle="modal" data-target="#minersAbsence">矿工缺勤</a>';
                str += '<a href="#overtimeWork" data-toggle="modal" data-target="#overtimeWork">补班加班</a>';
                str += '</div>';
                $(".scheduleForm td").css("z-index",1).find(".rightMenu").remove();
                $(this).find(".remarks").remove();
                $(this).css("z-index",10).append(str);
                console.log("454545")
            }
        }
    })
    //编辑实际工时
    $(".scheduleForm td.edit").dblclick(function(){
        $(this).html("<input type='text' value='"+ $(this).text() +"'>");
        $(this).find("input").focus();
    })
    $(document).on("blur",".scheduleForm td.edit input",function(){
        $(this).parent().html($(this).val());
    })
    $("*").not('.rightMenu').click(function(){
        $(".scheduleForm td").css("z-index",1).find(".rightMenu").remove();
    })
})
