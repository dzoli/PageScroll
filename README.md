# PageScroll

![Alt text](https://github.com/dzoli/PageScroll/blob/master/screenshots/scroller.JPG?raw=true)

## Pre-requisites
- Android SDK 25
- Android Build Tools v25.0.1

## How to install
To get a Git project into your build: 
- Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories: </br>
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
  
- Step 2. Add the dependency </br>
```
dependencies {
	compile 'com.github.dzoli:PageScroll:0.1.0'
}	
```
	  
## Custom attributes:

| Attribute  | Description |
| ------------- | ------------- |
| custom:textColor  | Change text color  |
| custom:heightOfElementsAndTextSize  | Scale elements and text size (scope will apply from 30 to 120)  |
| custom:textChange  | Change text  |

## API Methods:

| Method  | Description |
| ------------- | ------------- |
| void setMaxCount(Integer maxPage)  | @param maxPage  -Max items to view  |
| void setCurrPage(Integer currPage)  | @param currPage -Item which is currently showen  |

## Layout sample
Add custom view into your layout
```
<com.makaji.aleksej.pagescroller.PageScrollerView_
        android:id="@+id/pageScrollerView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:textChange="Page:"
        custom:textColor="RED"
        custom:heightOfElementsAndTextSize="60">
</com.makaji.aleksej.pagescroller.PageScrollerView_>
```
## License
This project is licensed under the MIT License - see the LICENSE.md file for details
