package com.sametdundar.guaranteeapp.navigation

import com.sametdundar.guaranteeapp.utils.Constants.CONTINUE_SCREEN
import com.sametdundar.guaranteeapp.utils.Constants.FORM_DETAIL
import com.sametdundar.guaranteeapp.utils.Constants.LIST_SCREEN
import com.sametdundar.guaranteeapp.utils.Constants.SHARE_SCREEN


sealed class ScreenRoutes(val route : String) {
    data object Home : ScreenRoutes(LIST_SCREEN)
    data object Favorites : ScreenRoutes(SHARE_SCREEN)
    data object Messages : ScreenRoutes(CONTINUE_SCREEN)
    data object FormDetail : ScreenRoutes(FORM_DETAIL)
}