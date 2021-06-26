import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fileIO {

	public static ArrayList<String> readFile(String inputFileName) {

		ArrayList<String> expr = new ArrayList<>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			String line = reader.readLine();
			while (line != null) {
				expr.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found.");
			;
		}

		return expr;
	}

	public static void writingOutput(ArrayList<String> outputs, String outputFileName) {

		BufferedWriter bw = null;
		try {

			File file = new File(outputFileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < outputs.size(); i++) {
				String mycontent = outputs.get(i);
				if (i != outputs.size() - 1) {
					bw.write(mycontent + "\n");
				} else {
					bw.write(mycontent);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}
	}
}
