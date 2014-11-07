import com.hangman.Game
import com.hangman.User
import org.springframework.web.context.support.WebApplicationContextUtils 
import org.codehaus.groovy.grails.commons.GrailsApplication; 
import grails.util.GrailsUtil; 
import groovy.ui.Console; 

class BootStrap {

    def init = { servletContext ->

        if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) { 
	      	/*
			// Start the console, great for spiking
	      	def appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
	      	def grailsApp = appCtx.getBean(GrailsApplication.APPLICATION_ID);
	      	Binding b = new Binding();
	      	b.setVariable("ctx", appCtx);
	      	def console = new Console(grailsApp.classLoader, b);
	      	console.run()*/

            // Load some test data 
    		def user1 = new User(name: "Demian")
			user1.save(flush: true)
			def user2 = new User(name: "Carrie")
			user2.save(flush: true)               
   			def game = new Game(user: user1, solution: "Tiberius", question: "Captain Kirk's middle name?")
			game.save(flush: true)
			game = new Game(user: user2, solution: "Vulcan", question: "Spock's birth planet?")
			game.save(flush: true)            
        }



    }
    def destroy = {
    }
}
