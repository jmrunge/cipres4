/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.zir.cipres.services;

import ar.com.zir.cipres.api.security.CipresSystemUser;
import ar.com.zir.cipres.legacy.api.security.Usuario;
import ar.com.zir.cipres.legacy.services.CipresSecurityService;
import ar.com.zir.skorpio.core.persistence.PersistenceException;
import ar.com.zir.skorpio.core.security.SecurityService;
import ar.com.zir.skorpio.core.security.SystemUser;
import ar.com.zir.skorpio.security.api.SystemRole;
import ar.com.zir.skorpio.security.services.SkorpioSecurityService;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmrunge
 */
@LocalBean
@Stateless
@Alternative
public class Cipres4SecurityService extends SecurityService {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private SkorpioSecurityService skorpioSecurityService;
    @Inject
    private CipresSecurityService cipresSecurityService;

    @Override
    public CipresSystemUser getNewSystemUser() {
        return new CipresSystemUser();
    }
    
    public List<CipresSystemUser> getSystemUsers() {
        List<CipresSystemUser> users = em.createNamedQuery("SkorpioSystemUser.findAll").getResultList();
        users.stream().forEach((user) -> {
            user.setUsuarioCipres(cipresSecurityService.getUsuarioById(user.getIdUsuarioCipres()));
        });
        return users;
    }
    
    public CipresSystemUser createSystemUser(CipresSystemUser su) throws PersistenceException {
        validateSystemUser(su);
        su = (CipresSystemUser) skorpioSecurityService.createSystemUser(su);
        su.setUsuarioCipres(cipresSecurityService.getUsuarioById(su.getIdUsuarioCipres()));
        return su;
    }
    
    public CipresSystemUser updateSystemUser(CipresSystemUser su) throws PersistenceException {
        validateSystemUser(su);
        su = (CipresSystemUser) skorpioSecurityService.updateSystemUser(su);
        su.setUsuarioCipres(cipresSecurityService.getUsuarioById(su.getIdUsuarioCipres()));
        return su;
    }
    
    private void validateSystemUser(CipresSystemUser su) throws PersistenceException {
        if (su.getUsuarioCipres() == null)
            throw new PersistenceException("Debe seleccionar un usuario ciprés");
        List<CipresSystemUser> users = em.createNamedQuery("CipresSystemUser.findByIdUsuarioCipres").setParameter("idUsuarioCipres", su.getIdUsuarioCipres()).getResultList();
        if (users != null && !users.isEmpty()) {
            if (su.getId() == null)
                throw new PersistenceException("Ese usuario ciprés ya está asignado a otro usuario");
            if (users.size() > 1)
                throw new PersistenceException("Ese usuario ciprés ya está asignado a VARIOS USUARIOS!!!!");
            if (!users.get(0).equals(su))
                throw new PersistenceException("Ese usuario ciprés ya está asignado a otro usuario");
        }
    }
    
    public void removeSystemUser(CipresSystemUser user) throws PersistenceException {
        skorpioSecurityService.removeSystemUser(user);
    }

    public CipresSystemUser refreshSystemUser(CipresSystemUser user) throws PersistenceException {
        CipresSystemUser su = (CipresSystemUser) skorpioSecurityService.refreshSystemUser(user);
        su.setUsuarioCipres(cipresSecurityService.getUsuarioById(su.getIdUsuarioCipres()));
        return su;
    }

    public List<SystemRole> getSystemRoles() {
        return skorpioSecurityService.getSystemRoles();
    }
    
    public List<Usuario> getLegacyUsers() {
        return cipresSecurityService.getUsuarios();
    }

    @Override
    public SystemUser login(SystemUser user) {
        CipresSystemUser su = (CipresSystemUser) skorpioSecurityService.login(user);
        if (su == null) return null;
        su.setUsuarioCipres(cipresSecurityService.getUsuarioById(su.getIdUsuarioCipres()));
        return su;
    }

}
