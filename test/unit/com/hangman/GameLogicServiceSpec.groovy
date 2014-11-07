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


    void "USER STORY: Play until win"() {
        when:"Game starts, First guess is wrong"
            def score = 8
            def solution = "testtest".toList()
            def answers = "".toList()
            def guess = "a"
            def resp = service.gameTurnLogic(solution, answers, guess, score)

        then:"Guess is wrong"
            resp?.gamePlay?.correctGuess == false
        and:"score goes down"
            (score = resp?.gamePlay?.score) == 7
        and:"answers must grow"
            (answers = resp?.gamePlay?.answers) == "a"
        and:"you have not won nor lost"
            resp?.gamePlay?.dateLost == null
            resp?.gamePlay?.dateWon == null
        and:"Solution changes"
            resp?.gamePlay?.currentSolution == " -  -  -  -  -  -  -  - "
        and:"Message should be you guessed wrong" 
            resp?.gamePlay?.message == "You guessed wrong"

        when:"Second guess is correct"
            guess = "e"
            resp = service.gameTurnLogic(solution, answers, guess, score)

        then:"Guess is correct"
            resp?.gamePlay?.correctGuess == true
        and:"score stays the same"
            (score = resp?.gamePlay?.score) == 7
        and:"answers must grow"
            (answers = resp?.gamePlay?.answers) == "ae"
        and:"you have not won nor lost"
            resp?.gamePlay?.dateLost == null
            resp?.gamePlay?.dateWon == null
        and:"Solution changes"
            resp?.gamePlay?.currentSolution == " - e -  -  - e -  - "
        and:"Message should be you guessed correct" 
            resp?.gamePlay?.message == "You guessed correct"

        when:"Third guess is correct"
            guess = "s"
            resp = service.gameTurnLogic(solution, answers, guess, score)

        then:"Guess is correct"
            resp?.gamePlay?.correctGuess == true
        and:"score stays the same"
            (score = resp?.gamePlay?.score) == 7
        and:"answers must grow"
            (answers = resp?.gamePlay?.answers) == "aes"
        and:"you have not won nor lost"
            resp?.gamePlay?.dateLost == null
            resp?.gamePlay?.dateWon == null
        and:"Solution changes"
            resp?.gamePlay?.currentSolution == " - es -  - es - "
        and:"Message should be you guessed correct" 
            resp?.gamePlay?.message == "You guessed correct"

        when:"Fourth guess you should win"
            guess = "t"
            resp = service.gameTurnLogic(solution, answers, guess, score)

        then:"Guess is correct"
            resp?.gamePlay?.correctGuess == true
        and:"score stays the same"
            (score = resp?.gamePlay?.score) == 7
        and:"answers must grow"
            (answers = resp?.gamePlay?.answers) == "aest"
        and:"you have not won nor lost"
            resp?.gamePlay?.dateLost == null
            resp?.gamePlay?.dateWon != null
        and:"Solution changes"
            resp?.gamePlay?.currentSolution == "testtest"
        and:"Message should be you have won" 
            resp?.gamePlay?.message == "You have won"

        when:"You have already won and guess"
            guess = "t"
            resp = service.gameTurnLogic(solution, answers, guess, score)

        then:"score is null"
            (score = resp?.gamePlay?.score) == null
        and:"Message should be you have won" 
            resp?.gamePlay?.message == "You have already won"   
    }

    void "gameTurnLogic() Has Won already"() {
        when:"Game has been won"
            def response = service.gameTurnLogic(
                "a", "a", "b", 1)

        then:"Score should be null"
            response?.gamePlay?.score == null
        and:"Should say you have already won"
            response?.gamePlay?.message == "You have already won"
    }
    void "gameTurnLogic() Has lost already"() {
        when:"Game has been lost"
            def response = service.gameTurnLogic(
                "abc", "a", "b", 0)

        then:"Score should be null"
            response?.gamePlay?.score == null
        and:"Should say you have already won"
            response?.gamePlay?.message == "You have already lost"
    }

    void "currentSolution(solution, answers) answers has no overlap with solution"() {
        when:
            def response = service.currentSolution("testtest".toList(), "xzv".toList())

        then:"There are no shown characters"
			response == " -  -  -  -  -  -  -  - "    
    }
    void "currentSolution(solution, answers) answers has some overlap with solution"() {
		when:
			def response = service.currentSolution("testtest".toList(), "eti".toList())

        then:"There are some characters shown"
			response == "te - tte - t"  
    }    	
    void "currentSolution(solution, answers) answers has 100% complete overlap with solution"() {
		when: 
			def response = service.currentSolution("testtest".toList(), "ets".toList())

        then:"There are some charachters shown"
			response == "testtest"      	
    }

    void "hasWon(solution, answers) answers has not won"() {
        when:
        	def response1 = service.hasWon("testtest".toList(), "txszmnyh".toList())
            def response2 = service.hasWon("TesT".toList(), "txszmnyh".toList())
            def response3 = service.hasWon("TesT".toList(), "Txszmnyh".toList())
            def response4 = service.hasWon("TesT".toList(), "Txszmnh".toList())

        then:"Then hasWon should be false"
			response1 == false    
            response2 == false    
            response3 == false    
            response4 == false    
    }
    void "hasWon(solution, answers) answers has won"() {
	    when:
        	def response1 = service.hasWon("testtest".toList(), "txszmnbve".toList())
            def response2 = service.hasWon("Testtest".toList(), "txszmnbve".toList())
            def response3 = service.hasWon("testtest".toList(), "Txszmnbve".toList())
            def response4 = service.hasWon("Testtest".toList(), "TxszMnbve".toList())

        then:"Then hasWon should be true"
			response1 == true    		
            response2 == true           
            response3 == true           
            response4 == true           
    }    

    void "ThasLost(score) score has not lost"() {
        when:
        	def response = service.hasLost(1)

        then:"Then hasLost should be false"
			response == false	
    }	
    void "hasLost(score) score has lost"() {
        when:
        	def response = service.hasLost(0)

        then:"Then hasLost should be true"
			response == true		
    }   

    void "calcScore(solution, guess, score) did not get a correct guess"() {
        when:
        	def response = service.calcScore("testtest".toList(), "a", 8)

        then:"We should see the score lower"
			response == 7
        }
    void "calcScore(solution, guess, score) Did get a correct guess"() {
		when:
        	def response = service.calcScore("testtest".toList(), "s", 8)

        then:"We should see the score stay the same"
			response == 8
	}    

    void "correctGuess(solution, guess, score) Did not get a correct guess"() {
        when:
            def response1 = service.correctGuess("testtest".toList(), "a")
            def response2 = service.correctGuess("testtest".toList(), "A")

        then:"We should see false"
            response1 == false
            response2 == false            
    }
    void "correctGuess(solution, guess, score) Did get a correct guess"() {
        when:
            def response1 = service.correctGuess("s".toList(), "s")
            def response2 = service.correctGuess("S".toList(), "s")
            def response3 = service.correctGuess("s".toList(), "S")
            def response4 = service.correctGuess("S".toList(), "S")

        then:"We should see true"
            response1 == true
            response2 == true
            response3 == true
            response4 == true
   }      
    void "guessedBefore(solution, guess, score) Did not get a correct guess"() {
        when:
            def response = service.guessedBefore("abcd".toList(), "e")

        then:"We should see false"
            response == false
    }
    void "guessedBefore(solution, guess, score) Did get a correct guess"() {
        when:
            def response = service.guessedBefore("abcd".toList(), "a")

        then:"We should see true"
            response == true
    }     

    void "newAnswer(answers, guess) Add a new char to the answers"() {
        when:
        	def response = service.newAnswers("abc".toList(), "d")

        then:"Should just append to end"
			response == ['a', 'b', 'c', 'd']
	}

    void "Upper and lowercase solution and answers should make no difference"() {
        when:
            def solution = "Aba".toList()
            def answers = "Aba".toList()

        then:"Should ignore uppercase/lowercase"
            service.newAnswers(answers, 'C') == service.newAnswers(solution, 'c')
            service.newAnswers(answers, 'c') == service.newAnswers(solution, 'c')
            service.correctGuess(solution, 'a') == true
            service.correctGuess(solution, 'A') == true
            service.correctGuess(solution, 'C') == false
            service.calcScore(solution, 'A', 1) == 1
            service.calcScore(solution, 'a', 1) == 1
            service.currentSolution(solution, 'A'.toList()) == "A - a"
            service.currentSolution(solution, 'a'.toList()) == "A - a"
            service.currentSolution(solution, 'B'.toList()) == " - b - "         
            service.hasWon(solution, "AB".toList()) == true   
            service.hasWon(solution, "ab".toList()) == true   
            service.hasWon(solution, "aB".toList()) == true   
    }

}
