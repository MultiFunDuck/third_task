package com.example.third_task.watcher

import com.example.third_task.PersonService
import com.example.third_task.PersonServiceImp
import com.example.third_task.db.Person
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.devtools.filewatch.ChangedFile
import org.springframework.boot.devtools.filewatch.ChangedFiles
import org.springframework.boot.devtools.filewatch.FileChangeListener
import org.springframework.stereotype.Component
import java.util.*


@Component
class JsonListener  : FileChangeListener {

    @Autowired
    lateinit var personService: PersonServiceImp


    private val mapper = ObjectMapper()


    override fun onChange(changeSet: MutableSet<ChangedFiles>?) {

        for (files: ChangedFiles in changeSet!!) {
            for (file: ChangedFile in files.files) {


                var personsToSave: Array<Person>? = null

                try {

                    personsToSave = mapper.readValue(file.file, Array<Person>::class.java)

                } catch (e: Exception) {

                    println("Couldn't convert file ${file.file.name} to Array of Person")

                } finally {

                    if(personsToSave != null){


                        for(personToSave: Person in personsToSave){
                            if(!personService.isThereSame(personToSave)){
                                personService.create(personToSave)
                                println("Person with name = ${personToSave.name}" +
                                        " and lastname = ${personToSave.lastname} is saved")
                            }
                            else {
                                println("ignoring Person{ ${personToSave.name}, ${personToSave.lastname} }")
                            }
                        }
                    }


                }

                println("Operation: " + file.type
                        + " On file: " + file.file.name + " is done"
                )
            }
        }

    }


}