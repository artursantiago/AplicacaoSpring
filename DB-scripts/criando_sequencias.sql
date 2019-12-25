-- SEQUENCE: comum.pessoa_seq

-- DROP SEQUENCE comum.pessoa_seq;

CREATE SEQUENCE comum.pessoa_seq
    INCREMENT 1
    START 12
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE comum.pessoa_seq
    OWNER TO postgres;



-- SEQUENCE: graduacao.aluno_seq

-- DROP SEQUENCE graduacao.aluno_seq;

CREATE SEQUENCE graduacao.aluno_seq
    INCREMENT 1
    START 12
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE graduacao.aluno_seq
    OWNER TO postgres;