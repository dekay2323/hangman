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
        	def response = service.hasLost(1)

        then:"Then hasLost should be false"
			response == false		

        when:"The score has lost"
        	response = service.hasLost(0)

        then:"Then hasLost should be true"
			response == true		
    }   

    void "Test the calcScore"() {
        when:"Did not get a correct guess"
        	def response = service.calcScore("testtest".toList(), "a".toList(), 8)

        then:"We should see the score lower"
			response == 7

		when:"Did get a correct guess"
        	response = service.calcScore("testtest".toList(), "s".toList(), 8)

        then:"We should see the score stay the same"
			response == 8
	}    

    void "Test the newAnswer"() {
        when:"Add a new char to the answers"
        	def response = service.newAnswers("abc".toList(), "e".toList())

        then:"Should just append to end"
			response == ['a', 'b', 'c', 'e']
	}

	void "Test USECASE: A round of play"() {
		when:"Game starts"
        	def score = 8
        	def solution = "testtest".toList()
        	def answers = "".toList()

        and:"First guess is wrong"
        	def guess = "a".toList()

        then:"Answers must grow, score goes down, and you have not won nor lost"
			(score = service.calcScore(solution, guess, score)) == 7
        	service.hasLost(score) == false
        	(answers = service.newAnswers(answers, guess)) == ['a']
        	service.hasWon(solution, answers) == false
        	service.printer(solution, answers) == "........"

        when:"Second round"

        and:"Second guess is correct"
            guess = "e".toList()

        then:"Answers must grow, score stays the same, and you have not won nor lost"
			(score = service.calcScore(solution, guess, score)) == 7
        	service.hasLost(score) == false
        	(answers = service.newAnswers(answers, guess)) == ['a','e']
        	service.hasWon(solution, answers) == false
        	service.printer(solution, answers) == ".e...e.."
	}
}
