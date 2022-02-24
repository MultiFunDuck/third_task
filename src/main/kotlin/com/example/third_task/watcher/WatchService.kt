package com.example.third_task.watcher

import com.example.third_task.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.devtools.filewatch.FileSystemWatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.io.File

@Service
@Configuration
class WatchService{

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var jsonListener: JsonListener

    @Bean
    fun fileSystemWatcher() : FileSystemWatcher{
        val watcher = FileSystemWatcher()
        watcher.addSourceDirectory(File("D:/work_test_tasks/third_task/src/main/resources"))
        watcher.addListener(jsonListener)
        watcher.start()
        println("Watcher has started")
        return watcher
    }

}


