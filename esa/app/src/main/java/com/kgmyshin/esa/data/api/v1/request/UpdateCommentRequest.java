/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1.request;

import com.google.gson.annotations.SerializedName;

public final class UpdateCommentRequest {

    @SerializedName("body_md")
    private final String bodyMd;
    @SerializedName("user")
    private final String user;

    public UpdateCommentRequest(String bodyMd, String user) {
        this.bodyMd = bodyMd;
        this.user = user;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public String getUser() {
        return user;
    }
}
