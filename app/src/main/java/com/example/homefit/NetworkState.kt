package com.example.homefit

abstract class NetworkState private constructor() {
    abstract fun getMessage(): String

    abstract inner class Success : NetworkState() {
        override fun getMessage(): String {
            return "No error found"
        }
    }

    class Error(var error: String) : NetworkState() {
        override fun getMessage(): String {
            return error
        }
    }

    inner class NetworkException(var error: String) : NetworkState() {
        override fun getMessage(): String {
            return error
        }
    }

    abstract class HttpErrors internal constructor() : NetworkState() {
        class ResourceForbidden(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }

        class ResourceNotFound(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }

        class InternalServerError(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }

        class BadGateWay(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }

        class ResourceRemoved(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }

        class RemovedResourceFound(var exception: String) : HttpErrors() {
            override fun getMessage(): String {
                return exception
            }
        }
    }
}