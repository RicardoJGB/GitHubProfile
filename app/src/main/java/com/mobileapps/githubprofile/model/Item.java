package com.mobileapps.githubprofile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("Login")
    @Expose
    private String login;
    @SerializedName("Avatar")
    @Expose
    private String avatarURL;
    @SerializedName("html_url")
    @Expose
    private String html_URL;

    public Item (String login, String avatarURL, String html_URL){
        this.login=login;
        this.avatarURL=avatarURL;
        this.html_URL=html_URL;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getHtml_URL() {
        return html_URL;
    }

    public void setHtml_URL(String html_URL) {
        this.html_URL = html_URL;
    }


}
