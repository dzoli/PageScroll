# PageScroll

![Alt text](https://github.com/dzoli/PageScroll/blob/master/screenshots/scroll.JPG?raw=true)

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
	compile 'com.github.dzoli:PageScroll:0.1.6'
}	
```
	  
## Custom attributes:

| Attribute  | Description |
| ------------- | ------------- |
| custom:textColor  | Change text color  |
| custom:heightOfElementsAndTextSize  | Scale elements and text size (scope will apply from 16 to 50)  |
| custom:textChange  | Change text  |
| custom:numbersFading  | Fading elements in list  |
| custom:animationSpeed  | Change animation speed of scrolling (scope will apply from 150 to 280. Optimal is 200, best performances  |

## API Methods:

| Method  | Description |
| ------------- | ------------- |
| void setMaxCount(Integer maxPage)  | @param maxPage  -Max items to view  |
| void setCurrentPage(Integer currPage)  | @param currPage -Item which is currently showen  |

## Layout sample
Add custom view into your layout
```
 <com.makaji.aleksej.pagescroller.view.PageScrollerView_
        android:id="@+id/pageScrollerView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:numbersFading="200"
        custom:textChange="Page:"
        custom:animationSpeed="200"
        custom:heightOfElementsAndTextSize="30"
        custom:textColor="GRAY"
        />
```
## License
This project is licensed under the MIT License - see the LICENSE.md file for details
