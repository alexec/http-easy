# HTTP-Easy

A library making it easy to do HTTP.

Example

~~~java
import httpeasy.Http.*;

public class Demo {
    public static void main(String[] args){
      String body = get("http://www.google.com").as(String.class);
    }
}
~~~