# MapView

Android View Based Mind Mapping Library

### Features

- [x] Custom Map Block Support (render anything)
- [x] Clickable Children
- [x] Zoom Layout Support
- [x] Extremely Customizable
- [x] Serialization Support With Gson 

### Setup

#### **Step 1**. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### **Step 2**. Add the dependency

```groovy
dependencies {
    implementation 'com.github.timeline-notes:mapview:0.3.4'
}
```

### Usage

```kotlin
val mapper = MindMapper(this)

val center = mapper.setCenter("My Center")

val another = mapper.addBlockToLeftOf(center, "Hello Darkness My Old Friend")

mapper.render()

layout.addView(mapper)
```
