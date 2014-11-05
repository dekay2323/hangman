class HangmanGame {
    final def welcome = "This is the game of hangman"
    final def loose = "You have lost"
    final def win = "You have won"

    def applyAnswer(def solution, def answer) {
        return printer(solution, answer)
    }
    
    def getNewScore(def solution, def answer, def score) {
        if (answer.size() == answer.intersect(solution).size()) {
            return score-1
        }
        score
    }

    boolean hasWon(def solution, def answer) {
        Set solutionSet = solution as Set
        Set answerSet = answer as Set
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
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
def solution = "testtest".toList()
def answer = []
def score = 8
println game.welcome
println game.applyAnswer(solution, answer)
println game.hasWon(solution, answer)
println "---"

answer += 't'
println score = game.getNewScore(solution, answer, score)
println "answer: ${answer}"
println game.applyAnswer(solution, answer)
println game.hasWon(solution, answer)
println "---"

answer += 'x'
println score = game.getNewScore(solution, answer, score)
println "answer: ${answer}"
println game.applyAnswer(solution, answer)
println game.hasWon(solution, answer)
println "---"

answer += 's'
println score = game.getNewScore(solution, answer, score)
println "answer: ${answer}"
println game.applyAnswer(solution, answer)
println game.hasWon(solution, answer)
println "---"

answer += 'e'
println score = game.getNewScore(solution, answer, score)
println "answer: ${answer}"
println game.applyAnswer(solution, answer)
println game.hasWon(solution, answer)


println "------------"