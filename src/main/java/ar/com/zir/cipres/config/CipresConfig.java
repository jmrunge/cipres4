/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.config;

import ar.com.zir.cipres.api.security.CipresSystemUser;
import ar.com.zir.cipres.legacy.api.security.Usuario;
import ar.com.zir.cipres.legacy.services.CipresSecurityService;
import ar.com.zir.skorpio.core.config.SystemConfig;
import ar.com.zir.skorpio.security.services.SkorpioSecurityService;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jmrunge
 */
@LocalBean
@Stateless
@Startup
public class CipresConfig extends SystemConfig {
    @Inject
    private SkorpioSecurityService securityService;
    @Inject
    private CipresSecurityService cipresSecurityService;

    @Override
    public String getAppName() {
        return "Cipres4";
    }

    @Override
    public String getHeaderStyle() {
        return "cipres-header";
    }

    @Override
    public String getMainStyle() {
        return "cipres-main";
    }

    @Override
    public float getHeaderHeight() {
        return 100f;
    }
    
    @PostConstruct
    public void initDb() {
        if (securityService.getSystemUsers().isEmpty()) {
            CipresSystemUser su = new CipresSystemUser();
            su.setUserName("root");
            su.setPassword("cipres");
            Usuario usuarioCipres = cipresSecurityService.getCipresAdminUser();
            su.setUsuarioCipres(usuarioCipres);
            securityService.createSystemUser(su);
        }
    }
    
    @Override
    public int getIdleTime() {
        return 5 * 60;
    }
    
}
