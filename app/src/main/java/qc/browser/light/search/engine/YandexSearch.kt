package qc.browser.light.search.engine

import qc.browser.light.R
import qc.browser.light.constant.Constants

/**
 * The Yandex search engine.
 *
 * See http://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Yandex.svg/600px-Yandex.svg.png
 * for the icon.
 */
class YandexSearch : BaseSearchEngine(
        "file:///android_asset/yandex.png",
        Constants.YANDEX_SEARCH,
        R.string.search_engine_yandex
)
