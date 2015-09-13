package com.github.jntakpe.sf.config.security;

import com.github.jntakpe.sf.config.ConfigConstants;
import com.github.jntakpe.sf.config.UrlConstants;
import com.github.jntakpe.sf.config.properties.OAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Configuration du serveur OAuth2
 *
 * @author jntakpe
 */
@Configuration
public class OAuth2ServerConfiguration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private Http401UnauthorizedEntryPoint authenticationEntryPoint;

        @Autowired
        private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

        /**
         * {@inheritDoc}
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl(UrlConstants.BASE + "/logout")
                    .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                    .and()
                    .csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, UrlConstants.BASE + "/utilisateurs").permitAll()
                    .antMatchers(UrlConstants.BASE + "/utilisateurs/nameAvailable").permitAll()
                    .antMatchers(UrlConstants.BASE + "/utilisateurs/emailAvailable").permitAll()
                    .antMatchers(UrlConstants.BASE + "/utilisateurs/resetPassword").permitAll()
                    .antMatchers(UrlConstants.BASE + "/**").authenticated()
                    .antMatchers("/manage/**").hasAnyAuthority(ConfigConstants.ADMIN);
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private OAuthProperties oAuthProperties;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(oAuthProperties.getClientId())
                    .scopes("read", "write")
                    .authorities(ConfigConstants.ADMIN, ConfigConstants.USER)
                    .authorizedGrantTypes("password", "refresh_token")
                    .secret(oAuthProperties.getSecret())
                    .accessTokenValiditySeconds(oAuthProperties.getTokenValidityInSeconds());
        }

    }
}
