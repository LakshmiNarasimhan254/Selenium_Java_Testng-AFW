package Java_Concepts;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class HashMap_Thread {
	
	public static void main(String[] args) {
		final Map<Integer, String> map = new HashMap<>();

		final Integer targetKey = 0b1111_1111_1111_1111; // 65 535
		final String targetValue = "v";
		map.put(targetKey, targetValue);

		new Thread(() -> {
		    IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
		}).start();


		while (true) {
		    if (!targetValue.equals(map.get(targetKey))) {
		        throw new RuntimeException("HashMap is not thread safe.");
		    }
	}
	}}



