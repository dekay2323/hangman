package com.hangman

import grails.transaction.Transactional

@Transactional
class GameLogicService {

    public def applyAnswer(def solution, def answers, def newAnswer, def score) {
    	def reply = [:]
        reply.score = score
        if (newAnswer.intersect(solution)?.size() == 0)
            reply.score = reply.score - 1
        reply.answers = answers + newAnswer
        reply.message = printer(solution, reply.answers)
        reply.hasWon = hasWon(solution, reply.answers)
        reply.hasLost = hasLost(score, solution, reply.answers)
        return reply
    }


    public def hasWon(def solution, def answers) {
        Set solutionSet = solution as Set
        Set answerSet = answers as Set
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        return false
    }
    
    public def hasLost(def score, def solution, def answers) {
        if (hasWon(solution, answers))
            return false
            
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
