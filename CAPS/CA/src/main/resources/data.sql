
INSERT INTO admin (first_name, last_name, user_name, user_password) VALUES('A_firstName','A_lastName','admin1','12345');

INSERT INTO lecturer (lecturer_num, first_name, last_name, user_name, user_password, title) VALUES ('l1','L_firstName1', 'L_lastname1', 'lecturer1', '12345','Senior');
INSERT INTO lecturer (lecturer_num, first_name, last_name, user_name, user_password, title) VALUES ('l2','L_firstName2', 'L_lastname2', 'lecturer2', '12345','Principal');
INSERT INTO lecturer (lecturer_num, first_name, last_name, user_name, user_password, title) VALUES ('l3','L_firstName3', 'L_lastname3', 'lecturer3', '12345','Principal');

INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c1',6,'An object-oriented programming language that produces software for multiple platforms',0,'Java',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c2',8,'Create interactive web pages and dynamically display content to users using JavaScript',0,'JavaScript',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c3',6,'Develop iOS and macOS applications with Swift',0,'Swift',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c4',8,'Scala is a functional programming language that allows engineers to elevate the quality of their code to resemble pure math',0,'Scala',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c5',6,'A core language favoured by Google which is perfect for building web servers, data pipelines, and even machine-learning packages',0,'Go',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c6',4,'A user-friendly programming language popular among beginners, having syntax which is clear, intuitive, and almost English-like',0,'Python',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c7',6,'Elm is a functional programming language that allows developers to create client-side interfaces without the declarative trappings of HTML and CSS',0,'Elm',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c8',4,'A scripting language that’s commonly used for web development. In particular, it’s used as the basis for the popular Ruby on Rails web application framework',0,'Ruby',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c9',8,'C# (pronounced C Sharp) is a general-purpose, object-oriented language built on the foundations of C',0,'C#',20);
INSERT INTO  course(course_num, credits, description, enrollment, NAME, size) VALUES ('c10',6,'Developed by the Mozilla Corporation, which is intended primarily for low-level systems programming, but with an added emphasis on speed and security',0,'Rust',20);

INSERT INTO student (enrollment_date,student_num, first_name, last_name, user_name, user_password) VALUES ('2021-07-02','s1', 'S_firstName1', 'S_lastname1', 'student1', '12345');
INSERT INTO student (enrollment_date,student_num, first_name, last_name, user_name, user_password) VALUES ('2021-07-02','s2', 'S_firstName2', 'S_lastname2', 'student2', '12345');
INSERT INTO student (enrollment_date,student_num, first_name, last_name, user_name, user_password) VALUES ('2021-07-02','s3', 'S_firstName3', 'S_lastname3', 'student3', '12345');
INSERT INTO student (enrollment_date,student_num, first_name, last_name, user_name, user_password) VALUES ('2021-07-02','s4', 'S_firstName4', 'S_lastname4', 'student4', '12345');
INSERT INTO student (enrollment_date,student_num, first_name, last_name, user_name, user_password) VALUES ('2021-07-02','s5', 'S_firstName5', 'S_lastname5', 'student5', '12345');