package to.grindelf.todoapp.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.control.CheckBox
import javafx.scene.layout.HBox
import javafx.scene.control.Label
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.layout.VBox
import to.grindelf.todoapp.utils.DataProcessor

class MainController {

    @FXML
    private lateinit var taskInput: TextField
    @FXML
    private lateinit var taskListView: ListView<HBox>

    private val tasks: ObservableList<HBox> = DataProcessor.readTasks()

    @FXML
    fun initialize() {
        taskListView.items = tasks
    }

    @FXML
    fun addTask() {
        val taskText = taskInput.text.trim()

        if (taskText.isNotEmpty()) {
            val taskLabel = Label(taskText)
            val checkBox = CheckBox()
            val removeButton = Button("Remove")

            val taskHBox = HBox(10.0, checkBox, taskLabel, removeButton)

            removeButton.setOnAction {
                tasks.remove(taskHBox)
            }
            tasks.add(taskHBox)
            taskInput.clear()
        }
    }

    @FXML
    fun removeSelectedTask() {
        val tasksToRemove = tasks.filter {
            val taskHBox = it.children.find { it is CheckBox } as? CheckBox
            taskHBox?.isSelected == true
        }

        tasks.removeAll(tasksToRemove)
    }

    fun transmitTasks(): ObservableList<HBox> = tasks

    @FXML
    fun saveTasks() {
        DataProcessor.saveTasks(tasks)
    }
}