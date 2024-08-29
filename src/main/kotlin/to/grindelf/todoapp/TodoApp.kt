package to.grindelf.todoapp

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import to.grindelf.todoapp.controllers.MainController
import to.grindelf.todoapp.utils.DataProcessor

object TodoApp {
    @JvmStatic
    fun main(args: Array<String>) {
        Application.launch(TodoApplication::class.java)
    }

    class TodoApplication : Application() {
        override fun start(stage: Stage) {
            val fxmlLoader = FXMLLoader(TodoApp::class.java.getResource("main-view.fxml"))
            val scene = Scene(fxmlLoader.load(), 720.0, 440.0)
            stage.title = "Todo App"
            stage.scene = scene
            stage.show()

//            stage.onCloseRequest = EventHandler {
//                applicationStopSequence()
//            }

        }

//        private fun applicationStopSequence() {
//            DataProcessor.saveTasks(MainController().transmitTasks())
//            println(MainController().transmitTasks())
//        }
    }
}
