package com.next.jiangzh.film.controller.common;

import lombok.Data;

public final class TraceUtil {

    private TraceUtil(){}

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void initThread(String userId){
        threadLocal.set(userId);
        System.out.println("TraceUtil:存入用户id：" + threadLocal.get());
    }

    public static String getUserId(){
        return threadLocal.get();
    }
    public static void removeUserId(String userId){
        System.out.println("TraceUtil:删除userId："+threadLocal.get());
        threadLocal.remove();
    }

//    @Data
//    class TraceUserInfo{
//        private String userId;
//        private String userName;
//    }
}
