package com.example.third_task

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

    @Bean
    fun fileSystemWatcher() : FileSystemWatcher{
        val watcher = FileSystemWatcher()
        watcher.addSourceDirectory(File("D:/work_test_tasks/third_task/src/main/resources"))
        watcher.addListener(JsonListener(personService))
        watcher.start()
        println("Watcher has started")
        return watcher
    }

}


