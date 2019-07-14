package com.yzy.service;

import com.yzy.entity.DataUnitFrom;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface DataUnitFromService {

	LayuiTable select(int page, int limit);

	Result insert(DataUnitFrom dataUnitFrom);

	Result update(DataUnitFrom dataUnitFrom);

	Result delete(String ids);

}
