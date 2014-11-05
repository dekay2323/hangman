package com.hangman

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(GameLogicService)
class GameLogicServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test the printer"() {
        when:"The answer has no overlap with solution"
        	def response = service.printer("testtest".toList(), "xzv".toList())

        then:"There are no shown characters"
			response == "........"       

		when:"The answer has some overlap with solution" 
			response = service.printer("testtest".toList(), "et".toList())

        then:"There are some charachters shown"
			response == "te.tte.t"      	

		when:"The answer has complete overlap with solution" 
			response = service.printer("testtest".toList(), "ets".toList())

        then:"There are some charachters shown"
			response == "testtest"      	
    }

    void "Test the hasWon"() {
        when:"The answer has not won"
        	def response = service.hasWon("testtest".toList(), "txszmnbv".toList())

        then:"Then hasWon should be false"
			response == false    

	    when:"The answer has won"
        	response = service.hasWon("testtest".toList(), "txszmnbve".toList())

        then:"Then hasWon should be true"
			response == true    		
    }    

    void "Test the hasLost"() {
        when:"The score has not lost"
        	def response = service.hasLost(1, "testtest".toList(), "txszmnbv".toList())

        then:"Then hasLost should be false"
			response == false		

        when:"The score has lost"
        	response = service.hasLost(0, "testtest".toList(), "t".toList())

        then:"Then hasLost should be true"
			response == true	

        when:"The score has lost but the answer solves the solution"
        	response = service.hasLost(1, "testtest".toList(), "tes".toList())

        then:"Then hasLost should be false"
			response == false	
    }   

    void "Test the applyAnswer"() {
        when:"Single matching answer"
        	def response = service.applyAnswer("testtest".toList(), "a".toList(), "e".toList(), 8)

        then:"We should see the score stay the same"
			response?.score == 8
		then:"Answers should become larger"
			response?.answers == ['a','e']
	}
}
