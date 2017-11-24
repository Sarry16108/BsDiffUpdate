//
// Created by Administrator on 2017/11/23.
//

#ifndef BSDIFFUPDATE_BS_H
#define BSDIFFUPDATE_BS_H

#endif //BSDIFFUPDATE_BS_H

#include <malloc.h>
#include <jni.h>

int mydiff(int argc,char *argv[]);
int mypatch(int argc,char * argv[]);

JNIEXPORT jint JNICALL
        Java_com_example_finance_bsdiffupdate_MainActivity_patch
        (JNIEnv *env, jobject instance, jstring oldpath_, jstring newpath_,jstring patch_);
JNIEXPORT jint JNICALL
        Java_com_example_finance_bsdiffupdate_MainActivity_diff
        (JNIEnv *env, jobject instance, jstring oldpath_, jstring newpath_, jstring patch_);