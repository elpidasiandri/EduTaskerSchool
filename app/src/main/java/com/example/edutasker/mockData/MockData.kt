package com.example.edutasker.mockData

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.useCases.professor.InsertProfessorUseCase
import com.example.edutasker.useCases.student.InsertStudentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MockData {
    fun insertMockData(
        taskUseCases: TaskUseCases,
        insertProfessor: InsertProfessorUseCase,
        insertStudent: InsertStudentUseCase,
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
                listOf("Biology", "Chemistry")
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
                "p3", "Mike", "Brown", "mikeBrown@mail.com", "1234", "",
                listOf("History")
            )

            insertProfessor(prof1)
            insertProfessor(prof2)
            insertProfessor(prof3)

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
                    listOf("Chemistry")
                ),
                StudentEntity(
                    "s4",
                    "Lisa",
                    "Jone",
                    "",
                    "lis@mail.com",
                    "1234",
                    listOf("History", "Maths", "Thesis")
                ),
                StudentEntity(
                    "s5",
                    "Maria",
                    "Marou",
                    "https://img.freepik.com/free-photo/portrait-young-student-happy-be-back-university_23-2148586577.jpg?semt=ais_hybrid&w=740",
                    "maria@mail.com",
                    "1234",
                    listOf("Physics")
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
                    "",
                    "mitsos@mail.com",
                    "1234",
                    listOf("Thesis", "Physics")
                ),
                StudentEntity(
                    "s8",
                    "papa",
                    "Maria",
                    "",
                    "maria@mail.com",
                    "1234",
                    listOf("Thesis", "History", "Chemistry")
                ),
            )

            students.forEach { insertStudent(it) }

            // Tasks
            val tasks = listOf(
                TaskEntity(
                    "t1",
                    "Solve integrals",
                    "Maths",
                    "Calculate the following definite and indefinite integrals using standard techniques. Justify each step.",
                    "p1",
                    "26/06/2025",
                    creationDate = "11/05/2025",
                    assignTo = listOf("s1", "s2"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t2",
                    "Newton’s laws",
                    "Physics",
                    "Explain each of Newton’s three laws of motion with real-world examples. Then, solve the attached problems applying each law.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s1", "s2"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t3",
                    "Linear equations",
                    "Maths",
                    "Solve the system of linear equations provided in the worksheet. Show all steps of your work clearly.",
                    "p1",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s2", "s3"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t4",
                    "Cell structure",
                    "Biology",
                    "Describe the structure and function of major cell organelles. Create a labeled diagram of a eukaryotic cell.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s2", "s3"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t5",
                    "WWII timeline",
                    "History",
                    "Create a detailed timeline of the major events of World War II from 1939 to 1945. Include dates and brief descriptions.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s2", "s3"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t6",
                    "Matrix multiplication",
                    "Maths",
                    "Perform the multiplication of the given matrices. Provide the final result and intermediate steps where necessary.",
                    "p2",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s2", "s3"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t7",
                    "Periodic table",
                    "Chemistry",
                    "Identify 10 elements from the periodic table and describe their group, period, and key properties in one sentence each.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s1", "s4"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t8",
                    "Derivatives",
                    "Maths",
                    "Differentiate the following functions with respect to x. Justify each step using rules of differentiation.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s4", "s3"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t9",
                    "Electricity",
                    "Physics",
                    "Define current, voltage, and resistance. Solve the circuit problems based on Ohm’s Law.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s5"),
                    progress = "TODO"
                ),
                TaskEntity(
                    "t10",
                    "Ancient Greece",
                    "History",
                    "Write a short essay on the political system of Athens in Ancient Greece and compare it to modern democracy.",
                    "p3",
                    "28/06/2025",
                    creationDate = "22/05/2025",
                    assignTo = listOf("s2", "s5"),
                    progress = "TODO"
                ),
            )


            tasks.forEach { taskUseCases.insertTask(it) }

            val taskStudentMap = mapOf(
                "t1" to listOf("s1", "s2", "s4"),
                "t2" to listOf("s1", "s5"),
                "t3" to listOf("s1", "s6"),
                "t4" to listOf("s2"),
                "t5" to listOf("s4", "s6"),
                "t6" to listOf("s1", "s2"),
                "t7" to listOf("s3"),
                "t8" to listOf("s1", "s6"),
                "t9" to listOf("s1", "s5"),
                "t10" to listOf("s4", "s6"),
            )

            taskStudentMap.forEach { (taskId, studentIds) ->
                taskUseCases.assignTaskToStudents(taskId, studentIds)
            }
        }
    }
}
