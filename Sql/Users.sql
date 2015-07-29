--------------------------------------------------------
--  File created - середа-липня-29-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."USERS" ("LASTNAME" VARCHAR2(20), "FIRSTNAME" VARCHAR2(20), "TELEPHONE" VARCHAR2(20), "PASSWORD" VARCHAR2(20), "LOGIN" VARCHAR2(20), "ID" NUMBER)
REM INSERTING into SYSTEM.USERS
SET DEFINE OFF;
Insert into SYSTEM.USERS (LASTNAME,FIRSTNAME,TELEPHONE,PASSWORD,LOGIN,ID) values ('Shvydka','Irina','0923129083','215255','exwater','1');
Insert into SYSTEM.USERS (LASTNAME,FIRSTNAME,TELEPHONE,PASSWORD,LOGIN,ID) values ('2','2','2','2','2','5');
--------------------------------------------------------
--  DDL for Index USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."USERS_PK" ON "SYSTEM"."USERS" ("ID")
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "SYSTEM"."USERS" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."USERS" MODIFY ("LOGIN" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."USERS" MODIFY ("TELEPHONE" NOT NULL ENABLE)
--------------------------------------------------------
--  DDL for Trigger USERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."USERS_TRG" 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "SYSTEM"."USERS_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger USERS_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."USERS_TRG1" 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.LASTNAME IS NULL THEN
      SELECT USERS_SEQ.NEXTVAL INTO :NEW.LASTNAME FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "SYSTEM"."USERS_TRG1" ENABLE
