-- Insert into users
INSERT INTO users (email, password) VALUES
                                        ('superadmin1@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi'),

                                        ('admin1@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi'),
                                        ('admin2@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi'),

                                        ('professor1@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi'),
                                        ('professor2@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi'),

                                        ('student1@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi');

-- Insert roles
INSERT INTO roles(name, super_admin, admin, professor, student)
VALUES
    ('ROLE_ADD_FACULTY', 1, 0, 0, 0),
    ('ROLE_FIND_FACULTY', 1, 1, 0, 0),
    ('ROLE_GET_ALL_FACULTIES', 1, 1, 0, 0),
    ('ROLE_UPDATE_FACULTY', 1, 0, 0, 0),
    ('ROLE_DELETE_FACULTY', 1, 0, 0, 0),

    ('ROLE_ADD_ADMIN', 1, 0, 0, 0),
    ('ROLE_FIND_ADMIN', 1, 0, 0, 0),
    ('ROLE_GET_ALL_ADMINS', 1, 0, 0, 0),
    ('ROLE_UPDATE_ADMIN', 1, 1, 0, 0),
    ('ROLE_FIND_ADMINS_BY_FACULTY', 1, 0, 0, 0),

    ('ROLE_ADD_PROFESSOR', 0, 1, 0, 0),
    ('ROLE_FIND_PROFESSOR', 0, 1, 0, 0),
    ('ROLE_GET_ALL_PROFESSORS', 0, 1, 0, 0),
    ('ROLE_UPDATE_PROFESSOR', 0, 1, 0, 0),
    ('ROLE_FIND_PROFESSORS_BY_FACULTY', 0, 1, 0, 0),

('ROLE_ADD_STUDENT', 0, 1, 0, 0),
('ROLE_FIND_STUDENT', 0, 1, 0, 0),
('ROLE_GET_ALL_STUDENTS', 0, 1, 0, 0),
('ROLE_UPDATE_STUDENT', 0, 1, 0, 0),
('ROLE_FIND_STUDENT_BY_FACULTY', 0, 1, 0, 0),
('ROLE_GET_PROFILE', 0, 0, 0, 1),


    ('ROLE_ADD_GROUP', 1, 0, 0, 0),
    ('ROLE_FIND_GROUP', 1, 1, 0, 0),
    ('ROLE_GET_ALL_GROUPS', 1, 1, 0, 0),
    ('ROLE_GET_ALL_GROUPS_BY_FACULTY', 0, 1, 0, 0),
    ('ROLE_UPDATE_GROUP', 1, 0, 0, 0),
    ('ROLE_FIND_GROUP_BY_FACULTY', 1, 0, 0, 0),
    ('ROLE_DELETE_GROUP', 1, 0, 0, 0);

-- Assign roles automatically based on user type
INSERT INTO user_roles (user_id, role_id)
select 1,id from roles where super_admin=1;

INSERT INTO user_roles (user_id, role_id)
select 2,id from roles where admin=1;

INSERT INTO user_roles (user_id, role_id)
select 3,id from roles where admin=1;

INSERT INTO user_roles (user_id, role_id)
select 4,id from roles where professor=1;

INSERT INTO user_roles (user_id, role_id)
select 5,id from roles where professor=1;

INSERT INTO user_roles (user_id, role_id)
select 6,id from roles where student=1;

INSERT INTO faculties(name, description, code, dean, established_year, phone, contact_email, created_at, updated_at)
values ('Faculty of Engineering', 'Responsible for all engineering programs and departments.', 'ENG', 'Dr. Emily Carter',
        1985, '+994123456789', 'eng@university.edu', '1985-05-09', '1985-05-09'),
       ('Faculty of Science', 'Responsible for all science programs including Physics, Chemistry, and Biology.', 'SCI', 'Dr. John Smith',
        1990, '+994122345678', 'sci@university.edu', '1990-09-15', '1990-09-15'),
       ('Faculty of Medicine', 'Offers medical programs and related healthcare training.', 'MED', 'Dr. Sarah Williams',
        2000, '+994125678901', 'med@university.edu', '2000-11-20', '2000-11-20'),
       ('Faculty of Arts', 'Includes programs in literature, history, and the arts.', 'ART', 'Dr. Richard Brown',
        1980, '+994126789012', 'arts@university.edu', '1980-03-10', '1980-03-10'),
       ('Faculty of Business', 'Offers programs in business administration, management, and economics.', 'BUS', 'Dr. Linda Johnson',
        1995, '+994128901234', 'business@university.edu', '1995-07-25', '1995-07-25'),
       ('Faculty of Law', 'Provides legal studies and training in law.', 'LAW', 'Dr. Michael Lee',
        1988, '+994123216543', 'law@university.edu', '1988-06-30', '1988-06-30');

INSERT INTO admins(name, surname, phone, faculty_id, user_id)
values ('Murad', 'Muradov', '+994556781111', 1, 2),
 ('Agha', 'Aghayev', '+994778990909', 2, 3);

INSERT INTO professors (name, surname, phone, title, department, experience, faculty_id, user_id)
VALUES
-- Professor 1: Engineering
('Elchin', 'Aliyev', '+994501112233', 'Associate Professor', 'Computer Engineering', 12, 1, 4),
-- Professor 2: Science
('Leyla', 'Mammadova', '+994502223344', 'Dr.', 'Physics', 9, 2, 5);

