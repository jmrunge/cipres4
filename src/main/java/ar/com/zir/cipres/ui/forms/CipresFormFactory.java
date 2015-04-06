/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.ui.forms;

import ar.com.zir.cipres.config.CipresOptions;
import ar.com.zir.skorpio.core.options.SystemOption;
import ar.com.zir.skorpio.core.ui.forms.FormFactory;
import ar.com.zir.skorpio.core.sync.SynchronizedObjectFormProvider;
import ar.com.zir.skorpio.core.ui.forms.FormProvider;
import ar.com.zir.skorpio.security.forms.SystemRoleFormProvider;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jmrunge
 */
@Stateless
@LocalBean
public class CipresFormFactory extends FormFactory {
    @Inject
    private SystemRoleFormProvider systemRoleFormProvider;
    @Inject
    private CipresSystemUserFormProvider systemUserFormProvider;
    @Inject
    private SynchronizedObjectFormProvider synchronizedObjectFormProvider;
            
    @Override
    protected FormProvider getFormProvider(SystemOption option, String action) {
        switch (option.getId()) {
            case CipresOptions.OPTION_ROLES:
                return systemRoleFormProvider;
            case CipresOptions.OPTION_USERS:
                return systemUserFormProvider;
            case CipresOptions.OPTION_LOCKS:
                return synchronizedObjectFormProvider;
            default: 
                return null;
        }
    }
    
}
