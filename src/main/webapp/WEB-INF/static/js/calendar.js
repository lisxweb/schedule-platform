/**
 * create by: zhangboya on  16/7/28
 */

$(function () {
    //设置外层容器的宽度
    $(".slider").width($("body").width() - 20);
    //设置某一个date的宽度
    $(".date").width(($(".slider").width() - 100) / 4);
    //设置ul的宽度
    $(".calendar_slider").width($(".date").width() * 6 + 150).css("left", -$(".date").width() / 2 + "px");
    //获取恒定的left移动值
    var left = $(".date").width() / 2;
    //当窗体改变时 监听元素宽度的变化
    $(window).resize(function () {
        "use strict";
        $(".slider").width($("body").width() - 20);
        $(".date").width(($(".slider").width() - 100) / 4);
        $(".calendar_slider").width($(".date").width() * 6 + 150).css("left", -$(".date").width() / 2 + "px");
        //获取恒定的left移动值
        left = $(".date").width() / 2;
    })

    //存储用户选择的年份,默认为当前
    var year = new Date().getFullYear();
    //存储当前的月份,默认为当前
    var month = new Date().getMonth() + 1;
    //向 #dropdownMenu1 中添加文本节点
    $("#dropdownMenu1").prepend(year + "年");
    //向 #dropdownMenu2 中添加文本节点
    $("#dropdownMenu2").prepend(month + "月");
    //变量用于存放即将添加的 年份和月份
    var changeYear;
    var changeMonth;
    const NOW_Y = new Date().getFullYear();
    const NOW_M = new Date().getMonth() + 1;
    //页面开始加载时
    arrPush(month, year);
    //新建一个数组用于保存页面的显示
    var arr = [];
    //页面初始化
    DrawPage();
    //设置公用字符串
    var strPlanlistdata = ' <ul class="plan-list-data">' +
        ' <li class="data-catalog">' +
        '<p>' +
        '<b class="float-left">1.闸机维修</b>' +
        '<span class="float-right">2016-6-29 15:35</span>' +
        '</p>' +
        '<p>' +
        '<span class="float-left">西直门站B口闸机维修</span>' +
        '<a href="javascript:;" class="blue-font float-right">详情</a>' +
        '</p>' +
        '</li>' +
        '<li class="data-catalog"><p>' +
        '<b class="float-left">1.闸机维修</b>' +
        '<span class="float-right">2016-6-29 15:35</span>' +

        '</p><p><span class="float-left">西直门站B口闸机维修</span>' +
        '<a href="javascript:;" class="blue-font float-right">详情</a> ' +
        ' </p> </li> </ul>';
    //新建计划模板字符串
    var strnewPlan = "<div class='new-plan'>" +
        "<a href='#' class='blue-font'>新建计划</a>" +
        "</div>";

    //当点击左侧按钮时 执行click 函数
    $(".slider .icon-arrow-left").click(function () {
        "use strict";
        //没有执行动画时 才执行事件
        if (!$(".calendar_slider").is(":animated")) {
            if (month == 12) {
                month = 1;
                ++year;
            } else {
                ++month;
            }
            changeYear = arrPush(month, year)[arrPush(month, year).length - 1][1];
            changeMonth = arrPush(month, year)[arrPush(month, year).length - 1][0];
            Add("leftHand", changeYear, changeMonth);
            var last_child = $(".calendar_slider .date:last-child");
            if (last_child.find(".year").text().indexOf(NOW_Y.toString()) != -1 && (last_child.find(".month").text().indexOf(NOW_M.toString()) != -1)) {
                last_child.find(".panelTitle").addClass("orange");
                last_child.append($(strPlanlistdata));
            } else {
                last_child.append($(strnewPlan))
            }
            $(".calendar_slider").animate({"left": -left - 2 * left - 10 + "px"}, 500, function () {
                $(".calendar_slider").css("left", -left + "px");
                $(".calendar_slider .date").eq(0).remove();
            });
            $("#dropdownMenu1").text($(".date").eq(3).find(".year").text() + "年").append($('<span class="caret"></span>'));
            $("#dropdownMenu2").text($(".date").eq(3).find(".month").text()).append($('<span class="caret"></span>'));
        }
    })
    //当点击右侧 按钮时 执行click 事件
    $(".slider .icon-arrow-right").click(function () {
        "use strict";
        if (!$(".calendar_slider").is(":animated")) {
            if (month == 1) {
                month = 12;
                --year;
            } else {
                --month;
            }
            changeMonth = arrPush(month, year)[0][0];
            changeYear = arrPush(month, year)[0][1];
            Add("rightHand", changeYear, changeMonth);
            var first_child = $(".calendar_slider .date:first-child");
            if (first_child.find(".year").text().indexOf(NOW_Y.toString()) != -1 && (first_child.find(".month").text().indexOf(NOW_M.toString()) != -1)) {
                first_child.find(".panelTitle").addClass("orange");
                first_child.append($(strPlanlistdata));
            } else {
                first_child.append($(strnewPlan))
            }
            $(".calendar_slider").css("left", -left - 2 * left + "px");
            $(".calendar_slider").animate({"left": -left}, 500, function () {
                $(".calendar_slider").children(".date:last-child").remove();
            });
            $("#dropdownMenu1").text($(".date").eq(2).find(".year").text() + "年").append($('<span class="caret"></span>'));
            $("#dropdownMenu2").text($(".date").eq(2).find(".month").text()).append($('<span class="caret"></span>'));
        }
    })
    //添加元素的方法
    function Add(position, changeYear, changeMonth) {
        "use strict";
        var str = "<div class='date' style='width:" + 2 * left + "px'>";
        str += "<div class='datePanel'>";
        str += "<div class='panelTitle'>";
        str += "<h1 class='month'>";
        str += changeMonth;
        str += "月</h1><span class='year'>";
        str += changeYear;
        str += "</span></div></div>";
        str += "</div>";
        if (position == "leftHand") {
            $(".calendar_slider").append($(str));
        }
        if (position == "rightHand") {
            $(".calendar_slider").prepend($(str));
        }
    }

    //设置 .dropdown-menu 中的 li点击时执行的事件
    $(".dropdown-menu>li").click(function () {
        $(this).parent().prev().text($(this).text()).append($('<span class="caret"></span>'))
        var year = Number($("#dropdownMenu1").text().replace("年", ""));
        var month = Number($("#dropdownMenu2").text().replace("月", ""));
        var arr = arrPush(month, year);

        //绘制页面的元素
        $(".date").empty().each(function (index) {
            "use strict";
            //console.log(arr)
            var initYear = arr[index][1];
            var initMonth = arr[index][0];
            $(this).append("<div class='datePanel'><div class='panelTitle'><h1 class='month'>" + initMonth + "月</h1><span class='year'>" + initYear + "</span></div></div>");
            if (initYear == NOW_Y && initMonth == NOW_M) {
                $(this).find(".panelTitle").addClass("orange");
                $(this).append($(strPlanlistdata));
            } else {
                $(this).append($(strnewPlan))
            }
        })
    })
    //取得数据加载方法
    function arrPush(month, year) {
        arr = [];
        month == 1 && (arr.push([11, year - 1], [12, year - 1], [month, year], [month + 1, year], [month + 2, year]));
        month == 2 && (arr.push([12, year - 1], [month - 1, year], [month, year], [month + 1, year], [month + 2, year]));
        month == 12 && (arr.push([month - 2, year], [month - 1, year], [month, year], [1, year + 1], [2, year + 1]));
        month == 11 && (arr.push([month - 2, year], [month - 1, year], [month, year], [month + 1, year], [1, year + 1]));
        (month > 2 && month < 11) && (arr.push([month - 2, year], [month - 1, year], [month, year], [month + 1, year], [month + 2, year]));
        return arr;
    }

    //页面初始化的方法
    function DrawPage() {
        "use strict";
        var getArr = arrPush(month, year);
        $(".calendar_slider .date").each(function (index) {
            $(this).append($("<div class='datePanel'><div class='panelTitle'><h1 class='month'>" + getArr[index][0] + "月</h1><span class='year'>" + getArr[index][1] + "</span></div></div>"))
        })
    }

    appendDate(month, year);
    //向页面绘制数据的方法
    function appendDate(month, year) {
        "use strict";
        $(".date").each(function () {
                var Ycontent = $(this).find(".year").text();
                var Mcontent = $(this).find(".month").text();
                if (Ycontent.indexOf(year.toString()) != -1 && (Mcontent.indexOf(month.toString()) != -1)) {
                    $(this).find(".panelTitle").addClass("orange");
                    $(this).append($(strPlanlistdata));
                } else {
                    $(this).append($(strnewPlan))
                }
            }
        )
    }
})
