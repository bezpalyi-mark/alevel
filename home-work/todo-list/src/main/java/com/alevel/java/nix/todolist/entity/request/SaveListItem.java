package com.alevel.java.nix.todolist.entity.request;

public class SaveListItem {
    private String text;

    private boolean done;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
