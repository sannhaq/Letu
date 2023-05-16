package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.letu.R
import fragment.CartFragment
import fragment.HomeFragment
import fragment.ProfileFragment
import fragment.SearchFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var navigation: MeowBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation = findViewById(R.id.navigation)

        setFragment(HomeFragment.newInstance())
        navigation.add(MeowBottomNavigation.Model(1, R.drawable.home))
        navigation.add(MeowBottomNavigation.Model(2, R.drawable.search))
        navigation.add(MeowBottomNavigation.Model(3, R.drawable.cart))
        navigation.add(MeowBottomNavigation.Model(4, R.drawable.profile))

        navigation.setOnClickMenuListener{
            when(it.id){
                1 -> setFragment(HomeFragment.newInstance())
                2 -> setFragment(SearchFragment.newInstance())
                3 -> setFragment(CartFragment.newInstance())
                4 -> setFragment(ProfileFragment.newInstance())

                else -> " "
            }
        }
        navigation.show(1)
    }
    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }
}