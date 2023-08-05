object Deps {
    object Moko {
        private const val version = "0.16.1"

        const val mokoCore = "dev.icerock.moko:mvvm-core:$version"
        const val mokoCompose = "dev.icerock.moko:mvvm-compose:$version"
        const val mokoFlow = "dev.icerock.moko:mvvm-flow:$version"
        const val mokoFlowCompose = "dev.icerock.moko:mvvm-flow-compose:$version"
    }

    object Sqldelight {
        private const val version = "1.5.5"

        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val runtime = "com.squareup.sqldelight:runtime:$version"
        const val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:$version"
        const val androidDriver = "com.squareup.sqldelight:android-driver:$version"
        const val iosDriver = "com.squareup.sqldelight:native-driver:$version"
    }

    object Kotlinx {
        object Coroutines {
            private const val version = "1.5.2"

            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }

        object DateTime {
            private const val version = "0.4.0"

            const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$version"
        }
    }

    object Ktor {
        private const val version = "2.2.3"

        const val core = "io.ktor:ktor-client-core:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val serialization= "io.ktor:ktor-serialization-kotlinx-json:$version"

        // engines
        const val ktorAndroid = "io.ktor:ktor-client-android:$version"
        const val ktorIOS = "io.ktor:ktor-client-darwin:$version"
    }

    object Koin {
        private const val version = "3.2.0"

        const val core = "io.insert-koin:koin-core:$version"
        const val test = "io.insert-koin:koin-test:$version"
        const val android = "io.insert-koin:koin-android:$version"
    }

    object UI {
        private const val version = "1.4.0.9"

        const val composeView = "io.github.ltttttttttttt:ComposeViews:$version"
    }

    object Android {
        private const val androidCoreVersion = "1.10.1"
        const val androidCore = "androidx.core:core:$androidCoreVersion"

        private const val activityComposeVersion = "1.7.2"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

        private const val appcompatVersion = "1.6.1"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
    }

    object Test {

    }
}