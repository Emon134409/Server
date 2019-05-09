<p align="center">
  <img alt="opensnitch" src="img/wifi.png" height="150" />
  <p align="center">
    <img src="img/release.png" height="20">
    <a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="img/license.png" height="20"></a>
    <a href="http://www.beingup.net"><img src="img/website.png" height="20"></a>
  </p>
</p>
<p>Server is a light and fast library that makes networking for Android apps very simple. It is designed for transfer data between the Android app and server.</p>

Server offers the following benefits :
------
* It's light and fast.
* Easy to learn and use.
* Send data as Boolean, Integer, Float, Double, String, JSONArray and JSONObject.
* Receive data as String, JSONArray or JSONObject.
* Awesome methods and 100% controls.

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
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conn c = new Conn();
        c.data("hello world");
        c.connect("https://your_link.php");
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Conn()
        c.data("hello world")
        c.connect("https://your_link.php")
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

**PHP programm for server [your_link.php]**
```php
<?php 
require "conn.php";
$data = $_POST["app_data"]; // hello world

// your activity

$conn->close();
?>
```

**conn.php**
```php 
<?php 
$db_name = "databaseName";
$mysql_username = "username";
$mysql_password = "password";
$server_name = "serverName";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
?>
```

Methods
---------
**Sending Methods**
Those methods are using when developer want to connect with the server.

| Methods              | Description   |
| ---------------------|---------------|
| void data()          | This method takes data for send to the server. |
| void requestId()     | Every time Server Library will throw an integer `requestId` with the result. By default, it is `0`. Using this method the developer can change `requestId`. |
| void postName()      | For more security developer can change `postName` using this method. By default post name is `app_data`. |
| void errorMessage()  | A developer can throw an error from the server by print only the text `_error`. This is the default error message. To change this default error text developer can use this method. |
| void connect()       | This is the most important method and it is *required*. This method takes a string link as a parameter. It is required to write this method as the last method of this table. |


**Receiving Methods**
After server activity, those methods will run automatically.

| Methods                 | Description   |
| ------------------------|---------------|
| void ifRequestFailed()  | This method will invoke when the system can't connect with the server. |
| void ifNoResult()       | This method will invoke when server doesn't throw any result. |
| void ifError()          | This method will invoke when server throws an error message. |
| void result()           | This method will invoke when the server throws a valid data. |

Changelog
---------
* **1.0.0**
    * Initial release


License
-------
```
	MIT License

	Copyright (c) 2019 beingUP

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
```
