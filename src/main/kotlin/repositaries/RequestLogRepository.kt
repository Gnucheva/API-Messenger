package repositaries

import models.RequestLog
import org.springframework.data.repository.CrudRepository

interface RequestLogRepository : CrudRepository<RequestLog, Long>