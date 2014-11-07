package com.hangman

class Game {
	User user
	String solution
	String question
	String answers
    String guess
    String currentSolution
    Date dateCreated = new Date()
    Date dateWon
    Date dateLost

    def gameLogicService
	
    Integer score = 8

    static constraints = {
    	solution size: 1..40, nullable: false, blank: false, matches: "[0-9a-zA-Z .,-]*"
    	question size: 1..255, nullable: false, blank: false
    	answers size: 1..50, nullable: true, blank: true
        currentSolution size: 1..40, nullable: true, blank: true
        guess size: 1..1, nullable: true, blank: true, matches: "[0-9a-zA-Z .,-]*"
        dateWon nullable: true
        dateLost nullable: true
        user nullable: true
    }

    public String toString() {
    	question
    }

}
