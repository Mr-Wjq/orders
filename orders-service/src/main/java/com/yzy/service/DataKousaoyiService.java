package com.yzy.service;

import com.yzy.entity.DataKousaoyi;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface DataKousaoyiService {

	LayuiTable select(int page, int limit, String kousaoyiName);

	Result insert(DataKousaoyi dataKousaoyi);
	Result update(DataKousaoyi dataKousaoyi);

	Result delete(String ids);

}
