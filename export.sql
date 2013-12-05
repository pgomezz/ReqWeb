--------------------------------------------------------
--  File created - Quinta-feira-Dezembro-05-2013   
--------------------------------------------------------
DROP TABLE "ACTOR" cascade constraints;
DROP TABLE "ACTOR_USE_CASE" cascade constraints;
DROP TABLE "ALTERNATIVE_FLOWS" cascade constraints;
DROP TABLE "ALTRANREQ_ROLE" cascade constraints;
DROP TABLE "ALTRANREQ_USER" cascade constraints;
DROP TABLE "BUSINESS_CATEGORY" cascade constraints;
DROP TABLE "CLIENT" cascade constraints;
DROP TABLE "DOCUMENT" cascade constraints;
DROP TABLE "FUNCTIONAL_REQUIREMENT" cascade constraints;
DROP TABLE "FUNCTIONAL_REQ_DEPENDENCE" cascade constraints;
DROP TABLE "NON_FUNCTIONAL_REQUIREMENT" cascade constraints;
DROP TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" cascade constraints;
DROP TABLE "ORGANIZATION" cascade constraints;
DROP TABLE "PRIVILEGE" cascade constraints;
DROP TABLE "PROJECT" cascade constraints;
DROP TABLE "PROJECT_USER" cascade constraints;
DROP TABLE "ROLE_PRIVILEGE" cascade constraints;
DROP TABLE "SCOPE_DEFINITION_INTERACTION" cascade constraints;
DROP TABLE "USE_CASE" cascade constraints;
DROP TABLE "USE_CASE_DEPENDENCE" cascade constraints;
DROP SEQUENCE "SEQ_ACTOR";
DROP SEQUENCE "SEQ_ALTERNATIVE_FLOWS";
DROP SEQUENCE "SEQ_BUSINESS_CATEGORY";
DROP SEQUENCE "SEQ_CLIENT";
DROP SEQUENCE "SEQ_DOCUMENT";
DROP SEQUENCE "SEQ_FUNCTIONAL_REQUIREMENT";
DROP SEQUENCE "SEQ_NON_FUNCTIONAL_REQUIREMENT";
DROP SEQUENCE "SEQ_PRIVELEGE";
DROP SEQUENCE "SEQ_PROJECT";
DROP SEQUENCE "SEQ_ROLE";
DROP SEQUENCE "SEQ_SCOPE_DEF_INTERACTION";
DROP SEQUENCE "SEQ_USER";
DROP SEQUENCE "SEQ_USE_CASE";
--------------------------------------------------------
--  DDL for Sequence SEQ_ACTOR
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_ACTOR"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_ALTERNATIVE_FLOWS
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_ALTERNATIVE_FLOWS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_BUSINESS_CATEGORY
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_BUSINESS_CATEGORY"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_CLIENT
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_CLIENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_DOCUMENT
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_DOCUMENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_FUNCTIONAL_REQUIREMENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 241 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_NON_FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_NON_FUNCTIONAL_REQUIREMENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 161 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_PRIVELEGE
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_PRIVELEGE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_PROJECT
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_PROJECT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 181 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_ROLE
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_ROLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_SCOPE_DEF_INTERACTION
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_SCOPE_DEF_INTERACTION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_USER
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_USER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence SEQ_USE_CASE
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_USE_CASE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Table ACTOR
--------------------------------------------------------

  CREATE TABLE "ACTOR" ("ID_ACTOR" NUMBER, "NAME" VARCHAR2(50), "DESCRIPTION" CLOB)
/
--------------------------------------------------------
--  DDL for Table ACTOR_USE_CASE
--------------------------------------------------------

  CREATE TABLE "ACTOR_USE_CASE" ("ID_ACTOR" NUMBER, "ID_USE_CASE" NUMBER)
/
--------------------------------------------------------
--  DDL for Table ALTERNATIVE_FLOWS
--------------------------------------------------------

  CREATE TABLE "ALTERNATIVE_FLOWS" ("ID_ALTERNATIVE_FLOWS" NUMBER, "DESCRIPTION" CLOB, "ID_USE_CASE" NUMBER)
/
--------------------------------------------------------
--  DDL for Table ALTRANREQ_ROLE
--------------------------------------------------------

  CREATE TABLE "ALTRANREQ_ROLE" ("ID_ROLE" NUMBER, "TYPE" VARCHAR2(20), "DESCRIPTION" VARCHAR2(250))
/
--------------------------------------------------------
--  DDL for Table ALTRANREQ_USER
--------------------------------------------------------

  CREATE TABLE "ALTRANREQ_USER" ("ID_USER" NUMBER, "NAME" VARCHAR2(50), "PASSWORD" VARCHAR2(88), "USERNAME" VARCHAR2(20), "EMAIL" VARCHAR2(50), "MOBILE" VARCHAR2(20), "IS_ADMIN" CHAR(1), "AGE" NUMBER, "ADDRESS" VARCHAR2(200), "CITY" VARCHAR2(100), "ZIP_CODE" VARCHAR2(50))
/
--------------------------------------------------------
--  DDL for Table BUSINESS_CATEGORY
--------------------------------------------------------

  CREATE TABLE "BUSINESS_CATEGORY" ("ID_BUSINESS_CATEGORY" NUMBER, "NAME" VARCHAR2(50), "DESCRIPTION" CLOB)
/
--------------------------------------------------------
--  DDL for Table CLIENT
--------------------------------------------------------

  CREATE TABLE "CLIENT" ("ID_CLIENT" NUMBER, "NAME" VARCHAR2(100), "EMAIL" VARCHAR2(100), "MOBILE" VARCHAR2(20), "CLIENT_FUNCTION" VARCHAR2(100), "OTHER_CONTACT" VARCHAR2(20), "ID_ORGANIZATION" VARCHAR2(20))
/
--------------------------------------------------------
--  DDL for Table DOCUMENT
--------------------------------------------------------

  CREATE TABLE "DOCUMENT" ("ID_DOCUMENT" NUMBER, "ID_PROJECT" NUMBER, "FILEPATH" VARCHAR2(300), "TITLE" VARCHAR2(100), "DESCRIPTION" CLOB)
