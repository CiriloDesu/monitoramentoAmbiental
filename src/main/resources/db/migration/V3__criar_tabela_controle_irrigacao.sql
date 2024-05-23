CREATE SEQUENCE SEQ_IRRIGACAO
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE tbl_controle_irrigacao (
    irrigacao_id INTEGER DEFAULT SEQ_IRRIGACAO.NEXTVAL NOT NULL,
    area VARCHAR2(100),
    status VARCHAR2(20),
    programado_para TIMESTAMP,
    duracao_em_minutos NUMBER,
    temperatura NUMBER,
    umidade NUMBER,
    PRIMARY KEY (irrigacao_id)
);

