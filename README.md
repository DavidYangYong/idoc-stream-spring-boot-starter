# idoc-stream-spring-boot-starter
<p align="center">
  <a href="https://github.com/DavidYangYong/idoc-stream-spring-boot-starter">
   <img alt="idoc-stream-spring-boot-starter-Logo" src="https://img.shields.io/badge/gadget-Idoc%20Stream-pink.svg?
logo=data%3Aimage/png%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAAAsAAAAOCAYAAAD5YeaVAAACJ0lEQVR4AW2Qy0tUfxjGv3Pm3O%2BXGec3jnMZZ9Rxxp%2BWkYpJ2QkVIzDpkkoGBSOpBZmWgRIkWSs30SJchC26B9aijKLaFET9Be1q46ayRVDU5uk9Q8seeOG8X573eT/nZbwQqo5l5Rube52PQdH3BcaY4SbEiy2%2B/WFLv7ueblJv0luJmRHBHzqfwuyDRkzfKqB8pRZeQnwzuZynvgHTtwsYvZRBs2%2BVGSU5nfsjX2bocexqDsMLKXgZCQfmkzi6lK0EDJxOfNJdoYORQl5O3OeXY5%2BH59PI5XXUMh3RuIQR6v2J2Ld8h3488FXMBWb2TJkNPw9GUzirF3HX2oZFrQUD0Rqc84rfVcaPs0D/cTK/Q6y6N6eVsJX3MCglcUzJYVytw6CYxKRSj1Elu0bWCBNCXCnPGz96pTieOTvxyN6OIm/hid2Nx1SzWhGxsLwR4aQ9rFOMPl8lwwQlvXZ78NLZhRbBwZrTjReOjzuENKc1YUTOrLJ%2BKf7%2BsrEJhIL7dhfOaI34n7cxpRWwYnbghFqPMmENyelXzOLE0d1SNbrFGOKcgmDwnduH61Y7zJCAvVINDisZJMPqNEuHVeOQnPr11u3FESWLa2ZbBecp8VNaZXBBb15njHUxmxPC7YK3uGS0YkzNo1VwcZJWtwkeThHKMg33SfEVMqsskBeSjLqwMZYKa18ppXKRwESIv0u8NVPFyRb7hxK0ZYX%2BfIPO95D6KBXH/uoPnu/BfZ7Zxb0AAAAASUVORK5CYII">
  </a>
</p>

<p align="center">
  Born To Simplify Development
</p>

<p align="center">
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p>
## What is idoc-stream-spring-boot-starter?

idoc-stream-spring-boot-starter is an powerful enhanced toolkit of Stream Idoc for simplify development. This toolkit provides some
efficient,
useful, out-of-the-box features for Idoc, use it can effectively save your development time.

## Features

- Fully compatible with Stream
-   Auto configuration on startup
- Out-of-the-box interfaces for operate Idoc
-   Lambda-style API
-   Almighty and highly customizable code generator
- Automatic cache operation
- Jackson convert Inject defense
-   Support active record
-   Support pluggable custom interface
-   Build-in many useful extensions

## Getting started

- Add Idoc-Stream dependency
    - Latest Version: 3.3.5
    - Maven:
      ```xml
      <dependency>
          <groupId>com.moss.spring.boot</groupId>
          <artifactId>idoc-stream-spring-boot-starter</artifactId>
          <version>Latest Version</version>
      </dependency>
      ```
    - Gradle
      ```groovy
      compile group: 'com.moss.spring.boot', name: 'idoc-stream-spring-boot-starter', version: 'Latest Version'
      ```
-   Modify mapper file extends BaseMapper interface

    ```java
    public class MoreBaseServiceImpl extends AbstractBaseExecService<T> implements IBaseExecService<T> {
    
    }
    ```

- Use exec logic
  ``` java
  	@Override
	public T exec(T t) {
		return t;
	}
  ```
## License

Idoc-Stream is under the Apache 2.0 license. See the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) file for details.
