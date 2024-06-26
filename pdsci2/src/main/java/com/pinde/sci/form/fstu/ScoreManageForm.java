package com.pinde.sci.form.fstu;

import com.pinde.sci.model.mo.FstuScoreMain;

import java.util.List;

public class ScoreManageForm {
    //我的学分
    private FstuScoreMain score;
    //新增我的学分的申请文件
    private List<String> scoreApplyUrlList;

    public FstuScoreMain getScore() {
        return score;
    }

    public void setScore(FstuScoreMain score) {
        this.score = score;
    }

    public List<String> getScoreApplyUrlList() {
        return scoreApplyUrlList;
    }

    public void setScoreApplyUrlList(List<String> scoreApplyUrlList) {
        this.scoreApplyUrlList = scoreApplyUrlList;
    }
}
