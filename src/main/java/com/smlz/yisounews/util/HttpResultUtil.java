package com.smlz.yisounews.util;

import com.smlz.yisounews.entity.HttpResult;

public class HttpResultUtil {
    public static HttpResult success(Object object) {
        return new HttpResult(200,"请求成功",object);
    }
    public static HttpResult success() {
        return new HttpResult(200,"请求成功");
    }
    public static HttpResult failure(){
        return new HttpResult(400,"请求失败");
    }
    public static HttpResult failure(String error){
        return new HttpResult(400,error);
    }
}
