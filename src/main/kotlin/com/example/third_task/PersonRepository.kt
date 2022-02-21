package com.example.third_task

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: CrudRepository<Person,Int>{
    fun findAllByName(name: String): List<Person>

    fun findAllByLastname(lastname: String): List<Person>

    fun findAllByNameAndLastname(name: String,lastname: String): List<Person>
}