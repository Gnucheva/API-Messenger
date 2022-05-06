package models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class RequestLog(
    var requestMethod: String = "",
    var requestQuery: String? = "",
    var requestBody: String? = "",
    var requestPath: String? = "",
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
)