sealed class NetworkError(val message: String) {
    class ServerError(requestId: String, message: String?) :
        NetworkError(message = "Ошибка взаимодействия с сервером для запроса: id = $requestId. Сообщение об ошибке: $message")

    class NoData(requestId: String) :
        NetworkError(message = "Для запроса: id = $requestId нет данных")

    class NoInternet(val requestId: String) :
        NetworkError(message = "Нет подключения к интернету.")
}

class ErrorHandler {

    fun handleError(error: NetworkError) {
        when (error) {
            is NetworkError.ServerError -> showErrorMessage(error.message)
            is NetworkError.NoData -> showEmptyContent()
            is NetworkError.NoInternet -> {
                showErrorMessage(error.message)
                reloadRequest(error.requestId)
            }
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
        return when (code) { // метод будет вызываться программой всякий раз, когда будет получена ошибка
            null -> NetworkError.NoInternet(requestId)// возвращать ошибку NoInternet, если code = null
            200 -> NetworkError.NoData(requestId)// возвращать ошибку NoData, если code = 200
            else -> NetworkError.ServerError(requestId, error)// возвращать ошибку ServerError во всех остальных случаях
        }
    }
}