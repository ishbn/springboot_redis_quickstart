package com.blog.main.druid.filters;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * 配置监控拦截器
 * druid监控拦截器
 * @author 溶酶菌
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name="exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
