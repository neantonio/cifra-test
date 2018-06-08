package com.groupstp.cifra.web.doctype;

import com.groupstp.cifra.entity.Document;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;


public class IssueDocumentDs extends CustomGroupDatasource<Document, UUID> {

    @Override
    protected Collection<Document> getEntities(Map<String, Object> params) {
        return null;
    }
}