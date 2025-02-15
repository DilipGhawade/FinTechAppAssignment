//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.dilip.ghawade.fintechappassignment2.model.User
//import com.dilip.ghawade.fintechappassignment2.room.UserDao
//import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.*
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//
//@ExperimentalCoroutinesApi
//class AuthViewModelTest {
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var userDao: UserDao
//
//    private lateinit var authViewModel: AuthViewModel
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this) // Use openMocks if you're using Mockito 4+
//        authViewModel = AuthViewModel(userDao)
//    }
//
//    @Test
//    fun `registerUser should return success message on successful registration`() = runBlockingTest {
//        val user = User(username = "testUser", password = "testPass", email = "test@example.com", mobileNo = "1234567890")
//
//        // Stub the userDao insertUser method
//        `when`(userDao.insertUser(user)).thenReturn(1) // simulate successful registration
//
//        authViewModel.registerUser(user)
//
//        assertEquals("User Registered Successfully", authViewModel.loginState.getOrAwaitValue())
//    }
//
//    @Test
//    fun `registerUser should return error message on registration failure`() = runBlockingTest {
//        val user = User(username = "testUser", password = "testPass", email = "test@example.com", mobileNo = "1234567890")
//
//        // Stub the userDao insertUser method to return 0
//        `when`(userDao.insertUser(user)).thenReturn(0) // simulate failure
//
//        authViewModel.registerUser(user)
//
//        assertEquals("Error Registering User", authViewModel.loginState.getOrAwaitValue())
//    }
//}
