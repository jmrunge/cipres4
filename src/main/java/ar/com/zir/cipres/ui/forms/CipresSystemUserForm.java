/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.zir.cipres.ui.forms;

import ar.com.zir.skorpio.core.ui.components.DragDropTwinCol;
import ar.com.zir.skorpio.core.ui.forms.SkorpioAbmForm;
import ar.com.zir.skorpio.security.api.SystemRole;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import java.util.Collection;

/**
 *
 * @author jmrunge
 */
public class CipresSystemUserForm extends SkorpioAbmForm {
    private Collection<SystemRole> roles;
    
    public void setSystemRoles(Collection<SystemRole> roles) {
        this.roles = roles;
    }
    
    @Override
    protected void bindAdditionalFields(FieldGroup binder) {
        super.bindAdditionalFields(binder);
        PasswordField pwd = new PasswordField("Contraseña");
        pwd.setRequired(true);
        pwd.setImmediate(true);
        pwd.setReadOnly(true);
        pwd.addValueChangeListener((Property.ValueChangeEvent event) -> {
//            if (isEditing() && getFieldValue("password") != null && getFieldValue("password").equals("000000")) {
//                Notification.show("Password inválido", Notification.Type.ERROR_MESSAGE);
//                setFieldValue("password", null);
//            }
            updateUiPersistence(action, getBeanItem().getBean(), true);
        });
        pwd.addTextChangeListener((TextChangeEvent event) -> {
            if (event.getText().equals("000000")) {
                Notification.show("Password inválido", Notification.Type.ERROR_MESSAGE);
                setFieldValue("password", null);
            }
        });
        pwd.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        pwd.addStyleName("visible");
        pwd.setNullRepresentation("");
        registerField("password", pwd);
        binder.bind(pwd, "password");
        grid.addComponent(pwd);
        grid.setRows(2);
        grid.addComponent(getTwinCol(binder), 0, 1, 1, 1);
    }
    
    private Component getTwinCol(FieldGroup binder) {
        DragDropTwinCol twinCol = new DragDropTwinCol(SystemRole.class, "roleName", roles);
        twinCol.addStyleName("visible");
        twinCol.setCaption("Roles");
        twinCol.setListColumnWidth(143);
        twinCol.setSourceListCaption("Disponibles");
        twinCol.setTargetListCaption("Asignados");
        binder.bind(twinCol, "systemRoles");
        twinCol.setReadOnly(true);
        return twinCol;
    }
    
}
