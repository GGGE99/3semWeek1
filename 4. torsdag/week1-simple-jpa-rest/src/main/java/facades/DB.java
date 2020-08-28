/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcg
 */
public class DB {
    private static DB instance;

    private static Map props1 = new HashMap();
    private static Map props2 = new HashMap();
    private static Map memberProps = new HashMap();
    
    public static DB getDb() {
    if (instance == null) {
            instance = new DB();
            instance.creat();
        }
        return instance;
    }
    public void creat() {
        props1.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/Week1Day4?zeroDateTimeBehavior=CONVERT_TO_NULL");
        props1.put("javax.persistence.jdbc.user", "dev");
        props1.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props1.put("javax.persistence.jdbc.password", "ax2");
        props1.put("javax.persistence.schema-generation.database.action", "create");


        props2.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/startcode?zeroDateTimeBehavior=CONVERT_TO_NULL");
        props2.put("javax.persistence.jdbc.user", "dev");
        props2.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props2.put("javax.persistence.jdbc.password", "ax2");
        props2.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        memberProps.put("prud", props1);
        memberProps.put("test", props2);
    }

    public static Map getProps1() {
        return props1;
    }

    public static void setProps1(Map props1) {
        DB.props1 = props1;
    }

    public Map getProps2() {
        return props2;
    }

    public void setProps2(Map props2) {
        this.props2 = props2;
    }

    public Map getMemberProps() {
        return memberProps;
    }

    public void setMemberProps(Map memberProps) {
        this.memberProps = memberProps;
    }


}
