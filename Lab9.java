//James Zhang
package lab9;
import java.util.Scanner;
import java.io.*;

public class Lab9 {
public static void main(String[] args) throws IOException {
    int badColumnCount = 0;
    int totalCount = 0;
    int njCount = 0;
    int otherCount = 0;
    int poBoxOnlyCount = 0;
    int militaryCount = 0;
    int standardCount = 0;
    
    PrintWriter badZips = new PrintWriter("BadZips.txt");
    PrintWriter NJZips = new PrintWriter("NJZips.txt");
    File file = new File("free-zipcode-database.csv");
    Scanner inputFile = new Scanner(file);
    String lineRead;
    String[] columnArray;
    
    int i = 0;
    if(inputFile.hasNext())
    {
        lineRead = inputFile.nextLine();
        columnArray = lineRead.split(",");
    }
    
    while(inputFile.hasNext())
    {
        lineRead = inputFile.nextLine();
        columnArray = lineRead.split(",");
        for(i = 0; i < columnArray.length; i++)
        {
            columnArray[i] = columnArray[i].replace('"', ' ');
            columnArray[i] = columnArray[i].trim();
        }
        
        if (columnArray.length != 19)
        {
            badColumnCount++;
            totalCount++;
            badZips.println(lineRead);
        } else {
            totalCount++;

                if(columnArray[6].equalsIgnoreCase("po box only")) {
                    poBoxOnlyCount++;
                }

                else if(columnArray[6].equalsIgnoreCase("military")) {
                    militaryCount++;
                }

                else if(columnArray[6].equalsIgnoreCase("standard")) {
                    standardCount++;

                    if(columnArray[4].equalsIgnoreCase("NJ")) {
                        njCount++;

                        NJZips.println(columnArray[0] + ',' + columnArray[3]);
                    }

                }

                else {
                    otherCount++;
                }
        }
    }
    
    System.out.printf("Number PO Box                %,d\n", poBoxOnlyCount);
    System.out.printf("Number Military              %,d\n", militaryCount);
    System.out.printf("Number Standard              %,d - NJ %,d\n", standardCount, njCount);
    System.out.printf("Number Other                 %,d\n\n", otherCount);
    System.out.printf("Total Records Read           %,d\n\n", totalCount);
    System.out.printf("number Bad Records           %,d", badColumnCount);
    
    inputFile.close();
    badZips.close();
    NJZips.close();
        
        }
    }
