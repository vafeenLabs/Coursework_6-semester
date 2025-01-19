package ru.vafeen.universityschedule.presentation.components.screens

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.vafeen.universityschedule.domain.models.model_additions.Role
import ru.vafeen.universityschedule.domain.utils.getMainColorForThisTheme
import ru.vafeen.universityschedule.domain.utils.getVersionName
import ru.vafeen.universityschedule.presentation.components.ui_utils.CardOfSettings
import ru.vafeen.universityschedule.presentation.components.ui_utils.ColorPickerDialog
import ru.vafeen.universityschedule.presentation.components.ui_utils.FeatureOfSettings
import ru.vafeen.universityschedule.presentation.components.ui_utils.TextForThisTheme
import ru.vafeen.universityschedule.presentation.components.video.AssetsInfo
import ru.vafeen.universityschedule.presentation.components.video.GifPlayer
import ru.vafeen.universityschedule.presentation.components.viewModels.SettingsScreenViewModel
import ru.vafeen.universityschedule.presentation.navigation.BottomBarNavigator
import ru.vafeen.universityschedule.presentation.theme.FontSize
import ru.vafeen.universityschedule.presentation.theme.Theme
import ru.vafeen.universityschedule.presentation.utils.Link
import ru.vafeen.universityschedule.presentation.utils.getIconByRequestStatus
import ru.vafeen.universityschedule.presentation.utils.groupCourseString
import ru.vafeen.universityschedule.presentation.utils.openLink
import ru.vafeen.universityschedule.presentation.utils.sendEmail
import ru.vafeen.universityschedule.presentation.utils.suitableColor
import ru.vafeen.universityschedule.resources.R

