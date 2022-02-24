package com.example.third_task

import com.example.third_task.db.Person
import com.example.third_task.db.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


interface PersonService{
    fun create(savePersonRequest: Person)
    fun update(id: Int, savePersonRequest: Person)
    fun isThereSame(findPersonRequest: Person): Boolean
}


@Service
class PersonServiceImp: PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun create(person: Person) {
        personRepository.save(person)
    }

    override fun update(id:Int, savePersonRequest: Person) {
        var personToUpdate = personRepository.findById(id).get()
        personToUpdate.id = id
        personToUpdate.name = savePersonRequest.name
        personToUpdate.lastname = savePersonRequest.lastname
        personRepository.save(personToUpdate)
    }

    override fun isThereSame(findPersonRequest: Person): Boolean{

        return (personRepository.findAllByNameAndLastname(
            findPersonRequest.name,
            findPersonRequest.lastname
                    ).isNotEmpty()
                )

    }

    @PostConstruct()
    fun isRepoInit(){
        println("repo initialized")
    }
}