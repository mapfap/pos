package com.refresh.pos.ui.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;


import android.content.Context;
import android.graphics.Color;

public class Chart extends AbstractChart {

	public GraphicalView execute(Context context) {
		String[] titles = new String[] { "Sale", "Sale Growth" };
		List<Date[]> dates = new ArrayList<Date[]>();
		List<double[]> values = new ArrayList<double[]>();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			dates.add(new Date[12]);
			dates.get(i)[0] = new Date(108, 9, 1);
			dates.get(i)[1] = new Date(108, 9, 8);
			dates.get(i)[2] = new Date(108, 9, 15);
			dates.get(i)[3] = new Date(108, 9, 22);
			dates.get(i)[4] = new Date(108, 9, 29);
			dates.get(i)[5] = new Date(108, 10, 5);
			dates.get(i)[6] = new Date(108, 10, 12);
			dates.get(i)[7] = new Date(108, 10, 19);
			dates.get(i)[8] = new Date(108, 10, 26);
			dates.get(i)[9] = new Date(108, 11, 3);
			dates.get(i)[10] = new Date(108, 11, 10);
			dates.get(i)[11] = new Date(108, 11, 17);
		}
		values.add(new double[] { 142, 123, 142, 152, 149, 122, 110, 120, 125,
				155, 146, 150 });
		values.add(new double[] { 102, 90, 112, 105, 125, 112, 125, 112, 105,
				115, 116, 135 });
		length = values.get(0).length;
		int[] colors = new int[] { Color.BLUE, Color.GREEN };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "Sale Growth Rate", "Date", "Sale",
				dates.get(0)[0].getTime(), dates.get(0)[11].getTime(), 50, 190,
				Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(0);
		renderer.setYLabels(10);
		renderer.addYTextLabel(100, "test");
		length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true);
		}
		renderer.setXRoundedLabels(false);
		return ChartFactory.getTimeChartView(context, buildDateDataset(titles, dates, values), renderer, "dd/MM/yyyy");
				
	}
}
