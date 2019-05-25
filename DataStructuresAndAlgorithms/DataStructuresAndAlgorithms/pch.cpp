// pch.cpp: source file corresponding to pre-compiled header; necessary for compilation to succeed

#include "pch.h"

// In general, ignore this file, but keep it around if you are using pre-compiled headers.

long GiaiThua(int h) {
	if (h == 0)
		return (1);
	else
		return (h * GiaiThua(h - 1));
}