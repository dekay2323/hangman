package com.hangman

class Game {
	User user
	String solution
	String question
	String answers = ""
    String guess

	Integer score = 8

    static constraints = {
    	solution nullable: false, blank: false
    	question nullable: false, blank: false
    	answers nullable: true, blank: true
        guess size: 1..1, nullable: true, blank: true
    }

    public String toString() {
    	question
    }
}
