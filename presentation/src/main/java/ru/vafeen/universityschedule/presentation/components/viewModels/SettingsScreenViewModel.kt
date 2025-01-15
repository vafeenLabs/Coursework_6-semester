package ru.vafeen.universityschedule.presentation.components.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.models.Settings
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.network.FetchDataAndUpdateDBUseCase

/**
 * ViewModel для экрана настроек.
 * Управляет состоянием, связанным с настройками, обновлениями из Google Sheets и запросами на сервер.
 *
 * @property getAsFlowLessonsUseCase Юзкейс для получения данных о занятиях.
 * @property fetchDataAndUpdateDBUseCase Юзкейс для получения данных из Google Sheets и обновления базы данных.
 * @property getAsFlowGroupsUseCase Юзкейс для получения данных о группах.
 * @property catMeowUseCase Юзкейс для выполнения действия "мяу".
 * @property settingsManager Менеджер для работы с настройками приложения.
 */
internal class SettingsScreenViewModel(
    private val getAsFlowLessonsUseCase: GetAsFlowLessonsUseCase,
    private val fetchDataAndUpdateDBUseCase: FetchDataAndUpdateDBUseCase,
    private val getAsFlowGroupsUseCase: GetAsFlowGroupsUseCase,
    private val catMeowUseCase: CatMeowUseCase,
    private val settingsManager: SettingsManager,
) : ViewModel() {

    /**
     * Поток состояний для хранения настроек приложения.
     */
    val settings = settingsManager.settingsFlow

    /**
     * Поток состояний для хранения списка подгрупп.
     * Формируется из данных о занятиях.
     */
    val subgroupFlow = getAsFlowLessonsUseCase.invoke().map {
        it.mapNotNull { lesson -> lesson.subGroup }.distinct()
    }.stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = listOf())

    /**
     * Поток состояний для хранения списка групп.
     */
    val groupFlow = getAsFlowGroupsUseCase.invoke()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = listOf())

    /**
     * Поток состояний для статуса запроса к серверу Google Sheets.
     */
    private val _gSheetsServiceRequestStatusFlow =
        MutableStateFlow(GSheetsServiceRequestStatus.Waiting)
    val gSheetsServiceRequestStatusFlow = _gSheetsServiceRequestStatusFlow.asStateFlow()

    /**
     * Функция для выполнения действия "мяу".
     */
    fun meow() {
        catMeowUseCase.invoke()
    }

    /**
     * Сохраняет изменения настроек в SharedPreferences.
     * Принимает функцию, изменяющую текущие настройки.
     *
     * @param saving Функция, изменяющая настройки.
     */
    fun saveSettingsToSharedPreferences(saving: (Settings) -> Settings) {
        settingsManager.save(saving)
    }

    init {
        // Запускает процесс получения данных из Google Sheets и обновления базы данных.
        viewModelScope.launch(Dispatchers.IO) {
            fetchDataAndUpdateDBUseCase.invoke(this@launch) { status ->
                _gSheetsServiceRequestStatusFlow.emit(status)
            }
        }
    }
}
