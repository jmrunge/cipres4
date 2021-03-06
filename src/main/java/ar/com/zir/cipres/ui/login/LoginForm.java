/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.ui.login;


import ar.com.zir.skorpio.core.ui.AbstractSkorpioForm;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.cdi.UIScoped;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author jmrunge
 */
@SuppressWarnings("serial")
@UIScoped
public class LoginForm extends AbstractSkorpioForm {
	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_2;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;

	@AutoGenerated
	private Embedded embedded_2;

	@AutoGenerated
	private Label label_1;

	@AutoGenerated
	private Embedded embedded_1;

	@AutoGenerated
	private Button btnLogin;

	@AutoGenerated
	private TextField txtUsuario;

	@AutoGenerated
	private PasswordField pwdPassword;

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

        /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
    
        public LoginForm() {
            buildMainLayout();
            setCompositionRoot(mainLayout);
            btnLogin.addClickListener((Button.ClickEvent event) -> {
                this.commit();
            });
            btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
            btnLogin.addStyleName(Reindeer.BUTTON_DEFAULT);
        }
        
        @Override
        protected void bindBean(FieldGroup binder) {
            binder.bind(txtUsuario, "userName");
            binder.bind(pwdPassword, "password");
            if (txtUsuario.getValue() == null || txtUsuario.getValue().trim().length() == 0)
                txtUsuario.focus();
            else
                pwdPassword.focus();
        }

	@Override
    protected void fixNullRepresentation() {
        txtUsuario.setNullRepresentation("");
        pwdPassword.setNullRepresentation("");
    }

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// panel_2
		panel_2 = buildPanel_2();
		mainLayout.addComponent(panel_2);
		mainLayout.setComponentAlignment(panel_2, new Alignment(48));
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("500px");
		panel_2.setHeight("143px");
		
		// absoluteLayout_1
		absoluteLayout_1 = buildAbsoluteLayout_1();
		panel_2.setContent(absoluteLayout_1);
		
		return panel_2;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_1() {
		// common part: create layout
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("100.0%");
		absoluteLayout_1.setHeight("100.0%");
		
		// pwdPassword
		pwdPassword = new PasswordField();
		pwdPassword.setCaption("Password");
		pwdPassword.setImmediate(true);
		pwdPassword.setDescription("Contraseña del usuario");
		pwdPassword.setWidth("-1px");
		pwdPassword.setHeight("-1px");
		pwdPassword.setTabIndex(6);
		pwdPassword.setRequired(true);
		pwdPassword.setNullSettingAllowed(true);
		absoluteLayout_1.addComponent(pwdPassword, "top:99.0px;left:211.0px;");
		
		// txtUsuario
		txtUsuario = new TextField();
		txtUsuario.setCaption("Usuario");
		txtUsuario.setImmediate(true);
		txtUsuario.setDescription("Nombre de usuario");
		txtUsuario.setWidth("-1px");
		txtUsuario.setHeight("-1px");
		txtUsuario.setTabIndex(5);
		txtUsuario.setRequired(true);
		txtUsuario.setInputPrompt("Ingrese el usuario");
		txtUsuario.setMaxLength(10);
		absoluteLayout_1.addComponent(txtUsuario, "top:99.0px;left:18.0px;");
		
		// btnLogin
		btnLogin = new Button();
		btnLogin.setCaption("Login");
		btnLogin.setImmediate(true);
		btnLogin.setWidth("88px");
		btnLogin.setHeight("-1px");
		btnLogin.setTabIndex(7);
		absoluteLayout_1.addComponent(btnLogin, "top:99.0px;left:403.0px;");
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("56px");
		embedded_1.setHeight("56px");
		embedded_1.setSource(new ThemeResource("security.png"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/png");
		absoluteLayout_1.addComponent(embedded_1, "top:18.0px;left:425.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setStyleName("login");
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("<h2>Bienvenido a </h2>");
		label_1.setContentMode(ContentMode.HTML);
		absoluteLayout_1.addComponent(label_1, "top:18.0px;left:18.0px;");
		
		// embedded_2
		embedded_2 = new Embedded();
		embedded_2.setImmediate(false);
		embedded_2.setWidth("141px");
		embedded_2.setHeight("45px");
		embedded_2.setSource(new ThemeResource("cipres-header.png"));
		embedded_2.setType(1);
		embedded_2.setMimeType("image/png");
		absoluteLayout_1.addComponent(embedded_2, "top:25.0px;left:200.0px;");
		
		return absoluteLayout_1;
	}

    @Override
    public String getFormIcon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFormTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
