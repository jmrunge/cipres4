/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.zir.cipres.config;

import ar.com.zir.cipres.ui.SkorpioPresenter;
import ar.com.zir.cipres.ui.main.MainPresenter;
import ar.com.zir.skorpio.core.options.SystemOption;
import ar.com.zir.skorpio.core.options.SystemOptions;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author jmrunge
 */
@Stateless
@LocalBean
public class CipresOptions extends SystemOptions {
    public static final String OPTION_USERS = "users";
    public static final String OPTION_ROLES = "roles";
    public static final String OPTION_LOCKS = "locks";
    
    @Override
    public List<SystemOption> getRootSystemOptions() {
        List<SystemOption> options = new ArrayList<>();
        options.add(getOption("home", "Inicio", "home.png", MainPresenter.SHOW_HOME));
        options.addAll(getCelularSystemOptions());
        options.addAll(getSecuritySystemOptions());
        options.add(getOption("logout", "Salir", "logout.png", SkorpioPresenter.LOGOUT));
        return options;
    }
    
    private List<SystemOption> getSecurityChildren() {
        List<SystemOption> children = new ArrayList<>();
        SystemOption users = getOption(OPTION_USERS, "Usuarios", "systemuser.png", MainPresenter.ADD_WINDOW);
        users.addAction(ACTION_LIST, true);
        children.add(users);
        SystemOption roles = getOption(OPTION_ROLES, "Roles", "systemrole.png", MainPresenter.ADD_WINDOW);
        roles.addAction(ACTION_LIST, true);
        roles.addAction(ACTION_ADD, false);
        roles.addAction(ACTION_EDIT, false);
        children.add(roles);
        SystemOption locks = getOption(OPTION_LOCKS, "Lockeos", "locked.png", MainPresenter.ADD_WINDOW);
        locks.addAction(ACTION_LIST, true);
        locks.addAction(ACTION_EDIT, false);
        children.add(locks);
        List<SystemOption> options = new ArrayList<>();
        options.add(getOption("fakesecurity", "Seguridad", null, null, children));
        return options;
    }
    
    private SystemOption getOption(String id, String name, String resource, String event) {
        return getOption(id, name, resource, event, null);
    }
    
    private SystemOption getOption(String id, String name, String resource, String event, List<SystemOption> children) {
        SystemOption option = new SystemOption();
        option.setId(id);
        option.setName(name);
        option.setIcon(resource);
        option.setEvent(event);
        option.setChildren(children);
        return option;
    }

    @Override
    public List<SystemOption> getSecuritySystemOptions() {
        List<SystemOption> options = new ArrayList<>();
        options.add(getOption("security", "Seguridad", "security.png", MainPresenter.SHOW_MENU, getSecurityChildren()));
        return options;
    }
    
    public List<SystemOption> getCelularSystemOptions() {
        List<SystemOption> options = new ArrayList<>();
        options.add(getOption("celulares", "Telefonía Celular", "celular.png", MainPresenter.SHOW_MENU, new ArrayList<>()));
        return options;
    }
    
}
