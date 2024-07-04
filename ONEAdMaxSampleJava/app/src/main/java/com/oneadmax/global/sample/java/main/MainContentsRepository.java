package com.oneadmax.global.sample.java.main;

import com.oneadmax.global.sample.java.common.base.MenuItem;

import java.util.List;

public interface MainContentsRepository {
    List<MenuItem> fetchItems();
}
