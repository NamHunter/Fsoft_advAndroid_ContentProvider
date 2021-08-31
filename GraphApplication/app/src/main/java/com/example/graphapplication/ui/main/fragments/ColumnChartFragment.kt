package com.example.graphapplication.ui.main.fragments

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import com.example.graphapplication.R
import com.example.graphapplication.base.BaseFragment
import com.example.graphapplication.data.model.ColumnChart
import com.example.graphapplication.databinding.FragmentColumnChartBinding
import com.example.graphapplication.viewmodel.MainViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColumnChartFragment : BaseFragment<FragmentColumnChartBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun handleTasks() {
        firstSetup()

        initView()
        initObserve()
        initListener()
    }

    private fun initListener() {
        initSeekBarListener(binding.seekBar1)
        initSeekBarListener(binding.seekBar2)
        initSeekBarListener(binding.seekBar3)
        initSeekBarListener(binding.seekBar4)
        initSeekBarListener(binding.seekBar5)
    }

    private fun initSeekBarListener(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar) {
                    binding.seekBar1 -> {
                        viewModel.mColumnChart.value?.value_1 = progress.toDouble()
                    }
                    binding.seekBar2 -> {
                        viewModel.mColumnChart.value?.value_2 = progress.toDouble()
                    }
                    binding.seekBar3 -> {
                        viewModel.mColumnChart.value?.value_3 = progress.toDouble()
                    }
                    binding.seekBar4 -> {
                        viewModel.mColumnChart.value?.value_4 = progress.toDouble()
                    }
                    binding.seekBar5 -> {
                        viewModel.mColumnChart.value?.value_5 = progress.toDouble()
                    }
                }
                viewModel.updateValuesColumnChart()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun initObserve() {
        viewModel.mColumnChart.observe(viewLifecycleOwner, {
            if (it == null) {
                val columnChart = ColumnChart()
                viewModel.insertValuesColumnChart(columnChart)
                updateBuildChart(columnChart)
                updateSeekBar(columnChart)
            } else {
                updateBuildChart(it)
                updateSeekBar(it)
            }
        })
    }

    private fun updateSeekBar(columnChart: ColumnChart) {
        binding.seekBar1.progress = columnChart.value_1.toInt()
        binding.seekBar2.progress = columnChart.value_2.toInt()
        binding.seekBar3.progress = columnChart.value_3.toInt()
        binding.seekBar4.progress = columnChart.value_4.toInt()
        binding.seekBar5.progress = columnChart.value_5.toInt()
    }

    private fun updateBuildChart(columnChart: ColumnChart) {
        Log.e("TAG", "updateBuildChart: update")
        val dataEntries = arrayListOf(
            BarEntry(0f, columnChart.value_1.toFloat()),
            BarEntry(1f, columnChart.value_2.toFloat()),
            BarEntry(2f, columnChart.value_3.toFloat()),
            BarEntry(3f, columnChart.value_4.toFloat()),
            BarEntry(4f, columnChart.value_5.toFloat())
        )

        val dataSet = BarDataSet(dataEntries, getString(R.string.column_chart_view)).apply {
            setColors(*ColorTemplate.JOYFUL_COLORS)
            setValueTextColors(listOf(Color.GREEN))
            valueTextSize = 12f
        }

        val dataColumn = BarData(dataSet)

        binding.columnChart.apply {
            data = dataColumn
            invalidate()
        }
    }

    private fun initView() {
        binding.imageView1.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[0])
        binding.imageView2.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[1])
        binding.imageView3.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[2])
        binding.imageView4.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[3])
        binding.imageView5.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[4])

        val labels = listOf("Item1", "Item2", "Item3", "Item4", "Item5")
        binding.columnChart.apply {
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            description = Description().apply {
                text = ""
            }
            animateY(100)
            animateX(100)
            axisRight.isEnabled = false
            axisLeft.apply {
                axisMinimum = 0f
                axisMaximum = 100f
            }
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawAxisLine(false)
                setDrawGridLines(false)
            }
            setFitBars(true)
        }
    }

    private fun firstSetup() {
        viewModel.fetchValuesColumnChart()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentColumnChartBinding = FragmentColumnChartBinding.inflate(inflater, container, false)
}