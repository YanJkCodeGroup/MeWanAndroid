package com.android.wanandroid.module.login;

import java.util.List;

/**
 * @author Mr.Li：lkx
 * @description: I brandished my keyboard and book, vowing to write the world clearly.
 * @date : 2019/10/8 10:22
 */
public class LoginData {
    //      "admin": false,
    //    "chapterTops": [],
    //    "collectIds": [],
    //    "email": "",
    //    "icon": "",
    //    "id": 31435,
    //    "nickname": "lkx",
    //    "password": "",
    //    "publicName": "lkx",
    //    "token": "",
    //    "type": 0,
    //    "username": "lkx"
    public boolean admin;
    public List<?> chapterTops;
    public List<?> collectIds;
    public String email;
    public String icon;
    public int id;
    public String nickname;
    public String password;
    public String publicName;
    public String token;
    public int type;
    public String username;

    @Override
    public String toString() {
        return "LoginData{" +
                "admin=" + admin +
                ", chapterTops=" + chapterTops +
                ", collectIds=" + collectIds +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", publicName='" + publicName + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                '}';
    }
}
