package com.pinde.sci.model.mo;

public class ResTrainingOpinionWithBLOBs extends ResTrainingOpinion {
    private String opinionUserContent;

    private String opinionReplyContent;

    public String getOpinionUserContent() {
        return opinionUserContent;
    }

    public void setOpinionUserContent(String opinionUserContent) {
        this.opinionUserContent = opinionUserContent;
    }

    public String getOpinionReplyContent() {
        return opinionReplyContent;
    }

    public void setOpinionReplyContent(String opinionReplyContent) {
        this.opinionReplyContent = opinionReplyContent;
    }
}