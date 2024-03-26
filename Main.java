package fop_assignment;


import java.util.Scanner;

/**
 * 
 * @author Amaan Geelani Syed S2191704
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to our data collection program.");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name / path : ");
        Data logData = new Data(sc.nextLine());
        logData.storeData();
        System.out.println("Extracting data...");
        logData.getJobTimes();
        System.out.println("Data Extracted successfully.");
        
        OUTER:
        while(true){
            System.out.println("+---------------------------------------------------------+\n"+
            "Data Available:  \n"+
            "(1) : Number of jobs started\n"+
            "(2) : Number of jobs finished\n"+
            "(3) : Number of jobs by partition\n"+
            "(4) : Errors\n"+
            "(5) : Average time of jobs\n"+
            "(6) : Number of jobs killed\n"+
            "(7) : Number of jobs whose time limit was exhausted\n"+
            "(8) : Number of job clean ups\n"+
            "(9) : Number of jobs requeued\n"+
            "(0) : Exit\n"+
            "+---------------------------------------------------------+");
            System.out.print("Enter: ");
            int job = sc.nextInt();
            int subJob;
            switch (job) {
                case 1:
                    //Jobs Started
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs Started : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
         
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    
                    switch (subJob) {
                        case 1:
                        {
                            int[] graphData = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData[i] = logData.getJobsStarted(months[i],months[i+1]);
                            }
                            GUIPanel g1 = new GUIPanel("Total Jobs Started Each Month",graphData,monthNames);
                            String tableData[][] = new String[months.length][2];
                            tableData[7][0] = "TOTAL";
                            tableData[7][1] = ""+logData.getJobsStarted(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData[i][0] = monthNames[i];
                                tableData[i][1] = ""+graphData[i];
                            }
                            String[] columns = {"Month","Jobs Started"};
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData[i] = logData.getJobsStarted(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g1 = new GUIPanel("Total Jobs Started Each Week",graphData,weekCount);
                            String tableData[][] = new String[weeks.length][2];
                            tableData[29][0] = "TOTAL";
                            tableData[29][1] = ""+logData.getJobsStarted(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData[i][0] = weekCount[i];
                                tableData[i][1] = ""+graphData[i];
                            }
                            String[] columns = {"Week","Jobs Started"};
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of jobs started in the given time: "+logData.getJobsStarted(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0:
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                break;


                case 2:
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs Completed : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                        case 1: 
                        {
                            int[] graphData = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData[i] = logData.getJobsFinished(months[i],months[i+1]);
                            }
                            GUIPanel g1 = new GUIPanel("Total Jobs Completed Each Month",graphData,monthNames);
                            String tableData[][] = new String[months.length][2];
                            tableData[7][0] = "TOTAL";
                            tableData[7][1] = ""+logData.getJobsFinished(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData[i][0] = monthNames[i];
                                tableData[i][1] = ""+graphData[i];
                            }
                            String[] columns = {"Month","Jobs Completed"};
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData[i] = logData.getJobsFinished(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g1 = new GUIPanel("Total Jobs Completed Each Week",graphData,weekCount);
                            String tableData[][] = new String[weeks.length][2];
                            tableData[29][0] = "TOTAL";
                            tableData[29][1] = ""+logData.getJobsFinished(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData[i][0] = weekCount[i];
                                tableData[i][1] = ""+graphData[i];
                            }
                            String[] columns = {"Week","Jobs Completed"};
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of jobs finished in the given time: "+logData.getJobsFinished(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0:
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                break;


                case 3:
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs by Partitions :\n"+
                                        "(1) : All Data\n"+
                                        "(2) : GPU V100s\n"+
                                        "(3) : GPU K10\n"+
                                        "(4) : GPU Titan\n"+
                                        "(5) : GPU K40C\n"+
                                        "(6) : CPU Opteron\n"+
                                        "(7) : CPU EPYC\n"+
                                        "(8) : Custom\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                    case 1:
                        int[] graphData = new int[6];
                        String[] partitions = {"cpu-epyc","cpu-opteron","gpu-v100s","gpu-titan","gpu-k10","gpu-k40c"};
                        for(int i = 0; i<6; i++){
                            graphData[i] = logData.getJobsByPartitions(months[0],months[29],partitions[i]);
                        }
                        GUIPanel g1 = new GUIPanel("Total Jobs By Each Partition",graphData,partitions);
                        String tableData[][] = new String[6][2];
                        for(int i = 0; i < 6; i++){
                            tableData[i][0] = partitions[i];
                            tableData[i][1] = ""+graphData[i];
                        }
                        String[] columns = {"Partition","Jobs Started"};
                        g1.setTable(tableData, columns);
                        g1.makeGraph(g1);
                        break;
                    case 2:
                        graphJobsByPartition("gpu-v100s",logData);
                        break;
                    case 3:
                        graphJobsByPartition("gpu-k10",logData);
                        break;
                    case 4:
                        graphJobsByPartition("gpu-titan",logData);
                        break;
                    case 5:
                        graphJobsByPartition("gpu-k40c",logData);
                        break;
                    case 6:
                        graphJobsByPartition("cpu-opteron",logData);
                        break;
                    case 7:
                        graphJobsByPartition("cpu-epyc",logData);
                        break;
                    case 8:
                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                        sc.nextLine();
                        String startTime = sc.nextLine();
                        System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                        String endTime = sc.nextLine();
                        System.out.print("+---------------------------------------------------------+\n"+
                                        "Select partition:\n"+
                                        "(1) : GPU V100s\n"+
                                        "(2) : GPU K10\n"+
                                        "(3) : GPU Titan\n"+
                                        "(4) : GPU K40C\n"+
                                        "(5) : CPU Opteron\n"+
                                        "(6) : CPU EPYC\n"+
                                        "+---------------------------------------------------------+\n");
                        System.out.print("Enter: ");
                        String partition;
                        switch(sc.nextInt()){
                            case 1:
                                partition = "gpu-v100s";
                                break;
                            case 2:
                                partition = "gpu-k10";
                                break;
                            case 3:
                                partition = "gpu-titan";
                                break;
                            case 4:
                                partition = "gpu-k40c";
                                break;
                            case 5:
                                partition = "cpu-opteron";
                                break;
                            case 6:
                                partition = "cpu-epyc";
                                break;
                            default: 
                                partition = "";
                                System.out.println("Invalid Number");
                        }
                        System.out.println("Number of jobs finished in the given time: "+logData.getJobsByPartitions(startTime,endTime,partition));
                        System.out.println("+---------------------------------------------------------+");
                        break;
                    case 0:
                        break OUTER;
                    default:
                        System.out.println("Invalid number");
                        break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;


                case 4:
                    //Errors
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data errors : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                switch (subJob) {
                    case 1:
                    {
                        int[] graphData = new int[months.length-1];
                        for(int i = 0; i<months.length-1; i++){
                            graphData[i] = logData.errors(months[i],months[i+1]);
                        }
                        GUIPanel g1 = new GUIPanel("Errors",graphData,monthNames);
                        String tableData[][] = logData.errorList("2022-06-01 00:00:00.000", "2022-12-21 00:00:00.000");
                        String[] columns = {"Time of Error", "User ID","Error Message"};
                        g1.setTable(tableData, columns);
                        g1.setTableWidth(450);
                        g1.makeGraph(g1);
                        break;
                    }
                    case 2:
                    {
                        int[] graphData = new int[weeks.length-1];
                        for(int i = 0; i<weeks.length-1; i++){
                            graphData[i] = logData.errors(weeks[i],weeks[i+1]);
                        }
                        GUIPanel g1 = new GUIPanel("Errors",graphData,weekCount);
                        String tableData[][] = logData.errorList("2022-06-01 00:00:00.000", "2022-12-21 00:00:00.000");
                        String[] columns = {"Time of Error", "User ID","Error Message"};
                        g1.setTable(tableData, columns);
                        g1.setTableWidth(450);
                        g1.makeGraph(g1);
                        break;
                    }
                    case 3:
                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                        sc.nextLine();
                        String startTime = sc.nextLine();
                        System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                        String endTime = sc.nextLine();
                        System.out.println("Number of errors generated in the given time: "+logData.errors(startTime,endTime)); 
                        System.out.println("+---------------------------------------------------------+");
                        break;
                    case 0:
                        break OUTER;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;


                case 5:
                    //Average
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Average Job Time : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch(subJob){
                        case 1:{
                            int[] graphData = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData[i] = (int)logData.averageJobTime(months[i],months[i+1])/3600;
                            }
                            GUIPanel g1 = new GUIPanel("Average time of jobs, each month, in hours.",graphData,monthNames);
                            String tableData[][] = new String[months.length][2];
                            tableData[0][0] = "Total Average";
                            double totalAvg = logData.averageJobTime(months[0],months[7]);
                            tableData[0][1] = String.format("%d hours, %d minutes and %.3f seconds.", (int)totalAvg/3600, (int)(totalAvg/60)%60, totalAvg%60);
                            for(int i = 1; i < months.length; i++){
                                tableData[i][0] = monthNames[i-1];
                                double seconds = logData.averageJobTime(months[i-1],months[i]);
                                if(Double.isNaN(seconds)){
                                    tableData[i][1] = "Number jobs completed this month.";
                                    continue;
                                }
                                tableData[i][1] = String.format("%d hours, %d minutes and %.3f seconds.", (int)seconds/3600, (int)(seconds/60)%60, seconds%60);
                            }
                            String[] columns = {"Month","Average Job Time"};
                            g1.setTableWidth(450);
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData[i] = (int)logData.averageJobTime(weeks[i],weeks[i+1])/3600;
                            }
                            GUIPanel g1 = new GUIPanel("Average time of jobs, each week, in hours.",graphData,weekCount);
                            String tableData[][] = new String[weeks.length][2];
                            tableData[0][0] = "Total Average";
                            double totalAvg = logData.averageJobTime(weeks[0],weeks[29]);
                            tableData[0][1] = String.format("%d hours, %d minutes and %.3f seconds.", (int)totalAvg/3600, (int)(totalAvg/60)%60, totalAvg%60);
                            for(int i = 1; i < weeks.length; i++){
                                tableData[i][0] = weekCount[i-1];
                                double seconds = logData.averageJobTime(weeks[i-1],weeks[i]);
                                if(Double.isNaN(seconds)){
                                    tableData[i][1] = "Number jobs completed this week.";
                                    continue;
                                }
                                tableData[i][1] = String.format("%d hours, %d minutes and %.3f seconds.", (int)seconds/3600, (int)(seconds/60)%60, seconds%60);
                            }
                            String[] columns = {"Week","Average Job Time"};
                            g1.setTableWidth(450);
                            g1.setTable(tableData, columns);
                            g1.makeGraph(g1);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            double avg = logData.averageJobTime(startTime,endTime);
                            String average = String.format("%d hours, %d minutes and %.3f seconds.", (int)avg/3600, (int)(avg/60)%60, avg%60);
                            System.out.println("The Average time for Jobs in the given time period : "+average); 
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0:
                            break OUTER;
                        default:
                            System.out.println("Invalid Number");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;

                case 6:
                    // jobsKilled
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs Killed : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                        case 1:
                        {
                            int[] graphData3 = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData3[i] = logData.jobsKilled(months[i],months[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Total Jobs Killed Each Month",graphData3,monthNames);
                            String tableData[][] = new String[months.length][2];
                            tableData[7][0] = "TOTAL";
                            tableData[7][1] = ""+logData.jobsKilled(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData[i][0] = monthNames[i];
                                tableData[i][1] = ""+graphData3[i];
                            }
                            String[] columns = {"Month","Jobs Killed"};
                            g3.setTable(tableData, columns);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData3 = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData3[i] = logData.jobsKilled(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Total Jobs Killed Each Week",graphData3,weekCount);
                            String tableData3[][] = new String[weeks.length][2];
                            tableData3[29][0] = "TOTAL";
                            tableData3[29][1] = ""+logData.jobsKilled(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData3[i][0] = weekCount[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Week","Jobs Killed"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of jobs Killed in the given time: "+logData.jobsKilled(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0: 
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;
                
                case 7:
                    //time limit exhausted
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs whose time limit was exhausted : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                        case 1:
                        {
                            int[] graphData3 = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData3[i] = logData.timeLimitExhausted(months[i],months[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Jobs whose time limit was exhausted",graphData3,monthNames);
                            String tableData3[][] = new String[months.length][2];
                            tableData3[7][0] = "TOTAL";
                            tableData3[7][1] = ""+logData.timeLimitExhausted(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData3[i][0] = monthNames[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Month","Number. of jobs"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData3 = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData3[i] = logData.timeLimitExhausted(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Jobs whose time limit was exhausted",graphData3,weekCount);
                            String tableData3[][] = new String[weeks.length][2];
                            tableData3[29][0] = "TOTAL";
                            tableData3[29][1] = ""+logData.timeLimitExhausted(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData3[i][0] = weekCount[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Week","Number. of Jobs"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of Jobs whose time limit was exhausted: "+logData.timeLimitExhausted(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0: 
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;
                    
                case 8:
                    //jobs cleaned up
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs Cleaned up : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                        case 1:
                        {
                            int[] graphData3 = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData3[i] = logData.cleanUpsCompleted(months[i],months[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Number of Job clean ups done",graphData3,monthNames);
                            String tableData3[][] = new String[months.length][2];
                            tableData3[7][0] = "TOTAL";
                            tableData3[7][1] = ""+logData.cleanUpsCompleted(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData3[i][0] = monthNames[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Month","Number of Jobs"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData3 = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData3[i] = logData.cleanUpsCompleted(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Number of Job clean ups done",graphData3,weekCount);
                            String tableData3[][] = new String[weeks.length][2];
                            tableData3[29][0] = "TOTAL";
                            tableData3[29][1] = ""+logData.cleanUpsCompleted(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData3[i][0] = weekCount[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Week","Number of Jobs"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of job clean ups done : "+logData.cleanUpsCompleted(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0: 
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;
               
                case 9:
                    //jobs requeued
                    System.out.println("+---------------------------------------------------------+\n"+
                                        "Data for Jobs requeued : \n"+
                                        "(1) : Whole Data [graph & table] - divided by months\n"+
                                        "(2) : Whole Data [graph & table] - divided by weeks\n"+
                                        "(3) : Custom time period\n"+
                                        "(0) : Exit\n"+
                                        "+---------------------------------------------------------+");
                    System.out.print("Enter: ");
                    subJob = sc.nextInt();
                    switch (subJob) {
                        case 1:
                        {
                            int[] graphData3 = new int[months.length-1];
                            for(int i = 0; i<months.length-1; i++){
                                graphData3[i] = logData.jobsRequeued(months[i],months[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Total Jobs Requeued Each Week",graphData3,monthNames);
                            String tableData3[][] = new String[months.length][2];
                            tableData3[7][0] = "TOTAL";
                            tableData3[7][1] = ""+logData.jobsRequeued(months[0],months[7]);
                            for(int i = 0; i < months.length-1; i++){
                                tableData3[i][0] = monthNames[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Month","Jobs Requeued"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 2:
                        {
                            int[] graphData3 = new int[weeks.length-1];
                            for(int i = 0; i<weeks.length-1; i++){
                                graphData3[i] = logData.jobsRequeued(weeks[i],weeks[i+1]);
                            }
                            GUIPanel g3 = new GUIPanel("Total Jobs Requeued Each Week",graphData3,weekCount);
                            String tableData3[][] = new String[weeks.length][2];
                            tableData3[29][0] = "TOTAL";
                            tableData3[29][1] = ""+logData.jobsRequeued(weeks[0],weeks[29]);
                            for(int i = 0; i < weeks.length-1; i++){
                                tableData3[i][0] = weekCount[i];
                                tableData3[i][1] = ""+graphData3[i];
                            }
                            String[] columns3 = {"Week","Jobs Requeued"};
                            g3.setTable(tableData3, columns3);
                            g3.makeGraph(g3);
                            break;
                        }
                        case 3:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("Enter Start Time [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            sc.nextLine();
                            String startTime = sc.nextLine();
                            System.out.print("Enter End TIme [yyyy-MM-dd HH:mm:ss.SSS] : ");
                            String endTime = sc.nextLine();
                            System.out.println("Number of Jobs Requeued in the given time: "+logData.jobsRequeued(startTime,endTime));
                            System.out.println("+---------------------------------------------------------+");
                            break;
                        case 0: 
                            break OUTER;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    System.out.print("Enter (0) to continue: ");
                    sc.nextInt();
                    break;
                    
                case 0: 
                    System.out.println("Exited Successfully");
                    break OUTER;
                    
                default:
                    System.out.println("Invalid number");
                    break;
            }
        }
    }
    
    public static void graphJobsByPartition(String partition, Data logData){
        int[] graphData = new int[months.length-1];
        for(int i = 0; i<months.length-1; i++){
            graphData[i] = logData.getJobsByPartitions(months[i],months[i+1],partition);
        }
        GUIPanel g1 = new GUIPanel("Total Jobs by "+partition,graphData,monthNames);
        String tableData[][] = new String[months.length][2];
        tableData[7][0] = "TOTAL";
        tableData[7][1] = ""+logData.getJobsByPartitions(months[0],months[7],partition);
        for(int i = 0; i < months.length-1; i++){
             tableData[i][0] = monthNames[i];
             tableData[i][1] = ""+graphData[i];
        }
        String[] columns = {"Month","Jobs Completed"};
        g1.setTable(tableData, columns);
        g1.makeGraph(g1);
    }
    public static String[] monthNames = {"June","July","August","September","October","Numbervember","December"};
    public static String[] months =     {"2022-06-01 00:00:00.000","2022-07-01 00:00:00.000","2022-08-01 00:00:00.000","2022-09-01 00:00:00.000",
                                         "2022-10-01 00:00:00.000","2022-11-01 00:00:00.000","2022-12-01 00:00:00.000","2022-12-31 23:59:59.999"};
    public static String[] weeks =      {"2022-06-01 00:00:00.000","2022-06-08 00:00:00.000","2022-06-15 00:00:00.000","2022-06-22 00:00:00.000","2022-06-29 00:00:00.000",
                                         "2022-07-06 00:00:00.000","2022-07-13 00:00:00.000","2022-07-20 00:00:00.000","2022-07-27 00:00:00.000","2022-08-03 00:00:00.000",
                                         "2022-08-10 00:00:00.000","2022-08-17 00:00:00.000","2022-08-24 00:00:00.000","2022-08-31 00:00:00.000","2022-09-07 00:00:00.000",
                                         "2022-09-14 00:00:00.000","2022-09-21 00:00:00.000","2022-09-28 00:00:00.000","2022-10-05 00:00:00.000","2022-10-12 00:00:00.000",
                                         "2022-10-19 00:00:00.000","2022-10-26 00:00:00.000","2022-11-02 00:00:00.000","2022-11-09 00:00:00.000","2022-11-16 00:00:00.000",
                                         "2022-11-23 00:00:00.000","2022-12-30 00:00:00.000","2022-12-07 00:00:00.000","2022-12-14 00:00:00.000","2022-12-21 00:00:00.000"};
    public static String[] weekCount =  {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                                         "16", "17", "18", "19", "20", "21", "22", "23","24", "25", "26", "27", "28", "29", "30"};
}
