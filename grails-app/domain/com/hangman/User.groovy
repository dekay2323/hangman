package com.hangman

class User {
	String name

	static hasMany = [games: Game]

    static constraints = {
    	name nullable: false, blank: false
    	games nullable: true
    }

    public String toString() {
    	name
    }    
}
