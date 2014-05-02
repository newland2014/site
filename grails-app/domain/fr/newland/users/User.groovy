package fr.newland.users

import fr.newland.news.News

class User {
    String username
    String password
    String email
    String minecraftUsername

    static constraints = {
        email email: true
        username size: 6..15
        password size: 8..25
        minecraftUsername size: 4..25
    }
}
