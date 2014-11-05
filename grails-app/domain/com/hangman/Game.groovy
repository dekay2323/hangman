package com.hangman

class Game {
	User user
	String solution
	String question
	String answers = ""
	Integer score = 0

    static constraints = {
    	solution nullable: false, blank: false
    	question nullable: false, blank: false
    	answers nullable: true, blank: true
    }

    public String toString() {
    	question
    }
}
