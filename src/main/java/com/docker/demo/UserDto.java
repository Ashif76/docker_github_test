package com.docker.demo;

import com.docker.demo.entity.User;

public class UserDto extends BaseResponse {

    private User content;

    public User getContent() {
        return content;
    }

    public void setContent(final User content) {
        this.content = content;
    }
}
