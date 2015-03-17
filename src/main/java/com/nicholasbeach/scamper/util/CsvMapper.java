package com.nicholasbeach.scamper.util;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.nicholasbeach.scamper.domain.Transaction;

public class CsvMapper {

	private static final Logger logger = LoggerFactory.getLogger(CsvMapper.class);
	
	public List<Transaction> mapToTransactions(String csv) {
			
		
		//Read the CSV values into an array
	    CSVReader reader = new CSVReader(new StringReader(csv));
	    List<String[]> csvRows;
		try {
			csvRows = reader.readAll();
			reader.close();
			
		} catch (Exception exception) {
			logger.error("An error occured while parsing the CSV data. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
	    
		
		List<Transaction> transactions = new ArrayList<Transaction>();
	
		
		String[] header = csvRows.get(0);
		csvRows.remove(0);
		
		//Checking if Huntington CSV format
	    if(header[0].equals("Date")) {
	    	
		    for (String[] row : csvRows) {
		    	transactions.add(mapHuntingtonTransactionCsv(row));
		    	
			}
		    
	    }
	    
		return transactions;
	}
	
	
	private Transaction mapHuntingtonTransactionCsv(String[] row)
    {
		DateFormat dateFormat = new SimpleDateFormat("M/d/y");
    	Date date;
		try {
			date = dateFormat.parse(row[0]);

		} catch (Exception exception) {
			logger.error("An error occured while parsing a date. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
		
		BigDecimal amount = new BigDecimal(row[4].replace(",", ""));
    	String description = WordUtils.capitalizeFully(row[3]);
    	
		return new Transaction(date, description, amount);
    }


}
