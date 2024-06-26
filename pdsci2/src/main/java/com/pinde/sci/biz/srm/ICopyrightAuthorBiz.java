package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchCopyrightAuthor;

import java.util.List;

public interface ICopyrightAuthorBiz {

	void editAuthor(SrmAchCopyrightAuthor copyrightAuthor);

	List<SrmAchCopyrightAuthor> searchAuthorList(SrmAchCopyrightAuthor author);

    List<String> getCopyrightFlowByAuthor(SrmAchCopyrightAuthor author);
}
