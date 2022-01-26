package com.example.feature_details_screen.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.FragmentDetailsBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.navigate
import kotlin.math.abs

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val vm: DetailsViewModel by viewModel()
    private val binding: FragmentDetailsBinding by viewBinding()
    private var _imagesAdapter: ImagesCarouselAdapter? = null
    private val imagesAdapter: ImagesCarouselAdapter
        get() = _imagesAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadPhoneDetails()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDetailsObserve()
        initViewPager()

    }

    private fun initViewPager() {
        _imagesAdapter = ImagesCarouselAdapter()

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

    private fun loadDetailsObserve() {
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

                    val priceText =
                        it.price.toString()
                            .reversed().chunked(3)
                            .joinToString(",")
                            .reversed()
                    priceTextView.text = priceText

                    addToFavouritesButton.setOnClickListener { button ->
                        button.isSelected = !button.isSelected
                    }

                    myCardButton.setOnClickListener {
                        navigate(
                            NavCommand(
                                NavCommands.DeepLink(
                                    Uri.parse("https://mysite.com/cart"),
                                    false,
                                    true
                                )
                            )
                        )
                    }

                    backButton.setOnClickListener {
                        findNavController().popBackStack()
                    }

                }
            }
        }
    }

    private fun loadPhoneDetails() {
        vm.loadDetails((arguments?.get("deviceId") as String))
    }

    override fun onDestroy() {
        super.onDestroy()
        _imagesAdapter = null
    }

}