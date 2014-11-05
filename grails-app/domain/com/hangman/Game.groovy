package com.hangman

class Game {
	String user
	String solution
	String question

    static constraints = {
    	solution nullable: false, blank: false
    	question nullable: false, blank: false
    }
}
