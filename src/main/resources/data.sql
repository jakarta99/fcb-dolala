--匯入電文檔
INSERT INTO IR_CASE (SEQ_NO, VALUE_DATE, AMOUNT, CURRENCY)
VALUES ('123456789012345', '2022-03-10', '2000','USD');

--匯入匯款案件主檔
INSERT INTO IR_APPLY_MASTER 
(IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURENCY,IR_AMT) 
VALUES ('S1NHA00947',1,'N','093','Y','05052322','USD',3456.78);

INSERT INTO IR_APPLY_MASTER 
(IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURENCY,IR_AMT) 
VALUES ('S1NHA00955',1,'N','094','Y','86483666','HKD',13681.89);

INSERT INTO IR_APPLY_MASTER 
(IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURENCY,IR_AMT) 
VALUES ('S1NHA00963',1,'N','101','Y','03218306','USD',542436.2);

INSERT INTO IR_APPLY_MASTER 
(IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURENCY,IR_AMT) 
VALUES ('S1NHA00971',1,'N','102','Y','70538519','JPY',432643);

INSERT INTO IR_APPLY_MASTER 
(IR_NO,PAID_STATS,PRINT_ADV_MK,BE_ADV_BRANCH,OUR_CUST,CUSTOMER_ID,CURENCY,IR_AMT) 
VALUES ('S1NHA00979',1,'N','093','Y','05052322','ZAR',234623.76);

--匯率資料檔
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','B','USD','28.34','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','S','USD','28.44','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','B','EUR','31.78','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','S','EUR','31.18','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','B','HKD','3.596','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','S','HKD','3.656','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','B','JPY','0.2401','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','S','JPY','0.2441','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','B','ZAR','1.834','TWD','1');
INSERT INTO EXCHG_RATE (OPT_DATE,EXCHG_RATE_TYPE,CURRENCY,EXCHANGE_RATE,STANDARD_CURRENCY,STATUS) VALUES ('2022-03-11','S','ZAR','1.934','TWD','1');

--分行檔
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('091','NH','FIRST_CATEGORY','091','外匯營運處','台北市中正區重慶南路1段30號','(02)23481111');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('093','NHA','SECOND_CATEGORY','091','營業部','台北市中正區重慶南路1段30號','(02)23481111');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('094','AW','FIRST_CATEGORY','091','安和分行','台北市大安區信義路4段184號','(02)23256000');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('101','EM','THIRD_CATEGORY','091','南港分行','台北市南港區園區街3號2樓之8','(02)26558777');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('102','CY','THIRD_CATEGORY','091','西門分行','台北市萬華區西寧南路52號','(02)23119111');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('103','NG','FIRST_CATEGORY','091','忠孝路分行','台北市中正區忠孝東路2段94號','(02)23416111');
INSERT INTO BRANCH_INFORMATION(BRANCH,BRANCH_CODE,BRANCH_TYPE,ASSIGN_ZONE,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE) VALUES ('301','NZ','FIRST_CATEGORY','301','新竹分行','新竹市北區英明街3號','(03)5226111');

--國別檔
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('XA','0001','中華民國(OBU)','Taiwan(OBU)','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('TW','1001','中華民國','Taiwan','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('JP','1002','日本','Japan','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('KR','1003','大韓民國','Korea','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('HK','1004','香港','Hong Kong','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('SG','1009','新加坡','Singapore','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('KP','1019','北韓','Korea, Democratic People Republic of','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('CN','1020','中國大陸','China','1','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('IR','1212','伊朗','Iran','2','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('US','3002','美國','U.S.A','5','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('GB','5018','英國','United Kingdom','3','1');
INSERT INTO COUNTRY(COUNTRY_CODE,COUNTRY_NUMBER,CHN_NAME,ENG_NAME,CONTINENT_CODE,STATUS) VALUES ('ZA','6008','南非共和國','South Africa','4','1');

--編號控制檔
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR_SEQ','999',0);

INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','091',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','093',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','094',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','101',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','102',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','103',0);
INSERT INTO SERIAL_NUMBER(SYSTEM_TYPE,BRANCH,SERIAL_NO) VALUES ('IR','301',0);

--顧客資料檔
INSERT INTO CUSTOMER(AMEND_DATE,CUSTOMER_ID,BRANCH_ID,CHN_NAME,CONTACT_CHN_ADDR,COUNTRY,BENE_KIND,CREATE_DATE,EMAIL,CUST_TEL_NO,CUS_BIRTH_DATE,ENG_NAME,FCB_AGENT_ID,STATUS)
VALUES('2022-03-11','A123668221','093','胡小翔','台北市','中華民國','本國自然人','2022-03-11','i18002@firstbank.com.tw','0939999111','1985-02-09','HsiangLin','i18002','Y'),
('2022-02-16','M123886321','102','普小丁','莫斯科','俄羅斯','本國自然人','1952-10-07','i19030@firstbank.com.tw','0932123456','1952-10-07','KGB','i19030','Y'),
('2022-02-20','K123567789','222','澤小基','基輔','烏克蘭','本國自然人','1978-03-11','i20402@firstbank.com.tw','0937666555','1978-03-11','Ukrayina','i20402','Y'),
('2021-12-25','U123432654','158','馬小克','加州','美國','本國自然人','1971-06-28','i21503@firstbank.com.tw','0935235680','1971-06-28','Musk','i21503','Y'),
('2021-06-06','G123120999','166','宥小又','宜蘭縣','中華民國','本國自然人','2020-04-20','i22002@firstbank.com.tw','0933123321','2020-04-20','WillYo','i22002','Y');

--顧客帳號檔
INSERT INTO CUSTOMER_ACCOUNT(ACCOUNT_NUMBER,AMEND_DATE,BLACKLIST,BRANCH_ID,CREATE_DATE,CUSTOMER_SEQ_NO,FCB_AGENT_ID,STATUS)
VALUES('09368123456','2022-03-18','N','093','2020-01-02','1','i18002','Y'),
('10268123456','2022-02-17','N','102','2020-02-03','2','i19030','Y'),
('22240123456','2021-01-16','N','222','2020-03-04','3','i20402','Y'),
('15857123456','2021-12-15','N','158','2020-04-05','4','i21503','Y'),
('16668123456','2022-11-14','N','166','2020-05-06','5','i22002','Y');

--銀行檔
INSERT INTO BANK(BANK_SEQ_NO,SWIFTCODE,NAME) VALUES ('1','333','ooo');