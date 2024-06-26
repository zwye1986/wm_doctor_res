package com.pinde.sci.model.fstu;

import com.pinde.sci.model.mo.InxColumn;
import com.pinde.sci.model.mo.InxInfo;

public class FstuInfoExt extends InxInfo {
    /**
     *
     */
    private static final long serialVersionUID = -213115772691048695L;
    /**
     * 所属栏目
     */
    private InxColumn column;

    public InxColumn getColumn() {
        return column;
    }

    public void setColumn(InxColumn column) {
        this.column = column;
    }

}
