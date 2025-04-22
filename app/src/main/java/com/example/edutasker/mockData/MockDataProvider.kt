package com.example.edutasker.mockData

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.useCases.TaskUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MockDataProvider {
    fun insertMockData(taskUseCases: TaskUseCases) {
        CoroutineScope(Dispatchers.IO).launch {
            // Professors
            val prof1 = ProfessorEntity(
                "p1",
                "John",
                "Smith",
                "josnsmith@mail.com",
                "1234",
                "https://i.guim.co.uk/img/media/59baecefbc73d3bcf4a47b017453a27f19b55175/331_488_2481_1489/master/2481.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=285e4f639f1c71cbdf27c57315394bb1"
            )
            val prof2 = ProfessorEntity(
                "p2",
                "Anna",
                "Jones",
                "anna@mail.com",
                "1234",
                "https://cdn.eduadvisor.my/articles/2022/04/how-to-be-teacher-malaysia-feature.png"
            )
            val prof3 = ProfessorEntity("p3", "Mike", "Brown", "mikeBrown@mail.com", "1234", "")

            taskUseCases.insertProfessor(prof1)
            taskUseCases.insertProfessor(prof2)
            taskUseCases.insertProfessor(prof3)

            // Students
            val students = listOf(
                StudentEntity(
                    "s1",
                    "Alex",
                    "Papadopoulos",
                    "https://t4.ftcdn.net/jpg/02/24/86/95/360_F_224869519_aRaeLneqALfPNBzg0xxMZXghtvBXkfIA.jpg",
                    "alexPapa@mail.com",
                    "1234",
                    listOf("Math", "Physics")
                ),
                StudentEntity(
                    "s2",
                    "Kate",
                    "Papadopoulou",
                    "",
                    "katePapa@mail.com",
                    "1234",
                    listOf("Math", "Biology")
                ),
                StudentEntity(
                    "s3",
                    "John",
                    "Papas",
                    "https://s39613.pcdn.co/wp-content/uploads/2018/01/student-backpack-id683911900-FF180119.jpg",
                    "johnPapas@mail.com",
                    "1234",
                    listOf("Chemistry")
                ),
                StudentEntity(
                    "s4",
                    "Lisa",
                    "Jone",
                    "",
                    "lisaJone@mail.com",
                    "1234",
                    listOf("History", "Math")
                ),
                StudentEntity(
                    "s5",
                    "Maria",
                    "Marou",
                    "https://img.freepik.com/free-photo/portrait-young-student-happy-be-back-university_23-2148586577.jpg?semt=ais_hybrid&w=740",
                    "mariaMarou@mail.com",
                    "1234",
                    listOf("Physics")
                ),
                StudentEntity(
                    "s6",
                    "Nick",
                    "James",
                    "",
                    "nickJames@mail.com",
                    "1234",
                    listOf("Math", "History")
                ),
            )

            students.forEach { taskUseCases.insertStudent(it) }

            // Tasks
            val tasks = listOf(
                TaskEntity("t1", "Math", "Solve integrals", "p1",
//                    "s1"
                ),
                TaskEntity("t2", "Physics", "Newton’s laws", "p1"),
                TaskEntity("t3", "Math", "Linear equations", "p1"),
                TaskEntity("t4", "Biology", "Cell structure", "p2"),
                TaskEntity("t5", "History", "WWII timeline", "p2"),
                TaskEntity("t6", "Math", "Matrix multiplication", "p2"),
                TaskEntity("t7", "Chemistry", "Periodic table", "p3"),
                TaskEntity("t8", "Math", "Derivatives", "p3"),
                TaskEntity("t9", "Physics", "Electricity", "p3"),
                TaskEntity("t10", "History", "Ancient Greece", "p3"),
            )

            tasks.forEach { taskUseCases.insertTask(it) }

            // Connect tasks to students (randomly or based on subject match)
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
