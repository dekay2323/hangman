package com.hangman

import grails.transaction.NotTransactional

class GameLogicService {
    static transactional = false

    public def newAnswers(def answers, def guess) {
        return answers + guess
    }

    public def calcScore(def solution, def guess, def score) {
        if (guess.intersect(solution)?.size() == 0)
            return score-1
        return score
    }

    public def hasWon(def solution, def answers) {
        Set solutionSet = solution as Set
        Set answerSet = answers as Set
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        return false
    }
    
    public def hasLost(def score) {
        if (score <= 0)
            return true
            
        return false
    }
    
    public def printer = { solution, answers ->
       solution.collect {
       if (answers?.contains(it))
           return it
       else
           return '.'
        }?.join()
    }
}
