import Streams.StreamAnalysis;

import java.util.*;

/*

 */
public class Tasks {
    /**
     * @param args
     */
    public static void main(String[] args) {
        StreamAnalysis stream = new StreamAnalysis();
        stream.extractData("/DataSource.csv");
        System.out.println("Top 10 employees with highest salary: \n" + stream.getEmployeesMap());
        System.out.println("Departments in the company: " + stream.getDepartments().size());
        System.out.println("Average salary per department: " + stream.getAverageSalaryByDepartment());
        System.out.println("Three most unfair departments: ");
        printList(stream.getUnfairDepartment());
        System.out.println("Job title attracts mostly male: \n"+ stream.getJobs());
    }

    /**
     * @param list
     */
    private static void printList(List list) {
        if (list != null) {
            Iterator<?> iterator = list.iterator();
            while (iterator.hasNext())
                System.out.print(iterator.next() + " ");
        }
    }

}