package com.example.zuul_filter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:AccessFilter
 * Description:
 *
 * @Author Gyo
 * @Date 2017/9/28 9:28
 */
public class AccessFilter extends ZuulFilter {

    /**
     * @Author:Gyo
     * @Description:
     * @Date:2017/9/28 12:35
     * pre：可以在请求被路由之前调用
        route：在路由请求时候被调用
        post：在route和error过滤器之后被调用
        error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
