
CREATE TABLE public.role
(
    id bigserial NOT NULL,
    creation timestamp(6) without time zone,
    description character varying(255) ,
    role_name character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT ukiubw515ff0ugtm28p8g3myt0h UNIQUE (role_name)
);

CREATE TABLE public.account
(
    id bigserial NOT NULL,
    creation timestamp(6) without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    identification character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    pass character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    role_id bigint,
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT uka5ie3fgonyw716ws6gvh8i4b9 UNIQUE (email, identification),
    CONSTRAINT fkd4vb66o896tay3yy52oqxr9w0 FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.parking
(
    id bigserial NOT NULL,
    creation timestamp(6) without time zone,
    name character varying(255) COLLATE pg_catalog."default",
    value_hour double precision,
    vehicle_limit integer,
    user_id bigint,
    CONSTRAINT parking_pkey PRIMARY KEY (id),
    CONSTRAINT ukob0jqom83fxndso4eospoork3 UNIQUE (name),
    CONSTRAINT fkb9r0qxrhsh79dxbhemltkxbi5 FOREIGN KEY (user_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.vehicle
(
    id bigserial NOT NULL,
    creation timestamp(6) without time zone,
    date_admission timestamp(6) without time zone,
    plate character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    parking_id bigint,
    CONSTRAINT vehicle_pkey PRIMARY KEY (id),
    CONSTRAINT ukdptrc1lh70j3qrg5v38fmmw7g UNIQUE (plate),
    CONSTRAINT fk5hr3la0gyeb2eui7p64o4w7f3 FOREIGN KEY (parking_id)
        REFERENCES public.parking (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.parking_history
(
    id bigserial NOT NULL,
    creation timestamp(6) without time zone,
    departure_date timestamp(6) without time zone,
    parking_id bigint,
    vehicle_id bigint,
    date_admission timestamp(6) without time zone,
    amount bigint,
    CONSTRAINT parking_history_pkey PRIMARY KEY (id),
    CONSTRAINT fkfptml0ehjc8vfpg34if4onqtn FOREIGN KEY (vehicle_id)
        REFERENCES public.vehicle (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkj5g6e3x9h92krc9doryvnifb2 FOREIGN KEY (parking_id)
        REFERENCES public.parking (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


INSERT INTO public.role(
	id, creation, description, role_name, status)
	VALUES (1, CURRENT_TIMESTAMP, 'administrator', 'ADMIN', 'ENABLE');
	
INSERT INTO public.role(
	id, creation, description, role_name, status)
	VALUES (2, CURRENT_TIMESTAMP, 'user with parking', 'SOCIO', 'ENABLE');
	
INSERT INTO public.account(
	creation, email, identification, last_name, name, pass, status, role_id)
	VALUES (CURRENT_TIMESTAMP, 'admin@mail.com', '1004911746', 'Cegarra', 'Deimer', '$2a$12$XccnBq62gQnTy8v9MtoWz.Yy2NOEsP5C8WkVUYSFSA8gxHwAc3Um.', 'ENABLE', 1);
	