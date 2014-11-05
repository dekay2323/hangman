class Solution {
    String solution = "testtest"

    public def applyAnswer(def answer) {
        def matcher = solution =~ /${answer}/
        matcher.each { println it }
    } 
    
    public def isSolved(def answer) {
        solution == answer
    }
}

class Answer {
    String answer
}

class AnswerResult {
}

class Game {
}

    def ans = new Answer(answer: "t")
    def sol = new Solution()
    

println "------"
sol.applyAnswer("t")
println sol.isSolved("test")
println sol.isSolved("testtest")
println "------"

