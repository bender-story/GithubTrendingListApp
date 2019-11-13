package self.com.githubtrendinglistapp.network

import com.android.rahul.movies.network.ServiceInterface
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import retrofit2.mock.BehaviorDelegate
import self.com.githubtrendinglistapp.datamodel.Repositories
import self.com.githubtrendinglistapp.datamodel.TrendingData

/**
 * Mock service Interface
 *
 */
class MockServiceImpl(private val delegate: BehaviorDelegate<ServiceInterface>) :ServiceInterface{

    /**
     * get repository list from the service
     */
    override fun fetchTrendingRepositories(): Observable<List<Repositories>> {
        var json:String= getMockJSON()

        val mockResponse= Gson().fromJson(json, Array<Repositories>::class.java).toList()
        return delegate.returningResponse(mockResponse).fetchTrendingRepositories()
    }

    private fun getMockJSON():String{
        return "[\n" +
                "  {\n" +
                "    \"author\": \"tootsuite\",\n" +
                "    \"name\": \"mastodon\",\n" +
                "    \"avatar\": \"https://github.com/tootsuite.png\",\n" +
                "    \"url\": \"https://github.com/tootsuite/mastodon\",\n" +
                "    \"description\": \"Your self-hosted, globally interconnected microblogging community\",\n" +
                "    \"language\": \"Ruby\",\n" +
                "    \"languageColor\": \"#701516\",\n" +
                "    \"stars\": 19589,\n" +
                "    \"forks\": 3378,\n" +
                "    \"currentPeriodStars\": 113,\n" +
                "    \"builtBy\": [\n" +
                "      {\n" +
                "        \"username\": \"Gargron\",\n" +
                "        \"href\": \"https://github.com/Gargron\",\n" +
                "        \"avatar\": \"https://avatars3.githubusercontent.com/u/184731\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"username\": \"ThibG\",\n" +
                "        \"href\": \"https://github.com/ThibG\",\n" +
                "        \"avatar\": \"https://avatars3.githubusercontent.com/u/384364\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"username\": \"ykzts\",\n" +
                "        \"href\": \"https://github.com/ykzts\",\n" +
                "        \"avatar\": \"https://avatars3.githubusercontent.com/u/12539\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"AceLewis\",\n" +
                "    \"name\": \"my_first_calculator.py\",\n" +
                "    \"avatar\": \"https://github.com/AceLewis.png\",\n" +
                "    \"url\": \"https://github.com/AceLewis/my_first_calculator.py\",\n" +
                "    \"description\": \"my_first_calculator.py\",\n" +
                "    \"language\": \"Python\",\n" +
                "    \"languageColor\": \"#3572A5\",\n" +
                "    \"stars\": 1493,\n" +
                "    \"forks\": 157,\n" +
                "    \"currentPeriodStars\": 734,\n" +
                "    \"builtBy\": [\n" +
                "      {\n" +
                "        \"username\": \"AceLewis\",\n" +
                "        \"href\": \"https://github.com/AceLewis\",\n" +
                "        \"avatar\": \"https://avatars0.githubusercontent.com/u/10245962\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"username\": \"JuanPotato\",\n" +
                "        \"href\": \"https://github.com/JuanPotato\",\n" +
                "        \"avatar\": \"https://avatars1.githubusercontent.com/u/9531780\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"username\": \"SVilgelm\",\n" +
                "        \"href\": \"https://github.com/SVilgelm\",\n" +
                "        \"avatar\": \"https://avatars0.githubusercontent.com/u/523825\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"username\": \"clearsense\",\n" +
                "        \"href\": \"https://github.com/clearsense\",\n" +
                "        \"avatar\": \"https://avatars0.githubusercontent.com/u/9950886\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]"
    }

}