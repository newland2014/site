class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(controller: "/news")

        "500"(view:'/error')

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")
	}
}
