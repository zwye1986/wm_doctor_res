/*
Navicat Oracle Data Transfer
Oracle Client Version : 19.6.0.0.0

Source Server         : 江苏西医
Source Server Version : 120100
Source Host           : 192.168.2.17:1521
Source Schema         : PDSCI

Target Server Type    : ORACLE
Target Server Version : 120100
File Encoding         : 65001

Date: 2020-08-14 10:59:53
*/


-- ----------------------------
-- Table structure for LOCAL_DOCTOR_LUNZHUAN_DATA
-- ----------------------------
CREATE TABLE "PDSCI"."LOCAL_DOCTOR_LUNZHUAN_DATA" (
"RECORD_FLOW" VARCHAR2(50 BYTE) NOT NULL ,
"K_ID" VARCHAR2(50 BYTE) NULL ,
"PID" VARCHAR2(50 BYTE) NULL ,
"MONTH_DATE" VARCHAR2(50 BYTE) NULL ,
"IS_CONTAIN" VARCHAR2(50 BYTE) DEFAULT 'N'  NULL ,
"CREATE_TIME" VARCHAR2(100 BYTE) NULL ,
"ORG_FLOW" VARCHAR2(32 BYTE) NULL ,
"NOT_GRADUATE" VARCHAR2(32 BYTE) DEFAULT 'Y'  NULL ,
"GRADUATE" VARCHAR2(32 BYTE) DEFAULT 'Y'  NULL ,
"K_LEVEL" VARCHAR2(50 BYTE) NULL ,
"SPE_ID" VARCHAR2(50 BYTE) NULL ,
"SPE_NAME" VARCHAR2(50 BYTE) NULL ,
"TRAIN_SUM" VARCHAR2(50 BYTE) NULL ,
"WRITE_SUM" VARCHAR2(50 BYTE) NULL ,
"DATAAUDIT_SUM" VARCHAR2(50 BYTE) NULL ,
"AUDITSCALE" VARCHAR2(50 BYTE) NULL ,
"AVEWRITE_SUM" VARCHAR2(50 BYTE) NULL ,
"AVEAUDITSCALE" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Indexes structure for table LOCAL_DOCTOR_LUNZHUAN_DATA
-- ----------------------------

-- ----------------------------
-- Checks structure for table LOCAL_DOCTOR_LUNZHUAN_DATA
-- ----------------------------
ALTER TABLE "PDSCI"."LOCAL_DOCTOR_LUNZHUAN_DATA" ADD CHECK ("RECORD_FLOW" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table LOCAL_DOCTOR_LUNZHUAN_DATA
-- ----------------------------
ALTER TABLE "PDSCI"."LOCAL_DOCTOR_LUNZHUAN_DATA" ADD PRIMARY KEY ("RECORD_FLOW");
