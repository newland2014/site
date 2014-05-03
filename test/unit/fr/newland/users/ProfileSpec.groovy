package fr.newland.users

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Profile)
class ProfileSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create a simple user"() {
        when:
        def email = "email@email.com"
        def minecraftUsername = "minecraft"
        Profile user = new Profile(
                email: email,
                minecraftUsername: minecraftUsername);

        then:
        user.validate()

        when:
        user.save()

        then:
        Profile databaseUser = Profile.findById(user.id)
        assertEquals(email, databaseUser.email)
        assertEquals(minecraftUsername, databaseUser.minecraftUsername)
    }

    void "create a user with missing properties"() {
        when:
        Profile user = new Profile();

        then:
        !user.validate()

        and:
        def errors = user.errors
        errors.allErrors.size() == 4

    }

    void "create blank properties"() {
        when:
        def email = ""
        def minecraftUsername = ""

        Profile user = new Profile(
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
        def email = "email"
        def minecraftUsername = "mc"
        Profile user = new Profile(
                email: email,
                minecraftUsername: minecraftUsername);

        then:
        !user.validate()

        and:
        def errors = user.errors
        errors.allErrors.size() == 2

    }
}
