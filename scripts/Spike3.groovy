class HangmanGame {
    final def welcome = "This is the game of hangman"
    final def loose = "You have lost"
    final def win = "You have won"

    def loosePoint(def solution, def answer) {
        answer.intersect(solution)
    }

    def gainPoint(def solution, def answer) {
        answer.intersect(solution)
    }

    boolean hasWon(def solution, def answer) {
        Set solutionSet = solution as Set
        println solutionSet.size()
        Set answerSet = answer as Set
        if (answerSet.intersect(solutionSet).size() == solutionSet.size())
            return true

        return false
    }
}

println "Guess
println "------------"