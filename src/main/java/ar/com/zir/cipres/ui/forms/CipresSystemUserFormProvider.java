/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.zir.cipres.ui.forms;

import ar.com.zir.cipres.api.security.CipresSystemUser;
import ar.com.zir.cipres.legacy.api.security.Usuario;
import ar.com.zir.cipres.services.Cipres4SecurityService;
import ar.com.zir.skorpio.core.options.SystemOptions;
import ar.com.zir.skorpio.core.persistence.PersistenceException;
import ar.com.zir.skorpio.core.security.SystemUser;
import ar.com.zir.skorpio.core.ui.forms.FormProvider;
import ar.com.zir.skorpio.core.ui.forms.SkorpioAbmComboField;
import ar.com.zir.skorpio.core.ui.forms.SkorpioAbmField;
import ar.com.zir.skorpio.core.ui.forms.SkorpioBeanItemContainer;
import ar.com.zir.skorpio.core.ui.forms.SkorpioListColumn;
import ar.com.zir.skorpio.core.ui.forms.SkorpioListForm;
import ar.com.zir.skorpio.core.ui.persistence.SkorpioPersistedForm;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author jmrunge
 */
@Singleton
@LocalBean
public class CipresSystemUserFormProvider extends FormProvider {
    @Inject
    private Cipres4SecurityService securityService;

    @Override
    public List<SkorpioListColumn> getFormListColumns() {
        List<SkorpioListColumn> columns = new ArrayList<>();
        columns.add(new SkorpioListColumn("userName", String.class, "Rol"));
        columns.add(new SkorpioListColumn("usuarioCipres", String.class, "Usuario Ciprés"));
        return columns;
    }

    @Override
    public SkorpioBeanItemContainer getFormContainer() {
        return new SkorpioBeanItemContainer(CipresSystemUser.class);
    }

    @Override
    public void doRemoveItem(Object item, SystemUser user) throws PersistenceException {
        securityService.removeSystemUser((CipresSystemUser)item);
    }

    @Override
    public Class getObjectClass() {
        return CipresSystemUser.class;
    }

    @Override
    public List getTableObjects() {
        return securityService.getSystemUsers();
    }

    @Override
    public List<SkorpioAbmField> getFormFields() {
        List<SkorpioAbmField> fields = new ArrayList<>();
        fields.add(new SkorpioAbmField("Nombre", "userName", true));
        fields.add(new SkorpioAbmComboField("Usuario Ciprés", "usuarioCipres", true, "Seleccione Usuario Ciprés", "nombre"));
        return fields;
    }

    @Override
    public Object getNewObject() {
        return securityService.getNewSystemUser();
    }

    @Override
    public Object doCreateObject(Object item, SystemUser user) throws PersistenceException {
        return getFreshObject(securityService.createSystemUser((CipresSystemUser)item));
    }

    @Override
    public Object doUpdateObject(Object item, SystemUser user) throws PersistenceException {
        if ( ((CipresSystemUser)item).getPassword().equals("000000"))
            ((CipresSystemUser)item).setPassword(null);
        return getFreshObject(securityService.updateSystemUser((CipresSystemUser)item));
    }

    @Override
    public SkorpioPersistedForm getFormInstance(String action) {
        switch (action) {
            case SystemOptions.ACTION_LIST:
                return new SkorpioListForm();
            case SystemOptions.ACTION_ADD:
            case SystemOptions.ACTION_EDIT:
                CipresSystemUserForm form = new CipresSystemUserForm();
                form.setSystemRoles(securityService.getSystemRoles());
                form.initContainer("usuarioCipres", Usuario.class, securityService.getLegacyUsers());
                return form;
            default:
                return null;
        }
    }

    @Override
    public Object getFreshObject(Object item) throws PersistenceException {
        CipresSystemUser user = securityService.refreshSystemUser((CipresSystemUser)item);
        user.setPassword("000000");
        return user;
    }
    
}