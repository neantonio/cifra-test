alter table CIFRA_JOURNAL add constraint FK_CIFRA_JOURNAL_WAREHOUSE foreign key (WAREHOUSE_ID) references CIFRA_WAREHOUSE(ID);
create index IDX_CIFRA_JOURNAL_WAREHOUSE on CIFRA_JOURNAL (WAREHOUSE_ID);
