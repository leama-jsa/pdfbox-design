[![](https://jitpack.io/v/leama-jsa/pdfbox-design.svg)](https://jitpack.io/#leama-jsa/pdfbox-design)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)
com.github.leama-jsa:pdfbox-design:0.0.1
# pdfbox-design
A fork of [pdfbox-layout](https://github.com/ralfstuckert/pdfbox-layout) with the latest PDFBox dependency and few improvements

A tiny layout library on top of pdfbox. Main features are

* word wrapping
* text alignment
* paragraphs 
* pagination

See the [Wiki](https://github.com/ralfstuckert/pdfbox-layout/wiki) for more information on the usage, or browse the [javadoc](https://jitpack.io/com/github/ralfstuckert/pdfbox-layout/pdfbox2-layout/1.0.0/javadoc/).

These articles may also be useful as a short introduction:
* [PDF text layout made easy with PDFBox-Layout](https://hardmockcafe.blogspot.de/2016/04/pdf-text-layout-made-easy-with-pdfbox_17.html)
* [Creating Lists with PDFBox-Layout](https://hardmockcafe.blogspot.de/2016/06/creating-lists-with-pdfbox-layout.html)
* [Hyperlinks with PDFBox-Layout](http://hardmockcafe.blogspot.de/2016/08/hyperlinks-with-pdfbox-layout_46.html)

#### Maven:

```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    
    <dependency>
        <groupId>com.github.leama-jsa</groupId>
        <artifactId>pdfbox-design</artifactId>
        <version>0.0.1</version>
    </dependency>
```

#### Gradle:

```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         implementation 'com.github.leama-jsa:pdfbox-design:0.0.1'
   }
```
