package com.hermawan.dede.gudang.configSecurity;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource ds;
    
    private static final String SQL_LOGIN
            = "select username,password,active as enabled from t_user where username = ?";
    
    private static final String SQL_PERMISSION
            = "select u.username, r.rolename as role "
            + "from t_user u "
            + "join t_user_role ur on ur.id_user = u.id "
            + "join t_role r on ur.id_role = r.id "
            + "where u.username = ? ";
            
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth    
            .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }
}
