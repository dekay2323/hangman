package com.hangman

class Game {
	User user
	String solution
	String question
	String answers = ""
    Character guess

	Integer score = 8

    static constraints = {
    	solution nullable: false, blank: false
    	question nullable: false, blank: false
    	answers nullable: true, blank: true
        guess nullable: true, blank: true
    }

    public String toString() {
    	question
    }
}
