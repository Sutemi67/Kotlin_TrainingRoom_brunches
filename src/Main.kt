sealed class NetworkError(message: String) {
    data class ServerError(val requestId: String, val message: String?) :
        NetworkError(message = "Ошибка взаимодействия с сервером для запроса: id = $requestId. Сообщение об ошибке: $message")

    data class NoData(val requestId: String) : NetworkError(message = "Для запроса: id = $requestId нет данных")
    data class NoInternet(val requestId: String) : NetworkError(message = "Нет подключения к интернету.")
}

class ErrorHandler {

    fun handleError(error: NetworkError) {
        when (error) {
            is NetworkError.NoData -> showEmptyContent()
            is NetworkError.NoInternet -> reloadRequest(error.requestId)
            is NetworkError.ServerError -> error.message?.let { showErrorMessage(it) }
        }
    }

    private fun showErrorMessage(message: String) {
        println(message)
    }

    private fun showEmptyContent() {
        println("Показываем пустой экран")
    }

    private fun reloadRequest(requestId: String) {
        println("При появлении подключения к интернету перезапускаем запрос: id = $requestId")
    }
}

class Network {

    fun onNetworkError(code: Int?, requestId: String, error: String?): NetworkError {
        return when (code) {
            null -> NetworkError.NoInternet(requestId)
            200 -> NetworkError.NoData(requestId)
            else -> NetworkError.ServerError(requestId, error)
        }
    }
}