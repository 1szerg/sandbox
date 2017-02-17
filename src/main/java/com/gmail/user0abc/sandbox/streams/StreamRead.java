package com.gmail.user0abc.sandbox.streams;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class StreamRead {
	public static void main(String[] args) {
		try {
			goForIt(args[0], args[1]);
		} catch (Exception e) {
			System.out
					.println("Usage: java -jar StreamRead.jar <source_path> <destination_path>");
			System.out.println("Error: " + e.getLocalizedMessage());
		}
	}

	private static void goForIt(String inputFilePath, String outputFilePath) {
		File in = new File(inputFilePath);
		if (!in.exists() || !in.canRead() || in.isDirectory()) {
			throw new IllegalArgumentException("illegal <source_path> argument");
		}
		List<String> buffer = new LinkedList<>();
	}
}
