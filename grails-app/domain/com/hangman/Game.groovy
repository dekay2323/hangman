package com.hangman

class Game {
	User user
	String solution
	String question
	String answers
    String guess
    String currentSolution
    Date dateStarted = new Date()
    Date dateWon
    Date dateLost

	Integer score = 8

    static constraints = {
    	solution size: 1..40, nullable: false, blank: false
    	question size: 1..255, nullable: false, blank: false
    	answers size: 1..50, nullable: true, blank: true
        currentSolution size: 1..40, nullable: true, blank: true
        guess size: 1..1, nullable: true, blank: true
        dateWon nullable: true
        dateLost nullable: true
    }

    public String toString() {
    	question
    }
}
