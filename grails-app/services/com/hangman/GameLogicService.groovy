package com.hangman

import grails.transaction.NotTransactional

/**
* Primary logic for the Hangman game</br>
* Important fields
* <ul>
* <li>
* answers : This is a list of the answers that the user has already given
* </li>
* <li>
* guess : The current guess that the user has made
* </li>
* <li>
* score : The users score in the game
* </li>
* <li>
* solution : The actual solution that the user is trying to find
* </li>
* </ul>
*/
class GameLogicService {
    static transactional = false
    final static String UNSOLVED_BLOCK = " - "

    /**
    * This appends the guess onto the end of the answers
    */
    Collection newAnswers(Collection answers, String guess) {
        assert answers != null, "answers parameter should not be null"
        assert guess != null, "guess parameter should not be null"
        if (guessedBefore(answers, guess))
            return answer

        answers + guess?.toLowerCase()
    }

    Integer calcScore(Collection solution, String guess, Integer score) {
        assert solution != null, "solution parameter should not be null"     
        assert guess != null, "guess parameter should not be null" 
        assert score != null, "score parameter should not be null"    
        if (solution?.find{it?.toLowerCase() == guess?.toLowerCase()} <= 0)
            return score-1
        score
    }

    Boolean correctGuess(Collection solution, String guess) {
        assert solution != null, "solution parameter should not be null"     
        assert guess != null, "guess parameter should not be null"  
        if (solution?.find{it?.toLowerCase() == guess?.toLowerCase()} > 0)
            return true
        false        
    }
    
    Boolean guessedBefore(Collection answers, String guess) {
        assert answers != null, "answers parameter should not be null"     
        assert guess != null, "guess parameter should not be null" 
        if (answers?.find{it?.toLowerCase() == guess?.toLowerCase()} > 0)
            return true
        false       
    }

    Boolean hasWon(Collection solution, Collection answers) {
        assert solution != null, "solution parameter should not be null"     
        assert answers != null, "guess parameter should not be null" 
        // Set removes duplicates
        Set solutionSet = (solution as Set).collect {it.toLowerCase()}
        Set answerSet = (answers as Set).collect {it.toLowerCase()}
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        false
    }
    
    Boolean hasLost(Integer score) {
        assert score != null, "score parameter should not be null"  
        if (score <= 0)
            return true
            
        false
    }
    
    String currentSolution(Collection solution, Collection answers) {
        assert solution != null, "solution parameter should not be null"     
        assert answers != null, "guess parameter should not be null" 
        solution.collect { solVar ->
        if (answers?.find {it?.toLowerCase() == solVar?.toLowerCase()} > 0)
           return solVar
        else
           return UNSOLVED_BLOCK
        }?.join()
    }

    /**
    * Core game play turn logic which
    *
    * 
    * @return JSON result set
    */
    def gameTurnLogic(def solutionParam, def answersParam, def guessParam, def scoreParam) {
        assert solutionParam != null, "solution parameter should not be null"     
        assert answersParam != null, "answers parameter should not be null" 
        assert guessParam != null, "guess parameter should not be null"    
        assert scoreParam != null, "score parameter should not be null"    
        def builder = new groovy.json.JsonBuilder()
        solutionParam = solutionParam?.toList()
        scoreParam = calcScore(solutionParam, guessParam, scoreParam)


        // Breaking condition
        def won = hasWon(solutionParam, answersParam?.toList()) 
        if (won) {
            return builder.gamePlay {
                message "You have already won"
                guess guessParam
                correctGuess false
            }   
        }   
        // Breaking condition 
        def lost = hasLost(scoreParam)
        if (won) {
            return builder.gamePlay {
                message "You have already lost"
                guess guessParam
                correctGuess false
            }   
        }
        // Breaking condition
        def guessedBefore = guessedBefore(answersParam?.toList(), guessParam)
        if (guessedBefore) {
            return builder.gamePlay {
                message "You have guessed this before"
                guess guessParam
                correctGuess false
            }           
        }

        // Normal game play
        answersParam = newAnswers(answersParam?.toList(), guessParam)?.join()
        won = hasWon(solutionParam, answersParam?.toList()) 
        return builder.gamePlay {
            if (correctGuess(solutionParam, guessParam)) {
                message "You guessed correct"
                correctGuess true
            } else {
                message "You guessed wrong"
                correctGuess false
            }
            score scoreParam
            answers answersParam
            guess guessParam
            currentSolution currentSolution(solutionParam, answersParam?.toList())  
            if (won) {
                message "You have won"
                dateWon new Date()
            }

            if (lost) { 
                message "You have lost"
                dateLost new Date()
            }
        }
    }
}
