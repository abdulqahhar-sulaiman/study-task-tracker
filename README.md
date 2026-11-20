# Study Task Tracker

An Android app that helps students keep track of their study tasks. Built with Kotlin and Jetpack Compose as the Phase 1 project for **FNI-421 Mobile Computing** at Ain Shams University.

## Features

- Add a study task with a title and a subject
- View all tasks in a list
- Edit a task
- Mark a task as done or not done
- Delete a task
- Share a task with other apps (Android share sheet)
- Dark mode switch, remembered after the app is closed
- Tasks are saved on the device, so they are still there when the app reopens

## Tech Stack

| Area | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Task storage | Room |
| Settings storage | Jetpack DataStore (Preferences) |
| State | ViewModel, Kotlin Flow and StateFlow, coroutines |
| Sharing | Android Intent (`ACTION_SEND`) |

## Architecture

Data flows from the database up to the screens:

```
UI (Compose screens) -> TaskViewModel -> TaskRepository -> TaskDao -> Room database
```

Project structure (package `com.fni421.studytasktracker`):

```
data/       Task, TaskDao, AppDatabase, TaskRepository, SettingsStore
viewmodel/  TaskViewModel
ui/         TaskListScreen, TaskEditScreen, NavGraph, theme
util/       ShareHelper
MainActivity.kt
```

Navigation routes:

- `list`: the task list (start screen)
- `edit/{taskId}`: add or edit a task (`taskId = -1` means a new task)

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/abdulqahhar-sulaiman/study-task-tracker.git
   ```
2. Open the project folder in Android Studio.
3. Wait for Gradle to sync.
4. Run the app on an emulator or a physical Android device (minimum SDK 24).

## Team

| Member | Part |
|---|---|
| Abdul-Qahhar Sulaiman | Project setup, navigation, main activity, theme, integration |
| Arqam Mahmoud | Room database (entity, DAO, database) |
| Kaamelah Sulaiman-Olapade | Repository and ViewModel |
| Alamin Danladi | Task list screen |
| Zainab Ahmed | Add/edit task screen |
| Mahmoud Hussein | DataStore settings and share feature |

## Git Workflow

Each member worked on their own feature branch. Changes were merged into `main` through pull requests that needed one approving review.