/
--------------------------------------------------------
--  DDL for Table FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  CREATE TABLE "FUNCTIONAL_REQUIREMENT" ("ID_FUNCTIONAL_REQUIREMENT" NUMBER, "ID_PROJECT" NUMBER, "NAME" VARCHAR2(100), "DESCRIPTION" CLOB, "REASON" CLOB, "SOURCE" VARCHAR2(100), "AVALIATION_CRITERIA" CLOB, "CLIENT_PRIORITY" NUMBER, "CLIENT_INSATISFACTION" NUMBER, "ID_BUSINESS_CATEGORY" NUMBER, "REQUIREMENT_STATE" NUMBER, "VERSION" NUMBER, "ORDERNUMBER" NUMBER)
/
--------------------------------------------------------
--  DDL for Table FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  CREATE TABLE "FUNCTIONAL_REQ_DEPENDENCE" ("ID_FUNC_REQUIREMENT_1" NUMBER, "ID_FUNC_REQUIREMENT_2" NUMBER)
/
--------------------------------------------------------
--  DDL for Table NON_FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  CREATE TABLE "NON_FUNCTIONAL_REQUIREMENT" ("ID_NON_FUNC_REQUIREMENT" NUMBER, "TYPE" NUMBER, "NAME" VARCHAR2(50), "DESCRIPTION" CLOB, "REASON" CLOB, "SOURCE" VARCHAR2(100), "AVALIATION_CRITERIA" CLOB, "CLIENT_PRIORITY" NUMBER, "CLIENT_INSATISFACTION" NUMBER, "REQUIREMENT_STATE" NUMBER, "VERSION" NUMBER, "ID_PROJECT" NUMBER, "ORDERNUMBER" NUMBER)
/
--------------------------------------------------------
--  DDL for Table NON_FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  CREATE TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" ("ID_NON_FUNCTIONAL_REQ_1" NUMBER, "ID_NON_FUNCTIONAL_REQ_2" NUMBER)
/
--------------------------------------------------------
--  DDL for Table ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "ORGANIZATION" ("ID_ORGANIZATION" NUMBER, "NAME" VARCHAR2(200), "BUSINESS_AREA" VARCHAR2(100), "ADDRESS" VARCHAR2(200), "COUNTRY" VARCHAR2(50))
/
--------------------------------------------------------
--  DDL for Table PRIVILEGE
--------------------------------------------------------

  CREATE TABLE "PRIVILEGE" ("ID_PRIVILEGE" NUMBER, "ACTION_NAME" VARCHAR2(250))
/
--------------------------------------------------------
--  DDL for Table PROJECT
--------------------------------------------------------

  CREATE TABLE "PROJECT" ("ID_PROJECT" NUMBER, "NAME" VARCHAR2(100), "DESCRIPTION" CLOB, "BEGIN_DATE" DATE, "END_DATE" DATE, "PROJECT_STATE" NUMBER, "ID_PROJECT_MANAGER" NUMBER, "TERMINOLOGY" VARCHAR2(50), "ID_ORGANIZATION" NUMBER)
/
--------------------------------------------------------
--  DDL for Table PROJECT_USER
--------------------------------------------------------

  CREATE TABLE "PROJECT_USER" ("ID_USER" NUMBER, "ID_PROJECT" NUMBER, "ID_ROLE" NUMBER)
/
--------------------------------------------------------
--  DDL for Table ROLE_PRIVILEGE
--------------------------------------------------------

  CREATE TABLE "ROLE_PRIVILEGE" ("ID_ROLE" NUMBER, "ID_PRIVILEGE" NUMBER)
/
--------------------------------------------------------
--  DDL for Table SCOPE_DEFINITION_INTERACTION
--------------------------------------------------------

  CREATE TABLE "SCOPE_DEFINITION_INTERACTION" ("ID_SCOPE_DEF_INTERACTION" NUMBER, "TYPE" NUMBER, "TITLE" VARCHAR2(50), "INTERACTION_DATE" DATE, "ID_PROJECT" NUMBER, "DESCRIPTION" CLOB, "ID_CLIENT" NUMBER)
/
--------------------------------------------------------
--  DDL for Table USE_CASE
--------------------------------------------------------

  CREATE TABLE "USE_CASE" ("ID_USE_CASE" NUMBER, "NAME" VARCHAR2(100), "DESCRIPTION" CLOB, "AVALIATION_CRITERIA" CLOB, "PRE_CONDITION" CLOB, "POS_CONDITION" CLOB, "MAIN_SCENARIO" CLOB, "ID_FUNCTIONAL_REQUIREMENT" NUMBER, "ORDERNUMBER" NUMBER)
/
--------------------------------------------------------
--  DDL for Table USE_CASE_DEPENDENCE
--------------------------------------------------------

  CREATE TABLE "USE_CASE_DEPENDENCE" ("ID_USE_CASE_1" NUMBER, "ID_USE_CASE_2" NUMBER) 

   COMMENT ON COLUMN "USE_CASE_DEPENDENCE"."ID_USE_CASE_1" IS 'Pai'
   COMMENT ON COLUMN "USE_CASE_DEPENDENCE"."ID_USE_CASE_2" IS 'Use case 2 a depender do use case 1'
