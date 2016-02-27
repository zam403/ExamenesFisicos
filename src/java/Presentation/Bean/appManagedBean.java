/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import util.MyUtil;

/**
 *
 * @author Alfonso
 */
@ManagedBean
@ApplicationScoped
public class appManagedBean {

    /**
     * Creates a new instance of appManagedBean
     */
    public appManagedBean() {
    }
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
}
