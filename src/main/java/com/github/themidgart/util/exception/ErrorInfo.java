package com.github.themidgart.util.exception;

public class ErrorInfo {
    private String url;
    private ErrorType type;

    private String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;

        this.details = details;
    }
}
