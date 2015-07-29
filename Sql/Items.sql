--  DDL for Table ITEMS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."ITEMS" ("ID" NUMBER(*,0), "NAME" VARCHAR2(50), "ITEM_GROUP" NUMBER(*,0), "DESCRIPTION" VARCHAR2(2000), "PRICE" NUMBER(*,0), "IMAGE_1" VARCHAR2(50), "IMAGE_2" VARCHAR2(50))
REM INSERTING into SYSTEM.ITEMS
SET DEFINE OFF;
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('6','Плейткерріар Кора','1','Ще гарніше','1200','pk_1','pk_2');
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('3','Чохол бронежилету СТАН','1','Дуже гарний :)','1790','stan_1','stan_2');
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('7','РПС ТРИТОН','2','!!!','860','triton_1','triton_2');
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('8','Підсумок тактичний','3','111','170','pidsumok_1',null);
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('10','Платформа на стегно','4','вапав','260','p_stegno_1',null);
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('11','Платформа на стегно літня','4','вапва','210','p_stegno_l_1',null);
Insert into SYSTEM.ITEMS (ID,NAME,ITEM_GROUP,DESCRIPTION,PRICE,IMAGE_1,IMAGE_2) values ('13','Аптечка','5','вапва','350','aptechka_1','aptechka_2');
--------------------------------------------------------
--  DDL for Index ITEMS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."ITEMS_PK" ON "SYSTEM"."ITEMS" ("ID")
--------------------------------------------------------
--  Constraints for Table ITEMS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."ITEMS" ADD CONSTRAINT "ITEMS_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "SYSTEM"."ITEMS" MODIFY ("PRICE" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."ITEMS" MODIFY ("ITEM_GROUP" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."ITEMS" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "SYSTEM"."ITEMS" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table ITEMS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."ITEMS" ADD CONSTRAINT "FK_GROUP" FOREIGN KEY ("ITEM_GROUP") REFERENCES "SYSTEM"."ITEMS_GROUPS" ("ID") ENABLE
--------------------------------------------------------
--  DDL for Trigger ITEMS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."ITEMS_TRG" 
BEFORE INSERT ON ITEMS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT ITEMS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "SYSTEM"."ITEMS_TRG" ENABLE
