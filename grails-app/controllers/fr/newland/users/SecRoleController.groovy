package fr.newland.users

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SecRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SecRole.list(params), model:[secRoleInstanceCount: SecRole.count()]
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def show(SecRole secRoleInstance) {
        respond secRoleInstance
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def create() {
        respond new SecRole(params)
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    @Transactional
    def save(SecRole secRoleInstance) {
        if (secRoleInstance == null) {
            notFound()
            return
        }

        if (secRoleInstance.hasErrors()) {
            respond secRoleInstance.errors, view:'create'
            return
        }

        secRoleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secRoleInstance.label', default: 'SecRole'), secRoleInstance.id])
                redirect secRoleInstance
            }
            '*' { respond secRoleInstance, [status: CREATED] }
        }
    }

    def edit(SecRole secRoleInstance) {
        respond secRoleInstance
    }

    @Transactional
    def update(SecRole secRoleInstance) {
        if (secRoleInstance == null) {
            notFound()
            return
        }

        if (secRoleInstance.hasErrors()) {
            respond secRoleInstance.errors, view:'edit'
            return
        }

        secRoleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SecRole.label', default: 'SecRole'), secRoleInstance.id])
                redirect secRoleInstance
            }
            '*'{ respond secRoleInstance, [status: OK] }
        }
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    @Transactional
    def delete(SecRole secRoleInstance) {

        if (secRoleInstance == null) {
            notFound()
            return
        }

        secRoleInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SecRole.label', default: 'SecRole'), secRoleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secRoleInstance.label', default: 'SecRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
