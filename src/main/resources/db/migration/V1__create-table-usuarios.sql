CREATE TABLE IF NOT EXISTS usuarios(
    id bigserial not null,
    numero_cartao varchar(20) unique not null,
    vencimento_cartao date not null,
    ativo boolean not null,
    primary key(id)
);