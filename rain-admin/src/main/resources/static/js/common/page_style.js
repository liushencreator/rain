
$(function(){
    var main_width = $(".main_panel").width();
    var content_width = main_width - 250;

    // 左侧菜单栏占据宽度为 220px， 设置右侧内容面板宽度
    $(".main_panel .site-content").css("width", content_width);

    var screen_height = window.screen.height;
    var min_height = screen_height - 240;
    $(".main_panel .site-content").css("min-height", min_height);
    $(".main_panel .site-tree").css("min-height", min_height);

})
