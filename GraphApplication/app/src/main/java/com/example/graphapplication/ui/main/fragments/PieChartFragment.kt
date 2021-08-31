package com.example.graphapplication.ui.main.fragments

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import com.example.graphapplication.R
import com.example.graphapplication.base.BaseFragment
import com.example.graphapplication.data.model.PieChart
import com.example.graphapplication.databinding.FragmentPieChartBinding
import com.example.graphapplication.viewmodel.MainViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PieChartFragment : BaseFragment<FragmentPieChartBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun handleTasks() {
        firstSetup()

        initView()
        initObserve()
        initListener()
    }

    private fun initView() {
        binding.imageView1.setBackgroundColor(ColorTemplate.VORDIPLOM_COLORS[0])
        binding.imageView2.setBackgroundColor(ColorTemplate.VORDIPLOM_COLORS[1])
        binding.imageView3.setBackgroundColor(ColorTemplate.VORDIPLOM_COLORS[2])
        binding.imageView4.setBackgroundColor(ColorTemplate.VORDIPLOM_COLORS[3])

        binding.pieChart.apply {
            isDrawHoleEnabled = false
            setUsePercentValues(true)
            description = Description().apply {
                text = getString(R.string.pie_chart_view)
            }
            setHoleColor(Color.WHITE)
            holeRadius = 58f
            transparentCircleRadius = 61f
        }
    }

    private fun firstSetup() {
        viewModel.fetchValuesPieChart()
    }

    private fun initListener() {
        initSeekBarListener(binding.seekBar1)
        initSeekBarListener(binding.seekBar2)
        initSeekBarListener(binding.seekBar3)
        initSeekBarListener(binding.seekBar4)
    }

    private fun initSeekBarListener(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar) {
                    binding.seekBar1 -> {
                        viewModel.mPieChart.value?.value_1 = progress.toDouble()
                    }
                    binding.seekBar2 -> {
                        viewModel.mPieChart.value?.value_2 = progress.toDouble()
                    }
                    binding.seekBar3 -> {
                        viewModel.mPieChart.value?.value_3 = progress.toDouble()
                    }
                    binding.seekBar4 -> {
                        viewModel.mPieChart.value?.value_4 = progress.toDouble()
                    }
                }
                viewModel.updateValuesPieChart()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun initObserve() {
        viewModel.mPieChart.observe(viewLifecycleOwner, {
            if (it == null) {
                val pieChart = PieChart()
                viewModel.insertValuesPieChart(pieChart)
                updateBuildChart(pieChart)
                updateSeekBar(pieChart)
            } else {
                updateBuildChart(it)
                updateSeekBar(it)
            }
        })
    }

    private fun updateSeekBar(pieChart: PieChart) {
        binding.seekBar1.progress = pieChart.value_1.toInt()
        binding.seekBar2.progress = pieChart.value_2.toInt()
        binding.seekBar3.progress = pieChart.value_3.toInt()
        binding.seekBar4.progress = pieChart.value_4.toInt()
    }

    private fun updateBuildChart(pieChart: PieChart) {
        Log.e("TAG", "updateBuildChart: update")
        val dataEntries = arrayListOf(
            PieEntry(pieChart.value_1.toFloat(), getString(R.string.item_1), 0),
            PieEntry(pieChart.value_2.toFloat(), getString(R.string.item_2), 1),
            PieEntry(pieChart.value_3.toFloat(), getString(R.string.item_3), 2),
            PieEntry(pieChart.value_4.toFloat(), getString(R.string.item_4), 3)
        )

        val dataSet = PieDataSet(dataEntries, getString(R.string.column_chart_view)).apply {
            setColors(*ColorTemplate.VORDIPLOM_COLORS)
            sliceSpace = 3f
        }

        val dataPie = PieData(dataSet).apply {
            setValueTextSize(16f)
            setValueTextColor(Color.BLUE)
            setValueFormatter(PercentFormatter())
        }

        binding.pieChart.apply {
            data = dataPie
            invalidate()
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentPieChartBinding = FragmentPieChartBinding.inflate(layoutInflater, container, false)
}