package filters

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import services.TokenAuthenticationService
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter( //вызывается контейнером каждый раз,когда пара запрос/ответ проходит через ряд фильтров в результате клиентского запроса на ресурс
        request: ServletRequest,
        response: ServletResponse,
        filterChain: FilterChain //Передает запрос и ответ следующей сущности из ряда фильтров
    ) {
        val authentication = TokenAuthenticationService
            .getAuthentication(request as HttpServletRequest)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}