@Composable
internal fun SettingsScreen(bottomBarNavigator: BottomBarNavigator) {
    val viewModel: SettingsScreenViewModel = koinViewModel()
    val context = LocalContext.current
    val dark = isSystemInDarkTheme()
    val subgroupList by viewModel.subgroupFlow.collectAsState(listOf())
    val groupList by viewModel.groupFlow.collectAsState(listOf())
    val teachersList by viewModel.teachersFlow.collectAsState(listOf())
    val settings by viewModel.settings.collectAsState()

    var colorIsEditable by remember { mutableStateOf(false) }
    var isFeaturesEditable by remember { mutableStateOf(false) }
    var isGroupChanging by remember { mutableStateOf(false) }
    var isSubGroupChanging by remember { mutableStateOf(false) }
    var isTeacherChanging by remember { mutableStateOf(false) }
    var catsOnUIIsChanging by remember { mutableStateOf(false) }
    var isRoleChanging by remember { mutableStateOf(false) }
    val networkState by viewModel.gSheetsServiceRequestStatusFlow.collectAsState()

    BackHandler {
        bottomBarNavigator.back()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Заголовок экрана
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(
                        id = getIconByRequestStatus(
                            networkState = networkState
                        )
                    ),
                    contentDescription = stringResource(R.string.icon_data_updating_state),
                    tint = Theme.colors.oppositeTheme
                )
                Spacer(modifier = Modifier.width(15.dp))
                TextForThisTheme(
                    text = stringResource(R.string.settings),
                    fontSize = FontSize.big22
                )
            }
        }


        // Диалоговое окно для изменения цвета интерфейса
        if (colorIsEditable) {
            ColorPickerDialog(
                context = context,
                firstColor = settings.getMainColorForThisTheme(isDark = dark)
                    ?: Theme.colors.mainColor,
                onDismissRequest = { colorIsEditable = false }
            ) {
                viewModel.saveSettingsToSharedPreferences { settings ->
                    if (dark) settings.copy(
                        darkThemeColor = it
                    ) else settings.copy(lightThemeColor = it)
                }
            }
        }

        // Основной контент настроек
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Раздел "Общие"
            Box(modifier = Modifier.fillMaxWidth()) {
                TextForThisTheme(
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = FontSize.big22,
                    text = stringResource(R.string.general)
                )
                if (settings.catInSettings) {
                    GifPlayer(
                        size = 80.dp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { viewModel.meow() },
                        imageUri = Uri.parse(AssetsInfo.FUNNY_SETTINGS_CAT)
                    )
                }
            }

            // Роль
            CardOfSettings(
                text = stringResource(R.string.role),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.role),
                        contentDescription = stringResource(R.string.role),
                        tint = it.suitableColor()
                    )
                },
                onClick = {
                    isRoleChanging = !isRoleChanging
                },
                additionalContentIsVisible = isRoleChanging,
                additionalContent = {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = it)
                    ) {
                        items(Role.entries) { role ->
                            AssistChip(
                                leadingIcon = {
                                    if (role == settings.role) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.done),
                                            contentDescription = stringResource(R.string.this_is_user_role),
                                            tint = Theme.colors.oppositeTheme
                                        )
                                    }
                                },
                                modifier = Modifier.padding(horizontal = 3.dp),
                                onClick = {
                                    viewModel.saveSettingsToSharedPreferences { settings ->
                                        settings.copy(
                                            role = role
                                        ).let { s ->
                                            if (role == Role.Teacher) s.copy(
                                                subgroup = null,
                                                groupId = null
                                            ) else s.copy(teacherName = null)
                                        }
                                    }
                                },
                                label = { TextForThisTheme(text = stringResource(role.resourceName)) }
                            )
                        }

                    }
                }
            )

            // Группа
            if (groupList.isNotEmpty() && settings.role == Role.Student) {
                CardOfSettings(
                    text = stringResource(R.string.group),
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.group),
                            contentDescription = stringResource(R.string.group),
                            tint = it.suitableColor()
                        )
                    },
                    onClick = { isGroupChanging = !isGroupChanging },
                    additionalContentIsVisible = isGroupChanging,
                    additionalContent = {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = it)
                        ) {
                            items(groupList) { group ->

                                AssistChip(
                                    leadingIcon = {
                                        if (group.id == settings.groupId) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.done),
                                                contentDescription = stringResource(R.string.this_is_user_group),
                                                tint = Theme.colors.oppositeTheme
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(horizontal = 3.dp),
                                    onClick = {
                                        viewModel.saveSettingsToSharedPreferences { settings ->
                                            settings.copy(
                                                groupId = if (settings.groupId != group.id) group.id else null
                                            )
                                        }
                                    },
                                    label = { TextForThisTheme(text = group.groupCourseString()) }
                                )
                            }
                        }
                    }
                )
            }

            // Подгруппа
            if (subgroupList.isNotEmpty() && settings.role == Role.Student) {
                CardOfSettings(
                    text = stringResource(R.string.subgroup),
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.subgroup),
                            contentDescription = stringResource(R.string.subgroup),
                            tint = it.suitableColor()
                        )
                    },
                    onClick = { isSubGroupChanging = !isSubGroupChanging },
                    additionalContentIsVisible = isSubGroupChanging,
                    additionalContent = {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = it)
                        ) {
                            items(subgroupList) { subgroup ->
                                AssistChip(
                                    leadingIcon = {
                                        if (subgroup == settings.subgroup) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.done),
                                                contentDescription = stringResource(R.string.this_is_user_subgroup),
                                                tint = Theme.colors.oppositeTheme
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(horizontal = 3.dp),
                                    onClick = {
                                        viewModel.saveSettingsToSharedPreferences { settings ->
                                            settings.copy(
                                                subgroup = if (settings.subgroup != subgroup) subgroup else null
                                            )
                                        }
                                    },
                                    label = { TextForThisTheme(text = subgroup) }
                                )
                            }
                        }
                    }
                )
            }

            if (teachersList.isNotEmpty() && settings.role == Role.Teacher) {
                CardOfSettings(
                    text = stringResource(R.string.teacher),
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.teacher),
                            contentDescription = stringResource(R.string.icon_teacher),
                            tint = it.suitableColor()
                        )
                    },
                    onClick = { isTeacherChanging = !isTeacherChanging },
                    additionalContentIsVisible = isTeacherChanging,
                    additionalContent = {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = it)
                        ) {
                            items(teachersList) { teacher ->
                                AssistChip(
                                    leadingIcon = {
                                        if (teacher.name == settings.teacherName) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.done),
                                                contentDescription = stringResource(R.string.selected_teacher),
                                                tint = Theme.colors.oppositeTheme
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(horizontal = 3.dp),
                                    onClick = {
                                        viewModel.saveSettingsToSharedPreferences { settings ->
                                            settings.copy(
                                                teacherName = if (settings.teacherName != teacher.name) teacher.name else null
                                            )
                                        }
                                    },
                                    label = { TextForThisTheme(text = teacher.name) }
                                )
                            }
                        }
                    }
                )
            }

            // Карточка для настроек уведомлений
            CardOfSettings(
                text = stringResource(id = R.string.features),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.tune),
                        contentDescription = stringResource(R.string.features),
                        tint = it.suitableColor()
                    )
                },
                onClick = { isFeaturesEditable = !isFeaturesEditable },
                additionalContentIsVisible = isFeaturesEditable
            ) {
                FeatureOfSettings(
                    onClick = {
                        viewModel.saveSettingsToSharedPreferences { settings ->
                            settings.copy(notificationsAboutLesson = !settings.notificationsAboutLesson)
                        }
                    },
                    padding = it,
                    text = stringResource(R.string.notification_about_lesson_before_time),
                    checked = settings.notificationsAboutLesson
                )
                FeatureOfSettings(
                    onClick = {
                        viewModel.saveSettingsToSharedPreferences { settings ->
                            settings.copy(
                                notesAboutLesson = !settings.notesAboutLesson
                            )
                        }
                    },
                    padding = it,
                    text = stringResource(R.string.note),
                    checked = settings.notesAboutLesson
                )
            }

            // Раздел "Интерфейс"
            TextForThisTheme(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = FontSize.big22,
                text = stringResource(R.string.interface_str)
            )

            // Карточка для изменения цвета интерфейса
            CardOfSettings(
                text = stringResource(R.string.interface_color),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.palette),
                        contentDescription = stringResource(R.string.change_color_of_interface),
                        tint = it.suitableColor()
                    )
                },
                onClick = { colorIsEditable = true }
            )

            // Карточка для изменения отображения котиков
            CardOfSettings(
                text = stringResource(R.string.cats_on_ui),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = stringResource(R.string.cats_in_interface),
                        tint = it.suitableColor()
                    )
                },
                onClick = { catsOnUIIsChanging = !catsOnUIIsChanging },
                additionalContentIsVisible = catsOnUIIsChanging,
                additionalContent = {
                    Column {
                        FeatureOfSettings(
                            onClick = {
                                viewModel.saveSettingsToSharedPreferences { settings ->
                                    settings.copy(weekendCat = !settings.weekendCat)
                                }
                            },
                            padding = it,
                            text = stringResource(R.string.weekend_cat),
                            checked = settings.weekendCat
                        )
                        FeatureOfSettings(
                            onClick = {
                                viewModel.saveSettingsToSharedPreferences { settings ->
                                    settings.copy(catInSettings = !settings.catInSettings)
                                }
                            },
                            padding = it,
                            text = stringResource(R.string.cat_in_settings),
                            checked = settings.catInSettings
                        )
                    }
                }
            )

            // Раздел "Контакты"
            TextForThisTheme(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = FontSize.big22,
                text = stringResource(R.string.contacts)
            )

            // Карточка для отправки email
            CardOfSettings(
                text = stringResource(R.string.code),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.terminal),
                        contentDescription = stringResource(R.string.view_code),
                        tint = it.suitableColor()
                    )
                },
                onClick = { context.openLink(link = Link.CODE) }
            )

            // Карточка для отправки сообщения об ошибке
            CardOfSettings(
                text = stringResource(R.string.report_a_bug),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.bug_report),
                        contentDescription = stringResource(R.string.report_a_bug),
                        tint = it.suitableColor()
                    )
                }, onClick = {
                    context.sendEmail(email = Link.EMAIL)
                })
            // version
            TextForThisTheme(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(bottom = 20.dp)
                    .align(Alignment.End),
                fontSize = FontSize.small17,
                text = "${stringResource(R.string.version)} ${LocalContext.current.getVersionName()}"
            )
        }
    }
}
