# MVVMHabitComponent
##
> **原文地址：** [https://github.com/goldze/MVVMHabitComponent](https://github.com/goldze/MVVMHabitComponent)

### MVVM模式 + 组件化

安卓端项目结构
├── .gradle # gradle编译系统
├── .idea# 项目的配置信息，包括历史记录，版本控制信息
├── app #项目主工程
│ ├── build # 构建输出
│ ├── src # 主要文件目录
││├── main # 主要项目目录和代码
│││├── alone # 调试目录
│││├── jni # 预留项如jni
│││├── java # 项目源代码
│││├── res # 资源目录
├── gradle #构建
├── library-base #公共依赖库
├── library-res #公共资源库
├── library-network #网络库
├── module-xx # 各个模块组件
├── .gitignore # 忽略文件
├── build.gradle # 项目gradle编译文件
├── .gradle.properties # gradle相关全局属性设置
├── gradlew # 编译脚本
├── gradlew.bat # windows下的gradle wrapper可执行文件
├── local.properties # 配置sdk、ndk路径
├── README.md # 相关信息
├── config.gradle # 公用配置信息
├── module.build.gradle # 提前公用build.gradle模块
├── settings.gradle # 设置相关的gradle脚本
├── External Libraries # 项目依赖的库
