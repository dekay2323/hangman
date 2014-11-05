class Solution {
    String solution = "testtest"

    public def applyAnswer(def answer, def current) {
        def matcher = solution =~ /[${answer}]/
        //matcher.each { println "it: ${it}" }
        def replacer = (solution =~ /[^${answer}]/)?.replaceAll(".")
        println "replacer ${replacer}, current ${current}"
        replacer
    } 
    
    public def isSolved(def answer) {
        solution == answer
    }
}

class Answer {
    String answer
}

class Game {
}

    def ans = new Answer(answer: "t")
    def sol = new Solution()
    

println "------"
def current
current = sol.applyAnswer("t", current)
current = sol.applyAnswer("x", current)
current = sol.applyAnswer("e", current)
current = sol.applyAnswer("testtest", current)
println "------"

