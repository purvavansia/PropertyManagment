package com.example.purva.propertymanagment.data.model;

/**
 * Created by purva on 4/25/18.
 */

public class User {


    /**
     * msg : success
     * userid : 2
     * usertype : tenant
     * useremail : aa@aa.com
     * appapikey : 28a013c206583bc57fc2cfa4e11dfa14
     */

    private String msg;
    private String userid;
    private String usertype;
    private String useremail;
    private String appapikey;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }
}
