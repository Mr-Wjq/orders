function toMoKuai(mes){
	switch (mes) {
	case 1:
		location.href=getRootPath() + "/system/userManage";
		break;
	case 2:
		location.href=getRootPath() + "/system/toSystem";
		break;
	case 3:
		location.href=getRootPath() + "/platform/getAssetsMonitor";
		break;
	}
}