
DROP TABLE IF EXISTS subject,test,question,answer,question_answer,role,app_user,user_role CASCADE ;
CREATE TABLE subject
(
    subject_id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(100),
    description CHARACTER VARYING(250),
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE test
(
    test_id BIGSERIAL PRIMARY KEY ,
    name CHARACTER VARYING(100),
    subject_id BIGINT,
    mode CHARACTER VARYING(10),
    created TIMESTAMP,
    updated TIMESTAMP,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);

CREATE TABLE question
(
    question_id BIGSERIAL PRIMARY KEY,
    text CHARACTER VARYING(250),
    description CHARACTER VARYING(255),
    mode CHARACTER VARYING(100),
    subject_id BIGINT,
    test_id BIGINT,
    created TIMESTAMP,
    updated TIMESTAMP,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (test_id) REFERENCES test(test_id)
);

CREATE TABLE answer
(
        answer_id BIGSERIAL PRIMARY KEY,
        text CHARACTER VARYING(250),
        created TIMESTAMP,
        updated TIMESTAMP
);

CREATE TABLE question_answer
(
    question_id BIGINT,
    answer_id BIGINT,
    FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (answer_id) REFERENCES answer(answer_id)
);


CREATE TABLE role
(
    role_id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(250),
    created TIMESTAMP,
    updated TIMESTAMP
);
CREATE TABLE app_user
(
    user_id BIGSERIAL PRIMARY KEY ,
    username CHARACTER VARYING(250),
    password CHARACTER VARYING(250),
    email CHARACTER VARYING(250),
    phone CHARACTER VARYING(250),
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

INSERT INTO subject (name, description, created) VALUES ('Java','Строго типизированный объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('JavaScript','Мультипарадигменный язык программирования. Поддерживает объектно-ориентированный, императивный и функциональный стили.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Python','Высокоуровневый язык программирования общего назначения, ориентированный на повышение производительности разработчика и читаемости кода.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('C++','Компилируемый, статически типизированный язык программирования общего назначения.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('C','Компилируемый статически типизированный язык программирования общего назначения, разработанный в 1969—1973 годах сотрудником Bell Labs Деннисом Ритчи как развитие языка Би.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Golang','Компилируемый многопоточный язык программирования, разработанный внутри компании Google.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('PHP','Скриптовый язык[14] общего назначения, интенсивно применяемый для разработки веб-приложений.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('HTML','Стандартизированный язык разметки документов во Всемирной паутине.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('CSS','Формальный язык описания внешнего вида документа, написанного с использованием языка разметки.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Ruby','Динамический, рефлективный, интерпретируемый высокоуровневый язык программирования.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Математика','Наука об отношениях между объектами, о которых ничего не известно, кроме описывающих их некоторых свойств, — именно тех, которые в качестве аксиом положены в основание той или иной математической теории.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Физика','Область естествознания: наука о наиболее общих законах природы, о материи, её структуре, движении и правилах трансформации.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Английский язык','Язык англо-фризской подгруппы западной группы германской ветви индоевропейской языковой семьи. ','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Финский язык','Язык финнов, относящийся к прибалтийско-финской подгруппе финно-волжской группы финно-угорских языков; является агглютинативным языком номинативного строя со значительными элементами флективности.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('Рыболовство','Деятельность по добыче (вылову) водных биоресурсов и в предусмотренных законом случаях по приёмке, обработке, перегрузке, транспортировке, хранению и выгрузке уловов водных биоресурсов, производству рыбной и иной продукции из водных биоресурсов.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('История','Область знаний, а также гуманитарная наука, занимающаяся изучением человека (его деятельности, состояния, мировоззрения, социальных связей, организаций и так далее) в прошлом.','2020-05-28');
INSERT INTO subject (name, description, created) VALUES ('География','Комплекс естественных и общественных наук, изучающих структуру, функционирование и эволюцию географической оболочки, взаимодействие и распределение в пространстве природных и природно-общественных геосистем и их компонентов.','2020-05-28');