package com.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;

public class CsvUtility {
	public void downloadCsv(final String fileName, final String[] headers, final List<List<Object>> data,
			final HttpServletResponse response) throws IOException {
		System.out.println("Generating CSV file " + fileName);
		response.setDateHeader("Expires", 0); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".csv");
		response.setCharacterEncoding("UTF-8");
		final CSVWriter writer = new CSVWriter(response.getWriter());
		writer.writeNext(headers);
		for (final List<Object> row : data) {
			final String[] array = new String[row.size()];
			int	index = 0;
			for (final Object value : row) {
				array[index] = null == value ? "" : String.valueOf(value);
				index++;
			}
			writer.writeNext(array);
		}
		writer.close();
		System.out.println("Completed generation of CSV file " + fileName);
	}
}
