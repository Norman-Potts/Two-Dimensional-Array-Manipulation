/**
 * Two-Dimensional-Array-Manipulation
 * 2D Array manipulation - Read a file and produce some summary data.
 * Two-Dimensional-Array-Manipulation
 * Requirements
 * 1. Print the highest value found in the entire data set and how many 
 *    times it occurs in the data set.
 * 2. Print the first row number with the greatest range (highest-lowest)
 *    and print the range.
 * 3. Print the row and column of the highest prime number as well as the 
 *    highest Prime Number.
 * 4. Print the total of the column positions of the first Prime Number in
 *    each row. The first column should be considered column # 1.
 * 5. How many numbers in the range of 20000 to 120000 occur at least once.
 * 6. Which value occurs the most frequently?  How many times does it occur?
 *  
 */
package Two.Dimensional.Array.Manipulation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;




/** class Main
 *
 * @author Norman
 */
public class Main {
    /*** Analyze the Data - As required ***
     * Read DATA into data array then start timer.
     * Complete project requirements then stop timer
     * @param args 
     */
    public static void main(String[] args) {
        // First - Read from data file into 2D Array, then start the timmer.
        // Note the first line is the correct results       
        final String FILENAME = "DATA.TXT";        
        int [][] data = null;  int numberOfRows = 0;  int numberOfCols = 0;    
        try {
            File file = new File(FILENAME);
            Scanner inputFile = new Scanner(file);
            // Read the number of Rows and Columns first
            numberOfRows = inputFile.nextInt(); numberOfCols = inputFile.nextInt();
            data = new int[numberOfRows][numberOfCols];
            for (int row = 0; row < numberOfRows; row++)  {
                for (int col = 0; col < numberOfCols; col++) {
                    data[row][col] = inputFile.nextInt();
                }
            }        
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error reading data from " + FILENAME + " Exception = " + ex.getMessage());
        }
        System.out.printf ("Data has been read Rows: %d, Columns: %d \n", numberOfRows, numberOfCols);
        // File has been read into a 2D array called data.
        // Begin requirements...        
        // Start the timmer.
        long starttime = System.nanoTime();  
        
        
        // Requirement 1a. Find highest value found in entire data set.
        // Requirement 1b. Find how many times maxValue occurs.
        // Print results.        
        int CountofMax=0;
        int maxValue=data[0][0];
        for (int row =0; row < numberOfRows; row++) {
            for(int col=0; col < numberOfCols; col++) {
                if( data[row][col]> maxValue) {
                    maxValue = data[row][col];
                } else if( data[row][col] == maxValue) {
                    CountofMax++; 
                }
            }
        }
        System.out.printf("1. The highest value is %d and occurs %d times. \n",maxValue, CountofMax);       
        

        // Requirement 2 Find the row with the greatest range.    
        int rowhighest; int rowlowest; int rowrange ;  int greatestrange = 0; int rowNumber = 0;
        for (int row = 0; row < numberOfRows; row++) {
            rowhighest=0;  rowlowest = maxValue;  rowrange=0;
            for(int col=0; col < numberOfCols; col++) {
                if( data[row][col] > rowhighest ) {
                    rowhighest=data[row][col];
                }
                if(data[row][col]< rowlowest ) {
                    rowlowest = data[row][col];
                }      
                rowrange = rowhighest-rowlowest;
                if(rowrange > greatestrange)  {
                    greatestrange=rowrange;
                    rowNumber = row;
                }
            }
        }    
        System.out.printf("2. The greatest range is %d and is in row %d. \n", greatestrange, (rowNumber+1));                             


        // Requirement 3. Find the greatest prime number and the row and column
        // It is located in with +1 to off set subset 0.
        int GreatestNum=0; int Greatestrow=0; int Greatestcol=0;
        for(int row=0; row < numberOfRows; row++) {
            for(int col=0; col < numberOfCols; col++) {
                ///  int PrimeNumber = 0;                 
                int PrimeNumber=data[row][col];                                    
                if(Main.isPrime(PrimeNumber) == true) {                 
                    ///  PrimeNumber= num;
                    if (PrimeNumber > GreatestNum) {
                        GreatestNum= PrimeNumber;
                        Greatestrow=row+1;/// Add one to off set subset zero.
                        Greatestcol=col+1;/// Move row and col in to variable. 
                    }
                }                          
            }   
        }   
        System.out.printf("3. The greatest prime number is %d. The Row is %d. The col is %d.\n",GreatestNum, Greatestrow, Greatestcol );

     
        // Requirement 4. Print the total of the column positions of the first 
        // prime number in each row.
        int coltotal=0;
        for ( int row=0; row<numberOfRows; row++) {
            for(int col= 0; col < numberOfCols; col++) {
                int num=data[row][col];                         
                if(Main.isPrime(num) == true) {
                    coltotal= coltotal+(col+1);
                    break;
                }             
            }
        }    
        System.out.printf("4. The total of the column position of the first prime number in each row is %d.\n",coltotal);                       
        
        
        // Requirement 5. Print the number of numbers in the range of 200000
        // to 2200000 that occurs at least once.
        int tempNumber= 0;
        for(int row=0; row <= numberOfRows-1; row++) {
            for(int col = 0; col <= numberOfCols-1; col++) {
                if(tempNumber <data[row][col]) {
                    tempNumber = data[row][col]+1;            
                }
            }
        }        
        int [] HoldArray= new int[tempNumber];
        for(int row = 0; row <= numberOfRows-1; row++) {
            for(int col= 0; col <= numberOfCols-1; col++) {
                if(data[row][col] >=2000000 && data[row][col] <= 12000000 ) {
                    HoldArray[data[row][col]]++;
                }
            }
        }       
        int numofnum=0;
        for(int row=0; row<=tempNumber-1; row++) {
            if(HoldArray[row]>=1) {
                numofnum++;
            }        
        }
        System.out.printf("5. The number of values in the range of 200000 to 2200000 that occurs at least once is %d.\n",numofnum);
        
                        
        // Requirements 6. Find the number that appears the most times, and how 
        // many times it appears.
        int Occurs= 0; int MostNumber=0;
        for(int row=0; row<tempNumber-1; row++) {
            if(HoldArray[row]>Occurs) {
                Occurs = HoldArray[row];
                MostNumber = row;
            }
        }    
        System.out.printf("6. The number that occurs the most is %d, and occurs %d times.\n", MostNumber, Occurs );
        
        
        // This line in will tell you how long your solution takes to complete.
        // All six parts should occur in less than 1 second   
        double seconds = (System.nanoTime() - starttime);//this statment converts nanoTime to a double seconds 
        seconds = seconds/1000000000;// prints out seconds because it is easyier to read.
        System.out.printf("Time to execute = %d microseconds or %f seconds \n",  ((System.nanoTime() - starttime)/1000), seconds);                          
    }/// End of method main
  
   
   
    
    /** Method isPrime
     *      Is the number n a prime - simple implementation.
     *
     * @param n number to test
     * @return true is prime
     */
    public static boolean isPrime(int n) {
        int maxn = (int) Math.sqrt((double)n);
        for (int i = 2; i<=maxn; i++) {
            if (n % i == 0) {
                return false;
            }
        }        
        return true;          
    }//// End of method isPrime
   
    
}///End of class  Main

 
