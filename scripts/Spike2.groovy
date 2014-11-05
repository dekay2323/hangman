def printer = { solution, answer ->
    solution.collect {
       if (answer.contains(it))
           return it
       else
           return '.'
    }
}

def solution = "testtest".toList()
def answer = []
answer = ['s']

println answer.intersect(solution)

answer += 'e'

println answer.intersect(solution)
println "printer: ${printer(solution, answer).join()}"

answer += 'x'

println answer.intersect(solution)

answer += 't'

println answer.intersect(solution)

Set solutionSet = solution as Set
println solutionSet.size()
println answer.intersect(solution).size()

