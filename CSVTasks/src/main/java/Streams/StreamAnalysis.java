package Streams;

import javafx.collections.transformation.SortedList;
import lombok.Getter;
import models.Entity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 *
 */
public class StreamAnalysis {

    //
    @Getter
    private TreeMap<Double, Entity> employeesMap;
    //
    @Getter
    private ArrayList<String> departments;
    //
    @Getter
    private Hashtable<String, Double> averageSalaryByDepartment;
    //
    @Getter
    private SortedList<String> unfairDepartment;
    //
    @Getter
    private Hashtable<String, Integer> jobs;

    /**
     *
     */
    public StreamAnalysis() {
        departments = new ArrayList();
        employeesMap = new TreeMap<>();
        averageSalaryByDepartment = new Hashtable<>();
        jobs = new Hashtable<String, Integer>();
    }

    /**
     * @param fileName
     */
    public void extractData(String fileName) {
        try {
            Reader fileReader = new FileReader(getClass().getResource(fileName).getFile());
            //skip the header
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().parse(fileReader);
            Entity entity;
            for (CSVRecord record : csvParser) {
                entity = new Entity();
                entity.setId(record.get(0));
                entity.setName(record.get(1));
                entity.setGender(record.get(2));
                entity.setDepartment(record.get(3));
                entity.setJobTitle(record.get(4));
                entity.setSalary(new Double(record.get(5)));
                //
                updateTopEmployees(entity);
                //
                updateDepartmentsCount(entity);
                //
                updateAverageSalaryByDepartment(entity);
                //
                findUnfairDepartment(entity);
                //
                updateJobs(entity);
           //     System.out.println(entity.toString());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * @param entity
     * @return
     */
    private int counter = 0;
    private TreeMap<Double, Entity> updateTopEmployees(Entity entity) {
        if(counter < 10) {
            employeesMap.put(entity.getSalary(), entity);
            counter++;
        }else {
            if( entity.getSalary() > employeesMap.lastKey().doubleValue() ){
                employeesMap.remove(employeesMap.lastKey());
                employeesMap.put(entity.getSalary(), entity);
            }
        }
        return employeesMap;
    }

    /**
     * @param entity
     */
    private void updateDepartmentsCount(Entity entity) {
        if (!departments.contains(entity.getDepartment())) {
            departments.add(entity.getDepartment());
        }
    }

    /**
     * @param entity
     */
    private void updateAverageSalaryByDepartment(Entity entity) {
        if (averageSalaryByDepartment.containsKey(entity.getDepartment())) {
            double updatedAverageSalary = (averageSalaryByDepartment.get(entity.getDepartment()).doubleValue() + entity.getSalary()) / 2;
            averageSalaryByDepartment.replace(entity.getDepartment(), updatedAverageSalary);
        } else {
            averageSalaryByDepartment.put(entity.getDepartment(), entity.getSalary());
        }
    }

    /**
     * @param entity
     * @return
     */
    private SortedList<String> findUnfairDepartment(Entity entity) {
        return unfairDepartment;
    }

    /**
     * @param entity
     * @return
     */
    private void updateJobs(Entity entity) {
        if ("Male".equals(entity.getGender())) {
            if (jobs.containsKey(entity.getJobTitle())) {
                jobs.replace(entity.getJobTitle(), jobs.get(entity.getJobTitle()) + 1);
            } else {
                jobs.put(entity.getJobTitle(), 1);
            }
        }

    }
}