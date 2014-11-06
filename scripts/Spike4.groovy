def builder = new groovy.json.JsonBuilder()
def response = builder.gamePlay {
    message "You guessed correct"
    correctGuess "hello"

}
println builder.toPrettyString()

println "-----------------"