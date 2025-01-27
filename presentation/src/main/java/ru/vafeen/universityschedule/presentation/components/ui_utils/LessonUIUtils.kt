package ru.vafeen.universityschedule.presentation.components.ui_utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.models.model_additions.Frequency
import ru.vafeen.universityschedule.domain.utils.generateID
import ru.vafeen.universityschedule.presentation.components.viewModels.MainScreenViewModel
import ru.vafeen.universityschedule.presentation.theme.FontSize
import ru.vafeen.universityschedule.presentation.theme.Theme
import ru.vafeen.universityschedule.presentation.utils.NotificationAboutLessonsSettings
import ru.vafeen.universityschedule.presentation.utils.copyTextToClipBoard
import ru.vafeen.universityschedule.presentation.utils.createReminderAfterStartingLessonForBeCheckedAtThisLesson
import ru.vafeen.universityschedule.presentation.utils.createReminderBefore15MinutesOfLesson
import ru.vafeen.universityschedule.presentation.utils.getLessonTimeString
import ru.vafeen.universityschedule.presentation.utils.openLink
import ru.vafeen.universityschedule.presentation.utils.suitableColor
import ru.vafeen.universityschedule.resources.R
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Lesson.StringForSchedule(
    paddingValues: PaddingValues = PaddingValues(vertical = 10.dp),
    viewModel: MainScreenViewModel,
    dateOfThisLesson: LocalDate?,
    colorBack: Color,
    isNoteAvailable: Boolean,
    isNotificationsAvailable: Boolean,
) {
    suspend fun generateID(): Int = viewModel.remindersFlow.first().map {
        it.idOfReminder
    }.generateID()

    val focusManager = LocalFocusManager.current
    val settings by viewModel.settingsFlow.collectAsState()
    var note by remember { mutableStateOf(this.note ?: "") }
    var isFocused by remember { mutableStateOf(false) }
    val suitableColor by remember { mutableStateOf(colorBack.suitableColor()) }
    val context = LocalContext.current
    val checkBoxColor = CheckboxDefaults.colors(
        checkedColor = suitableColor,
        checkmarkColor =  suitableColor.suitableColor(),
        uncheckedColor =  suitableColor,
    )
    val outlinedTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = suitableColor,
        unfocusedTextColor = suitableColor,
        unfocusedLabelColor = suitableColor,
        focusedLabelColor = suitableColor,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        unfocusedBorderColor = suitableColor,
        focusedBorderColor = suitableColor,
    )

    var isAdditionalInfoExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        colors = CardDefaults.cardColors(
            containerColor = colorBack
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (isNoteAvailable || isNotificationsAvailable) it.clickable {
                        isAdditionalInfoExpanded = !isAdditionalInfoExpanded
                    }
                    else it
                }
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = stringResource(R.string.icon_time),
                    tint = suitableColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = getLessonTimeString(),
                    fontSize = FontSize.small17,
                    color = suitableColor
                )
                if (classroom?.isNotEmpty() == true) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.place),
                        contentDescription = stringResource(R.string.icon_classroom),
                        tint = suitableColor
                    )
                    Text(
                        text = classroom ?: undefined,
                        color = suitableColor,
                        fontSize = FontSize.small17
                    )
                }
                if (settings.notesAboutLesson || settings.notificationsAboutLesson || linkToCourse != null) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (idOfReminderBeforeLesson == null || idOfReminderAfterBeginningLesson == null || this@StringForSchedule.note?.isNotEmpty() != true) Icon(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = stringResource(R.string.edit_notifications_about_this_lesson),
                                tint = suitableColor
                            )
                            if (linkToCourse?.isNotEmpty() == true) Icon(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = stringResource(R.string.moodle_course_link),
                                tint = suitableColor
                            )
                            if (this@StringForSchedule.note?.isNotEmpty() == true) Icon(
                                painter = painterResource(id = R.drawable.edit),
                                contentDescription = stringResource(R.string.note),
                                tint = suitableColor
                            )
                            if (idOfReminderBeforeLesson != null)
                                Icon(
                                    painter = painterResource(id = R.drawable.message),
                                    contentDescription = stringResource(R.string.reminder_about_lesson_before_time),
                                    tint = suitableColor
                                )
                            if (idOfReminderAfterBeginningLesson != null)
                                Icon(
                                    painter = painterResource(id = R.drawable.notification_about_checking),
                                    contentDescription = stringResource(R.string.reminder_about_lesson_before_time),
                                    tint = suitableColor
                                )
                        }
                    }
                }
            }

            name.let {
                if (it.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = it, color = suitableColor, fontSize = FontSize.big22
                    )
                }
            }



            if (teacher.isNotEmpty()) Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = stringResource(R.string.icon_teacher),
                    tint = suitableColor
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = teacher.replace(oldValue = " ", newValue = "\n") ?: undefined,
                    fontSize = FontSize.small17,
                    color = suitableColor,
                    maxLines = 3
                )
            }

            if (subGroup?.isNotEmpty() == true) Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.subgroup),
                    contentDescription = stringResource(R.string.icon_subgroup),
                    tint = suitableColor
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = subGroup ?: undefined,
                    fontSize = FontSize.small17,
                    color = suitableColor,
                    maxLines = 3
                )
            }
            if (isAdditionalInfoExpanded) linkToCourse?.let { link ->
                if (link.isNotEmpty()) {
                    Row(
                        modifier = Modifier.combinedClickable(onClick = { context.openLink(link) },
                            onLongClick = {
                                context.copyTextToClipBoard(text = link, label = null)
                            }).padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.moodle_course_link),
                            fontSize = FontSize.small17,
                            color = Theme.colors.linkColor
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = stringResource(R.string.moodle_course_link),
                            tint = Theme.colors.linkColor
                        )
                    }
                }
            }

            if (!isAdditionalInfoExpanded && note.isNotEmpty())
                Text(text = note, fontSize = FontSize.micro14, color = suitableColor)

            if (isAdditionalInfoExpanded && (isNoteAvailable || isNotificationsAvailable)) Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isNoteAvailable) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                        value = note,
                        onValueChange = {
                            note = it
                        },
                        label = { Text(text = stringResource(R.string.note)) },
                        colors = outlinedTextFieldColors,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                            viewModel.updateLesson(this@StringForSchedule.copy(note = note))
                        }),
                        trailingIcon = {
                            if (isFocused && note.isNotEmpty()) {
                                IconButton(onClick = {
                                    note = ""
                                    focusManager.clearFocus()
                                    viewModel.updateLesson(this@StringForSchedule.copy(note = note))
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.clear),
                                        contentDescription = stringResource(R.string.clear_text),
                                        tint = suitableColor
                                    )
                                }
                            }
                        })
                }
                if (dateOfThisLesson != null && isNotificationsAvailable) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = stringResource(id = if (frequency == Frequency.Every || frequency == null) R.string.every_week else R.string.every_2_weeks),
                        fontSize = FontSize.medium19,
                        color = suitableColor
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = stringResource(R.string.reminder_about_lesson_before_time),
                                tint = suitableColor
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = stringResource(id = R.string.notification_about_lesson_before_time),
                                fontSize = FontSize.medium19,
                                color = suitableColor
                            )
                        }
                        Checkbox(
                            checked = idOfReminderBeforeLesson != null, onCheckedChange = {
                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    if (idOfReminderBeforeLesson == null) {
                                        val idOfNewReminder = generateID()
                                        val newReminder =
                                            this@StringForSchedule.createReminderBefore15MinutesOfLesson(
                                                idOfNewReminder = idOfNewReminder,
                                                dt = LocalDateTime.of(
                                                    dateOfThisLesson,
                                                    this@StringForSchedule.startTime.minusMinutes(
                                                        NotificationAboutLessonsSettings.MINUTES_BEFORE_LESSON_FOR_NOTIFICATION
                                                    )
                                                ),
                                                context = context,
                                            )
                                        viewModel.addReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
                                            lesson = this@StringForSchedule,
                                            newReminder = newReminder
                                        )
                                    } else {
                                        viewModel.removeReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
                                            lesson = this@StringForSchedule
                                        )
                                    }
                                }
                            }, colors = checkBoxColor
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.notification_about_checking),
                                contentDescription = stringResource(R.string.notification_about_lesson_after_starting),
                                tint = Theme.colors.oppositeTheme
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = stringResource(id = R.string.notification_about_lesson_after_starting),
                                fontSize = FontSize.medium19,
                                color = suitableColor
                            )
                        }
                        Checkbox(
                            checked = idOfReminderAfterBeginningLesson != null, onCheckedChange = {
                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    if (idOfReminderAfterBeginningLesson == null) {
                                        val idOfNewReminder = generateID()
                                        val newReminder =
                                            this@StringForSchedule.createReminderAfterStartingLessonForBeCheckedAtThisLesson(
                                                idOfNewReminder = idOfNewReminder,
                                                dt = LocalDateTime.of(
                                                    dateOfThisLesson,
                                                    this@StringForSchedule.startTime
                                                ),
                                                context = context
                                            )
                                        viewModel.addReminderAboutCheckingOnLessonAndUpdateLocalDB(
                                            lesson = this@StringForSchedule,
                                            newReminder = newReminder
                                        )
                                    } else {
                                        viewModel.removeReminderAboutCheckingOnLessonAndUpdateLocalDB(
                                            lesson = this@StringForSchedule
                                        )
                                    }
                                }
                            }, colors = checkBoxColor
                        )
                    }
                }
            }
        }
    }
}


