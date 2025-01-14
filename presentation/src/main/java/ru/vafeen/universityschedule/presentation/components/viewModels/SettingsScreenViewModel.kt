package ru.vafeen.universityschedule.presentation.components.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.models.Settings
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLessonDataAndUpdateDBUseCase

/**
 * ViewModel для экрана настроек.
 * Управляет состоянием, связанным с настройками, обновлениями из Google Sheets и запросами на сервер.
 *
 * @param getAsFlowLessonsUseCase UseCase для получения данных о занятиях.
 * @param getLessonDataAndUpdateDBUseCase UseCase для получения данных из Google Sheets и обновления базы данных.
 * @param catMeowUseCase UseCase для выполнения действия "мяу".
 * @param settingsManager Менеджер для работы с настройками приложения.
 */
internal class SettingsScreenViewModel(
    private val getAsFlowLessonsUseCase: GetAsFlowLessonsUseCase,
    private val getLessonDataAndUpdateDBUseCase: GetLessonDataAndUpdateDBUseCase,
    private val catMeowUseCase: CatMeowUseCase,
    private val settingsManager: SettingsManager,
) : ViewModel() {
    // Поток состояний для хранения настроек приложения
    val settings = settingsManager.settingsFlow

    /**
     * Функция для выполнения действия "мяу".
     */
    fun meow() {
        catMeowUseCase.invoke()
    }

    /**
     * Сохраняет изменения настроек в SharedPreferences.
     *
     * @param saving Функция, изменяющая текущие настройки.
     */
    fun saveSettingsToSharedPreferences(saving: (Settings) -> Settings) {
        settingsManager.save(saving)
    }

    // Поток состояний для статуса запроса к Google Sheets
    private val _gSheetsServiceRequestStatusFlow =
        MutableStateFlow<GSheetsServiceRequestStatus>(GSheetsServiceRequestStatus.Waiting)
    val gSheetsServiceRequestStatusFlow = _gSheetsServiceRequestStatusFlow.asStateFlow()

    init {
        // Запрос на получение данных
        viewModelScope.launch(Dispatchers.IO) {
            getLessonDataAndUpdateDBUseCase.invoke { status ->
                _gSheetsServiceRequestStatusFlow.emit(status)
            }
        }
    }


    // Поток состояний для хранения списка подгрупп
    private val _subgroupFlow = MutableStateFlow<List<String>>(listOf())
    val subgroupFlow = _subgroupFlow.asStateFlow()

    init {
        // Инициализируем поток для получения подгрупп из базы данных
        viewModelScope.launch(Dispatchers.IO) {
            getAsFlowLessonsUseCase.invoke().map { it.mapNotNull { lesson -> lesson.subGroup } }
                .collect { subGroups ->
                    // Эмитируем только уникальные подгруппы
                    _subgroupFlow.emit(subGroups.distinct())
                }
        }
    }
}
