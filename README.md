# PageScroll

# How to install
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
| setMaxCount(Integer maxPage)  | @param maxPage  -Max items to view  |
| setCurrPage(Integer currPage)  | @param currPage -Item which is currently showen  |
	  
