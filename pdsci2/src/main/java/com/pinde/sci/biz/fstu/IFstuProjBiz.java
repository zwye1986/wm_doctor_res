package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuProj;
import com.pinde.sci.model.mo.PubFile;

import java.io.IOException;
import java.util.List;

public interface IFstuProjBiz {
    List<FstuProj> search(FstuProj proj);

    FstuProj findByFlow(String projFlow);

    int save(FstuProj proj);

    int saveProjAndFile(FstuProj proj, List<PubFile> fileList) throws IOException;
}
