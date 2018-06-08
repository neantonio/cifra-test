package com.groupstp.cifra.web.document;

import com.groupstp.cifra.entity.Document;
import com.groupstp.cifra.entity.DocumentService;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;
import com.haulmont.cuba.gui.xml.DeclarativeAction;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.gui.components.WebGroupTable;
import com.vaadin.ui.Layout;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DocumentBrowse extends AbstractLookup {

    @Named("income.edit")
    private EditAction incomeEditAction;

    @Named("ok.edit")
    private EditAction okEditAction;

    @Named("problems.edit")
    private EditAction problemsEditAction;

    @Inject
    private Table<Document> issue;

    @Inject
    private Table<Document> ok;

    @Inject
    CollectionDatasource<Document, UUID> documentDs;

    @Inject
    GroupDatasource<Document, UUID> documentsIncomeDs;

    @Inject
    GroupDatasource<Document, UUID> documentsOkDs;

    @Inject
    GroupDatasource<Document, UUID> documentsProblemsDs;

    @Inject
    CustomGroupDatasource<Document, UUID> documentsIssueDs;

    @Inject
    private DocumentService documentService;


    @Inject private Layout tabularBox;

    @Inject
    protected Messages messages;

    @Override
    public void init(Map<String, Object> params) {
        incomeEditAction.setAfterCommitHandler(entity -> refresh());
        okEditAction.setAfterCommitHandler(entity -> refresh());
        problemsEditAction.setAfterCommitHandler(entity -> refresh());

        ((IssueDocumentDs)documentsIssueDs).setDocumentService(documentService);

    }

    private void refresh()
    {
        documentsIncomeDs.refresh();
        documentsOkDs.refresh();
        documentsProblemsDs.refresh();
        documentsIssueDs.refresh();
    }

    public void onIssue(Component source) {
        Set<Document> doc = ok.getSelected();
        if(doc.size()>0){
            String desc;
            if(doc.size()>1) desc=messages.getMessage(MessageEnum.DOCUMENT_ROD);
            else desc=messages.getMessage(MessageEnum.DOCUMENTS_ROD);
            makeConfirmDialog(messages.getMessage(MessageEnum.DOCUMENT),messages.getMessage(MessageEnum.MAKE_ISSUE)+" "+desc+"?",()->{
                doc.forEach((item)->documentService.issueDocument(item));
                refresh();
            });
        }
        else{
            showNotification(messages.getMessage(MessageEnum.SELECT_IN_TABLE), NotificationType.TRAY);
        }
    }
    
    public void onReturn(Component source) {

        Set<Document> doc = issue.getSelected();
        if(doc.size()>0){
            String desc;
            if(doc.size()>1) desc=messages.getMessage(MessageEnum.DOCUMENT_ROD);
                 else desc=messages.getMessage(MessageEnum.DOCUMENTS_ROD);
            makeConfirmDialog(messages.getMessage(MessageEnum.DOCUMENT),messages.getMessage(MessageEnum.MAKE_RETURN)+" "+desc+"?",()->{
                doc.forEach((item)->documentService.returnDocument(item));
                refresh();
            });
        }
        else{
            showNotification(messages.getMessage(MessageEnum.SELECT_IN_TABLE), NotificationType.TRAY);
        }

    }

    private void makeConfirmDialog(String header,String content,SomeAction action){
        String capitalHeader= header.substring(0, 1).toUpperCase() + header.substring(1);
        String capitalContent= content.substring(0, 1).toUpperCase() + content.substring(1);
        showOptionDialog(
                capitalHeader,
                capitalContent ,
                MessageType.CONFIRMATION,
                new Action[] {
                        new DialogAction(DialogAction.Type.YES) {
                            @Override
                            public void actionPerform(Component component) {
                              action.call();
                            }
                        },
                        new DialogAction(DialogAction.Type.NO)
                }
        );
    }

}

interface SomeAction{
    void call();
}

