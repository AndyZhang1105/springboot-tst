package com.zz.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 配置监控拦截器
 * druid监控拦截器
 * @ClassName: DruidStatFilter
 * @author 16437
 * @date
 */
@WebFilter(
        filterName = "druidWebStatFilter",
        urlPatterns = "/*",
        initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")}
)
public class DruidStatFilter extends WebStatFilter {
}
