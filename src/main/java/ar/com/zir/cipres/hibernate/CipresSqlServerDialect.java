/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.zir.cipres.hibernate;

import org.hibernate.dialect.SQLServer2012Dialect;

/**
 *
 * @author jmrunge
 */
public class CipresSqlServerDialect extends SQLServer2012Dialect {
    
    public CipresSqlServerDialect() {
        super();
//        registerColumnType(Types.BLOB, "varbinary");
    }
    
}
