package com.example.third_task

import javax.persistence.*


@Entity
open class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0,

    open var name: String,

    open var lastname: String

)