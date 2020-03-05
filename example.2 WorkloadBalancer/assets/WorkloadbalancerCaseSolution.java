import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WorkloadbalancerImpl {

		public List<List<Order>> distributeOrders(int days, List<Order> orders) {
		Comparator<Order> comp = new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				int temp = o1.getProcessor().compareTo(o2.getProcessor());
				if (temp == 0) {
					return Integer.valueOf(o2.getValue()).compareTo(
							Integer.valueOf(o1.getValue()));
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
				ordersPerProcessor.put(order.getProcessor(),
						new ArrayList<Order>());
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
					List<Order> subList = ordersForProcessor.subList(fromIndex,
							toIndex);
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
			Collections.sort(order,comp2);
		}
		return ordersPerday;
	}

}
