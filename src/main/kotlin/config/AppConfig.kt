package config

import components.AccountValidityInterceptor
import components.RequestLogInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig : WebMvcConfigurer {

    @Autowired
    lateinit var requestLogInterceptor: RequestLogInterceptor

    @Autowired
    lateinit var accountValidityInterceptor: AccountValidityInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestLogInterceptor)
        registry.addInterceptor(accountValidityInterceptor)
        super.addInterceptors(registry)
    }
}