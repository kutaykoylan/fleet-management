INSERT INTO "DELIVERY_POINTS" ("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","NAME") VALUES (1, NOW(), NOW(),0,'Branch');
INSERT INTO "DELIVERY_POINTS" ("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","NAME")  VALUES (2, NOW(), NOW(),0,'Distribution Centre');
INSERT INTO "DELIVERY_POINTS" ("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","NAME")  VALUES (3,NOW(), NOW(),0,'Transfer Centre');

INSERT INTO "SACKS" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","STATE","DELIVERY_POINT") VALUES (NOW(), NOW(),0,'C725799','CREATED',2);
INSERT INTO "SACKS" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","STATE","DELIVERY_POINT") VALUES (NOW(), NOW(),0,'C725800','CREATED',3);


INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P7988000121',5,'CREATED',1);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P7988000122',5,'CREATED',1);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P7988000123',9,'CREATED',1);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000120',33,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000121',17,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000122',26,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000123',35,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000124',1,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000125',200,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P8988000126',50,'CREATED',2);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P9988000126',15,'CREATED',3);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P9988000127',16,'CREATED',3);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P9988000128',55,'CREATED',3);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P9988000129',28,'CREATED',3);
INSERT INTO "PACKAGES" ("CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE","DESI","STATE","DELIVERY_POINT") VALUES(NOW(), NOW(),0,'P9988000130',17,'CREATED',3);

INSERT INTO "PACKAGE_SACK"("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE_OF_PACKAGE","BARCODE_OF_SACK") VALUES (1, NOW(), NOW(),0,'P8988000122','C725799');
INSERT INTO "PACKAGE_SACK"("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE_OF_PACKAGE","BARCODE_OF_SACK") VALUES (2, NOW(), NOW(),0,'P8988000126','C725799');
INSERT INTO "PACKAGE_SACK"("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE_OF_PACKAGE","BARCODE_OF_SACK") VALUES (3, NOW(), NOW(),0,'P9988000128','C725800');
INSERT INTO "PACKAGE_SACK"("ID","CREATION_DATE","LAST_MODIFIED_DATE","VERSION","BARCODE_OF_PACKAGE","BARCODE_OF_SACK") VALUES (4, NOW(), NOW(),0,'P9988000129','C725800');