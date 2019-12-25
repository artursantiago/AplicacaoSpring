-- Table: comum.pessoa

-- DROP TABLE comum.pessoa;

CREATE TABLE comum.pessoa
(
    id integer NOT NULL DEFAULT nextval('comum.pessoa_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pessoa_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE comum.pessoa
    OWNER to postgres;



-- Table: graduacao.aluno

-- DROP TABLE graduacao.aluno;

CREATE TABLE graduacao.aluno
(
    id integer NOT NULL DEFAULT nextval('graduacao.aluno_seq'::regclass),
    matricula character varying(15) COLLATE pg_catalog."default",
    ano_de_entrada character varying(4) COLLATE pg_catalog."default",
    id_pessoa integer NOT NULL,
    CONSTRAINT aluno_pkey PRIMARY KEY (id),
    CONSTRAINT aluno_id_pessoa_fkey FOREIGN KEY (id_pessoa)
        REFERENCES comum.pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE graduacao.aluno
    OWNER to postgres;