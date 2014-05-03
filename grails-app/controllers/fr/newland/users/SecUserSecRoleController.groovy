package fr.newland.users

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SecUserSecRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SecUserSecRole.list(params), model: [secUserSecRoleInstanceCount: SecUserSecRole.count()]
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def show(SecUserSecRole secUserSecRoleInstance) {
        respond secUserSecRoleInstance
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def create() {
        respond new SecUserSecRole(params)
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    @Transactional
    def save(SecUserSecRole secUserSecRoleInstance) {
        if (secUserSecRoleInstance == null) {
            notFound()
            return
        }

        if (secUserSecRoleInstance.hasErrors()) {
            respond secUserSecRoleInstance.errors, view: 'create'
            return
        }

        secUserSecRoleInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secUserSecRoleInstance.label', default: 'SecUserSecRole'), secUserSecRoleInstance.id])
                redirect secUserSecRoleInstance
            }
            '*' { respond secUserSecRoleInstance, [status: CREATED] }
        }
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def edit(SecUserSecRole secUserSecRoleInstance) {
        respond secUserSecRoleInstance
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    @Transactional
    def update(SecUserSecRole secUserSecRoleInstance) {
        if (secUserSecRoleInstance == null) {
            notFound()
            return
        }

        if (secUserSecRoleInstance.hasErrors()) {
            respond secUserSecRoleInstance.errors, view: 'edit'
            return
        }

        secUserSecRoleInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SecUserSecRole.label', default: 'SecUserSecRole'), secUserSecRoleInstance.id])
                redirect secUserSecRoleInstance
            }
            '*' { respond secUserSecRoleInstance, [status: OK] }
        }
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    @Transactional
    def delete(SecUserSecRole secUserSecRoleInstance) {

        if (secUserSecRoleInstance == null) {
            notFound()
            return
        }

        secUserSecRoleInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SecUserSecRole.label', default: 'SecUserSecRole'), secUserSecRoleInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secUserSecRoleInstance.label', default: 'SecUserSecRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
