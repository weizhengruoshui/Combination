package com.stephen.combination

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.stephen.combination.analytics.AnalyticsAgency
import com.stephen.combination.dagger.component.AppComponent
import com.stephen.combination.dagger.component.DaggerAppComponent
import com.stephen.combination.dagger.module.AppModule
import com.stephen.combination.notification.NotificationData
import javax.inject.Inject

abstract class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var analyticsAgency: AnalyticsAgency

    override fun onCreate() {
        super.onCreate()
        initComponent()
        initAnalytics()
        initNotificationChannel()
    }

    //use to init app component
    open fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(
                AppModule(
                    this
                )
            )
            .build()
        appComponent.inject(this)
    }

    /**
     * in this method, you have to use
     * analyticsAgency.setAgent method to set
     * the agent of analyticsAgency
     */
    abstract fun initAnalytics()


    private fun initNotificationChannel() {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(
            createNotificationChannel(
                "Regular Notification",
                "This is a regular notification.",
                NotificationData.REGULAR_CHANNEL_ID
            )
        )

        notificationManager.createNotificationChannel(
            createNotificationChannel(
                "With Parent Notification",
                "This is a parent notification.",
                NotificationData.WITH_PARENT_CHANNEL_ID
            )
        )
    }

    private fun createNotificationChannel(
        name: String,
        descriptionText: String,
        channelId: String
    ): NotificationChannel {
        return NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = descriptionText
        }
    }

}