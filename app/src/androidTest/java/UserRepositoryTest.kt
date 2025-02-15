//package com.dilip.ghawade.fintechappassignment2
//
//import com.dilip.ghawade.fintechappassignment2.room.UserDao
//
//class UserRepositoryTest {
//    private lateinit var userDao: UserDao
//    private lateinit var userRepository: UserRepository
//
//    @Before
//    fun setUp() {
//        userDao = mock(UserDao::class.java)
//        userRepository = UserRepository(userDao)
//    }
//
//    @Test
//    fun `insertUser should call userDao insertUser`() = runBlocking {
//        val user = User(username = "testUser", password = "testPass", email = "test@example.com", mobileNo = "1234567890")
//
//        userRepository.insertUser(user)
//
//        verify(userDao).insertUser(user)
//    }
//}
