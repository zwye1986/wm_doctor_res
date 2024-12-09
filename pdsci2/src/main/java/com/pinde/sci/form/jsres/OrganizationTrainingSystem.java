package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class OrganizationTrainingSystem implements java.io.Serializable {
        /**
         *  基地信息--组织管理--培训制度表单
         */
        private static final long serialVersionUID = -3524388431912961659L;

        private String id;

        private String rulesAndRegulations;

        private String appendix;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getRulesAndRegulations() {
                return rulesAndRegulations;
        }

        public void setRulesAndRegulations(String rulesAndRegulations) {
                this.rulesAndRegulations = rulesAndRegulations;
        }

        public String getAppendix() {
                return appendix;
        }

        public void setAppendix(String appendix) {
                this.appendix = appendix;
        }
}
