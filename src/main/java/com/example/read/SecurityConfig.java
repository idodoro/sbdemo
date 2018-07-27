package com.example.read;

import com.example.read.dao.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by lenovo on 2018/7/20.
 */
//@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").hasRole("READER")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("11").
//                password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("11")).roles("READER");
//        auth.inMemoryAuthentication().withUser("11").
//                password(NoOpPasswordEncoder.getInstance().matches("11")).roles("READER");
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails userDetails = readerRepository.findById(username).orElse(null);
//                return readerRepository.findOne(username);      //springboot1.3.0可用
//                String encodedPWD = NoOpPasswordEncoder.getInstance().encode("11");
                if (userDetails != null) {
                    return userDetails;
                }
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
        });
    }



}
