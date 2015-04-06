/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.zir.cipres.api.security;

import ar.com.zir.cipres.legacy.api.security.Usuario;
import ar.com.zir.skorpio.security.api.SkorpioSystemUser;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jmrunge
 */
@NamedQueries({
    @NamedQuery(name = "CipresSystemUser.findByIdUsuarioCipres", query = "select u from CipresSystemUser u where u.idUsuarioCipres = :idUsuarioCipres")
})
@Entity
public class CipresSystemUser extends SkorpioSystemUser {
    @NotNull
    @Basic
    private Short idUsuarioCipres;
    @Transient
    private Usuario usuarioCipres;

    public Usuario getUsuarioCipres() {
        return usuarioCipres;
    }

    public void setUsuarioCipres(Usuario usuarioCipres) {
        this.usuarioCipres = usuarioCipres;
        if (usuarioCipres != null)
            setIdUsuarioCipres(usuarioCipres.getIdusuario());
        else
            setIdUsuarioCipres(null);
    }

    public Short getIdUsuarioCipres() {
        return idUsuarioCipres;
    }

    public void setIdUsuarioCipres(Short idUsuarioCipres) {
        this.idUsuarioCipres = idUsuarioCipres;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CipresSystemUser)) {
            return false;
        }
        CipresSystemUser other = (CipresSystemUser) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

}
