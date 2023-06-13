package com.example.server.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;
    private String password;

    private String sex;

    private String jianjie;

    public User(){}

    public User(String id, String username, String password, String sex, String jianjie){
        this.id=id;
        this.username=username;
        this.password=password;
        this.sex=sex;
        this.jianjie=jianjie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getJianjie() {
        return jianjie;
    }
}
