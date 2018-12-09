package util;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import entities.Complaint;

public class ComplaintComparator implements Comparator<Complaint> {

	public int compare(Complaint complaint1, Complaint complaint2) {
		String priority1 = complaint1.getPriority();
		String priority2 = complaint2.getPriority();

		String status1 = complaint1.getStatus();
		String status2 = complaint2.getStatus();

		if (priority1 != null && priority2 != null && status1 != null && status2 != null) {
			if (status1.equals(status2)) {
				if (priority1.equals(priority2)) {
					return 0;

				} else if (priority1.equals("High")) {
					return -1;

				} else if (priority2.equals("High")) {
					return 1;
				}

				else if (priority1.equals("Medium")) {
					return -1;

				} else {
					return 1;
				}

			} else if (status1.equals("New")) {
				return -1;
			}

			return 1;
			
		} else {
			return -1;
		}
	}

	public Comparator<Complaint> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Complaint> thenComparing(Comparator<? super Complaint> other) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> Comparator<Complaint> thenComparing(Function<? super Complaint, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Complaint> thenComparing(Function<? super Complaint, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Complaint> thenComparingInt(ToIntFunction<? super Complaint> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Complaint> thenComparingLong(ToLongFunction<? super Complaint> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Complaint> thenComparingDouble(ToDoubleFunction<? super Complaint> keyExtractor) {
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
