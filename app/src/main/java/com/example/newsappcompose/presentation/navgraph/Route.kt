package com.example.newsappcompose.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object OnboardingScreen : Route(route = "onboardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object BookmarkScreen : Route(route = "bookmarkScreen")
    data object DetailsScreen : Route(route = "detailsScreen")
    data object AppStartNavigation : Route(route = "appStartNavigation")
    data object NewsNavigation : Route(route = "newsNavigation")
    data object NewsNavigatorScreen : Route(route = "newsNavigatorScreen")
}
