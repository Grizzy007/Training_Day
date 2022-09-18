insert into category (id, name)
values (1,'World Class'),
       (2,'Qualified'),
       (3,'International Pro'),
       (4,'Certified');
insert into trainers (id, name, surname, category_id)
values (1,'Dmitriy','Sergeev',3),
       (2,'Viktor','Morozov',2),
       (3,'Ivan','Sidorov',1);
insert into users (id, age, login, nickname, pass, user_role)
values (1,18,'admin@email.com','best_admin','$2a$12$W9J7OBtXTXKgbJ0rYeTgHenhBwKk5t1xcDGXC7//j.NtOF1zin0vq','ADMIN');