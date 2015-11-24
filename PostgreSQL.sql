
---------------------------------------------------------
-- Financial Data Grabber
---------------------------------------------------------

---------------------------------------------------------
-- USUARIO
---------------------------------------------------------
CREATE ROLE fdg_user LOGIN
  ENCRYPTED PASSWORD 'md5a0250204c2a5655c02098718082b4e5c'
  NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;

---------------------------------------------------------
-- DATA BASE
---------------------------------------------------------
CREATE DATABASE fdg_db
  WITH OWNER = fdg_user
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE fdg_db
  IS 'Financial Data Grabber (data-base)';

---------------------------------------------------------
-- Alterar conexao para fdg_db com usuario postgres (new connection)
---------------------------------------------------------

---------------------------------------------------------
-- SEQUENCE ADMINISTRADOR
---------------------------------------------------------
CREATE SEQUENCE fdg_administrador_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9999999
  START 1
  CACHE 1;
  
---------------------------------------------------------
-- TABLE ADMINISTRADOR
---------------------------------------------------------
CREATE TABLE fdg_administrador
(
  id bigint NOT NULL DEFAULT nextval('fdg_administrador_seq'::regclass),
  cnpj bigint NOT NULL,
  razao_social character(200) NOT NULL,
  CONSTRAINT "fdg_administrador_pk" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE fdg_administrador
  OWNER TO postgres;
  
COMMENT ON TABLE fdg_administrador
  IS 'Administrador';

CREATE UNIQUE INDEX fdg_administrador_index_1
  ON fdg_administrador
  USING btree
  (cnpj);

CREATE UNIQUE INDEX fdg_administrador_index_2
  ON fdg_administrador
  USING btree
  (razao_social COLLATE pg_catalog."default");

INSERT INTO fdg_administrador(cnpj, razao_social) VALUES (61809182000130,'CREDIT SUISSE HEDGING-GRIFFO CORRETORA DE VALORES S.A.');

SELECT * FROM fdg_administrador;

---------------------------------------------------------
-- SEQUENCE FUNDO
---------------------------------------------------------
CREATE SEQUENCE fdg_fundo_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9999999
  START 1
  CACHE 1;
  
---------------------------------------------------------
-- TABLE FUNDO
---------------------------------------------------------
CREATE TABLE fdg_fundo
(
  id bigint NOT NULL DEFAULT nextval('fdg_fundo_seq'::regclass),
  cnpj bigint NOT NULL,
  razao_social character(120) NOT NULL,
  id_administrador bigint NOT NULL,
  dt_inicio date,
  dt_constituicao date,
  classe character(40) NOT NULL,
  condominio character(12) NOT NULL,
  indicador_desempenho character(60),
  taxa_performance character(120),
  fl_exclusivo int CONSTRAINT fdg_fundo_c_fl_exclusivo CHECK (fl_exclusivo in (0,1)),
  fl_cotas int CONSTRAINT fdg_fundo_c_cotas CHECK (fl_cotas in (0,1)),
  fl_tratamento_tributario int CONSTRAINT fdg_fundo_c_fl_tratamento_tributario CHECK (fl_tratamento_tributario in (0,1)),
  fl_investidores_qualificados int CONSTRAINT fdg_fundo_c_fl_investidores_qualificados CHECK (fl_investidores_qualificados in (0,1)),
  CONSTRAINT "fdg_fundo_pk" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


ALTER TABLE fdg_fundo
  ADD CONSTRAINT fdg_fundo_fk_administrator FOREIGN KEY (id_administrador) REFERENCES fdg_administrador (id)
   ON UPDATE NO ACTION ON DELETE RESTRICT;
   
ALTER TABLE fdg_fundo
  OWNER TO postgres;
  
COMMENT ON TABLE fdg_fundo
  IS 'Fundo';

CREATE UNIQUE INDEX fdg_fundo_index_1
  ON fdg_fundo
  USING btree
  (cnpj);

CREATE UNIQUE INDEX fdg_fundo_index_2
  ON fdg_fundo
  USING btree
  (razao_social COLLATE pg_catalog."default");

CREATE INDEX fdg_fundo_index_3
  ON fdg_fundo
  USING btree
  (id_administrador);

INSERT INTO fdg_fundo (cnpj, razao_social, id_administrador, dt_inicio, dt_constituicao, classe, condominio, fl_exclusivo, 
	fl_cotas, fl_tratamento_tributario, fl_investidores_qualificados ) 
VALUES (08613315000116,'CSHG SENTA PUA FUNDO DE INVESTIMENTO EM AÇÕES', 1, '2007-04-24', '2007-03-07', 'Fundo de Ações', 'Aberto', 0,
	0, 0, 0);
  
SELECT * FROM fdg_fundo;

---------------------------------------------------------
-- TABLE COTAS
---------------------------------------------------------
CREATE TABLE fdg_cota
(
  id_fundo bigint NOT NULL,
  dt_cota date NOT NULL,
  vl_cota double precision NOT NULL,
  CONSTRAINT "fdg_cota_pk" PRIMARY KEY (id_fundo,dt_cota)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE fdg_cota
  ADD CONSTRAINT fdg_cota_fk_fundo FOREIGN KEY (id_fundo) REFERENCES fdg_fundo (id)
   ON UPDATE NO ACTION ON DELETE RESTRICT;
   
ALTER TABLE fdg_cota
  OWNER TO postgres;
  
COMMENT ON TABLE fdg_cota
  IS 'Cotas';

CREATE UNIQUE INDEX fdg_cota_index_1
  ON fdg_cota
  USING btree
  (dt_cota,id_fundo);

INSERT INTO fdg_cota (id_fundo, dt_cota, vl_cota ) VALUES (1, '2015-11-24', 1.123456789);

SELECT * FROM fdg_cota;
