package zsb.azb.mcm.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zsb.azb.mcm.Interceptor.CookieInterceptor;

@Configuration
public class WebConfig
        implements WebMvcConfigurer
{
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(CookieInterceptor()).excludePathPatterns(new String[] { "/uplink/*" }).excludePathPatterns(new String[] { "/manhole/test" }).excludePathPatterns(new String[] { "/simple" }).excludePathPatterns(new String[] { "/dept/test" }).excludePathPatterns(new String[] { "/iot/uplink/*" }).excludePathPatterns(new String[] { "/aep/*" }).excludePathPatterns(new String[] { "/gettoken" }).excludePathPatterns(new String[] { "/executor/*" }).excludePathPatterns(new String[] { "/user/login" }).excludePathPatterns(new String[] { "/device/**" }).excludePathPatterns(new String[] { "/data/**" }).excludePathPatterns(new String[] { "/thing/**" }).excludePathPatterns(new String[] { "/batch/**" });
    }

    @Bean
    public CookieInterceptor CookieInterceptor()
    {
        return new CookieInterceptor();
    }
}
