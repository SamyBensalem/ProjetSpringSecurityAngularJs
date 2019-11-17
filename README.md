# ProjetSpringSecurityAngularJs
Projet realisé avec spring security et angularjs avec une bdd mysql, ce projet vise a gerer les droits en fonction des utilisateurs mis en bdd

nom de la bdd mysql :  enset-sec



requetes d'insertion des roles:PROF,ADMIN,ETUDIANT,SCOLARITE (le password est toujours '123')

INSERT INTO `users` (`username`, `actived`, `password`) VALUES ('admin', b'1', '123');
INSERT INTO `users` (`username`, `actived`, `password`) VALUES ('prof1', b'1', '123'), ('prof2', b'1', '123');
INSERT INTO `users` (`username`, `actived`, `password`) VALUES ('et1', b'1', '123'), ('et2', b'1', '123');
INSERT INTO `users` (`username`, `actived`, `password`) VALUES ('sco1', b'1', '123'), ('sco2', b'1', '123');
INSERT INTO `role` (`role`, `description`) VALUES ('ADMIN', NULL), ('PROF', NULL);
INSERT INTO `role` (`role`, `description`) VALUES ('ETUDIANT', NULL), ('SCOLARITE', NULL);
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('admin', 'ADMIN');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('admin', 'PROF');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('et1', 'ETUDIANT');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('et2', 'ETUDIANT');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('prof1', 'PROF');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('prof2', 'PROF');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('sco1', 'SCOLARITE');
INSERT INTO `users_roles` (`user_username`, `roles_role`) VALUES ('sco2', 'SCOLARITE');


datasource:

spring.datasource.url=jdbc:mysql://localhost:3308/enset-sec?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===============================
# = JPA / HIBERNATE
# ===============================

# Use spring.jpa.properties.* for Hibernate native properties (the prefix is
# stripped before adding them to the entity manager).

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update): with "update" the database
# schema will be automatically updated accordingly to java entities found in
# the project
spring.jpa.hibernate.ddl-auto = update

# Naming strategy
#spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect


# ===============================
# = SECURITY
# ===============================

#spring.security.user.name = admin
#spring.security.user.password = 123








