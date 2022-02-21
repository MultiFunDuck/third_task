package com.example.third_task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


interface PersonService{
    fun create(savePersonRequest: Person)
    fun update(id: Int, savePersonRequest: Person)
    fun isThereSame(findPersonRequest: Person): Boolean
}


@Service
@Component
class PersonServiceImp: PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun create(savePersonRequest: Person) {
        personRepository.save(
            Person(
                name = savePersonRequest.name,
                lastname = savePersonRequest.lastname
            ))
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

}