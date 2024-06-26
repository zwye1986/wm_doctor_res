package com.pinde.sci.form.czyyjxres;



import java.io.Serializable;
import java.util.List;

public class SpeExtForm implements Serializable {
    private List<SpeForm> speExts;

    public SpeExtForm() {

    }
    public SpeExtForm(List<SpeForm> speExts) {
        this.speExts = speExts;
    }

    public List<SpeForm> getSpeExts() {
        return speExts;
    }

    public void setSpeExts(List<SpeForm> speExts) {
        this.speExts = speExts;
    }
}
