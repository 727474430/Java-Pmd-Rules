package com.raindrop.pmd.checks.comment;


enum EnumMustHaveCommentCheck {

    OK(200),
    /* Error code */
    ERROR(500),
    // Fail code
    FAIL(400);

    private int code;

    EnumMustHaveCommentCheck(int code) {
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

