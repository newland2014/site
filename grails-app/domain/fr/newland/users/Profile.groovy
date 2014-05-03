package fr.newland.users

class Profile {
    String email
    String minecraftUsername

    static constraints = {
        email email: true
        minecraftUsername size: 4..25
    }
}
