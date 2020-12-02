package com.benwunet.home.ui.fragment;


import android.view.View;

public class CardItem {

    private String title;
    private String content;
    private boolean isVip;
    private int background;
    private View.OnClickListener listener;

    public CardItem(String title, String text) {
        this.title = title;
        content = text;
    }

    public CardItem(String title, String content, int background) {
        this.title = title;
        this.content = content;
        this.background = background;
    }

    public CardItem(String title, String content, boolean isVip, int background) {
        this.content = content;
        this.title = title;
        this.isVip = isVip;
        this.background = background;
    }

    public CardItem(String title, String content, boolean isVip, int background, View.OnClickListener listener) {
        this.content = content;
        this.title = title;
        this.isVip = isVip;
        this.background = background;
        this.listener = listener;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getBackground() {
        return background;
    }

    public boolean isVip() {
        return isVip;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
