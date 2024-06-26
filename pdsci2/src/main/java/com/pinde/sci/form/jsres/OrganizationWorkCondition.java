package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class OrganizationWorkCondition implements Serializable {
        /**
         *  基地信息--组织管理--工作情况表单
         */
        private static final long serialVersionUID = -3524388431912961659L;

        private String id;

        private String fileName;

        private String appendix;

        public String getFileName() {
                return fileName;
        }

        public void setFileName(String fileName) {
                this.fileName = fileName;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getAppendix() {
                return appendix;
        }

        public void setAppendix(String appendix) {
                this.appendix = appendix;
        }
}
