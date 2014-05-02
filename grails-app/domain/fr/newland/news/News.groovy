package fr.newland.news

import fr.newland.users.User

class News {
    String title
    String post
    Date dateCreated
    User author

    static mapping = {
        autoTimestamp true
    }
}
