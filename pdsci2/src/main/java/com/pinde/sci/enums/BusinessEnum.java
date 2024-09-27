package com.pinde.sci.enums;

public interface BusinessEnum extends BaseEnum{

    enum TeachingCertLevelEnum {
        ACADEMY("academy", "院级", ""),
        URBAN("urban", "市级", ""),
        PROVINCE("province", "省级", ""),
        NATION("nation", "国家级", "")
        ;

        private String code;
        private String name;
        private String nameEn;

        TeachingCertLevelEnum(String code, String name, String nameEn) {
            this.code = code;
            this.name = name;
            this.nameEn = nameEn;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getNameEn() {
            return nameEn;
        }
    }
}
