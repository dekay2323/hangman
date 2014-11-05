package com.hangman

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import grails.converters.JSON

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(GameController)
@Mock([Game])
class GameControllerSpec extends Specification {

    def setup() {
    	def game1 = new Game(user: "Demian", solution: "testtest")
		game1.save(flush: true)
		game1 = new Game(user: "Carrie", solution: "Tiberius")
		game1.save(flush: true)
    }

    def cleanup() {
    }

    void 'test render index'() {
        when:
        controller.index()

        then:
        println "response.text ${response.text}"
        response.text != null
        response.text != ""

        def jsonResponse = JSON.parse(response.text)
        jsonResponse.size() == 2      
        jsonResponse.find {it.user == "Carrie"} != null
    }

    void 'test render show'() {
        when:
        params.id = 2
        controller.show()

        then:
        println "response.text ${response.text}"
        response.text != null
        response.text != ""

        def jsonResponse = JSON.parse(response.text)
        jsonResponse.user == "Carrie"
    }


    void 'test render create'() {
        when:
    	params.user = "Andy"
    	params.solution = "Spock"
    	request.method = 'POST'
        controller.save()

        then:
        println "response.text ${response.text}"
    	response.status == 201
    }
}
