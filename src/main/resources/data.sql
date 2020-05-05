insert into users (username, password) values ('admin', '$2a$10$8aCc1OCYmvKNuSJAjp41hupK1A3jqYTRqcZiKObivtkVXZv3WnpqC');
insert into groups (name) values ('ADMINISTRATORS');
insert into groups_users (groups_id, users_id) values (1, 1);