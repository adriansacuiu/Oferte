package util;

import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import entities.Request;

public class RequestComparator implements Comparator<Request> {

	public int compare(Request request1, Request request2) {
		Date startDateDate1 = request1.getDate();
		Date startDateDate2 = request2.getDate();
		String status1 = request1.getStatus();
		String status2 = request2.getStatus();
		
		if (startDateDate1 != null && startDateDate2 != null && status1 != null && status2 != null) {		
		String date1 = startDateDate1.toString();
		String date2 = startDateDate2.toString();
		
			if (status1.equals(status2)) {
				return date1.compareTo(date2);

			} else if (status1.equals("New")) {
				return -1;

			} else if (status2.equals("New")) {
				return 1;

			} else if (status1.equals("Done")) {
				return -1;

			} else if (status2.equals("Done")) {
				return 1;

			} else if (status1.equals("Rejected")) {
				return -1;

			}

			return 1;
			
		} else {
			return -1;
		}

	}

	public Comparator<Request> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Request> thenComparing(Comparator<? super Request> other) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> Comparator<Request> thenComparing(Function<? super Request, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Request> thenComparing(Function<? super Request, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Request> thenComparingInt(ToIntFunction<? super Request> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Request> thenComparingLong(ToLongFunction<? super Request> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Request> thenComparingDouble(ToDoubleFunction<? super Request> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

}
