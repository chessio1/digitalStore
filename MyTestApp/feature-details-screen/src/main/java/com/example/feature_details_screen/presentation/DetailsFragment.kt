package com.example.feature_details_screen.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.utils.autoCleared
import com.example.core.utils.utils.convertToMoney
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.FragmentDetailsBinding
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.navigate
import kotlin.math.abs

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val vm: DetailsViewModel by viewModel()
    private val binding: FragmentDetailsBinding by viewBinding()
    private var imagesAdapter: ImagesCarouselAdapter by autoCleared()
    private var firebaseAnalytics: FirebaseAnalytics by autoCleared()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.loadDetails(itemId = (arguments?.get("deviceId") as String))
        initFirebaseAnalytics()

    }

    private fun initFirebaseAnalytics() {
            firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewPager()
        initDetailsObserver()

    }

    private fun initView() {

        binding.addToFavouritesButton.setOnClickListener { button ->
            button.isSelected = !button.isSelected
        }

        binding.myCardButton.setOnClickListener {
            navigate("https://mysite.com/cart")
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addToCartButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID,arguments?.get("deviceId") as String)
            }
            firebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.ADD_TO_CART,bundle)
        }

    }

    private fun initViewPager() {
        imagesAdapter = ImagesCarouselAdapter()
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )

        binding.carouselPager.apply {
            adapter = imagesAdapter
            offscreenPageLimit = 1
            setPageTransformer(pageTransformer)
            addItemDecoration(itemDecoration)
        }

    }

    private fun initDetailsObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.details.collect {
                if (it == null) return@collect
                imagesAdapter.addImages(it.images)
                with(binding) {
                    progressBar.isVisible = false
                    detailsCard.isVisible = true
                    phoneNameTextView.text = it.title
                    ratingBar.rating = it.rating.toFloat()
                    processorTextView.text = it.CPU
                    cameraTextView.text = it.camera.replace(",", "+")
                    ssdTextView.text = it.ssd
                    hddTextView.text = it.sd
                    hdd1.text = it.capacity[0]
                    hdd2.text = it.capacity[1]
                    color1.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor(it.color[0]))
                    color2.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor(it.color[1]))

                    val priceText = it.price.toString().convertToMoney()
                    priceTextView.text = priceText
                }
            }
        }
    }



//    override fun onDestroy() {
//        super.onDestroy()
//        firebaseAnalytics = null
//    }

}