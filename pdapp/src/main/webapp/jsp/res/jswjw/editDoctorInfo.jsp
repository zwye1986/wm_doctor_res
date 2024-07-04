<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8" %>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "baseUrl":"${upload_base_url}",
    "doctorFlow":"${doctor.doctorFlow}",
    "userInfo":
    [{
    "inputId" : "user.userName",
    "inputType" : "text",
    "label" : "姓名",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.userName)}
    },
    {
    "inputId" : "user.sexId",
    "inputType" : "checkbox",
    "label" : "性别",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.sexId)},
    "options" :[{
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    {
    "optionDesc" : "男",
    "optionId" : "Man"
    },
    {
    "optionDesc" : "女",
    "optionId" : "Woman"
    }
    ]
    },{
    "inputId" : "user.userHeadImg",
    "inputType" : "img",
    "label" : "头像",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty user.userHeadImg}">
        "value":""
    </c:if>
    <c:if test="${not empty user.userHeadImg}">
        "value":"${user.userHeadImg}"
    </c:if>
    },{
    "inputId" : "user.cretTypeId",
    "inputType" : "select",
    "label" : "证件类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.cretTypeId)},
    "options" : [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionDesc" : "身份证",
    "optionId" : "01"
    },{
    "optionDesc" : "军官证",
    "optionId" : "02"
    },{
    "optionDesc" : "护照",
    "optionId" : "05"
    },{
    "optionDesc" : "港澳居民来往内地通行证",
    "optionId" : "06"
    },{
    "optionDesc" : "台湾居民来往内地通行证",
    "optionId" : "07"
    }]
    },{
    "inputId" : "user.idNo",
    "inputType" : "text",
    "label" : "证件号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.idNo)}
    },{
    "inputId" : "user.nationId",
    "inputType" : "select",
    "label" : "民族",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.nationId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${userNationEnumList}" var="certificateType" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(certificateType.id)},
        "optionDesc": ${pdfn:toJsonString(certificateType.name)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },
    {
    "inputId" : "user.nationalityId",
    "inputType" : "checkbox",
    "label" : "国籍",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.nationalityId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumNationalityList}" var="nationalityEnum" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
        "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
        <c:if test="${nationalityEnum.dictName eq '中国'}">
            ,"item":[{
            "inputId" : "userResumeExt.locationOfProvince",
            "inputType" : "select",
            "label" : "户口所在地省行政区划",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(userResumeExt.locationOfProvince)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${provinceEnumList}" var="province" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(province.id)},
                "optionDesc": ${pdfn:toJsonString(province.name)}
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            }]
        </c:if>
<%--        <c:if test="${nationalityEnum.dictName ne '中国'}">--%>
<%--            ,"item":[{--%>
<%--            "inputId" : "qtCountry",--%>
<%--            "inputType" : "text",--%>
<%--            "label" : "国家或地区",--%>
<%--            "readonly" : false,--%>
<%--            "required" : true,--%>
<%--            "value" : ${pdfn:toJsonString(user.nationalityName)}--%>
<%--            }]--%>
<%--        </c:if>--%>
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.maritalStatus",
    "inputType" : "select",
    "label" : "婚姻状况",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.maritalStatus)},
    "options" : [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionDesc" : "未婚",
    "optionId" : "1"
    },{
    "optionDesc" : "已婚",
    "optionId" : "2"
    },{
    "optionDesc" : "初婚",
    "optionId" : "3"
    },{
    "optionDesc" : "再婚",
    "optionId" : "4"
    },{
    "optionDesc" : "复婚",
    "optionId" : "5"
    },{
    "optionDesc" : "丧偶",
    "optionId" : "6"
    },{
    "optionDesc" : "离婚",
    "optionId" : "7"
    }]
    },{
    "inputId" : "user.userBirthday",
    "inputType" : "date",
    "label" : "生日",
    "readonly" : false,
    "required" : true,
    "verify": {
    "dateFmt": "yyyy-MM-dd",
    "type": "date"
    },
    "value" : ${pdfn:toJsonString(user.userBirthday)}
    },{
    "inputId" : "user.userPhone",
    "inputType" : "text",
    "label" : "手机",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.userPhone)}
    },{
    "inputId" : "user.userEmail",
    "inputType" : "text",
    "label" : "电子邮箱",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.userEmail)}
    },{
    "inputId" : "doctor.englishGradeExamType",
    "inputType" : "checkbox",
    "label" : "外语等级考试类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(doctor.englishGradeExamType)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumEnglishGradeExamTypeList}" var="nationalityEnum" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
        "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
        <c:if test="${nationalityEnum.dictName ne '未参加外语等级考试' and nationalityEnum.dictName ne '其他语种等级考试'}">
            ,"item":[{
            "inputId" : "doctor.englishAbility",
            "inputType" : "checkbox",
            "label" : "英语能力",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.englishAbility)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumEnglishAbilityList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                <c:if test="${nationalityEnum.dictName ne '未通过'}">
                    ,"item":[{
                    "inputId" : "doctor.englishCertificateNumber",
                    "inputType" : "text",
                    "label" : "外语等级考试证书编号",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(doctor.englishCertificateNumber)}
                    },{
                    "inputId" : "doctor.englishCertificateDate",
                    "inputType" : "date",
                    "label" : "外语等级考试证书取得日期",
                    "readonly" : false,
                    "required" : true,
                    "verify": {
                    "dateFmt": "yyyy-MM-dd",
                    "type": "date"
                    },
                    "value" : ${pdfn:toJsonString(doctor.englishCertificateDate)}
                    }]
                </c:if>
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            }]
        </c:if>
        <c:if test="${nationalityEnum.dictName eq '其他语种等级考试'}">
            ,"item":[{
            "inputId" : "doctor.foreignSkills",
            "inputType" : "text",
            "label" : "外语能力",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.foreignSkills)}
            },{
            "inputId" : "doctor.englishCertificateNumber",
            "inputType" : "text",
            "label" : "外语等级考试证书编号",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.englishCertificateNumber)}
            },{
            "inputId" : "doctor.englishCertificateDate",
            "inputType" : "date",
            "label" : "外语等级考试证书取得日期",
            "readonly" : false,
            "required" : true,
            "verify": {
            "dateFmt": "yyyy-MM-dd",
            "type": "date"
            },
            "value" : ${pdfn:toJsonString(doctor.englishCertificateDate)}
            }]
        </c:if>
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "user.idcardFrontImg",
    "inputType" : "img",
    "label" : "身份证人像面",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty user.idcardFrontImg}">
        "value":""
    </c:if>
    <c:if test="${not empty user.idcardFrontImg}">
        "value":"${user.idcardFrontImg}"
    </c:if>
    },{
    "inputId" : "user.idcardOppositeImg",
    "inputType" : "img",
    "label" : "身份证国徽面",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty user.idcardOppositeImg}">
        "value":""
    </c:if>
    <c:if test="${not empty user.idcardOppositeImg}">
        "value":"${user.idcardOppositeImg}"
    </c:if>
    },{
    "inputId" : "doctor.doctorTypeId",
    "inputType" : "checkbox",
    "label" : "人员类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(doctor.doctorTypeId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${jsResDocTypeEnumList}" var="doctorType" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(doctorType.id)},
        "optionDesc": ${pdfn:toJsonString(doctorType.name)},
        "item":[
        <c:if test="${'Company' eq doctorType.id}">
            {
            "inputId" : "doctor.workOrgId",
            "inputType" : "select",
            "label" : "派送单位",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.workOrgId)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumWorkOrgList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            },{
            "inputId" : "userResumeExt.medicalHeaithOrgId",
            "inputType" : "select",
            "label" : "医疗卫生机构",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(userResumeExt.medicalHeaithOrgId)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                <c:if test="${nationalityEnum.dictName eq '三级医疗机构'}">
                    ,"item":[
                    {
                    "inputId" : "userResumeExt.hospitalAttrId",
                    "inputType" : "select",
                    "label" : "医院属性",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.hospitalAttrId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    },{
                    "inputId" : "userResumeExt.baseAttributeId",
                    "inputType" : "select",
                    "label" : "单位性质",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.baseAttributeId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    },{
                    "inputId" : "userResumeExt.hospitalCategoryId",
                    "inputType" : "select",
                    "label" : "医院类别",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.hospitalCategoryId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]}

                    ]

                </c:if>
                <c:if test="${nationalityEnum.dictName eq '基层医疗卫生机构'}">
                    ,"item":[
                    {
                    "inputId" : "userResumeExt.basicHealthOrgId",
                    "inputType" : "select",
                    "label" : "基层医疗卫生机构",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.basicHealthOrgId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    }

                    ]

                </c:if>
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            }
        </c:if>
        <c:if test="${'CompanyEntrust' eq doctorType.id}">
            {
            "inputId" : "doctor.workOrgId",
            "inputType" : "select",
            "label" : "派送单位",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.workOrgId)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumWorkOrgList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            },{
            "inputId" : "userResumeExt.workUnit",
            "inputType" : "text",
            "label" : "工作单位",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(userResumeExt.workUnit)}
            },{
            "inputId" : "userResumeExt.armyHospital",
            "inputType" : "select",
            "label" : "是否军队医院",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(userResumeExt.armyHospital)},
            "options" :[
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },{
            "optionDesc" : "是",
            "optionId" : "Y"
            },{
            "optionDesc" : "否",
            "optionId" : "N"
            }]
            },{
            "inputId" : "userResumeExt.workUniteCreditCode",
            "inputType" : "text",
            "label" : "工作单位统一信用代码(15或18位)：",
            "readonly" : false,
            "required" : false,
            "value" : ${pdfn:toJsonString(userResumeExt.workUniteCreditCode)}
            },{
            "inputId" : "userResumeExt.medicalHeaithOrgId",
            "inputType" : "select",
            "label" : "医疗卫生机构",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(userResumeExt.medicalHeaithOrgId)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                <c:if test="${nationalityEnum.dictName eq '三级医疗机构'}">
                    ,"item":[
                    {
                    "inputId" : "userResumeExt.hospitalAttrId",
                    "inputType" : "select",
                    "label" : "医院属性",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.hospitalAttrId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    },{
                    "inputId" : "userResumeExt.baseAttributeId",
                    "inputType" : "select",
                    "label" : "单位性质",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.baseAttributeId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    },{
                    "inputId" : "userResumeExt.hospitalCategoryId",
                    "inputType" : "select",
                    "label" : "医院类别",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.hospitalCategoryId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]}

                    ]

                </c:if>
                <c:if test="${nationalityEnum.dictName eq '基层医疗卫生机构'}">
                    ,"item":[
                    {
                    "inputId" : "userResumeExt.basicHealthOrgId",
                    "inputType" : "select",
                    "label" : "基层医疗卫生机构",
                    "readonly" : false,
                    "required" : true,
                    "value" : ${pdfn:toJsonString(userResumeExt.basicHealthOrgId)},
                    "options": [
                    {
                    "optionDesc" : "请选择",
                    "optionId" : ""
                    },
                    <c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra" varStatus="status">
                        {
                        "optionDesc" : ${pdfn:toJsonString(tra.dictName)},
                        "optionId" : ${pdfn:toJsonString(tra.dictId)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                    ]
                    }

                    ]

                </c:if>
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            }
        </c:if>
        <c:if test="${'Graduate' eq doctorType.id}">
            {
            "inputId" : "doctor.workOrgId",
            "inputType" : "select",
            "label" : "派送学校",
            "readonly" : false,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctor.workOrgId)},
            "options": [
            {
            "optionDesc" : "请选择",
            "optionId" : ""
            },
            <c:forEach items="${dictTypeEnumSendSchoolList}" var="nationalityEnum" varStatus="status">
                {
                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},
                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}
                }
                <c:if test="${not status.last }">
                    ,
                </c:if>
            </c:forEach>
            ]
            }
        </c:if>
