# 1.VMG Android SDK

Welcome to our android SDK. What this SDK does, it allows you to play "outstream" video advertisment from VMG SSP ad server. 
To get a feeling of how it works, we included a demo to see an example of how to make use of this library.
It is a very easy to use library. This library is compatible for every screen size and even for tablets. We made our library as simple as possible so you don't need to worry
much about the code you need to implement, when your main focus should be the app you are developing.

We as VMG are very dedicated to our customers and we want our customers to have the most simple, elegant but strong systems. We as VMG are very proud to share with you our SDK.


What we want to achieve in this documentation is showing you how to implement our library into your project. We have a couple of simple steps. After you have red the steps,
we have a full example, this is mainly for if you did not understand a part of the steps. 

Enjoy our library!

![alt text](http://www.videomediagroup.com/wp-content/uploads/2016/01/logo-transparant-website.png)


## 1.2 Run the demo
Are you a little bit unpatient? Well for you who are inpatient to see it in action, we provided a little demo app, so you can see it in action.
In order to run the demo app, you need to do the following things:
* clone this repository
* save it in a folder
* open android studio
* open the cloned repository and let the gradle build

Once the waiting is done and the gradle is build, you can run the demo. We hope you enjoy this demo as much as we liked to build it.

## 1.3 How to download the VMG SDK library
In order to use this library you can add it to your project as a gradle dependency. You can add this gradle dependency inside your gradle file.
Once you have implemented this dependency, you can use the methods of the classes inside the library. This library contains all the classes you need to load a advertisment
in your application. 
We have a little example on how to add the dependency.

First of all add our Maven repository
```java
repositories {
 
    maven {
        url  "http://dl.bintray.com/videomediagroup/VMG"
    }
}
```
After this step you need to compile our gradle dependency inside your **Build.gradle** in the **Build.gradle (Module:app)**. Here you need to 
add this line of code **compile 'com.VMG.Vmgsdklibrary:vmgsdklib:1.0.2'**. Just like this:

```java
    dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
   compile 'com.VMG.Vmgsdklibrary:vmgsdklib:1.0.2'
}
```
You don't need to add:

```java
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```
in your **AndroidManifest.XML**. This will be done by our library.
Congratulations on finishing the first steps,now you can start using our libary.
in the following section you will see how to integrate the library and where to add the code.

Here you will find a full example:

```java
 apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    buildToolsVersion "26.0.1"
    defaultConfig {
        applicationId "demo.videomediagroup.com.vmgdemo"
        minSdkVersion 21
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
repositories {
 
    maven {
        url  "http://dl.bintray.com/videomediagroup/VMG"
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
   compile 'com.VMG.Vmgsdklibrary:vmgsdklib:1.0.2'
}

```
# 2.How to integrate the library into your app?

 Now we will explain how to use the library and how to implement the library code inside your app. Just follow the simple steps 
 and everything will be allright. If you don't understand something you can always scroll back and check it. If you still don't understand it, you can scroll down
 and check the full example. Let's begin.
 
## 2.1 Integrate the SDK

The first thing you need todo is compile the library as a gradle dependency, **in section 1.3 you can read how to do that**. After that is done, 
you need to add the following line of code inside your **MainActivity**, in the `onCreate()` method.

`VMGConfig.loadConfig(getApplicationContext(), configurationId);`

so this will look like this:
```java
public class MainActivity extends AppCompatActivity {
   
 @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        VMGConfig.loadConfig(getApplicationContext(), configurationId);
}
```

This line of code will make sure that the configuration is loaded through the lifetime of the app.
after you have added the line of code above, create a **Fragment**. Add the following lines of code:

create a new private instance of the **VMGBase** class.
`private VMGBase vmgBase;`

after that is done, add the following line of code inside the `onCreate()` or inside the `onViewCreated()` method.
`vmgBase = new VMGBase(getActivity(), webView,placementId);`.
This will instantiate the **VMGBase class** and we need to give it three arguments:

* the context (getActivity())
* The WebView you want to load the advertisment in
* and the placementId of the ad

This will look like this:
```java
    public class ExampleFragment extends Fragment {
    private WebView webView;
    private VMGBase vmgBase;

    public ExampleFragment() {
    }

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example, container, false);
      
        webView = view.findViewById(R.id.webView);
        
        vmgBase = new VMGBase(getActivity(), webView, placementId);
       
        return view;
    }
}
```
You must add these lines of code in every **Fragment** You want to load your advertisment.
We have a special scroll event which you need to implement when you have an advertisment that needs to load inRead. In the following section we will see how you can implement this.

## 2.3 VMG scroll event
You add the following lines of code If you have an advertisment that needs to open inRead when the user of the app is scrolling through the app.

 ```java
 public class ExampleFragment extends Fragment {
    private WebView webView;
    private VMGBase vmgBase;
    private NestedScrollView nestedScrollView;
    private RelativeLayout rootLayout;
    

    public ExampleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example, container, false);
        
        nestedScrollView = view.findViewById(R.id.scrollview);
        rootLayout = view.findViewById(R.id.rootLayout);
        webView = view.findViewById(R.id.webView);
        
        vmgBase = new VMGBase(getActivity(), webView, placementId);
        
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                vmgBase.VMGScrollEvent(scrollY, scrollX, rootLayout);
            }
        });


        return view;
    }
}

 ```
What we mean by **rootLayout** is the layout you use as the parent in your **layout XML file**.
```java
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/rootLayout" 
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:animateLayoutChanges="true"
    tools:context="com.example.SDKDemo.ScrollPage">

```
As you can see by rootLayout we mean the layout that is on top of the XML file.

Above you see how to add the **scrollEvent** inside the **Fragment class** you want to load the advertisment. These were the simple steps to make use of our library.
Easy right? In the following section we have a full example. 

# 3.Full example
We will show you a full example of how it is done. First we start with the line of code we need to add in the **MainActivity**
```java
    public class MainActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        VMGConfig.loadConfig(getApplicationContext(), configurationId);
 }

```
We added the line of code in the **MainActivity** now we need to add some code in the **Fragment** we want to load the add in.

```java
    public class YourFragment extends Fragment {
    private WebView webView;
    private VMGBase vmgBase;
    private NestedScrollView scrollView;
    private RelativeLayout rootLayout;
   
     public YourFragment() {
    }

   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                             
        final View view = inflater.inflate(R.layout.fragment_your, container, false);
        
        scrollView = view.findViewById(R.id.scrollView);
        rootLayout = view.findViewById(R.id.rootLayout);
        webView = view.findViewById(R.id.webView);
        
        vmgBase = new VMGBase(getActivity(), webView, placementId);

        
        scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            
                vmgBase.VMGScrollEvent(scrollY, scrollX, rootLayout);
            }
        });


        return view;
    }
}

```
As you can see it is just a little bit of code that you need to include. We hope these examples have helped you to add the technology into your app.

# 4.Author
Seyfullah Semen -> seyfullah.semen@videomediagroup.com

