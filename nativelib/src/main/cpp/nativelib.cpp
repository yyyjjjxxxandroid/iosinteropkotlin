#include <jni.h>
#include <string>

#include <valarray>

#include "game.h"



extern "C" JNIEXPORT jobjectArray JNICALL
Java_com_equationl_nativelib_NativeLib_stepUpdate(
        JNIEnv* env,
        jobject,
        jobjectArray lifeList
        ) {


    int len1 = env -> GetArrayLength(lifeList);
    auto dim =  (jintArray)env->GetObjectArrayElement(lifeList, 0);
    int len2 = env -> GetArrayLength(dim);
    int **board;
    board = new int *[len1];
    for(int i=0; i<len1; ++i){
        auto oneDim = (jintArray)env->GetObjectArrayElement(lifeList, i);
        jint *element = env->GetIntArrayElements(oneDim, JNI_FALSE);
        board[i] = new int [len2];
        for(int j=0; j<len2; ++j) {
            //out.write(std::to_string(element[j]).c_str(), 1);
            board[i][j]= element[j];
        }
        // 释放数组
        // ① 模式 0 : 刷新 Java 数组 , 释放 C/C++ 数组
        // ② 模式 1 ( JNI_COMMIT ) : 刷新 Java 数组 , 不释放 C/C ++ 数组
        // ③ 模式 2 ( JNI_ABORT ) : 不刷新 Java 数组 , 释放 C/C++ 数组
        env->ReleaseIntArrayElements(oneDim, element, JNI_ABORT);
        // 释放引用
        env->DeleteLocalRef(oneDim);
    }

    // 实际的处理逻辑
    updateStep(board, len1, len2);

    jclass cls = env->FindClass("[I");
    jintArray iniVal = env->NewIntArray(len2);
    jobjectArray result = env->NewObjectArray(len1, cls, iniVal);

    for (int i = 0; i < len1; i++)
    {
        jintArray inner = env->NewIntArray(len2);
        env->SetIntArrayRegion(inner, 0, len2, (jint*)board[i]);
        env->SetObjectArrayElement(result, i, inner);
        env->DeleteLocalRef(inner);
    }

    //out.close();

    return result;
}