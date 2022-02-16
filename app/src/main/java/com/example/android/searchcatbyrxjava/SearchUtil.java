package com.example.android.searchcatbyrxjava;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class SearchUtil {

  @NonNull
  static ArrayList<String> getFilterList(String filter, ArrayList<String> tagsArrayList) {
      // creating a new array list to filter our data.
      ArrayList<String> filterList = new ArrayList<>();

      // running a for loop to compare elements.
      for (String item : tagsArrayList) {
          // checking if the entered string matched with any item of our recycler view.
          if (item.toLowerCase().contains(filter.toLowerCase())) {
              // if the item is matched we are
              // adding it to our filtered list.
              filterList.add(item);
          }
      }
    return filterList;
  }
}
