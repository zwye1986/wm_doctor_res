alter table res_org_spe add min_recruit_capacity varchar2(20);
alter table res_joint_org add session_number varchar2(24);
alter table RES_ORG_SPE add base_capacity varchar2(20);

update RES_JOINT_ORG
set SESSION_NUMBER = '2023'
where RECORD_STATUS = 'Y';

-- 20240528
create table PDSCI.RES_STANDARD_DEPT (
         standard_dept_flow varchar2(50) primary key,
         standard_dept_code varchar2(50),
         standard_dept_name varchar2(50),
         top_level_dept_code varchar2(50),
         parent_dept_code varchar2(50),
         dept_level varchar2(10),
         dept_level_name varchar2(20),
         dept_status varchar2(2),
         record_status varchar2(2),
         create_time varchar2(20),
         create_user_flow varchar2(50),
         modify_time varchar2(20),
         modify_user_flow varchar2(50)
);

comment on table pdsci.res_standard_dept is '标准科室表';
comment on column pdsci.res_standard_dept.standard_dept_flow is '标准科室主键';
comment on column pdsci.res_standard_dept.standard_dept_code is '标准科室编码';
comment on column pdsci.res_standard_dept.standard_dept_name is '标准科室名称';
comment on column pdsci.res_standard_dept.top_level_dept_code is '最上层标准科室编码';
comment on column pdsci.res_standard_dept.parent_dept_code is '上一层准标科室编码';
comment on column pdsci.res_standard_dept.dept_level is '标准科室层级';
comment on column pdsci.res_standard_dept.dept_level_name is '标准科室层级名称';
comment on column pdsci.res_standard_dept.dept_status is '标准科室状态（是否禁用）';
comment on column pdsci.res_standard_dept.record_status is '标准字段：记录状态';
comment on column pdsci.res_standard_dept.create_time is '标准字段：创建时间';
comment on column pdsci.res_standard_dept.create_user_flow is '标准字段：创建人主键';
comment on column pdsci.res_standard_dept.modify_time is '标准字段：最后修改时间';
comment on column pdsci.res_standard_dept.modify_user_flow is '标准字段：最后修改人主键';

create table PDSCI.RES_SPE_BASE_STD_DEPT (
     spe_base_std_dept_flow varchar2(50) primary key,
     spe_base_id varchar2(50),
     standard_dept_flow varchar2(50),
     rotation_require_status varchar2(2),
     record_status varchar2(2),
     create_time varchar2(20),
     create_user_flow varchar2(50),
     modify_time varchar2(20),
     modify_user_flow varchar2(50)
);

comment on table PDSCI.RES_SPE_BASE_STD_DEPT is '专业基地和标准科室关联表';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.spe_base_std_dept_flow is '专业基地和标准科室关联表主键';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.spe_base_id is '专业基地id';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.standard_dept_flow is '标准科室id';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.rotation_require_status is '轮转状态：1:必轮，2:选轮，0:未选择';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.record_status is '标准字段：记录状态';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.create_time is '标准字段：创建时间';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.create_user_flow is '标准字段：创建人主键';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.modify_time is '标准字段：最后修改时间';
comment on column PDSCI.RES_SPE_BASE_STD_DEPT.modify_user_flow is '标准字段：最后修改人主键';

-- 现网与开发（测试）环境不一致
-- 修改res_base表主键
alter table res_base drop primary key;
alter table  res_base  add constraint  pk_org_year primary key(org_flow, session_number) disable;
-- 加字段
ALTER table pdsci.JSRES_DEPT_CONFIG
    add SCORE_TOPLIMIT varchar2(5);
COMMENT on COLUMN pdsci.JSRES_DEPT_CONFIG.SCORE_TOPLIMIT is '出科考分数上限';

ALTER table pdsci.RES_BASE_SPE_DEPT
    add SESSION_NUMBER varchar2(20);
COMMENT on COLUMN pdsci.RES_BASE_SPE_DEPT.SESSION_NUMBER is '年份';

ALTER table pdsci.RES_BASE_SPE_DEPT_DATA
    add SESSION_NUMBER varchar2(20);
COMMENT on COLUMN pdsci.RES_BASE_SPE_DEPT_DATA.SESSION_NUMBER is '年份';

ALTER table pdsci.RES_BASE_SPE_DEPT_DATA
    add INFO_TYPE varchar2(40);
COMMENT on COLUMN pdsci.RES_BASE_SPE_DEPT_DATA.INFO_TYPE is '指标类型';

alter table PDSCI.RES_SCHEDULE_SCORE
add SUBSTANDARD varchar2(10);
COMMENT on COLUMN pdsci.RES_SCHEDULE_SCORE.SUBSTANDARD is '低于标准';

alter table PDSCI.RES_SCHEDULE_SCORE
    add DEDUCT_POINTS varchar2(10);
COMMENT on COLUMN pdsci.RES_SCHEDULE_SCORE.DEDUCT_POINTS is '扣分';

-- 20240604 基地科室表
CREATE TABLE "PDSCI"."RES_DEPT_REL_STD_DEPT" (
     "DEPT_FLOW" VARCHAR2(50) PRIMARY KEY,
     "DEPT_CODE" VARCHAR2(50),
     "DEPT_NAME" VARCHAR2(50),
     "STANDARD_DEPT_FLOW" VARCHAR2(50),
     "ORG_FLOW" VARCHAR2(50),
     "ORG_NAME" VARCHAR2(50),
     "DEPT_STATUS" VARCHAR2(2),
     "RECORD_STATUS" VARCHAR2(2),
     "CREATE_TIME" VARCHAR2(20),
     "CREATE_USER_FLOW" VARCHAR2(50),
     "MODIFY_TIME" VARCHAR2(20),
     "MODIFY_USER_FLOW" VARCHAR2(50)
);

COMMENT ON TABLE "PDSCI"."RES_DEPT_REL_STD_DEPT" IS '科室信息表（带标准科室，1对1）';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".DEPT_FLOW IS '科室主键';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".DEPT_CODE IS '科室编码';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".DEPT_NAME IS '科室名称';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".ORG_FLOW IS '组织主键';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".STANDARD_DEPT_FLOW IS '标准科室主键';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".ORG_NAME IS '组织名称';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".DEPT_STATUS IS '科室状态：Y（启用），N（禁用）';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".RECORD_STATUS IS '标准字段：记录状态';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".CREATE_TIME IS '标准字段：创建时间';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".CREATE_USER_FLOW IS '标准字段：创建人主键';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".MODIFY_TIME IS '标准字段：最后修改时间';
COMMENT ON COLUMN "PDSCI"."RES_DEPT_REL_STD_DEPT".MODIFY_USER_FLOW IS '标准字段：最后修改人主键';