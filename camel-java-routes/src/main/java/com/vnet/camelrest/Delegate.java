package com.vnet.camelrest;

import com.vnet.Utils;
import com.vnet.db.SqlService;
import com.vnet.service.LoincService;
import com.vnet.service.MeasurementService;
import org.apache.camel.BeanInject;

public class Delegate {

    public Delegate() {}

    @BeanInject
    private LoincService loincService;

    @BeanInject
    private MeasurementService measurementService;

    private String driverClassname;
    private String url;
    private String username;
    private String password;

    public void setDriverClassname(String driverClassname) {this.driverClassname = Utils.beanEnvProperty(driverClassname);}
    public void setUrl(String url) {this.url = Utils.beanEnvProperty(url);}
    public void setUsername(String username) {this.username = Utils.beanEnvProperty(username);}
    public void setPassword(String password) {this.password = Utils.beanEnvProperty(password);}

    public void initServices() {
        final SqlService sqlService = new SqlService(driverClassname, url, username, password);
        loincService.setSqlService(sqlService);
        measurementService.setLoincService(loincService);
        measurementService.setSqlService(sqlService);
    }
}
