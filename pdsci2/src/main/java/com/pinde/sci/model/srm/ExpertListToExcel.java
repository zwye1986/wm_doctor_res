package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmExpertProj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by www.0001.Ga on 2016.9.20.
 */
public class ExpertListToExcel implements Serializable {

    private static final long serialVersionUID = 540420160920095566L;

    private String num;
    private PubProj pubProj;
    private List<SrmExpertProj> expertProjList;
    private String scoreCount;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public PubProj getPubProj() {
        return pubProj;
    }

    public void setPubProj(PubProj pubProj) {
        this.pubProj = pubProj;
    }

    public List<SrmExpertProj> getExpertProjList() {
        return expertProjList;
    }

    public void setExpertProjList(List<SrmExpertProj> expertProjList) {
        this.expertProjList = expertProjList;
    }

    public String getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(String scoreCount) {
        this.scoreCount = scoreCount;
    }
}
