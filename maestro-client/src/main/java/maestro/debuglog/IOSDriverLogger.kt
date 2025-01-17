package maestro.debuglog

import maestro.logger.Logger
import org.slf4j.LoggerFactory

class IOSDriverLogger(val clazz: Class<*>) : Logger {

    override fun info(message: String) {
        LoggerFactory.getLogger(clazz).info(message)
        loggerFor(clazz).info(message)
    }

    companion object {
        private var loggers = mutableMapOf<Class<*>, java.util.logging.Logger>()

        fun loggerFor(clazz: Class<*>): java.util.logging.Logger {
            if (!loggers.containsKey(clazz)) {
                loggers[clazz] = DebugLogStore.loggerFor(clazz)
            }
            return loggers[clazz]!!
        }
    }
}
