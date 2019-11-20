
/**
 * post 请求
 * @param url
 * @param data
 */
function post_json_request(url, data){
    console.log(data)
    var defer = $.Deferred();
    $.ajax({
        url: base + url,
        type: "post",
        data: data,
        contentType: 'application/json;charset=utf-8',
        success: function (result) {
            defer.resolve(result);
        }
    });
    return defer.promise();
}

/**
 * post 请求
 * @param url
 * @param data
 */
function post_args_request(url, data){
    var defer = $.Deferred();
    $.ajax({
        url: base + url,
        type: "post",
        dataType: "json",
        data: data,
        success: function (result) {
            defer.resolve(result);
        }
    });
    return defer.promise();
}


/**
 * post 请求
 * @param url
 * @param data
 */
function post_request(url){
    var defer = $.Deferred();
    $.ajax({
        url: base + url,
        type: "post",
        dataType: "json",
        success: function (result) {
            defer.resolve(result);
        }
    });
    return defer.promise();
}