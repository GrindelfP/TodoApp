package to.grindelf.todoapp.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import java.io.File

object DataProcessor {

    private const val FILE_PATH: String = "src/main/resources/to/grindelf/todoapp/tasks"
    private const val MT_FILE_PATH: String = "src/main/resources/to/grindelf/todoapp/tasks_mt"
    private val serializer: ObjectMapper = objectMapper()
    private val tasksFile: File = File(FILE_PATH)

    private fun <T> observableListToList(observableList: ObservableList<T>): List<T> {
        val list: MutableList<T> = mutableListOf()
        observableList.forEach { element ->
            list.add(element)
        }

        return list
    }

    private fun listToObservableListOfHBox(list: List<String>): ObservableList<HBox> {
        val observableList: ObservableList<HBox> = FXCollections.observableArrayList()

        list.forEach { element ->
            val hBox = HBox(10.0, CheckBox(), Label(element), Button("Remove"))
            observableList.add(hBox)
        }

        return observableList
    }

    fun readTasks(): ObservableList<HBox> {
        val dataAsText: String = tasksFile.readText()
        val taskList: List<String> = if (dataAsText.isNotEmpty()) {
            serializer.readValue<List<String>>(dataAsText)
        } else {
            emptyList()
        }

        return listToObservableListOfHBox(taskList)
    }

    fun saveTasks(tasks: ObservableList<HBox>) {
        val tasksAsListOfStrings = mutableListOf<String>()
        val tasksAsList = observableListToList(tasks)

        tasks.forEach { task ->
            val taskText = task.children.filterIsInstance<Label>().firstOrNull()
            if (taskText != null) tasksAsListOfStrings.add(taskText.text)
        }

        serializer.writeValue(File(FILE_PATH), tasksAsListOfStrings)
    }

    private fun objectMapper(): ObjectMapper = ObjectMapper().registerModules(kotlinModule())
}
