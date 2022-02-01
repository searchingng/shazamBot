package com.company.Dto;

public class Option {
    String caption;
    Action[] actions;
    String image;
    String type;
    String listcaption;
    String overflowimage;
    Boolean colouroverflowimage;
    String providername;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getListcaption() {
        return listcaption;
    }

    public void setListcaption(String listcaption) {
        this.listcaption = listcaption;
    }

    public String getOverflowimage() {
        return overflowimage;
    }

    public void setOverflowimage(String overflowimage) {
        this.overflowimage = overflowimage;
    }

    public Boolean getColouroverflowimage() {
        return colouroverflowimage;
    }

    public void setColouroverflowimage(Boolean colouroverflowimage) {
        this.colouroverflowimage = colouroverflowimage;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }
}
