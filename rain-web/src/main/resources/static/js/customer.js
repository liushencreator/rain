/**
 * Created by Lenovo on 2018/9/13.
 */
function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}


function background(pc_image,mobile_image){
    var c_height=window.screen.height;
    if(isPC()){
        c_height=c_height*0.857;
        $(".div-all").css("background-image","url(/web/images/"+pc_image);
        $(".div-all").css("background-size","100%");
        $(".div-all").css("background-position","bottom center");
        $(".div-all").css("background-repeat","repeat-y");
    }else{
        c_height=c_height*0.9;
        $(".div-all").css("background-image","url(/web/images/"+mobile_image);
        $(".div-all").css("background-size","130%");
        $(".div-all").css("background-position","top center");
        $(".div-all").css("background-repeat","repeat-y");
    }
    $(".div-all").css("height",c_height+"px");
}