<%--        <c:if test="${'Social' eq doctorType.id}">--%>
<%--            {--%>
<%--            "inputId" : "userResumeExt.hospitalCategoryId",--%>
<%--            "inputType" : "select",--%>
<%--            "label" : "医院类别",--%>
<%--            "readonly" : false,--%>
<%--            "required" : true,--%>
<%--            "value" : ${pdfn:toJsonString(userResumeExt.hospitalCategoryId)},--%>
<%--            "options": [--%>
<%--            {--%>
<%--            "optionDesc" : "请选择",--%>
<%--            "optionId" : ""--%>
<%--            },--%>
<%--            <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="nationalityEnum" varStatus="status">--%>
<%--                {--%>
<%--                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},--%>
<%--                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}--%>
<%--                }--%>
<%--                <c:if test="${not status.last }">--%>
<%--                    ,--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
<%--            ]--%>
<%--            },--%>
<%--            {--%>
<%--            "inputId" : "userResumeExt.baseAttributeId",--%>
<%--            "inputType" : "select",--%>
<%--            "label" : "单位性质",--%>
<%--            "readonly" : false,--%>
<%--            "required" : true,--%>
<%--            "value" : ${pdfn:toJsonString(userResumeExt.baseAttributeId)},--%>
<%--            "options": [--%>
<%--            {--%>
<%--            "optionDesc" : "请选择",--%>
<%--            "optionId" : ""--%>
<%--            },--%>
<%--            <c:forEach items="${dictTypeEnumBaseAttributeList}" var="nationalityEnum" varStatus="status">--%>
<%--                {--%>
<%--                "optionId": ${pdfn:toJsonString(nationalityEnum.dictId)},--%>
<%--                "optionDesc": ${pdfn:toJsonString(nationalityEnum.dictName)}--%>
<%--                }--%>
<%--                <c:if test="${not status.last }">--%>
<%--                    ,--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
<%--            ]--%>
<%--            }--%>
<%--        </c:if>--%>
        ]}
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]

    },{
    "inputId" : "doctor.computerSkills",
    "inputType" : "text",
    "label" : "计算机能力",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(doctor.computerSkills)}
    },{
    "inputId" : "userResumeExt.telephone",
    "inputType" : "text",
    "label" : "固定电话",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(userResumeExt.telephone)}
    },{
    "inputId" : "user.userAddress",
    "inputType" : "text",
    "label" : "本人通讯地址",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(user.userAddress)}
    },{
    "inputId" : "doctor.emergencyName",
    "inputType" : "text",
    "label" : "紧急联系人",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(doctor.emergencyName)}
    },{
    "inputId" : "doctor.emergencyPhone",
    "inputType" : "text",
    "label" : "紧急联系人手机",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(doctor.emergencyPhone)}
    },{
    "inputId" : "userResumeExt.emergencyAddress",
    "inputType" : "text",
    "label" : "紧急联系人地址",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(userResumeExt.emergencyAddress)}
    },{
    "inputId" : "userResumeExt.armyType",
    "inputType" : "select",
    "label" : "是否军队人员",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.armyType)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${armyTypeEnumList}" var="army" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(army.id)},
        "optionDesc": ${pdfn:toJsonString(army.name)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    }
    ],


    "education":[
    {
    "inputId" : "doctor.isYearGraduate",
    "inputType" : "checkbox",
    "label" : "是否为应届生",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(doctor.isYearGraduate)},
    "options": [ {
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否"
    }]
    },{
    "inputId" : "userResumeExt.isGeneralOrderOrientationTrainee",
    "inputType" : "checkbox",
    "label" : "是否为农村订单定向免费医学毕业生",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isGeneralOrderOrientationTrainee)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否" }]
    },{
    "inputId" : "userResumeExt.isReading",
    "inputType" : "checkbox",
    "label" : "是否在读",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isReading)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.readingCollege",
    "inputType" : "select",
    "label" : "在读学历",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.readingCollege)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumUserEducationList}" var="dict" varStatus="status">{
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}}
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.readingDate",
    "inputType" : "date",
    "label" : "预计毕业时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.readingDate)}
    },{
    "inputId" : "userResumeExt.readingSchoolId",
    "inputType" : "search",
    "label" : "在读院校",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.readingSchoolId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.readingProfessionId",
    "inputType" : "search",
    "label" : "在读专业",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.readingProfessionId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    }



    ,{
    "inputId" : "userResumeExt.isJuniorCollege",
    "inputType" : "checkbox",
    "label" : "是否大专",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isJuniorCollege)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.juniorCollegeFullTime",
    "inputType" : "checkbox",
    "label" : "是否全日制",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeFullTime)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.juniorCollegeGradate",
    "inputType" : "date",
    "label" : "大专毕业时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeGradate)}
    },{
    "inputId" : "userResumeExt.juniorCollegeSchoolId",
    "inputType" : "select",
    "label" : "大专毕业院校",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeSchoolId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.juniorCollegeProfessionId",
    "inputType" : "select",
    "label" : "大专毕业专业",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeProfessionId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.isJuniorCollegeCertificate",
    "inputType" : "checkbox",
    "label" : "是否获得毕业证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isJuniorCollegeCertificate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.juniorCollegeUrl",
    "inputType" : "img",
    "label" : "证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.juniorCollegeUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.juniorCollegeUrl}">
        "value":"${userResumeExt.juniorCollegeUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.juniorCollegeCode",
    "inputType" : "text",
    "label" : "学历证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeCode)}
    },{
    "inputId" : "userResumeExt.juniorCollegeDate",
    "inputType" : "date",
    "label" : "证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.juniorCollegeDate)}
    }]

    },{
    "optionId": "N",
    "optionDesc": "否"}]
    }
    ]
    },{
    "optionId": "N",
    "optionDesc": "否"
    }]
    }

    ,{
    "inputId" : "userResumeExt.isUndergraduate",
    "inputType" : "checkbox",
    "label" : "是否本科",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isUndergraduate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.undergraduateFullTime",
    "inputType" : "checkbox",
    "label" : "是否全日制",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.undergraduateFullTime)},
    "options": [ {
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.graduationTime",
    "inputType" : "date",
    "label" : "本科毕业时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.graduationTime)}
    },{
    "inputId" : "userResumeExt.graduatedId",
    "inputType" : "select",
    "label" : "本科毕业院校",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.graduatedId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.specializedId",
    "inputType" : "select",
    "label" : "本科毕业专业",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.specializedId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.degreeId",
    "inputType" : "select",
    "label" : "学位",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.degreeId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.isCollegeHaveDiploma",
    "inputType" : "checkbox",
    "label" : "是否获得毕业证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isCollegeHaveDiploma)},
    "options": [ {
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.undergraduateUrl",
    "inputType" : "img",
    "label" : "证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.undergraduateUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.undergraduateUrl}">
        "value":"${userResumeExt.undergraduateUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.collegeDiplomaNo",
    "inputType" : "text",
    "label" : "学历证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.collegeDiplomaNo)}
    },{
    "inputId" : "userResumeExt.collegeDiplomaTime",
    "inputType" : "date",
    "label" : "证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.collegeDiplomaTime)}
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.isCollegeDegreeCertificate",
    "inputType" : "checkbox",
    "label" : "是否获得学位证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isCollegeDegreeCertificate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.undergraduateDegreeUrl",
    "inputType" : "img",
    "label" : "学位证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.undergraduateDegreeUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.undergraduateDegreeUrl}">
        "value":"${userResumeExt.undergraduateDegreeUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.collegeDegreeNo",
    "inputType" : "text",
    "label" : "学位证书编号",
    "readonly" : false,

    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.collegeDegreeNo)}
    },{
    "inputId" : "userResumeExt.collegeDegreeTime",
    "inputType" : "date",
    "label" : "学位证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.collegeDegreeTime)}
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    }]
    },{
    "optionId": "N",
    "optionDesc": "否" }]
    }

    ,{
    "inputId" : "userResumeExt.isMaster",
    "inputType" : "checkbox",
    "label" : "是否为硕士",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isMaster)},
    "options": [ {
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.masterDegreeTypeId",
    "inputType" : "select",
    "label" : "硕士学位类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterDegreeTypeId)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "1",
    "optionDesc": "专业型" },{
    "optionId": "2",
    "optionDesc": "学术型"}]
    },{
    "inputId" : "userResumeExt.isMasterFullTime",
    "inputType" : "checkbox",
    "label" : "是否全日制",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isMasterFullTime)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.masterGraSchoolId",
    "inputType" : "search",
    "label" : "硕士毕业院校",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterGraSchoolId)},
    "options":[{
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.masterMajorId",
    "inputType" : "search",
    "label" : "硕士毕业专业",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterMajorId)},
    "options":[{
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(majorsDict.dictId)},
        "optionDesc": ${pdfn:toJsonString(majorsDict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.masterDegreeId",
    "inputType" : "select",
    "label" : "学位",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterDegreeId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>]
    },{
    "inputId" : "userResumeExt.masterGraTime",
    "inputType" : "date",
    "label" : "硕士毕业时间",
    "readonly" : false,
    "required" : true,
    "verify": {
    "dateFmt": "yyyy-MM-dd",
    "type": "date"
    },
    "value" : ${pdfn:toJsonString(userResumeExt.masterGraTime)}
    },{
    "inputId" : "userResumeExt.isMasterHaveDiploma",
    "inputType" : "checkbox",
    "label" : "是否获得毕业证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isMasterHaveDiploma)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是" },{
    "optionId": "N",
    "optionDesc": "否" }]
    },{
    "inputId" : "userResumeExt.masterEducationUrl",
    "inputType" : "img",
    "label" : "学历证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.masterEducationUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.masterEducationUrl}">
        "value":"${userResumeExt.masterEducationUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.masterDiplomaNo",
    "inputType" : "text",
    "label" : "学历证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterDiplomaNo)}
    },{
    "inputId" : "userResumeExt.masterDiplomaTime",
    "inputType" : "date",
    "label" : "学历证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": { "dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.masterDiplomaTime)}
    },{
    "inputId" : "userResumeExt.isMasterDegreeCertificate",
    "inputType" : "checkbox",
    "label" : "是否获得学位证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isMasterDegreeCertificate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[
    {
    "inputId" : "userResumeExt.masterCertificateUrl",
    "inputType" : "img",
    "label" : "学位证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.masterCertificateUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.masterCertificateUrl}">
        "value":"3${userResumeExt.masterCertificateUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.masterDegreeNo",
    "inputType" : "text",
    "label" : "学位证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.masterDegreeNo)}
    },{
    "inputId" : "userResumeExt.masterDegreeTime",
    "inputType" : "date",
    "label" : "学位证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.masterDegreeTime)}
    }
    ]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    },
    {
    "inputId" : "userResumeExt.isDoctor",
    "inputType" : "checkbox",
    "label" : "是否为博士",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isDoctor)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.doctorDegreeTypeId",
    "inputType" : "select",
    "label" : "博士学位类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDegreeTypeId)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "1",
    "optionDesc": "专业型"},{
    "optionId": "2",
    "optionDesc": "学术型"}]
    },{
    "inputId" : "userResumeExt.isDoctorFullTime",
    "inputType" : "checkbox",
    "label" : "是否全日制",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isDoctorFullTime)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"},{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.doctorGraSchoolId",
    "inputType" : "search",
    "label" : "博士毕业院校",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorGraSchoolId)},
    "options":[
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.doctorMajorId",
    "inputType" : "search",
    "label" : "博士毕业专业",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorMajorId)},
    "options":[
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(majorsDict.dictId)},
        "optionDesc": ${pdfn:toJsonString(majorsDict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.doctorDegreeId",
    "inputType" : "select",
    "label" : "学位",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDegreeId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.doctorGraTime",
    "inputType" : "date",
    "label" : "博士毕业时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.doctorGraTime)}
    },{
    "inputId" : "userResumeExt.isDoctorHaveDiploma",
    "inputType" : "checkbox",
    "label" : "是否获得毕业证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isDoctorHaveDiploma)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是"
    ,"item":[{
    "inputId" : "userResumeExt.doctorEducationUrl",
    "inputType" : "img",
    "label" : "学历证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.doctorEducationUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.doctorEducationUrl}">
        "value":"${userResumeExt.doctorEducationUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.doctorDiplomaNo",
    "inputType" : "text",
    "label" : "学历证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDiplomaNo)}
    },{
    "inputId" : "userResumeExt.doctorDiplomaTime",
    "inputType" : "date",
    "label" : "学历证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": { "dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDiplomaTime)}
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    },{
    "inputId" : "userResumeExt.isDoctorDegreeCertificate",
    "inputType" : "checkbox",
    "label" : "是否获得学位证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isDoctorDegreeCertificate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.doctorCertificateUrl",
    "inputType" : "img",
    "label" : "学位证书照片",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.doctorCertificateUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.doctorCertificateUrl}">
        "value":"${userResumeExt.doctorCertificateUrl}"
    </c:if>
    },{
    "inputId" : "userResumeExt.doctorDegreeNo",
    "inputType" : "text",
    "label" : "学位证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDegreeNo)}
    },{
    "inputId" : "userResumeExt.doctorDegreeTime",
    "inputType" : "date",
    "label" : "学位证书取得时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.doctorDegreeTime)}
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"
    }]
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}]
    }


    ,{
    "inputId" : "doctor.certificateNo",
    "inputType" : "text",
    "label" : "最高毕业证书编号",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(doctor.certificateNo)}
    },{
    "inputId" : "userResumeExt.certificateUri",
    "inputType" : "img",
    "label" : "最高毕业证书上传",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.certificateUri}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.certificateUri}">
        "value":"${userResumeExt.certificateUri}"
    </c:if>
    },{
    "inputId" : "doctor.degreeNo",
    "inputType" : "text",
    "label" : "最高学位证书编号",
    "readonly" : false,
    "required" : false,
    "value" : ${pdfn:toJsonString(doctor.degreeNo)}
    },{
    "inputId" : "userResumeExt.degreeUri",
    "inputType" : "img",
    "label" : "最高学位证书上传",
    "readonly" : false,
    "required" : false,
    <c:if test="${empty userResumeExt.degreeUri}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.degreeUri}">
        "value":"${userResumeExt.degreeUri}"
    </c:if>
    },{
    "inputId" : "user.educationId",
    "inputType" : "select",
    "label" : "最高学历",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(user.educationId)},
    "options": [
    {
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumUserEducationList}" var="dict" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dict.dictId)},
        "optionDesc": ${pdfn:toJsonString(dict.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    }
    ],


"doctorInfo":[
    {
    "inputId" : "userResumeExt.isPassQualifyingExamination",
    "inputType" : "checkbox",
    "label" : "是否通过医师资格考试",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isPassQualifyingExamination)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.passQualifyingExaminationTime",
    "inputType" : "date",
    "label" : "通过医师资格考试时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.passQualifyingExaminationTime)}
    },{
    "inputId" : "userResumeExt.isHaveQualificationCertificate",
    "inputType" : "checkbox",
    "label" : "是否获得医师资格证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isHaveQualificationCertificate)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.haveQualificationCertificateTime",
    "inputType" : "date",
    "label" : "取得医师资格证书时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.haveQualificationCertificateTime)}
    },{
    "inputId" : "userResumeExt.physicianQualificationLevel",
    "inputType" : "select",
    "label" : "医师资格级别",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.physicianQualificationLevel)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "zyys",
    "optionDesc":"执业医师"
    },{
    "optionId": "zyzlys",
    "optionDesc":"执业助理医师"
    }
    ]
    },{
    "inputId" : "userResumeExt.physicianQualificationClass",
    "inputType" : "select",
    "label" : "医师资格类别",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.physicianQualificationClass)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "lc",
    "optionDesc":"临床"
    },{
    "optionId": "kq",
    "optionDesc":"口腔"
    },{
    "optionId": "ggws",
    "optionDesc":"公共卫生"
    },{
    "optionId": "zy",
    "optionDesc":"中医"
    }]
    },{
    "inputId" : "userResumeExt.doctorQualificationCertificateCode",
    "inputType" : "text",
    "label" : "医师资格证书编码",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorQualificationCertificateCode)}
    },{
    "inputId" : "userResumeExt.doctorQualificationCertificateUrl",
    "inputType" : "img",
    "label" : "上传证书材料",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.doctorQualificationCertificateUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.doctorQualificationCertificateUrl}">
        "value":"${userResumeExt.doctorQualificationCertificateUrl}"
    </c:if>
    }]
    },{
    "optionId": "N",
    "optionDesc": "否",
    "item":[{
    "inputId" : "userResumeExt.qualificationMaterialId2",
    "inputType" : "select",
    "label" : "成绩单类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.qualificationMaterialId2)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "178",
    "optionDesc":"已通过国家执业医师考试的成绩单"
    },{
    "optionId": "201",
    "optionDesc":"已通过国家助理执业医师考试成绩单"
    }
    ]
    },{
    "inputId" : "userResumeExt.qualificationMaterialId2Url",
    "inputType" : "img",
    "label" : "成绩单附件",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.qualificationMaterialId2Url}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.qualificationMaterialId2Url}">
        "value":"${userResumeExt.qualificationMaterialId2Url}"
    </c:if>
    }]}]
    },{
    "inputId" : "userResumeExt.isHavePracticingCategory",
    "inputType" : "checkbox",
    "label" : "是否获得医师执业证书",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.isHavePracticingCategory)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.havePracticingCategoryTime",
    "inputType" : "date",
    "label" : "取得医师执业证书时间",
    "readonly" : false,
    "required" : true,
    "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
    "value" : ${pdfn:toJsonString(userResumeExt.havePracticingCategoryTime)}
    },{
    "inputId" : "userResumeExt.practicingCategoryLevelId",
    "inputType" : "select",
    "label" : "执业类型",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.practicingCategoryLevelId)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType" varStatus="status">
        {
        "optionId": ${pdfn:toJsonString(dictTypeEnumPracticeType.dictId)},
        "optionDesc": ${pdfn:toJsonString(dictTypeEnumPracticeType.dictName)}
        }
        <c:if test="${not status.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.practicingCategoryScopeId",
    "inputType" : "select",
    "label" : "执业范围",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.practicingCategoryScopeId)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },
    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
        <c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
        <c:forEach items="${applicationScope[dictKey]}" var="scope">
            {
            "optionId": ${pdfn:toJsonString(scope.dictId)},
            "optionDesc": ${pdfn:toJsonString(scope.dictName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
    </c:forEach>
    ]
    },{
    "inputId" : "userResumeExt.doctorPracticingCategoryCode",
    "inputType" : "text",
    "label" : "医师执业证书编码",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.doctorPracticingCategoryCode)}
    },{
    "inputId" : "userResumeExt.doctorPracticingCategoryUrl",
    "inputType" : "img",
    "label" : "上传证书材料",
    "readonly" : false,
    "required" : true,
    <c:if test="${empty userResumeExt.doctorPracticingCategoryUrl}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.doctorPracticingCategoryUrl}">
        "value":"${userResumeExt.doctorPracticingCategoryUrl}"
    </c:if>
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"
    }]
    }]
    },{
    "optionId": "N",
    "optionDesc": "否"}
    ]
    },{
    "inputId" : "userResumeExt.specialCertificationUri",
    "inputType" : "img",
    "label" : "特殊岗位证明材料",
    "readonly" : false,
    "required" : false,
    <c:if test="${empty userResumeExt.specialCertificationUri}">
        "value":""
    </c:if>
    <c:if test="${not empty userResumeExt.specialCertificationUri}">
        "value":"${userResumeExt.specialCertificationUri}"
    </c:if>
    }

    ],



    "westSupportInfo":[{
    "inputId" : "userResumeExt.westernSupportResidents",
    "inputType" : "checkbox",
    "label" : "是否为西部支援行动住院医师",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidents)},
    "options": [{
    "optionDesc" : "请选择",
    "optionId" : ""
    },{
    "optionId": "Y",
    "optionDesc": "是",
    "item":[{
    "inputId" : "userResumeExt.westernSupportResidentsSendProvince",
    "inputType" : "text",
    "label" : "西部支援行动住院医师送出省份",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsSendProvince)}
    },{
    "inputId" : "userResumeExt.westernSupportResidentsSendWorkUnitCode",
    "inputType" : "text",
    "label" : "西部支援行动住院医师送出单位统一社会信用代码",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsSendWorkUnitCode)}
    },{
    "inputId" : "userResumeExt.westernSupportResidentsSendWorkUnit",
    "inputType" : "text",
    "label" : "西部支援行动住院医师送出单位",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsSendWorkUnit)}
    },{
    "inputId" : "userResumeExt.westernSupportResidentsReceiveProvince",
    "inputType" : "text",
    "label" : "西部支援行动住院医师接收省份",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsReceiveProvince)}
    },{
    "inputId" : "userResumeExt.westernSupportResidentsReceiveHospitalCode",
    "inputType" : "text",
    "label" : "西部支援行动住院医师接收省份培训基地(医院)统一社会信用代码：",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsReceiveHospitalCode)}
    },{
    "inputId" : "userResumeExt.westernSupportResidentsReceiveHospital",
    "inputType" : "text",
    "label" : "西部支援行动住院医师接收省份培训基地(医院)",
    "readonly" : false,
    "required" : true,
    "value" : ${pdfn:toJsonString(userResumeExt.westernSupportResidentsReceiveHospital)}
    }]},{
    "optionId": "N",
    "optionDesc": "否"}]
    }]


</c:if>
}