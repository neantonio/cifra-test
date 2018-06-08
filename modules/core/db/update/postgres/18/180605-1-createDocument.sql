create table CIFRA_DOCUMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DOC_STATUS integer not null,
    WAREHOUSE_ID uuid,
    CELL varchar(50),
    CONTRAGENT_ID uuid,
    COMPANY_ID uuid not null,
    DIVISION_ID uuid,
    DOC_TYPE_ID uuid not null,
    FILE_ID uuid,
    GOT_ORIGINAL boolean,
    DESCRIPTION varchar(255),
    NUMBER_ varchar(15) not null,
    DATE_ date not null,
    DATE_LOAD date,
    DOC_CAUSE_ID uuid,
    PROBLEMS text,
    FIX_DUE date,
    --
    primary key (ID)
);
