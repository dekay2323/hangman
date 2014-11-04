import com.hangman.Role
import com.hangman.User
import com.hangman.UserRole

class BootStrap {


    def init = { servletContext ->
    	def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
     	def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

     	def adminUser = new User(username: 'admin', password:'admin', enabled: true)
     	adminUser.save(flush: true)

     	UserRole.create adminUser, adminRole, true

     	assert User.count() == 1
     	assert Role.count() == 2
     	assert UserRole.count() == 1
	}

    def destroy = {
    }
}
