# explore
学习Spring Security

##SpringSecurity基本原理

认证处理流程：绿色块用来处理认证，…可以自定义登录认证过滤器。
* UsernamePasswordAuthenticationFilter 处理表单登录
* BasicAuthenticationFilter 处理http basic登录
* ExceptionTranslationFilter 异常处理过滤器
* FilterSecurityInterceptor 依据SecurityConfig的配置决定当前请求是否可以访问服务。
