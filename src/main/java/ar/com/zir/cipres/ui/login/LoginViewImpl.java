/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.ui.login;

import ar.com.zir.skorpio.core.ui.login.AbstractLoginView;
import ar.com.zir.skorpio.core.ui.login.LoginView;
import ar.com.zir.skorpio.core.ui.AbstractSkorpioForm;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author jmrunge
 */
public class LoginViewImpl extends AbstractLoginView implements LoginView {
    @Inject
    private Instance<LoginForm> loginForm;
    
    @Override
    public AbstractSkorpioForm getLoginForm() {
        return loginForm.get();
    }
    
}
