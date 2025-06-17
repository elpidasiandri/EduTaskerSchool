## EduTasker 

> EduTasker is a mobile application designed to manage tasks between professors and students in an educational setting.

**Project Overview**
This app facilitates a task management system where:
- Professors can:
     - Create tasks for students
     - Monitor the progress of each assigned task in real-time
     - Edit title/description/deadline of task and progress only when the progress is done
     - Search users on search bar
     - Filter students on dashboard to see the progress of their tasks
     - Tap notification icon to see the notifications of tasks
     - Tap on exit icon to go to login
- Students can:
     - View their assigned tasks
     - Update the progress status of each task
     - Tap notification icon to see the notifications of tasks
     - Tap on exit icon to go to login
> ⚠️ Note: This project uses mock data. You can view all user credentials and related data in the edutasker.mockData file.
To login suggested users:
professor -> anna@mail.com and password 1234
student -> maria@mail.com and password 1234

**Tech Stack**
- UI: Jetpack Compose, xml view system
- Architecture: MVVM & MVI
- Reactive Flow: StateFlow
- Local Storage: Room Database
- Project Structure:
   - Clean architecture with use cases and repositories
   - Separate repositories for each domain (professor, student, task, notification, common)
   - Navigation & UI Flow: Jetpack Navigation and XML where needed
   - domain models

## Schema

>> edutasker
* composable # composable files
    * errorOrSuccessToast  # Error message component
    * login         # login ui
    * professor  # professor ui
        * arrowWithStudents #arrow with all students (when the user taps on icon of student, the student's tasks appear)
        * assignTask #tapping on add icon, which is on bottom bar, professor can create a task
        * menu
        * searchBar
    * student  # student ui
        * menu
    * task  # task ui
        * preview #ui of tasks on preview 
            * professorActionsOnView
            * studentActionsOnView
        * taskProfile #ui task on bashboard
* db
    * daos             # Data Access Objects (DAO) for interacting with Room database
        *convert
    * entities         # Room entities representing database tables
        *relations
* di                   # Modules for Dependency Injection (using Koin)
* mapper
* mockdata
* model  # Domain models
* repositories
    * notificationDatabase    
    * professorDatabase   
    * relationsDatabase
    * studentDatabase
    * taskDatabase
* screens
    * login  
        * screens
        * viewModelState
    * notification    
    * professor   
        * screens
        * viewModel
    * student   
        * screens
        * viewModel
* uiTheme
* useCases
    * notification     
    * professor   
    * student
    * task
        * needOnInitialize
        * updateByProfessor
        * updateByStudent
* utils                # extensions, date helper

  

**Architecture & Code Organization**
- All UI colors are centralized in the edutasker.ui.theme package for consistent theming across the app.
- All strings are stored in res/values/strings.xml to support easy localization and maintainability.

**BaseActivity**
To reduce boilerplate code across activities, a reusable abstract class BaseActivity has been created.The setup, there, allows each activity to simply pass its specific ViewBinding, minimizing code repetition and avoiding the need to manually nullify bindings in every onDestroy.

**Navigation & Animations**
- Navigation is implemented using Navigation Component (NavGraph).
- Custom screen transitions and animations are added for a smooth user experience.
   - All animations are defined under the res/anim directory.

**Utilities & Extensions**
A utility file has been created under edutasker.utils to organize helpful Kotlin extensions used throughout the app.
- Compose Click Handling: noRippleClickable. A custom modifier that disables the default ripple effect in Jetpack Compose when an element is clicked.This improves UX in cases where visual click feedback is not desired.
- Error Handling:catchAndHandleError and showErrorBasedOnErrorCode. The catchAndHandleError catches exceptions and extracts both the error code and message in a structured way. Useful for centralized error processing across use cases and view models.The showErrorBasedOnErrorCode returns known error codes to specific string resource IDs to show relevant and localized error messages to users.
- Null Safety Extensions:orEmptyIfNull. To reduce repeated null checks and ensure default values.
