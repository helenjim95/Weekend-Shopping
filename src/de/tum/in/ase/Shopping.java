package de.tum.in.ase;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Shopping {
	private final Item[] shoppingList;
	private int bagCapacity;

	public Shopping(Item[] shoppingList, int bagCapacity) {
		this.shoppingList = shoppingList;
		this.bagCapacity = bagCapacity;
	}

	// TODO: implement search(String itemName)
	public int search(String itemName) {
		if (shoppingList.length == 0) {
			return -1;
		} else {
//		check if itemName exists in shoppingList: if yes: return -1 / if not: return index of the item in the list
		for (int i = 0; i < shoppingList.length; i++) {
			if (shoppingList[i].getName().equals(itemName)) {
				return i;
			}
		}
		return -1;
		}
	}

	// TODO: implement findMin()
	public int findMin() {
		if (shoppingList.length == 0) {
			return -1;
		} else {
			int temp_index_min = 0;
			int min_value = shoppingList[0].getValue();
			for (int i = 1; i < shoppingList.length; i++) {
				if (shoppingList[i].getValue() < min_value) {
					temp_index_min = i;
					min_value = shoppingList[i].getValue();
				}
			}
			return temp_index_min;
		}
	}

	// TODO: implement findMax()
	public int findMax() {
		if (shoppingList.length == 0) {
			return -1;
		} else {
			int temp_index_max = 0;
			int max_value = shoppingList[0].getValue();
			for (int i = 1; i < shoppingList.length; i++) {
				if (shoppingList[i].getValue() >= max_value) {
					temp_index_max = i;
					max_value = shoppingList[i].getValue();
				}
			}
			return temp_index_max;
		}
	}

	// TODO: implement fillBagMax()
	public Item[] fillBagMax() {
//		Return null if there is no prepared shopping list yet.
		if (shoppingList.length == 0) {
			return null;
		} else {
//		there should be no empty spot in the new shopping list - new list is the same length as original list
			List<Item> copy_of_shopping_list = new ArrayList<>(Arrays.asList(shoppingList));
			List<Item> new_shoppingList = new ArrayList<>();
			int sum_weight = 0;
			while(new_shoppingList.size() < copy_of_shopping_list.size()) {
				//		maximize value
				int max_index = findMax();
				Item max_item = shoppingList[max_index];
				int max_weight = max_item.getWeight();
				//		ensure sum of their weights does not exceed the maximum bagCapacity
				if (sum_weight + max_weight <= bagCapacity) {
					new_shoppingList.add(max_item);
					copy_of_shopping_list.remove(max_item);
					sum_weight += max_weight;
				} else {
					//		If the next item with highest value is too heavy,
//		then leave it out and try to find the next item with fitting weight and highest value
//		among the remaining items
					copy_of_shopping_list.remove(max_item);
				}
			}
//			conver List<Item> back to Item[]
			Item[] new_shoppingList_array = new Item[new_shoppingList.size()];
			new_shoppingList_array = new_shoppingList.toArray(new_shoppingList_array);
			return new_shoppingList_array;
		}
	}

	// TODO: implement calValue()
	public int calValue() {
		if (shoppingList.length == 0) {
			return 0;
		} else {
			Item[] shoppingList = fillBagMax();
			int sum_value = 0;
			for (int i = 0; i < shoppingList.length; i++) {
				sum_value += shoppingList[i].getValue();
			}
			return sum_value;
		}
	}

	public static void main(String[] args) {
		//you can test your code here by creating your own shopping object
	}
}
