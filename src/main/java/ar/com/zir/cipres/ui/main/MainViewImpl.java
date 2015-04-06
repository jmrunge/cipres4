/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.ui.main;

import ar.com.zir.skorpio.core.ui.main.SkorpioMainForm;
import ar.com.zir.skorpio.core.ui.main.AbstractMainView;
import ar.com.zir.skorpio.core.ui.main.SkorpioMainView;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author jmrunge
 */
public class MainViewImpl extends AbstractMainView implements SkorpioMainView {
    @Inject
    private Instance<MainFormImpl> mainForm;
    
    @Override
    public SkorpioMainForm getMainForm() {
        return mainForm.get();
    }
    
}
