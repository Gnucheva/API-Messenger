package services

import models.RequestLog
import org.springframework.stereotype.Service
import repositaries.RequestLogRepository
import javax.servlet.http.HttpServletRequest

@Service
class RequestLogServiceImpl(val repository: RequestLogRepository) : RequestLogService {
    override fun logRequest(request: HttpServletRequest) {
        val log = RequestLog()
        log.requestMethod = request.method
        log.requestQuery = request.queryString
        //log.requestPath = request.pathInfo.toString()
        repository.save(log)
    }
}