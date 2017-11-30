package qc.browser.light.search

import android.app.Application
import com.anthonycr.bonsai.Single
import com.anthonycr.bonsai.SingleAction
import qc.browser.light.database.HistoryItem
import qc.browser.light.search.suggestions.BaiduSuggestionsModel
import qc.browser.light.search.suggestions.DuckSuggestionsModel
import qc.browser.light.search.suggestions.GoogleSuggestionsModel

internal object SuggestionsManager {

    @JvmStatic
    @Volatile var isRequestInProgress: Boolean = false

    @JvmStatic
    fun createGoogleQueryObservable(query: String, application: Application) =
            Single.create(SingleAction<List<HistoryItem>> { subscriber ->
                isRequestInProgress = true
                val results = GoogleSuggestionsModel(application).fetchResults(query)
                subscriber.onItem(results)
                subscriber.onComplete()
                isRequestInProgress = false
            })

    @JvmStatic
    fun createBaiduQueryObservable(query: String, application: Application) =
            Single.create(SingleAction<List<HistoryItem>> { subscriber ->
                isRequestInProgress = true
                val results = BaiduSuggestionsModel(application).fetchResults(query)
                subscriber.onItem(results)
                subscriber.onComplete()
                isRequestInProgress = false
            })

    @JvmStatic
    fun createDuckQueryObservable(query: String, application: Application) =
            Single.create(SingleAction<List<HistoryItem>> { subscriber ->
                isRequestInProgress = true
                val results = DuckSuggestionsModel(application).fetchResults(query)
                subscriber.onItem(results)
                subscriber.onComplete()
                isRequestInProgress = false
            })

}
