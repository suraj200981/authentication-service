package com.example.url.shortner.microservices.authenticationservice

import com.example.url.shortner.microservices.authenticationservice.utils.validators.FirstNameValidator
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class RegisterSpec extends Specification {

    def "When value is suraj"(){
        given:
        def validator = new FirstNameValidator()
        def validFirstName = "suraj"

        when:
        def result = validator.isValid(validFirstName)

        then:
        result == true
    }

    def "When value is Suraj"(){
        given:
        def validator = new FirstNameValidator()
        def invalidFirstName = "Suraj"

        when:
        def result = validator.isValid(invalidFirstName)

        then:
        result == false
    }
}
