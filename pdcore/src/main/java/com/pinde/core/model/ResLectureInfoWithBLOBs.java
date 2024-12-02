package com.pinde.core.model;

public class ResLectureInfoWithBLOBs extends ResLectureInfo {
    private String lectureContent;

    private String lectureTrainPlace;

    private String lectureDesc;

    public String getLectureContent() {
        return lectureContent;
    }

    public void setLectureContent(String lectureContent) {
        this.lectureContent = lectureContent;
    }

    public String getLectureTrainPlace() {
        return lectureTrainPlace;
    }

    public void setLectureTrainPlace(String lectureTrainPlace) {
        this.lectureTrainPlace = lectureTrainPlace;
    }

    public String getLectureDesc() {
        return lectureDesc;
    }

    public void setLectureDesc(String lectureDesc) {
        this.lectureDesc = lectureDesc;
    }
}