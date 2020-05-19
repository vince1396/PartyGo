import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rpifinal.hitema.partyGo.data.user.repositories.UserRepository
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}