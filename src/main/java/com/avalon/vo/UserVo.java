package com.avalon.vo;

import com.avalon.domain.User;

public class UserVo extends User{

    private String loginAction;

    public String getLoginAction() {
        return loginAction;
    }

    public void setLoginAction(String loginAction) {
        this.loginAction = loginAction;
    }
}
