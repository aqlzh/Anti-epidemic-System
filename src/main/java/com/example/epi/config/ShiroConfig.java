package com.example.epi.config;

import com.example.epi.shiro.JWTFilter;
import com.example.epi.shiro.MyRealm;
import com.example.epi.util.JwtUtil;
import com.example.epi.util.RedisUtil;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public RedisUtil getRedisUtil() {
        return new RedisUtil();
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(MyRealm myRealm) {
        System.out.println("------------securityManager-------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(myRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        System.out.println("-----------shiroFilterFactoryBean------------");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwtFilter", new JWTFilter(getJwtUtil(), getRedisUtil()));
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");

        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/", "anon");
        filterRuleMap.put("/doLogin", "anon");

        //放行swagger
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/v2/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        //filterRuleMap.put("/csrf/**", "anon");

        //放行druid
        filterRuleMap.put("/druid/**","anon");
        //filterRuleMap.put("/favicon.ico","anon");

        //放行静态资源
        filterRuleMap.put("/static/**", "anon");
        filterRuleMap.put("*.js", "anon");
        filterRuleMap.put("*.css", "anon");
        filterRuleMap.put("/css/**", "anon");
        filterRuleMap.put("/js/**", "anon");
        filterRuleMap.put("/images/**", "anon");

        //filterRuleMap.put("/**", "jwtFilter");
        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        System.out.println("-----------defaultAdvisorAutoProxyCreator-------------");
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        System.out.println("-----------lifecycleBeanPostProcessor-----------");
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        System.out.println("--------------authorizationAttributeSourceAdvisor-----------------");
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
