package com.nicholasbeach.scamper.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nicholasbeach.scamper.persistence.TransactionMapper;
import org.apache.commons.lang.WordUtils;


import au.com.bytecode.opencsv.CSVReader;

import com.nicholasbeach.scamper.domain.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;

@Service
public class TransactionService extends AbstractResourceService<Transaction> {

    @Inject
    private TransactionMapper transactionMapper;

    public void importCsvTransactions(MultipartFile file) {


        String csvText;
        try {
            csvText = new String(file.getBytes(),"UTF-8");

            TransactionService mapper = new TransactionService();


            //Read the CSV values into an array
            CSVReader reader = new CSVReader(new StringReader(csvText));
            List<String[]> csvRows;
            try {
                csvRows = reader.readAll();
                reader.close();

            } catch (Exception exception) {
                log.error("An error occured while parsing the CSV data. Error = {}", exception.getMessage());
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

            for (Transaction transaction : transactions) {
                transactionMapper.create(transaction);
            }

        } catch (UnsupportedEncodingException e) {
            log.error("CSV mapping failed. Unsupported encoding");

        } catch (IOException e) {
            log.error("CSV mapping failed. IO exception");

        }

    }

	
	private Transaction mapHuntingtonTransactionCsv(String[] row)
    {
		DateFormat dateFormat = new SimpleDateFormat("M/d/y");
    	Date date;
		try {
			date = dateFormat.parse(row[0]);

		} catch (Exception exception) {
            log.error("An error occured while parsing a date. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
		
		BigDecimal amount = new BigDecimal(row[4].replace(",", ""));
    	String description = WordUtils.capitalizeFully(row[3]);
    	
		return new Transaction(date, description, amount);
    }


    @Override
    protected Class getResourceClass() {
        return Transaction.class;
    }

    @Override
    protected TransactionMapper getMapper() {
        return transactionMapper;
    }

}
