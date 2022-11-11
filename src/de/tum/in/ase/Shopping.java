package de.tum.in.ase;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Shopping {
//	TODO: contructor only two, write new find max method?
	private final Item[] shoppingList;
	private int bagCapacity;

	public Shopping(Item[] shoppingList, int bagCapacity) {
		this.shoppingList = shoppingList;
		this.bagCapacity = bagCapacity;
	}

	// TODO: implement search(String itemName)
	public int search(String itemName) {
		if (shoppingList == null || shoppingList.length == 0) {
			return -1;
		} else {
//		check if itemName exists in shoppingList: if yes: return index of the item in the list / if not: return -1
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

	public int findMax(List<Item> shoppingList) {
		if (shoppingList.size() == 0) {
			return -1;
		} else {
			int temp_index_max = 0;
			int max_value = shoppingList.get(0).getValue();
			for (int i = 1; i < shoppingList.size(); i++) {
				if (shoppingList.get(i).getValue() >= max_value) {
					temp_index_max = i;
					max_value = shoppingList.get(i).getValue();
				}
			}
			return temp_index_max;
		}
	}

//	need to fix this: how many items does the new shopping list should have?
	// TODO: implement fillBagMax()
	public Item[] fillBagMax() {
//		Return null if there is no prepared shopping list yet.
		if (shoppingList.length == 0) {
			return null;
		} else {
//		stopping condition - new list's total item weight <= bagCapacity or copy_of_shopping_list length == 0
			List<Item> copy_of_shopping_list = new ArrayList<>(Arrays.asList(shoppingList));
			List<Item> new_shoppingList = new ArrayList<>();
			List<Item> searched_list = new ArrayList<>();
			int sum_weight = 0;
			int max_weight = 0;
			while(copy_of_shopping_list.size() > 0) {
				//		maximize value
//				TODO: need to fix this (not to find max for the original list
				int max_index = findMax(copy_of_shopping_list);
				Item max_item = copy_of_shopping_list.get(max_index);
				max_weight = max_item.getWeight();
				//		ensure sum of their weights does not exceed the maximum bagCapacity
				if (sum_weight + max_weight <= this.bagCapacity) {
					new_shoppingList.add(max_item);
					copy_of_shopping_list.remove(max_item);
					sum_weight += max_weight;
//						set the searched max_item value to 0 so that it's not max anymore
				} else {
					//		If the next item with highest value is too heavy,
//		then leave it out and try to find the next item with fitting weight and highest value
//		among the remaining items
					copy_of_shopping_list.remove(max_item);
				}
//				searched_list.add(max_item);
			}
//			convert List<Item> back to Item[]
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
		Item computer = new Item("computer", 10, 10);
		Item headphone = new Item("headphone", 5, 5);
		Item mouse = new Item("mouse", 4, 3);
		Item keyboard = new Item("keyboard", 6, 6);
		Item charger = new Item("charger", 2, 2);
		Item[] shoppingList = new Item[] {computer, headphone, mouse, keyboard, charger};
		Shopping shopping = new Shopping(shoppingList, 15);
//		System.out.printf("search result (o): %d%n", shopping.search("charger"));
//		System.out.printf("search result (x): %d%n", shopping.search("macbook"));
//		System.out.printf("min: %d%n", shopping.findMin());
//		System.out.printf("max: %d%n", shopping.findMax());
		Item[] new_list = shopping.fillBagMax();
		System.out.println("new_list: ");
		for (Item item : new_list) {
			System.out.println(item.getName());
		}
	}
}
