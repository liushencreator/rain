
var form = layui.form;
layui.use('form', function(){
    form.render();

    //监听提交
    form.on('submit(saveMenu)', function(data){
        var field_json = JSON.stringify(data.field);
        console.log("json :" + field_json);
        $.when(post_json_request("/admin/menu/save_config.html", field_json)).done(function(result){
        });
        return false;
    });
});

// layui 树结构
layui.use('tree', function () {
    var tree = layui.tree;
    $.when(post_request("/admin/menu/menu_tree_config.html")).done(function(result){
        tree.render({
            elem: '#menu_tree_config',  //绑定元素
            // edit: ['add', 'update', 'del'],
            showLine: false,
            // onlyIconControl: true,
            accordion: true,
            data: result.map.menu_tree,
            click: function(obj){
                show_menu_config(obj.data.id);
            }
        });
    });
});

/**
 * 显示菜单配置
 * @param id
 */
function show_menu_config(id){
    $("#menu_icon_i").removeClass();

    // 渲染数据
    var data = {'id': id};
    $.when(post_args_request("/admin/menu/get_menu_config.html", data)).done(function(result){
        var menu_config = result.map.menu_config;

        $("input[name='id']").val(menu_config.id);
        $("input[name='menuName']").val(menu_config.menuName);
        $("input[name='menuUrl']").val(menu_config.menuUrl);
        $("#parentId").val(menu_config.parentId);
        $("input[name='sort']").val(menu_config.sort);
        $("input[name='menuIcon']").val(menu_config.menuIcon);
        $("#menu_icon_i").addClass(menu_config.menuIcon);
        form.render();
    });
}

/**
 * 加载一级菜单
 */
$(function(){
    $.when(post_request("/admin/menu/first_level_menu.html")).done(function(result){
        var option_nodes = '<option value="0">无</option>';
        $.each(result.map.first_level_menu, function(index, item){
            option_nodes += '<option value="' + item.id + '">' + item.menuName + '</option>';
        });
        $("#parentId").append(option_nodes);
        form.render();
    });
});