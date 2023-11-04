
INSERT INTO public.role(
	id, creation, description, role_name, status)
	VALUES (1, CURRENT_TIMESTAMP, 'administrator', 'ADMIN', 'ENABLE');
	
INSERT INTO public.role(
	id, creation, description, role_name, status)
	VALUES (2, CURRENT_TIMESTAMP, 'user with parking', 'SOCIO', 'ENABLE');
	
INSERT INTO public.account(
	creation, email, identification, last_name, name, pass, status, role_id)
	VALUES (CURRENT_TIMESTAMP, 'admin@mail.com', '1004911746', 'Cegarra', 'Deimer', '$2a$12$XccnBq62gQnTy8v9MtoWz.Yy2NOEsP5C8WkVUYSFSA8gxHwAc3Um.', 'ENABLE', 1);
	