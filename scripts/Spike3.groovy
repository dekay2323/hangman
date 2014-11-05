class HangmanGame {
    final def welcome = "This is the game of hangman"
    
    def applyAnswer(def solution, def answer, def newAnswer, def score) {
        def reply = [:]
        reply.score = score
        if (newAnswer.intersect(solution)?.size() == 0)
            reply.score = reply.score - 1
        reply.answer = answer + newAnswer
        reply.message = printer(solution, reply.answer)
        reply.hasWon = hasWon(solution, reply.answer)
        reply.hasLost = hasLost(score)
        return reply
    }

    private boolean hasWon(def solution, def answer) {
        Set solutionSet = solution as Set
        Set answerSet = answer as Set
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        return false
    }
    
    private boolean hasLost(def score) {
        if (score <= 0)
            return true
            
        return false
    }
    
    private def printer = { solution, answer ->
       solution.collect {
       if (answer.contains(it))
           return it
       else
           return '.'
        }?.join()
    }
}

println "---Start"
def game = new HangmanGame()
println game.welcome

def reply = game.applyAnswer("testtest".toList(), [], ['t'], 8)
println reply.answer
println reply.score
println reply.message
println reply.hasWon
println "--"

reply = game.applyAnswer("testtest".toList(), reply.answer, ['x'], reply.score)
println reply.answer
println reply.score
println reply.message
println reply.hasWon
println reply.hasLost
println "--"


reply = game.applyAnswer("testtest".toList(), reply.answer, ['z'], reply.score)
println reply.answer
println reply.score
println reply.message
println reply.hasWon
println reply.hasLost
println "--"

reply = game.applyAnswer("testtest".toList(), reply.answer, ['e'], reply.score)
println reply.answer
println reply.score
println reply.message
println reply.hasWon
println reply.hasLost
println "--"

reply = game.applyAnswer("testtest".toList(), reply.answer, ['s'], reply.score)
println reply.answer
println reply.score
println reply.message
println reply.hasWon
println reply.hasLost
println "--"

println "------------"