import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

// Import any package as required

public class HuffmanSubmit implements Huffman {

	private static final int NUM_ASCII = 256;

	public static class Node implements Comparable<Node> {

		public Character c;
		public int weight; // the higher the frequency the higher the weight
		public Node left;
		public Node right;

		// constructor
		public Node(Character c, int weight, Node left, Node right) {
			// setting the class variables to have the values the object is instantiated
			// with
			this.c = c;
			this.weight = weight;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node node) {
			return this.weight - node.weight;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public String toString() {
			return "Character: " + c + ", Frequency: " + weight;
		}

	}

	public void writeFreqFile(String freqFile, int[] freqArr) {
		// FileWriter writes on the file using BufferedWriter

		BufferedWriter bw = null;
		try {
			File file = new File(freqFile);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < freqArr.length; i++) {
				if (freqArr[i] > 0) {
					String mycontent = getAsciiCode((char) i) + ":" + Integer.toString(freqArr[i]);
					bw.write(mycontent + "\n");
				}
			}
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Frequency file could not be written.");
		}
	}

	// gets ASCII code of a given character and if the length of the code isn't 8
	// just slaps on a 0 in the front
	public String getAsciiCode(char c) {
		String asciiCode = Integer.toBinaryString(c);
		while (asciiCode.length() != 8) {
			asciiCode = "0" + asciiCode;
		}
		return asciiCode;
	}

	public static Node buildTrie(int[] freqArr) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>(freqArr.length);

		for (int i = 0; i < freqArr.length; i++) {
			if (freqArr[i] > 0) {
				queue.add(new Node((char) i, freqArr[i], null, null));
			}
		}

		while (queue.size() > 1) {
			// small trees are merged till root is generated
			Node left_child = queue.poll();
			Node right_child = queue.poll();
			Node parent = new Node('\0', left_child.weight + right_child.weight, left_child, right_child);
			queue.add(parent);
		}
		// returns parent node of trie
		return queue.poll();
	}

	// buildCode DONE
	public static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1');
		} else {
			st[x.c] = s;
		}
	}

	// Feel free to add more methods and variables as required.
	public void encode(String inputFile, String outputFile, String freqFile) {

		int[] freqArr = new int[NUM_ASCII];
		ArrayList<Character> charList = new ArrayList<>();

		BinaryIn binaryIn = new BinaryIn(inputFile);

		BinaryOut binaryOut = new BinaryOut(outputFile);

		while (!binaryIn.isEmpty()) {
			char c = binaryIn.readChar();
			freqArr[c]++;
			charList.add(c);
		}

		writeFreqFile(freqFile, freqArr);

		Node root = buildTrie(freqArr);

		String[] st = new String[NUM_ASCII];
		buildCode(st, root, "");

		for (int i = 0; i < charList.size(); i++) {
			String code = st[charList.get(i)];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '0') {
					binaryOut.write(false);
				} else if (code.charAt(j) == '1') {
					binaryOut.write(true);
				} else
					throw new IllegalStateException("Illegal state");
			}
		}

		// close output stream
		binaryOut.flush();
		binaryOut.close();

	}

	//reads the frequency file, splits the character and its frequency and assigns the character that
	//frequency in the int[] freq array which is then passed onto the other buildTrie method 
	public static Node buildTrie(String freqFile) {
		int[] freqArr = new int[NUM_ASCII];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(freqFile));
			String line = reader.readLine();
			while (line != null && line != "" && line != "\n") {
				String str[] = line.split(":");
				freqArr[Integer.parseInt(str[0], 2)] = Integer.parseInt(str[1]);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found.");
		}

		return buildTrie(freqArr);

	}

	public void decode(String inputFile, String outputFile, String freqFile) {
		// initializing binary input stream
		BinaryIn in = new BinaryIn(inputFile);

		// initializing binary output stream
		BinaryOut out = new BinaryOut(outputFile);

		// read the trie, encoded as a bitstream
		Node trie_root = buildTrie(freqFile);

		int length = trie_root.weight;
		
		for (int i = 0; i < length; i++) {
			Node x = trie_root;
			while (!x.isLeaf()) {
				boolean bit = in.readBoolean();
				if (bit)
					x = x.right;
				else
					x = x.left;
			}
			out.write(x.c);
		}
		out.flush();
		out.close();
	}

	public static void main(String[] args) {
		Huffman huffman = new HuffmanSubmit();
		huffman.encode("alice30.txt", "alice30.enc", "freq.txt");
		huffman.decode("alice30.enc", "alice30_dec.txt", "freq.txt");
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
		// After decoding, both ur.jpg and ur_dec.jpg should be the same.
		// On linux and mac, you can use `diff' command to check if they are the same.
	}

}
