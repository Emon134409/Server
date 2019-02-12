<p align="center">
  <img alt="opensnitch" src="img/wifi.png" height="150" />
  <p align="center">
    <img src="img/release.png" height="20">
    <a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="img/license.png" height="20"></a>
    <a href="http://www.beingup.net"><img src="img/website.png" height="20"></a>
  </p>
</p>
<p>Server is a light and fast library that makes networking for Android apps very simple. It is designed for transfer data between the Android app and server. Another major advantage of this is that it is very friendly with PHP.</p>

Server offers the following benefits :
------
* It's light and fast.
* Easy to learn and use.
* Send data as Integer, Float, Double, String, JSONArray and JSONObject.
* Receive data as String, JSONArray or JSONObject.
* Awesome methods and 100% controls.
* Very friendly with PHP.

build.gradle (Project)
------
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

build.gradle (Module: app)
------
```
dependencies {
	implementation 'com.github.beingup:Server:1.0.0'
}
```

Usage
-----
**Example in JAVA**
```java
public class MainActivity extends AppCompatActivity {

    String TAG = "server123";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conn c = new Conn();
        c.data("data");
        c.connect("php page link");
    }

    class Conn extends Server {
        @Override
        public void ifRequestFailed(int i){
            Log.d(TAG, "Server request failed.");
        }

        @Override
        public void ifNoResult(int i){
            Log.d(TAG, "No result from server.");
        }

        @Override
        public void ifError(int i){
            Log.d(TAG, "Something error in server.");
        }

        @Override
        public void result (int i, String s, JSONArray ar, JSONObject ob){
            Log.d(TAG, "Data receive success.");
        }
    }
}
```

**Example in Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {
    
    val TAG = "server123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Conn()
        c.data("hello world")
        c.connect("your link")
    }

    inner class Conn : Server(){
        override fun ifRequestFailed(i: Int){
            Log.d(TAG, "Server request failed.")
        }

        override fun ifNoResult(i: Int){
            Log.d(TAG, "No result from server.")
        }

        override fun ifError(i: Int){
            Log.d(TAG, "Something error in server.")
        }

        override fun result(i: Int, s: String, ar: JSONArray, ob: JSONObject){
            Log.d(TAG, "Data receive success.")
        }
    }
}
```
