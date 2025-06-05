package com.example.edutasker.mockData

import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.useCases.task.TaskUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MockData {
    fun insertMockData(
        taskUseCases: TaskUseCases,
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            // Professors
            val prof1 = ProfessorEntity(
                "p1",
                "John",
                "Smith",
                "jo@mail.com",
                "1234",
                "https://i.guim.co.uk/img/media/59baecefbc73d3bcf4a47b017453a27f19b55175/331_488_2481_1489/master/2481.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=285e4f639f1c71cbdf27c57315394bb1",
                listOf("Biology", "Chemistry", "Physics")
            )
            val prof2 = ProfessorEntity(
                "p2",
                "Anna",
                "Jones",
                "anna@mail.com",
                "1234",
                "https://cdn.eduadvisor.my/articles/2022/04/how-to-be-teacher-malaysia-feature.png",
                listOf("Maths", "Biology", "Thesis")
            )
            val prof3 = ProfessorEntity(
                "p3",
                "Mike",
                "Brown",
                "mikeBrown@mail.com",
                "1234",
                "",
                listOf("History")
            )

            // Students
            val students = listOf(
                StudentEntity(
                    "s1",
                    "Alex",
                    "Papadopoulos",
                    "https://t4.ftcdn.net/jpg/02/24/86/95/360_F_224869519_aRaeLneqALfPNBzg0xxMZXghtvBXkfIA.jpg",
                    "al@mail.com",
                    "1234",
                    listOf("Maths", "Physics")
                ),
                StudentEntity(
                    "s2",
                    "Kate",
                    "Papadopoulou",
                    "",
                    "kat@mail.com",
                    "1234",
                    listOf("Maths", "Biology")
                ),
                StudentEntity(
                    "s3",
                    "John",
                    "Papas",
                    "https://s39613.pcdn.co/wp-content/uploads/2018/01/student-backpack-id683911900-FF180119.jpg",
                    "pas@mail.com",
                    "1234",
                    listOf("Chemistry", "History", "Biology")
                ),
                StudentEntity(
                    "s4",
                    "Lisa",
                    "Jone",
                    "",
                    "lis@mail.com",
                    "1234",
                    listOf("Maths", "Thesis")
                ),
                StudentEntity(
                    "s5",
                    "Maria",
                    "Marou",
                    "https://img.freepik.com/free-photo/portrait-young-student-happy-be-back-university_23-2148586577.jpg?semt=ais_hybrid&w=740",
                    "maria@mail.com",
                    "1234",
                    listOf("Maths")
                ),
                StudentEntity(
                    "s6",
                    "Nick",
                    "James",
                    "",
                    "nick@mail.com",
                    "1234",
                    listOf("Maths", "History")
                ),
                StudentEntity(
                    "s7",
                    "Mitso",
                    "Mitso",
                    "https://1f90aa49a6.cbaul-cdnwnd.com/3aa7324c017277129ad47ed56667ff02/200000027-d841bd841e/8a94663a80ff9f19873971cea2bce2f0.jpg?ph=1f90aa49a6",
                    "mitsos@mail.com",
                    "1234",
                    listOf("Thesis", "Physics")
                ),
                StudentEntity(
                    "s8",
                    "papa",
                    "Maria",
                    "https://1f90aa49a6.cbaul-cdnwnd.com/3aa7324c017277129ad47ed56667ff02/200000024-0f0480f04a/0d5f8a0a02b8891c6ea74c3ace6aadb8.jpg?ph=1f90aa49a6",
                    "ma0@mail.com",
                    "1234",
                    listOf("Thesis", "History", "Chemistry")
                ),
            )

            // Tasks
            val tasks = listOf(
                TaskEntity(
                    "t1",
                    "Solve integrals",
                    "Maths",
                    "Calculate the following definite and indefinite integrals using standard techniques. Justify each step.",
                    "p2",
                    "26/06/2025",
                    creationDate = "11/05/2025",
                    assignTo = "s1",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t2",
                    "Solve integrals",
                    "Maths",
                    "Calculate the following definite and indefinite integrals using standard techniques. Justify each step.",
                    "p2",
                    "26/06/2025",
                    creationDate = "11/05/2025",
                    assignTo = "s2",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t3",
                    "Newton’s laws",
                    "Physics",
                    "Explain each of Newton’s three laws of motion with real-world examples. Then, solve the attached problems applying each law.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s1",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t4",
                    "Newton’s laws",
                    "Physics",
                    "Explain each of Newton’s three laws of motion with real-world examples. Then, solve the attached problems applying each law.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s7",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t5",
                    "Linear equations",
                    "Maths",
                    "Solve the system of linear equations provided in the worksheet. Show all steps of your work clearly.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s2",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t6",
                    "Linear equations",
                    "Maths",
                    "Solve the system of linear equations provided in the worksheet. Show all steps of your work clearly.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s2",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t7",
                    "Cell structure",
                    "Biology",
                    "Describe the structure and function of major cell organelles. Create a labeled diagram of a eukaryotic cell.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s2",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t8",
                    "Cell structure",
                    "Biology",
                    "Describe the structure and function of major cell organelles. Create a labeled diagram of a eukaryotic cell.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s3",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t9",
                    "WWII timeline",
                    "History",
                    "Create a detailed timeline of the major events of World War II from 1939 to 1945. Include dates and brief descriptions.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s6",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t10",
                    "WWII timeline",
                    "History",
                    "Create a detailed timeline of the major events of World War II from 1939 to 1945. Include dates and brief descriptions.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s3",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t11",
                    "Matrix multiplication",
                    "Maths",
                    "Perform the multiplication of the given matrices. Provide the final result and intermediate steps where necessary.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s4",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t12",
                    "Matrix multiplication",
                    "Maths",
                    "Perform the multiplication of the given matrices. Provide the final result and intermediate steps where necessary.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s5",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t13",
                    "Periodic table",
                    "Chemistry",
                    "Identify 10 elements from the periodic table and describe their group, period, and key properties in one sentence each.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s8",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t14",
                    "Periodic table",
                    "Chemistry",
                    "Identify 10 elements from the periodic table and describe their group, period, and key properties in one sentence each.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s8",
                    progress = TaskStatus.TODO.name
                ),
                TaskEntity(
                    "t15",
                    "Derivatives",
                    "Maths",
                    "Differentiate the following functions with respect to x. Justify each step using rules of differentiation.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s8",
                    progress = TaskStatus.IN_PROGRESS.name
                ),
                TaskEntity(
                    "t16",
                    "Electricity",
                    "Physics",
                    "Define current, voltage, and resistance. Solve the circuit problems based on Ohm’s Law.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s7",
                    progress = TaskStatus.IN_PROGRESS.name
                ),
                TaskEntity(
                    "t17",
                    "Ancient Greece",
                    "History",
                    "Write a short essay on the political system of Athens in Ancient Greece and compare it to modern democracy.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s6",
                    progress = TaskStatus.DONE.name
                ),
                TaskEntity(
                    "t18",
                    "Ancient Greece",
                    "History",
                    "Write a short essay on the political system of Athens in Ancient Greece and compare it to modern democracy.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = "s6",
                    progress = TaskStatus.DONE.name
                )
            )

            //Notifications
            val notification = listOf(
                NotificationEntity(
                    notificationId = "n1",
                    taskId = "t1",
                    studentId = "s1",
                    professorId = "p2",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n2",
                    taskId = "t2",
                    studentId = "s2",
                    professorId = "p2",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n3",
                    taskId = "t3",
                    studentId = "s1",
                    professorId = "p1",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n4",
                    taskId = "t4",
                    studentId = "s7",
                    professorId = "p1",
                    readableByProfessor = true,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n5",
                    taskId = "t5",
                    studentId = "s2",
                    professorId = "p2",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n6",
                    taskId = "t6",
                    studentId = "s2",
                    professorId = "p2",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n7",
                    taskId = "t7",
                    studentId = "s2",
                    professorId = "p1",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n8",
                    taskId = "t8",
                    studentId = "s3",
                    professorId = "p2",
                    readableByProfessor = true,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n9",
                    taskId = "t9",
                    studentId = "s6",
                    professorId = "p3",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n10",
                    taskId = "t10",
                    studentId = "s3",
                    professorId = "p3",
                    readableByProfessor = true,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n11",
                    taskId = "t11",
                    studentId = "s4",
                    professorId = "p2",
                    readableByProfessor = false,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n12",
                    taskId = "t12",
                    studentId = "s5",
                    professorId = "p2",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n13",
                    taskId = "t13",
                    studentId = "s8",
                    professorId = "p1",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n14",
                    taskId = "t14",
                    studentId = "s8",
                    professorId = "p1",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n15",
                    taskId = "t15",
                    studentId = "s8",
                    professorId = "p3",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n16",
                    taskId = "t16",
                    studentId = "s7",
                    professorId = "p3",
                    readableByProfessor = false,
                    readableByStudent = false
                ),
                NotificationEntity(
                    notificationId = "n17",
                    taskId = "t17",
                    studentId = "s6",
                    professorId = "p3",
                    readableByProfessor = true,
                    readableByStudent = true
                ),
                NotificationEntity(
                    notificationId = "n18",
                    taskId = "t18",
                    studentId = "s6",
                    professorId = "p3",
                    readableByProfessor = false,
                    readableByStudent = false
                )
            )

            taskUseCases.insertMockData(
                professors = listOf(prof1, prof2, prof3),
                students = students,
                tasks = tasks,
                notifications = notification
            )
        }
    }
}
