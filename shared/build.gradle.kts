
@file:Suppress("DEPRECATION")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)

        id("io.github.ttypic.swiftklib") version "0.6.4"

}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
//        // 添加MTGenerateSign依赖
//        pod("MTGenerateSign") {
//            git("http://techgit.meitu.com/iOSModules_public/MTGenerateSign.git"){
//                branch = "master"
//                tag = "1.4.2"
//            }
//        }
//        pod("FirebaseAuth") {
//            version = "10.16.0"
//        }
//        pod("YYModel"){
//            version = "1.0.4"
//        }
        pod("MTGenerateSign") {
            moduleName = "MTGenerateSignKit"
            source = git("http://techgit.meitu.com/iOSModules_public/MTGenerateSign.git") {
                tag = "1.2.1"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.apply {
            compilations.getByName("main") {
//                val frameworkPath = "/Users/yjx/AndroidStudioProjects/iosinteropkotlin/shared/src/iosMain/kotlin/nativeInterop/cinterop/MTGenerateSign-1.4.2/MTGenerateSign/MTGenerateSignKit.framework/MTGenerateSignKit" // 替换为实际路径
//                val MTGenerateSignKit by cinterops.creating {
//                    definitionFile.set(project.file("/Users/yjx/AndroidStudioProjects/iosinteropkotlin/shared/src/iosMain/kotlin/nativeInterop/cinterop/MTGenerateSignKit.def"))
//                    compilerOpts(
//                        "-framework",
//                        "MTGenerateSignKit",
//                        "-F$frameworkPath"
//                    )
//                }
//                target.binaries.all {
//
//                    linkerOpts(
//                        "-framework",
//                        "MTGenerateSignKit",
//                        "-F$frameworkPath"
//                    )
//                }
                cinterops {
                    val nativelib by creating {
                        defFile(project.file("src/iosMain/kotlin/nativeinterop/cinterop/nativelib.def"))
                        compilerOpts("-Isrc/iosMain/kotlin/nativeinterop/cinterop/")
                    }
                    val libtest by creating {
                        defFile(project.file("src/iosMain/kotlin/nativeinterop/cinterop/libtest.def"))
                        compilerOpts("-Isrc/iosMain/kotlin/nativeinterop/cinterop/")
                    }

                    create("HelloSwift")
                }

            }
        }
    }
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

}

android {
    namespace = "com.example.iosinteropkotlin"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
swiftklib {
    create("HelloSwift") {
        path = file("native/HelloSwift")
        packageName("com.ttypic.objclibs.greeting")
    }
}
dependencies {
    implementation(project(":nativelib"))
}
