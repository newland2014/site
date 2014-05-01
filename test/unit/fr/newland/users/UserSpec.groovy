package fr.newland.users

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create a simple user"() {
        when:
        def username = "username"
        def password = "password"
        def email = "email@email.com"
        def minecraftUsername = "minecraft"
        User user = new User(
                username: username,
                password: password,
                email: email,
                minecraftUsername: minecraftUsername);

        then:
        user.validate()

        when:
        user.save()

        then:
        User databaseUser = User.findById(user.id)
        assertEquals(username, databaseUser.username)
        assertEquals(password, databaseUser.password)
        assertEquals(email, databaseUser.email)
        assertEquals(minecraftUsername, databaseUser.minecraftUsername)
    }

    void "create a user with missing properties"() {
        when:
        User user = new User();

        then:
        !user.validate()

        and:
        def errors = user.errors
        errors.allErrors.size() == 4

    }

    void "create blank properties"() {
        when:
        def username = ""
        def password = ""
        def email = ""
        def minecraftUsername = ""

        User user = new User(
                username: username,
                password: password,
                email: email,
                minecraftUsername: minecraftUsername);

        then:
        !user.validate()

        and:
        def errors = user.errors
        errors.allErrors.size() == 4

    }

    void "create user with wrong properties"() {
        when:
        def username = "usr"
        def password = "pwd"
        def email = "email"
        def minecraftUsername = "mc"
        User user = new User(
                username: username,
                password: password,
                email: email,
                minecraftUsername: minecraftUsername);

        then:
        !user.validate()

        and:
        def errors = user.errors
        errors.allErrors.size() == 4

    }
}
