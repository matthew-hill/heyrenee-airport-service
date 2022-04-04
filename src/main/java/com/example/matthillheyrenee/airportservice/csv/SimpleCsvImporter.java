package com.example.matthillheyrenee.airportservice.csv;

import com.example.matthillheyrenee.airportservice.model.Airport;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SimpleCsvImporter implements CsvImporter {

    private static final String AIRPORT_DATA_FILE_PATH = "src/main/resources/static/airport_data.csv";
    private static final char DEFAULT_SEPARATOR = '|';

    private static final String[] COLUMN_ORDERING = new String[] {"id", "name", "city", "country", "iataCode",
            "icaoCode", "latitude", "longitude", "altitude", "timeZone"};

    FileReader fileReader;
    CSVParser parser;
    CSVReader csvReader;
    CsvToBean csvBean;

    public void queueNewFileToParse() throws FileNotFoundException {
        queueNewFileToParse(AIRPORT_DATA_FILE_PATH);
    }

    public void queueNewFileToParse(String file) throws FileNotFoundException {
        fileReader = getFileReader(file);

        if (fileReader != null) {
            initializeCsvMapping();
        } else {
            //log error, throw csv error
        }
    }

    public List<Airport> getParsedAirportData() {
        List<Airport> airports = csvBean.parse();

        if (airports == null) {
            //log error
        }

        try {
            fileReader.close();
        } catch (IOException ex) {
            //log error on close
        }

        return airports;
    }

    private FileReader getFileReader(String file) throws FileNotFoundException {
        return new FileReader(file);
    }

    private void initializeCsvMapping() {
        parser = new CSVParserBuilder().withSeparator(DEFAULT_SEPARATOR).build();
        csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).withSkipLines(1).build();

        csvBean = new CsvToBean();
        csvBean.setMappingStrategy(getMappingStrategy());
        csvBean.setCsvReader(csvReader);
    }

    private ColumnPositionMappingStrategy getMappingStrategy() {
        ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
        mappingStrategy.setType(Airport.class);
        mappingStrategy.setColumnMapping(COLUMN_ORDERING);

        return mappingStrategy;
    }
}
