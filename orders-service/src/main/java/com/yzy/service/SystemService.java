package com.yzy.service;

import com.yzy.utils.Result;

public interface SystemService {

	Result getPhoneCode(String phone);

	Result getPhoneCodeForRp(String phone);

}
