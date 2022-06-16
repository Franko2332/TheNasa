package ru.gb.thenasa.model

class Const {
    companion object {
        const val BASE_URL = "https://api.nasa.gov/"
        const val DEFAULT_THEME = 0
        const val MOON_THEME = 1
        const val MARTIAN_THEME = 2
        const val THEME_ID = "THEME_ID"
        const val VISIBLE_FRAGMENT = "VISIBLE_FRAGMENT"
        const val DATE = "DATE"
    }

    object NasaAppFragmentsNames{
        const val TO_DO_NOTES_FRAGMENT = "TO_DO_NOTES_FRAGMENT"
        const val MAIN_FRAGMENT = "MAIN_FRAGMENT"
        const val SETTINGS_FRAGMENT = "SETTINGS_FRAGMENT"
        const val MARTIAN_PICTURES_FRAGMENT = "MARTIAN_PICTURES_FRAGMENT"
    }


    object RoverCamerasNames {
        const val FHAZ = "fhaz"
        const val RHAZ = "rhaz"
        const val MAST = "mast"
    }
}