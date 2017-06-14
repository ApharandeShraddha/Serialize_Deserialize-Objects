package genericSerDeser.fileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;



/**
 * Class having methods for file read and write operations
 * @author    Shraddha Apharande
 */
public class FileProcessor implements FileDisplayInterface{
	
	
	 BufferedReader reader;
	 BufferedWriter writer;

	public FileProcessor( BufferedReader readerIn, BufferedWriter writerIn) {
		
		reader=readerIn;
		writer=writerIn;
	}
/**
 * This method read input file 
 * @param Input filename
 * @return StringBuilder
 */
	public String readFile( ) {

		String line="";

		try {
			// File read code taken from web (below 1 line)
			 line = reader.readLine();
			if (line != null && line.trim().isEmpty()) {
				System.err.println("Either current line or Input file is empty \n");
				System.exit(1);
			}
		}  catch (IOException ex) {
			System.err.println("Error while reading file.");
			System.exit(1);
		} catch (Exception ex) {
			System.err.println("Error while performing file operations");
			System.exit(1);
		}finally{
			
		}
		return line;
	}
	
	/**
	 * This method writes sum of all cells to output file
	 * @param Output filename
	 * @return void
	 */

	public  void write(String outputString) {
		try {
			writer.write(outputString);
			
		} catch (IOException e) {
			System.err.println("Writing to output file failed");
			System.exit(1);
		} 
	}
	
	
}
