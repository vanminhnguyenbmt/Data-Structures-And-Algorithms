// pch.cpp: source file corresponding to pre-compiled header; necessary for compilation to succeed

#include "pch.h"

// In general, ignore this file, but keep it around if you are using pre-compiled headers.

long GiaiThua(int h) {
	if (h == 0)
		return (1);
	else
		return (h * GiaiThua(h - 1));
}

void ThapHaNoi(int soDia, int cotNguon, int cotDich, int cotTrungGian) {
	if (soDia == 1)
		printf("Chuyen tu cot %d --> cot %d\n", cotNguon, cotDich);
	else {
		ThapHaNoi(soDia - 1, cotNguon, cotTrungGian, cotDich);
		ThapHaNoi(1, cotNguon, cotDich, cotTrungGian);
		ThapHaNoi(soDia - 1, cotTrungGian, cotDich, cotNguon);
	}
}