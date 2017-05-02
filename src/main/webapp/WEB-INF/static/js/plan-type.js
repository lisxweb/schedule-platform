/**
 * create by: zhangboya on  16/8/2
 */
$(function () {
    "use strict";
    var status = 1;
    var md = "一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月".split("_");
    var now = new Date();
    var str;
    var days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var arr = [];
    var wd = ["第一周", "第二周", "第三周", "第四周", "第五周", "第六周"];
    var year_change;
    var month_change;
    var static_year;
    $(document).click(function(event){
        $(".week_month_plan_timepicker").css("display","none")
        //重新初始化 year_chang 和month-change
        year_change =now.getFullYear();
        month_change = now.getMonth()+1;
    })
    $(".datetimepicker").click(function(){
        event.stopPropagation()
    })
    $(".week_month_plan_timepicker").addClass("week-map")
    $("#time-choose").val(SlotString())
    InitWeek();
    //计划类型选择时间的不同形式
    $(".select_date").change(function () {
        var op_val = $(this).find("option:selected").val();
        var next_formLine = $(this).parents(".form_line").next();
        str = now.getMonth() > 9 ? now.getMonth() : "0" + (now.getMonth() + 1);
        if (op_val == 1) {//周计划
            status = 1;
            next_formLine.removeClass("time-slot");
            $(".week_month_plan").show().siblings("div").hide();
            $(".week_month_plan_timepicker").addClass("week-map")
            InitWeek();//默认传今天  //初始化为周
            str = SlotString();//默认为今天
            $("#time-choose").val(str)
        } else if (op_val == 2) {//月计划
            status = 0;
            $("#time-choose").val(now.getFullYear() + "-" + str)
            next_formLine.removeClass("time-slot");
            $(".week_month_plan").show().siblings("div").hide();
            $(".week_month_plan_timepicker").removeClass("week-map")
            InitMonth()
        } else {
            $(".week_month_plan_timepicker").removeClass("week-map")
            next_formLine.addClass("time-slot");
            $(".auto_plan").show();
            $(".auto_plan").siblings("div").hide();
            $(".start_time").find("input").val("起始日期");
            $(".end_time").find("input").val("结束日期");
        }
    })
    //初始化月份
    function InitMonth(year) {
        var year = year || new Date().getFullYear()
        var month = Number($("#time-choose").val().slice(5, 7))-1;
        $(".center").text(year);
        $('.picker-body').empty();
        md.forEach(function (val) {
            $('.picker-body').append('<li class="month">' + val + '</li>');
        })

        $(".month:eq(" + new Date().getMonth() + ")").addClass("active");
        $(".month:eq(" + month + ")").addClass("active").siblings().removeClass("active");
    }

    //初始化 年
    function InitYear(year) {//默认出现的时间开始点是2012年 默认展示9个
        var str;
        static_year = year||2012;
        year_change=static_year;
        $(".picker-body").empty();
        for (var i = 0; i < 12; i++) {
            $(".picker-body").append($('<li class="year">' + year_change++ + '</li>'));
        }
        str = $(".year").eq(0).text() + "-" + $(".year").last().text()
        $(".center").text(str);
        $(".year").each(function () {
            //$(this).text() == now.getFullYear() && $(this).addClass("active");
            $(this).text() == $("#time-choose").val().slice(0, 4) && $(this).addClass("active").siblings().removeClass("active")
        })

    }

    //初始化周但是初始化周和月份不同需要判定一个月有几周所以得传参数
    function InitWeek(year,month) {
        var year = year || new Date().getFullYear();
        var month = month || new Date().getMonth() + 1;
        var date_now = $("#time-choose").val().slice(8,10);
        str = month > 9 ? month : "0" + month;
        $(".center").text(year + "-" + str);
        $('.picker-body').empty();
        var crr = justfyDay(year, month);
        //遍历数组
        for (var i = 0; i < crr.length; i++) {
            $(".picker-body").append($('<li class="week">' + wd[i] + '</li>'))
        }
        // 判断输入框时间在第几周
        for (var key in crr) {
            if (crr[key].toString().indexOf(date_now) != -1) {
                $(".week:eq(" + key + ")").addClass("active");
            }
        }
    }

    //判断选择的月份有多少天并将日期打散成周计划
    function justfyDay(year, month) {
        var brr = [];
        arr = [];
        var count = 0;//作用是计数
        //闰年
        days[1] = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? 29 : 28;
        //判断每一月的一号是星期几,那么 每个月的一号和紧邻的星期日就是一个周段
        var newDate = new Date(year + "-" + month + "-" + 1).getDay();
        count = 7 - newDate + 1;
        for (var i = 1; i <=days[month - 1]; i++) {//把这个月的日子都分散成组
            i > 9 ? brr.push(i) : brr.push("0" + i);
            if (brr.length == count) {
                arr.push(brr);
                count = 7;
                brr = []
            } else if (i == days[month - 1]) {
                arr.push(brr)
            }
        }
        return arr;
    }
    //返回时间段的字符串 此方法只用于开始的初始化时间
    function SlotString(year, month, date) {
        var year = year || now.getFullYear();
        var month = month || now.getMonth() + 1;
        month=month>9?month:"0"+month;
        var date = date || now.getDate();
        // var str = $(".center").text();
        var arr = justfyDay(year, month);//判断这一年的这一个月有多少天并返回数组
        for (var key in arr) {//判断传过来的日子在数组的第几周
            if (arr[key].indexOf(date) != -1) {
                str =""+year+month+arr[key][0] + year+month + arr[key][arr[key].length - 1]
                str = str.replace(/-/g, "").replace(/(\d{4})(\d{2})(\d{2})(\d{4})(\d{2})(\d{2})/, "$1.$2.$3-$4.$5.$6");
            }
        }
        return str;

    }

    SlotString()
    //给月份中的li 增加点击事件
    $(".picker-body").on("click", "li", function (event) {
        str = $(".center").text();
        $(this).addClass("active").siblings("li").removeClass("active");
        //此时分为两种情况 第一种情况为点击后直接将值赋值给表单，第二种情况就是跳转到周初始化
        if (status == 0) {//当为第一种情况
            str += $(this).index() + 1 > 9 ? ("-" + ($(this).index() + 1)) : ("-0" + ($(this).index() + 1))
            $("#time-choose").val(str);
            $(".week_month_plan_timepicker").hide()
        } else if (status == 1) {//当点击周计划时，将状态改为1 ，此状态表示，当我选择那一周时直接将这一周所对应的天给到表单中
            //获取选定第几周的时间段
            var year = str.slice(0, 4);
            var month = str.slice(5, 7);
            arr = justfyDay(year, month);//返回本月日子数组
            str += arr[$(this).index()][0] + str + arr[$(this).index()][arr[$(this).index()].length - 1];
            str = str.replace(/-/g, "").replace(/(\d{4})(\d{2})(\d{2})(\d{4})(\d{2})(\d{2})/, "$1.$2.$3-$4.$5.$6");
            $("#time-choose").val(str);
            $(".week_month_plan_timepicker").hide()
        } else if (status == 2) {//这种情况为 点击月了以后有点击了选择年份 之后再选择月份
            //获取到点击的月份
            year_change = $(this).text();
            //遍历
            InitMonth(year_change);
            status = 0;
        } else if (status == 3) {//这种情况为 当点击了周 然后又点击了月份
            month_change = $(this).index() + 1;
            InitWeek(year_change, month_change);
            year_change = $(".center").val().slice(0, 4);
            status = 4
        } else if (status == 4) {//这种情况是点击周后点再点月份 然后在点击周 后直接消失
            year_change = $(".center").text().slice(0, 4);
            Handle.call(this);
            // $(this).sublings().removeClass("active");
        } else if (status == 5) {//这种情况是 点击了年份
            year_change = $(this).text();
            InitMonth(year_change);
            status = 6;
        } else if (status == 6) {//初始化周 再初始化 月 点击了年 初始化月 再点击月份
            month_change = $(this).index() + 1;
            InitWeek(year_change, month_change);
            status = 7;
        } else if (status == 7) {
            Handle.call(this);
        }
    })
    function Handle() {
        var arr = justfyDay(year_change, month_change);
        var month = month_change > 9 ? month_change : "0" + month_change;
        var str = year_change + month + arr[$(this).index()][0] + year_change + month + arr[$(this).index()][arr[$(this).index()].length - 1];
        str = str.replace(/(\d{4})(\d{2})(\d{2})(\d{4})(\d{2})(\d{2})/, "$1.$2.$3-$4.$5.$6");
        $("#time-choose").val(str)
        $(".week_month_plan_timepicker").hide()
    }
    //点击 .custom执行事件
    $(".custom").click(function () {
        $(".week_month_plan_timepicker").show()
        if($(".week_month_plan_timepicker").hasClass("week-map")){
            var year = $("#time-choose").val().slice(0,4);
            var month = Number($("#time-choose").val().slice(5,7));
            InitWeek(year,month);
        }else{
            InitMonth(year_change);
        }
    })
    //给.center 绑定click事件
    $(".center").click(function () {
        if ($(".week_month_plan_timepicker").is(".week-map") && $(this).text().length == 4) {
            InitYear();
            status = 5;
        } else if ($(this).text().length == 4) {//当内容的长度为4时 例如2016 则进行跳转
            InitYear();
            status = 2;
        } else if ($(this).text().length == 7) {//当内容长度为7时 例如2016-08 则进行跳转
            InitMonth()
            status = 3;
        }
    })
    //给 .week_month_plan_timepicker 种的 .float-left  绑定click 事件
    $(".week_month_plan_timepicker .float-left ").click(function(){
        if ($(".center").text().length == 4) {//选定月份时
            year_change = Number($(".center").text())-1;
            InitMonth(year_change)
        }else if($(".center").text().length == 7){//选定周时
            var year = Number($(".center").text().slice(0,4));
            var month= Number($(".center").text().slice(5,7));
            month--;
            if(month==0){
                month=12;
                year--;
            }
            InitWeek(year,month)
            $(".week").removeClass("active");
        }else if($(".center").text().length ==9){
            static_year-=12;
            InitYear(static_year);
        }
    })
    //给 .week_month_plan_timepicker 种的 .float-right 绑定click 事件
    $(".week_month_plan_timepicker .float-right ").click(function(){
        if ($(".center").text().length == 4) {//选定月份时
            year_change = Number($(".center").text())+1;
            InitMonth(year_change)
        }else if($(".center").text().length == 7){//选定周时
            var year = Number($(".center").text().slice(0,4));
            var month= Number($(".center").text().slice(5,7));
            month++;
            if(month==13){
                month=1;
                year++;
            }
            InitWeek(year,month)
            $(".week").removeClass("active");
        }else if($(".center").text().length ==9){
            static_year+=12;
            InitYear(static_year);
        }
    })
})