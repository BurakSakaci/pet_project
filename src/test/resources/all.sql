-- Drop sequences if they exist
DROP SEQUENCE IF EXISTS seq_animal_id;
DROP SEQUENCE IF EXISTS seq_application_id;
DROP SEQUENCE IF EXISTS seq_city_id;
DROP SEQUENCE IF EXISTS seq_post_id; -- Drop seq_post_id if it exists
DROP SEQUENCE IF EXISTS seq_product_id;
DROP SEQUENCE IF EXISTS seq_user_id;

-- Drop foreign key constraints
ALTER TABLE IF EXISTS application DROP CONSTRAINT IF EXISTS application_post_fk;
ALTER TABLE IF EXISTS application DROP CONSTRAINT IF EXISTS application_user_fk;
-- Remove the line that drops the "post_animal_fk" constraint if it doesn't exist.
ALTER TABLE IF EXISTS post DROP CONSTRAINT IF EXISTS post_user_fk;
ALTER TABLE IF EXISTS product DROP CONSTRAINT IF EXISTS product_animal_fk;
ALTER TABLE IF EXISTS "user" DROP CONSTRAINT IF EXISTS user_city_fk;

-- Drop tables with CASCADE
DROP TABLE IF EXISTS animal CASCADE;
DROP TABLE IF EXISTS application CASCADE;
DROP TABLE IF EXISTS city CASCADE;
DROP TABLE IF EXISTS post CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

-- Recreate sequences if they don't exist
CREATE SEQUENCE IF NOT EXISTS seq_animal_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_application_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_city_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_post_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_product_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_user_id START WITH 1 INCREMENT BY 1;

create table animal
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);
create table application
(
    is_active  boolean,
    apply_date timestamp(6),
    id         bigint not null,
    post_id    bigint not null,
    user_id    bigint not null,
    primary key (id)
);
create table city
(
    city_code bigint,
    id        bigint not null,
    city_name varchar(255),
    primary key (id)
);
create table post
(
    is_active    boolean,
    animal_id    bigint,
    created_date timestamp(6),
    id           bigint not null,
    updated_date timestamp(6),
    user_id      bigint not null,
    description  varchar(255),
    image_url    varchar(255),
    title        varchar(255),
    primary key (id)
);
create table product
(
    animal_id bigint       not null,
    id        bigint       not null,
    name      varchar(255) not null,
    primary key (id)
);
create table "user"
(
    city_id    bigint not null,
    id         bigint not null,
    address    varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    user_name  varchar(255),
    primary key (id)
);

-- application tablosuna CASCADE özelliği ekleyerek foreign key tanımlama
ALTER TABLE IF EXISTS application
    ADD CONSTRAINT application_post_fk
    FOREIGN KEY (post_id) REFERENCES post(id)
    ON
DELETE
CASCADE;

ALTER TABLE IF EXISTS application
    ADD CONSTRAINT application_user_fk
    FOREIGN KEY (user_id) REFERENCES "user"(id)
    ON
DELETE
CASCADE;

-- post tablosuna CASCADE özelliği ekleyerek foreign key tanımlama
ALTER TABLE IF EXISTS post
    ADD CONSTRAINT post_animal_fk
    FOREIGN KEY (animal_id) REFERENCES animal(id)
    ON
DELETE
CASCADE;

ALTER TABLE IF EXISTS post
    ADD CONSTRAINT post_user_fk
    FOREIGN KEY (user_id) REFERENCES "user"(id)
    ON
DELETE
CASCADE;

-- product tablosuna CASCADE özelliği ekleyerek foreign key tanımlama
ALTER TABLE IF EXISTS product
    ADD CONSTRAINT product_animal_fk
    FOREIGN KEY (animal_id) REFERENCES animal(id)
    ON
DELETE
CASCADE;

-- user tablosuna CASCADE özelliği ekleyerek foreign key tanımlama
ALTER TABLE IF EXISTS "user"
    ADD CONSTRAINT user_city_fk
    FOREIGN KEY (city_id) REFERENCES city(id)
    ON
DELETE
CASCADE;
INSERT INTO animal (id, name)
VALUES (1, 'Dog'),
       (2, 'Cat'),
       (3, 'Bird'),
       (4, 'Fish'),
       (5, 'Hamster'),
       (6, 'Turtle'),
       (7, 'Rabbit'),
       (8, 'Squirrel'),
       (9, 'Parrot'),
       (10, 'Koala');

