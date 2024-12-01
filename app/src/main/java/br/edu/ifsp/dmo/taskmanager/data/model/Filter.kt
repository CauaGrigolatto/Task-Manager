package br.edu.ifsp.dmo.taskmanager.data.model

enum class Filter {
    ALL {
        override fun toString(): String {
            return "All";
        }
    },

    DONE {
        override fun toString(): String {
            return "Done"
        }
    },

    TODO {
        override fun toString(): String {
            return "Todo";
        }
    }
}