CREATE SEQUENCE SEQ_USUARIOSMB
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE USUARIOS_MB (
    USUARIO_ID INTEGER DEFAULT SEQ_USUARIOSMB.NEXTVAL NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) UNIQUE NOT NULL,
    SENHA VARCHAR(60) NOT NULL,
    ROLE VARCHAR2(50) DEFAULT 'USER'
    );