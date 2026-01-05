package com.dorm2khu.meal.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.dorm2khu.meal.ui.home.HomeRoute
import com.dorm2khu.meal.ui.home.HomeViewModel
import com.dorm2khu.meal.ui.sidemenu.SideMenu
import com.dorm2khu.meal.ui.sidemenu.SideMenuNavEvent
import com.dorm2khu.meal.ui.sidemenu.SideMenuViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun MainContainerRoute(
    sideMenuViewModel: SideMenuViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val hazeState = remember { HazeState() }

    LaunchedEffect(Unit) {
        sideMenuViewModel.fetchMenuDataIfNeeded()
    }

    LaunchedEffect(Unit) {
        sideMenuViewModel.navEvents.collect { event ->
            when (event) {
                is SideMenuNavEvent.RestaurantChanged -> {
                    homeViewModel.onRestaurantChanged(
                        restaurantId = event.restaurant.id,
                        restaurantName = event.restaurant.name
                    )
                }
                SideMenuNavEvent.ToSettings -> {
                    // settings navigation
                }
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                modifier = Modifier,
                hazeState = hazeState,
                restaurants = sideMenuViewModel.restaurants,
                selectedRestaurantId = sideMenuViewModel.selectedRestaurantId,
                settingsTitle = sideMenuViewModel.settingsTitle,
                onRestaurantSelected = { res ->
                    sideMenuViewModel.selectRestaurant(res.id)
                    scope.launch { drawerState.close() }
                },
                onSettingsSelected = {
                    sideMenuViewModel.openSettings()
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .haze(hazeState)
        ) {
            HomeRoute(
                onMenuButtonTapped = {
                    scope.launch { drawerState.open() }
                }
            )
        }
    }
}