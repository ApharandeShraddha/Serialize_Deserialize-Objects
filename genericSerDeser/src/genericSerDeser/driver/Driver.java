package genericSerDeser.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import genericSerDeser.fileOperations.FileDisplayInterface;
import genericSerDeser.fileOperations.FileProcessor;
import genericSerDeser.fileOperations.Logger;
import genericSerDeser.fileOperations.Logger.DebugLevel;
import genericSerDeser.strategy.SerStrategy;
import genericSerDeser.util.DPML;
import genericSerDeser.util.PopulateObjects;

public class Driver {
	int debugValue = 0;
	String inputFileName = "";
	String outputFileName = "";
	static BufferedReader reader = null;
	static BufferedWriter writer = null;

	public static void main(String args[]) {
		
		Driver dr = new Driver();
		FileDisplayInterface fp;
		SerStrategy dpmlSerializationStrategy;

		try {

			dr.validateArgs(args);
			Logger.setDebugValue(dr.getDebugValue());

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(dr.getInputFileName())));
			File file = new File(dr.getOutputFileName());
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

			fp = new FileProcessor(reader, writer);

			PopulateObjects populateObjects = new PopulateObjects(fp);
			List<Object> firstSecondClassObj = populateObjects.deserObjects();
			
			//Using DPML Strategy 
			dpmlSerializationStrategy = new DPML(fp);
			populateObjects.processObjects(firstSecondClassObj, dpmlSerializationStrategy);
			
			Logger.writeMessage("Output Stored in "+dr.getOutputFileName()+" File",DebugLevel.RELEASE);

		} catch (FileNotFoundException ex) {
			System.err.println("Input File " + dr.getInputFileName() + " doesn't exist.");
			System.exit(1);
		} catch (Exception ex) {
			System.err.println("Improper Input");
			System.exit(1);
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	} // end main(...)

	/**
	 * Validate input arguments
	 * 
	 * @param args
	 * @return void
	 */
	private void validateArgs(String args1[]) {
		
		
		if (args1.length == 3) {
			inputFileName = args1[0];
			outputFileName = args1[1];

			try {
				debugValue = Integer.valueOf(args1[2]);
				if (debugValue < 0 || debugValue > 4) {
					System.err.println("Debug value should be between 0 and 4.");
					System.exit(1);
				}
			} catch (IllegalArgumentException ex) {
				System.err.println("Debug level should be an Integer");
				System.exit(1);
			} finally {

			}
		} else {
			System.err.println("Invalid number of arguments. Expected : input file, output file, debug value .");
			System.exit(1);
		}
	}

	/**
	 * @return integer
	 */
	public int getDebugValue() {
		return debugValue;
	}

	/**
	 * @return void
	 */
	public void setDebugValue(int debugValue) {
		this.debugValue = debugValue;
	}

	/**
	 * @return String
	 */
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * @return void
	 */
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	/**
	 * @return String
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * @return void
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}//end of Driver class
