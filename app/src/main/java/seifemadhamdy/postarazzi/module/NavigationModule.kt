package seifemadhamdy.postarazzi.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import seifemadhamdy.postarazzi.navigator.Navigator

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavigationModule {

    @Provides
    fun provideNavigator(): Navigator {
        return Navigator()
    }
}