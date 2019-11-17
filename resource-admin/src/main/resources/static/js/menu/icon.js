
/**
 * 设置菜单图标
 */
function menu_icon_setting() {

    var icon_panel = $("#icon-panel").html();
    layer.open({
        type: 1,
        title: false,
        closeBtn: false,
        area: ['990px', '600px'],
        shade: 0.01,
        //设定一个id，防止重复弹出
        id: 'LAY_layuipro',
        btn: ['确定选中', '取消返回'],
        btnAlign: 'c',
        //拖拽模式，0或者1
        moveType: 1,
        shadeClose: true,
        content: icon_panel,
        yes: function(index, layero){
            var i_class = $(".select-icon-li i").attr("class");
            $("#menu_icon_i").removeClass();
            $("#menu_icon_i").addClass(i_class);
            $("input[name='menuIcon']").val(i_class);
            layer.close(index);
        }
    });
};


// 图标面板的选中样式
$("body").on("click", ".site-doc-icon li", function() {
    $(".site-doc-icon li").removeClass();
    $(this).addClass("select-icon-li");
});

// 图标双击事件
$("body").on("dblclick", ".site-doc-icon li", function() {
    var i_class = $(".select-icon-li i").attr("class");
    $("#menu_icon_i").removeClass();
    $("#menu_icon_i").addClass(i_class);
    $("input[name='menuIcon']").val(i_class);
    layer.closeAll();
});