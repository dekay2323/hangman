import com.hangman.Game

class BootStrap {

    def init = { servletContext ->
   		def game1 = new Game(user: "Demian", solution: "testest")
		game1.save(flush: true)
		game1 = new Game(user: "Carrie", solution: "Tiberius")
		game1.save(flush: true)
    }
    def destroy = {
    }
}