-- Insert data into City table
INSERT INTO city (id, city_code, city_name)
VALUES (1, 34, 'Istanbul'),
       (2, 35, 'Ankara'),
       (3, 36, 'Izmir'),
       (4, 37, 'Manisa'),
       (5, 38, 'Antalya'),
       (6, 39, 'Mersin'),
       (7, 40, 'Kayseri'),
       (8, 41, 'Erzurum'),
       (9, 42, 'Canakkale'),
       (10, 43, 'Gaziantep');


INSERT INTO "user" (city_id, id, address, first_name, last_name, password, user_name)
VALUES (1, 1, '123 Main St', 'John', 'Doe', 'password123', 'john_doe'),
       (2, 2, '456 Oak Ave', 'Jane', 'Smith', 'pass456', 'jane_smith'),
       (3, 3, '789 Pine Blvd', 'Robert', 'Johnson', 'secure789', 'robert_j'),
       (4, 4, '101 Elm Ln', 'Emily', 'Williams', 'safe101', 'emily_w'),
       (5, 5, '202 Cedar Rd', 'Michael', 'Brown', 'key202', 'michael_b'),
       (6, 6, '303 Spruce Ct', 'Jennifer', 'Davis', 'lock303', 'jennifer_d'),
       (7, 7, '404 Birch Dr', 'Daniel', 'Martinez', 'code404', 'daniel_m'),
       (8, 8, '505 Maple Ave', 'Sarah', 'Anderson', 'secret505', 'sarah_a'),
       (9, 9, '606 Pine St', 'Christopher', 'Taylor', 'confidential606', 'chris_t'),
       (10, 10, '707 Oak Blvd', 'Megan', 'Moore', 'private707', 'megan_m');

