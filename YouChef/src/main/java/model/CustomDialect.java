package model;
import java.sql.Types;  

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;  
public class CustomDialect extends SQLServerDialect {  
    public CustomDialect() {  
        super();  
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());  
        //registerHibernateType(-9, StandardBasicTypes.STRING.getName());  
    }  
}  
