package fop_project;

/**
 *
 * @author Amaan Geelani Syed S2191704
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private String file;
    Data(String file){
        this.file = file;
    }
    public ArrayList<String[]> data = new ArrayList<>();             // each array store 1 line, and this list stores all arrays.
    public ArrayList<String[]> jobsStarted = new ArrayList<>();
    public ArrayList<String[]> jobsFinished = new ArrayList<>();
    public ArrayList<String[]> jobsFinishedSuccessfully = new ArrayList<>();
    public ArrayList<String[]> jobTimes = new ArrayList<>();

    public void storeData(){
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String lineread = "";
            while((lineread = input.readLine())!=null){
                String[] temp = lineread.split(" ");
                temp[0] = dateTimeFormatter(temp[0]);
                data.add(temp);
            }
            input.close();
            System.out.println("File has been read.");
            System.out.println("No of lines: "+data.size());
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        
        //to store jobs started.
        for(String[] line : data){
            if(line.length<3)
                continue;               // to prevent index out of bound error
            boolean correctLine = (line[1].equals("sched:") && line[2].contentEquals("Allocate")) || (line[2].contains("_start_job"));
            if(true && correctLine){
                jobsStarted.add(line);
            }
        }
        //to store jobs completed.
        for(String[] line : data){
            if(line.length<4)
                continue;               // to prevent index out of bound error
            boolean correctLine = line[1].equals("_job_complete:") && line[3].contentEquals("done");
            if(true && correctLine){
                jobsFinished.add(line);
            }
        }
        //to store jobs completed sucessfully
        for(String[] line : data){
            if(line.length<5)
                continue;               // to prevent index out of bound error
            boolean correctLine = line[1].equals("_job_complete:") && line[3].contentEquals("WEXITSTATUS") && line[4].contentEquals("0");
            if(true && correctLine){
                jobsFinishedSuccessfully.add(line);
            }
        }
    }
    
    public String dateTimeFormatter(String dateTime){
        return dateTime.substring(1,11)+" "+dateTime.substring(12,24); // converts [2022-06-01T04:05:04.581] to 2022-06-01 04:05:04.581
    }
    
    public int getJobsStarted(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : jobsStarted){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(timePeriod) 
                count++;
        }
        return count;
    }
    
    public int getJobsFinishedSuccessfully(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : jobsFinishedSuccessfully){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(timePeriod) 
                count++;
        }
        return count;
    }
    
    public int getJobsNotFinishedCorrectly(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : data){
            if(line.length<4) continue;
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            boolean condition = line[1].equals("_job_complete:") && line[3].contentEquals("WEXITSTATUS") && !line[4].contentEquals("0");
            if(timePeriod && condition) 
                count++;
        }
        return count;
    }

    public int getJobsByPartitions(String startDateTime, String endDateTime, String partition){
        int count = 0;
        for(String[] line : jobsStarted){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(line.length<7)
                continue;               // to prevent index out of bound error
            boolean correctPartition = line[6].contains(partition);
            if(timePeriod && correctPartition) 
                count++;
        }
        return count;
    }

    public int errors(String startDateTime, String endDateTime){                                             //if only number of errors needed
        int count = 0;
        for(String[] line : data){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(line.length<3)
                continue;               // to prevent index out of bound error
            boolean correctLine = line[1].equals("error:") && line[2].contentEquals("This");
            if(timePeriod && correctLine) {
                count++;
            }
        }
        return count;
    }

    public String[][] errorList(String startDateTime, String endDateTime){              //if no. of errors need as well as errors need to be stored and processed
        int count = 0;
        String[][] listErrors = new String[errors(startDateTime, endDateTime)][3];
        for(String[] line : data){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(line.length<3)
                continue;               // to prevent index out of bound error
            boolean correctLine = line[1].equals("error:") && line[2].contentEquals("This");
            if(timePeriod && correctLine) {
                listErrors[count][0] = line[0];                                  //stores the time of the error
                listErrors[count][1] = (line[5].split("\'"))[1];          //stores the user
                listErrors[count][2] = line[7]+" "+line[8]+" "+line[9]+" "+line[10]+" "+line[11]+" "+line[12]+" "+line[13];       //stores the errorcode
                count++;
            }
        }
        return listErrors;
    }
    
    public void getJobTimes() {
        for(String[] line1: jobsStarted){
            String[] temp = new String[3];
            String jobID;
            if(line1[1].equals("sched/backfill:"))
                jobID = (line1[4].split("="))[1];                   //when line starts with backfill
            else 
                jobID = (line1[3].split("="))[1];                   //when line starts with allocate
            temp[1] = jobID;
            String jobStartTime = line1[0];
            String jobFinishTime = "";
            for(String[] line2: jobsFinished){
                if(line2[2].contains(jobID)){
                    jobFinishTime = line2[0];
                }
            }
            if("".equals(jobFinishTime)){
                continue;
            }
            temp[0]=jobFinishTime;
            temp[2] = Float.toString(getTimeDifference(jobStartTime, jobFinishTime));
            jobTimes.add(temp);
        }
    }

    public double averageJobTime(String startDateTime, String endDateTime){
        double sum = 0, count = 0;
        for(String[] line: jobTimes){
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            if(timePeriod){
                sum += Double.valueOf(line[2]);
                count++;
            }
        }
        return sum/count;
    }

    public float getTimeDifference(String startTime, String endTime){ //      2022-06-08 18:30:10.196
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            return (end.getTime()-start.getTime())/(float)1000;
        } catch (ParseException e) {
            System.out.println("Could not parse Date.");
        }
        return 0;
    }

    public int jobsKilled(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : data){
            if(line.length<4) continue;
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            boolean condition = line[2].equals("REQUEST_KILL_JOB") && line[3].contains("JobId");
            if(timePeriod && condition) 
                count++;
        }
        return count;
    }
    
    public int timeLimitExhausted(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : data){
            if(line.length<3) continue;
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            boolean condition = line[1].equals("Time") && line[2].equals("limit");
            if(timePeriod && condition) 
                count++;
        }
        return count;
    }
    
    public int jobsRequeued(String startDateTime, String endDateTime){
        int count = 0;
        for(String[] line : data){
            if(line.length<3) continue;
            boolean timePeriod = line[0].compareTo(startDateTime)>=0 && line[0].compareTo(endDateTime)<0;
            boolean condition = line[1].equals("Requeuing");
            if(timePeriod && condition) 
                count++;
        }
        return count;
    }
}
