# ScratchViewSample
Android widget for scratch and win view in andoird.

## Custom scratch view in android projects

## Step 1. Add the JitPack repository to your build file

gradle
```gradle
allprojects {
	       repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	    }
```
maven
```maven
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
## Step 2. Add the dependency

gradle 
```gradle
dependencies {
	implementation 'com.github.gokul42252:ScratchViewSample:Tag'
	     }
```
maven
```maven
<dependency>
	<groupId>com.github.gokul42252</groupId>
	<artifactId>ScratchViewSample</artifactId>
	<version>Tag</version>
</dependency>
```
## Step 3. Implementing in project
### Add Activity.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:background="@color/colorPrimaryDark"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <android.support.v7.widget.AppCompatTextView
            android:layout_width="200dp"
            android:background="@color/colorWhite"
            android:layout_height="200dp"
            android:id="@+id/text"
            android:text="@string/you_won_n_11"
            android:textSize="28sp"
            tools:ignore="MissingConstraints"
            android:gravity="center"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
    />

    <com.ct.scratchview.view.EraseView
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:id="@+id/main_view"
            android:background="@color/trans"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
    />

</android.support.constraint.ConstraintLayout>
```
![alt text](https://github.com/gokul42252/ScratchViewSample/blob/master/app/Screenshot_20190208-163558.jpg)




