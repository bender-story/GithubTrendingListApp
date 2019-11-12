package self.com.githubtrendinglistapp

object Constants {
    const val NEWS_API_BASE_URL = "https://github-trending-api.now.sh/"
    const val twoHoursPeriod:Long= 7200000L
}

enum class ServiceType{
    API,
    MOCK
}

