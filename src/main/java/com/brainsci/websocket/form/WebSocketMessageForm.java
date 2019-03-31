package com.brainsci.websocket.form;

/**
 * ...
 *
 * @author izhx 2018/7/27 11:02
 */

public class WebSocketMessageForm {
    // 属于哪一模块
    private String module;

    // 数据
    private String json;

    public WebSocketMessageForm(String module, String json) {
        this.module = module;
        this.json = json;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
