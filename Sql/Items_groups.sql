--------------------------------------------------------
--  File created - середа-липня-29-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ITEMS_GROUPS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."ITEMS_GROUPS" ("ID" NUMBER(*,0), "NAME" VARCHAR2(50))
REM INSERTING into SYSTEM.ITEMS_GROUPS
SET DEFINE OFF;
Insert into SYSTEM.ITEMS_GROUPS (ID,NAME) values ('1','Чохли бронежилетів');
Insert into SYSTEM.ITEMS_GROUPS (ID,NAME) values ('2','РПС');
Insert into SYSTEM.ITEMS_GROUPS (ID,NAME) values ('3','Підсумки');
Insert into SYSTEM.ITEMS_GROUPS (ID,NAME) values ('4','Платформи');
Insert into SYSTEM.ITEMS_GROUPS (ID,NAME) values ('5','Аптечка');
--------------------------------------------------------
--  DDL for Index ITEMS_GROUPS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."ITEMS_GROUPS_PK" ON "SYSTEM"."ITEMS_GROUPS" ("ID")
--------------------------------------------------------
--  Constraints for Table ITEMS_GROUPS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."ITEMS_GROUPS" ADD CONSTRAINT "ITEMS_GROUPS_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "SYSTEM"."ITEMS_GROUPS" MODIFY ("NAME" NOT NULL ENABLE)
--------------------------------------------------------
--  DDL for Trigger ITEMS_GROUPS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."ITEMS_GROUPS_TRG" 
BEFORE INSERT ON ITEMS_GROUPS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT ITEMS_GROUPS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "SYSTEM"."ITEMS_GROUPS_TRG" ENABLE
