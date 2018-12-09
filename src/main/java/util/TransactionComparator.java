package util;

import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import entities.Transaction;

public class TransactionComparator implements Comparator<Transaction> {

	public int compare(Transaction transaction1, Transaction transaction2) {
		Date startDateDate1 = transaction1.getStartDate();
		Date startDateDate2 = transaction1.getStartDate();
		String status1 = transaction1.getStatus();
		String status2 = transaction2.getStatus();
		
		if (startDateDate1 != null && startDateDate2 != null && status1 != null && status2 != null) {		
		String startDate1 = startDateDate1.toString();
		String startDate2 = startDateDate2.toString();
		
			if (status1.equals(status2)) {
				return startDate1.compareTo(startDate2);

			} else if (status1.equals("Pending")) {
				return -1;

			} else if (status2.equals("Pending")) {
				return 1;

			} else if (status1.equals("Success")) {
				return -1;

			} else if (status2.equals("Success")) {
				return 1;

			} else if (status1.equals("Declined")) {
				return -1;

			}

			return 1;
			
		} else {
			return -1;
		}
	}

	public Comparator<Transaction> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Transaction> thenComparing(Comparator<? super Transaction> other) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> Comparator<Transaction> thenComparing(Function<? super Transaction, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Transaction> thenComparing(Function<? super Transaction, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Transaction> thenComparingInt(ToIntFunction<? super Transaction> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Transaction> thenComparingLong(ToLongFunction<? super Transaction> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Transaction> thenComparingDouble(ToDoubleFunction<? super Transaction> keyExtractor) {
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
