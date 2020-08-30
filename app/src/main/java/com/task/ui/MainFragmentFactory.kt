package com.task.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.task.utils.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory @Inject constructor(
    private val networkHelper: NetworkHelper
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {

            BlogFragment::class.java.name -> {
                val fragment = BlogFragment(networkHelper)
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}