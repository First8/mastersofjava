import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/*
Input:
	Value   processor
	50560	processor 1
	80536	processor 2
	4481	processor 3
	99623	processor 4
	96823	processor 2
	74527	processor 4
	43930	processor 3
	29523	processor 4
	51699	processor 4
	10943	processor 4
	33150	processor 2
	54758	processor 4
	48050	processor 4
	12173	processor 1
	98232	processor 3



	3 days expected	result				2 days expected result
	count 7								count 9
	99623 : processor 4					99623 : processor 4
	98232 : processor 3					98232 : processor 3
	96823 : processor 2					96823 : processor 2
	74527 : processor 4					80536 : processor 2
	54758 : processor 4					74527 : processor 4
	50560 : processor 1					54758 : processor 4
	12173 : processor 1					51699 : processor 4
	count 4					            50560 : processor 1
	80536 : processor 2					43930 : processor 3
	51699 : processor 4					count 6
	48050 : processor 4					48050 : processor 4
	43930 : processor 3					33150 : processor 2
	count 4					            29523 : processor 4
	33150 : processor 2					12173 : processor 1
	29523 : processor 4					10943 : processor 4
	10943 : processor 4					4481 : processor 3
	4481 : processor 3					
*/

public class WorkloadbalancerDaysinListTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void testDaysinList() {

		WorkloadBalancerImpl instance = new WorkloadBalancerImpl();
		int threeDayJob = 3;
		int twoDayJob = 2;
		List<List<Order>> orders = null;
		orders = instance.distributeOrders(threeDayJob, createTestData());
		checkDaysinList(orders, threeDayJob);
		orders = instance.distributeOrders(twoDayJob, createTestData());
		checkDaysinList(orders, twoDayJob);
	}

	private boolean checkOrderSequence(List<List<Order>> actualResult, int days) {
		List<List<Order>> expectedResult = distributeOrders(days, createTestData());
		for (int i = 0; i < actualResult.size(); i++) {
			List<Order> actual = actualResult.get(i);
			List<Order> expected = expectedResult.get(i);
			actual.removeAll(expected);
			collector.checkThat("", actual.size(), equalTo(0));
		}
		return true;
	}

	private boolean checkOrderCountPerDay(List<List<Order>> actualResult, int days) {
		List<List<Order>> expectedResult = distributeOrders(days, createTestData());
		for (int i = 0; i < actualResult.size(); i++) {
			List<Order> actual = actualResult.get(i);
			List<Order> expected = expectedResult.get(i);
			collector.checkThat("", actual.size(), equalTo(expected.size()));
		}
		return true;
	}

	private boolean checkDaysinList(List<List<Order>> orders, int days) {
		collector.checkThat("", days, equalTo(orders.size()));
		return true;

	}

	private List<Order> createTestData() {
		List<Order> testSet = new ArrayList<>();
		testSet.add(new Order(50560, "processor 1"));
		testSet.add(new Order(80536, "processor 2"));
		testSet.add(new Order(4481, "processor 3"));
		testSet.add(new Order(99623, "processor 4"));
		testSet.add(new Order(96823, "processor 2"));
		testSet.add(new Order(74527, "processor 4"));
		testSet.add(new Order(43930, "processor 3"));
		testSet.add(new Order(29523, "processor 4"));
		testSet.add(new Order(51699, "processor 4"));
		testSet.add(new Order(10943, "processor 4"));
		testSet.add(new Order(33150, "processor 2"));
		testSet.add(new Order(54758, "processor 4"));
		testSet.add(new Order(48050, "processor 4"));
		testSet.add(new Order(12173, "processor 1"));
		testSet.add(new Order(98232, "processor 3"));
		return testSet;
	}

	private List<List<Order>> distributeOrders(int days, List<Order> orders) {
		Comparator<Order> comp = new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				int temp = o1.getProcessor().compareTo(o2.getProcessor());
				if (temp == 0) {
					return Integer.valueOf(o2.getValue()).compareTo(Integer.valueOf(o1.getValue()));
				} else {
					return temp;
				}
			}
		};
		Collections.sort(orders, comp);
		List<List<Order>> ordersPerday = new ArrayList<List<Order>>();

		Map<String, List<Order>> ordersPerProcessor = new HashMap<>();
		for (Order order : orders) {
			if (ordersPerProcessor.get(order.getProcessor()) == null) {
				ordersPerProcessor.put(order.getProcessor(), new ArrayList<Order>());
			}
			ordersPerProcessor.get(order.getProcessor()).add(order);
		}

		for (int i = 0; i < days; i++) {
			ordersPerday.add(new ArrayList<Order>());
		}

		for (Entry<String, List<Order>> processorList : ordersPerProcessor.entrySet()) {
			List<Order> ordersForProcessor = processorList.getValue();
			int batchSizePerBatch[] = new int[days];
			for (int i = 0; i < batchSizePerBatch.length; i++) {
				batchSizePerBatch[i] = ordersForProcessor.size() / days;
				if (i == 0 && ordersForProcessor.size() % days != 0) {
					batchSizePerBatch[i] += ordersForProcessor.size() % days;
				}
			}
			int fromIndex = 0;
			for (int j = 0; j < batchSizePerBatch.length; j++) {
				int toIndex = batchSizePerBatch[j] + fromIndex;
				if (toIndex >= fromIndex) {
					List<Order> subList = ordersForProcessor.subList(fromIndex, toIndex);
					ordersPerday.get(j).addAll(subList);
				}
				fromIndex += batchSizePerBatch[j];
			}

		}
		Comparator<Order> comp2 = new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return Integer.valueOf(o2.getValue()).compareTo(Integer.valueOf(o1.getValue()));
			}
		};
		for (List<Order> order : ordersPerday) {
			Collections.sort(order, comp2);
		}
		return ordersPerday;
	}

}
