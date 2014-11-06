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

    /**
    * This appends the guess onto the end of the answers
    */
    Collection newAnswers(Collection answers, String guess) {
        assert answers != null, "answers parameter should not be null"
        assert guess != null, "guess parameter should not be null"
        answers + guess?.toLowerCase()
    }

    Integer calcScore(Collection solution, String guess, Integer score) {
        if (solution?.find{it?.toLowerCase() == guess?.toLowerCase()} <= 0)
            return score-1
        score
    }

    Boolean correctGuess(Collection solution, String guess) {
        if (solution?.find{it?.toLowerCase() == guess?.toLowerCase()} > 0)
            return true
        false        
    }

    Boolean hasWon(Collection solution, Collection answers) {
        // Set removes duplicates
        Set solutionSet = (solution as Set).collect {it.toLowerCase()}
        Set answerSet = (answers as Set).collect {it.toLowerCase()}
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        false
    }
    
    Boolean hasLost(Integer score) {
        if (score <= 0)
            return true
            
        false
    }
    
    String printer(Collection solution, Collection answers) {
       solution.collect { solVar ->
       if (answers?.find {it?.toLowerCase() == solVar?.toLowerCase()} > 0)
           return solVar
       else
           return '.'
        }?.join()
    }

    /**
    * Core game play turn logic which
    *
    * 
    * @return JSON result set
    */
    def gameTurnLogicJson(def solutionParam, def answersParam, def guessParam, def scoreParam) {
        solutionParam = solutionParam?.toList()
        answersParam = newAnswers(answersParam?.toList(), guessParam)?.join()
        scoreParam = calcScore(solutionParam, guessParam, scoreParam)

        def builder = new groovy.json.JsonBuilder()
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
            currentSolution printer(solutionParam, answersParam?.toList())  
            if (hasWon(solutionParam, answersParam?.toList())) {
                message "You have won"
                dateWon new Date()
            }

            if (hasLost(scoreParam)) { 
                message "You have lost"
                dateLost new Date()
            }
        }
    }
}