-- Update data in post table
INSERT INTO post (id, is_active, animal_id, created_date, updated_date, user_id, description, image_url, title)
VALUES (1, true, 1, NOW(), NOW(), 1, 'A loyal dog playing in the park',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download.jpeg', 'Playful Dog'),
       (2, true, 2, NOW(), NOW(), 2, 'Curious cat exploring its surroundings',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(1).jpeg', 'Adventurous Cat'),
       (3, true, 3, NOW(), NOW(), 3, 'Colorful bird perched on a branch',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(2).jpeg', 'Vibrant Bird'),
       (4, true, 4, NOW(), NOW(), 4, 'Beautiful fish swimming in a coral reef',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(3).jpeg', 'Underwater Fish'),
       (5, true, 5, NOW(), NOW(), 5, 'Cute hamster enjoying its wheel exercise',
        'https://raw.githubusercontent.com/emrgry/petDB/main/7232924c-f50d-11eb-97f9-89051db5b6c1_image_hires_185341.jpg',
        'Active Hamster'),
       (6, true, 6, NOW(), NOW(), 6, 'Charming turtle basking in the sun',
        'https://raw.githubusercontent.com/emrgry/petDB/main/images.jpeg', 'Sunbathing Turtle'),
       (7, true, 7, NOW(), NOW(), 7, 'Energetic rabbit hopping in a garden',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(4).jpeg', 'Garden Rabbit'),
       (8, true, 8, NOW(), NOW(), 8, 'Playful squirrel gathering nuts in a park',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(5).jpeg', 'Nut-Gathering Squirrel'),
       (9, true, 9, NOW(), NOW(), 9, 'My Beautiful Parret',
        'https://raw.githubusercontent.com/emrgry/petDB/main/downloadp.jpeg', 'Speaking Parrot'),
       (10, true, 10, NOW(), NOW(), 10, 'Sleeping koala in a eucalyptus tree',
        'https://raw.githubusercontent.com/emrgry/petDB/main/download%20(6).jpeg', 'Dreaming Koala');



INSERT INTO product (animal_id, id, name)
VALUES (1, 1, 'Lion Toy'),
       (2, 2, 'Elephant Plush'),
       (3, 3, 'Giraffe Puzzle'),
       (4, 4, 'Tiger Figurine'),
       (5, 5, 'Penguin Stuffed Animal'),
       (6, 6, 'Kangaroo Keychain'),
       (7, 7, 'Zebra Mug'),
       (8, 8, 'Dolphin Backpack'),
       (9, 9, 'Panda Poster'),
       (10, 10, 'Koala Magnet');


--------User tablosundan kullanıcı ismi güncelleme olcak.

CREATE
OR REPLACE FUNCTION updateUserUsername(user_id_param bigint, new_username_param varchar)
RETURNS void AS $$
BEGIN
UPDATE "user"
SET user_name = new_username_param
WHERE id = user_id_param;
END;
$$
LANGUAGE plpgsql;

--SELECT updateUserUsername(1, 'YeniKullaniciAdi');




--------Giriş kısmında bir sorgu yapılcak. kullanıcı adı ve password kontrolü için bunu
CREATE TYPE user_login_result AS (
    login_status INTEGER -- 1: Giriş başarılı, 2: Password yanlış, 3: Kullanıcı yok
    );


CREATE
OR REPLACE FUNCTION check_user_login(p_username VARCHAR(255), p_password VARCHAR(255))
RETURNS user_login_result
AS $$
DECLARE
result user_login_result;
    user_id
INT;
BEGIN
    -- Kullanıcı adı ve şifre kontrolü
SELECT id
INTO user_id
FROM "user"
WHERE user_name = p_username;

IF
user_id IS NOT NULL THEN
        -- Kullanıcı adı doğru, şifreyi kontrol et
        IF EXISTS (SELECT 1 FROM "user" WHERE id = user_id AND password = p_password) THEN
            -- Kullanıcı adı ve şifre doğru
            result.login_status := 1;
ELSE
            -- Kullanıcı adı doğru, ancak şifre yanlış
            result.login_status := 2;
END IF;
ELSE
        -- Kullanıcı adı bulunamadı
        result.login_status := 3;
END IF;

RETURN result;
END;
$$
LANGUAGE plpgsql;

-- Kullanıcı adı ve şifresi doğru olan bir çağrı
--SELECT * FROM check_user_login('john_doe', 'password123');

-- Kullanıcı adı doğru ancak şifresi yanlış olan bir çağrı
--SELECT * FROM check_user_login('john_doe', 'wrong_password');

-- Kullanıcı adı bulunamayan bir çağrı
--SELECT * FROM check_user_login('nonexistent_user', 'any_password');






-----------user tablosundaki kayıtları ekrana bastırmak için cursor yapısı
CREATE
OR REPLACE FUNCTION print_users()
RETURNS void
AS $$
DECLARE
user_record RECORD;
    user_cursor
CURSOR FOR
SELECT *
FROM "user";
BEGIN
OPEN user_cursor;
LOOP
FETCH user_cursor INTO user_record;
        EXIT
WHEN NOT FOUND;


        RAISE
NOTICE 'User ID: %, Name: %, City ID: %', user_record.id, user_record.first_name || ' ' || user_record.last_name, user_record.city_id;
END LOOP;
CLOSE user_cursor;
END;
$$
LANGUAGE plpgsql;


--SELECT print_users();



----- user id yi verip bilgilerini alsak hangi hayvanı ve hangi city id ye sahip olduğu
CREATE
OR REPLACE FUNCTION print_user_info(user_id_param bigint)
RETURNS void
AS $$
DECLARE
user_record RECORD;
    user_cursor
CURSOR FOR
SELECT u.id   AS user_id,
       u.first_name,
       u.last_name,
       u.address,
       u.city_id,
       a.name AS animal_name,
       c.city_name
FROM "user" u
         LEFT JOIN post p ON u.id = p.user_id
         LEFT JOIN animal a ON p.animal_id = a.id
         LEFT JOIN city c ON u.city_id = c.id
WHERE u.id = user_id_param;
BEGIN
OPEN user_cursor;
LOOP
FETCH user_cursor INTO user_record;
        EXIT
WHEN NOT FOUND;

        -- Kullanıcı ve hayvan bilgilerini ekrana bastırma
        RAISE
NOTICE 'User ID: %, Name: % %, Address: %, City ID: %, Animal: %, City: %',
                     user_record.user_id, user_record.first_name, user_record.last_name,
                     user_record.address, user_record.city_id, user_record.animal_name, user_record.city_name;
END LOOP;
CLOSE user_cursor;
END;
$$
LANGUAGE plpgsql;

--SELECT print_user_info(1); -- 1  user_id'yi kullanın



--"post" tablosundaki hayvan türlerine göre ortalama başlık uzunluğunu 20 karakterden fazla olanları listeleme
SELECT animal_id, AVG(LENGTH(title)) AS avg_title_length
FROM post
GROUP BY animal_id
HAVING AVG(LENGTH(title)) > 20;

--user_id için bilgileri elde edebilirsiniz VİEW
CREATE
OR REPLACE VIEW user_info_view AS
SELECT u.id   AS user_id,
       u.first_name,
       u.last_name,
       u.address,
       u.city_id,
       a.name AS animal_name,
       c.city_name
FROM "user" u
         LEFT JOIN post p ON u.id = p.user_id
         LEFT JOIN animal a ON p.animal_id = a.id
         LEFT JOIN city c ON u.city_id = c.id;

--SELECT * FROM user_info_view WHERE user_id = 1; -- 1 yerine istediğiniz user_id'yi kullanın


--
--  bir şehirdeki aktif hayvan ilanlarını listeleyen bir görünüm BUDA VİEW
CREATE
OR REPLACE VIEW active_animal_posts_by_city_view AS
SELECT p.id   AS post_id,
       p.title,
       p.description,
       p.image_url,
       p.created_date,
       u.user_name,
       a.name AS animal_name,
       u.city_id,
       c.city_name
FROM post p
         JOIN "user" u ON p.user_id = u.id
         JOIN animal a ON p.animal_id = a.id
         JOIN city c ON u.city_id = c.id
WHERE p.is_active = true;


--SELECT * FROM active_animal_posts_by_city_view WHERE city_id = 1;


--bir kullanıcının sahip olduğu hayvanlar ve bu hayvanların ilanları ile ilgili bilgi BU DA VİEW
CREATE
OR REPLACE VIEW user_animals_view AS
SELECT u.id   AS user_id,
       u.user_name,
       a.id   AS animal_id,
       a.name AS animal_name,
       p.id   AS post_id,
       p.title,
       p.description,
       p.image_url
FROM "user" u
         LEFT JOIN post p ON u.id = p.user_id
         LEFT JOIN animal a ON p.animal_id = a.id;


--SELECT * FROM user_animals_view WHERE user_id = 1;

--belirli bir id'ye sahip kullanıcıyı silen bir fonksiyon
CREATE
OR REPLACE FUNCTION delete_user_by_id(user_id_param INT)
RETURNS VOID
AS $$
BEGIN
DELETE
FROM "user"
WHERE id = user_id_param;
END;
$$
LANGUAGE plpgsql;

SELECT delete_user_by_id(1);
-- 1 yerine silmek istediğiniz kullanıcı ID'sini kullanın

--belirli bir id'ye sahip ilanı silmek
CREATE
OR REPLACE FUNCTION delete_post_by_id(post_id_param INT)
RETURNS VOID
AS $$
BEGIN
DELETE
FROM post
WHERE id = post_id_param;
END;
$$
LANGUAGE plpgsql;

SELECT delete_post_by_id(1);

--İlanları güncellemek için bir fonksiyon
CREATE
OR REPLACE FUNCTION update_post(post_id_param INT, new_title VARCHAR(255), new_description VARCHAR(255), new_image_url VARCHAR(255))
RETURNS VOID
AS $$
BEGIN
UPDATE post
SET title       = new_title,
    description = new_description,
    image_url   = new_image_url
WHERE id = post_id_param;
END;
$$
LANGUAGE plpgsql;
-- Önce ilan güncelleme fonksiyonunu çağırarak bir ilanı güncelleyelim
SELECT update_post(1, 'Yeni Başlık', 'Yeni Açıklama', 'Yeni Resim URL');
-- 1 yerine güncellemek istediğiniz ilan ID'sini kullanın


--Kullanıcı bilgilerini güncellemek
CREATE
OR REPLACE FUNCTION update_user(user_id_param INT, new_first_name VARCHAR(255), new_last_name VARCHAR(255), new_address VARCHAR(255), new_password VARCHAR(255))
RETURNS VOID
AS $$
BEGIN
UPDATE "user"
SET first_name = new_first_name,
    last_name  = new_last_name,
    address    = new_address,
    password   = new_password
WHERE id = user_id_param;
END;
$$
LANGUAGE plpgsql;