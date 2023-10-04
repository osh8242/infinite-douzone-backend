CREATE USER "HRHR" IDENTIFIED BY "1004";
GRANT CONNECT, RESOURCE TO "HRHR";
ALTER USER "HRHR" QUOTA UNLIMITED ON USERS;
COMMIT;
--------------------------------------------------------
--  DDL 쿼리문 생성일자 - 일요일-10월-01-2023
--------------------------------------------------------
--------------------------------------------------------
--  DDL FOR SEQUENCE EMPFAM_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "HRHR"."EMPFAM_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL FOR SEQUENCE SWSMOTHER_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "HRHR"."SWSMOTHER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 47 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL FOR SEQUENCE DATE_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "HRHR"."DATE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL FOR SEQUENCE SALARYALLOW_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "HRHR"."SALARYALLOW_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 200 CACHE 20  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL FOR SEQUENCE SALARYDEDUCT_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "HRHR"."SALARYDEDUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 500 CACHE 20  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------

--  DDL FOR TABLE CODE
--------------------------------------------------------

CREATE TABLE "HRHR"."CODE"
(
    "PARENT_ID" VARCHAR2(100 BYTE),
    "CODE_ID" VARCHAR2(100 BYTE),
    "CODE_NAME" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

COMMENT ON COLUMN "HRHR"."CODE"."PARENT_ID" IS '분류코드';
   COMMENT ON COLUMN "HRHR"."CODE"."CODE_ID" IS '코드아이디';
   COMMENT ON COLUMN "HRHR"."CODE"."CODE_NAME" IS '코드이름';
   COMMENT ON TABLE "HRHR"."CODE"  IS '코드테이블';
--------------------------------------------------------
--  DDL FOR TABLE DATE
--------------------------------------------------------

CREATE TABLE "HRHR"."DATE"
(	"ALLOW_YEAR" VARCHAR2(100 BYTE),
     "MONTH" VARCHAR2(100 BYTE) ,
     "DATE" VARCHAR2(100 BYTE),
     "DATE_ID" VARCHAR2(100 BYTE),
     "YN_COMPLETE" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";
COMMENT ON COLUMN "HRHR"."DATE"."ALLOW_YEAR" IS '귀속년';
   COMMENT ON COLUMN "HRHR"."DATE"."MONTH" IS '귀속연월';
   COMMENT ON COLUMN "HRHR"."DATE"."DATE" IS '날짜';
   COMMENT ON COLUMN "HRHR"."DATE"."DATE_ID" IS '날짜키';
	 COMMENT ON COLUMN "HRHR"."DATE"."YN_COMPLETE" IS '완료여부';
--------------------------------------------------------
--  DDL FOR TABLE EMP
--------------------------------------------------------

CREATE TABLE "HRHR"."EMP"
(	"CD_EMP" VARCHAR2(100 BYTE) PRIMARY KEY,
     "NM_KRNAME" VARCHAR2(100 BYTE) ,
     "YN_FOR" VARCHAR2(100 BYTE) ,
     "NO_SOCIAL" VARCHAR2(100 BYTE) ,
     "JOB_OK" VARCHAR2(100 BYTE)  DEFAULT 'Y',
     "DA_ENTER" VARCHAR2(100 BYTE)  DEFAULT TO_CHAR(SYSDATE, 'YYYY-MM-DD'),
     "FG_SEX" VARCHAR2(100 BYTE),
     "ABB_NATION" VARCHAR2(100 BYTE) ,
     "YN_RESIDENT" VARCHAR2(100 BYTE) ,
-- "STR_39_1_1" VARCHAR2(100 BYTE),
-- "STR_40_1_1" VARCHAR2(100 BYTE),
     "CD_NATION" VARCHAR2(100 BYTE),
     "ZIP_HOME" VARCHAR2(100 BYTE),
     "ADD_HOME1" VARCHAR2(300 BYTE),
     "ADD_HOME2" VARCHAR2(300 BYTE) ,
     "TEL_HOME1" VARCHAR2(100 BYTE),
     "TEL_HOME2" VARCHAR2(100 BYTE),
     "TEL_HOME3" VARCHAR2(100 BYTE),
     "CEL_EMP1" VARCHAR2(100 BYTE),
     "CEL_EMP2" VARCHAR2(100 BYTE) ,
     "CEL_EMP3" VARCHAR2(100 BYTE) ,
     "EM_EMP" VARCHAR2(300 BYTE) ,
     "ID_MSN" VARCHAR2(100 BYTE),
     "CD_DEPT" VARCHAR2(100 BYTE),
     "CD_OCCUP" VARCHAR2(100 BYTE),
     "RANK_NO" VARCHAR2(100 BYTE) ,
     "CD_SALCLS" VARCHAR2(100 BYTE),
     "CD_FIELD" VARCHAR2(100 BYTE) ,
     "CD_PROJECT" VARCHAR2(100 BYTE) ,
     "DA_RETIRE" VARCHAR2(100 BYTE),
     "CD_BANK" VARCHAR2(100 BYTE) ,
     "NO_BNKACCT" VARCHAR2(100 BYTE) ,
     "NM_BNKOWNER" VARCHAR2(100 BYTE),
--	"MN_6_1_1" VARCHAR2(100 BYTE) ,
     "CD_ACCTIT1" VARCHAR2(100 BYTE),
     "CD_ACCTIT2" VARCHAR2(100 BYTE),
     "FG_PAYSUPPLY" VARCHAR2(100 BYTE) ,
     "MN_MMAVERAGE" VARCHAR2(100 BYTE) ,
     "MN_NATPTARGET" VARCHAR2(100 BYTE) ,
     "MN_NATPSLEV" VARCHAR2(100 BYTE),
     "MN_HOSTARGET" VARCHAR2(100 BYTE),
     "MN_HOS" VARCHAR2(100 BYTE) ,
     "NO_HEALTH" VARCHAR2(100 BYTE),
     "MN_EMPTARGET" VARCHAR2(100 BYTE) ,
     "MN_EMP" VARCHAR2(100 BYTE) ,
     "YN_EMPLOY" VARCHAR2(100 BYTE) ,
     "YN_CEO" VARCHAR2(100 BYTE) ,
     "YN_SANJAE" VARCHAR2(100 BYTE) ,
     "YN_LONGINSUR" VARCHAR2(100 BYTE),
--	"STR_32_1_1" VARCHAR2(100 BYTE),
--	"STR_33_1_1" VARCHAR2(100 BYTE) ,
--	"STR_35_1_1" VARCHAR2(100 BYTE) ,
--	"MN_2_1_1" VARCHAR2(100 BYTE) ,
--	"MN_3_1_1" VARCHAR2(100 BYTE) ,
--	"STR_1_1_1" VARCHAR2(100 BYTE) ,
--	"STR_45_1_1" VARCHAR2(100 BYTE) ,
--	"STR_46_1_1" VARCHAR2(100 BYTE),
--	"STR_47_1_1" VARCHAR2(100 BYTE),
--	"MN_1_1_1" VARCHAR2(100 BYTE),
     "YN_DANIL" VARCHAR2(100 BYTE) ,
     "YN_ICLMAN" VARCHAR2(100 BYTE),
--	"STR_43_1_1" VARCHAR2(100 BYTE) ,
--	"STR_44_1_1" VARCHAR2(100 BYTE),
     "MN_ICLPAY" VARCHAR2(100 BYTE),
--	"STR_34_1_1" VARCHAR2(100 BYTE) ,
     "YN_FORLABOR" VARCHAR2(100 BYTE),
     "YN_UNIT" VARCHAR2(100 BYTE),
     "YN_OVERWORK" VARCHAR2(100 BYTE),
--	"STR_42_1_1" VARCHAR2(100 BYTE),
--	"MN_4_1_1" VARCHAR2(100 BYTE),
     "INCOME_CLASSFICATION" VARCHAR2(100 BYTE) DEFAULT 'empAll',
     "DATE_OFCREATE" VARCHAR2(100 BYTE) DEFAULT '2023-01'
)
    TABLESPACE "USERS" ;

--------------------------------------------------------
--  DDL FOR TABLE EMPADD
--------------------------------------------------------

CREATE TABLE "HRHR"."EMPADD"
(
    "CD_EMP" VARCHAR2(100 BYTE) PRIMARY KEY,
    "NM_CHNAME" VARCHAR2(100 BYTE),
    "NM_ENNAME" VARCHAR2(100 BYTE),
    "NO_SOCIAL" VARCHAR2(100 BYTE),
    "FG_SEX" VARCHAR2(100 BYTE),
    "DA_BIRTH" VARCHAR2(100 BYTE),
    "FG_WEDDING" VARCHAR2(100 BYTE),
    "CD_DEPT" VARCHAR2(100 BYTE),
    "RANK_NO" VARCHAR2(100 BYTE),
    "CD_OFFDUTY" VARCHAR2(100 BYTE),
    "YN_DRAWCONTRACTS" VARCHAR2(100 BYTE),
    "DA_ENTER" VARCHAR2(100 BYTE),
    "DA_RETIRE" VARCHAR2(100 BYTE),
    "TY_EMPLOY" VARCHAR2(100 BYTE) DEFAULT 'PUBLIC',
    "DC_MIDRETIRE" VARCHAR2(100 BYTE),
    "FG_DISORDER" VARCHAR2(100 BYTE),
    "STR_HOBBY" VARCHAR2(100 BYTE),
    "NM_SPECIAL" VARCHAR2(100 BYTE),
    "TY_RELIGION" VARCHAR2(100 BYTE),
    "ZIP_HOME" VARCHAR2(100 BYTE),
    "ADD_HOME1" VARCHAR2(100 BYTE),
    "ADD_HOME2" VARCHAR2(100 BYTE),
    "ZIP_ORGIN" VARCHAR2(100 BYTE),
    "ADD_ORGIN1" VARCHAR2(300 BYTE),
    "ADD_ORGIN2" VARCHAR2(100 BYTE),
    "MN_KEEPGARDEN" VARCHAR2(100 BYTE),
    "MN_KEEPESTATE" VARCHAR2(100 BYTE),
    "FG_OWNHOUSE" VARCHAR2(100 BYTE),
    "TEL_HOME1" VARCHAR2(100 BYTE),
    "TEL_HOME2" VARCHAR2(100 BYTE),
    "TEL_HOME3" VARCHAR2(100 BYTE),
    "CEL_EMP1" VARCHAR2(100 BYTE),
    "CEL_EMP2" VARCHAR2(100 BYTE),
    "CEL_EMP3" VARCHAR2(100 BYTE),
    "TEL_OFFICE1" VARCHAR2(100 BYTE),
    "TEL_OFFICE2" VARCHAR2(100 BYTE),
    "TEL_OFFICE3" VARCHAR2(100 BYTE),
    "DA_MLTBEGIN" VARCHAR2(100 BYTE),
    "TEL_EXTENS" VARCHAR2(100 BYTE),
    "TY_MILIT" VARCHAR2(100 BYTE),
    "WHY_MLTOFF" VARCHAR2(100 BYTE),
    "DA_MLTEND" VARCHAR2(100 BYTE),
    "FG_MLTTYPE" VARCHAR2(100 BYTE),
    "NM_MLTBRANC" VARCHAR2(100 BYTE),
    "STR_MLTDISCH" VARCHAR2(100 BYTE),
    "CD_MLTRANK" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";


-- 외래 키(FK) 설정 및 CASCADE 옵션 추가
ALTER TABLE "HRHR"."EMPADD"
    ADD CONSTRAINT "FK_EMPADD_EMP"
        FOREIGN KEY ("CD_EMP")
            REFERENCES "HRHR"."EMP" ("CD_EMP")
            ON DELETE CASCADE;

--EMP테이블과 중복되는 EMPADD 컬럼의 ON UPDATE CASCADE를 구현하는 트리거
CREATE OR REPLACE TRIGGER "HRHR"."EMP_UPDATE_TRIGGER"
AFTER UPDATE OF NO_SOCIAL, FG_SEX, CD_DEPT, RANK_NO, DA_ENTER, DA_RETIRE ON "HRHR".EMP
    FOR EACH ROW
BEGIN
UPDATE "HRHR".EMPADD
SET
    NO_SOCIAL = :NEW.NO_SOCIAL,
    FG_SEX = :NEW.FG_SEX,
    CD_DEPT = :NEW.CD_DEPT,
    RANK_NO = :NEW.RANK_NO,
    DA_ENTER = :NEW.DA_ENTER,
    DA_RETIRE = :NEW.DA_RETIRE
WHERE
        CD_EMP = :NEW.CD_EMP;
END;

   COMMENT ON COLUMN "HRHR"."EMPADD"."CD_EMP" IS '사원코드';
   COMMENT ON COLUMN "HRHR"."EMPADD"."NM_CHNAME" IS '한자성명';
   COMMENT ON COLUMN "HRHR"."EMPADD"."NM_ENNAME" IS '영문성명';
   COMMENT ON COLUMN "HRHR"."EMPADD"."YN_DRAWCONTRACTS" IS '근로계약서';
   COMMENT ON COLUMN "HRHR"."EMPADD"."FG_WEDDING" IS '결혼여부';
   COMMENT ON COLUMN "HRHR"."EMPADD"."DA_BIRTH" IS '생년월일';
   COMMENT ON COLUMN "HRHR"."EMPADD"."STR_HOBBY" IS '취미';
   COMMENT ON COLUMN "HRHR"."EMPADD"."NM_SPECIAL" IS '특기';
   COMMENT ON COLUMN "HRHR"."EMPADD"."NO_SOCIAL" IS '주민등록번호';
   COMMENT ON COLUMN "HRHR"."EMPADD"."FG_SEX" IS '성별';
   COMMENT ON COLUMN "HRHR"."EMPADD"."CD_DEPT" IS '부서';
   COMMENT ON COLUMN "HRHR"."EMPADD"."RANK_NO" IS '직급';
   COMMENT ON COLUMN "HRHR"."EMPADD"."DA_ENTER" IS '입사년월일';
   COMMENT ON COLUMN "HRHR"."EMPADD"."DA_RETIRE" IS '퇴사년월일';
   COMMENT ON COLUMN "HRHR"."EMPADD"."CD_OFFDUTY" IS '직무';
--------------------------------------------------------
--  DDL FOR TABLE EMPFAM
--------------------------------------------------------

CREATE TABLE "HRHR"."EMPFAM"
(
    "SEQ_VAL" NUMBER DEFAULT "HRHR"."EMPFAM_SEQ"."NEXTVAL",
    "CD_EMP" VARCHAR2(100 BYTE),
    "CD_CALREL" VARCHAR2(100 BYTE),
    "CD_FAMREL" VARCHAR2(100 BYTE),
    "NM_KRNAME" VARCHAR2(100 BYTE),
    "YN_FOR" VARCHAR2(100 BYTE),
    "NO_SOCIAL" VARCHAR2(100 BYTE),
    "FG_SCHOOL" VARCHAR2(100 BYTE),
    "FG_GRADUATION" VARCHAR2(100 BYTE),
    "YN_TOGETHER" VARCHAR2(100 BYTE),
    "YN_LUNARBIR" VARCHAR2(100 BYTE),
    "DA_BIRTH" VARCHAR2(100 BYTE),
    "CD_JOB" VARCHAR2(100 BYTE),
    "NM_KRCOM" VARCHAR2(300 BYTE),
    "CD_OFFPOS" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

ALTER TABLE "HRHR"."EMPFAM"
    ADD CONSTRAINT "PK_EMPFAM" PRIMARY KEY ("CD_EMP", "SEQ_VAL");

ALTER TABLE "HRHR"."EMPFAM"
    ADD CONSTRAINT "FK_EMPFAM_EMPADD" FOREIGN KEY ("CD_EMP")
        REFERENCES "HRHR"."EMPADD" ("CD_EMP")
        ON DELETE CASCADE;

--------------------------------------------------------
--  DDL FOR TABLE EMPLOYEE
--------------------------------------------------------

CREATE TABLE "HRHR"."EMPLOYEE"
(
    "EMPLOYEEID" NUMBER PRIMARY KEY,
    "EMPLOYEENAME" VARCHAR2(255 BYTE),
    "MANAGERID" NUMBER
)
    TABLESPACE "USERS";
--------------------------------------------------------
--  DDL FOR TABLE EMPMENUUSAGE
--------------------------------------------------------

CREATE TABLE "HRHR"."EMPMENUUSAGE"
(
    "CD_EMP" VARCHAR2(100 BYTE) PRIMARY KEY,
    "USE_MENULIST" CLOB
)
    TABLESPACE "USERS"
LOB ("USE_MENULIST") STORE AS SECUREFILE
(
    TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192
);
--------------------------------------------------------
--  DDL FOR TABLE EMPPHOTO
--------------------------------------------------------
CREATE TABLE "HRHR"."EMPPHOTO"
(
    "CD_EMP" VARCHAR2(100 BYTE),
    "NM_PHOTO" VARCHAR2(100 BYTE),
    "FILE_PATH" VARCHAR2(100 BYTE),
    "UUID" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

ALTER TABLE "HRHR"."EMPPHOTO"
    ADD CONSTRAINT "PK_EMPPHOTO" PRIMARY KEY ("CD_EMP");

ALTER TABLE "HRHR"."EMPPHOTO"
    ADD CONSTRAINT "FK_EMPPHOTO_EMPADD"
        FOREIGN KEY ("CD_EMP")
            REFERENCES "HRHR"."EMPADD" ("CD_EMP")
            ON DELETE CASCADE;

CREATE OR REPLACE TRIGGER "HRHR"."EMPPHOTO_INSERT_TRIGGER"
AFTER INSERT ON "HRHR"."EMPADD"
FOR EACH ROW
BEGIN
INSERT INTO EMPPHOTO (CD_EMP) VALUES (:NEW.CD_EMP);
END;

--------------------------------------------------------
--  DDL FOR TABLE MEMBER
--------------------------------------------------------

CREATE TABLE "HRHR"."MEMBER"
(
    "COMPANY_CODE" VARCHAR2(100 BYTE),
    "USER_ID" VARCHAR2(100 BYTE),
    "USER_PWD" VARCHAR2(100 BYTE),
    "EMP_IMG" VARCHAR2(100 BYTE),
    "USER_NAME" VARCHAR2(100 BYTE),
    "EMAIL" VARCHAR2(100 BYTE),
    "PHONE" VARCHAR2(100 BYTE),
    "THEME" VARCHAR2(100 BYTE),
    "SALT" VARCHAR2(100 BYTE)
)

    TABLESPACE "USERS";
--------------------------------------------------------
--  DDL FOR TABLE SAALLOW
--------------------------------------------------------

CREATE TABLE "HRHR"."SAALLOW"
(
    "CD_ALLOW" VARCHAR2(100 BYTE),
    "YN_TAX" VARCHAR2(100 BYTE),
    "NM_ALLOW" VARCHAR2(100 BYTE),
    "SAL_DIVISION" VARCHAR2(100 BYTE),
    "MONTHLY_YN" VARCHAR2(100 BYTE),
    "COMMONLY_YN" VARCHAR2(100 BYTE),
    "NONTAX_LIMIT" VARCHAR2(100 BYTE),
    "NONTAX_DIVISON" VARCHAR2(100 BYTE),
    "ORDER" NUMBER(*,0)
)
    TABLESPACE "USERS";

COMMENT ON COLUMN "HRHR"."SAALLOW"."CD_ALLOW" IS '급여항목코드';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."YN_TAX" IS '과세/비과세';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."NM_ALLOW" IS '급여항목이름';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."SAL_DIVISION" IS '구분(급여, 상여)';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."MONTHLY_YN" IS '월정액 급여 포함여부';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."COMMONLY_YN" IS '통상임금 포함여부';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."NONTAX_LIMIT" IS '비과세항목 한도';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."NONTAX_DIVISON" IS '비과세항목 구분';
   COMMENT ON COLUMN "HRHR"."SAALLOW"."ORDER" IS '순서';
   COMMENT ON TABLE "HRHR"."SAALLOW"  IS '급여항목';
--------------------------------------------------------
--  DDL FOR TABLE SAALLOWPAY
--------------------------------------------------------

CREATE TABLE "HRHR"."SAALLOWPAY"
(
    "CD_ALLOW" VARCHAR2(100 BYTE),
    "ALLOW_PAY" VARCHAR2(100 BYTE),
    "CD_EMP" VARCHAR2(100 BYTE),
    "DATE_ID" VARCHAR2(100 BYTE),
    "YN_TAX" VARCHAR2(100 BYTE)

)
    TABLESPACE "USERS";


COMMENT ON COLUMN "HRHR"."SAALLOWPAY"."CD_ALLOW" IS '급여항목코드';
   COMMENT ON COLUMN "HRHR"."SAALLOWPAY"."ALLOW_PAY" IS '급여항목 지급 금액';
   COMMENT ON COLUMN "HRHR"."SAALLOWPAY"."CD_EMP" IS '사원번호';
   COMMENT ON COLUMN "HRHR"."SAALLOWPAY"."DATE_ID" IS 'DATE KEY';
   COMMENT ON COLUMN "HRHR"."SAALLOWPAY"."YN_TAX" IS '과세여부';

   COMMENT ON TABLE "HRHR"."SAALLOWPAY"  IS '급여_급여항목';
--------------------------------------------------------
--  DDL FOR TABLE SADEDUCT
--------------------------------------------------------

CREATE TABLE "HRHR"."SADEDUCT"
(
    "CD_DEDUCT" VARCHAR2(100 BYTE),
    "NM_DEDUCT" VARCHAR2(100 BYTE),
    "YN_BONUS" VARCHAR2(100 BYTE),
    "YN_SAL" VARCHAR2(100 BYTE),
    "RATE" VARCHAR2(100 BYTE),
    "STATIC" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

COMMENT ON COLUMN "HRHR"."SADEDUCT"."CD_DEDUCT" IS '공제항목 코드';
   COMMENT ON COLUMN "HRHR"."SADEDUCT"."NM_DEDUCT" IS '공제항목 이름';
   COMMENT ON COLUMN "HRHR"."SADEDUCT"."YN_BONUS" IS '상여여부';
   COMMENT ON COLUMN "HRHR"."SADEDUCT"."YN_SAL" IS '급여여부';
	 COMMENT ON COLUMN "HRHR".SADEDUCT."RATE" IS '비율';
	 COMMENT ON COLUMN "HRHR".SADEDUCT."STATIC" IS '고정여부';
   COMMENT ON TABLE "HRHR"."SADEDUCT"  IS '공제항목';


--------------------------------------------------------
--  DDL FOR TABLE SADEDUCTPAY
--------------------------------------------------------

CREATE TABLE "HRHR"."SADEDUCTPAY"
(
    "CD_DEDUCT" VARCHAR2(100 BYTE),
    "ALLOW_PAY" VARCHAR2(100 BYTE),
    "CD_EMP" VARCHAR2(100 BYTE),
    "DATE_ID" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";


COMMENT ON COLUMN "HRHR"."SADEDUCTPAY"."CD_DEDUCT" IS '공제항목,급여항목 코드';
   COMMENT ON COLUMN "HRHR"."SADEDUCTPAY"."ALLOW_PAY" IS '지급 금액';
   COMMENT ON COLUMN "HRHR"."SADEDUCTPAY"."CD_EMP" IS '사원번호';
   COMMENT ON COLUMN "HRHR"."SADEDUCTPAY"."DATE_ID" IS 'DATEID';
   COMMENT ON TABLE "HRHR"."SADEDUCTPAY"  IS '사원별 지급액';

--------------------------------------------------------
--  DDL FOR TABLE INCOMETAX
--------------------------------------------------------

CREATE TABLE "HRHR"."INCOMETAX"
(
    "RATE" VARCHAR2(100 BYTE),
    "PROGRESSIVE_TAX" VARCHAR2(100 BYTE),
    "SAL_MIN" VARCHAR2(100 BYTE),
    "SAL_MAX" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

COMMENT ON COLUMN "HRHR"."INCOMETAX"."RATE" IS '세율 %';
   COMMENT ON COLUMN "HRHR"."INCOMETAX"."PROGRESSIVE_TAX" IS '누진세';
   COMMENT ON COLUMN "HRHR"."INCOMETAX"."SAL_MIN" IS '급여 최소금액';
   COMMENT ON COLUMN "HRHR"."INCOMETAX"."SAL_MAX" IS '급여 최대금액';
   COMMENT ON TABLE "HRHR"."INCOMETAX"  IS '소득세 구간별 세율';

--------------------------------------------------------
--  DDL FOR TABLE SWSM
--------------------------------------------------------

CREATE TABLE "HRHR"."SWSM"
(
    "WITHHOLDING_YEAR" VARCHAR2(100 BYTE),
    "DATE_OF_CREATION" VARCHAR2(100 BYTE),
    "CD_EMP" VARCHAR2(100 BYTE),
    "OTHER_BENEFITS_ITEM" VARCHAR2(100 BYTE),
    "OTHER_BENEFITS_AMOUNT" VARCHAR2(100 BYTE),
    "NM_KRNAME" VARCHAR2(100 BYTE),
    "RESIDENT_STATE" VARCHAR2(100 BYTE),
    "RRN" VARCHAR2(100 BYTE),
    "SALARY_AMOUNT" VARCHAR2(100 BYTE),
    "BONUS_AMOUNT" VARCHAR2(100 BYTE),
    "START_EMP_CONTRACT_PERIOD" VARCHAR2(100 BYTE),
    "END_EMP_CONTRACT_PERIOD" VARCHAR2(100 BYTE),
    "POST_CODE" VARCHAR2(100 BYTE),
    "ADDRESS" VARCHAR2(100 BYTE),
    "JOB_DESCRIPTION" VARCHAR2(100 BYTE),
    "START_BREAK_TIME" VARCHAR2(100 BYTE),
    "END_BREAK_TIME" VARCHAR2(100 BYTE),
    "WORKING_DAY" VARCHAR2(100 BYTE),
    "DAY_OFF" VARCHAR2(100 BYTE),
    "SALARY_TYPE" VARCHAR2(100 BYTE),
    "OTHER_BENEFITS" VARCHAR2(100 BYTE),
    "BONUS_PAYMENT_STATUS" VARCHAR2(100 BYTE),
    "SALARY_PAYMENT_DATE_TYPE" VARCHAR2(100 BYTE),
    "PAYMENT_DATE" VARCHAR2(100 BYTE),
    "PAYMENT_METHOD" VARCHAR2(100 BYTE),
    "EMP_INSURANCE" VARCHAR2(100 BYTE),
    "COMPENSATION_INSURANCE" VARCHAR2(100 BYTE),
    "NATIONAL_PENSION" VARCHAR2(100 BYTE),
    "HEALTH_INSURANCE" VARCHAR2(100 BYTE),
    "ADD_DETAIL" VARCHAR2(100 BYTE),
    "START_WORKTIME" VARCHAR2(100 BYTE),
    "END_WORKTIME" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

--------------------------------------------------------
--  DDL FOR TABLE SWSMOTHER
--------------------------------------------------------

CREATE TABLE "HRHR"."SWSMOTHER"
(
    "OTHER_TYPE" VARCHAR2(100 BYTE),
    "OTHER_MONEY" VARCHAR2(100 BYTE),
    "CD_EMP" VARCHAR2(100 BYTE),
    "SEQ_VAL" VARCHAR2(100 BYTE) DEFAULT "HRHR"."SWSMOTHER_SEQ"."NEXTVAL"
)
    TABLESPACE "USERS";

--------------------------------------------------------
--  DDL FOR TABLE USER
--------------------------------------------------------

CREATE TABLE "HRHR"."USER"
(
    "COMPANY_CODE" VARCHAR2(100 BYTE),
    "USER_ID" VARCHAR2(100 BYTE),
    "USER_PWD" VARCHAR2(100 BYTE)
)
    TABLESPACE "USERS";

-- SWSM EMP TRIGER
CREATE OR REPLACE TRIGGER "HRHR"."TR_SWSM_INSTER_BY_EMP_INSERT"
AFTER INSERT ON "HRHR".EMP
FOR EACH ROW
BEGIN
INSERT INTO "HRHR".SWSM (CD_EMP)
VALUES (:NEW.CD_EMP);
END;

--------------------------------------------------------
--  DATA INSERT
--------------------------------------------------------
-- INSERTING INTO HRHR.CODE
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES (NULL,'ROOT',NULL);
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_ITEMS','SAL_BASIC','기본급');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_ITEMS','SAL_OVERTIME','연장근로');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_ITEMS','SAL_MEAL','식대');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_ITEMS','SAL_RND','연구개발비');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','SAL_ITEMS','급여항목');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','DEDUCT_ITEMS','공제항목');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_DIVISON','SAL','1.급여');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('SAL_DIVISON','BONUS','2.상여');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','SAL_DIVISON','급여구분');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('CAL_CODE','OVER','연장근로수당');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('CAL_CODE','NIGHT','야간수당');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','CAL_CODE','계산코드');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','CAL_CODE','계산코드');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEPT','D005','팅팅팅팀');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_NATION','국민연금');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_HEALTH','건강보험');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_EMPLOY','고용보험');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_LONGTERM','장기요양보험료');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_LOCALINCOME','지방소득세');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEDUCT_ITEMS','DEDUCT_INCOME','소득세');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','DEPT','부서');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEPT','D001','인사팀');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEPT','D002','경영지원팀');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEPT','D003','솔루션팀');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('DEPT','D004','플랫폼팀');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','RANK_NO','직급');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('RANK_NO','RANK_JU','주임');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('RANK_NO','RANK_SE','선임');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('RANK_NO','RANK_CA','책임');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('RANK_NO','RANK_SU','수석');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('RANK_NO','RANK_CEO','사장');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('ROOT','OCCUP','직종');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('OCCUP','OC_001','직종1');
INSERT INTO HRHR.CODE (PARENT_ID,CODE_ID,CODE_NAME) VALUES ('OCCUP','OC_002','직종2');

-- INSERTING INTO HRHR."SADEDUCT"
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEDUCT_NATION', '국민연금', NULL, 'Y', '4.5', 'Y');
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEDUCT_HEALTH_INSURANCE', '건강보험', NULL, 'Y', '3.545', 'Y');
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEDUCT_EMPLOYMENT_INSURANCE', '고용보험', 'Y', 'Y', '12.81', 'Y');
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEUCT_LONGTERM_CARE_INSURANCE', '장기요양보험료', NULL, 'Y', '0.9', 'Y');
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEDUCT_LOCALINCOME', '지방소득세', NULL, 'Y', '소득세 * 0.1', 'Y');
INSERT INTO HRHR.SADEDUCT (CD_DEDUCT, NM_DEDUCT, YN_BONUS, YN_SAL, RATE, STATIC) VALUES('DEDUCT_INCOME', '소득세', NULL, 'Y', '간이세액', 'Y');