/
REM INSERTING into ACTOR
SET DEFINE OFF;
Insert into ACTOR (ID_ACTOR,NAME) values (11,'vamos ver como isto corre');
Insert into ACTOR (ID_ACTOR,NAME) values (234567,'antenas noob');
Insert into ACTOR (ID_ACTOR,NAME) values (1234124,'Dona Odete');
Insert into ACTOR (ID_ACTOR,NAME) values (1235412412,'1234');
REM INSERTING into ACTOR_USE_CASE
SET DEFINE OFF;
REM INSERTING into ALTERNATIVE_FLOWS
SET DEFINE OFF;
REM INSERTING into ALTRANREQ_ROLE
SET DEFINE OFF;
Insert into ALTRANREQ_ROLE (ID_ROLE,TYPE,DESCRIPTION) values (1,'Analista Funcional','dddddddd');
Insert into ALTRANREQ_ROLE (ID_ROLE,TYPE,DESCRIPTION) values (2,'1','teste');
Insert into ALTRANREQ_ROLE (ID_ROLE,TYPE,DESCRIPTION) values (5,'ddsdc','wdfr');
REM INSERTING into ALTRANREQ_USER
SET DEFINE OFF;
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (1,'teste','teste','teste','teste@mail.com','960000000','1',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (2,'Bruno','bn','bn','bn','96','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (3,'qqew','a','a','a','32','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (4,'myuser','[B@2ac8e633','myuser','myuser','1','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (5,'teste3','teste3','teste3','teste3@mail.com','933333333','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (6,'x','���f$�9�1��ѫ� ���','x','x','1','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (7,'as','as','as','as','1231413123123','0',null,null,null,null);
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (9,'admin','���f$�9�1��ѫ� ���','admin','admin','1','1',1,'1','1','1');
Insert into ALTRANREQ_USER (ID_USER,NAME,PASSWORD,USERNAME,EMAIL,MOBILE,IS_ADMIN,AGE,ADDRESS,CITY,ZIP_CODE) values (8,'teste e coiso','coiso','coiso','coiso@coisa.coisa','900000000','1',null,null,null,null);
REM INSERTING into BUSINESS_CATEGORY
SET DEFINE OFF;
Insert into BUSINESS_CATEGORY (ID_BUSINESS_CATEGORY,NAME) values (97,'Engenharia Mec�nica');
Insert into BUSINESS_CATEGORY (ID_BUSINESS_CATEGORY,NAME) values (71,'Ind�stria');
Insert into BUSINESS_CATEGORY (ID_BUSINESS_CATEGORY,NAME) values (101,'teste');
REM INSERTING into CLIENT
SET DEFINE OFF;
REM INSERTING into DOCUMENT
SET DEFINE OFF;
Insert into DOCUMENT (ID_DOCUMENT,ID_PROJECT,FILEPATH,TITLE) values (1,1,'...','Doc Teste');
Insert into DOCUMENT (ID_DOCUMENT,ID_PROJECT,FILEPATH,TITLE) values (2,1,'doc','doc');
REM INSERTING into FUNCTIONAL_REQUIREMENT
SET DEFINE OFF;
Insert into FUNCTIONAL_REQUIREMENT (ID_FUNCTIONAL_REQUIREMENT,ID_PROJECT,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,ID_BUSINESS_CATEGORY,REQUIREMENT_STATE,VERSION,ORDERNUMBER) values (225,2,'asdfghjkl','asdfghjkl�',1,1,97,0,1,null);
Insert into FUNCTIONAL_REQUIREMENT (ID_FUNCTIONAL_REQUIREMENT,ID_PROJECT,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,ID_BUSINESS_CATEGORY,REQUIREMENT_STATE,VERSION,ORDERNUMBER) values (221,1,'PGteste2','Fonte',1,1,97,0,1,1);
Insert into FUNCTIONAL_REQUIREMENT (ID_FUNCTIONAL_REQUIREMENT,ID_PROJECT,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,ID_BUSINESS_CATEGORY,REQUIREMENT_STATE,VERSION,ORDERNUMBER) values (224,1,'PGteste2','123teste',2,2,71,2,2,null);
Insert into FUNCTIONAL_REQUIREMENT (ID_FUNCTIONAL_REQUIREMENT,ID_PROJECT,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,ID_BUSINESS_CATEGORY,REQUIREMENT_STATE,VERSION,ORDERNUMBER) values (190,129,'rgege','erwgegw',1,1,71,0,1,null);
Insert into FUNCTIONAL_REQUIREMENT (ID_FUNCTIONAL_REQUIREMENT,ID_PROJECT,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,ID_BUSINESS_CATEGORY,REQUIREMENT_STATE,VERSION,ORDERNUMBER) values (199,122,'RequisitoTestavel','Tester',5,5,71,2,1,null);
REM INSERTING into FUNCTIONAL_REQ_DEPENDENCE
SET DEFINE OFF;
Insert into FUNCTIONAL_REQ_DEPENDENCE (ID_FUNC_REQUIREMENT_1,ID_FUNC_REQUIREMENT_2) values (224,224);
Insert into FUNCTIONAL_REQ_DEPENDENCE (ID_FUNC_REQUIREMENT_1,ID_FUNC_REQUIREMENT_2) values (221,224);
REM INSERTING into NON_FUNCTIONAL_REQUIREMENT
SET DEFINE OFF;
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (1,1,'lka','kasl�',1,1,1,1,3,1);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (2,2,'ewrds','fdsxc',3,3,3,3,2,3);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (3,3,'fqe','fqw',1,2,3,4,3,4);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (4,2,'Cenas','Cenas',3,3,2,1,1,2);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (5,0,'teste drops','kytck',1,1,0,1,1,1);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (41,1,'cenas','asd',2,2,2,1,1,2);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (45,5,'sadasfsadfa','adas',5,1,0,1,1,null);
Insert into NON_FUNCTIONAL_REQUIREMENT (ID_NON_FUNC_REQUIREMENT,TYPE,NAME,SOURCE,CLIENT_PRIORITY,CLIENT_INSATISFACTION,REQUIREMENT_STATE,VERSION,ID_PROJECT,ORDERNUMBER) values (46,0,'UsabilidadeCampos','cliente',0,0,2,2,122,null);
REM INSERTING into NON_FUNCTIONAL_REQ_DEPENDENCE
SET DEFINE OFF;
Insert into NON_FUNCTIONAL_REQ_DEPENDENCE (ID_NON_FUNCTIONAL_REQ_1,ID_NON_FUNCTIONAL_REQ_2) values (3,2);
Insert into NON_FUNCTIONAL_REQ_DEPENDENCE (ID_NON_FUNCTIONAL_REQ_1,ID_NON_FUNCTIONAL_REQ_2) values (4,1);
Insert into NON_FUNCTIONAL_REQ_DEPENDENCE (ID_NON_FUNCTIONAL_REQ_1,ID_NON_FUNCTIONAL_REQ_2) values (41,5);
Insert into NON_FUNCTIONAL_REQ_DEPENDENCE (ID_NON_FUNCTIONAL_REQ_1,ID_NON_FUNCTIONAL_REQ_2) values (46,4);
Insert into NON_FUNCTIONAL_REQ_DEPENDENCE (ID_NON_FUNCTIONAL_REQ_1,ID_NON_FUNCTIONAL_REQ_2) values (45,5);
REM INSERTING into ORGANIZATION
SET DEFINE OFF;
Insert into ORGANIZATION (ID_ORGANIZATION,NAME,BUSINESS_AREA,ADDRESS,COUNTRY) values (1,'Altran','A','A','France');
REM INSERTING into PRIVILEGE
SET DEFINE OFF;
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (1,'Acesso �rea Administra��o e gest�o de utilizadores');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (2,'Acesso �rea de Gest�o de Organiza��es');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (3,'Acesso �rea Gest�o do Projecto');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (4,'Acessos �rea Gest�o requisitos funcionais');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (5,'Acessos �rea Gest�o requisitos n�o funcionais');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (6,'Acessos �rea Gest�o casos de uso');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (7,'Acessos �rea Gest�o requisitos t�cnicos');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (8,'Criar, alterar e remover utilizadores');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (9,'Atribui��o do perfil de gestor de projecto');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (10,'Atribui��o do perfil de analista funcional');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (11,'Criar projectos');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (12,'Alterar projectos');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (13,'Remover projectos');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (14,'Criar, alterar e remover requisitos funcionais');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (15,'Criar, alterar e remover requisitos t�cnicos');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (16,'Exportar dados para documento');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (17,'Criar, alterar e remover cliente');
Insert into PRIVILEGE (ID_PRIVILEGE,ACTION_NAME) values (18,'Consultar cliente');
REM INSERTING into PROJECT
SET DEFINE OFF;
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (1,'AltranReq',to_date('18-NOV-13','DD-MON-RR'),to_date('22-NOV-13','DD-MON-RR'),1,2,'E',1);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (2,'O Ssegueiro e...',to_date('19-DEC-13','DD-MON-RR'),to_date('30-NOV-13','DD-MON-RR'),1,2,'A',1);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (3,'Teste antenas',null,null,0,1,'S',1);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (104,'Projecto dos Testers',null,null,0,2,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (161,'JNtesteProj',to_date('13-DEC-13','DD-MON-RR'),to_date('09-JAN-14','DD-MON-RR'),0,2,'JN',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (141,'qwerty',to_date('12-DEC-13','DD-MON-RR'),to_date('20-DEC-13','DD-MON-RR'),1,7,'asdfgh',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (142,'qwerty',to_date('12-DEC-13','DD-MON-RR'),to_date('20-DEC-13','DD-MON-RR'),1,7,'asdfgh',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (122,'Project1',to_date('03-APR-20','DD-MON-RR'),to_date('01-JAN-01','DD-MON-RR'),0,1,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (123,'project1',to_date('01-JAN-20','DD-MON-RR'),to_date('01-JAN-01','DD-MON-RR'),0,2,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (115,'Sandrina Fernandes',to_date('15-NOV-13','DD-MON-RR'),to_date('29-NOV-13','DD-MON-RR'),0,3,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (124,'teste obrigatoriedade',null,null,0,5,'jkjkds',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (125,'teste obrigatoriedade2',null,null,0,4,'jkjkds',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (131,'asdfg',null,null,0,4,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (119,'Sandrina Fernandes',to_date('15-NOV-13','DD-MON-RR'),to_date('29-NOV-13','DD-MON-RR'),0,2,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (120,'Sandrina Fernandes',to_date('15-NOV-13','DD-MON-RR'),to_date('29-NOV-13','DD-MON-RR'),0,2,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (132,'xcvbn',null,null,0,3,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (128,'teslkuylgkuyglu',null,null,0,1,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (129,'teste remoção ninguém mexe',to_date('04-NOV-13','DD-MON-RR'),to_date('14-NOV-13','DD-MON-RR'),0,4,'ewdew',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (133,'trew',null,null,1,5,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (134,'werty',to_date('12-NOV-13','DD-MON-RR'),to_date('14-NOV-13','DD-MON-RR'),1,2,'tr',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (162,'asddddddd',to_date('30-DEC-13','DD-MON-RR'),to_date('02-DEC-13','DD-MON-RR'),1,1,'as',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (136,'ver isto',null,null,0,1,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (140,'Projecto',to_date('05-NOV-13','DD-MON-RR'),to_date('29-NOV-13','DD-MON-RR'),0,2,'pr',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (138,'VER ISTO',null,null,0,1,null,null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (163,'JN Teste novamente',null,null,0,6,'qwerty',null);
Insert into PROJECT (ID_PROJECT,NAME,BEGIN_DATE,END_DATE,PROJECT_STATE,ID_PROJECT_MANAGER,TERMINOLOGY,ID_ORGANIZATION) values (164,'JN teste outro',null,null,0,7,null,null);
REM INSERTING into PROJECT_USER
SET DEFINE OFF;
Insert into PROJECT_USER (ID_USER,ID_PROJECT,ID_ROLE) values (2,2,2);
Insert into PROJECT_USER (ID_USER,ID_PROJECT,ID_ROLE) values (1,1,1);
Insert into PROJECT_USER (ID_USER,ID_PROJECT,ID_ROLE) values (2,1,2);
REM INSERTING into ROLE_PRIVILEGE
SET DEFINE OFF;
REM INSERTING into SCOPE_DEFINITION_INTERACTION
SET DEFINE OFF;
REM INSERTING into USE_CASE
SET DEFINE OFF;
Insert into USE_CASE (ID_USE_CASE,NAME,ID_FUNCTIONAL_REQUIREMENT,ORDERNUMBER) values (22,'testeqret',221,1);
Insert into USE_CASE (ID_USE_CASE,NAME,ID_FUNCTIONAL_REQUIREMENT,ORDERNUMBER) values (21,'rmda',221,2);
Insert into USE_CASE (ID_USE_CASE,NAME,ID_FUNCTIONAL_REQUIREMENT,ORDERNUMBER) values (17,'TTT',199,null);
REM INSERTING into USE_CASE_DEPENDENCE
SET DEFINE OFF;
Insert into USE_CASE_DEPENDENCE (ID_USE_CASE_1,ID_USE_CASE_2) values (21,17);
--------------------------------------------------------
--  DDL for Index ACTOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACTOR_PK" ON "ACTOR" ("ID_ACTOR")
/
--------------------------------------------------------
--  DDL for Index ACTOR_USE_CASE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACTOR_USE_CASE_PK" ON "ACTOR_USE_CASE" ("ID_ACTOR", "ID_USE_CASE")
/
--------------------------------------------------------
--  DDL for Index ALTERNATIVE_FLOWS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ALTERNATIVE_FLOWS_PK" ON "ALTERNATIVE_FLOWS" ("ID_ALTERNATIVE_FLOWS")
/
--------------------------------------------------------
--  DDL for Index BUSINESS_CATEGORY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BUSINESS_CATEGORY_PK" ON "BUSINESS_CATEGORY" ("ID_BUSINESS_CATEGORY")
/
--------------------------------------------------------
--  DDL for Index CLIENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CLIENT_PK" ON "CLIENT" ("ID_CLIENT")
/
--------------------------------------------------------
--  DDL for Index DOCUMENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCUMENTS_PK" ON "DOCUMENT" ("ID_DOCUMENT")
/
--------------------------------------------------------
--  DDL for Index FUNCTIONAL_REQUIREMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FUNCTIONAL_REQUIREMENT_PK" ON "FUNCTIONAL_REQUIREMENT" ("ID_FUNCTIONAL_REQUIREMENT")
/
--------------------------------------------------------
--  DDL for Index NON_FUNCTIONAL_REQUIREMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "NON_FUNCTIONAL_REQUIREMENT_PK" ON "NON_FUNCTIONAL_REQUIREMENT" ("ID_NON_FUNC_REQUIREMENT")
/
--------------------------------------------------------
--  DDL for Index ORGANIZATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ORGANIZATION_PK" ON "ORGANIZATION" ("ID_ORGANIZATION")
/
--------------------------------------------------------
--  DDL for Index PRIVILEGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PRIVILEGE_PK" ON "PRIVILEGE" ("ID_PRIVILEGE")
/
--------------------------------------------------------
--  DDL for Index PROJECT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PROJECT_PK" ON "PROJECT" ("ID_PROJECT")
/
--------------------------------------------------------
--  DDL for Index ROLE_BY_PROJECT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROLE_BY_PROJECT_PK" ON "PROJECT_USER" ("ID_USER", "ID_PROJECT")
/
--------------------------------------------------------
--  DDL for Index ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROLE_PK" ON "ALTRANREQ_ROLE" ("ID_ROLE")
/
--------------------------------------------------------
--  DDL for Index ROLE_PRIVILEGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROLE_PRIVILEGE_PK" ON "ROLE_PRIVILEGE" ("ID_ROLE", "ID_PRIVILEGE")
/
--------------------------------------------------------
--  DDL for Index SCOPE_DEF_INTERACTION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOPE_DEF_INTERACTION_PK" ON "SCOPE_DEFINITION_INTERACTION" ("ID_SCOPE_DEF_INTERACTION")
/
--------------------------------------------------------
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "USER_PK" ON "ALTRANREQ_USER" ("ID_USER")
/
--------------------------------------------------------
--  DDL for Index USE_CASE_DEPENDENCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "USE_CASE_DEPENDENCE_PK" ON "USE_CASE_DEPENDENCE" ("ID_USE_CASE_2", "ID_USE_CASE_1")
/
--------------------------------------------------------
--  DDL for Index USE_CASE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "USE_CASE_PK" ON "USE_CASE" ("ID_USE_CASE")
/
--------------------------------------------------------
--  Constraints for Table ACTOR
--------------------------------------------------------

  ALTER TABLE "ACTOR" MODIFY ("ID_ACTOR" NOT NULL ENABLE)
  ALTER TABLE "ACTOR" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "ACTOR" ADD CONSTRAINT "ACTOR_PK" PRIMARY KEY ("ID_ACTOR") ENABLE
/
--------------------------------------------------------
--  Constraints for Table ACTOR_USE_CASE
--------------------------------------------------------

  ALTER TABLE "ACTOR_USE_CASE" MODIFY ("ID_ACTOR" NOT NULL ENABLE)
  ALTER TABLE "ACTOR_USE_CASE" MODIFY ("ID_USE_CASE" NOT NULL ENABLE)
  ALTER TABLE "ACTOR_USE_CASE" ADD CONSTRAINT "ACTOR_USE_CASE_PK" PRIMARY KEY ("ID_ACTOR", "ID_USE_CASE") ENABLE
/
--------------------------------------------------------
--  Constraints for Table ALTERNATIVE_FLOWS
--------------------------------------------------------

  ALTER TABLE "ALTERNATIVE_FLOWS" MODIFY ("ID_ALTERNATIVE_FLOWS" NOT NULL ENABLE)
  ALTER TABLE "ALTERNATIVE_FLOWS" MODIFY ("ID_USE_CASE" NOT NULL ENABLE)
  ALTER TABLE "ALTERNATIVE_FLOWS" ADD CONSTRAINT "ALTERNATIVE_FLOWS_PK" PRIMARY KEY ("ID_ALTERNATIVE_FLOWS") ENABLE
/
--------------------------------------------------------
--  Constraints for Table ALTRANREQ_ROLE
--------------------------------------------------------

  ALTER TABLE "ALTRANREQ_ROLE" MODIFY ("ID_ROLE" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_ROLE" MODIFY ("TYPE" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_ROLE" ADD CONSTRAINT "ROLE_PK" PRIMARY KEY ("ID_ROLE") ENABLE
/
--------------------------------------------------------
--  Constraints for Table ALTRANREQ_USER
--------------------------------------------------------

  ALTER TABLE "ALTRANREQ_USER" MODIFY ("IS_ADMIN" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("ID_USER") ENABLE
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("ID_USER" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("PASSWORD" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("USERNAME" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("EMAIL" NOT NULL ENABLE)
  ALTER TABLE "ALTRANREQ_USER" MODIFY ("MOBILE" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table BUSINESS_CATEGORY
--------------------------------------------------------

  ALTER TABLE "BUSINESS_CATEGORY" MODIFY ("ID_BUSINESS_CATEGORY" NOT NULL ENABLE)
  ALTER TABLE "BUSINESS_CATEGORY" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "BUSINESS_CATEGORY" ADD CONSTRAINT "BUSINESS_CATEGORY_PK" PRIMARY KEY ("ID_BUSINESS_CATEGORY") ENABLE
/
--------------------------------------------------------
--  Constraints for Table CLIENT
--------------------------------------------------------

  ALTER TABLE "CLIENT" MODIFY ("ID_CLIENT" NOT NULL ENABLE)
  ALTER TABLE "CLIENT" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "CLIENT" ADD CONSTRAINT "CLIENT_PK" PRIMARY KEY ("ID_CLIENT") ENABLE
  ALTER TABLE "CLIENT" MODIFY ("EMAIL" NOT NULL ENABLE)
  ALTER TABLE "CLIENT" MODIFY ("MOBILE" NOT NULL ENABLE)
  ALTER TABLE "CLIENT" MODIFY ("CLIENT_FUNCTION" NOT NULL ENABLE)
  ALTER TABLE "CLIENT" MODIFY ("ID_ORGANIZATION" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table DOCUMENT
--------------------------------------------------------

  ALTER TABLE "DOCUMENT" MODIFY ("FILEPATH" NOT NULL ENABLE)
  ALTER TABLE "DOCUMENT" MODIFY ("ID_DOCUMENT" NOT NULL ENABLE)
  ALTER TABLE "DOCUMENT" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "DOCUMENT" MODIFY ("TITLE" NOT NULL ENABLE)
  ALTER TABLE "DOCUMENT" ADD CONSTRAINT "DOCUMENTS_PK" PRIMARY KEY ("ID_DOCUMENT") ENABLE
/
--------------------------------------------------------
--  Constraints for Table FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("ID_FUNCTIONAL_REQUIREMENT" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("SOURCE" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("CLIENT_PRIORITY" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("CLIENT_INSATISFACTION" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("ID_BUSINESS_CATEGORY" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("REQUIREMENT_STATE" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" MODIFY ("VERSION" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" ADD CONSTRAINT "FUNCTIONAL_REQUIREMENT_PK" PRIMARY KEY ("ID_FUNCTIONAL_REQUIREMENT") ENABLE
/
--------------------------------------------------------
--  Constraints for Table FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "FUNCTIONAL_REQ_DEPENDENCE" MODIFY ("ID_FUNC_REQUIREMENT_1" NOT NULL ENABLE)
  ALTER TABLE "FUNCTIONAL_REQ_DEPENDENCE" MODIFY ("ID_FUNC_REQUIREMENT_2" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table NON_FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("ID_NON_FUNC_REQUIREMENT" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("TYPE" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("SOURCE" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("CLIENT_PRIORITY" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("CLIENT_INSATISFACTION" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("REQUIREMENT_STATE" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("VERSION" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" ADD CONSTRAINT "NON_FUNCTIONAL_REQUIREMENT_PK" PRIMARY KEY ("ID_NON_FUNC_REQUIREMENT") ENABLE
/
--------------------------------------------------------
--  Constraints for Table NON_FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" MODIFY ("ID_NON_FUNCTIONAL_REQ_1" NOT NULL ENABLE)
  ALTER TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" MODIFY ("ID_NON_FUNCTIONAL_REQ_2" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "ORGANIZATION" MODIFY ("ID_ORGANIZATION" NOT NULL ENABLE)
  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "ORGANIZATION_PK" PRIMARY KEY ("ID_ORGANIZATION") ENABLE
  ALTER TABLE "ORGANIZATION" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "ORGANIZATION" MODIFY ("BUSINESS_AREA" NOT NULL ENABLE)
  ALTER TABLE "ORGANIZATION" MODIFY ("ADDRESS" NOT NULL ENABLE)
  ALTER TABLE "ORGANIZATION" MODIFY ("COUNTRY" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table PRIVILEGE
--------------------------------------------------------

  ALTER TABLE "PRIVILEGE" MODIFY ("ID_PRIVILEGE" NOT NULL ENABLE)
  ALTER TABLE "PRIVILEGE" MODIFY ("ACTION_NAME" NOT NULL ENABLE)
  ALTER TABLE "PRIVILEGE" ADD CONSTRAINT "PRIVILEGE_PK" PRIMARY KEY ("ID_PRIVILEGE") ENABLE
/
--------------------------------------------------------
--  Constraints for Table PROJECT
--------------------------------------------------------

  ALTER TABLE "PROJECT" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "PROJECT" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "PROJECT" MODIFY ("PROJECT_STATE" NOT NULL ENABLE)
  ALTER TABLE "PROJECT" ADD CONSTRAINT "PROJECT_PK" PRIMARY KEY ("ID_PROJECT") ENABLE
/
--------------------------------------------------------
--  Constraints for Table PROJECT_USER
--------------------------------------------------------

  ALTER TABLE "PROJECT_USER" ADD CONSTRAINT "ROLE_BY_PROJECT_PK" PRIMARY KEY ("ID_USER", "ID_PROJECT") ENABLE
  ALTER TABLE "PROJECT_USER" MODIFY ("ID_USER" NOT NULL ENABLE)
  ALTER TABLE "PROJECT_USER" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "PROJECT_USER" MODIFY ("ID_ROLE" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table ROLE_PRIVILEGE
--------------------------------------------------------

  ALTER TABLE "ROLE_PRIVILEGE" MODIFY ("ID_PRIVILEGE" NOT NULL ENABLE)
  ALTER TABLE "ROLE_PRIVILEGE" ADD CONSTRAINT "ROLE_PRIVILEGE_PK" PRIMARY KEY ("ID_ROLE", "ID_PRIVILEGE") ENABLE
  ALTER TABLE "ROLE_PRIVILEGE" MODIFY ("ID_ROLE" NOT NULL ENABLE)
/
--------------------------------------------------------
--  Constraints for Table SCOPE_DEFINITION_INTERACTION
--------------------------------------------------------

  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("ID_SCOPE_DEF_INTERACTION" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("TYPE" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("TITLE" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("INTERACTION_DATE" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("ID_PROJECT" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("DESCRIPTION" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" MODIFY ("ID_CLIENT" NOT NULL ENABLE)
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" ADD CONSTRAINT "SCOPE_DEF_INTERACTION_PK" PRIMARY KEY ("ID_SCOPE_DEF_INTERACTION") ENABLE
/
--------------------------------------------------------
--  Constraints for Table USE_CASE
--------------------------------------------------------

  ALTER TABLE "USE_CASE" MODIFY ("ID_USE_CASE" NOT NULL ENABLE)
  ALTER TABLE "USE_CASE" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "USE_CASE" MODIFY ("ID_FUNCTIONAL_REQUIREMENT" NOT NULL ENABLE)
  ALTER TABLE "USE_CASE" ADD CONSTRAINT "USE_CASE_PK" PRIMARY KEY ("ID_USE_CASE") ENABLE
/
--------------------------------------------------------
--  Constraints for Table USE_CASE_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "USE_CASE_DEPENDENCE" MODIFY ("ID_USE_CASE_1" NOT NULL ENABLE)
  ALTER TABLE "USE_CASE_DEPENDENCE" MODIFY ("ID_USE_CASE_2" NOT NULL ENABLE)
  ALTER TABLE "USE_CASE_DEPENDENCE" ADD CONSTRAINT "USE_CASE_DEPENDENCE_PK" PRIMARY KEY ("ID_USE_CASE_2", "ID_USE_CASE_1") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table ACTOR_USE_CASE
--------------------------------------------------------

  ALTER TABLE "ACTOR_USE_CASE" ADD CONSTRAINT "ACTOR_FK" FOREIGN KEY ("ID_ACTOR") REFERENCES "ACTOR" ("ID_ACTOR") ENABLE
  ALTER TABLE "ACTOR_USE_CASE" ADD CONSTRAINT "USE_CASE_FK_A" FOREIGN KEY ("ID_USE_CASE") REFERENCES "USE_CASE" ("ID_USE_CASE") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table ALTERNATIVE_FLOWS
--------------------------------------------------------

  ALTER TABLE "ALTERNATIVE_FLOWS" ADD CONSTRAINT "USE_CASE_FK" FOREIGN KEY ("ID_USE_CASE") REFERENCES "USE_CASE" ("ID_USE_CASE") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table CLIENT
--------------------------------------------------------

  ALTER TABLE "CLIENT" ADD CONSTRAINT "CLIENT_ORGANIZATION_FK1" FOREIGN KEY ("ID_CLIENT") REFERENCES "ORGANIZATION" ("ID_ORGANIZATION") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table DOCUMENT
--------------------------------------------------------

  ALTER TABLE "DOCUMENT" ADD CONSTRAINT "PROJECT_FK" FOREIGN KEY ("ID_PROJECT") REFERENCES "PROJECT" ("ID_PROJECT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  ALTER TABLE "FUNCTIONAL_REQUIREMENT" ADD CONSTRAINT "BUSINESS_CATEGORY_FK" FOREIGN KEY ("ID_BUSINESS_CATEGORY") REFERENCES "BUSINESS_CATEGORY" ("ID_BUSINESS_CATEGORY") ENABLE
  ALTER TABLE "FUNCTIONAL_REQUIREMENT" ADD CONSTRAINT "PROJECT_FK2" FOREIGN KEY ("ID_PROJECT") REFERENCES "PROJECT" ("ID_PROJECT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "FUNCTIONAL_REQ_DEPENDENCE" ADD CONSTRAINT "FUNCTIONAL_REQ_FK_1" FOREIGN KEY ("ID_FUNC_REQUIREMENT_1") REFERENCES "FUNCTIONAL_REQUIREMENT" ("ID_FUNCTIONAL_REQUIREMENT") ENABLE
  ALTER TABLE "FUNCTIONAL_REQ_DEPENDENCE" ADD CONSTRAINT "FUNCTIONAL_REQ_FK_2" FOREIGN KEY ("ID_FUNC_REQUIREMENT_2") REFERENCES "FUNCTIONAL_REQUIREMENT" ("ID_FUNCTIONAL_REQUIREMENT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table NON_FUNCTIONAL_REQUIREMENT
--------------------------------------------------------

  ALTER TABLE "NON_FUNCTIONAL_REQUIREMENT" ADD CONSTRAINT "PROJECT_FK3" FOREIGN KEY ("ID_PROJECT") REFERENCES "PROJECT" ("ID_PROJECT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table NON_FUNCTIONAL_REQ_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" ADD CONSTRAINT "NON_FUNCTIONAL_FK_1" FOREIGN KEY ("ID_NON_FUNCTIONAL_REQ_1") REFERENCES "NON_FUNCTIONAL_REQUIREMENT" ("ID_NON_FUNC_REQUIREMENT") ENABLE
  ALTER TABLE "NON_FUNCTIONAL_REQ_DEPENDENCE" ADD CONSTRAINT "NON_FUNCTIONAL_FK_2" FOREIGN KEY ("ID_NON_FUNCTIONAL_REQ_2") REFERENCES "NON_FUNCTIONAL_REQUIREMENT" ("ID_NON_FUNC_REQUIREMENT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table PROJECT
--------------------------------------------------------

  ALTER TABLE "PROJECT" ADD CONSTRAINT "PROJECT_ORGANIZATION_FK1" FOREIGN KEY ("ID_ORGANIZATION") REFERENCES "ORGANIZATION" ("ID_ORGANIZATION") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table PROJECT_USER
--------------------------------------------------------

  ALTER TABLE "PROJECT_USER" ADD CONSTRAINT "PROJECT_FK1" FOREIGN KEY ("ID_PROJECT") REFERENCES "PROJECT" ("ID_PROJECT") ENABLE
  ALTER TABLE "PROJECT_USER" ADD CONSTRAINT "PROJECT_USER_ALTRANREQ_RO_FK1" FOREIGN KEY ("ID_ROLE") REFERENCES "ALTRANREQ_ROLE" ("ID_ROLE") ENABLE
  ALTER TABLE "PROJECT_USER" ADD CONSTRAINT "USER_FK" FOREIGN KEY ("ID_USER") REFERENCES "ALTRANREQ_USER" ("ID_USER") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table ROLE_PRIVILEGE
--------------------------------------------------------

  ALTER TABLE "ROLE_PRIVILEGE" ADD CONSTRAINT "PRIVILEGE_FK" FOREIGN KEY ("ID_PRIVILEGE") REFERENCES "PRIVILEGE" ("ID_PRIVILEGE") ENABLE
  ALTER TABLE "ROLE_PRIVILEGE" ADD CONSTRAINT "ROLE_FK" FOREIGN KEY ("ID_ROLE") REFERENCES "ALTRANREQ_ROLE" ("ID_ROLE") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table SCOPE_DEFINITION_INTERACTION
--------------------------------------------------------

  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" ADD CONSTRAINT "CLIENT_FK" FOREIGN KEY ("ID_CLIENT") REFERENCES "CLIENT" ("ID_CLIENT") ENABLE
  ALTER TABLE "SCOPE_DEFINITION_INTERACTION" ADD CONSTRAINT "PROJECT_FK4" FOREIGN KEY ("ID_PROJECT") REFERENCES "PROJECT" ("ID_PROJECT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table USE_CASE
--------------------------------------------------------

  ALTER TABLE "USE_CASE" ADD CONSTRAINT "FUNCTIONAL_REQUIREMENT_FK" FOREIGN KEY ("ID_FUNCTIONAL_REQUIREMENT") REFERENCES "FUNCTIONAL_REQUIREMENT" ("ID_FUNCTIONAL_REQUIREMENT") ENABLE
/
--------------------------------------------------------
--  Ref Constraints for Table USE_CASE_DEPENDENCE
--------------------------------------------------------

  ALTER TABLE "USE_CASE_DEPENDENCE" ADD CONSTRAINT "USE_CASE_FK_1" FOREIGN KEY ("ID_USE_CASE_1") REFERENCES "USE_CASE" ("ID_USE_CASE") ENABLE
  ALTER TABLE "USE_CASE_DEPENDENCE" ADD CONSTRAINT "USE_CASE_FK_2" FOREIGN KEY ("ID_USE_CASE_2") REFERENCES "USE_CASE" ("ID_USE_CASE") ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_ACTOR_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_ACTOR_PK" 
   before insert on "ACTOR" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_ACTOR" is null then 
         select SEQ_ACTOR.nextval into :NEW."ID_ACTOR" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_ACTOR_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_ALTERNATIVE_FLOWS_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_ALTERNATIVE_FLOWS_PK" 
   before insert on "ALTERNATIVE_FLOWS" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_ALTERNATIVE_FLOWS" is null then 
         select SEQ_ALTERNATIVE_FLOWS.nextval into :NEW."ID_ALTERNATIVE_FLOWS" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_ALTERNATIVE_FLOWS_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_BUSINESS_CATEGORY_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_BUSINESS_CATEGORY_PK" 
   before insert on "BUSINESS_CATEGORY" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_BUSINESS_CATEGORY" is null then 
         select SEQ_BUSINESS_CATEGORY.nextval into :NEW."ID_BUSINESS_CATEGORY" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_BUSINESS_CATEGORY_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_CLIENT_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_CLIENT_PK" 
   before insert on "CLIENT" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_CLIENT" is null then 
         select SEQ_CLIENT.nextval into :NEW."ID_CLIENT" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_CLIENT_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_DOCUMENT_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_DOCUMENT_PK" 
   before insert on "DOCUMENT" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_DOCUMENT" is null then 
         select SEQ_DOCUMENT.nextval into :NEW."ID_DOCUMENT" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_DOCUMENT_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_FUNC_REQ_DEPENDENCE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_FUNC_REQ_DEPENDENCE" 
   before insert on "FUNCTIONAL_REQ_DEPENDENCE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_FUNC_REQUIREMENT_1" is null then 
         select SEQ_FUNCTIONAL_REQUIREMENT.CURRVAL into :NEW."ID_FUNC_REQUIREMENT_1" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_FUNC_REQ_DEPENDENCE" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_FUNC_REQ_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_FUNC_REQ_PK" 
   before insert on "FUNCTIONAL_REQUIREMENT" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_FUNCTIONAL_REQUIREMENT" is null then 
         select SEQ_FUNCTIONAL_REQUIREMENT.nextval into :NEW."ID_FUNCTIONAL_REQUIREMENT" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_FUNC_REQ_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_NON_FUNC_REQ_DEP
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_NON_FUNC_REQ_DEP" 
   before insert on "NON_FUNCTIONAL_REQ_DEPENDENCE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_NON_FUNCTIONAL_REQ_1" is null then 
         select SEQ_NON_FUNCTIONAL_REQUIREMENT.CURRVAL into :NEW."ID_NON_FUNCTIONAL_REQ_1" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_NON_FUNC_REQ_DEP" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_NON_FUNC_REQ_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_NON_FUNC_REQ_PK" 
   before insert on "NON_FUNCTIONAL_REQUIREMENT" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_NON_FUNC_REQUIREMENT" is null then 
         select SEQ_NON_FUNCTIONAL_REQUIREMENT.nextval into :NEW."ID_NON_FUNC_REQUIREMENT" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_NON_FUNC_REQ_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_PRIVILEGE_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_PRIVILEGE_PK" 
   before insert on "PRIVILEGE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_PRIVILEGE" is null then 
         select SEQ_PRIVELEGE.nextval into :NEW."ID_PRIVILEGE" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_PRIVILEGE_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_PROJECT_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_PROJECT_PK" 
   before insert on "PROJECT" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_PROJECT" is null then 
         select SEQ_PROJECT.nextval into :NEW."ID_PROJECT" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_PROJECT_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_ROLE_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_ROLE_PK" 
   before insert on "ALTRANREQ_ROLE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_ROLE" is null then 
         select SEQ_ROLE.nextval into :NEW."ID_ROLE" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_ROLE_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_SDI_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_SDI_PK" 
   before insert on "SCOPE_DEFINITION_INTERACTION" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_SCOPE_DEF_INTERACTION" is null then 
         select SEQ_SCOPE_DEF_INTERACTION.nextval into :NEW."ID_SCOPE_DEF_INTERACTION" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_SDI_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_USER_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_USER_PK" 
   before insert on "ALTRANREQ_USER" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_USER" is null then 
         select SEQ_USER.nextval into :NEW."ID_USER" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_USER_PK" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_USE_CASE_DEPENDENCE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_USE_CASE_DEPENDENCE" 
   before insert on "USE_CASE_DEPENDENCE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_USE_CASE_1" is null then 
         select SEQ_USE_CASE.CURRVAL into :NEW."ID_USE_CASE_1" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_USE_CASE_DEPENDENCE" ENABLE
/
--------------------------------------------------------
--  DDL for Trigger TRIGGER_USE_CASE_PK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_USE_CASE_PK" 
   before insert on "USE_CASE" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_USE_CASE" is null then 
         select SEQ_USE_CASE.nextval into :NEW."ID_USE_CASE" from dual; 
      end if; 
   end if; 
end;
ALTER TRIGGER "TRIGGER_USE_CASE_PK" ENABLE
/
